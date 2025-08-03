package dev.drawethree.xprivatemines.api.manager;

import dev.drawethree.xprivatemines.api.model.MineTier;

import java.util.List;
import java.util.Optional;

public interface MineTierManager {

    /**
     * Gets the next tier after the one with the given key.
     *
     * @param currentKey the key of the current tier
     * @return optional containing the next MineTier if it exists
     */
    Optional<MineTier> getNextTier(String currentKey);

    /**
     * Gets the next tier after the given tier.
     *
     * @param mineTier the current MineTier
     * @return optional containing the next MineTier if it exists
     */
    Optional<MineTier> getNextTier(MineTier mineTier);

    /**
     * Gets a list of all registered mine tiers in load order.
     *
     * @return list of MineTiers
     */
    List<MineTier> getAllTiers();

    /**
     * Gets the default tier (fallback tier, usually with key "default").
     *
     * @return default MineTier
     */
    MineTier getDefaultTier();

    /**
     * Gets the tier by its string key. If not found, falls back to default.
     *
     * @param key the tier key
     * @return the corresponding MineTier or default
     */
    MineTier getTier(String key);
}
