package dev.drawethree.xprivatemines.api.manager;

import com.cryptomorin.xseries.XMaterial;
import dev.drawethree.xprivatemines.api.model.MineTier;
import dev.drawethree.xprivatemines.api.model.block.MineBlockCatalogue;
import dev.drawethree.xprivatemines.api.model.block.MineBlockRef;
import dev.drawethree.xprivatemines.api.model.MinesSchematic;
import dev.drawethree.xprivatemines.api.model.PrivateMine;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
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
     * @deprecated since 1.4 &mdash; <b>not callable from an addon</b>. X-PrivateMines relocates
     * XSeries when it shades this API, so the running plugin's parameter type differs from the one
     * an addon links against and the call fails with {@code NoSuchMethodError}. It also cannot
     * express a custom-block selection. Use {@link #setBlock(PrivateMine, String)} or
     * {@link #setBlock(PrivateMine, MineBlockRef)}.
     */
    @Deprecated
    boolean setBlock(PrivateMine mine, XMaterial material);

    /**
     * Changes the block of a mine by id and immediately refills it.
     * <p>
     * {@code materialName} is a vanilla material name ({@code "DIAMOND_ORE"}) or a custom-block id
     * ({@code "nexo:ruby_ore"}, {@code "oraxen:ruby_ore"}, {@code "myns:ruby"}) &mdash; the same
     * string {@link MineBlockRef#getId()} returns. Unknown ids are rejected and the mine is left
     * untouched; validate up front with {@link MineBlockCatalogue#isValidBlockId(String)} if you
     * need to tell "unknown block" apart from "not a private mine".
     * <p>
     * Passing {@code null} or a blank string reverts to the tier's composition; prefer the explicit
     * {@link #clearBlock(PrivateMine)}.
     * <p>
     * <b>Main thread only</b> &mdash; it triggers a refill.
     *
     * @param mine         the mine to update
     * @param materialName the block id, or null/blank to clear
     * @return {@code true} if the change was applied, {@code false} if the id is unknown
     */
    boolean setBlock(PrivateMine mine, String materialName);

    /**
     * Typed form of {@link #setBlock(PrivateMine, String)}; equivalent to passing
     * {@code block.getId()}. Main thread only.
     *
     * @param mine  the mine to update
     * @param block the new block, or {@code null} to revert to the tier's composition
     * @return {@code true} if the change was applied
     * @since 1.4
     */
    default boolean setBlock(@NotNull PrivateMine mine, @Nullable MineBlockRef block) {
        return setBlock(mine, block == null ? null : block.getId());
    }

    /**
     * Clears the mine's single-block override so it refills from its tier's composition again.
     * Main thread only.
     *
     * @param mine the mine to update
     * @return {@code true} if the change was applied
     * @since 1.4
     */
    default boolean clearBlock(@NotNull PrivateMine mine) {
        throw new UnsupportedOperationException("clearBlock requires X-PrivateMines built against API 1.4");
    }

    /**
     * Removes real blocks left standing inside a mine's mining area and re-prepares it &mdash; the
     * operation behind {@code /pmine fixblocks}.
     * <p>
     * In packet-mine mode a real block inside the region (schematic terrain that a growing region
     * swallowed, or a mine created before packet mode was enabled) is resolved as itself by
     * anything reading the world, which is how plot floors end up in autosell. This re-runs the
     * interior clear and bedrock shell for the region as it currently stands. In real-block mode it
     * is a plain refill.
     * <p>
     * <b>Main thread only.</b> The work is asynchronous and throttled: this returns before the
     * clean has finished, and the mine is unmineable until it does. Safe to run on a live mine.
     *
     * @param mine the mine to clean
     * @since 1.4
     */
    default void cleanRealBlocks(@NotNull PrivateMine mine) {
        throw new UnsupportedOperationException("cleanRealBlocks requires X-PrivateMines built against API 1.4");
    }

    /**
     * Rebuilds the schematic registry from disk &mdash; the {@code schematics/} folder plus
     * schematic-settings.yml &mdash; as {@code /pmine reload} does.
     * <p>
     * Existing mines keep running; only the templates used for new mines are refreshed.
     * Main thread only.
     *
     * @since 1.4
     */
    default void reloadSchematics() {
        throw new UnsupportedOperationException("reloadSchematics requires X-PrivateMines built against API 1.4");
    }

    /**
     * The schematic used when a mine is created without an explicit one.
     *
     * @return the default schematic, or {@code null} if no schematics are loaded
     * @since 1.4
     */
    @Nullable
    default MinesSchematic getDefaultSchematic() {
        return null;
    }

    /**
     * Looks up a schematic by name &mdash; the file name without its extension.
     *
     * @param name the schematic name
     * @return the schematic, or {@code null} if none is registered under that name
     * @since 1.4
     */
    @Nullable
    default MinesSchematic getSchematicByName(@NotNull String name) {
        return null;
    }

    /**
     * The cost to upgrade this mine to the next tier, as an exact decimal.
     *
     * @param mine the mine to price
     * @return the next upgrade cost, never null
     * @since 1.4
     */
    @NotNull
    default BigDecimal getNextUpgradeCostExact(@NotNull PrivateMine mine) {
        return BigDecimal.valueOf(getNextUpgradeCost(mine));
    }
}
