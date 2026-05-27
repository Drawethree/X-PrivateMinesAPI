package dev.drawethree.xprivatemines.api.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Provides economy operations through whichever currency backend X-PrivateMines
 * is configured to use (Vault, X-Prison, ExcellentEconomy, PlayerPoints).
 * <p>
 * Obtain via {@link dev.drawethree.xprivatemines.api.XPrivateMinesAPI#getEconomyProvider()}.
 */
public interface MineEconomyProvider {

    /**
     * Withdraws the given amount from the player's balance.
     *
     * @param player the player
     * @param amount the amount to withdraw
     * @return {@code true} if the withdrawal succeeded
     */
    boolean withdraw(Player player, double amount);

    /**
     * Deposits the given amount into the player's balance.
     *
     * @param player the player
     * @param amount the amount to deposit
     * @return {@code true} if the deposit succeeded
     */
    boolean deposit(Player player, double amount);

    /**
     * Returns the player's current balance.
     *
     * @param player the player
     * @return their balance
     */
    double getBalance(Player player);

    /**
     * Returns the balance for an offline player.
     * Returns 0.0 if the provider does not support offline lookups.
     *
     * @param player the offline player
     * @return their balance, or 0.0 if unsupported
     */
    default double getBalance(OfflinePlayer player) {
        Player online = player.getPlayer();
        return online != null ? getBalance(online) : 0.0;
    }

    /**
     * Returns {@code true} if the player has at least {@code amount} in their balance.
     *
     * @param player the player
     * @param amount the amount to check
     * @return {@code true} if the player can afford it
     */
    boolean has(Player player, double amount);

    /**
     * Returns a human-readable formatted string for the given amount using the
     * active economy's currency symbol and formatting rules.
     *
     * @param player the player (used by some providers for locale / formatting)
     * @param amount the amount to format
     * @return formatted currency string
     */
    String format(Player player, double amount);
}
