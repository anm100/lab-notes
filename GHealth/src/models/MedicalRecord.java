package models;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * public class MedicalRecord
 * extends class Entity
 * @author maisam marjieh
 *
 */
@DatabaseTable(tableName = "medical_records")
public class MedicalRecord extends Entity{

	/**
	 *The  medical record id 
	 */
	@DatabaseField(generatedId = true)
	private int mid;
	
	/**
	 * The medical record  of patient 
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "patient_id")
	private Patient patient;
	
	/**
	 * The medical record creation date 
	 */
	@DatabaseField()
	private Date creationDate;
	
	/**
	 * The medical record treatments 
	 */
	@ForeignCollectionField(eager=true , maxEagerLevel=2)
    private ForeignCollection<Treatment> treatments;

	/**
	 * 
	 * @return the id of this medical record instance 
	 */
	public int getMid() {
		return mid;
	}

	/**
	 * Sets the value of  the filed mid from given  id 
	 * @param mid
	 */
	public void setMid(int mid) {
		this.mid = mid;
	}

	/**
	 * 
	 * @return the patient of this medical record  
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets this medicalRecord's patient with the given patient 
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * 
	 * @return the creation date of this medical record 
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets this medicalRecord's creationDate  from given date 
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * 
	 * @return the  treatments that belongs to this medical record 
	 */
	public ForeignCollection<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * Sets the treatments that belongs to this medical record from given treatments list 
	 * @param treatments
	 */
	public void setTreatments(ForeignCollection<Treatment> treatments) {
		this.treatments = treatments;
	}
	

}
