package Fixtures;


import Client.Application;
import Client.Config;
import Controllers.AppointmentsController;
import fit.ActionFixture;

public class getSpecialties extends ActionFixture {

	
	private String spec;
	private int i;
	
	public void connect(){
		Config.getConfig().readTextConfig();
		Application.connect();
	}
	public int numOfSpec(){
		String[] all =AppointmentsController.getSpecialties();
		if(all==null) return 0;
		return all.length;
	}
	
	public void setSpec(String spec){
		this.spec=spec;
	}
	
	public boolean isExist(){
		String[] all =AppointmentsController.getSpecialties();
		if(all==null) return false;
		for(int i=0;i<all.length;i++){
			if(all[i].equals(spec)) return true;
		}
		return false;
	}
	
	public void setIndex(int i){
		this.i=i;
	}
	public String specAt(){
		String[] all =AppointmentsController.getSpecialties();
		if(all==null || i>=all.length || i<0) return "exception";
		return all[i];
	}
}
