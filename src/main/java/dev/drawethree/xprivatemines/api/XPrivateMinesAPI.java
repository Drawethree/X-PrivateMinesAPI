package dev.drawethree.xprivatemines.api;


import dev.drawethree.xprivatemines.api.manager.MineTierManager;
import dev.drawethree.xprivatemines.api.manager.PrivateMinesManager;
import org.jetbrains.annotations.NotNull;

/**
 * Main API interface for interacting with the XPrivateMines plugin.
 */
public interface XPrivateMinesAPI {

    /**
     * Gets the AutoMiner module API.
     *
     * @return the AutoMiner API instance
     */
    @NotNull
    MineTierManager getTierManager();

    /**
     * Gets the AutoMiner module API.
     *
     * @return the AutoMiner API instance
     */
    @NotNull
    PrivateMinesManager getMinesManager();


    /**
     * Gets the singleton instance of the XPrivateMinesAPI.
     *
     * @return the XPrivateMinesAPI instance
     * @throws IllegalStateException if the API has not been initialized
     */
    @NotNull
    static XPrivateMinesAPI getInstance() {
        if (InstanceHolder.INSTANCE == null) {
            throw new IllegalStateException("XPrivateMines API has not been initialized!");
        }
        return InstanceHolder.INSTANCE;
    }

    /**
     * Sets the singleton instance of the XPrivateMinesAPI.
     *
     * @param instance the API instance to set
     * @throws IllegalStateException if the API is already initialized
     */
    static void setInstance(@NotNull XPrivateMinesAPI instance) {
        if (InstanceHolder.INSTANCE != null) {
            throw new IllegalStateException("XPrivateMines API is already initialized!");
        }
        InstanceHolder.INSTANCE = instance;
    }

    /**
     * Holder class for the singleton instance.
     */
    class InstanceHolder {
        private static XPrivateMinesAPI INSTANCE;
    }

}
