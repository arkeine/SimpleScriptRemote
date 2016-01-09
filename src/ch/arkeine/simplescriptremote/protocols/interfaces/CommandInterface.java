
package ch.arkeine.simplescriptremote.protocols.interfaces;



public interface CommandInterface
	{
	void setCommand(String command);

	String getCommand();

	void executeCommande();

	void addCommandResultListener(ResultCommandListener listener);

	void removeCommandResultListener(ResultCommandListener listener);

	void addCommandResultErrorListener(ResultCommandErrorListener listener);

	void removeCommandResulErrortListener(ResultCommandErrorListener listener);
	}

