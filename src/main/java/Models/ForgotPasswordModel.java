package Models;

import java.util.ArrayList;
import java.util.Arrays;

import MiscFunctions.Constants;
import MiscFunctions.ExtentVariables;

public class ForgotPasswordModel {

	public static String forgotPassword_email = "Project123.test.user100@gmail.com";
	public static String forgotPassword_password = "Project123123";
	public static String forgotPasswordSecurityEmail = "junnaid0005@gmail.com";
	public static String forgotPassword_resetPassword = "Project123123";
	
	public static ArrayList<String> lstFpEmail = new ArrayList<>(
			Arrays.asList("xxxxxxxxxx@12345pk", 
					forgotPassword_email));
	
	public static ArrayList<String> lstFpUrl = new ArrayList<>(
			Arrays.asList(Constants.url_forgotPassword,
					Constants.url_loginPage));
	
	public static ArrayList<String> lstFpAlertMessages = new ArrayList<>(
			Arrays.asList("User is not registered.", 
					"Please check your e-mail for instructions."));  
	
	public static ArrayList<String> lstFpTestCase = new ArrayList<>(
			Arrays.asList("AN-FP-01: Verify user is not able to reset password with user that is not registered", 
					"AN-FP-02: Verify user is able to reset password for valid user"));
	

	public static ArrayList<String> lstFpPassMessage = new ArrayList<>(
			Arrays.asList("User received alert message that 'User is not registered.' successfully", 
					"User received alert message that 'Please check your e-mail for instructions.' successfully"));
	
	public static ArrayList<String> lstFpFailMessage = new ArrayList<>(
			Arrays.asList("User did not received alert message that 'User is not registered.'", 
					"User did not received alert message that 'Please check your e-mail for instructions.'"));
	
	
}
