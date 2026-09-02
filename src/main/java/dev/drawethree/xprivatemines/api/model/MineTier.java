package dev.drawethree.xprivatemines.api.model;

import com.cryptomorin.xseries.XMaterial;
import dev.drawethree.xprivatemines.api.model.block.MineBlockRef;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
     * @deprecated since 1.4 &mdash; <b>lossy, and unusable from an addon</b>. It silently omits
     * every custom-block-provider entry, so a tier that is 50% {@code nexo:ruby_ore} reports a map
     * whose weights do not account for half the mine. It is additionally unusable from an addon at
     * runtime: X-PrivateMines relocates XSeries when it shades this API, so the {@code XMaterial}
     * keys are of a different class than the addon links against and iterating the map throws
     * {@code ClassCastException}. Use {@link #getBlockComposition()} for a rich view or
     * {@link #getBlockWeightsAsStrings()} for a dependency-free one.
     * <p>
     * Kept indefinitely for binary compatibility with API 1.3 addons.
     */
    @Deprecated
    Map<XMaterial, Integer> getBlockWeights();

    /**
     * Gets the block distribution as a map of block id strings to their weights.
     * Suitable for use in contexts where XSeries is not available on the classpath.
     * <p>
     * Keys are canonical block ids as returned by {@link MineBlockRef#getId()} &mdash; a vanilla
     * material name, or a namespaced custom id such as {@code nexo:ruby_ore}. Unlike
     * {@link #getBlockWeights()} this map is complete.
     *
     * @return map of block ids and their weights
     */
    Map<String, Integer> getBlockWeightsAsStrings();

    /**
     * The complete block composition of this tier &mdash; vanilla and custom alike &mdash; in
     * config order, each block mapped to its relative spawn weight.
     * <p>
     * Weights are relative, not percentages; divide by {@link #getTotalWeight()} for a share. The
     * returned map is a snapshot, so mutating it does not affect the tier.
     *
     * @return the block composition, never null
     * @since 1.4
     */
    @NotNull
    default Map<MineBlockRef, Integer> getBlockComposition() {
        return Map.of();
    }

    /**
     * The composition's blocks in config order, without their weights.
     *
     * @return the blocks of this tier, never null
     * @since 1.4
     */
    @NotNull
    default List<MineBlockRef> getBlocks() {
        return new ArrayList<>(getBlockComposition().keySet());
    }

    /**
     * The sum of all weights in {@link #getBlockComposition()}; the denominator for percentage
     * display.
     *
     * @return the total weight, or {@code 0} for an empty composition &mdash; guard before dividing
     * @since 1.4
     */
    default int getTotalWeight() {
        int sum = 0;
        for (Integer w : getBlockComposition().values()) {
            sum += (w == null ? 0 : w);
        }
        return sum;
    }

    /**
     * The upgrade cost as an exact decimal, for economies whose values exceed {@code double}
     * precision.
     * <p>
     * Prefer this over {@link #getUpgradeCost()} whenever the value is shown to a player or
     * compared against a balance from
     * {@link dev.drawethree.xprivatemines.api.economy.MineEconomyProvider}.
     *
     * @return the upgrade cost, never null
     * @since 1.4
     */
    @NotNull
    default BigDecimal getUpgradeCostExact() {
        return BigDecimal.valueOf(getUpgradeCost());
    }
}
