package de.lhochbaum.customborders.handlers;

import dagger.Subcomponent;

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
