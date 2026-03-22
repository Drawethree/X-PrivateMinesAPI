package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is being created.
 * <p>
 * This event is called before the mine is fully created and can be cancelled.
 */
@Getter
public final class PrivateMineCreateEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PrivateMine mine;

    /**
     * Constructs a new {@code PrivateMineCreateEvent}.
     *
     * @param mine    the {@link PrivateMine} that is being created
     */
    public PrivateMineCreateEvent(PrivateMine mine) {
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
