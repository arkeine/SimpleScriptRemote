package ch.arkeine.simplescriptremote;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.arkeine.simplescriptremote.generic_gui.JFrameRemote;
import ch.arkeine.simplescriptremote.tool.SharredConstants;

public class UseJFrameRemote {

	/* ============================================ */
	// PUBLIC
	/* ============================================ */

	public static void main(String[] args) {
		main();
	}

	public static void main() {
		configureLogger();
		configureLAF();

		new JFrameRemote();
	}

	/* ============================================ */
	// PRIVATE
	/* ============================================ */

	private static void configureLogger() {
		// console handler
		Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
		l.setLevel(Level.INFO);

		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		handler.setLevel(Level.INFO);
		l.addHandler(handler);

	}

	private static void configureLAF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
			Logger l = Logger.getLogger(SharredConstants.LOGGER_NAME);
			l.info(e.getMessage());
		}

	}
}
