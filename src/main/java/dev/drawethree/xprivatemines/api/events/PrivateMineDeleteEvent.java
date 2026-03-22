package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is about to be deleted.
 * <p>
 * This event is called before the mine is deleted and can be cancelled.
 * Cancelling this event will prevent the mine from being deleted.
 */
@Getter
public final class PrivateMineDeleteEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Setter
    private boolean cancelled;

    private final PrivateMine mine;

    /**
     * Constructs a new {@code PrivateMineDeleteEvent}.
     *
     * @param mine the {@link PrivateMine} that is being deleted
     */
    public PrivateMineDeleteEvent(PrivateMine mine) {
        this.mine = mine;
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
