package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * public class Confirmation
 * @author Ahmad Mnasra
 *
 */
@DatabaseTable(tableName = "confirmations")
public class Confirmation  extends Entity {
	/**
	 * Confirmation id , auto generate in database.
	 */
	@DatabaseField(generatedId = true)
	private int cid ; 
	/**
	 * Referral ID from HMO 
	 */
	@DatabaseField()
	private String refferal_id ; 
	/**
	 * Approval ID from HMO 
	 */
	@DatabaseField()
	private String approval_id ; 
	
	/**
	 * HMO ID 
	 */
	@DatabaseField()
	private String hmo_id ; 
	
	
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getHmo_id() {
		return hmo_id;
	}

	public void setHmo_id(String idHMOO) {
		this.hmo_id = idHMOO;
	}

	public String getRefferal_id() {
		return refferal_id;
	}

	public void setRefferal_id(String refferal_id) {
		this.refferal_id = refferal_id;
	}

	public String getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(String approval_id) {
		this.approval_id = approval_id;
	}

    
}
