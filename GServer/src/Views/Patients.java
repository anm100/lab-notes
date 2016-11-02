package Views;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;

import models.Appointment;
import models.Doctor;
import models.MedicalRecord;
import models.Patient;
import Database.DbHandler;
import Server.Config;
import Utils.Request;

/**
 * 
 * Database view for Patients , have all the Patients Queries
 * 
 * @author maisam marjieh
 *
 */
public class Patients extends View {

	/**
	 * Query to get patient from database by his id.
	 * 
	 * @param request
	 *            : "patients/getById" params: "sid" (patient id)
	 * @return Patient instance
	 */
	public Object getById(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		try {
			Patient p = db.patients
					.queryForId((String) request.getParam("sid"));
			return p;
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * get HMO information
	 * 
	 * @param request
	 *            : "patients/getHmoInformation" params: (file)
	 * @return image of HMO information
	 */
	public Object getHmoInformation(Request request) {
		return new ImageIcon(Config.getConfig().getHomeDirectory()
				+ "/information/" + request.getParam("info"));
	}

	/**
	 * Query to add new patient and creation medical record to him and save it
	 * in database
	 * 
	 * @param request
	 *            "patients/add " params: (patient)
	 * @return success message if the patient was added successfully else return
	 *         null
	 * 
	 */

	public Object add(Request request) throws SQLException {
		DbHandler db = Config.getConfig().getHandler();
		Patient patient = (Patient) request.getParam("patient");
		MedicalRecord md = new MedicalRecord();
		if (db.patients.idExists(patient.getSid()))
			return false;
		else {

			md.setPatient(patient);
			try {
				md.setCreationDate(Utils.DateTime.currentDate());
			} catch (ParseException ex) {
				Config.getConfig().getLogger().exception(ex);
			}
			try {
				db.records.createIfNotExists(md);
			} catch (SQLException e) {
				Config.getConfig().getLogger().exception(e);
			}

			try {
				patient.setMedicalRecord(md);
				db.patients.createIfNotExists(patient);
				return true;
			} catch (SQLException e) {
				Config.getConfig().getLogger().exception(e);
				return false;
			}
		}
	}

	/**
	 * sending Request to HMO of the patient
	 * 
	 * @param request
	 * @return success message
	 */
	public Object sendRequest(Request request) {
		Patient patient = (Patient) request.getParam("patient");
		System.out.println("----------------------------------------");
		System.out.println("Request Information Patient : "
				+ patient.getFirstName() + " " + patient.getLastName()
				+ " sended to HMO :");
		System.out.println("----------------------------------------");
		return "success";
	}
}
