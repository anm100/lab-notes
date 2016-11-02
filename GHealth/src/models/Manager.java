package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "managers")
/**
 * Manager Entity Class
 * @author Ahdab Serhan
 *
 */
public class Manager extends ClinicEmployee {
	@DatabaseField()
	private boolean isCeo;
/**
 * 
 * @return if the manager is CEO manager
 */
	public boolean isCeo() {
		return isCeo;
	}
/**
 * set isCEO manger 
 * @param isCeo
 */
	public void setCeo(boolean isCeo) {
		this.isCeo = isCeo;
	}
}
 