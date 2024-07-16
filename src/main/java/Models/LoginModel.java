package Models;

import java.util.ArrayList;
import java.util.Arrays;

import org.aeonbits.owner.ConfigFactory;

import Config.ReadPropertyFile;


public class LoginModel {

	public String email;
	public String password;
	public String testCaseName;
	public boolean invalidCredentials;
	
	public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
	
	public LoginModel (String _email, String _password, String _testCaseName)
	
	{
		this.email = _email;
		this.password = _password;
		this.testCaseName = _testCaseName;
	}
	
	public static ArrayList<LoginModel> lstLogin = new ArrayList<>(
			Arrays.asList(
					new LoginModel(config.ie_username(),"xxxxxxx", "AN-Login-01: Verify user is not able to login with valid username and invalid password"),
					new LoginModel("xxxxxxxxxxxxxx", config.ie_password(), "AN-Login-02: Verify user is not able to login with invalid username and valid password"),
					new LoginModel("xxxxxxxxxxxxxx", "xxxxxxxxxxxxx", "AN-Login-03: Verify user is not able to login with invalid username and invalid password"),
					new LoginModel("", "", "AN-Login-04: Verify user is not able to login with empty username and empty password"),
					new LoginModel("", config.ie_password(), "AN-Login-05: Verify user is not able to login with empty username and valid password"),
					new LoginModel("xxxxxxxxxxxxxx", "", "AN-Login-06: Verify user is not able to login with valid username and empty password")));
}
