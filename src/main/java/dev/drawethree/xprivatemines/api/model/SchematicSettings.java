package dev.drawethree.xprivatemines.api.model;

import me.lucko.helper.serialize.Point;
import me.lucko.helper.serialize.Position;
import org.codemc.worldguardwrapper.flag.WrappedState;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Map;

public interface SchematicSettings {

    /**
     * Gets the permission required to create schematic
     *
     * @return required player permission
     */
    String getPermission();

    /**
     * Returns whether this schematic uses bedrock walls around the mine region.
     *
     * @return true if bedrock walls are enabled
     */
    boolean isBedrockWalls();

    /**
     * Gets the spawn point inside the schematic.
     *
     * @return spawn point
     */
    Point getSpawn();

    /**
     * Gets the teleport location used when resetting the mine.
     *
     * @return reset teleport point
     */
    Point getResetLocation();

    /**
     * Gets the first corner of the schematic's total region.
     *
     * @return region corner 1
     */
    Position getRegionPos1();

    /**
     * Gets the second corner of the schematic's total region.
     *
     * @return region corner 2
     */
    Position getRegionPos2();

    /**
     * Gets the first corner of the mine area (within the region).
     *
     * @return mine region corner 1
     */
    Position getMinesPos1();

    /**
     * Gets the second corner of the mine area (within the region).
     *
     * @return mine region corner 2
     */
    Position getMinesPos2();

    /**
     * Gets the maximum allowed expansion levels for the mine.
     *
     * @return max expand level
     */
    int getMaxExpand();

    /**
     * Gets the default mine size from the schematic.
     *
     * @return mine size
     */
    int getMineSize();

    /**
     * Gets the cost to expand the mine by one level.
     *
     * @return cost per expansion
     */
    double getExpandCost();

    /**
     * Gets the region's priority for WorldGuard.
     *
     * @return priority value
     */
    int getRegionPriority();

    /**
     * Gets the priority of the mine sub-region (for WorldGuard).
     *
     * @return mine region priority
     */
    int getMineRegionPriority();

    /**
     * Gets the WorldGuard flags to apply to the outer region.
     *
     * @return map of region flags
     * @deprecated since 1.4, for addon use. X-PrivateMines relocates WorldGuardWrapper when it
     * shades this API, so reading a {@code WrappedState} value from an addon throws
     * {@code ClassCastException}. Use {@link #getRegionFlagNames()}.
     */
    @Deprecated
    Map<String, WrappedState> getRegionFlags();

    /**
     * Gets the WorldGuard flags to apply to the mine region.
     *
     * @return map of mine region flags
     * @deprecated since 1.4, for addon use &mdash; see {@link #getRegionFlags()}. Use
     * {@link #getMineRegionFlagNames()}.
     */
    @Deprecated
    Map<String, WrappedState> getMineRegionFlags();

    /**
     * The horizontal gap in blocks kept between the mining region and the schematic's walls, so an
     * expanding mine stops short of eating its own structure.
     * <p>
     * Read from {@code wall-gap} in schematic-settings.yml; {@code 0} (flush with the walls) when
     * unset. Never negative.
     *
     * @return the wall gap in blocks
     * @since 1.4
     */
    default int getWallGap() {
        return 0;
    }

    /**
     * {@link #getSpawn()} as a Bukkit {@link Location}, with yaw and pitch preserved.
     * Relocation-free alternative that does not require {@code me.lucko.helper} on the classpath.
     *
     * @return the spawn location, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getSpawnLocation() {
        return null;
    }

    /**
     * {@link #getResetLocation()} as a Bukkit {@link Location}.
     *
     * @return the reset teleport location, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getResetTeleportLocation() {
        return null;
    }

    /**
     * {@link #getRegionPos1()} as a Bukkit {@link Location}.
     *
     * @return the first corner of the outer region, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getRegionCorner1() {
        return null;
    }

    /**
     * {@link #getRegionPos2()} as a Bukkit {@link Location}.
     *
     * @return the second corner of the outer region, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getRegionCorner2() {
        return null;
    }

    /**
     * {@link #getMinesPos1()} as a Bukkit {@link Location}.
     *
     * @return the first corner of the mining area, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getMineCorner1() {
        return null;
    }

    /**
     * {@link #getMinesPos2()} as a Bukkit {@link Location}.
     *
     * @return the second corner of the mining area, or {@code null} if unset
     * @since 1.4
     */
    @Nullable
    default Location getMineCorner2() {
        return null;
    }

    /**
     * The outer region's WorldGuard flags as plain {@code flag -> state-name} strings, where the
     * state is {@code "ALLOW"} or {@code "DENY"}.
     * <p>
     * Relocation-safe alternative to {@link #getRegionFlags()}.
     *
     * @return the region flags by name, never null
     * @since 1.4
     */
    @NotNull
    default Map<String, String> getRegionFlagNames() {
        return Map.of();
    }

    /**
     * The mining region's WorldGuard flags as plain {@code flag -> state-name} strings.
     *
     * @return the mine region flags by name, never null
     * @see #getRegionFlagNames()
     * @since 1.4
     */
    @NotNull
    default Map<String, String> getMineRegionFlagNames() {
        return Map.of();
    }

    /**
     * The cost to expand the mine by one level, as an exact decimal.
     *
     * @return the expand cost, never null
     * @since 1.4
     */
    @NotNull
    default BigDecimal getExpandCostExact() {
        return BigDecimal.valueOf(getExpandCost());
    }
}
