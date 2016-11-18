package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "labs")
public class Lab extends Entity {
	
	@DatabaseField(generatedId = true)
	private int Lid ;
	
	@DatabaseField()
	private String NamePatient ;
	
	@DatabaseField()
	private String NPhone ;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "labratorian")
	private Labratorian labratorian ; 
	
	@DatabaseField()
	private Date startDate;
	
	
	@DatabaseField()
	private Date endDate;
	
	
	public String getNamePatient() {
		return NamePatient;
	}

	public void setNamePatient(String namePatient) {
		NamePatient = namePatient;
	}

	public String getNPhone() {
		return NPhone;
	}

	public void setNPhone(String nPhone) {
		NPhone = nPhone;
	}

	public Labratorian getLabratorian() {
		return labratorian;
	}

	public void setLabratorian(Labratorian labratorian) {
		this.labratorian = labratorian;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getLid() {
		return Lid;
	}


	

}
