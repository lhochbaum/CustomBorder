package de.lhochbaum.customborders.di.component;

import dagger.Subcomponent;
import de.lhochbaum.customborders.di.scope.HandlerScope;
import de.lhochbaum.customborders.handlers.MergeHandler;

@HandlerScope
@Subcomponent
public interface HandlerRepository {
    /**
     * Returns a {@link MergeHandler} which deals
     * with plots being merged.
     *
     * @return Merge handler.
     */
    MergeHandler mergeHandler();

    @Subcomponent.Builder
    interface Builder {
        HandlerRepository build();
    }
}
