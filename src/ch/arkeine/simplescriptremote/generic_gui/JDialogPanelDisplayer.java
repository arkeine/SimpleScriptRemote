
package ch.arkeine.simplescriptremote.generic_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class JDialogPanelDisplayer extends JDialog
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JDialogPanelDisplayer(JPanel panel)
		{
		this.panel = panel;
		geometry();
		control();
		appearance();
		}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public boolean isOkPressed()
		{
		return this.okPressed;
		}

	public JPanel getPanel()
		{
		return this.panel;
		}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry()
		{
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(1, 0));
		buttonOk = new JButton("Ok");
		buttonCancel = new JButton("Cancel");
		panelButtons.add(buttonOk);
		panelButtons.add(buttonCancel);

		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		add(panel, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
		}

	private void control()
		{
		buttonOk.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					okPressed = true;
					dispose();
					}
			});

		buttonCancel.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					okPressed = false;
					dispose();
					}
			});
		}

	private void appearance()
		{
		setSize(200, 200);
		setModal(true);
		setMinimumSize(getSize());
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	private JPanel panel;
	private JButton buttonOk;
	private JButton buttonCancel;
	private boolean okPressed;
	}
