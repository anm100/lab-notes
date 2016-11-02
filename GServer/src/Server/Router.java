package Server;

import Utils.Request;
import Views.*;

/**
 * Server router, routes the requests to the the relevant views
 * 
 * @author aj_pa
 *
 */
public class Router {
	Doctors doctors = new Doctors();
	Patients patients = new Patients();
	Reports reports = new Reports();
	Appointments appointments = new Appointments();
	Users users = new Users();
	Treatments treatments = new Treatments();
	Visits visits = new Visits();
	Examinations examinations = new Examinations();
	Clinics clinics = new Clinics();
	Invoices invoices = new Invoices();
	Referrals referrals = new Referrals();

	/**
	 * Router constructor.
	 */
	public Router() {
	}

	/**
	 * resolve handles the request and invokes the relevant view.
	 * 
	 * @param request
	 * @return Object
	 */
	public Object resolve(Request request) {
		switch (request.getView()) {
		case "doctors":
			return doctors.resolve(request);
		case "patients":
			return patients.resolve(request);
		case "appointments":
			return appointments.resolve(request);
		case "users":
			return users.resolve(request);
		case "reports":
			return reports.resolve(request);
		case "treatments":
			return treatments.resolve(request);
		case "visits":
			return visits.resolve(request);
		case "examinations":
			return examinations.resolve(request);
		case "clinics":
			return clinics.resolve(request);
		case "invoices":
			return invoices.resolve(request);
		case "referrals":
			return referrals.resolve(request);
		}
		return null;
	}
}