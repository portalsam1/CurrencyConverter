package net.portalsam.currencyconverter;

import net.portalsam.currencyconverter.api.CurrencyAPI;
import net.portalsam.currencyconverter.commands.ConvertCurrencyCommand;
import net.portalsam.currencyconverter.commands.CurrencyUpdateCommand;
import net.portalsam.currencyconverter.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class CurrencyConverter extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static final String pluginHeader = ChatColor.WHITE + "[" + ChatColor.GREEN + "Currency" + ChatColor.AQUA + "Converter" + ChatColor.WHITE + "]";

    /*/ Paper default functions. /*/

    @Override
    public void onEnable() {

        Configuration.setup(this);
        CurrencyAPI.initialize(this);
        registerCommands();

        log.info(String.format("[%s] - Has been enabled.", pluginHeader));

    }

    @Override
    public void onDisable() {

        log.info(String.format("[%s] - Has been disabled.", pluginHeader));

    }

    /*/ Setup. /*/

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("convertcurrency")).setExecutor(new ConvertCurrencyCommand());
        Objects.requireNonNull(this.getCommand("updatecurrency")).setExecutor(new CurrencyUpdateCommand());
    }

    /*/ Getters. /*/

    public static Logger getLog() {
        return log;
    }

}
