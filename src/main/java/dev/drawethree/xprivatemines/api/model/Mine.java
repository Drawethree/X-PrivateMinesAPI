package dev.drawethree.xprivatemines.api.model;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.codemc.worldguardwrapper.region.IWrappedRegion;

import java.util.List;

public interface Mine {

    /**
     * Gets the WorldGuard region associated with this mine.
     *
     * @return the mine's wrapped region
     */
    IWrappedRegion getRegion();

    /**
     * Sets the WorldGuard region for this mine.
     *
     * @param region the region to set
     */
    void setRegion(IWrappedRegion region);

    /**
     * Gets the material currently selected for the mine blocks.
     *
     * @return selected block material
     */
    Material getSelectedMaterial();

    /**
     * Sets the material used in the mine.
     *
     * @param material the new selected material
     */
    void setSelectedMaterial(Material material);

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
     *
     * @param count estimated remaining blocks to set
     */
    void setEstimatedRemainingBlocks(long count);

    /**
     * Decrement the estimated remaining block count by 1.
     */
    void decrementRemainingBlockCount();

    /**
     * Handle breaking multiple blocks at once (e.g. explosion, multi-break tool).
     *
     * @param blocks the list of broken blocks
     */
    void handleBlockBreak(List<Block> blocks);
}
