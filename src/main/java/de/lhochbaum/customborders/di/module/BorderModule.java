package de.lhochbaum.customborders.di.module;

import com.intellectualcrafters.plot.api.PlotAPI;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public final class BorderModule {
    @Provides @Singleton
    PlotAPI providesPlotAPI() {
        return new PlotAPI();
    }
}
