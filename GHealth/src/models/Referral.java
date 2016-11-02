package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * public class Referral 
 *  extends class Entity  
 * @author Ahmad Mnasra 
 *
 */
@DatabaseTable(tableName = "referrals")
public class Referral extends Entity{
	
	private static final long serialVersionUID = 1L;
	/**
	 * The Referral id  generate automatic 
	 */
	@DatabaseField(generatedId = true)
	private int rid ; 
	
	/**
	 * Referral for this patient 
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "patient_id")
	private Patient patient ; 
	/**
	 * Name of doctor from HMO who write referral's patient
	 */
	@DatabaseField()
	private String doctor_name ; 
	
	/**
	 * Date of creation this referral 
	 */
	@DatabaseField()
	private Date date ; 
	
	/**
	 * Description  of Referral 
	 */
	@DatabaseField()
	private String description; 
	
	/**
	 * Specialty of referral   
	 */
	@DatabaseField()
	private String speciality; 
	
	/**
	 * Confirmation  of this Referral  
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "confirm_id")
	private Confirmation confirmation;
	
	/**
	 *The flag which tell if this  Referral has a confirmation  .
	 *(true if confirmation exist or treatment is closed ) .
	 */
	@DatabaseField()
	private boolean active;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		description = desc;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Confirmation getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(Confirmation confirmation) {
		this.confirmation = confirmation;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
}
