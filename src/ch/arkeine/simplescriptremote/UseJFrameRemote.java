package ch.arkeine.simplescriptremote;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ch.arkeine.simplescriptremote.generic_gui.JFrameRemote;
import ch.arkeine.simplescriptremote.tool.SharredConstants;


public class UseJFrameRemote
	{

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		// console handler
		Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
		l.setLevel(Level.INFO);

		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		handler.setLevel(Level.INFO);
		l.addHandler(handler);

		new JFrameRemote();
		}
	}
