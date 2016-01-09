
package ch.arkeine.simplescriptremote.generic_gui.communication_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ch.arkeine.simplescriptremote.generic_gui.JPanelLed;
import ch.arkeine.simplescriptremote.protocols.interfaces.CommandInterface;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandErrorListener;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandListener;

public class JPanelSimpleExecutableButton extends JPanel implements ResultCommandErrorListener ,ResultCommandListener
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelSimpleExecutableButton(CommandInterface command)
		{
		this.command = command;

		geometry();
		control();
		appearance();
		}

	/* ============================================ */
	// OVERRIDE
	/* ============================================ */

	@Override
	public void onCommandResult(String resultValue)
		{
		panelLedStatus.setActivationState(true);
		}

	@Override
	public void onCommunicationError(Exception e)
		{
		panelLedStatus.setActivationState(false);
		}

	@Override
	public void onCommandError(String resultValue, int resultCode)
		{
		panelLedStatus.setActivationState(false);
		}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public void setTextBorder(String textInfo)
		{
		borderTitle.setTitle(textInfo);
		}

	public String getTextBorder()
		{
		return borderTitle.getTitle();
		}

	public void setTextButton(String text)
		{
		this.buttonExecute.setText(text);
		}

	public String getTextButton()
		{
		return buttonExecute.getText();
		}

	/* ============================================ */
	// DELEGATE
	/* ============================================ */

	public void setColorOk(Color c)
		{
		panelLedStatus.setColorActivated(c);
		}

	public void setColorError(Color c)
		{
		panelLedStatus.setColorDesactivated(c);
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry()
		{
		buttonExecute = new JButton();
		panelLedStatus = new JPanelLed();
		borderTitle = BorderFactory.createTitledBorder("");

		setBorder(borderTitle);
		setLayout(new BorderLayout());

		add(panelLedStatus, BorderLayout.SOUTH);
		add(buttonExecute, BorderLayout.CENTER);
		}

	private void control()
		{
		command.addCommandResultErrorListener(this);
		command.addCommandResultListener(this);

		buttonExecute.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (thread == null || !thread.isAlive())
						{
						thread = new Thread(getTask());
						thread.start();
						}
					}
			});
		}

	private void appearance()
		{
		// nothing
		}

	private Runnable getTask()
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					panelLedStatus.setTextInfo("Updating");
					JPanelSimpleExecutableButton.this.repaint();
					command.executeCommande();
					panelLedStatus.setTextInfo("");
					JPanelSimpleExecutableButton.this.repaint();
					}
			};
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	// tools
	private CommandInterface command;
	private Thread thread;

	// interface
	private JButton buttonExecute;
	private JPanelLed panelLedStatus;
	private TitledBorder borderTitle;
	}
