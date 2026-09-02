package dev.drawethree.xprivatemines.api.model.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The set of blocks an addon may offer in a block picker, and the entry point for validating a
 * block id before handing it to a setter.
 * <p>
 * Obtain via {@link dev.drawethree.xprivatemines.api.XPrivateMinesAPI#getBlockCatalogue()}.
 * <p>
 * <b>Threading.</b> The list accessors are main-thread only: building them touches the custom-block
 * providers, which are not thread-safe. An addon serving a picker over HTTP should build its
 * payload on the main thread and cache it rather than querying per request.
 *
 * @since 1.4
 */
public interface MineBlockCatalogue {

    /**
     * The server owner's curated selectable list &mdash; the {@code gui.blocks} config list backing
     * the in-game block-change menu, in config order, vanilla and custom mixed.
     * <p>
     * This is what a picker should show by default: it is the only list the server owner has
     * actually approved. Re-read after {@code /pmine reload} and after a provider finishes loading,
     * so do not cache it indefinitely.
     *
     * @return the selectable blocks, never null
     */
    @NotNull List<MineBlockRef> getSelectableBlocks();

    /**
     * Every placeable vanilla block on this server version, sorted by material name. Offered for
     * admin-facing pickers that intentionally bypass {@link #getSelectableBlocks()}.
     *
     * @return the vanilla blocks, never null
     */
    @NotNull List<MineBlockRef> getVanillaBlocks();

    /**
     * Every custom block registered by the currently loaded providers.
     * <p>
     * Empty when no provider is installed, and empty (not partial) while a provider is still
     * loading &mdash; re-query once {@link #getActiveProviders()} lists it.
     *
     * @return the custom blocks, never null
     */
    @NotNull List<MineBlockRef> getCustomBlocks();

    /**
     * Plugin names of the custom-block providers that are installed and have finished loading.
     *
     * @return the active provider names, never null
     */
    @NotNull List<String> getActiveProviders();

    /**
     * Resolves an id &mdash; a vanilla material name or a namespaced custom id &mdash; to a ref.
     *
     * @param id the block id to resolve
     * @return the ref, or {@code null} if the id names neither a vanilla material nor a block
     * claimable by a known provider
     */
    @Nullable MineBlockRef resolve(@NotNull String id);

    /**
     * Whether {@code id} would be accepted by the block setters right now. Use this to validate
     * user input up front, so an unknown block can be reported with context instead of surfacing
     * as a bare {@code false} from a setter.
     *
     * @param id the block id to check
     * @return {@code true} if the id is usable right now
     */
    boolean isValidBlockId(@NotNull String id);
}
