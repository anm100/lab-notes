package models;

import java.util.Date;
/**
 * public Invoice class
 * extend Entity class
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "invoices")
public class Invoice extends Entity {
	
	/**
	 * the invoice id 
	 */
	@DatabaseField(generatedId = true)
	private int InvoiceId; 
	/**
	 * The Invoice payment 
	 */
	@DatabaseField()
	private double Payment; 
	/**
	 * The invoice creation date 
	 */
	@DatabaseField()
	private Date date;
	/**
	 * The invoice treatment 
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "treatment_id")
	private Treatment treatment;
	
	
	
	/**
	 * 
	 * @return the id of this invoice instance 
	 */
	public int getInvoiceId() {
		return InvoiceId;
	}
	/**
	 * Sets this invoice's id with given id value
	 * @param invoiceId
	 */
	public void setInvoiceId(int invoiceId) {
		this.InvoiceId = invoiceId;
	}
	/**
	 * 
	 * @return the payment of this invoice 
	 */
	public double getPayment() {
		return Payment;
	}
	/**
	 * Sets this invoice's payment from given payment value  
	 * @param payment
	 */
	public void setPayment(double payment) {
		Payment = payment;
	}
	/**
	 * 
	 * @return the creation date of this invoice 
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Sets this invoice's date from given date 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 
	 * @return the treatment of this invoice
	 */
	public Treatment getTreatment() {
		return treatment;
	}
	/**
	 * Sets the treatment  of this invoice instance 
	 * @param treatment
	 */
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
}
