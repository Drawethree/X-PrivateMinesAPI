package dev.drawethree.xprivatemines.api.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

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

    /**
     * Withdraws an exact amount.
     * <p>
     * Prefer this over {@link #withdraw(Player, double)} on servers whose currency exceeds
     * {@code double} precision &mdash; X-Prison prison economies routinely do, and a {@code double}
     * round-trip of a balance above 2<sup>53</sup> silently changes the value.
     * <p>
     * The default implementation degrades to the {@code double} overload, so a provider from an
     * older build still works, lossily.
     *
     * @param player the player
     * @param amount the amount to withdraw
     * @return {@code true} if the withdrawal succeeded
     * @since 1.4
     */
    default boolean withdraw(Player player, BigDecimal amount) {
        return withdraw(player, amount == null ? 0.0D : amount.doubleValue());
    }

    /**
     * Exact-precision form of {@link #deposit(Player, double)}.
     *
     * @param player the player
     * @param amount the amount to deposit
     * @return {@code true} if the deposit succeeded
     * @since 1.4
     */
    default boolean deposit(Player player, BigDecimal amount) {
        return deposit(player, amount == null ? 0.0D : amount.doubleValue());
    }

    /**
     * Exact-precision form of {@link #has(Player, double)}.
     *
     * @param player the player
     * @param amount the amount to check
     * @return {@code true} if the player can afford it
     * @since 1.4
     */
    default boolean has(Player player, BigDecimal amount) {
        return has(player, amount == null ? 0.0D : amount.doubleValue());
    }

    /**
     * Exact-precision form of {@link #format(Player, double)}, using the active economy's own
     * formatter (symbol, suffixes, locale).
     *
     * @param player the player
     * @param amount the amount to format
     * @return formatted currency string
     * @since 1.4
     */
    default String format(Player player, BigDecimal amount) {
        return format(player, amount == null ? 0.0D : amount.doubleValue());
    }

    /**
     * The player's balance at full precision.
     * <p>
     * Named distinctly from {@link #getBalance(Player)} because Java cannot overload on return
     * type. This is the accessor to use for anything displayed to a player or persisted.
     *
     * @param player the player
     * @return the balance, or {@link BigDecimal#ZERO} if no economy backend is configured
     * @since 1.4
     */
    @NotNull
    default BigDecimal getBalanceExact(Player player) {
        return BigDecimal.valueOf(getBalance(player));
    }

    /**
     * Offline-player form of {@link #getBalanceExact(Player)}.
     *
     * @param player the offline player
     * @return the balance, or {@link BigDecimal#ZERO} if the backend has no offline support
     * @since 1.4
     */
    @NotNull
    default BigDecimal getBalanceExact(OfflinePlayer player) {
        return BigDecimal.valueOf(getBalance(player));
    }

    /**
     * The display name of the currency mines are paid in &mdash; "Tokens", "Gems", "Dollars" and so
     * on &mdash; taken from the active backend.
     * <p>
     * Servers running a non-money currency should use this rather than hardcoding "money" in their
     * UI.
     *
     * @return the currency name, never null or blank
     * @since 1.4
     */
    @NotNull
    default String getCurrencyName() {
        return "money";
    }
}
