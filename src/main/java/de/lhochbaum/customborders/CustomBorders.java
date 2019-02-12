package de.lhochbaum.customborders;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.flag.Flag;
import com.intellectualcrafters.plot.flag.Flags;
import com.intellectualcrafters.plot.flag.StringFlag;
import de.lhochbaum.customborders.handlers.HandlerRepository;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class CustomBorders extends JavaPlugin {
    public static final Flag<String> FLAG = new StringFlag("wall");

    @Override
    public void onEnable() {
        // create main component.
        final BorderComponent component = DaggerBorderComponent.builder()
            .plugin(this)
            .build();

        // register the custom flag.
        Flags.registerFlag(FLAG);
        final PlotAPI api = component.plotAPI();
        api.addFlag(FLAG);

        // create repository which contains our listeners.
        final HandlerRepository handlerRepo = component.handlerBuilder().build();

        // register our listeners.
        final Listener[] listeners = new Listener[] {
            // listener instances go here.

            handlerRepo.mergeHandler(),
            component.borderMenu()
        };
        Arrays.stream(listeners).forEach(l -> getServer().getPluginManager().registerEvents(l, this));

        // register the command.
        getCommand("wand").setExecutor(component.borderCommand());
    }
}
