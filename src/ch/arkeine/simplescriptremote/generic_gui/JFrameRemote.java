
package ch.arkeine.simplescriptremote.generic_gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdom2.JDOMException;

import ch.arkeine.simplescriptremote.generic_gui.gui_building.xml_builder.XMLProperties;
import ch.arkeine.simplescriptremote.tool.SharredConstants;

public class JFrameRemote extends JFrame
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JFrameRemote()
		{
		try
			{
			interfacePropoerties = new XMLProperties(propertiesPath);
			geometry();
			control();
			appearance();
			}
		catch (JDOMException | IOException e)
			{
			Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
			l.warning(e.getMessage());

			JOptionPane.showMessageDialog(this, "Can not load interface description");
			//TODO create a custom exception
			System.exit(-1);
			}
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry()
		{
		panelRemote = new JPanelRemote(interfacePropoerties);

		setLayout(new BorderLayout());

		add(panelRemote, BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Simple Script Remote");
		setSize(300, 300);
		setMinimumSize(getSize());
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	// interface
	private JPanelRemote panelRemote;

	// tools
	private XMLProperties interfacePropoerties;

	/* ============================================ */
	// CONSTANT
	/* ============================================ */

	private final String propertiesPath = "interface.xml";

	}
