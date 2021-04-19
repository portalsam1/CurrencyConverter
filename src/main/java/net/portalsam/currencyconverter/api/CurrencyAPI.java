package net.portalsam.currencyconverter.api;

import net.portalsam.currencyconverter.CurrencyConverter;
import net.portalsam.currencyconverter.configuration.Configuration;
import net.portalsam.currencyconverter.currency.CurrencyProcessor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CurrencyAPI {

    private static JavaPlugin javaPlugin;

    public static String baseURL = "https://free.currconv.com/api/v7/convert?q=";

    public static URL currencyAPIURLOne = null;
    public static URL currencyAPIURLTwo = null;
    public static URL currencyAPIURLThree = null;

    public static HashMap<String, Double> currencyMap = new HashMap<>();

    public static BukkitTask updateTask;

    /*/ Setup API Urls and populate the CurrencyMap. /*/
    public static void initialize(JavaPlugin plugin) {

        javaPlugin = plugin;

        try {
            currencyAPIURLOne = new URL(baseURL + "USD_CAD,USD_AUD&compact=ultra&apiKey=" + Configuration.getApiKey());
            currencyAPIURLTwo = new URL(baseURL + "USD_GBP,USD_EUR&compact=ultra&apiKey=" + Configuration.getApiKey());
            currencyAPIURLThree = new URL(baseURL + "USD_JPY&compact=ultra&apiKey=" + Configuration.getApiKey());
        } catch (MalformedURLException e) {
            CurrencyConverter.getLog().severe(String.format("[%s] - MalformedURL, this should not happen. Disabling plugin.",
                    plugin.getDescription().getName()));
            javaPlugin.getPluginLoader().disablePlugin(javaPlugin);
        }

        updateCurrencyMap();

    }

    /*/ Get all the currencies we want to support, clear the currencyMap and save them to it. /*/
    public static void updateCurrencyMap() {

        javaPlugin.getLogger().info(" - Updating currency data.");

        String apiDataOne;
        String apiDataTwo;
        String apiDataThree;

        try {

            apiDataOne = readUrl(currencyAPIURLOne);
            apiDataTwo = readUrl(currencyAPIURLTwo);
            apiDataThree = readUrl(currencyAPIURLThree);

            JSONParser parser = new JSONParser();

            JSONObject jsonObjectOne = (JSONObject) parser.parse(apiDataOne);
            JSONObject jsonObjectTwo = (JSONObject) parser.parse(apiDataTwo);
            JSONObject jsonObjectThree = (JSONObject) parser.parse(apiDataThree);

            currencyMap.clear();

            currencyMap.put("USD_CAD", (Double)jsonObjectOne.get("USD_CAD"));
            currencyMap.put("USD_AUD", (Double)jsonObjectOne.get("USD_AUD"));
            currencyMap.put("USD_GBP", (Double)jsonObjectTwo.get("USD_GBP"));
            currencyMap.put("USD_EUR", (Double)jsonObjectTwo.get("USD_EUR"));
            currencyMap.put("USD_JPY", (Double)jsonObjectThree.get("USD_JPY"));

            CurrencyProcessor.updateValues(currencyMap.get("USD_CAD"), currencyMap.get("USD_AUD"),
                    currencyMap.get("USD_GBP"), currencyMap.get("USD_EUR"), currencyMap.get("USD_JPY"));

        } catch (Exception e) {
            e.printStackTrace();
            CurrencyConverter.getLog().severe(String.format("[%s] - Could not read from URL, is it down, are you out of requests?",
                    CurrencyConverter.getInstance().getDescription().getName()));
        }

        /*/ Update currencyMap every 2 hours. /*/
        updateTask = Bukkit.getScheduler().runTaskLater(javaPlugin, CurrencyAPI::updateCurrencyMap, 144000);

    }

    /*/ Utility functions. /*/

    private static String readUrl(URL url) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        }
    }

}
