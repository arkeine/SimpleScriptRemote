
package ch.arkeine.simplescriptremote.protocols.ssh.communication;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.swing.event.EventListenerList;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import ch.arkeine.simplescriptremote.protocols.interfaces.CommandInterface;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandErrorListener;
import ch.arkeine.simplescriptremote.protocols.interfaces.ResultCommandListener;
import ch.arkeine.simplescriptremote.protocols.ssh.SessionManager;
import ch.arkeine.simplescriptremote.tool.SharredConstants;

public class CommandSSH implements Runnable ,CommandInterface
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public CommandSSH(SessionManager manager)
		{
		this.sessionManager = manager;
		this.command = "";
		this.listenerList = new EventListenerList();
		}

	/* ============================================ */
	// LISTENER
	/* ============================================ */

	@Override
	public void addCommandResultListener(ResultCommandListener listener)
		{
		listenerList.add(ResultCommandListener.class, listener);
		}

	@Override
	public void removeCommandResultListener(ResultCommandListener listener)
		{
		listenerList.remove(ResultCommandListener.class, listener);
		}

	@Override
	public void addCommandResultErrorListener(ResultCommandErrorListener listener)
		{
		listenerList.add(ResultCommandErrorListener.class, listener);
		}

	@Override
	public void removeCommandResulErrortListener(ResultCommandErrorListener listener)
		{
		listenerList.remove(ResultCommandErrorListener.class, listener);
		}

	protected void fireOnCommandResultErrorEvent(String resultValue, int resultCode)
		{
		for(ResultCommandErrorListener l:listenerList.getListeners(ResultCommandErrorListener.class))
			{
			l.onCommandError(resultValue, resultCode);
			}
		}

	protected void fireOnCommunicationErrorEvent(Exception e)
		{
		for(ResultCommandErrorListener l:listenerList.getListeners(ResultCommandErrorListener.class))
			{
			l.onCommunicationError(e);
			}
		}

	protected void fireOnCommandResultEvent(String result)
		{
		for(ResultCommandListener l:listenerList.getListeners(ResultCommandListener.class))
			{
			l.onCommandResult(result);
			}
		}

	/* ============================================ */
	// OVERRIDE
	/* ============================================ */

	@Override
	public void executeCommande()
		{
		Session s = sessionManager.getSession();

		if (s != null)
			{
			try
				{
				StringBuilder resultValue = new StringBuilder();
				int resultCode = executeCommande(s, resultValue);

				if (resultCode == 0)
					{
					fireOnCommandResultEvent(resultValue.toString());
					}
				else
					{
					fireOnCommandResultErrorEvent(resultValue.toString(), resultCode);
					}
				}
			catch (JSchException | IOException | InterruptedException e)
				{
				Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
				l.warning(e.getMessage());

				fireOnCommunicationErrorEvent(e);
				}
			}
		}

	@Override
	public void run()
		{
		executeCommande();
		}

	@Override
	public void setCommand(String command)
		{
		if (command != null)
			{
			this.command = command;
			}
		}

	@Override
	public String getCommand()
		{
		return this.command;
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private int executeCommande(Session s, StringBuilder result)
			throws IOException, JSchException, InterruptedException
		{
		ChannelExec channel;
		channel = (ChannelExec)s.openChannel("exec");
		channel.setCommand(command);
		channel.setInputStream(null);
		channel.setErrStream(System.err);

		InputStream in = channel.getInputStream();
		channel.connect();

		byte[] tmp = new byte[1024];
		while(true)
			{
			while(in.available() > 0)
				{
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					{
					break;
					}
				result.append(new String(tmp, 0, i));
				}
			if (channel.isClosed())
				{
				if (in.available() > 0)
					{
					continue;
					}
				break;
				}
			Thread.sleep(1000);
			}
		int status = channel.getExitStatus();
		channel.disconnect();
		return status;
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	private String command;
	private SessionManager sessionManager;
	private EventListenerList listenerList;
	}
