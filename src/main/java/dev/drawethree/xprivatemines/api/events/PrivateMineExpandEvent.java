package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is expanded.
 * <p>
 * This event is called after the mine has been successfully expanded.
 */
@Getter
public final class PrivateMineExpandEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Setter
    private boolean cancelled;

    private final PrivateMine mine;

    @Setter
    private int expandLevel;

    /**
     * Constructs a new {@code PrivateMineExpandEvent}.
     *
     * @param mine        the {@link PrivateMine} that has been expanded
     * @param expandLevel the new expansion level of the mine
     */
    public PrivateMineExpandEvent(PrivateMine mine, int expandLevel) {
        this.mine = mine;
        this.expandLevel = expandLevel;
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
