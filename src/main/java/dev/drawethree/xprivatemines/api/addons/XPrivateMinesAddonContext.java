package dev.drawethree.xprivatemines.api.addons;

import dev.drawethree.xprivatemines.api.XPrivateMinesAPI;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.logging.Logger;

/**
 * Contextual environment provided to an addon when it is enabled.
 * Gives the addon everything it needs without requiring static singleton access.
 */
public interface XPrivateMinesAddonContext {

    /**
     * Returns the X-PrivateMines API for accessing all module APIs.
     */
    XPrivateMinesAPI getAPI();

    /**
     * Returns a dedicated folder for this addon's config and data files.
     * The folder is created automatically if it does not exist.
     */
    File getDataFolder();

    /**
     * Returns a logger prefixed with this addon's name.
     */
    Logger getLogger();

    /**
     * Returns the addon's display name, as declared in the JAR manifest.
     */
    String getAddonName();

    /**
     * Returns the addon's version string, as declared in the JAR manifest.
     */
    String getAddonVersion();

    /**
     * Returns the addon's author, as declared in the JAR manifest.
     */
    String getAddonAuthor();

    /**
     * Registers a Bukkit event listener scoped to the X-PrivateMines plugin.
     * Use this instead of calling Bukkit.getPluginManager().registerEvents() directly.
     */
    void registerEvents(Listener listener);
}
