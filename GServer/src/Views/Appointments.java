package Views;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import models.Appointment;
import models.Doctor;
import models.Patient;
import models.Shift;
import Database.DbHandler;
import Server.Config;
import Utils.DateTime;
import Utils.Request;

/**
 * Database view for appointments , have all the Appointments Queries.
 * 
 * @author Muhamad Igbaria
 *
 */
public class Appointments extends View {

	/**
	 * Query to get specific patient all future appointments.
	 * 
	 * @param request
	 *            : "appointments/getPatientAppointments" ,HashMap params:
	 *            ("patient_id","curr_date").
	 * 
	 * @return Array list of all future patient's appointment.
	 * 
	 */
	public Object getPatientAppointments(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();
		List<Appointment> app;
		try {
			app = q.orderBy("appointmentTime", true).where().eq("patient_id", request.getParam("patient_id")).and()
					.ge("appointmentTime", request.getParam("curr_date")).query();

			return app;
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * Query to delete an appointment.
	 * 
	 * @param request
	 *            : "appointments/delete" ,HashMap params: ("appointment" 
	 *            Appointment instance).
	 * 
	 * @return boolean value , id delete success return true , else return
	 *         false.
	 * 
	 * @throws SQLException
	 */

	public Object setAppointmentDone(Request request) throws Exception {
		Date d1 = DateTime.currentDay();
		Date d2 = DateTime.addDay(d1, 1);
		DbHandler db = Config.getConfig().getHandler();
		List<Appointment> app = db.appointments.queryBuilder().orderBy("appointmentTime", false).where()
				.between("appointmentTime", d1, d2).and().eq("patient_id", request.getParam("patient_id")).and()
				.eq("doctor_id", request.getParam("doctor_id")).query();

		if (app.size() <= 0)
			return "failed";
		Appointment p = app.get(0);
		p.setDone(true);
		db.appointments.update(p);
		return "success";
	}

	/**
	 * Query to delete an appointment.
	 * 
	 * @param request
	 *            : "appointments/delete" ,HashMap params: ("appointment" 
	 *            Appointment instance).
	 * 
	 * @return boolean value , id delete success return true , else return
	 *         false.
	 * 
	 */
	public Object delete(Request request) {

		try {
			Config.getConfig().getHandler().appointments.delete((Appointment) request.getParam("appointment"));
			return true;

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return false;

		}

	}

	/**
	 * Query to add new appointment to database
	 * 
	 * @param request
	 *            : "appointments/add" ,HashMap params: ("doctor_id"
	 *            ,"patient_id").
	 * 
	 * @return true id adding success , and false if occur any error like the
	 *         appointment exist
	 * 
	 */
	public Object add(Request request) {
		DbHandler db = Config.getConfig().getHandler();

		Doctor doctor;
		Patient patient;
		try {
			doctor = db.doctors.queryForId((String) request.getParam("doctor_id"));
			patient = db.patients.queryForId((String) request.getParam("patient_id"));

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return false;
		}

		if (!isExist(request.getParam("doctor_id"), request.getParam("patient_id"), request.getParam("app_time"))) {
			try {
				Appointment app = new Appointment(doctor, patient, (Date) request.getParam("app_time"));
				try {
					app.setCreationTime(DateTime.currentDate());
				} catch (ParseException e) {
					Config.getConfig().getLogger().exception(e);
				}
				db.appointments.create(app);
				return true;
			} catch (SQLException e) {
				Config.getConfig().getLogger().exception(e);
				return false;
			}
		}
		return false;

	}

	/**
	 * check if given appointment exist in database
	 * 
	 * @param doctor_id
	 * @param patient_id
	 * @param app_time
	 *            : appointment time
	 * @return true if the appointment is exist , return false else .
	 * 
	 */
	public boolean isExist(Object doctor_id, Object patient_id, Object app_time) {
		DbHandler db = Config.getConfig().getHandler();
		if(db==null) return false;
		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();
		ArrayList<Appointment> app;

		try {
			//check by doctor id ,patient id and date
			app = (ArrayList<Appointment>) q.where().eq("doctor_id", (String) doctor_id).and()
					.eq("patient_id", (String) patient_id).and().eq("appointmentTime", (Date) app_time).query();
			if (app != null && app.size() > 0)
				return true;
			
			//check by doctor id and date
			app=(ArrayList<Appointment>) q.where().eq("doctor_id", (String) doctor_id).and()
					.eq("appointmentTime", (Date) app_time).query();
			if (app != null && app.size() > 0)
				return true;
			
			//check by patient id and date
			app = (ArrayList<Appointment>) q.where()
					.eq("patient_id", (String) patient_id).and().eq("appointmentTime", (Date) app_time).query();
			if (app != null && app.size() > 0)
				return true;
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return false;
		}
		
		return false;
	}

	/**
	 * Query to get the date if the last visit of patient to specific doctor
	 * 
	 * @param request
	 *            : "appointments/lastVisit" ,HashMap params: ("patient_id").
	 * 
	 * @param doctor_id
	 *            : the doctor id that need to check the patient last visit for
	 *            him
	 * @return String date of the last visit if exist such last visit , return
	 *         empty string if no last visit , return null if exception occured.
	 */
	public Object lastVisit(Request request, String doctor_id) {
		DbHandler db = Config.getConfig().getHandler();

		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();
		List<Appointment> app;
		Date curr_date = null;

		try {
			curr_date = DateTime.currentDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			Config.getConfig().getLogger().exception(e1);
		}

		try {
			app = q.orderBy("appointmentTime", false).limit(1).where().eq("doctor_id", doctor_id).and()
					.eq("patient_id", request.getParam("patient_id")).and().le("appointmentTime", curr_date).query();

			if (app.size() == 0)
				return "";
			else
				return DateTime.getDateString(app.get(0).getAppointmentTime());
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * Query to get all available times to appointment that not overlap with
	 * chooses doctor and patient appointments times
	 * 
	 * @param request
	 *            : "appointments/availableTimes" ,HashMap params:
	 *            ("doctor_id","patient_id","curr_time").
	 * 
	 * @return Appointments Array list all available times for set appointment
	 */
	public Object availableTimes(Request request) {
		ArrayList<Appointment> unAvailable = (ArrayList<Appointment>) unAvailableApps(request.getParam("doctor_id"),
				request.getParam("patient_id"), request.getParam("curr_time"));

		ArrayList<Shift> doc_shifts = (ArrayList<Shift>) getDoctorShifts(request.getParam("doctor_id"),
				request.getParam("curr_time"));

		ArrayList<Appointment> allOptions = new ArrayList<Appointment>();

		for (Shift shift : doc_shifts) {
			Date time = shift.getStartDate();
			while (time.before(shift.getEndDate())) {
				allOptions.add(new Appointment(time));
				time = DateTime.addMinutes(time, 30);
			}
		}
		allOptions.removeAll(unAvailable);

		return allOptions;
	}

	/**
	 * Query to get all doctor's shifts
	 * 
	 * @param doctor_id
	 * @param curr_time
	 * @return Shift array list of all future all doctor's shifts.
	 */
	private List<Shift> getDoctorShifts(Object doctor_id, Object curr_time) {

		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Shift, String> q = db.shifts.queryBuilder();

		try {
			return q.orderBy("startDate", true).where().eq("doctor_id", (String) doctor_id).and()
					.ge("startDate", (Date) curr_time).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 * Query for get the unavailable times of doctor and patient to set
	 * appointment , get the patient and doctor appointments .
	 * 
	 * @param doctor_id
	 * @param patient_id
	 * @param curr_time
	 * @return Appointment array list of unavailable times of patient and
	 *         doctor.
	 */
	private List<Appointment> unAvailableApps(Object doctor_id, Object patient_id, Object curr_time) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();
		Where<Appointment, Integer> where = q.orderBy("appointmentTime", true).where();
		List<Appointment> unavailableApps = null;

		try {
			where.ge("appointmentTime", (Date) curr_time);
			where.eq("doctor_id", (String) doctor_id).or().eq("patient_id", (String) patient_id);
			unavailableApps = where.and(where, where).query();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return unavailableApps;
	}

	/**
	 * find all appointments of tomorrow and send email reminder to the
	 * patients.
	 */
	public void sendEmails() {
		DbHandler db = Config.getConfig().getHandler();

		Date tmr_start = DateTime.getTomorrowDate();// tomorrow begin of day
		tmr_start.setHours(0);
		tmr_start.setMinutes(0);
		Date tmr_end = DateTime.getTomorrowDate();// tomorrow end of day
		tmr_end.setHours(23);
		tmr_end.setMinutes(59);
		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();
		ArrayList<Appointment> res = null;

		try {
			res = (ArrayList<Appointment>) q.where().ge("appointmentTime", tmr_start).and()
					.le("appointmentTime", tmr_end).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Patient patient;
		System.out.println("----------------------------------------");
		System.out.println("Send messages to patients:");
		System.out.println();
		if (res == null || res.size() == 0)
			System.out.println("No messages to send");
		else {
			for (Appointment app : res) {
				patient = app.getPatient();
				System.out.println("Message sent to :" + patient.getFirstName() + " " + patient.getLastName()
						+ " at email address: " + patient.getEmail());
				System.out.println("Message: Tomorrow you have appointment at " + app.getAppointmentTime()
						+ " in clinic : " + app.getDoctor().getClinic().getName());
				System.out.println();
			}
		}

		System.out.println("----------------------------------------");

	}

}
