package net.portalsam.currencyconverter.api;

import net.milkbowl.vault.economy.Economy;
import net.portalsam.currencyconverter.CurrencyConverter;
import org.bukkit.plugin.RegisteredServiceProvider;

/*/ Keep stuff in here to not cause errors. /*/
public class EconomyAPIContainer {

    public static boolean vaultEnabled = false;

    private static Economy econ = null;

    public static void initialize() {
        setupVault();
    }

    /*/ Vault functions. /*/

    private static void setupVault() {

            RegisteredServiceProvider<Economy> rsp = CurrencyConverter.getInstance().getServer().getServicesManager().getRegistration(Economy.class);

            if (rsp == null) return;

            econ = rsp.getProvider();
            vaultEnabled = true;

    }

    /*/ Getters. /*/

    public static boolean isVaultEnabled() {
        return vaultEnabled;
    }

    public static Economy getEcon() {
        return econ;
    }

}
