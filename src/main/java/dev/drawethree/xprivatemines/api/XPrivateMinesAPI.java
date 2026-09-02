package dev.drawethree.xprivatemines.api;


import dev.drawethree.xprivatemines.api.addons.XPrivateMinesAddonInfo;
import dev.drawethree.xprivatemines.api.economy.MineEconomyProvider;
import dev.drawethree.xprivatemines.api.manager.MineTierManager;
import dev.drawethree.xprivatemines.api.manager.PrivateMinesManager;
import dev.drawethree.xprivatemines.api.model.block.MineBlockCatalogue;
import dev.drawethree.xprivatemines.api.virtual.PacketMineService;
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
     * The catalogue of blocks usable in mines, and the entry point for validating a block id.
     *
     * @return the block catalogue, never null
     * @since 1.4
     */
    @NotNull
    default MineBlockCatalogue getBlockCatalogue() {
        throw new UnsupportedOperationException("getBlockCatalogue requires X-PrivateMines built against API 1.4");
    }

    /**
     * Packet-mine ("virtual mine") state.
     * <p>
     * Never null &mdash; check {@link PacketMineService#isActive()} rather than the return value.
     *
     * @return the packet-mine service, never null
     * @since 1.4
     */
    @NotNull
    default PacketMineService getPacketMines() {
        throw new UnsupportedOperationException("getPacketMines requires X-PrivateMines built against API 1.4");
    }

    /**
     * The API revision this X-PrivateMines build implements: an integer that increases by one per
     * API release &mdash; {@code 13} for 1.3, {@code 14} for 1.4.
     * <p>
     * <b>This is the feature-detection primitive.</b> An addon jar can be dropped onto a server
     * running an older X-PrivateMines than it was built for; the addon loader only <em>warns</em>
     * on a version mismatch, it does not refuse the load. Gate every 1.4-only call behind
     * {@code api.getApiRevision() >= 14} and disable the affected feature otherwise, rather than
     * letting a {@code NoSuchMethodError} escape from a request handler.
     * <p>
     * A compile-time constant would be useless here: a {@code static final} field is inlined into
     * the addon at compile time and would report the version the addon was <em>built</em> against,
     * not the one it is running on. This method is answered by the running plugin. The default
     * ({@code 13}) is what you observe when the running plugin was built against API 1.3.
     *
     * @return the implemented API revision
     * @since 1.4
     */
    default int getApiRevision() {
        return 13;
    }

    /**
     * The running X-PrivateMines plugin version, e.g. {@code "2026.4.0.0"}.
     * <p>
     * Informational only &mdash; use {@link #getApiRevision()} for capability checks.
     *
     * @return the plugin version, never null
     * @since 1.4
     */
    @NotNull
    default String getPluginVersion() {
        return "unknown";
    }


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
