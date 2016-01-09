
package ch.arkeine.simplescriptremote.generic_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JPanelLed extends JPanel
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public JPanelLed()
		{
		this.textInfo = "";
		this.colorActivated = Color.GREEN;
		this.colorDesactivated = Color.RED;
		this.colorDisable = Color.GRAY;

		geometry();
		control();
		appearance();
		}

	/* ============================================ */
	// OVERRIDE
	/* ============================================ */

	@Override
	protected void paintComponent(Graphics arg0)
		{
		// draw the background
		if (isActivated)
			{
			setBackground(colorActivated);
			}
		else
			{
			setBackground(colorDesactivated);
			}
		if (!isEnabled())
			{
			setBackground(colorDisable);
			}

		super.paintComponent(arg0);

		// draw the text
		arg0.setFont(f);
		fontMetrics = arg0.getFontMetrics(f);

		int centreX = (getWidth() - fontMetrics.stringWidth(textInfo)) / 2;
		int centreY = (getHeight() + fontMetrics.getHeight()) / 2;
		arg0.drawString(textInfo, centreX, centreY);
		}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public String getTextInfo()
		{
		return this.textInfo;
		}

	public Color getColorActivated()
		{
		return this.colorActivated;
		}

	public Color getColorDesactivated()
		{
		return this.colorDesactivated;
		}

	public Color getColorDisable()
		{
		return this.colorDisable;
		}

	public void setTextInfo(String textInfo)
		{
		this.textInfo = textInfo;
		}

	public void setColorActivated(Color colorActivated)
		{
		this.colorActivated = colorActivated;
		}

	public void setColorDesactivated(Color colorDesactivated)
		{
		this.colorDesactivated = colorDesactivated;
		}

	public void setColorDisable(Color colorDisable)
		{
		this.colorDisable = colorDisable;
		}

	public void setActivationState(boolean activated)
	{
		this.isActivated = activated;
	}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private void geometry()
		{
		setLayout(null);
		f = new Font("Impact", Font.BOLD, 20);
		}

	private void control()
		{
		// nothing
		}

	private void appearance()
		{
		setSize(50, 50);
		setMinimumSize(getSize());
		setPreferredSize(getSize());
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, false));
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	// input-output
	private boolean isActivated;
	private String textInfo;
	private Color colorActivated;
	private Color colorDesactivated;
	private Color colorDisable;

	// tools
	private FontMetrics fontMetrics;
	private Font f;

	}
