package models;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * public class treatment 
 *  extends class Entity  
 * @author maisam marjieh
 *
 */
@DatabaseTable(tableName = "treatments")
public class Treatment extends Entity{
 

	/**
	 * The treatment id  generate automatic 
	 */
	@DatabaseField(generatedId = true)
	private int tid;
	
	/**
	 *The flag which tell if this  treatment was closed .(true if treatment is closed) .
	 */
	@DatabaseField()
	private boolean endFlag = false;
	

	/**
	 * The flag tell if this treatment has invoice .(true if treatment has invoice)  
	 */
	@DatabaseField()
	private boolean hasInvoice = false;
	
	/**
	 * 
	 * @return true if this treatment has invoice .
	 */
	public boolean isHasInvoice() {
		return hasInvoice;
	}

	/**
	 * Sets this treatment's hasInvoice flag with the given hasInvoice value 
	 * @param hasInvoice
	 */
	public void setHasInvoice(boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	/**
	 * 
	 * @return endFlag value of this treatment
	 */
	public boolean isEndFlag() {
		return endFlag;
	}

	/**
	 * Sets this treatment's endFlag from the  given endFlag value
	 * @param endFlag
	 */
	public void setEndFlag(boolean endFlag) {
		this.endFlag = endFlag;
	}
	
	/**
	 * The treatment type 
	 */
	@DatabaseField()
	private String tType ;
	
	/**
	 * The treatment startDate 
	 */
	@DatabaseField()
	private Date start;
	
	/**
	 * The treatment end date 
	 */
	@DatabaseField()
    private Date end;
	
	/**
	 * The  treatment doctor 
	 */
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "doctor_id")
	private Doctor doctor;
	
	/**
	 * medical record instance , which this treatment that belongs to this medical records instance 
	 */
    
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "medical_id")
	private MedicalRecord medicalRecord;
	
	/**
	 *The treatment visits 
	 */
	
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Visit> visits;
	
	/**
	 * The treatment Examination
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Examination> examination;
	
	/**
	 * 
	 * @return the Examination of this treatment 
	 */
	public ForeignCollection<Examination> getExamination() {
		return examination;
	}

	/**
	 * Sets this treatment's examinations from given examinations list  
	 * @param examination
	 */
	public void setExamination(ForeignCollection<Examination> examination) {
		this.examination = examination;
	}

	/**
	 * The treatment status 
	 */
	@DatabaseField()
    private String status;
	
	/**
	 * 
	 * @return the id of this treatment  instance 
	 */
	public int getTid() {
		return tid;
	}

	/**
	 * Sets this treatment's id from given id value
	 * @param tid
	 */
	public void setTid(int tid) {
		this.tid = tid;
	}

	/**
	 * 
	 * @return the start date of this treatment 
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Sets this treatment's stratDate from given date 
	 * @param start
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * 
	 * @return the end Date of this treatment instance 
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Sets this treatment's endDate from given date 
	 * @param end
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * 
	 * @return medical record instance of this treatment 
	 */
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * Sets this treatment's medical records from given medical record instance 
	 * @param medicalRecord
	 */
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * 
	 * @return the visits of this treatment 
	 */
	public ForeignCollection<Visit> getVisits() {
		return visits;
	}

	/**
	 * Sets the visits that belongs to this treatment with the given visit list 
	 * @param visits
	 */
	public void setVisits(ForeignCollection<Visit> visits) {
		this.visits = visits;
	}

	/**
	 * 
	 * @return The status of this treatment instance 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets this treatment's status with the  given status 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Sets this treatment's doctor from given doctor instance 
	 * @param doctor
	 */
	public void setDoctor (Doctor doctor)
	{
		this.doctor=doctor;
	}
	
	/**
	 * 
	 * @return the doctor of this treatment 
	 */
	public Doctor getDoctor()
	{
		return doctor;
	}
	/**
	 * 
	 * @return the treatment type 
	 */
	public String gettType() {
		return tType;
	}

	/**
	 * Sets this treatment's type with the given type  
	 * @param tType
	 */
	public void settType(String tType) {
		this.tType = tType;
	}
	
	/**
	 * @return a string representation of this treatment .
	 */
	
	public String toString()
	{
		return "Treatment"+tid+ "-"+this.tType;
	}

	
    
}
