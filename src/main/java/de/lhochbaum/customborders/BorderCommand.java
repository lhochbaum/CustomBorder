package de.lhochbaum.customborders;

import com.google.inject.Inject;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class BorderCommand implements CommandExecutor {
    private final PlotAPI api;

    @Inject
    public BorderCommand(final PlotAPI api) {
        this.api = api;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, String[] args) {
        return false;
    }
}
