package Client;

import java.io.IOException;

import Utils.Logger;
import Utils.Request;
import ocsf.client.*;
import ui.main.ClientUI;

public class Client extends AbstractClient {
	public Client(String host, int port) {
		super(host, port);
	}

	public void handleMessageFromServer(Object msg) {
	}

	public Object sendRequest(Request request) {
		try {
			return this.sendToServer(request);
		} catch (ClassNotFoundException | IOException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	protected void finalize() {
		close();
	}

	public void open() {
		try {
			openConnection();
		} catch (IOException e) {
			Config.getConfig().getLogger().exception(e);
		}
	}

	public void close() {
		try {
			closeConnection();
		} catch (IOException e) {
			Config.getConfig().getLogger().exception(e);

		}
	}
}