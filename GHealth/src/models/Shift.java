package models;


import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Shift Entity Class. evry shift has start and end time and doctor id.
 * Unique(start time, end time , doctor id) 
 * @author Muhamad Igbaria
 *
 */
@DatabaseTable(tableName = "shifts")
public class Shift extends Entity{
	
	/**
	 * default constructor
	 */
	public Shift(){
		super();
	}
	/**
	 * 
	 * @param startDate- the start time of shift like: 27/6/2016 09:00:00
	 * @param endDate - the end time of shift like : 27/6/2016 17:00:00
	 * @param doctor - shift doctor .
	 */
	public Shift(Date startDate, Date endDate,Doctor doctor){
		this.startDate=startDate;
		this.endDate=endDate;
		this.doctor=doctor;
	}
	
	/**
	 * shift id , auto generate in database.
	 */
	@DatabaseField(generatedId = true)
	private int id;
	/**
	 * start time of the shift .
	 */
	@DatabaseField(uniqueCombo=true)
	private Date startDate;
	/**
	 * end time of the shift.
	 */
	@DatabaseField(uniqueCombo=true)
	private Date endDate;
	
	/**
	 * shift doctor
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "doctor_id" , uniqueCombo=true)
	private Doctor doctor;
	/**
	 * 
	 * @return shift id .
	 */
	public int getId() {
		return id;
	}
	/**
	 * set shift id.
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return shift start time.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * set shift start time.
	 * @param startDate 
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 
	 * @return shift end time .
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * set shift end date.
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
