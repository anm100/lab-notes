package Utils;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;


/**
 * Compare two Doctors from doctors table in Add new appointment GUI - for sort the doctors by patient last visit
 * @author Muhamad Igbaria
 *
 */
public class DoctorsComparator implements Comparator<Object[]>{

	/**
	 * Compare two doctors by the last visit ,
	 *  the method get patient last visit in two doctors and return the doctor that have the bigger date.
	 *  the Object[] include : 
	 *  					Doctor id,Doctor name,Doctor clinic,Last visit Date
	 *  the Objects compared by Last visit Date. 
	 */
	@Override
	public int compare(Object[] rec1, Object[] rec2) {
		String date1= (String) rec1[3];
		String date2= (String) rec2[3];
		if(date1.equals("") && date2.equals("")) return 0;
		if (date1.equals("") && !date2.equals("")) return 1;
		if(!date1.equals("") && date2.equals("")) return -1;
		if(!date1.equals("") && !date2.equals("")) {
			//split the date : dd/MM/yyyy
			String s_date1[]=date1.split("/");
			String s_date2[]=date2.split("/");
			
			Date time1 ;
			Date time2;
			try {
				time1 = DateTime.getDate(Integer.parseInt(s_date1[2]), Integer.parseInt(s_date1[1]), Integer.parseInt(s_date1[0]));
				time2 = DateTime.getDate(Integer.parseInt(s_date2[2]), Integer.parseInt(s_date2[1]), Integer.parseInt(s_date2[0]));
				if(time1.before(time2)) return 1;
				if(time1.after(time2)) return -1;
				return 0;
			
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
		}
		return 0;
	}
}
