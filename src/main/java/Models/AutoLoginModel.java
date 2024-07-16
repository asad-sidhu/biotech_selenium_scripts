package Models;

import java.util.ArrayList;

public class AutoLoginModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String precondition;
	public ArrayList<ReportFilters> lstFilters;
	public static ArrayList<AutoLoginModel> lstAutoLoginCheck = new ArrayList<>();
	
	public AutoLoginModel() {

	}


	public static ArrayList<AutoLoginModel> FillData() {
		ArrayList<AutoLoginModel> lstAutoLoginModel = new ArrayList<AutoLoginModel>();
		AutoLoginModel objTmp = new AutoLoginModel();

		ReportFilters objFilter = new ReportFilters();
		objFilter.FilterName = "User Management";
		objTmp.TestCaseName = "AN-AL-01: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageUsersMenu";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Organization Management";
		objTmp.TestCaseName = "AN-AL-02: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageOrganMenu";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Access Management";
		objTmp.TestCaseName = "AN-AL-03: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageRole";
		objFilter.FilterID = "Access Management";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Alert Management";
		objTmp.TestCaseName = "AN-AL-04: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on User Management Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageAlertMenu";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Reports Management";
		objTmp.TestCaseName = "AN-AL-05: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageReportRole";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Agreement Management";
		objTmp.TestCaseName = "AN-AL-06: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageUserAgreement";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Barcode Management";
		objTmp.TestCaseName = "AN-AL-07: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageBarcode";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "PIPER Management";
		objTmp.TestCaseName = "AN-AL-08: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManagePiperScreen";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "PIPER Software Management";
		objTmp.TestCaseName = "AN-AL-09: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTPiperSoftware";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "PIPER Configuration Management";
		objTmp.TestCaseName = "AN-AL-10: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManagePiperConfig";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);

		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Data Template Management";
		objTmp.TestCaseName = "AN-AL-11: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageDataTemplateMenu";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Data Upload";
		objTmp.TestCaseName = "AN-AL-12: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTDataUpload";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Poultry Management";
		objTmp.TestCaseName = "AN-AL-13: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManagePoultry";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
//		objTmp = new AutoLoginModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Reports";
//		objTmp.TestCaseName = "AN-AL-14: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
//		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
//		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.FilterXPath = "roleMGMTReport";
//		objTmp.lstFilters.add(objFilter);
//		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Reports";
		objTmp.TestCaseName = "AN-AL-14: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTReport";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		objTmp = new AutoLoginModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Contact Us";
		objTmp.TestCaseName = "AN-AL-15: Verify when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.TestCaseDescription = "This testcase will verify that when user right click on "+ objFilter.FilterName +" Screen from the navigation bar that screen is opened in a new tab";
		objTmp.precondition = "Click on Adminsitration and right click on "+ objFilter.FilterName +" to open it in new tab";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.FilterXPath = "roleMGMTManageContactUs";
		objTmp.lstFilters.add(objFilter);
		lstAutoLoginModel.add(objTmp);
		
		return lstAutoLoginModel;
	}
}
