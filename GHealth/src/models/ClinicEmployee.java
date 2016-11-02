package models;

import com.j256.ormlite.field.DatabaseField;
/**
 * public abstract class ClinicEmployee
 * extend class User
 * @author maisam marjieh 
 *
 */
public abstract class ClinicEmployee extends User{
	
	/**
	 * The clinic employee 
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "clinic_id")
	private Clinic clinic ;

	/**
	 * 
	 * @return where the employee works 
	 */
	public Clinic getClinic() {
		return clinic;
	}

	/**
	 * Sets where the employee works with given clinic instance 
	 * @param clinic
	 */
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	} 
}
 