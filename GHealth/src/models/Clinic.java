package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * public class Clinic 
 * extends class Entity 
 * @author maisam marjieh 
 *
 */
@DatabaseTable(tableName = "clinics")
public class Clinic extends Entity{
	
	/**
	 * The clinic id 
	 */
	@DatabaseField(generatedId = true)
	private int cid ; 
	
	/**
	 * The clinic name 
	 */
	@DatabaseField()
	private String name;
	
	/**
	 * The clinic address
	 */
	@DatabaseField()
	private String address;
	
	/**
	 * The clinic email
	 */
	@DatabaseField()
	private String email ;
	
	/**
	 * the clinic phone 
	 */
	@DatabaseField()
	private String phone;

	/**
	 * true if the clinic has Labratory 
	 */
	@DatabaseField()
	private boolean hasLabratory;
	
	/**
	 * The clinic labratorians 
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Labratorian> labratorians;
	
	/**
	 * The clinic doctors
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Doctor> doctors; 
	
	
	/**
	 * 
	 * @return true if the clinic has labratory .false else
	 */
	public boolean isHasLabratory() {
		return hasLabratory;
	}


	/**
	 * Sets this clinic's has labratory with the given value
	 * @param hasLabratory
	 */
	public void setHasLabratory(boolean hasLabratory) {
		this.hasLabratory = hasLabratory;
	}


	/**
	 * 
	 * @return the id of this clinic 
	 */
	public int getCid() {
		return cid;
	}


	/**
	 * Sets this clinic's id from the given id value 
	 * @param cid
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * 
	 * @return the name of this clinic instance 
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets this clinic's name from the given name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 
	 * @return the address of this clinic instance 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets this clinic's address with the given address 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * 
	 * @return the email of this clinic instance 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets this clinic's email with the  given email 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * 
	 * @return the phone number of this clinic 
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Sets this clinic's phone with the given the phone number 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * 
	 * @return  labratorian lists  of  this clinic 
	 */
	public ForeignCollection<Labratorian> getLabratorians() {
		return labratorians;
	}


	/**
	 * Sets this clnic's labratorian with the given labratorians list 
	 * @param labratorians
	 */
	public void setLabratorians(ForeignCollection<Labratorian> labratorians) {
		this.labratorians = labratorians;
	}


	/**
	 * 
	 * @return doctors list of this clinic 
	 */
	public ForeignCollection<Doctor> getDoctors() {
		return doctors;
	}


	/**
	 * Sets this clinic's doctors from the given doctor list 
	 * @param doctors
	 */
	public void setDoctors(ForeignCollection<Doctor> doctors) {
		this.doctors = doctors;
	}


	/**
	 * a string representation of this clinic .
	 */
	public String toString(){
		return "" + this.getName();
	}
}
