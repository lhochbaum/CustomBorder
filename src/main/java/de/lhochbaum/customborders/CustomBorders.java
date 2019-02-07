package de.lhochbaum.customborders;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomBorders extends JavaPlugin {
    @Override
    public void onEnable() {
        final Injector injector = Guice.createInjector(binder -> {
            binder.bind(CustomBorders.class).toInstance(this);
            binder.bind(PlotAPI.class).toInstance(new PlotAPI());
        });
    }
}
