package dev.drawethree.xprivatemines.api.model;

import me.lucko.helper.serialize.Point;
import me.lucko.helper.serialize.Position;
import org.codemc.worldguardwrapper.flag.WrappedState;

import java.util.Map;

public interface SchematicSettings {

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
     */
    Map<String, WrappedState> getRegionFlags();

    /**
     * Gets the WorldGuard flags to apply to the mine region.
     *
     * @return map of mine region flags
     */
    Map<String, WrappedState> getMineRegionFlags();
}
