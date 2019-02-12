package de.lhochbaum.customborders;

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
