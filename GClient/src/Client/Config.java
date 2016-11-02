package Client;

import java.util.ArrayList;

import Utils.FileManager;
import Utils.Logger;

public class Config {
	public boolean isDebug = false;
	private String host = "localhost";
	private int port = 5000;
	private FileManager fileManager = new FileManager("c:/GHealth");
	private Logger logger = new Logger(isDebug);

	private static Config instance = new Config();

	private Config() {
	}

	public static Config getConfig() {
		return instance;
	}

	public void readTextConfig() {
		try {
			ArrayList<String> lines = fileManager.readFile("config.txt");
			String[] ip = lines.get(0).split(":");
			host = ip[0];
			port = Integer.parseInt(ip[1]);
		} catch (Exception e) {
			host = "localhost";
			port = 5000;
			Config.getConfig().getLogger().exception(e);
		}

	}

	public void writeTextConfig() {
		try {
			String ip = host + ":" + port;
			fileManager.writeFile("config.txt", ip);
		} catch (Exception e) {
			Config.getConfig().getLogger().exception(e);
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

}
