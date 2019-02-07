package de.lhochbaum.customborders;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class BorderCommand implements CommandExecutor {
    private final BorderMenu menu;

    @Inject
    public BorderCommand(final BorderMenu menu) {
        this.menu = menu;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        menu.show((Player) sender, 0);
        return true;
    }
}
