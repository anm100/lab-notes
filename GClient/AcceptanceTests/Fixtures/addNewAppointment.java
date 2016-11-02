package Fixtures;

import java.text.ParseException;
import java.util.Date;

import Client.Application;
import Client.Config;
import Controllers.AppointmentsController;
import Utils.DateTime;
import fit.ActionFixture;

public class addNewAppointment extends ActionFixture {

	
	private String doc_id;
	private String patient_id;
	private Date time;
	public void connect(){
		Config.getConfig().readTextConfig();
		Application.connect();
	}
	
	public void setDocId(String doc_id){
		this.doc_id=doc_id;
	}
	
	public void setPatientId(String patient_id){
		this.patient_id=patient_id;
	}
	
	public void setDate(String date) throws NumberFormatException, ParseException{
		String[] sp= date.split(" ");
		String [] day= sp[0].split("/");
		String [] time=sp[1].split(":");
		this.time=DateTime.getDate(Integer.parseInt(day[2]), Integer.parseInt(day[1]), Integer.parseInt(day[0]),
				Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}
	public boolean addIfNotExist() throws NumberFormatException, ParseException{
		
		return AppointmentsController.addNewAppointment(doc_id, patient_id, this.time);
	}
}
