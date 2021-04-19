package net.portalsam.currencyconverter.commands.tabcomplete;

import net.portalsam.currencyconverter.currency.CurrencyProcessor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConvertBalanceTabComplete implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        /*/ Show all the currency types for tab complete on balance convert command. /*/
        switch (args.length) {
            case 1:
            case 2:
                return CurrencyProcessor.currencyList;
            default:
                return null;
        }

    }

}
