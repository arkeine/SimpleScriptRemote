
package ch.arkeine.simplescriptremote.protocols.ssh.dedicated_gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.jcraft.jsch.UserInfo;

/**
 * Allow the Jsch library to ask user's credentials
 */
public class UserInfoDisplayer implements UserInfo
	{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

	@Override
	public String getPassphrase()
		{
		return passphrase;
		}

	@Override
	public String getPassword()
		{
		return password;
		}

	@Override
	public boolean promptPassword(String message)
		{
		String s = askPassword(message, "Login");
		if (s != null)
			{
			password = s;
			return true;
			}
		return false;
		}

	@Override
	public boolean promptPassphrase(String message)
		{
		String s = askPassword(message, "Login");
		if (s != null)
			{
			passphrase = s;
			return true;
			}
		return false;
		}

	@Override
	public boolean promptYesNo(String message)
		{
		Object[] options = { "yes", "no" };
		int foo = JOptionPane.showOptionDialog(null, message, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		return foo == 0;
		}

	@Override
	public void showMessage(String message)
		{
		JOptionPane.showMessageDialog(null, message);
		}

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

	private String askPassword(String message, String title)
		{
		// Create the dialog components
		JPanel panel = new JPanel();
		JLabel label = new JLabel(message);
		JPasswordField pass = new JPasswordField(20);
		panel.add(label);
		panel.add(pass);

		// Ask user
		String[] options = new String[] { "OK", "Cancel" };
		int result = JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (result == 0)
			{
			// pressing OK button
			char[] password = pass.getPassword();
			return new String(password);
			}
		else
			{
			// Cancel the dialog
			return null;
			}
		}

    /* ============================================ */
    // ATTRIBUT
    /* ============================================ */

	private String password;
	private String passphrase;
	}
