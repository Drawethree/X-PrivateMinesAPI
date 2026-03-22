package dev.drawethree.xprivatemines.api.events;

import dev.drawethree.xprivatemines.api.model.MineTier;
import dev.drawethree.xprivatemines.api.model.PrivateMine;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event fired when a private mine is upgraded.
 * <p>
 * This event is called after the mine has been upgraded.
 * It is cancellable; cancelling it will prevent the upgrade from occurring.
 */
@Getter
public final class PrivateMineUpgradeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Setter
    private boolean cancelled;

    private final PrivateMine mine;

    private final MineTier oldTier;

    @Setter
    private MineTier newTier;

    /**
     * Constructs a new {@code PrivateMineUpgradeEvent}.
     *
     * @param mine     the {@link PrivateMine} being upgraded
     * @param oldTier  the previous {@link MineTier} of the mine
     * @param newTier  the new {@link MineTier} the mine is being upgraded to
     */
    public PrivateMineUpgradeEvent(PrivateMine mine, MineTier oldTier, MineTier newTier) {
        this.mine = mine;
        this.oldTier = oldTier;
        this.newTier = newTier;
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
