package Controllers;

import Client.Application;
import Utils.Request;
import models.Patient;

/**
 * Patients Controller . have all the methods that connect the client GUI to the
 * database . the methods send request to the database and receive the database
 * results.
 * 
 * @author Muhamad Igbaria , Bolous Abu Jaber , Ahmad Mnasra
 *
 */
public class PatientsController {

	/**
	 * 
	 * @param id
	 *            : patient id
	 * @return boolean value , if patient exist in the database return true ,
	 *         else false.
	 */
	public static boolean exists(String id) {
		if (getById(id) != null)
			return true;
		return false;
	}

	/**
	 * send request to database to return Patient instance by his id if exist.
	 * 
	 * @param id:
	 *            patient id
	 * @return Patient instance if found , and null else.
	 */

	public static Patient getById(String id) {

		Request r = new Request("patients/getById");
		r.addParam("sid", id);
		return (Patient) Application.client.sendRequest(r);

	}

	/**
	 * send request to database to add new patient
	 * 
	 * @param patient
	 *            : Patient instance
	 */
	public static boolean AddNewPatient(Patient patient) {
		Request r = new Request("patients/add");
		r.addParam("patient", patient);
		return (boolean)Application.client.sendRequest(r);
	}
}
