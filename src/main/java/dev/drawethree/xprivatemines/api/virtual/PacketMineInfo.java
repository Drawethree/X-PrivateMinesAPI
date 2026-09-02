package dev.drawethree.xprivatemines.api.virtual;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Snapshot of one mine's packet-mode ("virtual") block store: blocks that exist only server-side
 * and are painted onto clients, never placed in the world.
 * <p>
 * Values are a consistent snapshot taken when the instance was created, not a live view &mdash;
 * re-query for fresh numbers. Available only while packet mines are active; see
 * {@link PacketMineService#isActive()}.
 *
 * @since 1.4
 */
public interface PacketMineInfo {

    /**
     * The UUID of the private mine this store belongs to.
     *
     * @return the mine's UUID
     */
    @NotNull UUID getMineUuid();

    /**
     * The exact number of unmined blocks. Equal to {@link #getTotalBlocks()} for an unmined mine.
     *
     * @return remaining blocks
     */
    long getRemainingBlocks();

    /**
     * The total number of blocks in the mining region, i.e. its cuboid volume.
     *
     * @return total blocks
     */
    long getTotalBlocks();

    /**
     * Whether the mine's real-world preparation (interior clear and bedrock shell) has finished.
     * <p>
     * A store that is not ready is hidden from the dig path, so the mine is temporarily
     * unmineable &mdash; surface it as "preparing", not "broken".
     *
     * @return {@code true} once the mine is ready to be mined
     */
    boolean isReady();

    /**
     * Whether the backing block array has been built. A never-visited mine reports its full volume
     * as remaining without ever allocating, so {@code false} means the numbers are accurate but
     * nothing is held in memory.
     *
     * @return {@code true} if the block array is allocated
     */
    boolean isMaterialized();

    /**
     * The fill generation, incremented on every refill and eviction. Two snapshots with different
     * generations describe different fills and must not be diffed against each other.
     *
     * @return the current generation
     */
    int getGeneration();

    /**
     * The percentage of the region still unmined, from {@code 0.0} to {@code 100.0}.
     *
     * @return percentage remaining; {@code 100.0} for a zero-volume region
     */
    default double getPercentageRemaining() {
        long total = getTotalBlocks();
        return total <= 0 ? 100.0D : (getRemainingBlocks() * 100.0D) / total;
    }
}
