package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * public class labratorian 
 * extends ClinicEmployee  
 * @author maisam marjieh 
 *
 */
@DatabaseTable(tableName = "labratorians")
public class Labratorian extends ClinicEmployee {
	
	/**
	 * the labratorian examinations 
	 */
	@ForeignCollectionField(eager=true)
    private ForeignCollection<Examination> examinations;

	/**
	 * 
	 * @return the examination that this labratorian performed them 
	 */
	public ForeignCollection<Examination> getExaminations() {
		return examinations;
	}

	/**
	 * Sets this labratorian's examinations from given examinations list 
	 * @param examinations
	 */
	public void setExaminations(ForeignCollection<Examination> examinations) {
		this.examinations = examinations;
	}
}
