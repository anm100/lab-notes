package models;

import java.util.Date;

import Utils.DateTime;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * public Visit  class 
 * extends class Entity 
 * @author maisam marjieh
 *
 */
@DatabaseTable(tableName = "visits")
public class Visit extends Entity{
	
	/**
	 * The visit id
	 */
	@DatabaseField(generatedId = true)
	private int vid;
	
	/**
	 * The visit date
	 */
	
	@DatabaseField()
	private Date visitDate;
	
	/**
	 *The  visit comments 
	 */
	
	@DatabaseField()
	private String comments;
	
	/**
	 * Instance of the  treatment  that visit belongs to him 
	 */
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "treatment_id")
	private Treatment treatment;
	
	/**
	 * 
	 * @return The id value of this visit instance 
	 */
	
	public int getVid() {
		return vid;
	}
	
	/**
	 * Sets this Visit's id with the given id.
	 */

	public void setVid(int vid) {
		this.vid = vid;
	}
	/**
	 * 
	 * @return The date value of this visit instance
	 */

	public Date getVisitDate() {
		return visitDate;
	}
	
	/**
	 * Sets this Visit's date with the given date   
	 * @param visitDate
	 */

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	/**
	 * 
	 * @return The comments value of this visit instance 
	 */

	public String getComments() {
		return comments;
	}
	/**
	 * Sets this Visit's comments with the given comments 
	 * @param comments
	 */

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * 
	 * @return The treatment instance of this visit instance (treatment that this visit belongs to him) 
	 */

	public Treatment getTreatment() {
		return treatment;
	}
	/**
	 * Sets this Visit's treatment with the given treatment instance  
	 * @param treatment that this visit belongs to him 
	 */

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	/**
	 * @return a string representation of this visit.
	 */
	
	public String toString(){
		return "Visit" +vid+ ": "+DateTime.getDateString(visitDate);
		
	}
	
}
