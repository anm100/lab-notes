package Views;

import java.sql.SQLException;

import java.text.ParseException;

import models.Confirmation;
import models.Doctor;
import models.Referral;
import Database.DbHandler;
import Server.Config;
import Utils.Request;
/**
 * Database view for referral .
 * 
 * @author Ahmad Mnasra
 *
 */
public class Referrals extends View {
	/**
	 * Query to add a new referral and confirmation to database
	 * 
	 * @param request
	 *             : "Referrals/add" ,HashMap params:
	 *            (referral).
	 * @return referral instance from database
	 */

	public Object add(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Referral ref = (Referral) request.getParam("ref");
		Confirmation conf = (Confirmation) ref.getConfirmation();

		try {
			try {
				ref.setDate(Utils.DateTime.currentDate());
			} catch (ParseException e) {
				Config.getConfig().getLogger().exception(e);
			}
			try {
				db.confirmations.create((Confirmation) ref.getConfirmation());

			} catch (SQLException e) {
				Config.getConfig().getLogger().exception(e);
				return "Failed";
			}

			db.refferals.create(ref);

		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);

			return "Failed";
		}
		return "success";
	}
	/**
	 * Query to update  a  referral  to database
	 * 
	 * @param request
	 *             : "Referrals/update" ,HashMap params:
	 *            (referral).
	 * @return referral instance from database
	 */

	public Object update(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Referral ref = (Referral) request.getParam("ref");
		try {
			db.refferals.update(ref);
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return "failed";
		}
		return "success";
	}

}
