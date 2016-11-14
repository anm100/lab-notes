package Client;

import models.User;
import ui.main.ClientUI;
import ui.main.SignInUI;

public class Application {

	public static Client client = null;
	public static User user = null;
	
	public static void connect() {
		Config cfg = Config.getConfig();
		if (client != null) {
			client.close();
			client = null;
		}
		client = new Client(cfg.getHost(), cfg.getPort());
		Config.getConfig().writeTextConfig();
		client.open();
	}

	public static void main(String[] args) {
		Config.getConfig().readTextConfig();
		connect();
		new SignInUI();
	}
}
