package dev.drawethree.xprivatemines.api.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PrivateMine {

    /**
     * Gets the UUID of this private mine.
     *
     * @return UUID of the mine
     */
    UUID getUuid();

    /**
     * Gets the current owner of the mine.
     *
     * @return the owner's UUID
     */
    UUID getOwner();

    /**
     * Gets the owner as an OfflinePlayer.
     *
     * @return the OfflinePlayer representing the owner
     */
    OfflinePlayer getOfflineOwner();

    /**
     * Gets the Mine object (region, block data, etc.) associated with this private mine.
     *
     * @return the Mine
     */
    Mine getMine();

    /**
     * Checks if a given OfflinePlayer is banned from this mine.
     *
     * @param player the player to check
     * @return true if banned
     */
    boolean isBanned(OfflinePlayer player);

    /**
     * Gets the list of players banned from this mine.
     *
     * @return list of banned OfflinePlayers
     */
    List<OfflinePlayer> getBannedPlayers();

    /**
     * Gets the set of UUIDs of players banned from this mine.
     *
     * @return set of banned player UUIDs
     */
    Set<UUID> getBannedPlayersUUID();

    /**
     * Sets the owner of this mine.
     *
     * @param newOwner the UUID of the new owner
     */
    void setOwner(UUID newOwner);

    /**
     * Teleports a player to the mine's spawn location.
     *
     * @param player the player to teleport
     */
    void teleport(Player player);

    /**
     * Checks if the given location is inside the **mine** region.
     *
     * @param location the location to check
     * @return true if the location is inside the mine
     */
    boolean isInMine(Location location);

    /**
     * Checks if the given location is inside the **private mine's full region**, including housing, etc.
     *
     * @param location the location to check
     * @return true if inside private mine region
     */
    boolean isInPrivateMine(Location location);

    /**
     * Checks if a given OfflinePlayer is the owner of this mine.
     *
     * @param player the player to check
     * @return true if the player is the owner
     */
    boolean isOwner(OfflinePlayer player);

    /**
     * Gets the mine's open state.
     *
     * @return true if mine is open to visitors
     */
    boolean isOpen();

    /**
     * Checks whether the mine is open to the specified player.
     *
     * @param player the {@link OfflinePlayer} to check access for
     * @return {@code true} if the player is allowed to access the mine, {@code false} otherwise
     */
    boolean isOpenTo(OfflinePlayer player);

    /**
     * Sets whether the mine is open to visitors.
     *
     * @param open true to open the mine
     */
    void setOpen(boolean open);

    /**
     * Gets the current entry fee to visit the mine.
     *
     * @return the entry fee
     */
    double getEntryFee();

    /**
     * Sets the entry fee required to enter the mine.
     *
     * @param newFee the new entry fee
     */
    void setEntryFee(double newFee);

    /**
     * Gets the tax rate of the mine.
     *
     * @return the tax rate
     */
    double getTax();

    /**
     * Sets the tax rate of the mine.
     *
     * @param newTax new tax value
     */
    void setTax(double newTax);

    /**
     * Gets the mine's expansion level.
     *
     * @return expand level (affects size)
     */
    int getExpandLevel();

    /**
     * Sets the mine's expansion level.
     *
     * @param level the new expansion level
     */
    void setExpandLevel(int level);

    /**
     * Gets the reset percentage at which the mine auto-resets.
     *
     * @return the reset percentage (0-100)
     */
    double getResetPercentage();

    /**
     * Sets the percentage at which the mine auto-resets.
     *
     * @param resetPercentage value from 0-100
     */
    void setResetPercentage(int resetPercentage);

    /**
     * Gets the current unclaimed earnings from the mine.
     *
     * @return unclaimed money
     */
    double getUnclaimedMoney();

    /**
     * Sets the unclaimed money value.
     *
     * @param money unclaimed amount
     */
    void setUnclaimedMoney(double money);

    /**
     * Gets the X offset of the mine's location.
     *
     * @return x-offset
     */
    double getXOffset();

    /**
     * Gets the Z offset of the mine's location.
     *
     * @return z-offset
     */
    double getZOffset();

    /**
     * Gets the current mine tier.
     *
     * @return the mine tier
     */
    MineTier getTier();

    /**
     * Sets the mine tier.
     *
     * @param tier the new mine tier
     */
    void setTier(MineTier tier);

    /**
     * Gets the schematic this private mine was created from.
     *
     * @return the mine's schematic
     */
    MinesSchematic getSchematic();

    /**
     * Returns the spawn location players are teleported to when entering this mine.
     *
     * @return the mine's spawn location
     */
    Location getSpawnLocation();

    /**
     * Returns the current size of the mine's ore pit (base size + expand level).
     *
     * @return mine size in blocks
     */
    int getMineSize();

    /**
     * Returns a snapshot list of all players currently inside the ore region of this mine.
     *
     * @return list of players in the mine pit
     */
    List<Player> getPlayersInMine();

    /**
     * Returns a snapshot list of all players currently inside the full private mine region
     * (includes the outer housing area, not just the ore pit).
     *
     * @return list of players in the private mine
     */
    List<Player> getPlayersInPrivateMine();
}
