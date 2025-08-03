package dev.drawethree.xprivatemines.api.model;

import com.cryptomorin.xseries.XMaterial;

import java.util.Map;

public interface MineTier {

    /**
     * Gets the unique identifier key for this mine tier.
     *
     * @return the tier key
     */
    String getKey();

    /**
     * Gets the display name of this mine tier.
     *
     * @return the tier name
     */
    String getName();

    /**
     * Gets the upgrade cost required to move to this tier.
     *
     * @return the upgrade cost
     */
    double getUpgradeCost();

    /**
     * Gets the block distribution (weight mapping) for this tier.
     * Each XMaterial is mapped to its relative spawn weight.
     *
     * @return map of block types and their weights
     */
    Map<XMaterial, Integer> getBlockWeights();
}
