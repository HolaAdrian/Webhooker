package de.adrian.webhooker;

import com.google.gson.JsonObject;
import de.adrian.webhooker.Utitlity.Importer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public final class Webhooker extends JavaPlugin {

    private static URL webhookurl;

    public static FileConfiguration config;
    public static Webhooker main;

    public static Logger logger;




    @Override
    public void onEnable() {

        logger = this.getLogger();
        this.getLogger().info("Hello and thanks for using Webhooker!");
        if (!getConfig().isSet("webhook")){
            saveDefaultConfig();
        }

        config = getConfig();
        main = this;
        if (getConfig().isSet("webhook")){
            try {
                webhookurl = new URL(getConfig().getString("webhook"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            this.getLogger().warning("No Webhook was set!");
        }


        Importer.ImportAll(Bukkit.getPluginManager(), this);
    }


    public static void SendDiscordBanWebhook(String content){

        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("content", content);
            HttpsURLConnection urlConnection = (HttpsURLConnection) webhookurl.openConnection();
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");


            OutputStream stream = urlConnection.getOutputStream();
            stream.write(jsonObject.toString().getBytes());
            stream.flush();
            stream.close();

            urlConnection.getInputStream().close();
            urlConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Goodbye and thanks for using Webhooker!");
    }
}
