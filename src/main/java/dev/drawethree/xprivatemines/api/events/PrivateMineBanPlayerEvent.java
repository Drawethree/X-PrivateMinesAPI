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
 * Event fired when a player is banned from a private mine.
 * <p>
 * This event is called after the player has been banned from the mine.
 * It can be cancelled to prevent the ban from taking effect.
 */
@Getter
public final class PrivateMineBanPlayerEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Setter
    private boolean cancelled;

    /**
     * The private mine from which the player is being banned.
     */
    private final PrivateMine mine;

    /**
     * The player being banned.
     */
    private final OfflinePlayer player;

    /**
     * Constructs a new {@code PrivateMineBanPlayerEvent}.
     *
     * @param mine the {@link PrivateMine} from which the player is being banned
     * @param player the {@link OfflinePlayer} who is being banned
     */
    public PrivateMineBanPlayerEvent(PrivateMine mine, OfflinePlayer player) {
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
