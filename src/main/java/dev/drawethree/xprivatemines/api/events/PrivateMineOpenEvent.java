package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is opened.
 * <p>
 * This event is called after the mine has been successfully opened.
 */
@Getter
public final class PrivateMineOpenEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PrivateMine mine;

    /**
     * Constructs a new {@code PrivateMineOpenEvent}.
     *
     * @param mine the {@link PrivateMine} that has been opened
     */
    public PrivateMineOpenEvent(PrivateMine mine) {
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
