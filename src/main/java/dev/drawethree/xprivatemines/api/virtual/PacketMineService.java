package dev.drawethree.xprivatemines.api.virtual;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Entry point to the packet-mines ("virtual mines") feature, in which mine ore exists only in an
 * in-memory store and on players' clients rather than as real blocks in the world.
 * <p>
 * Always obtainable from {@link dev.drawethree.xprivatemines.api.XPrivateMinesAPI#getPacketMines()}.
 * When the feature is off the service is still present but reports {@link #isActive()}
 * {@code false} and returns {@code null}/zero from everything else, so callers branch on state
 * rather than on null.
 *
 * @since 1.4
 */
public interface PacketMineService {

    /**
     * Whether packet mines are enabled ({@code packet-mines: true} in config.yml) and the engine
     * started successfully.
     * <p>
     * When {@code false}, mines are real blocks in the world and the block counts reported by
     * {@link dev.drawethree.xprivatemines.api.model.Mine} are estimates rather than exact.
     *
     * @return {@code true} if the packet-mine engine is running
     */
    boolean isActive();

    /**
     * A snapshot of one mine's virtual block store.
     *
     * @param mine the mine to inspect
     * @return the snapshot, or {@code null} if packet mines are inactive or this mine has no store
     * yet (no mining region, or created since the last engine scan)
     */
    @Nullable PacketMineInfo getMineInfo(@NotNull PrivateMine mine);

    /**
     * The number of mines that currently have a registered virtual store. Zero when inactive.
     *
     * @return the tracked mine count
     */
    int getTrackedMineCount();

    /**
     * Whether the X-Prison virtual-blocks bridge is active, i.e. blocks mined in packet mines are
     * routed through X-Prison's break pipeline (enchants, autosell, multipliers) rather than
     * dropping straight to the player's inventory.
     * <p>
     * Only meaningful while {@link #isActive()} is {@code true}.
     *
     * @return {@code true} if the X-Prison bridge is wired up
     */
    boolean isBridgeActive();
}
