package dev.drawethree.xprivatemines.api.model;

import java.io.File;

public interface MinesSchematic {

    /**
     * Gets the file associated with this schematic.
     *
     * @return the schematic file
     */
    File getFile();

    /**
     * Gets the name of this schematic.
     *
     * @return the schematic name
     */
    String getName();

    /**
     * Gets the settings applied to this schematic.
     *
     * @return schematic settings
     */
    SchematicSettings getSettings();
}
