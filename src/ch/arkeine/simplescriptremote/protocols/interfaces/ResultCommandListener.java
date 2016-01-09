
package ch.arkeine.simplescriptremote.protocols.interfaces;

import java.util.EventListener;


public interface ResultCommandListener extends EventListener
	{
	void onCommandResult(String result);
	}

