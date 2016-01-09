
package ch.arkeine.simplescriptremote.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWrapper extends Properties
	{

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	private static PropertiesWrapper instance = null;

	public static PropertiesWrapper getInstance()
		{
		if (instance == null)
			{
			instance = new PropertiesWrapper("conf.txt");
			}
		return instance;
		}

	private PropertiesWrapper(String fileName)
		{
		this.fileName = fileName;
		this.prop = new Properties();
		}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public String getSessionName()
		{
		return prop.getProperty("SESSION_NAME", "arkeine");
		}

	public String getSessionAddressIP()
		{
		return prop.getProperty("SESSION_IP", "localhost");
		}

	public String getSessionPort()
		{
		return prop.getProperty("SESSION_PORT", "22");
		}

	public String getCommandStart()
		{
		return prop.getProperty("COMMAND_START", "/crypted/home/arkeine/start.sh");
		}

	public String getCommandStop()
		{
		return prop.getProperty("COMMAND_START", "/crypted/home/arkeine/stop.sh");
		}

	public void loadProperties() throws IOException
		{
		try (FileInputStream input = new FileInputStream(fileName))
			{
			prop.load(input);
			}
		}

	public void saveProperties() throws IOException
		{
		try (FileOutputStream output = new FileOutputStream(fileName))
			{
			prop.store(output, null);
			}
		}

	/* ============================================ */
	// ATTRIBUT
	/* ============================================ */

	private Properties prop;
	private String fileName;
	}
