package Views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Secretary;
import models.User;
import Database.DbHandler;
import Server.Config;
import Utils.Request;

public class Users extends View {
	/**
	 * dictionary of the connected users
	 */
	private static HashMap<String, String> connectedUsers = new HashMap<String, String>();

	public static void clientDisconnected(String ip) {
		for (String key : connectedUsers.keySet()) {
			if (connectedUsers.get(key).equalsIgnoreCase(ip)) {
				Config.getConfig().getLogger().debug("Disconnected online user : " + key + " IP:" + ip);
				connectedUsers.remove(key);
			}
		}
	}

	/**
	 * returns a User object given an SID
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public Object getById(Request request) throws SQLException {
		return getUserById((String) request.getParam("sid"));
	}

	/**
	 * returns a specific user by his sid
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	private User getUserById(String id) throws SQLException {
		DbHandler db = Config.getConfig().getHandler();
		Secretary s = db.secretaries.queryForId(id);
		if (s != null)
			return s;
		return null;
	}

	/**
	 * gets the online users and returns an ArrayList of them
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public Object getOnlineUsers(Request request) throws SQLException {
		List<User> users = new ArrayList<User>();
		for (String key : Users.connectedUsers.keySet())
			users.add(getUserById(key));
		return users;
	}

	/**
	 * returns the online users that are connected to the server
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public Object getLockedUsers(Request request) throws SQLException {
		DbHandler db = Config.getConfig().getHandler();
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put("isLocked", 1);

		List<User> users = new ArrayList<User>();
		return users;
	}

	/**
	 * Sets an account as locked
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public Object setLocked(Request request) throws SQLException {
		User u = (User) request.getParam("user");
		updateUser(u);
		Config.getConfig().getLogger()
				.debug("Account " + u.getSid() + " was " + (u.isLocked() ? "Locked" : "Unlocked"));

		return true;
	}

	/**
	 * Updates the user object in the database
	 * 
	 * @param user
	 * @throws SQLException
	 */
	private void updateUser(User user) throws SQLException {
		DbHandler db = Config.getConfig().getHandler();
		String cls = user.getClass().getTypeName();
		switch (cls) {
		case "models.Secretary":
			db.secretaries.update((Secretary) user);
			break;
		}
	}
	
	/**
	 * Sets the user online in the server.
	 * 
	 * @param request
	 * @return
	 */
	public Object setOnline(Request request) {
		DbHandler db = Config.getConfig().getHandler();

		User u = (User) request.getParam("user");
		if (connectedUsers.containsKey(u.getSid()))
			return false;

		connectedUsers.put(u.getSid(), (String) request.getParam("ip"));
		return true;
	}
}
