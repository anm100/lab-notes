package Fixtures;

import java.util.ArrayList;

import Client.Application;
import Client.Config;
import Controllers.AppointmentsController;
import fit.ActionFixture;

public class getDoctorsBySpeciality extends ActionFixture {
	private ArrayList<Object[]> doctors;
	private String spec;
	private String patient_id;
	private int i;
	public void connect(){
		Config.getConfig().readTextConfig();
		Application.connect();
	}
	
	public void setSpec(String spec){
		this.spec=spec;
	}
	public void setPatientId(String id){
		patient_id=id;
	}
	public void setIndex(int i){
		this.i=i;
	}
	public int numOfDoctors(){
		doctors= AppointmentsController.getDoctorsBySpeciality(spec, "300000000");
		return doctors.size();
	}
	
	public String lastVisitById(){
		doctors= AppointmentsController.getDoctorsBySpeciality(spec, patient_id);
		Object[] firstDoc=doctors.get(0);
		return (String) firstDoc[0];
	}
	
	public String lastVisitByDate(){
		doctors= AppointmentsController.getDoctorsBySpeciality(spec, patient_id);
		Object[] firstDoc=doctors.get(0);
		return (String) firstDoc[3];
	}
	
	public String doctorAt(){
		doctors= AppointmentsController.getDoctorsBySpeciality(spec, patient_id);
		if(doctors==null || i>=doctors.size() || i<0) return "exception";
		Object[] d= doctors.get(i);
		return (String) d[0];
	}
}
