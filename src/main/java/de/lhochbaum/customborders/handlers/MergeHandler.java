package de.lhochbaum.customborders.handlers;

import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.events.PlotMergeEvent;
import de.lhochbaum.customborders.BorderChanger;
import de.lhochbaum.customborders.CustomBorders;
import de.lhochbaum.customborders.di.scope.HandlerScope;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;

@HandlerScope
public final class MergeHandler implements Listener {
    private final BorderChanger changer;
    private final CustomBorders plugin;

    @Inject
    public MergeHandler(final BorderChanger changer, final CustomBorders plugin) {
        this.changer = changer;
        this.plugin = plugin;
    }

    @EventHandler
    public void onMerge(final PlotMergeEvent event) {
        final Plot plot = event.getPlot();

        // ugly af.
        plot.getConnectedPlots().stream()
            .filter(p -> p.hasFlag(CustomBorders.FLAG))
            .findAny()
            .ifPresent(p -> {
                final Material material = Material.matchMaterial(p.getFlag(CustomBorders.FLAG).get());

                Bukkit.getScheduler().runTaskLater(plugin, () ->
                    p.getConnectedPlots().forEach(c -> changer.change(c, material)), 3 * 20L);
            });
    }
}
