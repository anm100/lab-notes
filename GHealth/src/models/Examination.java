package models;

import java.util.Date;

import Utils.DateTime;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * public class Examination
 * extends class Entity  
 * @author maisam marjieh
 *
 */

@DatabaseTable(tableName = "examinations")
public class Examination extends Entity {
	
	/**
	 * Examination id
	 */

	@DatabaseField(generatedId = true)
	private int eid;
	
	/**
	 * the treatment that visit belongs to him 
	 */

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "treatment_id")
	private Treatment treatment;
	
	/**
	 * Examination  labratorian ( the labratorian which performed the Examination)
	 */

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "labratorian_id")
	private Labratorian labratorian;
	
	/**
	 * Examination clinic (where the Examination will be done ) 
	 */
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "clinic_id")
	private Clinic clinic;
	
	/**
	 * Examination type 
	 */

	@DatabaseField()
	private String eType;

	/**
	 * Examination referral comments
	 */
	@DatabaseField()
	private String comments;

	/**
	 * Examination date 
	 */
	@DatabaseField()
	private Date examinationDate;

	/**
	 * Examination referral date 
	 */
	
	@DatabaseField()
	private Date referralDate;

	/**
	 * 
	 * @return Examination id 
	 */
	public int getEid() {
		return eid;
	}


	/**
	 * set Examination id 
	 * @param eid Examination id
	 */
	public void setEid(int eid) {
		this.eid = eid;
	}


	/**
	 * 
	 * @return treatment of Examination
	 */
	public Treatment getTreatment() {
		return treatment;
	}


	/**
	 * set treatment 
	 * @param treatment
	 */
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}


	/**
	 * 
	 * @return Examination  labratorian 
	 */
	public Labratorian getLabratorian() {
		return labratorian;
	}

	/**
	 * set Examination labratorian 
	 * @param labratorian
	 */

	public void setLabratorian(Labratorian labratorian) {
		this.labratorian = labratorian;
	}

	/**
	 * 
	 * @return clinic 
	 */

	public Clinic getClinic() {
		return clinic;
	}

	/**
	 * set clinic 
	 * @param clinic
	 */

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	/**
	 * 
	 * @return Examination Type 
	 */

	public String geteType() {
		return eType;
	}


	/**
	 * set Examination type 
	 * @param eType 
	 */
	public void seteType(String eType) {
		this.eType = eType;
	}


	/**
	 * 
	 * @return Examination referral comments 
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * set Examination referral comments 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * 
	 * @return Examination date 
	 */
	public Date getExaminationDate() {
		return examinationDate;
	}


	/**
	 * set Examination date 
	 * @param examinationDate
	 */
	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}


	/**
	 * 
	 * @return Examination referral date 
	 */
	public Date getReferralDate() {
		return referralDate;
	}


	/**
	 * set Examination referral date
	 * @param referralDate
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}


	/**
	 * 
	 * @return Examination result 
	 */
	public String getResults() {
		return results;
	}


	/**
	 * set Examination result 
	 * @param results
	 */
	public void setResults(String results) {
		this.results = results;
	}


	/**
	 * 
	 * @return Examination image 
	 */
	public String getFile() {
		return file;
	}


	/**
	 * set Examination image 
	 * @param file
	 */
	public void setFile(String file) {
		this.file = file;
	}


	/**
	 * Examination result 
	 */
	@DatabaseField()
	private String results;

	/**
	 * Examination image 
	 */
	@DatabaseField()
	private String file;

	public String toString() {
		return "Examination" + this.eid + ":"+DateTime.getDateString(this.examinationDate) ;
	}

}
