
package ch.arkeine.simplescriptremote.generic_gui.communication_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ch.arkeine.simplescriptremote.generic_gui.JPanelLed;
import ch.arkeine.simplescriptremote.protocols.interfaces.CommandInterface;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandErrorListener;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandListener;
import ch.arkeine.simplescriptremote.tool.SharredConstants;

public class JPanelSimpleExecutableLed extends JPanel implements ResultCommandErrorListener, ResultCommandListener
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelSimpleExecutableLed(CommandInterface command, int delay)
		{
		this.command = command;

		geometry();
		control();
		appearance();

		this.timer = new Thread(getTask(delay));
		this.timer.start();
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
		panelLedStatus = new JPanelLed();
		borderTitle = BorderFactory.createTitledBorder("");

		setBorder(borderTitle);
		setLayout(new BorderLayout());

		add(panelLedStatus, BorderLayout.CENTER);
		}

	private void control()
		{
		command.addCommandResultErrorListener(this);
		command.addCommandResultListener(this);
		}

	private void appearance()
		{
		// nothing
		}

	private Runnable getTask(final int delay)
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					try
						{
						while(true)
							{
							panelLedStatus.setTextInfo("Updating");
							JPanelSimpleExecutableLed.this.repaint();
							command.executeCommande();
							panelLedStatus.setTextInfo("");
							JPanelSimpleExecutableLed.this.repaint();
							Thread.sleep(delay);
							}
						}
					catch (InterruptedException e)
						{
						Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
						l.info(e.getMessage());
						}
					}
			};
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	// tools
	private CommandInterface command;
	private Thread timer;

	// interface
	private JPanelLed panelLedStatus;
	private TitledBorder borderTitle;

	}
