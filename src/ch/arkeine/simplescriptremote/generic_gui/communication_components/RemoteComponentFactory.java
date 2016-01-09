
package ch.arkeine.simplescriptremote.generic_gui.communication_components;

import java.awt.Color;

import javax.swing.JPanel;

import ch.arkeine.simplescriptremote.protocols.interfaces.CommandInterface;

public class RemoteComponentFactory
	{

	/* ============================================ */
	// SIMPLE EXECUTABLE LED
	/* ============================================ */

	public static JPanel createSimpleExecutableLed(CommandInterface command, int delay, String borderText)
		{
		return createSimpleExecutableLed(command, delay, borderText, Color.GREEN, Color.RED);
		}

	public static JPanel createSimpleExecutableLed(CommandInterface command, int delay, String borderText, Color colorOk, Color colorError)
		{
		JPanelSimpleExecutableLed p = new JPanelSimpleExecutableLed(command, delay);
		p.setTextBorder(borderText);
		p.setColorOk(colorOk);
		p.setColorError(colorError);

		return p;
		}

	/* ============================================ */
	// SIMPLE EXECUTABLE BUTTON
	/* ============================================ */

	public static JPanel createSimpleExecutableButton(CommandInterface command, String borderText, String buttonText)
		{
		return createSimpleExecutableButton(command, borderText, buttonText, Color.GREEN, Color.RED);
		}

	public static JPanel createSimpleExecutableButton(CommandInterface command, String borderText, String buttonText, Color colorOk, Color colorError)
		{
		JPanelSimpleExecutableButton p = new JPanelSimpleExecutableButton(command);
		p.setTextBorder(borderText);
		p.setTextButton(buttonText);
		p.setColorOk(colorOk);
		p.setColorError(colorError);

		return p;
		}
	}
