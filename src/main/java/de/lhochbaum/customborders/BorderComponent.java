package de.lhochbaum.customborders;

import com.intellectualcrafters.plot.api.PlotAPI;
import dagger.BindsInstance;
import dagger.Component;
import de.lhochbaum.customborders.handlers.HandlerRepository;

import javax.inject.Singleton;

@Singleton
@Component(modules = BorderModule.class)
public interface BorderComponent {
    /**
     * Returns the handler repository binding which
     * contains our handlers.
     *
     * @return Handler repository.
     */
    HandlerRepository.Builder handlerBuilder();

    /**
     * Returns the border changer binding.
     *
     * @return Border changer.
     */
    BorderChanger borderChanger();

    /**
     * Returns the command binding.
     *
     * @return Command.
     */
    BorderCommand borderCommand();

    /**
     * Returns the border menu binding.
     *
     * @return Border menu.
     */
    BorderMenu borderMenu();

    /**
     * Returns the PlotSquared API binding.
     *
     * @return PlotSquared API.
     */
    PlotAPI plotAPI();

    @Component.Builder
    interface Builder {
        @BindsInstance Builder plugin(final CustomBorders plugin);

        BorderComponent build();
    }
}
