package models;

import com.j256.ormlite.field.DatabaseField;
/**
 * public abstract class user
 * @author maisam marjieh 
 *
 */
public abstract class User extends Person {
	/**
	 * user password 
	 */
	@DatabaseField()
	private String pass;
	/**
	 * true if the user is locked
	 */
	@DatabaseField()
	private boolean isLocked;
	
	
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	} 
	
}
