package models;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * public class  Patient
 * @author maisam
 *
 */
@DatabaseTable(tableName = "patients")
public class Patient extends Person {

	/**
	 * The patient  weight
	 */
	@DatabaseField()
	private float weight;

	/**
	 * The patient height
	 */
	@DatabaseField()
	private float height;

	/**
	 * The patient gender
	 */
	@DatabaseField()
	private String gender;

	/**
	 *  the leavingTime of patient 
	 */
	@DatabaseField()
	private Date leavingTime;

	/**
	 * The patient appointments
	 */
	@ForeignCollectionField(eager = true)
	private ForeignCollection<Appointment> appointments;

	/**
	 * The patient referrals
	 */
	@ForeignCollectionField(eager = true)
	private ForeignCollection<Referral> referrals;

	/**
	 * The patient medical records
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "medical_id")
	private MedicalRecord medicalRecord;

	/**
	 * 
	 * @return the patient referrals
	 */
	public ForeignCollection<Referral> getReferrals() {
		return referrals;
	}

	/**
	 * Sets this patient's referrals with the given referrals
	 * @param referrals
	 */
	public void setReferrals(ForeignCollection<Referral> referrals) {
		this.referrals = referrals;
	}

	/**
	 * 
	 * @return the appointments of patient
	 */
	public ForeignCollection<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * Sets this patient's appointments from the given appointments
	 * @param appointments
	 */
	public void setAppointments(ForeignCollection<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * 
	 * @return the medical records of the patients 
	 */
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * Sets this patient's medicalRecord with the given medicalRecord
	 * @param medicalRecord
	 */
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * 
	 * @return the patient weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Sets this patient's weight from the given weight 
	 * @param weight
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * 
	 * @return the patient  height 
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Sets this patient's height with the given height
	 * @param height
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * 
	 * @return the patient gender 
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets this patient's gender from given gender 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

}
