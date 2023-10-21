package de.adrian.webhooker.Utitlity;

import de.adrian.webhooker.Listeners.JoinQuitListener;
import de.adrian.webhooker.Listeners.PlayerDeathListener;
import de.adrian.webhooker.Webhooker;
import org.bukkit.plugin.PluginManager;

public class Importer {

    public static void ImportAll(PluginManager pluginManager, Webhooker main){
        ImportListeners(pluginManager, main);

    }


    private static void ImportListeners(PluginManager pluginManager, Webhooker main){
        pluginManager.registerEvents(new JoinQuitListener(), main);
        pluginManager.registerEvents(new PlayerDeathListener(), main);
    }
}
