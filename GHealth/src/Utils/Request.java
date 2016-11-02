package Utils;

import java.io.Serializable;
import java.util.HashMap;
/**
 * Request to send to the server .
 * @author Muhamad Igbaria
 *
 */
public class Request implements Serializable {

	/**
	 * the full url request like : view/op
	 */
	private String url;
	/**
	 * the view class in GServer Views
	 */
	private String view;
	/**
	 * the operation is the name of the method in view 
	 */
	private String op;
	/**
	 * the parametres (input) of the request
	 */
	private HashMap<String, Object> params;

	/**
	 * 
	 * @param url : full url
	 */
	public Request(String url) {
		this.url = url;
		String[] p = url.split("/");
		view = p[0];
		op = p[1];
		params = new HashMap<String, Object>();
	}

	/**
	 * 
	 * @return  hashmap
	 */
	public HashMap<String, Object> getParams(){
		return params;
	}
	/**
	 * 
	 * @param key : Hashmap key
	 * @param obj ; HashMap Object
	 */
	public void addParam(String key, Object obj) {
		params.put(key, obj);
	}

	/**
	 * 
	 * @param key : key of the Object to get
	 * @return : Object that has the key value
	 */
	public Object getParam(String key) {
		return params.get(key);
	}

	/**
	 * 
	 * @return view
	 */
	public String getView() {
		return view;
	}

	/**
	 * 
	 * @return the full url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @return the op (method name)
	 */
	public String getOp() {
		return op;
	}

}
