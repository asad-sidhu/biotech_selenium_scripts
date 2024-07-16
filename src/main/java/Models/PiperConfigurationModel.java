package Models;

import java.util.ArrayList;

import MiscFunctions.DateUtil;
import MiscFunctions.ExtentVariables;

public class PiperConfigurationModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String steps;
	public String passStep;
	public String failStep;
	public String pathogen;
	public String sampleMatrix;
	public boolean GreaterLesserCheck;
	public ArrayList<ReportFilters> lstFilters;
	public static ArrayList<PiperConfigurationModel> lstPiperConfigurationCreate = new ArrayList<>();
	public static ArrayList<PiperConfigurationModel> lstPiperConfigurationCreatePA = new ArrayList<>();
	
	public PiperConfigurationModel() {
	}

	public static String applyFilterTitle = "Verify user can apply ";
	public static String applyFilterDesc = "This test case will verify that filtered records are displayed in table on applying ";

	public static ArrayList<PiperConfigurationModel> FillData() {
		ArrayList<PiperConfigurationModel> lstPiperConfigurationModel = new ArrayList<PiperConfigurationModel>();
		PiperConfigurationModel objTmp;
		ReportFilters objFilter;
	/*
		objTmp = new PiperConfigurationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-02: Verify that user cannot create an installation run leaving all fields empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot create an installation run leaving all fields empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "";
		objFilter.MaxMean = "";
		objFilter.MinStd = "";
		objFilter.MaxStd = "";
		objTmp.steps = "Leave all fields empty";
		objTmp.passStep = "User was not able to create installation run without filling all mandatory fields";
		objTmp.failStep = "Installation run was created without filling all mandatory fields";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstPiperConfigurationModel.add(objTmp);
		
		objTmp = new PiperConfigurationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-03: Verify user cannot enter alpha data in numeric fields while creating new installation run";
		objTmp.TestCaseDescription = "This test case will verify user cannot enter alpha data in numeric fields while creating new installation run";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "a";
		objFilter.MaxMean = "a";
		objFilter.MinStd = "a";
		objFilter.MaxStd = "a";
		objTmp.steps = "Enter alpha data in all fields";
		objTmp.passStep = "User was not able to enter alpha data in numeric fields successfully";
		objTmp.failStep = "User was able to enter alpha data in numeric fields";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstPiperConfigurationModel.add(objTmp);

		objTmp = new PiperConfigurationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-04: Verify min Mean and min Std. cannot be greater than Max Mean and Max Std. value respectively";
		objTmp.TestCaseDescription = "This test case will verify min Mean and min Std. cannot be greater than Max Mean and Max Std. value respectively";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "20";
		objFilter.MaxMean = "2";
		objFilter.MinStd = "20";
		objFilter.MaxStd = "2";
		objTmp.steps = "Enter Min value greater than max value";
		objTmp.passStep = "User was not able to add min values greater than max values successfully";
		objTmp.failStep = "User was able to enter min values greater than max values";
		objTmp.GreaterLesserCheck = true;
		objTmp.lstFilters.add(objFilter);
		lstPiperConfigurationModel.add(objTmp);
	*/
		objTmp = new PiperConfigurationModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-05: Verify user can create an Installtion Run";
		objTmp.TestCaseDescription = "This test case will verify that user can create an INstallation Run";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.MinMean = "1"+DateUtil.date0;
		objFilter.MaxMean = "99999";
		objFilter.MinStd = "100";
		objFilter.MaxStd = "1000";
		objTmp.steps = "Enter valid data in all fields";
		objTmp.passStep = "User was able to create installation run successfully";
		objTmp.failStep = "User failed to create installation run";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstPiperConfigurationModel.add(objTmp);
		
		return lstPiperConfigurationModel;
	}
	
	
	public static ArrayList<PiperConfigurationModel> CreatePA() {
		ArrayList<PiperConfigurationModel> lstPiperConfigurationModel = new ArrayList<PiperConfigurationModel>();
		PiperConfigurationModel objTmp = new PiperConfigurationModel();
		
		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-PCM-02: Verify that user cannot create an installation run leaving all fields empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot create an installation run leaving all fields empty";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.pathogen = "Patho_2";
		objTmp.sampleMatrix = "";
		objTmp.steps = "Leave all fields empty";
		objTmp.passStep = "User was not able to create installation run without filling all mandatory fields";
		objTmp.failStep = "Installation run was created without filling all mandatory fields";
		objTmp.GreaterLesserCheck = false;
		objTmp.lstFilters.add(objFilter);
		lstPiperConfigurationModel.add(objTmp);
	
		return lstPiperConfigurationModel;
	}
	
	
}