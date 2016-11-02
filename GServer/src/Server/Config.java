package Server;

import Database.DbHandler;
import Utils.FileManager;
import Utils.Logger;
/**
 * Class Config, the configuration of the server.
 * @author aj_pa
 *
 */
public class Config {
	private boolean isDebug = true;
	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	private int port = 5000;
	private String dbUrl = "jdbc:mysql://localhost/test";
	private String dbUser = "root";
	private String dbPassword = "2222";
	private Logger logger = new Logger(isDebug);
	private String homeDirectory = "c:/GHealth";
	private FileManager fileManager = new FileManager(homeDirectory);
	private DbHandler handler;


	private static Config instance = new Config();

	private Config() {
	}

	public static Config getConfig() {
		return instance;
	}

	public static Config fromArgs(String[] args) {
		Config cfg = Config.getConfig();

		if (args.length == 0)
			return cfg;

		int port = Integer.parseInt(args[0]);

		if (args.length > 0 && port != 0)
			cfg.setPort(port);
		if (args.length > 1)
			cfg.setDbUrl(args[1]);
		if (args.length > 2)
			cfg.setDbUser(args[2]);
		if (args.length > 3)
			cfg.setDbPassword(args[3]);

		cfg.printConfig();
		
		return cfg;
	}

	public void printConfig() {

		logger.debug("[CONFIGURATION]");
		logger.debug("\t|URL : " + dbUrl);
		logger.debug("\t|PORT : " + port);
		logger.debug("\t|USER : " + dbUser);
		logger.debug("\t|PASSWORD : " + dbPassword);
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
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

	public DbHandler getHandler() {
		return handler;
	}

	public void setHandler(DbHandler handler) {
		this.handler = handler;
	}

	public String getHomeDirectory() {
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

}
