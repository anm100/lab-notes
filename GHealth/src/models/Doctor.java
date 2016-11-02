package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * public class Doctor
 * extends class ClinicEmployee
 * @author maisam marjieh 
 *
 */
@DatabaseTable(tableName="doctors")
public class Doctor extends ClinicEmployee{
	private static final long serialVersionUID = 1L;

	/**
	 * The doctor specialty 
	 */
	@DatabaseField()
	private String speciality;

	/**
	 * The doctor shifts
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Shift> shifts;
	
	/**
	 * The doctor appointments
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Appointment> appointments;
	
	/**
	 * 
	 * @return the  specialty of doctor
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * Sets this doctor's speciality with the given speciality
	 * @param speciality of the doctor 
	 */

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	/**
	 * 
	 * @return the doctor shifts
	 */
	public ForeignCollection<Shift> getShifts() {
		return shifts;
	}

	/**
	 * Sets this doctor's shifts from the given shifts
	 * @param shifts
	 */
	public void setShifts(ForeignCollection<Shift> shifts) {
		this.shifts = shifts;
	}

	/**
	 * 
	 * @return the appointments of doctor
	 */
	public ForeignCollection<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * Sets this doctor's appointments 
	 * @param appointments
	 */
	public void setAppointments(ForeignCollection<Appointment> appointments) {
		this.appointments = appointments;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
