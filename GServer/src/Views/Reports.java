package Views;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import models.Appointment;
import models.Doctor;
import models.Patient;
import models.Report;
import Database.DbHandler;
import Server.Config;
import Utils.DateTime;
import Utils.Request;
import models.Statistic;

/**
 * 
 * @author Ahdab Serhan
 *
 */
public class Reports extends View {
	/**
	 * this function return the number of patient in specific period and sets
	 * this value in statistic
	 * 
	 * @param request
	 * @return x
	 * @throws Exception
	 */
	public Object numOfPatient(Request request) throws Exception {
		DbHandler db = Config.getConfig().getHandler();
		Statistic s = new Statistic();
		QueryBuilder<Appointment, Integer> q = db.appointments.queryBuilder();

		try {
			q.where().between("appointmentTime", Utils.DateTime.getDate(2016, 10, 5, 0, 0),
					Utils.DateTime.getDate(2016, 10, 7));
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;

		}

		List<Appointment> lp = q.query();
		int x = lp.size();
		s.setNumOfPatients(x);
		return x;
	}

	/**
	 * this function get request (date) and return monthly report for specific
	 * date
	 * 
	 * @param request
	 * @return report
	 */
	public Object getMonthlyReport(Request request) {
		Date d = (Date) request.getParam("date");
		return buildMonthlyReport(d);
	}

	/**
	 * this function get date and build and return monthly report by filling
	 * it's statistics
	 * 
	 * @param d
	 * @return report
	 */
	public Report buildMonthlyReport(Date d) {
		Report r = new Report();
		r.setStatistic(new ArrayList<Statistic>());
		int i = 0;

		Statistic stat = new Statistic();
		for (Statistic s : reportByDate(d, Utils.DateTime.addMonth(d))) {
			stat.setDate(s.getDate());
			stat.setNumOfPatients(s.getNumOfPatients() + stat.getNumOfPatients());
			stat.setWaitingPeriod(s.getWaitingPeriod() + stat.getWaitingPeriod());
			i++;

			if (i % 7 == 0) {
				r.getStatistic().add(stat);
				stat = new Statistic();
			}
		}

		fillStat(r);
		return r;
	}

	public int getCanceledAppointments(Date d) {

		DbHandler db = Config.getConfig().getHandler();
		Date d1 = d;
		Date d2 = DateTime.addMonth(d);
		List<Appointment> app;
		try {
			app = db.appointments.queryBuilder().where().between("appointmentTime", d1, d2).and().eq("isDone", false)
					.query();
			return app.size();

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
		}
		return 0;
	}

	public int getLeavingClients(Date d) {

		DbHandler db = Config.getConfig().getHandler();
		Date d1 = d;
		Date d2 = DateTime.addMonth(d);
		List<Patient> app;
		try {
			app = db.patients.queryBuilder().where().between("leavingTime", d1, d2).query();
			return app.size();

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
		}
		return 0;
	}

	/**
	 * this function get a request (date) and return report that contain
	 * statistics for n months that the CEO request
	 * 
	 * @param request
	 * @return report
	 */
	public Object getNMonths(Request request) {
		Date d = null;
		try {
			d = (Date) DateTime.currentMont();
		} catch (ParseException e) {
			Config.getConfig().getLogger().exception(e);
		}
		int n = (int) request.getParam("N");
		Report r = new Report();

		r.setStatistic(new ArrayList<Statistic>());
		int j;
		Statistic stat = new Statistic();
		for (j = 0; j < n; j++) {

			Report rN = buildMonthlyReport(d);
			d = Utils.DateTime.MinusMonth(d);
			for (Statistic s : rN.getStatistic()) {

				stat.setNumOfPatients(s.getNumOfPatients() + stat.getNumOfPatients());
				stat.setWaitingPeriod(s.getWaitingPeriod() + stat.getWaitingPeriod());

			}
			stat.setDate(d);
			r.getCanceledAppointments().add(getCanceledAppointments(d));
			r.getPatientsLeft().add(getLeavingClients(d));
			r.getStatistic().add(stat);
			stat = new Statistic();
		}

		fillStat(r);
		return r;
	}

	public Object getPeriodReport(Request request) {
		Date d1 = (Date) request.getParam("date1");
		Date d2 = (Date) request.getParam("date2");
		Report r = new Report();
		r.setStatistic(new ArrayList<Statistic>());
		Statistic stat = new Statistic();

		while (d1.before(d2)) {
			Report rN = buildMonthlyReport(d1);
			for (Statistic s : rN.getStatistic()) {
				stat.setNumOfPatients(s.getNumOfPatients() + stat.getNumOfPatients());
				stat.setWaitingPeriod(s.getWaitingPeriod() + stat.getWaitingPeriod());
			}
			stat.setDate(d1);
			r.getCanceledAppointments().add(getCanceledAppointments(d1));
			r.getPatientsLeft().add(getLeavingClients(d1));
			r.getStatistic().add(stat);

			stat = new Statistic();
			d1 = Utils.DateTime.addMonth(d1);
		}

		fillStat(r);
		return r;
	}

	/**
	 * this function get request (date) and return weekly report by filling it's
	 * statistics
	 * 
	 * @param request
	 * @return report
	 */
	public Object getWeeklyReport(Request request) {
		Date d = (Date) request.getParam("date");
		return buildWeeklyReport(d);
	}

	/**
	 * this function gets date of the week, build a weekly report by filling
	 * statistic of specific day in specific week
	 * 
	 * @param d
	 * @return report
	 */
	public Report buildWeeklyReport(Date d) {
		Report r = new Report();
		ArrayList<Statistic> stat = (ArrayList<Statistic>) reportByDate(d, Utils.DateTime.addDay(d, 7));
		r.setStatistic(stat);
		r = fillStat(r);
		return r;
	}

	/**
	 * this function gets report and fills it's statistics (avg,max,min) for the
	 * parameters :number of patient and for waiting period
	 * 
	 * @param r
	 * @return report
	 */
	private Report fillStat(Report r) {
		int size = r.getStatistic().size();
		for (Statistic s : r.getStatistic()) {
			r.setpAvg(r.getpAvg() + s.getNumOfPatients());
			r.setwAvg(r.getwAvg() + s.getWaitingPeriod());
			if (s.getNumOfPatients() > r.getpMax())
				r.setpMax(s.getNumOfPatients());
			if (s.getNumOfPatients() < r.getpMin())
				r.setpMin(s.getNumOfPatients());
			if (s.getWaitingPeriod() > r.getwMax())
				r.setwMax(s.getWaitingPeriod());
			if (s.getWaitingPeriod() < r.getwMin())
				r.setwMin(s.getWaitingPeriod());

		}
		r.setpAvg(r.getpAvg() / size);
		r.setwAvg(r.getwAvg() / size);
		return fillStd(r);
	}

	/**
	 * this function get report and calculate the standard division for
	 * parameters :number of patient and waiting period
	 * 
	 * @param r
	 * @return report
	 */
	private Report fillStd(Report r) {
		double pStd = 0;
		double wStd = 0;
		int size = r.getStatistic().size();
		for (Statistic s : r.getStatistic()) {
			pStd += Math.pow(s.getNumOfPatients() - r.getpAvg(), 2);
			wStd += Math.pow(s.getWaitingPeriod() - r.getwAvg(), 2);

		}
		pStd = pStd / size;
		wStd = wStd / size;
		pStd = Math.sqrt(pStd);
		wStd = Math.sqrt(wStd);
		r.setpStd(pStd);
		r.setwStd(wStd);
		return r;
	}

	/**
	 * this function get start date and end date and return List of statistics
	 * between those two dates
	 * 
	 * @param d1
	 * @param d2
	 * @return List
	 */
	public static List<Statistic> reportByDate(Date d1, Date d2) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Statistic, Integer> q = db.statistics.queryBuilder();

		try {
			return q.orderBy("date", false).where().between("date", d1, d2).query();
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}
}
