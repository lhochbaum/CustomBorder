package de.lhochbaum.customborders;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class CustomBorders extends JavaPlugin {
    @Override
    public void onEnable() {
        final Injector injector = Guice.createInjector(binder -> {
            // bind the plugin instance for other classes.
            binder.bind(CustomBorders.class).toInstance(this);

            // create API instance and bind it.
            binder.bind(PlotAPI.class).toInstance(new PlotAPI());

            // make sure the changer and menu are singleton.
            binder.bind(BorderChanger.class).in(Singleton.class);
            binder.bind(BorderMenu.class).in(Singleton.class);
        });

        // register our listeners.
        Listener[] listeners = new Listener[] {
            // listener instances go here.

            injector.getInstance(BorderMenu.class),
        };
        Arrays.stream(listeners).forEach(l -> getServer().getPluginManager().registerEvents(l, this));

        // register the command for opening the GUI.
        getCommand("wand").setExecutor(injector.getInstance(BorderCommand.class));
    }
}
