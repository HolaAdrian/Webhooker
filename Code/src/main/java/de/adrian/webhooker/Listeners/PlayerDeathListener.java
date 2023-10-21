package de.adrian.webhooker.Listeners;

import de.adrian.webhooker.Webhooker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    FileConfiguration config = Webhooker.config;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!Webhooker.config.getBoolean("deathwebhook.do") == true){
            return;
        }
        Player player = event.getEntity();
        String webhookmessage = config.getString("deathwebhook.title") + "\n" + player.getName() + " " + config.getString("deathwebhook.message");
        Webhooker.SendDiscordBanWebhook(webhookmessage);

    }
}
