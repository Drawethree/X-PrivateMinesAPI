package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player enters a private mine's outer region.
 * <p>
 * Cancelling this event prevents the player from entering: their movement or
 * teleportation is blocked and they are left at their previous position.
 */
@Getter
public final class MineEnterEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Player player;
    private final PrivateMine mine;

    @Setter
    private boolean cancelled;

    public MineEnterEvent(Player player, PrivateMine mine) {
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
