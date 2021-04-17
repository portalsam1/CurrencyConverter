package net.portalsam.currencyconverter.commands;

import net.portalsam.currencyconverter.CurrencyConverter;
import net.portalsam.currencyconverter.api.CurrencyAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CurrencyUpdateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length < 1) {
            Bukkit.getScheduler().cancelTask(CurrencyAPI.updateTask.getTaskId());
            CurrencyAPI.updateCurrencyMap();
            sender.sendMessage(CurrencyConverter.pluginHeader + ": Attempting to refresh currency data.");
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("disable")) {
                Bukkit.getScheduler().cancelTask(CurrencyAPI.updateTask.getTaskId());
                sender.sendMessage(CurrencyConverter.pluginHeader + ": Updating currency " + ChatColor.RED + "disabled" +
                        ChatColor.WHITE + ", use /updatecurrency to re-enable.");
            } else {
                sender.sendMessage(CurrencyConverter.pluginHeader + ": " + ChatColor.RED + "Invalid argument.");
            }
        } else {
            sender.sendMessage(CurrencyConverter.pluginHeader + ": " + ChatColor.RED + "Invalid number of arguments.");
        }

        return true;

    }

}
