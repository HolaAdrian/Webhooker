package de.adrian.webhooker.Listeners;

import de.adrian.webhooker.Webhooker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    FileConfiguration config = Webhooker.config;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!Webhooker.config.getBoolean("loginwebhook.do") == true){
            return;
        }
        Player player = event.getPlayer();
        String webhookmessage = config.getString("loginwebhook.titlejoin") + "\n" + player.getName() + " " + config.getString("loginwebhook.messagejoin");
        Webhooker.SendDiscordBanWebhook(webhookmessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!Webhooker.config.getBoolean("loginwebhook.do") == true){
            return;
        }
        Player player = event.getPlayer();
        String webhookmessage = config.getString("loginwebhook.titlequit") + "\n" + player.getName() + " " + config.getString("loginwebhook.messagequit");
        Webhooker.SendDiscordBanWebhook(webhookmessage);
    }
}
