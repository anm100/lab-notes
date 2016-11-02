package models;

import java.util.Date;

import Utils.DateTime;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Appointment Entity Class .
 * 
 * @author Muhamad Igbaria
 *
 */
@DatabaseTable(tableName = "appoitments")
public class Appointment extends Entity {
	/**
	 * default constructor
	 */
	public Appointment() {
	}

	/**
	 * this constructor for create database examples
	 * @param d:
	 * 				Doctor instance
	 * @param p:
	 *          	Patient instance
	 * @param t:
	 *            	Date instance -the appointment time .
	 * @param creation_time:
	 * 				creation time
	 */
	public Appointment(Doctor d, Patient p, Date t,Date creation_time){
		this.doctor = d;
		this.patient = p;
		this.appointmentTime = t;
		this.creationTime=creation_time;
	}
	/**
	 * 
	 * @param d:
	 *            Doctor instance
	 * @param p:
	 *            Patient instance
	 * @param t:
	 *            Date instance -the appointment time .
	 */
	public Appointment(Doctor d, Patient p, Date t) {
		this.doctor = d;
		this.patient = p;
		this.appointmentTime = t;
	}

	/**
	 * 
	 * @param t:
	 *            Date instance -the appointment time .
	 */
	public Appointment(Date t) {
		this.appointmentTime = t;
	}

	/**
	 * appointment id , auto generate in database.
	 */
	@DatabaseField(generatedId = true)
	private int id;

	/**
	 * appointment doctor
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "doctor_id")
	private Doctor doctor;

	/**
	 * appointment patient
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "patient_id")
	private Patient patient;
	/**
	 * the creation time of the appointment
	 */
	@DatabaseField()
	private Date creationTime;
	/**
	 * the appointment time
	 */
	@DatabaseField()
	private Date appointmentTime;
	
	/**
	 * flag if the patient come to the appointment
	 */
	
	@DatabaseField()
	private boolean isDone;

	/**
	 * 
	 * @return appointment id
	 */

	public int getId() {
		return id;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	/**
	 * set appointment id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return appointment creation time
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * set appointment creation time
	 * 
	 * @param creationTime
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * 
	 * @return appointment time
	 */
	public Date getAppointmentTime() {
		return appointmentTime;
	}

	/**
	 * set appointment time
	 * 
	 * @param appointmentTime
	 */
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	/**
	 * 
	 * @return appointment doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * set appointment doctor.
	 * 
	 * @param doctor
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * 
	 * @return appointment patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * set appointment patient
	 * 
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * 
	 * @return the appointment finish time , finish= begin +30 minutes.
	 */

	public Date getfinishTime() {
		return DateTime.addMinutes(appointmentTime, 30);
	}

	/**
	 * two appointment equals if they overlap in times Overlap  (StartA small than 
	 * EndB) and (EndA bigger than  StartB)
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Appointment))
			return false;
		Date d1_start = ((Appointment) obj).getAppointmentTime();
		Date d1_end = ((Appointment) obj).getfinishTime();
		Date d2_start = this.appointmentTime;
		Date d2_end = this.getfinishTime();
		if (DateTime.isOverlap(d1_start, d1_end, d2_start, d2_end))
			return true;
		return false;
	}

}