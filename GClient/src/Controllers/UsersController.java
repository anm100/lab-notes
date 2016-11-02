package Controllers;

import java.util.List;

import Client.Application;
import Utils.Request;
import models.User;

/**
 * users controller class, connects to the REST Api.
 * 
 * @author aj_pa
 *
 */
public class UsersController {
	/**
	 * return a user by an SID
	 * 
	 * @param sid
	 * @return
	 */
	public static User getUser(String sid) {
		Request r = new Request("users/getById");
		r.addParam("sid", sid);
		return (User) Application.client.sendRequest(r);
	}

	/**
	 * Checks if the user entered a correct password
	 * 
	 * @param u
	 * @param password
	 * @return
	 */
	public static boolean authinticateUser(User u, String password) {
		return u.getPass().equals(password);
	}

	/**
	 * sets the user status to online on the server.
	 * 
	 * @param u
	 * @return
	 */
	public static boolean setOnline(User u) {
		Request r = new Request("users/setOnline");
		r.addParam("user", u);
		return (boolean) Application.client.sendRequest(r);
	}

	/**
	 * locks the user account in case of 3 wrong passwords
	 * 
	 * @param u
	 * @param isLocked
	 * @return
	 */
	public static boolean setLocked(User u, boolean isLocked) {
		Request r = new Request("users/setLocked");
		u.setLocked(isLocked);
		r.addParam("user", u);
		return (boolean) Application.client.sendRequest(r);
	}

	/**
	 * returns all the locked accounts
	 * 
	 * @return
	 */
	public static List<User> getLockedUsers() {
		Request r = new Request("users/getLockedUsers");
		return (List<User>) Application.client.sendRequest(r);

	}

	/**
	 * return all the online users
	 * 
	 * @return
	 */
	public static List<User> getOnlineUsers() {
		Request r = new Request("users/getOnlineUsers");
		return (List<User>) Application.client.sendRequest(r);
	}
}
