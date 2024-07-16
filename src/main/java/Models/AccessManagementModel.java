package Models;

import static MiscFunctions.DateUtil.date0;

import java.util.ArrayList;
import java.util.Arrays;
import PageObjects.Patho_2LogPage;

public class AccessManagementModel {

	public boolean isOpenPopUp;
	public String accessName;
	public String accessDesc;
	public String step1;
	public String testcase;
	public boolean step;
	
	public String rgName;
	public String rgDesc;
	public boolean rgReport;
	public String rgstep1;
	public boolean rgsave;
	public String rgtestcase;
	public boolean rgstep;
	
	public String TestCaseName;
	public String TestCaseDescription;
	public ArrayList<ReportFilters> lstFilters;

	public static ArrayList<String> lstAccessCreate = new ArrayList<>(
			Arrays.asList("Administrator"+date0, 
					"Role Description"));
	
	
	public static ArrayList<String> lstAccessAlertMessages = new ArrayList<>(
			Arrays.asList("New Reporting role created.", 
					"Role has been updated successfully."));
	
	public static ArrayList<AccessManagementModel> lstUserManagementAccessRole = new ArrayList<>();
	
	public AccessManagementModel (boolean _isOpenPopup, String _accessName, String _accessDesc, String _step1, String _testcase, boolean _step )
	
	{
		this.isOpenPopUp = _isOpenPopup;
		this.accessName = _accessName;
		this.accessDesc = _accessDesc;
		this.step1 = _step1;
		this.testcase = _testcase;
		this.step = _step;
	}
	
	public AccessManagementModel (String _rgName, String _rgDesc, boolean _rgReports, String _rgstep1, boolean _rgsave, String _rgtestcase, boolean _rgstep )
	
	{
		this.rgName = _rgName;
		this.rgDesc = _rgDesc;
		this.rgReport = _rgReports;
		this.rgstep1 = _rgstep1;
		this.rgsave = _rgsave;
		this.rgtestcase = _rgtestcase;
		this.rgstep = _rgstep;
	}
	
	
	public AccessManagementModel() {
	}
	

}
