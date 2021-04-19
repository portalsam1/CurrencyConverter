package net.portalsam.currencyconverter.commands;

import net.portalsam.currencyconverter.CurrencyConverter;
import net.portalsam.currencyconverter.api.EconomyAPIContainer;
import net.portalsam.currencyconverter.currency.CurrencyProcessor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConvertBalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        /*/ If vault is not found, throw an error in chat when trying to use this command. Otherwise run it normally /*/
        if(EconomyAPIContainer.isVaultEnabled()) {

            /*/ If there are less than one argument throw an error in chat. /*/
            if(args.length<1) {

                sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "You must specify two currencies!");

            } else { /*/ If two arguments provided attempt to convert the players balance into the second currency provided. /*/

                try {

                    /*/ Store player balance. /*/
                    double playerBalance = EconomyAPIContainer.getEcon().getBalance((Player) sender);

                    /*/ If the player balance is 0, don't bother converting. /*/
                    if(playerBalance <= 0) {
                        sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "Your balance is zero! Can not convert.");
                        return true;
                    }

                    /*/ Get the converted amount. /*/
                    double convertedAmount = CurrencyProcessor.convertCurrency(EconomyAPIContainer.getEcon().getBalance((Player)sender), args[0], args[1]);

                    /*/ If the value returned back is a negative number the currency given was invalid. /*/
                    if (convertedAmount > 0) {

                        sender.sendMessage(
                                CurrencyConverter.getPluginHeader() + ": " +
                                        EconomyAPIContainer.getEcon().getBalance((Player)sender) + " " +
                                                ChatColor.GREEN + args[0].toUpperCase() +
                                                ChatColor.WHITE + " is " + CurrencyConverter.getDecimalFormat().format(convertedAmount) + " " +
                                                ChatColor.GREEN + args[1].toUpperCase()
                        );

                    } else {
                        sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "Invalid currency!");
                    }

                } catch (Exception e) { /*/ Add this as a catch for stuff I'm too lazy to check :3 /*/
                    sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "Unexpected input!");
                    e.printStackTrace();
                }
            }
        } else {
            sender.sendMessage(CurrencyConverter.getPluginHeader() + ": " + ChatColor.RED + "This command is disabled due to Vault not being installed on this server.");
        }

        return true;
    }

}
