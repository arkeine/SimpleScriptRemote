
package ch.arkeine.simplescriptremote.generic_gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import ch.arkeine.simplescriptremote.generic_gui.gui_building.enumerations.Orientation;
import ch.arkeine.simplescriptremote.generic_gui.gui_building.interfaces.DisplayGridInterface;

public class JPanelRemote extends JPanel
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelRemote(DisplayGridInterface gridContent)
		{
		this.gridContent = gridContent;
		geometry();
		control();
		appearance();
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry()
		{

		List<List<JPanel>> base = gridContent.getListGridBase();
		Orientation orientation = gridContent.getGridOrientation();

		// set the orientation of the content
		if (orientation == Orientation.VERTICAL)
			{
			GridLayout layout = new GridLayout(0, 1);
			setLayout(layout);
			}
		else
			{
			GridLayout layout = new GridLayout(1, 0);
			setLayout(layout);
			}

		// fill with components
		for(List<JPanel> subList:base)
			{
			JPanel subPanel = new JPanel();

			if (orientation == Orientation.VERTICAL)
				{
				GridLayout layout = new GridLayout(1, 0);
				subPanel.setLayout(layout);
				}
			else
				{
				GridLayout layout = new GridLayout(0, 1);
				subPanel.setLayout(layout);
				}

			for(JPanel component:subList)
				{
				subPanel.add(component);
				}

			add(subPanel);
			}
		}

	private void control()
		{
		// nothing
		}

	private void appearance()
		{
		// nothing
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	// Interface
	private DisplayGridInterface gridContent;

	}
