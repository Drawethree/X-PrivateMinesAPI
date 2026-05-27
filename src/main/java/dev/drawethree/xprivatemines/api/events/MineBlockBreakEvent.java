package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player breaks a block inside the ore region of a private mine.
 * <p>
 * Cancelling this event prevents the block from breaking and stops the mine's
 * remaining-block counter from being decremented.
 */
@Getter
public final class MineBlockBreakEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Player player;
    private final PrivateMine mine;
    private final Block block;

    @Setter
    private boolean cancelled;

    public MineBlockBreakEvent(Player player, PrivateMine mine, Block block) {
        this.player = player;
        this.mine = mine;
        this.block = block;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
