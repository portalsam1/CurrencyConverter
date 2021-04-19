package net.portalsam.currencyconverter;

import net.portalsam.currencyconverter.api.CurrencyAPI;
import net.portalsam.currencyconverter.api.EconomyAPIContainer;
import net.portalsam.currencyconverter.commands.ConvertBalanceCommand;
import net.portalsam.currencyconverter.commands.ConvertCurrencyCommand;
import net.portalsam.currencyconverter.commands.CurrencyUpdateCommand;
import net.portalsam.currencyconverter.commands.tabcomplete.ConvertBalanceTabComplete;
import net.portalsam.currencyconverter.commands.tabcomplete.ConvertCurrencyTabComplete;
import net.portalsam.currencyconverter.commands.tabcomplete.CurrencyUpdateTabComplete;
import net.portalsam.currencyconverter.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.logging.Logger;

public final class CurrencyConverter extends JavaPlugin {

    /*/ Paper variables. /*/

    private static final Logger log = Logger.getLogger("Minecraft");

    /*/ Plugin variables. /*/

    public static final String pluginHeader = ChatColor.WHITE + "[" + ChatColor.GREEN + "Currency" + ChatColor.AQUA + "Converter" + ChatColor.WHITE + "]";
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public static CurrencyConverter INSTANCE = null;

    /*/ Paper default functions. /*/

    @Override
    public void onEnable() {

        /*/ Make this class accessible from elsewhere. /*/
        INSTANCE = this;

        /*/ Setup config. /*/
        Configuration.setup(this);
        CurrencyAPI.initialize(this);

        /*/ Dependency setup. /*/
        checkForVault();

        /*/ Plugin setup. /*/
        registerCommands();

        log.info(String.format("[%s] - Has been enabled.", this.getDescription().getName()));

    }

    @Override
    public void onDisable() {

        log.info(String.format("[%s] - Has been disabled.", this.getDescription().getName()));

    }

    /*/ Vault functions. /*/
    private void checkForVault() {
        /*/ If vault is detected, initialize the EconomyAPI class so we dont cause any errors if it isn't. /*/
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            log.info(String.format("[%s] - No vault dependency found, '/convertbalance' will be disabled.", CurrencyConverter.getInstance().getDescription().getName()));
        else
            EconomyAPIContainer.initialize();
    }

    /*/ Setup. /*/

    private void registerCommands() {

        /*/ Command executors. /*/
        Objects.requireNonNull(this.getCommand("convertcurrency")).setExecutor(new ConvertCurrencyCommand());
        Objects.requireNonNull(this.getCommand("convertbalance")).setExecutor(new ConvertBalanceCommand());
        Objects.requireNonNull(this.getCommand("updatecurrency")).setExecutor(new CurrencyUpdateCommand());

        /*/ Command tab completes. /*/
        Objects.requireNonNull(this.getCommand("convertcurrency")).setTabCompleter(new ConvertCurrencyTabComplete());
        Objects.requireNonNull(this.getCommand("convertbalance")).setTabCompleter(new ConvertBalanceTabComplete());
        Objects.requireNonNull(this.getCommand("updatecurrency")).setTabCompleter(new CurrencyUpdateTabComplete());

    }

    /*/ Getters. /*/

    public static String getPluginHeader() {
        return pluginHeader;
    }

    public static CurrencyConverter getInstance() {
        return INSTANCE;
    }

    public static Logger getLog() {
        return log;
    }

    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

}
