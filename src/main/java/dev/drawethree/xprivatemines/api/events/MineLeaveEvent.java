package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player leaves a private mine's outer region.
 * <p>
 * Also fires when the player disconnects or is kicked while inside a mine.
 * This event is not cancellable.
 */
@Getter
public final class MineLeaveEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Player player;
    private final PrivateMine mine;

    public MineLeaveEvent(Player player, PrivateMine mine) {
        this.player = player;
        this.mine = mine;
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
