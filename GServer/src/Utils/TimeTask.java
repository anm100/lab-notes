package Utils;

import java.util.TimerTask;

import Views.Appointments;
/**
 * Timer Task class to send emails to patients at fixed hour for example every day at 00:00 
 * @author Muhamad Igbaria
 *
 */
public class TimeTask extends TimerTask{

	@Override
	public void run() {
		new Appointments().sendEmails();
		
	}
}
