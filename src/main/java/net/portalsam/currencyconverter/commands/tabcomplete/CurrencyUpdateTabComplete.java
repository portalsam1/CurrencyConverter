package net.portalsam.currencyconverter.commands.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CurrencyUpdateTabComplete implements TabCompleter {

    private static final List<String> DISABLE_TEXT = new ArrayList<>();

    /*/ Lazy storing. /*/
    static {
        DISABLE_TEXT.add("disable");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        /*/ Return the text 'disable' for the update currency command. /*/
        if (args.length == 1)
            return DISABLE_TEXT;

        return null;

    }

}
