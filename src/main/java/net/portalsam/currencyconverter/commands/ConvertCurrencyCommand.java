package net.portalsam.currencyconverter.commands;

import net.portalsam.currencyconverter.CurrencyConverter;
import net.portalsam.currencyconverter.currency.CurrencyProcessor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ConvertCurrencyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        /*/ If there are less than one argument throw an error in chat. /*/
        if(args.length<1) {

            sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "You must specify two currencies!");

        } else if(args.length == 1) { /*/ If there is only one argument specified print the value of the currency provided. /*/

            sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + CurrencyProcessor.currencyValue(args[0]));

        } else { /*/ If two arguments provided attempt to convert the first value provided into the second currency provided. /*/

            try {

                /*/ Abhorrent mess to handle decimal numbers. /*/
                String[] tempArgument = args[0].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                String[] currencyArgumentsOne = new String[2];

                if (args[0].contains(".")) {
                    currencyArgumentsOne[0] = tempArgument[0] + tempArgument[1] + tempArgument[2];
                    currencyArgumentsOne[1] = tempArgument[3];
                } else {
                    currencyArgumentsOne[0] = tempArgument[0];
                    currencyArgumentsOne[1] = tempArgument[1];
                }
                /*/ End abhorrent mess. /*/

                /*/ Get the converted amount. /*/
                double convertedAmount = CurrencyProcessor.convertCurrency(Double.parseDouble(currencyArgumentsOne[0]), currencyArgumentsOne[1], args[1]);

                /*/ If the value returned back is a negative number the currency given was invalid. /*/
                if (convertedAmount > 0) {
                    sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + CurrencyConverter.getDecimalFormat().format(Double.parseDouble(currencyArgumentsOne[0])) + " " + ChatColor.GREEN + currencyArgumentsOne[1].toUpperCase() +
                            ChatColor.WHITE + " is " + CurrencyConverter.getDecimalFormat().format(convertedAmount) + " " + ChatColor.GREEN + args[1].toUpperCase());
                } else {
                    sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "Invalid currency!");
                }

            } catch (Exception e) { /*/ Add this as a catch for stuff I'm too lazy to check :3 /*/
                sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "Unexpected input!");
            }

        }

        return true;
    }

}
