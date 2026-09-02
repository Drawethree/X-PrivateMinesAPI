package dev.drawethree.xprivatemines.api.model.block;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Read-only view of a single block usable in a mine: either a vanilla material or a custom block
 * supplied by a provider plugin (ItemsAdder {@code namespace:id}, Nexo {@code nexo:id},
 * Oraxen {@code oraxen:id}).
 * <p>
 * {@link #getId()} is the canonical primary key and the exact string accepted by every
 * {@code String}-taking block setter in this API (see
 * {@link dev.drawethree.xprivatemines.api.manager.PrivateMinesManager#setBlock(
 * dev.drawethree.xprivatemines.api.model.PrivateMine, String)}). Round-tripping a ref through its
 * id is always lossless.
 * <p>
 * <b>Equality contract.</b> Two refs are equal iff they denote the same block: for custom blocks,
 * equal {@link #getId()}; for vanilla blocks, the same underlying material regardless of the
 * spelling the id was written in. {@code hashCode} must agree, so refs are usable as map keys
 * (see {@link dev.drawethree.xprivatemines.api.model.MineTier#getBlockComposition()}).
 * <p>
 * <b>Resolution.</b> Provider plugins publish their content asynchronously after server start, so
 * a ref for a custom block may exist before its provider can resolve it; see {@link #isResolved()}.
 * Refs are cheap value views and safe to hold, but the resolution-dependent accessors
 * ({@link #getBukkitMaterial()}, {@link #getIcon()}, {@link #getDisplayName()}) must be called from
 * the main server thread &mdash; the backing provider APIs are not thread-safe. {@link #getId()},
 * {@link #isCustom()} and {@link #getProvider()} are plain field reads and safe anywhere.
 *
 * @since 1.4
 */
public interface MineBlockRef {

    /**
     * The canonical id of this block, e.g. {@code "DIAMOND_ORE"}, {@code "nexo:ruby_ore"},
     * {@code "myns:ruby"}. Safe off the main thread.
     *
     * @return the canonical id, never null or blank
     */
    @NotNull String getId();

    /**
     * Whether this block comes from a custom-block provider rather than vanilla Minecraft.
     * Safe off the main thread.
     *
     * @return {@code true} for a provider block
     */
    boolean isCustom();

    /**
     * The Bukkit plugin name of the owning provider ({@code "ItemsAdder"}, {@code "Nexo"},
     * {@code "Oraxen"}). Safe off the main thread.
     *
     * @return the provider name, or {@code null} for a vanilla block
     */
    @Nullable String getProvider();

    /**
     * The vanilla material for a vanilla block; for a custom block, the underlying material the
     * provider places in the world (useful as an icon or texture stand-in).
     *
     * @return the material, or {@code null} only when a custom block cannot currently be resolved
     * @see #isResolved()
     */
    @Nullable Material getBukkitMaterial();

    /**
     * Whether this block can be resolved right now. Always {@code true} for vanilla blocks.
     * <p>
     * A {@code false} custom block is still a legal id and will start resolving once its provider
     * finishes loading; callers should render it as "pending" rather than reject it.
     *
     * @return {@code true} if the block resolves right now
     */
    boolean isResolved();

    /**
     * A human-readable name for menus &mdash; {@code "Diamond Ore"}, or the provider's configured
     * display name for a custom block.
     *
     * @return the display name, never null
     */
    @NotNull String getDisplayName();

    /**
     * A fresh {@link ItemStack} suitable as a GUI or dashboard icon. Falls back to stone when the
     * block cannot be resolved. Main thread only; the caller owns the returned stack.
     *
     * @return a new icon stack
     */
    @NotNull ItemStack getIcon();

    /**
     * The suffix used by the {@code xprivatemines.block.<node>} permission for this block, so an
     * addon can show which blocks a given player is allowed to select. Vanilla blocks use the
     * lowercase material name ({@code diamond_ore}); custom blocks replace the id's colon with a
     * dot ({@code nexo.ruby_ore}).
     *
     * @return the permission node suffix
     */
    @NotNull String getPermissionNode();
}
