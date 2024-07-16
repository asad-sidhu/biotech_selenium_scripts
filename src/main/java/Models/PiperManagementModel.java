package Models;

import java.util.ArrayList;
import java.util.Arrays;


public class PiperManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String steps;
	public String passStep;
	public String failStep;
	public String emailList;
	public String hoursList;
	public boolean negativeScenario;
	public boolean positiveScenario;
	public ArrayList<ReportFilters> lstFilters;
	public static ArrayList<PiperManagementModel> lstPiperManagementCreate = new ArrayList<>();
	public PiperManagementModel() {

	}

	public static String applyFilterTitle = "Verify user can apply ";
	public static String applyFilterDesc = "This test case will verify that filtered records are displayed in table on applying ";

	
	public static ArrayList<PiperManagementModel> FillData() {
		ArrayList<PiperManagementModel> lstPiperManagementModel = new ArrayList<PiperManagementModel>();
		PiperManagementModel objTmp = new PiperManagementModel();
		
		ReportFilters objFilter = new ReportFilters();
		/*
		objTmp.TestCaseName = "AN-PM-13: Verify validation on entering invalid hours";
		objTmp.TestCaseDescription = "This test case will verify validation on entering invalid hours";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.steps = "Enter invalid hours";
		objTmp.emailList = "";
		objTmp.hoursList = "hello";
		objTmp.negativeScenario = true;
		objTmp.positiveScenario = false;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("//div[contains(text(),'Please specify a number')]"));
		objTmp.passStep = "User was not able to save alert setting with invalid hours";
		objTmp.failStep = "User was not able to save alert setting with invalid hours";
		objTmp.lstFilters.add(objFilter);
		lstPiperManagementModel.add(objTmp);
		
		objTmp = new PiperManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PM-14/15: Verify validation on entering invalid email address";
		objTmp.TestCaseDescription = "This test case will verify validation on entering invalid email address";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.steps = "Enter invalid email";
		objTmp.emailList = "email";
		objTmp.hoursList = "1";
		objTmp.negativeScenario = true;
		objTmp.positiveScenario = false;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("//div[contains(text(),'1.) Please enter comma separated list of valid email addresses')]", 
				"//div[contains(text(),'2.) There should not be a comma at the start or the end')]", "//div[contains(text(),' 3.) There should not be a duplicate email')]",
				"//div[contains(text(),'4.) There should be a minumum of 1 email address & a maximum of 30 email addresses')]", "//div[contains(text(),' 5.) Email address should not contain any special character')]"));
		objTmp.passStep = "User was not able to save alert setting with invalid email";
		objTmp.failStep = "User was not able to save alert setting with invalid email";
		objTmp.lstFilters.add(objFilter);
		lstPiperManagementModel.add(objTmp);
		
		objTmp = new PiperManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-16: Verify that user cannot create an alert setting with duplicate email";
		objTmp.TestCaseDescription = "This test case will verify that user cannot create an alert setting with duplicate email";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.steps = "Enter duplicate email";
		objTmp.emailList = "xxxxxxxxxx@12345com.pk,xxxxxxxxxx@12345com.pk";
		objTmp.hoursList = "1";
		objTmp.negativeScenario = true;
		objTmp.positiveScenario = false;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList("//div[contains(text(),'1.) Please enter comma separated list of valid email addresses')]", 
				"//div[contains(text(),'2.) There should not be a comma at the start or the end')]", "//div[contains(text(),' 3.) There should not be a duplicate email')]",
				"//div[contains(text(),'4.) There should be a minumum of 1 email address & a maximum of 30 email addresses')]", "//div[contains(text(),' 5.) Email address should not contain any special character')]"));
		objTmp.passStep = "User was not able to create an alert setting with duplicate email";
		objTmp.failStep = "User was able to create an alert setting with duplicate email";
		objTmp.lstFilters.add(objFilter);
		lstPiperManagementModel.add(objTmp);
		*/
		objTmp = new PiperManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-13: Verify that user can create an alert setting with mulitple valid emails";
		objTmp.TestCaseDescription = "This test case will verify that user can create an alert setting with mulitple valid emails";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.steps = "Enter valid emails seperated by commas";
		objTmp.emailList = "mahnoor.hashmi@12345com.pk, xxxxxxxxxx@12345com";
		objTmp.hoursList = "1";
		objTmp.negativeScenario = false;
		objTmp.positiveScenario = true;
		objFilter.LstFilterValues = new ArrayList<>(Arrays.asList(""));
		objTmp.passStep = "User was able to create an alert setting";
		objTmp.failStep = "User was not able to create an alert setting";
		objTmp.lstFilters.add(objFilter);
		lstPiperManagementModel.add(objTmp);
		
		
		return lstPiperManagementModel;
	}
}
