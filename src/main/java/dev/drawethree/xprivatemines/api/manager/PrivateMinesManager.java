package dev.drawethree.xprivatemines.api.manager;

import dev.drawethree.xprivatemines.api.model.MineTier;
import dev.drawethree.xprivatemines.api.model.MinesSchematic;
import dev.drawethree.xprivatemines.api.model.PrivateMine;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
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
}
