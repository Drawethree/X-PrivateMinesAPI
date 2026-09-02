package dev.drawethree.xprivatemines.api.model;

import dev.drawethree.xprivatemines.api.model.block.MineBlockRef;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.codemc.worldguardwrapper.region.IWrappedRegion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Mine {

    /**
     * Gets the WorldGuard region associated with this mine.
     *
     * @return the mine's wrapped region
     * @deprecated since 1.4, for addon use. X-PrivateMines relocates WorldGuardWrapper when it
     * shades this API, so an addon compiled against the published API artifact links
     * {@code org.codemc.worldguardwrapper...} while the running plugin exposes
     * {@code dev.drawethree.libs.worldguardwrapper...} &mdash; the call fails at runtime with
     * {@code NoSuchMethodError}. Use {@link #getRegionId()}, {@link #getWorld()},
     * {@link #getMinCorner()} and {@link #getMaxCorner()} instead. Still valid for code running
     * inside the plugin itself.
     */
    @Deprecated
    IWrappedRegion getRegion();

    /**
     * Sets the WorldGuard region for this mine.
     *
     * @param region the region to set
     * @deprecated since 1.4, for addon use &mdash; see {@link #getRegion()} for the relocation
     * problem. There is no addon-safe replacement; regions are managed by the plugin.
     */
    @Deprecated
    void setRegion(IWrappedRegion region);

    /**
     * Gets the material currently selected for the mine blocks.
     *
     * @return selected block material
     * @deprecated since 1.4 &mdash; <b>returns {@code null} whenever the mine's selected block is a
     * custom-block-provider block</b> (ItemsAdder/Nexo/Oraxen), which is indistinguishable from
     * "no block selected". Callers that read null as "uses the tier's composition" silently
     * mis-report every custom-block mine. Use {@link #getSelectedBlock()} or
     * {@link #getSelectedBlockId()}, whose null means exactly one thing.
     * <p>
     * Kept indefinitely for binary compatibility with API 1.3 addons.
     */
    @Deprecated
    Material getSelectedMaterial();

    /**
     * Sets the material used in the mine.
     *
     * @param material the new selected material
     * @deprecated since 1.4 &mdash; cannot express a custom-block selection, and bypasses the
     * refill that makes the change visible. Use
     * {@link dev.drawethree.xprivatemines.api.manager.PrivateMinesManager#setBlock(PrivateMine, String)}.
     */
    @Deprecated
    void setSelectedMaterial(Material material);

    /**
     * The block this mine is currently filled with.
     * <p>
     * Replaces {@link #getSelectedMaterial()}, which cannot represent a custom-block selection.
     * Main thread only if you go on to call the resolution-dependent accessors on the returned ref.
     *
     * @return the selected block, or {@code null} when the mine uses its tier's block composition
     * instead of a single override block
     * @since 1.4
     */
    @Nullable
    default MineBlockRef getSelectedBlock() {
        return null;
    }

    /**
     * The canonical id of the selected block, or {@code null} when the mine uses its tier's
     * composition.
     * <p>
     * Cheap: never touches a provider plugin, so it is safe off the main thread. Feed the value
     * straight back into
     * {@link dev.drawethree.xprivatemines.api.manager.PrivateMinesManager#setBlock(PrivateMine, String)}.
     *
     * @return the selected block id, or {@code null} for tier-based composition
     * @since 1.4
     */
    @Nullable
    default String getSelectedBlockId() {
        return null;
    }

    /**
     * Gets the total number of blocks in the mine region.
     *
     * @return total block count
     */
    long getTotalBlockCount();

    /**
     * Sets the total number of blocks in the mine region.
     *
     * @param count the total block count to set
     */
    void setTotalBlockCount(long count);

    /**
     * Gets the estimated number of remaining (non-air) blocks in the mine.
     *
     * @return remaining block count
     */
    long getEstimatedRemainingBlocks();

    /**
     * Sets the estimated number of remaining (non-air) blocks in the mine.
     * <p>
     * <b>No-op in packet-mine mode</b>, where the virtual block store is the source of truth.
     *
     * @param count estimated remaining blocks to set
     */
    void setEstimatedRemainingBlocks(long count);

    /**
     * The number of unmined blocks left in the mining region.
     * <p>
     * Identical to {@link #getEstimatedRemainingBlocks()}; the neutral name exists because the
     * value is <em>exact</em> in packet-mine mode.
     *
     * @return remaining block count
     * @see #isRemainingBlockCountExact()
     * @since 1.4
     */
    default long getRemainingBlocks() {
        return getEstimatedRemainingBlocks();
    }

    /**
     * Whether {@link #getRemainingBlocks()} is an exact count rather than an estimate.
     * <p>
     * Exact in packet-mine mode, where the virtual block store <em>is</em> the counter. In
     * real-block mode the count is a running estimate seeded at full capacity and decremented by
     * break events, so it drifts under world edits and third-party block changes.
     *
     * @return {@code true} if the remaining count is exact
     * @since 1.4
     */
    default boolean isRemainingBlockCountExact() {
        return false;
    }

    /**
     * The WorldGuard region id of the mining area, of the form {@code <mine-uuid>_mine}.
     * Relocation-safe replacement for part of {@link #getRegion()}.
     *
     * @return the region id, or {@code null} if the region has not been created yet
     * @since 1.4
     */
    @Nullable
    default String getRegionId() {
        return null;
    }

    /**
     * The world the mining region lives in.
     *
     * @return the world, or {@code null} if it is unloaded or the region does not exist yet
     * @since 1.4
     */
    @Nullable
    default World getWorld() {
        return null;
    }

    /**
     * The minimum corner of the mining region.
     *
     * @return the minimum corner, or {@code null} if the region does not exist yet
     * @since 1.4
     */
    @Nullable
    default Location getMinCorner() {
        return null;
    }

    /**
     * The maximum corner of the mining region.
     *
     * @return the maximum corner, or {@code null} if the region does not exist yet
     * @since 1.4
     */
    @Nullable
    default Location getMaxCorner() {
        return null;
    }

    /**
     * Decrement the estimated remaining block count by 1.
     * <p>
     * <b>No-op in packet-mine mode</b>, where the virtual block store is the source of truth.
     */
    void decrementRemainingBlockCount();

    /**
     * Handle breaking multiple blocks at once (e.g. explosion, multi-break tool).
     * <p>
     * <b>No-op in packet-mine mode</b>, where the virtual block store is the source of truth.
     *
     * @param blocks the list of broken blocks
     */
    void handleBlockBreak(List<Block> blocks);
}
