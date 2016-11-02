package Views;

import models.Doctor;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import com.j256.ormlite.dao.RawRowMapper;

import Database.DbHandler;
import Server.Config;
import Utils.DateTime;
import Utils.DoctorsComparator;
import Utils.Request;

public class Doctors extends View {
	/**
	 * Query to get all available doctors  .
	 * 
	 * @param request
	 *            :Request instance that has all the information the query need
	 *            - no parameters.
	 * @return all Doctors.
	 */
	public Object all(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		try {
			return db.doctors.queryForAll();
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * Query to get all available doctors specialties .
	 * 
	 * @param request
	 *            :Request instance that has all the information the query need
	 *            - no parameters.
	 * @return all specialities.
	 */
	public Object getSpecialities(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		try {
			return db.doctors.queryRaw("select DISTINCT  speciality from doctors", new RawRowMapper<String>() {
				@Override
				public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
					return resultColumns[0];
				}
			}).getResults();
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	
	/**
	 * Query to give all available doctors by given specialty .
	 * 
	 * @param request
	 *            : Request instance that has all the information the query need
	 *            - doctor specialty .
	 * @return sorted arraylist of Objects which has three attributes:
	 * @return doctor name , clinic name, last visit (when was the last time
	 *         this patient visit this doctor)
	 * @return the list sorted In descending order by last visit
	 */
	public Object bySpeciality(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		String s = (String) request.getParam("speciality");

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("speciality", s);

		ArrayList<Doctor> doctors;
		try {
			doctors = (ArrayList<Doctor>) db.doctors.queryForFieldValues(map);
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
		Appointments app_view = new Appointments();
		ArrayList<Object[]> doc_tableUI = new ArrayList<Object[]>();
		for (Doctor d : doctors) {
			doc_tableUI.add(new Object[] { d.getSid(), d.getFirstName() + " " + d.getLastName(),
					d.getClinic().getName(), app_view.lastVisit(request, d.getSid()) });
		}
		Collections.sort(doc_tableUI, new DoctorsComparator());
		return doc_tableUI;
	}

	public Object add(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Doctor d = (Doctor) request.getParam("doctor");

		try {
			db.doctors.create(d);
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return "Failed";
		}

		return "success";
	}
}
