package Controllers;

import java.util.ArrayList;

import models.Invoice;
import models.Secretary;
import models.Treatment;
import Client.Application;
import Utils.Request;

/**
 * public class InvoiceController have all the methods that connect the client
 * GUI to the database
 * 
 * @author maisam marjieh
 *
 */
public class InvoiceController {

	/**
	 * send request for a list of treatments which has closed and has not
	 * invoice
	 * 
	 * @param sec
	 *            - secretary instance
	 * @return list of Treatments
	 */
	public static ArrayList<Treatment> getAllopenTreatments(Secretary sec)

	{
		Request r = new Request("treatments/getTreatments");
		r.addParam("Secretary", sec);
		return (ArrayList<Treatment>) Application.client.sendRequest(r);

	}

	/**
	 * send request for server to send new invoice to HMO of the patient 
	 * 
	 * @param invoice
	 *            - the new invoice that will be send to HMO
	 */
	public static void sendInvoice(Invoice invoice) {

		Request r = new Request("invoices/send");
		r.addParam("Invoice", invoice);
		Application.client.sendRequest(r);

	}

}
