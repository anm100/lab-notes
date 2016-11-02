package Views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Dispatcher;
import models.Doctor;
import models.Labratorian;
import models.Manager;
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
		Doctor d = db.doctors.queryForId(id);
		if (d != null)
			return d;
		Labratorian l = db.labratorians.queryForId(id);
		if (l != null)
			return l;
		Secretary s = db.secretaries.queryForId(id);
		if (s != null)
			return s;
		Dispatcher dis = db.dispatchers.queryForId(id);
		if (dis != null)
			return dis;
		Manager man = db.managers.queryForId(id);
		if (man != null)
			return man;
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
		List<Doctor> doctors = db.doctors.queryForFieldValues(values);
		List<Labratorian> labratorians = db.labratorians.queryForFieldValues(values);
		users.addAll(doctors);
		users.addAll(labratorians);
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
		case "models.Doctor":
			db.doctors.update((Doctor) user);
			break;
		case "models.Labratorian":
			db.labratorians.update((Labratorian) user);
			break;
		case "models.Dispatcher":
			db.dispatchers.update((Dispatcher) user);
			break;
		case "models.Secretary":
			db.secretaries.update((Secretary) user);
			break;
		case "models.Manager":
			db.managers.update((Manager) user);
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
