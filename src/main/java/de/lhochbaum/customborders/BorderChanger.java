package de.lhochbaum.customborders;

import com.google.inject.Inject;
import com.intellectualcrafters.plot.api.PlotAPI;
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

        plotPlayer.getPlots().forEach(plot ->
            plot.setComponent("border", "minecraft:" + material.name()));
    }
}
