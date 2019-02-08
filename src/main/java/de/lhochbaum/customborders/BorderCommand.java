package de.lhochbaum.customborders;

import com.google.inject.Inject;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class BorderCommand implements CommandExecutor {
    private final BorderMenu menu;
    private final PlotAPI api;

    @Inject
    public BorderCommand(final BorderMenu menu, final PlotAPI api) {
        this.menu = menu;
        this.api = api;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;
        final PlotPlayer plotPlayer = api.wrapPlayer(player);

        if (plotPlayer.getPlots().size() == 0) {
            player.sendMessage("§cDu besitzt keine Plots.");
            return true;
        }

        menu.show(player, 0);
        return true;
    }
}
