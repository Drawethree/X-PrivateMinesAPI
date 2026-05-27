package dev.drawethree.xprivatemines.api;


import dev.drawethree.xprivatemines.api.addons.XPrivateMinesAddonInfo;
import dev.drawethree.xprivatemines.api.economy.MineEconomyProvider;
import dev.drawethree.xprivatemines.api.manager.MineTierManager;
import dev.drawethree.xprivatemines.api.manager.PrivateMinesManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Main API interface for interacting with the XPrivateMines plugin.
 */
public interface XPrivateMinesAPI {

    /**
     * Gets the tier manager.
     *
     * @return the MineTierManager instance
     */
    @NotNull
    MineTierManager getTierManager();

    /**
     * Gets the mines manager.
     *
     * @return the PrivateMinesManager instance
     */
    @NotNull
    PrivateMinesManager getMinesManager();

    /**
     * Gets the economy provider that wraps whichever currency backend the plugin
     * is configured to use (Vault, X-Prison, ExcellentEconomy, PlayerPoints).
     * Use this to withdraw/deposit/format currency without depending on a specific economy plugin.
     *
     * @return the active MineEconomyProvider
     */
    @NotNull
    MineEconomyProvider getEconomyProvider();


    /** Returns metadata for all currently loaded addons. */
    @NotNull
    List<XPrivateMinesAddonInfo> getLoadedAddons();

    /**
     * Enables the addon with the given name (case-insensitive).
     *
     * @return true if the addon was found and enabled, false if not found
     */
    boolean enableAddon(@NotNull String name);

    /**
     * Disables the addon with the given name (case-insensitive).
     *
     * @return true if the addon was found and disabled, false if not found
     */
    boolean disableAddon(@NotNull String name);

    /**
     * Loads an addon JAR file from the {@code addons/} folder at runtime.
     *
     * @param filename just the filename, e.g. {@code "MyAddon.jar"}
     * @return true if loaded successfully, false if already loaded or file not found
     */
    boolean loadAddonFromFile(@NotNull String filename);

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
