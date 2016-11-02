package Controllers;

import models.Clinic;
import models.Examination;
import models.Labratorian;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Client.Application;
import Utils.Request;

/**
 * public class ExaminationController have all the methods that connect the
 * client GUI to the database.and manage all operations for examinations
 * 
 * @author maisam marjieh
 *
 */
public class ExaminationController {

	/**
	 * get specific examination
	 * 
	 * @param id
	 *            of specific examination
	 * @return examination instance
	 */
	public static Examination getById(int id) {

		Request r = new Request("examinations/getById");
		r.addParam("sid", id);
		return (Examination) Application.client.sendRequest(r);
	}

	/**
	 * send Request to server to get the requested clinic
	 * 
	 * @param cid
	 *            - the id of the requested clinic
	 * @return the requested clinic
	 */
	public static Clinic getClinic(int cid) {
		Request r = new Request("clinics/getById");
		r.addParam("cid", cid);
		return (Clinic) Application.client.sendRequest(r);
	}

	/**
	 * send request to server to update examination
	 * 
	 * @param ex
	 *            -Examination instance that will be updated
	 * @param ic
	 *            - ImageIcon that will be added to the examination instance ex
	 */
	public static void saveExamination(Examination ex, ImageIcon ic) {
		Request r = new Request("examinations/update");
		r.addParam("examination", ex);
		if (ic != null)
			r.addParam("image", ic);
		Application.client.sendRequest(r);

	}

	public static ArrayList<Examination> getExaminations(Labratorian lab) {
		Request r = new Request("examinations/getExaminations");
		r.addParam("labratorian", lab);

		return (ArrayList<Examination>) Application.client.sendRequest(r);

	}

	/**
	 * Sends the request and waits for an image for the examination
	 * 
	 * @param ex
	 *            - examination instance
	 * @return ImageIcon - image containing the file
	 */
	public static ImageIcon getImage(Examination ex) {
		if (ex.getFile() == null)
			return null;
		Request r = new Request("examinations/getExaminationImage");
		r.addParam("examination", ex);
		ImageIcon image = (ImageIcon) Application.client.sendRequest(r);
		return image;
	}

	/**
	 * send Request to server to save a new ExaminationReferral in dataBase
	 * 
	 * @param e
	 *            -the new Examination that will be added to database
	 */
	public static void saveExaminationReferral(Examination e) {

		Request r = new Request("examinations/add");
		r.addParam("exam", e);
		Application.client.sendRequest(r);
	}

}
