package net.portalsam.currencyconverter.commands;

import net.portalsam.currencyconverter.CurrencyConverter;
import net.portalsam.currencyconverter.currency.CurrencyProcessor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class ConvertCurrencyCommand implements CommandExecutor {

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(args.length<1) {
            sender.sendMessage(CurrencyConverter.pluginHeader + ": " + ChatColor.RED + "You must specify two currencies!");
        } else if(args.length == 1) {
            sender.sendMessage(CurrencyConverter.pluginHeader + ": " + CurrencyProcessor.currencyValue(args[0]));
        } else {

            String[] tempArgument = args[0].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            String[] currencyArgumentsOne = new String[2];
            if(args[0].contains(".")) {
                currencyArgumentsOne[0] = tempArgument[0] + tempArgument[1] + tempArgument[2];
                currencyArgumentsOne[1] = tempArgument[3];
            } else {
                currencyArgumentsOne[0] = tempArgument[0];
                currencyArgumentsOne[1] = tempArgument[1];
            }

            double convertedAmount = CurrencyProcessor.convertCurrency(Double.parseDouble(currencyArgumentsOne[0]), currencyArgumentsOne[1], args[1]);

            if(convertedAmount > 0) {
                sender.sendMessage(CurrencyConverter.pluginHeader + ": " + decimalFormat.format(Double.parseDouble(currencyArgumentsOne[0])) + " " + ChatColor.GREEN + currencyArgumentsOne[1].toUpperCase() +
                        ChatColor.WHITE + " is " + decimalFormat.format(convertedAmount) + " " + ChatColor.GREEN + args[1].toUpperCase());
            } else {
                sender.sendMessage(CurrencyConverter.pluginHeader + ": " + ChatColor.RED + "Invalid currency!");
            }

        }

        return true;
    }

}
