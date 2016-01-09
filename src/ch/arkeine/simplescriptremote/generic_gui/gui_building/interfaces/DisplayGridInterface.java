
package ch.arkeine.simplescriptremote.generic_gui.gui_building.interfaces;

import java.util.List;

import javax.swing.JPanel;

import ch.arkeine.simplescriptremote.generic_gui.gui_building.enumerations.Orientation;


/**
 * The GUI is based on gridLayout imbricated. Please see TODO
 *
 * In the way to build custom GUI dynamically, a concret object can load
 * the GUI settings from a description file (xml, properties, csv, etc...).
 *
 * This object must implement this interface.
 */
public interface DisplayGridInterface
	{

	/**
	 * The layout grid has a fixed number of collumn/row. This method indicate
	 * the which one is fixed : collumn or row.
	 *
	 * On a <code>Orientation.VERTICAL</code> return the collumns are fixed
	 * and the gowing direction is the bottom
	 *
	 * On a <code>Orientation.HORIZONTAL</code> return the rows are fixed
	 * and the gowing direction is the left
	 */
	public Orientation getGridOrientation();

	/**
	 * Return the GUI description content.
	 *
	 * The first list is for the fixed direction (row/collumn depending the orientation).
	 * The second list is for the other direction and contains the component description to create.
	 */
	public List<List<JPanel>> getListGridBase();
	}

