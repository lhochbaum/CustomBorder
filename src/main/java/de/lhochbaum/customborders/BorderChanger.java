package de.lhochbaum.customborders;

import com.google.inject.Inject;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class BorderChanger {
    private final PlotAPI api;

    @Inject
    public BorderChanger(final PlotAPI api) {
        this.api = api;
    }

    public void change(final Player player, final Material material) {
        final PlotPlayer plotPlayer = api.wrapPlayer(player);
        plotPlayer.getPlots().forEach(p -> change(p, material));
    }

    public void change(final Plot plot, final Material material) {
        change(plot, material.name().toLowerCase());
    }

    public void change(final Plot plot, final String materialName) {
        plot.setComponent("wall", materialName);
        plot.setComponent("border", materialName);
    }
}
