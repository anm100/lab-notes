
import Client.Application;
import Client.Config;
import Controllers.PatientsController;
import Controllers.UsersController;
import models.Doctor;
import models.Patient;
import models.User;
import junit.framework.TestCase;


//test the login functionality
public class LoginTests extends TestCase {
	
	private Doctor d1 ;
	

	
	protected void setUp() throws Exception {
		Config.getConfig().readTextConfig();
		Application.connect();
		d1 = new Doctor ();
		d1.setSid("200000000");
		d1.setPass("123123");
		d1.setFirstName("toni");
		d1.setLastName("kroos");
		
		
		
	}
	//check if getUser method get the correct patient 
	public void test_getUser_UserIsExist() {
		User result =  UsersController.getUser("200000000");
		User expected = d1;
		assertTrue(expected.getSid().equals(result.getSid()));
	}

	// check if the method return null about user not exist 
	public void test_getUser_Not_Exist() {
		User result = UsersController.getUser("205495161");
		User expected = null;
		assertEquals(expected, result);
	}

	//check the case of :user enter the correct password 
	public void test_authinticateUser_correct_password() {
		boolean result = UsersController.authinticateUser(d1, "123123");
		boolean expected = true;
		assertEquals(expected, result);
	}

	//case of : insert incorrect password 
	public void test_authinticateUser_incorrect_password() {
		boolean result = UsersController.authinticateUser(d1, "200000235");
		boolean expected = false;
		assertEquals(expected, result);
	}

	//set online and connecting new user
	public void test_setOnline_isNotOnline() {
		boolean result = UsersController.setOnline(d1);
		boolean expected = true;
		assertEquals(expected, result);
		}


	
	

}
