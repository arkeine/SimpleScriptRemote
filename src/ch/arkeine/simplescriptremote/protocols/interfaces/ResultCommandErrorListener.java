
package ch.arkeine.simplescriptremote.protocols.interfaces;

import java.util.EventListener;


public interface ResultCommandErrorListener  extends EventListener
	{
	void onCommunicationError(Exception e);

	void onCommandError(String resultValue, int resultCode);
	}

