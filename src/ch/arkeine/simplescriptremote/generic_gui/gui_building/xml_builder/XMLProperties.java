
package ch.arkeine.simplescriptremote.generic_gui.gui_building.xml_builder;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import ch.arkeine.simplescriptremote.generic_gui.communication_components.RemoteComponentFactory;
import ch.arkeine.simplescriptremote.generic_gui.gui_building.enumerations.Component;
import ch.arkeine.simplescriptremote.generic_gui.gui_building.enumerations.Orientation;
import ch.arkeine.simplescriptremote.generic_gui.gui_building.enumerations.Protocol;
import ch.arkeine.simplescriptremote.generic_gui.gui_building.interfaces.DisplayGridInterface;
import ch.arkeine.simplescriptremote.protocols.interfaces.CommandInterface;
import ch.arkeine.simplescriptremote.protocols.ssh.SessionManager;
import ch.arkeine.simplescriptremote.protocols.ssh.communication.CommandSSH;

public class XMLProperties implements DisplayGridInterface
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public XMLProperties(String file) throws JDOMException, IOException
		{
		this.listGridBase = new ArrayList<List<JPanel>>(4);
		this.gridOrientation = Orientation.HORIZONTAL;
		load(file);
		}

	/* ============================================ */
	// OVERRIDE
	/* ============================================ */

	@Override
	public Orientation getGridOrientation()
		{
		return gridOrientation;
		}

	@Override
	public List<List<JPanel>> getListGridBase()
		{
		return this.listGridBase;
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void load(String file) throws JDOMException, IOException
		{
		// load document
		SAXBuilder sax = new SAXBuilder();
		Document document = sax.build(new File(file));

		// initialization
		Element root = document.getRootElement();
		listGridBase.clear();
		gridOrientation = Orientation.valueOf(root.getAttributeValue(ORIENTATION_KEY, Orientation.VERTICAL.name()));

		// fill the list
		for(Element base:root.getChildren())
			{
			ArrayList<JPanel> listLine = new ArrayList<>(4);
			for(Element line:base.getChildren())
				{
				listLine.add(createComponent(line));
				}
			listGridBase.add(listLine);
			}
		}

	/* ============================================ */
	// CREATE GRAPHICAL COMPONENT
	/* ============================================ */

	private JPanel createComponent(Element e)
		{
		String componentType = e.getAttributeValue(COMPONENT_KEY, "");

		switch(Component.valueOf(componentType))
			{
			case SIMPLE_BUTTON:
				return createSimpleExecutableButton(e);

			case SIMPLE_LED:
				return createSimpleExecutableLed(e);
			default:
				return new JPanel();
			}

		}

	private JPanel createSimpleExecutableLed(Element e)
		{
		String colorOk = e.getAttributeValue(COLOR_OK_KEY, "#00FF00");
		String colorError = e.getAttributeValue(COLOR_ERROR_KEY, "#FF0000");
		String borderText = e.getAttributeValue(BORDER_TEXT_KEY, "");
		String delay = e.getAttributeValue(DELAY_KEY, "");
		String protocol = e.getAttributeValue(PROTOCOL_KEY, "0");
		String command = e.getText();

		return RemoteComponentFactory.createSimpleExecutableLed(createCommand(Protocol.valueOf(protocol), command), Integer.valueOf(delay), borderText, Color.decode(colorOk), Color.decode(colorError));
		}

	private JPanel createSimpleExecutableButton(Element e)
		{
		String colorOk = e.getAttributeValue(COLOR_OK_KEY, "#00FF00");
		String colorError = e.getAttributeValue(COLOR_ERROR_KEY, "#FF0000");
		String borderText = e.getAttributeValue(BORDER_TEXT_KEY, "");
		String buttonText = e.getAttributeValue(BUTTON_TEXT_KEY, "");
		String protocol = e.getAttributeValue(PROTOCOL_KEY, "0");
		String command = e.getText();

		return RemoteComponentFactory.createSimpleExecutableButton(createCommand(Protocol.valueOf(protocol), command), borderText, buttonText, Color.decode(colorOk), Color.decode(colorError));

		}

	/* ============================================ */
	// CREATE COMMAND
	/* ============================================ */

	private CommandInterface createCommand(Protocol p, String c)
		{
		CommandInterface result = null;
		switch(p)
			{
			case SSH:
				result = new CommandSSH(SessionManager.getInstance());
				result.setCommand(c);
				break;
			}

		return result;
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	private Orientation gridOrientation;
	private List<List<JPanel>> listGridBase;

	/* ============================================ */
	// CONSTANT
	/* ============================================ */

	private static final String ORIENTATION_KEY = "orientation";
	private static final String COLOR_OK_KEY = "color-result-ok";
	private static final String COLOR_ERROR_KEY = "color-result-error";
	private static final String BORDER_TEXT_KEY = "border-text";
	private static final String BUTTON_TEXT_KEY = "button-text";
	private static final String DELAY_KEY = "delay";
	private static final String PROTOCOL_KEY = "protocol";
	private static final String COMPONENT_KEY = "component";
	}
