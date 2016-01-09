
package ch.arkeine.simplescriptremote.protocols.ssh.dedicated_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JPanelSessionInfo extends JPanel
	{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

	public JPanelSessionInfo()
		{
		geometry();
		control();
		appearance();
		}

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

	@Override
	public void setName(String s)
		{
		this.textEditName.setText(s);
		}


	public void setIP(String s)
		{
		this.textEditAddressIP.setText(s);
		}


	public void setPort(String s)
		{
		this.textEditPort.setText(s);
		}

	public String getHostName()
		{
		return this.textEditName.getText();
		}

	public String getAddressIP()
		{
		return this.textEditAddressIP.getText();
		}

	public String getPort()
		{
		//TODO mettre en place un controle de saisie pour le port et l'IP
		return this.textEditPort.getText();
		}

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

	private void geometry()
		{
		setLayout(new BorderLayout());

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblAddressIP = new JLabel("Address IP");
		panel_1.add(lblAddressIP);
		textEditAddressIP = new JTextField();
		panel_1.add(textEditAddressIP);

		JLabel lblUsersName = new JLabel("User's name");
		panel_1.add(lblUsersName);
		textEditName = new JTextField();
		panel_1.add(textEditName);

		JLabel lblPort = new JLabel("Port");
		panel_1.add(lblPort);
		textEditPort = new JTextField();
		panel_1.add(textEditPort);
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

	private JTextField textEditName;
	private JTextField textEditAddressIP;
	private JTextField textEditPort;
	}
