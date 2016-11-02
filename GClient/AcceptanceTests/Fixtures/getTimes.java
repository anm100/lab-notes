package Fixtures;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import Utils.DateTime;
import Client.Application;
import Client.Config;
import Controllers.AppointmentsController;
import models.Appointment;
import fit.ActionFixture;

public class getTimes extends ActionFixture {
	ArrayList<Appointment> apps;
	private String doc_id;
	private String patient_id;
	private Date date;
	
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
		this.date=DateTime.getDate(Integer.parseInt(day[2]), Integer.parseInt(day[1]), Integer.parseInt(day[0]),
				Integer.parseInt(time[0]), Integer.parseInt(time[1]));
	}
	public boolean isAvailable() throws ParseException{
		apps=AppointmentsController.getTimes(doc_id, patient_id);
		
		if(apps.size()==0) return false;
		for(Appointment a:apps){
			if(a.getAppointmentTime().equals(date)) return true;
		}
		return false;
		
	}
	public boolean overlapTimes(){
		apps=AppointmentsController.getTimes(doc_id, patient_id);
		if(apps.size()==0) return false;
		for(int i=1;i<apps.size();i++){
			if(apps.get(i-1).equals(apps.get(i))) return true;
		}
		return false;
	}
}
