package Controllers;

import models.Patient;
import models.Referral;
import Client.Application;
import Utils.Request;
/**
 * Referral Controller . have all the methods that connect the client GUI to the
 * database . the methods send request to the database and receive the database
 * results.
 * 
 * @author  Ahmad Mnasra
 *
 */
public class ReferralController {

	/**
	 * send request to database to add new Referral and confirmation 
	 * @param ref : : Referral instance
	 */
	public void addReferralHMO(Referral ref) {
		Request r = new Request("referrals/add");
		r.addParam("ref", ref);
		Application.client.sendRequest(r);
	}


	/**
	 * 
	 * @param p : patient instance 
	 * @param Spec : specialty's referral 
	 * @return boolean value , if Referral exists and active in database return true ,
	 *     else false.
	 */
	public boolean referralActiveForSpec(Patient p, String Spec) {
		for (Referral ref : p.getReferrals()) {
			if (ref.isActive() == true && ref.getSpeciality().compareTo(Spec) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param p : patient instance 
	 * @param Spec : specialty's referral 
	 * @return boolean value , set inactive referral's treatment is finished  
	 * 		   
	 * update in the database return true if success,
	 *         else false.
	 */
	public boolean referralFinshed(Patient p, String Spec) {
		Request r = new Request("referrals/update");
		for (Referral ref : p.getReferrals()) {
			if (ref.isActive() == true && ref.getSpeciality().compareTo(Spec) == 0) {
				ref.setActive(false);
				r.addParam("ref", ref);
				Application.client.sendRequest(r);
				return true;
			}
		}
		return false;
	}

}
