package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Statistic Entity Class
 * @author Ahdab Serhan
 *
 */
@DatabaseTable(tableName = "statistics")
public class Statistic extends Entity{
	@DatabaseField(generatedId = true)
    private int id;
	@DatabaseField()
	private Date date;
	@DatabaseField()
	private int numOfPatients;

	@DatabaseField()
	private int waitingPeriod;
/**
 * 
 * @return statistic id
 */
	public int getId() {
		return id;
	}
/**
 * set statistic id
 * @param id
 */
	public void setId(int id) {
		this.id = id;
	}
/**
 * 
 * @return statistic date
 */
	public Date getDate() {
		return date;
	}
/**
 * set statistic date
 * @param date
 */
	public void setDate(Date date) {
		this.date = date;
	}
/**
 * 
 * @return number of patient
 */
	public int getNumOfPatients() {
		return numOfPatients;
	}
/**
 * set number of patient
 * @param numOfPatients
 */
	public void setNumOfPatients(int numOfPatients) {
		this.numOfPatients = numOfPatients;
	}
/**
 * 
 * @return waiting period
 */
	public int getWaitingPeriod() {
		return waitingPeriod;
	}
/**
 * set waiting period
 * @param waitingPeriod
 */
	public void setWaitingPeriod(int waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	

	
	
	
}
