package Database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.*;

/**
 * Database Handler, the main class that intializes, fills and controls the
 * database using DAO objects.
 * 
 * @author aj_pa
 *
 */
public class DbHandler {

	private ConnectionSource connection;

	
	public Dao<Secretary, String> secretaries;
	public Dao<User, String> users;

	
	/**
	 * need to provide url , user ,pass to conenct to database
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public DbHandler(String url, String username, String password) {
		try {
			connection = new JdbcConnectionSource(url, username, password);
			initializeDao();
			createTable(); 
			fillDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createTable() throws SQLException {
		// TODO Auto-generated method stub
		//TableUtils.createTable(connection, User.class);
		
	}

	/**
	 * fills the database with random data.
	 * 
	 * @throws Exception
	 */
	public void fillDataBase() throws Exception {
		DataFiller df = new DataFiller(this);
		
	}

	/**
	 * initializes all the DAO for ORM usage
	 * 
	 * @throws Exception
	 */
	public void initializeDao() throws Exception {
		
		secretaries = DaoManager.createDao(connection, Secretary.class);
		users = DaoManager.createDao(connection, User.class);

	}

	/**
	 * creates all the tables using ORM, it also drops the tables first
	 * 
	 * @throws Exception
	 */
}