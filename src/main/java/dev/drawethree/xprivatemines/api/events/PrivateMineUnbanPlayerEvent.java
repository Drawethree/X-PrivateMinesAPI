package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a player is unbanned from a private mine.
 * <p>
 * This event is called after the player has been unbanned from the mine.
 * It can be cancelled to prevent the unban from taking effect.
 */
@Getter
public final class PrivateMineUnbanPlayerEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Setter
    private boolean cancelled;

    /**
     * The private mine from which the player is being unbanned.
     */
    private final PrivateMine mine;

    /**
     * The player being unbanned.
     */
    private final OfflinePlayer player;

    /**
     * Constructs a new {@code PrivateMineUnbanPlayerEvent}.
     *
     * @param mine the {@link PrivateMine} from which the player is being unbanned
     * @param player the {@link OfflinePlayer} who is being unbanned
     */
    public PrivateMineUnbanPlayerEvent(PrivateMine mine, OfflinePlayer player) {
        this.mine = mine;
        this.player = player;
    }

    /**
     * Gets the handler list for this event.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    /**
     * Gets the handlers for this event.
     *
     * @return the handler list
     */
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
