package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is closed.
 * <p>
 * This event is called after the mine has been fully closed.
 */
@Getter
public final class PrivateMineCloseEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PrivateMine mine;

    /**
     * Constructs a new {@code PrivateMineCloseEvent}.
     *
     * @param mine the {@link PrivateMine} that has been closed
     */
    public PrivateMineCloseEvent(PrivateMine mine) {
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
