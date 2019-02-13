package de.lhochbaum.customborders.di.component;

import com.intellectualcrafters.plot.api.PlotAPI;
import dagger.BindsInstance;
import dagger.Component;
import de.lhochbaum.customborders.BorderChanger;
import de.lhochbaum.customborders.BorderCommand;
import de.lhochbaum.customborders.BorderMenu;
import de.lhochbaum.customborders.CustomBorders;
import de.lhochbaum.customborders.di.module.BorderModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = BorderModule.class)
public interface BorderComponent {
    /**
     * Returns the {@link HandlerRepository} binding which
     * contains our handlers.
     *
     * @return Handler repository.
     */
    HandlerRepository.Builder handlerBuilder();

    /**
     * Returns the {@link BorderChanger} binding.
     *
     * @return Border changer.
     */
    BorderChanger borderChanger();

    /**
     * Returns the {@link BorderCommand} binding.
     *
     * @return Command.
     */
    BorderCommand borderCommand();

    /**
     * Returns the {@link BorderMenu} binding.
     *
     * @return Border menu.
     */
    BorderMenu borderMenu();

    /**
     * Returns the {@link PlotAPI} binding.
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
