package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is about to be reset.
 * <p>
 * This event is called before the mine reset occurs and can be cancelled.
 * Cancelling this event will prevent the mine from being reset.
 */
@Getter
public final class PrivateMinePreResetEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PrivateMine mine;

    @Setter
    private boolean cancelled;

    /**
     * Constructs a new {@code PrivateMinePreResetEvent}.
     *
     * @param mine the {@link PrivateMine} that is about to be reset
     */
    public PrivateMinePreResetEvent(PrivateMine mine) {
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
