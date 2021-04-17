package net.portalsam.currencyconverter.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configuration {

    public static FileConfiguration config = null;

    public static String apiKey = null;

    /*/ Setup and save configuration /*/
    public static void setup(JavaPlugin plugin) {

        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        /*/ Default moneyAPI key. /*/
        config.addDefault("apiKey", "0fb286389316ad37a985");

        config.options().copyDefaults(true);
        plugin.saveConfig();

        apiKey = config.getString("apiKey");

        assert apiKey != null;
        if(apiKey.equals("0fb286389316ad37a985"))
            plugin.getLogger().warning(" - You are still using the default API Key! " +
                    "Remaining queries may already be zero, it is HIGHLY recommended you get your own API Key from " +
                    "https://free.currconv.com/. The plugin will still function without updating but results may be inaccurate.");

    }

    /*/ Getters. /*/

    public static String getApiKey() {
        return apiKey;
    }

}
