package net.portalsam.currencyconverter.currency;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;

public class CurrencyProcessor {

    /*/ Default values from Saturday April 17, 2021. One US Dollar is worth 'x' in these currencies./*/
    public static final double USD = 1;
    public static double cad = 1.250555;
    public static double aud = 1.292992;
    public static double gbp = 0.722543;
    public static double eur = 0.834589;
    public static double jpy = 108.817504;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void updateValues(double newCad, double newAud, double newGbp, double newEur, double newJpy) {

        cad = newCad;
        aud = newAud;
        gbp = newGbp;
        eur = newEur;
        jpy = newJpy;

    }

    public static String currencyValue(String currency) {

        currency = currency.toUpperCase();

        switch (currency) {
            case "USD":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + USD + ChatColor.GREEN + " US Dollar";
            case "CAD":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + decimalFormat.format(cad) + ChatColor.GREEN + " Canadian Dollar";
            case "AUD":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + decimalFormat.format(aud) + ChatColor.GREEN + " Australian Dollar";
            case "GBP":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + decimalFormat.format(gbp) + ChatColor.GREEN + " Great British Pound";
            case "EUR":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + decimalFormat.format(eur) + ChatColor.GREEN + " Euro";
            case "JPY":
                return "One " + ChatColor.GREEN + "US Dollar" + ChatColor.WHITE + " is worth " + decimalFormat.format(jpy) + ChatColor.GREEN + " Japanese Yen";
            default:
                return ChatColor.RED + "Invalid currency specified.";
        }

    }

    /*/ Converting functions. /*/

    public static double convertCurrency(double fromAmount, String fromCurrency, String toCurrency)
    {
        double usd = otherCurrencyToUSD(fromAmount, fromCurrency);
        return usdToOtherCurrency(usd, toCurrency);
    }

    public static double usdToOtherCurrency(double usd, String otherCurrency)
    {

        otherCurrency = otherCurrency.toUpperCase();

        switch (otherCurrency)
        {
            case "USD":
                return usd;
            case "CAD":
                return usd * cad;
            case "AUD":
                return usd * aud;
            case "GBP":
                return usd * gbp;
            case "EUR":
                return usd * eur;
            case "JPY":
                return usd * jpy;
            default:
                return -1d;

        }
    }

    public static double otherCurrencyToUSD(double amount, String otherCurrency)
    {

        otherCurrency = otherCurrency.toUpperCase();

        switch (otherCurrency)
        {
            case "USD":
                return amount;
            case "CAD":
                return amount / cad;
            case "AUD":
                return amount / aud;
            case "GBP":
                return amount / gbp;
            case "EUR":
                return amount / eur;
            case "JPY":
                return amount / jpy;
            default:
                return -1d;

        }
    }

}
