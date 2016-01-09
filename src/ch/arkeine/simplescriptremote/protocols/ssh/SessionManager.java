
package ch.arkeine.simplescriptremote.protocols.ssh;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import ch.arkeine.simplescriptremote.generic_gui.JDialogPanelDisplayer;
import ch.arkeine.simplescriptremote.protocols.ssh.dedicated_gui.JPanelSessionInfo;
import ch.arkeine.simplescriptremote.protocols.ssh.dedicated_gui.UserInfoDisplayer;
import ch.arkeine.simplescriptremote.tool.SharredConstants;

public class SessionManager
	{
	
	/* ============================================ */
	// SINGLETON
	/* ============================================ */
	
	private static SessionManager instance = null;
	
	public static SessionManager getInstance()
		{
		if (instance == null)
			{
			instance = new SessionManager();
			}
		return instance;
		}
	
	private SessionManager()
		{
		
		}
	
	/* ============================================ */
	// PUBLIC
	/* ============================================ */
	
	public synchronized Session getSession()
		{
		// Ask user for session data and try to open it
		if (session == null)
			{
			JPanelSessionInfo panelSession = new JPanelSessionInfo();
			JDialogPanelDisplayer dialog = new JDialogPanelDisplayer(panelSession);
			
			if (dialog.isOkPressed())
				{
				try
					{
					JSch jsch = new JSch();
					session = jsch.getSession(panelSession.getHostName(), panelSession.getAddressIP(), Integer.valueOf(panelSession.getPort()));
					
					UserInfo ui = new UserInfoDisplayer();
					session.setUserInfo(ui);
					session.connect();
					}
				catch (JSchException e)
					{
					session = null;
					Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
					l.warning(e.getMessage());
					JOptionPane.showMessageDialog(null, "Error while creating session");
					}
				}
			}
		return session;
		}
	
	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */
	
	private Session session;
	}
