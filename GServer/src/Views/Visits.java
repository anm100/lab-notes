package Views;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.QueryBuilder;

import models.Examination;
import models.Visit;
import Database.DbHandler;
import Server.Config;
import Utils.Request;

/**
 * Database view for visits , have all the visit Queries. 
 * @author maisam marjieh 
 *
 */
public class Visits extends View{
	
	/**
	 * Query  to add a new visit to database 
	 * @param request:  : "visits/add" ,HashMap params:
	 *            (visit).
	 * @return visit  
	 */
	public Object add (Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Visit v =(Visit) request.getParam("visit");
		try {
			
			db.visits.create(v); 
			return getVisit(request) ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 *  Query to get the last visit which added to specific treatment 
	 * @param request : "visits/getVisit" ,HashMap params:
	 *            (treatment_id  ).
	 * @return visit instance 
	 */
	public Object getVisit (Request request)
	{
		DbHandler db = Config.getConfig().getHandler();
			QueryBuilder <Visit , Integer> q = db.visits.queryBuilder();
			List <Visit> visit;
			
			
				try {
					visit=   q.orderBy("visitDate",false).limit(1).where()
					.eq("treatment_id",(Integer)request.getParam("treatment_id") ).query();
					if(visit.size() == 0) return null;
					else return visit.get(0);
				
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}					
				
				
		}  
			
	
	
	
}


