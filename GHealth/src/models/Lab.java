package models;

import com.j256.ormlite.field.DatabaseField;

public class Lab extends Entity {
	
	@DatabaseField(generatedId = true)
	private int Lid ;
	private String NamePatient ;
	private String NPhone ;
	private Labratorian labratorian ; 
	

}
