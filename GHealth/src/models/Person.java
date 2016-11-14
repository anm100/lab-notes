package models;

import java.util.Date;



import com.j256.ormlite.field.DatabaseField;

/**
 * public class  person 
 * @author maisam marjieh 
 *
 */
public abstract class Person extends Entity{
	/**
	 * The person id 
	 */
	@DatabaseField(id = true)
	private String sid;
	
	/**
	 * The person last name 
	 */
	@DatabaseField()
	private String lastName;
	
	/**
	 * The person first name 
	 */
	@DatabaseField()
	private String firstName;
	
	
	/**
	 * The person email 
	 */
	@DatabaseField()
	private String email;
	
	/**
	 * The person phone 
	 */
	@DatabaseField()
	private String phone;
	
	/**
	 * The person address
	 */
	@DatabaseField()
	private String address;
	/**
	 * 
	 * @return person address 
	 */
	
	public String getAddress() {
		return address;
	}
	/**
	 * Sets this person address from the given address 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 
	 * @return the person id 
	 */
	public String getSid() {
		return sid;
	}
	
	/**
	 * Sets this person's  id with the given id 
	 * @param sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	/**
	 * 
	 * @return last name of the person 
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets this person's last name from the given last name 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return the first name of person 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets this person's firstName with the given firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return the person email 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Sets this person's email from the given email 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return the person phone 
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Sets this person's phone from the given phone  
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
