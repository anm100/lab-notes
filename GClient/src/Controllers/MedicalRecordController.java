package Controllers;

import java.text.ParseException;

import javax.swing.ImageIcon;

import Client.Application;
import Utils.DateTime;
import Utils.Request;
import models.Doctor;
import models.Examination;
import models.MedicalRecord;
import models.Patient;
import models.Treatment;
import models.Visit;

/**
 * The medicalRecordController contains all the methods necessary to create new
 * visit , treatment , and Examination and to save them in database,this class
 * connect the GUI to server and the database
 * 
 * @author maisam marjieh
 *
 */
public class MedicalRecordController {

	/**
	 * send Request to server to save a new treatment in database
	 * 
	 * @param t
	 *            treatment will be added to database
	 * @return the treatment that added to database
	 */

	public static Object saveTreatment(Treatment t) {
		Request r = new Request("treatments/add");
		r.addParam("treatment", t);
		r.addParam("medical_id", t.getMedicalRecord().getMid());
		return Application.client.sendRequest(r);

	}

	/**
	 * send Request to server to save a new visit in dataBase
	 * 
	 * @param v
	 *            visit will be saved in dataBase
	 * @return visit instance from dataBase
	 */

	public static Object saveVisit(Visit v) {

		Request r = new Request("visits/add");
		r.addParam("visit", v);
		r.addParam("treatment_id", v.getTreatment().getTid());
		return Application.client.sendRequest(r);

	}

	/**
	 * sending Request to server to update treatment in database, creation and
	 * send report to HMO doctor
	 * 
	 * @param t
	 *            Treatment will be updated
	 *
	 */

	public static void sendTreatmentReport(Treatment t) {
		Request r = new Request("treatments/sendReport");
		r.addParam("treatment", t);
		Application.client.sendRequest(r);

	}

	/**
	 * send Request to HMO to ask about patient information
	 * 
	 * @param p
	 *            The patient is requested the information
	 */

	public static void sendRequestToHMO(Patient p) {
		Request r = new Request("patients/sendRequest");
		r.addParam("patient", p);
		Application.client.sendRequest(r);
	}

	/**
	 * send request to server to get the HMO information about patient
	 * 
	 * @param file
	 * @return the HMO information (image of information)
	 */

	public static ImageIcon getHmoInformation(String file) {
		Request r = new Request("patients/getHmoInformation");
		r.addParam("info", file);
		return (ImageIcon) Application.client.sendRequest(r);
	}

	/**
	 * Sending a request for a list of clinics which has a laboratories
	 * 
	 * @return list of clinics
	 */

	public static Object getAllLabratories() {
		Request r = new Request("clinics/getClinics");
		return Application.client.sendRequest(r);

	}

}
