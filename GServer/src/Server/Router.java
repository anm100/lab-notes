package Server;

import Utils.Request;
import Views.*;

/**
 * Server router, routes the requests to the the relevant views
 * 
 * @author aj_pa
 *
 */
public class Router {
	
	Users users = new Users();


	/**
	 * Router constructor.
	 */
	public Router() {
	}

	/**
	 * resolve handles the request and invokes the relevant view.
	 * 
	 * @param request
	 * @return Object
	 */
	public Object resolve(Request request) {
		switch (request.getView()) {
		
		case "users":
			return users.resolve(request);
		
		}
		return null;
	}
}