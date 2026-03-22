package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired after a private mine has been reset.
 * <p>
 * This event is called once the mine reset process has fully completed.
 */
@Getter
public final class PrivateMinePostResetEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PrivateMine mine;

    /**
     * Constructs a new {@code PrivateMinePostResetEvent}.
     *
     * @param mine the {@link PrivateMine} that has been reset
     */
    public PrivateMinePostResetEvent(PrivateMine mine) {
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
