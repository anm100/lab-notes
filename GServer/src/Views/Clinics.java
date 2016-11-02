package Views;

import java.util.List;
import java.sql.SQLException;

import models.Clinic;

import com.j256.ormlite.stmt.QueryBuilder;

import Utils.Request;
import Database.DbHandler;
import Server.Config;

/**
 * Database view for Clinics , have all the Clinics Queries.
 * 
 * @author maisam marjieh
 *
 */
public class Clinics extends View {

	/**
	 * Query to get all clinics that have labs .
	 * 
	 * @param request   : "clinics/getClinics" ,HashMap params:
	 *            ().
	 * @return list of clinics 
	 */
	public Object getClinics(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		QueryBuilder<Clinic, Integer> d = db.clinics.queryBuilder();
		List<Clinic> clinic;
		try {

			clinic = d.where().eq("hasLabratory", true).query();
			return clinic;
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return "fail";
		}
	}

	/**
	 * get specific clinic from dataBase and send to client 
	 * 
	 * @param request
	 *           : "clinics/getById" ,HashMap params:
	 *            (cid,).
	 * @return clinic
	 */
	public Object getById(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		try {
			return db.clinics.queryForId((int) request.getParam("cid"));
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

}
