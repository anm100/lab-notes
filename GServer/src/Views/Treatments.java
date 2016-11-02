package Views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.QueryBuilder;

import Database.DbHandler;
import Server.Config;
import Utils.Request;
import models.Doctor;
import models.Secretary;
import models.Treatment;

/**
 * Database view for treatment , have all the treatment Queries.
 * 
 * @author maisam marjieh
 *
 */

public class Treatments extends View {

	/**
	 * Query to add a new treatment to database
	 * 
	 * @param request
	 *             : "treatments/add" ,HashMap params:
	 *            (treatment).
	 * @return treatment instance from database
	 */
	public Object add(Request request) {
		DbHandler db = Config.getConfig().getHandler();

		try {
			db.treatments.create((Treatment) request.getParam("treatment"));
			return getLastTreatment(request);
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * call updateTreatment method to update the treatment (add result and end date )
	 * send a report about treatment to the HMO of the  patient 
	 * @param request : "treatments/sendReport" ,HashMap params:
	 *            (treatment).
	 * @return success message 
	 */
	public Object sendReport(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Treatment tr = (Treatment) request.getParam("treatment");
		updateTreatment(tr);
		System.out.printf("\n\nsending  report about treatment \n");
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println("treatment Deails : \n\n" + "Treatment id : "
				+ tr.getTid() + "\nTreatment name : " + tr.gettType());
		System.out.println("The treatment doctor : "
				+ tr.getDoctor().getFirstName() + " "
				+ tr.getDoctor().getLastName());
		System.out.println("Patient : "
				+ tr.getMedicalRecord().getPatient().getFirstName() + " "
				+ tr.getMedicalRecord().getPatient().getLastName());
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println();
		return "success";
	}

	/**
	 * Query to update treatment in database
	 * 
	 * @param treatment instance will be updated 
	 * @return success message if the treatment was updated successfully
	 */
	public Object updateTreatment(Treatment treatment ) {
		DbHandler db = Config.getConfig().getHandler();
		try {
			db.treatments.update(treatment);
			return "success";
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}

	}

	/**
	 * Query to get the last treatment which added to specific medical record
	 * 
	 * @param request
	 *           "treatments/getLastTreatment" ,HashMap params:  (medical_id , ).
	 * @return the last treatment in the specific medical record
	 */
	public Object getLastTreatment(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Treatment, Integer> q = db.treatments.queryBuilder();
		List<Treatment> treatment;

		try {
			treatment = q
					.orderBy("start", false)
					.limit(1)
					.where()
					.eq("medical_id",
							((Integer) request.getParam("medical_id"))).query();
			if (treatment.size() == 0)
				return null;
			else
				return treatment.get(0);

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}

	}

	/**
	 * Query to get all treatment that closed and has not invoice at moment get
	 * treatments which performed by doctors that works in the same clinic where
	 * specific secretary works
	 * 
	 * @param request :
	 *            "treatments/getTreatments" ,HashMap params:  (Secretary , ).
	 * @return list of treatments
	 */
	public Object getTreatments(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Doctor, String> d = db.doctors.queryBuilder();
		QueryBuilder<Treatment, Integer> p = db.treatments.queryBuilder();
		List<Doctor> doctor;
		Secretary secr = (Secretary) request.getParam("Secretary");
		List<Treatment> tretmentList;
		List<Treatment> allTreatment = new ArrayList<Treatment>();

		try {
			doctor = d.where().eq("clinic_id", secr.getClinic().getCid())
					.query();
			for (Doctor doc : doctor) {
				tretmentList = p.where().eq("endFlag", true).and()
						.eq("hasInvoice", false).and()
						.eq("doctor_id", doc.getSid()).query();
				allTreatment.addAll(tretmentList);
			}
			return allTreatment;
		}

		catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}
}
