package dev.drawethree.xprivatemines.api.manager;

import com.cryptomorin.xseries.XMaterial;
import dev.drawethree.xprivatemines.api.model.MineTier;
import dev.drawethree.xprivatemines.api.model.MinesSchematic;
import dev.drawethree.xprivatemines.api.model.PrivateMine;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PrivateMinesManager {

    /**
     * Creates a new private mine for the given player.
     *
     * @param owner the player to own the new mine
     * @param schematic schematic that should be used
     * @return future representing the created mine
     */
    CompletableFuture<PrivateMine> createPrivateMine(OfflinePlayer owner, MinesSchematic schematic);

    /**
     * Deletes a private mine.
     *
     * @param sender who requested the deletion
     * @param mine   the mine to delete
     */
    void deleteMine(CommandSender sender, PrivateMine mine);

    /**
     * Gets the private mine associated with a player.
     *
     * @param player the player
     * @return the player's private mine, or null if they have none
     */
    PrivateMine getPrivateMine(OfflinePlayer player);

    /**
     * Looks up a private mine by its unique ID.
     *
     * @param mineUuid the UUID of the mine
     * @return the private mine, or null if not found
     */
    PrivateMine getMineById(UUID mineUuid);

    /**
     * Looks up a private mine by its owner's UUID.
     *
     * @param ownerUuid the UUID of the owner
     * @return the owner's private mine, or null if not found
     */
    PrivateMine getMineByOwner(UUID ownerUuid);

    /**
     * Gets the private mine at a specific location.
     *
     * @param location the location to check
     * @return the private mine at that location, or null
     */
    PrivateMine getPrivateMineAtLocation(Location location);

    /**
     * Gets all private mines currently loaded.
     *
     * @return a collection of all private mines
     */
    Collection<PrivateMine> getAll();

    /**
     * Returns {@code true} once the async mine loading from disk has fully
     * completed and all mines are registered in memory.
     * Use this to distinguish "0 mines loaded" from "mines still loading".
     */
    boolean isMinesReady();

    /**
     * Gets all schematics currently registered.
     *
     * @return a collection of all available schematics
     */
    Collection<MinesSchematic> getAllSchematics();

    /**
     * Forces a mine to expand by a given amount.
     *
     * @param sender       the command sender
     * @param mine         the mine to expand
     * @param expandAmount amount to expand by
     * @return true if successful
     */
    boolean forceExpand(CommandSender sender, PrivateMine mine, int expandAmount);

    /**
     * Checks if a mine is at max expansion.
     *
     * @param mine the mine to check
     * @return true if at max expansion
     */
    boolean isMaxExpand(PrivateMine mine);

    /**
     * Forces a mine to upgrade to a specific tier.
     *
     * @param sender the command sender
     * @param mine   the mine to upgrade
     * @param tier   the tier to upgrade to
     * @return true if successful
     */
    boolean forceUpgrade(CommandSender sender, PrivateMine mine, MineTier tier);

    /**
     * Checks if a mine is already at the highest tier.
     *
     * @param mine the mine to check
     * @return true if at max tier
     */
    boolean isMaxTier(PrivateMine mine);

    /**
     * Gets the cost to upgrade the mine to the next tier.
     *
     * @param mine the mine
     * @return the next upgrade cost
     */
    double getNextUpgradeCost(PrivateMine mine);

    /**
     * Checks if a mine should reset (based on remaining blocks).
     *
     * @param mine the mine to check
     * @return true if it should reset
     */
    boolean shouldReset(PrivateMine mine);

    /**
     * Refills (resets) a mine.
     *
     * @param mine the mine to refill
     */
    void refill(PrivateMine mine);

    /**
     * Starts pre-generating a specified number of private mines.
     *
     * @param sender the command sender
     * @param schematic schematic to use
     * @param amount number of mines to pregen
     */
    void pregen(CommandSender sender, MinesSchematic schematic, int amount);

    /**
     * Checks if a pregen operation is running.
     *
     * @return true if pregen is in progress
     */
    boolean isPregenRunning();

    /**
     * Stops the ongoing pregen operation.
     */
    void stopPregen();

    /**
     * Returns the number of mines successfully created so far in the current pregen run.
     * Returns 0 if no pregen is running.
     *
     * @return mines completed so far
     */
    int getPregenCompleted();

    /**
     * Returns the total number of mines requested in the current pregen run.
     * Returns 0 if no pregen is running.
     *
     * @return total mines requested
     */
    int getPregenTotal();

    /**
     * Bans a player from a private mine.
     *
     * @param mine the mine
     * @param player the player to ban
     */
    void banPlayer(PrivateMine mine, OfflinePlayer player);

    /**
     * Unbans a player from a private mine.
     *
     * @param mine the mine
     * @param player the player to unban
     */
    void unbanPlayer(PrivateMine mine, OfflinePlayer player);

    /**
     * Kicks a player from their current mine (if inside one).
     *
     * @param target the player to kick
     */
    void kickPlayer(Player target);

    /**
     * Reassigns an existing mine (typically unclaimed) to a new owner.
     * Updates both the mine's owner field and the internal registry index.
     *
     * @param mine     the mine to reassign
     * @param newOwner the new owner
     */
    void reassignMine(PrivateMine mine, OfflinePlayer newOwner);

    /**
     * Lets a player upgrade their own mine to the next tier, charging them the upgrade cost.
     * Sends the appropriate success/failure messages to the player.
     *
     * @param mine   the mine to upgrade
     * @param player the player paying for the upgrade
     * @return {@code true} if the upgrade succeeded
     */
    boolean upgradeMine(PrivateMine mine, Player player);

    /**
     * Lets a player expand their own mine by one level, charging them the expand cost.
     * Sends the appropriate success/failure messages to the player.
     *
     * @param mine   the mine to expand
     * @param player the player paying for the expansion
     * @return {@code true} if the expansion succeeded
     */
    boolean expandMine(PrivateMine mine, Player player);

    /**
     * Changes the block material of a mine and immediately refills it.
     * Use {@code null} to revert to tier-based block composition.
     *
     * @param mine     the mine to update
     * @param material the new block material, or {@code null} to use tier blocks
     * @return {@code true} if the change was applied
     */
    boolean setBlock(PrivateMine mine, XMaterial material);

    /**
     * Changes the block material of a mine by material name and immediately refills it.
     * Use {@code null} or blank string to revert to tier-based block composition.
     *
     * @param mine         the mine to update
     * @param materialName the Bukkit material name (e.g. "DIAMOND_ORE"), or null/blank to clear
     * @return {@code true} if the change was applied, {@code false} if the material name is unknown
     */
    boolean setBlock(PrivateMine mine, String materialName);
}
