package Models;

import static MiscFunctions.DateUtil.date0;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportsManagementModel {

	public boolean isOpenPopUp;
	public String rmName;
	public String rmDesc;
	public String step1;
	public boolean save;
	public String testcaseTitle;
	public String testcaseDesc;
	public boolean step;
	public String passScenario;
	public String failScenario;
		
	public String rgName;
	public String rgDesc;
	public boolean rgReport;
	public String rgstep1;
	public boolean rgsave;
	public String rgtestcaseTitle;
	public String rgtestcaseDesc;
	public boolean rgstep;
	public String rgpassScenario;
	public String rgfailScenario;
	
	public static String RoleName = "Test Role - "+date0;		
	public static String ReportGroupName = "Test Report Group - "+date0;
	
	
	public ReportsManagementModel (String _rgName, String _rgDesc, boolean _rgReports, String _rgstep1, boolean _rgsave, String _rgtestcaseTitle, String _rgtestcaseDesc, boolean _rgstep, String _rgpassScenario, String _rgfailScenario )
	
	{
		this.rgName = _rgName;
		this.rgDesc = _rgDesc;
		this.rgReport = _rgReports;
		this.rgstep1 = _rgstep1;
		this.rgsave = _rgsave;
		this.rgtestcaseTitle = _rgtestcaseTitle;
		this.rgtestcaseDesc = _rgtestcaseDesc;
		this.rgstep = _rgstep;
		this.rgpassScenario = _rgpassScenario;
		this.rgfailScenario =_rgfailScenario;
	}
	
	public static ArrayList<ReportsManagementModel> lstRGMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new ReportsManagementModel("", "", false, "Leave all fields empty", false, "AN-RM-14: Leave all fields empty and click on save button", "This test case wll verify that user cannot create report group without filling all mandatory fields", true, "The user was not able to create Report Group leaving all fields empty", "User was able to create a Report Group leaving all fields empty"),
					new ReportsManagementModel("", "Lorem Ipsum", false, "Leave 2 fields empty", false, "AN-RM-15: Leave 2 field empty and click on save button", "This test case will verify that user is not able to create role with two field empty", true, "The user was not able to create Report Group leaving 2 fields empty", "User was able to create a Report Group leaving 2 fields empty"),
					new ReportsManagementModel(ReportGroupName, "Group created by automation script", true, "Fill all fields", true, "AN-RM-16: User should be able to save Report Details", "This test case will verify that user is able to create new report group", false, "The user was able to create Report Group after entering valid data in all fields", "The user was not able to create Report Group")));

	
}
