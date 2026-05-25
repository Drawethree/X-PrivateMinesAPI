package dev.drawethree.xprivatemines.api.addons;

/**
 * Represents a modular addon for the X-PrivateMines plugin.
 * <p>
 * Implementations of this interface can be loaded dynamically by X-PrivateMines to
 * provide additional functionality, such as custom enchants, features, or integrations.
 * </p>
 * <p>
 * Every addon JAR must declare the following attributes in its manifest:
 * <ul>
 *   <li>{@code X-PrivateMines-Addon-Class} — fully-qualified name of the class implementing this interface (required)</li>
 *   <li>{@code X-PrivateMines-Addon-Name} — display name shown in the addon manager GUI</li>
 *   <li>{@code X-PrivateMines-Addon-Version} — version string</li>
 *   <li>{@code X-PrivateMines-Addon-Author} — author name</li>
 *   <li>{@code X-PrivateMines-Addon-Description} — short description</li>
 *   <li>{@code X-PrivateMines-Min-Version} — minimum X-PrivateMines version required (e.g. {@code 2026.2.0}), optional</li>
 * </ul>
 * </p>
 */
public interface XPrivateMinesAddon {

    /**
     * Called when the addon is being enabled.
     * <p>
     * Use the provided {@link XPrivateMinesAddonContext} to access module APIs, register Bukkit
     * event listeners, obtain a dedicated data folder, and log messages.
     * </p>
     *
     * @param context the runtime context provided by X-PrivateMines
     */
    void onEnable(XPrivateMinesAddonContext context);

    /**
     * Called when the addon is being disabled.
     * <p>
     * Use this to clean up resources or perform any shutdown logic.
     * Bukkit event listeners registered via {@link XPrivateMinesAddonContext#registerEvents} are
     * unregistered automatically by the server on plugin shutdown, but you should release
     * any other held resources here.
     * </p>
     */
    void onDisable();
}
