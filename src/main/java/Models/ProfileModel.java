package Models;

import java.util.ArrayList;
import java.util.Arrays;

import MiscFunctions.Constants;

public class ProfileModel {

	public Boolean isOpenPage;
	public String url;
	public String testCaseNavigate;
	public String stepPage;
	public String firstName;
	public String lastName;
	public Boolean cellCode;
	public String cellNo;
	public Boolean phoneCode;
	public String phoneNo;
	public String testCaseMandatoryCheck;
	public Boolean CheckS1;
	public String pageTitle;

	public ProfileModel( String _url, String _testCase, String _stepPage, String _pageTitle)

	{
		this.url = _url;
		this.testCaseNavigate = _testCase;
		this.stepPage = _stepPage;
		this.pageTitle = _pageTitle;
	}



	public static ArrayList<ProfileModel> lstProfileNavigate = new ArrayList<>(
			Arrays.asList(
					new ProfileModel(Constants.url_dashboard,"AN-PS-01: Navigate to Profile Setting from Home Screen", "1. Hover to sidebar and click on Home", "Home"),
					new ProfileModel(Constants.url_userManagement,"AN-PS-02: Navigate to Profile Setting from abc Management Screen", "1. Hover to sidebar and click on User Management", "User Management"),
					new ProfileModel(Constants.url_organizationManagement,"AN-PS-03: Navigate to Profile Setting from Organization Management Screen", "1. Hover to sidebar and click on Organization Management", "Organization Management"),
					new ProfileModel(Constants.url_roleManagement,"AN-PS-04: Navigate to Profile Setting from Access Management Screen", "1. Hover to sidebar and click on Access Management Screen", "Access Management"),
					new ProfileModel(Constants.url_reportManagement,"AN-PS-05: Navigate to Profile Setting from Reports Management Screen", "1. Hover to sidebar and click on Reports Management", "Reports Management"),
					new ProfileModel(Constants.url_qrCodeManagement, "AN-PS-06: Navigate to Profile Setting from barcode Management Screen", "1. Hover to sidebar and click on Barcode Management", "Barcode Management"),
					new ProfileModel(Constants.url_termsManagement, "AN-PS-07: Navigate to Profile Setting from Agreement Management Screen", "1. Hover to sidebar and click on Agreement Management", "Agreement Management"),
					new ProfileModel(Constants.url_notificationSettings, "AN-PS-08: Navigate to Profile Setting from Alert Management Screen", "1. Hover to sidebar and click on Alert Management", "Alert Management"),
					new ProfileModel(Constants.url_configurationAdmin, "AN-PS-09: Navigate to Profile Setting from Complex Cycling Config Screen", "1. Hover to sidebar and click on Complex Cycling Config", "Complex OPG Range Config"),
					new ProfileModel(Constants.url_assetAdmin, "AN-PS-10: Navigate to Profile Setting from Asset Registration Screen", "1. Hover to sidebar and click on Asset Registration", "Asset Management"),
					new ProfileModel(Constants.url_programAdmin, "AN-PS-11: Navigate to Profile Setting from Program Management Screen", "1. Hover to sidebar and click on Program Registration", "Program Management"),
					new ProfileModel(Constants.url_surveyAdmin, "AN-PS-12: Navigate to Profile Setting from Form Management Screen", "1. Hover to sidebar and click on form Management", "Form Management"),
					new ProfileModel(Constants.url_productCatalog, "AN-PS-13: Navigate to Profile Setting from Company Products Screen", "1. Hover to sidebar and click on Company Products Screen", "Company Products"),
					new ProfileModel(Constants.url_interventionAdmin, "AN-PS-14: Navigate to Profile Setting from Intervention Management Screen", "1. Hover to sidebar and click on Intervention Management Screen", "Intervention Management"),
					new ProfileModel(Constants.url_strategyManagement, "AN-PS-15: Navigate to Profile Setting from Monitoring Strategy Screen", "1. Hover to sidebar and click on Monitoring Strategy Screen", "Monitoring Strategy"),
					new ProfileModel(Constants.url_productCatalog, "AN-PS-16: Navigate to Profile Setting from Product Management Screen", "1. Hover to sidebar and click on Product Management Screen", "Product Management"),
					new ProfileModel(Constants.url_animalManagement, "AN-PS-17: Navigate to Profile Setting from Piper Management Screen", "1. Hover to sidebar and click on Piper Management", "PIPER Management"),
					new ProfileModel(Constants.url_softwareAdmin, "AN-PS-18: Navigate to Profile Setting from Piper Software Management Screen", "1. Hover to sidebar and click on Piper Software Management", "PIPER Software Management"),
					new ProfileModel(Constants.url_configurationAdmin, "AN-PS-19: Navigate to Profile Setting from Piper Configuration Management Screen",  "1. Hover to sidebar and click on Piper Config Management", "PIPER Configuration Management"),
					new ProfileModel(Constants.url_templateAdmin, "AN-PS-20: Navigate to Profile Setting from Data Template Screen", "1. Hover to sidebar and click on Data Template", "Data Template Management"),
					new ProfileModel(Constants.url_dataAdmin, "AN-PS-21: Navigate to Profile Setting from Data Upload Screen", "1. Hover to sidebar and click on Data Upload", "Data Upload"),
					new ProfileModel(Constants.url_reportManagement, "AN-PS-22: Navigate to Profile Setting from Reports Screen", "1. Hover to sidebar and click on Reports", "Reports")
					));



}
