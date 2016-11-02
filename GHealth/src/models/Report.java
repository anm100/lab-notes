package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import com.j256.ormlite.field.DatabaseField;
/**
 * Report Entity Class
 * @author Ahdab Serhan
 *
 */
public class Report implements Serializable {
	private int pMax=0;
	private int pMin=Integer.MAX_VALUE;
	private double pAvg=0;
	private double pStd=0;
	private int wMax=0;
	private int wMin=Integer.MAX_VALUE;
	private double wAvg=0;
	private double wStd=0;
	private ArrayList<Integer> patientsLeft = new ArrayList<Integer>();
	private ArrayList<Integer> canceledAppointments = new ArrayList<Integer>();
	/**
	 * 
	 * @return the maximum of patient's number
	 */
	public int getpMax() {
		return pMax;
	}
/**
 * set the maximum of patient's number
 * @param pMax
 */
	public void setpMax(int pMax) {
		this.pMax = pMax;
	}
/**
 * 
 * @return the minimum of patient's number
 */
	public int getpMin() {
		return pMin;
	}
/**
 * set the minimum of the patient's number
 * @param pMin
 */
	public void setpMin(int pMin) {
		this.pMin = pMin;
	}
/**
 * 
 * @return the average of patient's number
 */
	public double getpAvg() {
		return pAvg;
	}
/**
 * set the average of patient's number
 * @param pAvg
 */
	public void setpAvg(double pAvg) {
		this.pAvg = pAvg;
	}
/**
 * 
 * @return the standard divsion of patient's number
 */
	public double getpStd() {
		return pStd;
	}

/**
 * set the standart division of patient's number
 * @param pStd
 */
	public void setpStd(double pStd) {
		this.pStd = pStd;
	}
/**
 * 
 * @return the maximum of waiting period 
 */
	public int getwMax() {
		return wMax;
	}
/**
 * set the maximum of waiting period
 * @param wMax
 */
	public void setwMax(int wMax) {
		this.wMax = wMax;
	}
/**
 * 
 * @return the minimum of waiting period
 */
	public int getwMin() {
		return wMin;
	}
/**
 * set the minimum of waiting period
 * @param wMin
 */
	public void setwMin(int wMin) {
		this.wMin = wMin;
	}
/**
 * 
 * @return the average of waiting period
 */
	public double getwAvg() {
		return wAvg;
	}
/**
 * set the average of waiting period
 * @param wAvg
 */
	public void setwAvg(double wAvg) {
		this.wAvg = wAvg;
	}
/**
 * 
 * @return the standard division of waiting period
 */
	public double getwStd() {
		return wStd;
	}
/**
 * set the standard division of waiting period
 * @param wStd
 */
	public void setwStd(double wStd) {
		this.wStd = wStd;
	}
/**
 * 
 * @return array list of statistic
 */
	public ArrayList<Statistic> getStatistic() {
		return statistic;
	}
/**
 * set the statistics in arrayList
 * @param statistic
 */
	public void setStatistic(ArrayList<Statistic> statistic) {
		this.statistic = statistic;
	}

/**
 * 
 * @return arrayList of the patients that left GHealth 
 */
	public ArrayList<Integer> getPatientsLeft() {
		return patientsLeft;
	}
/**
 * set the patients that left GHealth in arrayList
 * @param patientsLeft
 */
	public void setPatientsLeft(ArrayList<Integer> patientsLeft) {
		this.patientsLeft = patientsLeft;
	}
/**
 * 
 * @return arrayList of appointments that canceled
 */
	public ArrayList<Integer> getCanceledAppointments() {
		return canceledAppointments;
	}
/**
 * set the canceled appointments in arrayList
 * @param canceledAppointments
 */
	public void setCanceledAppointments(ArrayList<Integer> canceledAppointments) {
		this.canceledAppointments = canceledAppointments;
	}


	private ArrayList<Statistic> statistic;
    

}
