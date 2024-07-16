package Models;

import static MiscFunctions.DateUtil.date0;

import java.util.ArrayList;
import java.util.Arrays;

public class OrganizationManagementModel {

	public String testCaseTitle;

	public String fileName;
	public ArrayList<ReportFilters> lstFilters;
	public String passStep;
	public String failStep;
	public String AlertMessage;
	public String ErrorMessage;
	public boolean ErrorCase;
	public boolean getParentSiteID;
	public static ArrayList<OrganizationManagementModel> lstOrgBulkSiteUpload = new ArrayList<>();
	
	
	public static String BulkSitefileName = "BulkSiteTemplate.xlsx";
//	public static String OrganizationName = "Test Organization5747";
//	public static String ReportingHouse1Name = "Reporting House 1 (0900)";

	public static String OrganizationName = "Test Organization"+date0;
//	public static String OrganizationName = "Test Organization5747";
	public static String EngagementOrganizationName = "Test Engagement"+date0;
	public static String AlliedOrganizationName = "TestAlliedOrg_"+date0;
	public static String AlliedOrganizationNameCompanyProduct = "AlliedProduct2";
//	public static String AlliedOrganizationName = "Test_Allied_Org_3451";

	
	public OrganizationManagementModel() {
	}


	public static ArrayList<OrganizationManagementModel> BulkSiteFillData() {
		ArrayList<OrganizationManagementModel> lstBulkSiteUploadModel = new ArrayList<OrganizationManagementModel>();
		OrganizationManagementModel objTmp = new OrganizationManagementModel();
		ReportFilters objFilter = new ReportFilters();
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-24: Verify that user cannot upload Bulk Sites leaving Parent Site ID empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0,1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "Region", "TestSite1"));
		objTmp.getParentSiteID = false;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for PARENT_SITE_ID.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Parent Site empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Parent Site empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-25: Verify that user cannot upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for SITE_TYPE.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-26: Verify that user cannot upload Bulk Sites with invalid Site Type";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Home", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Site Type does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-27: Verify that user cannot upload Bulk Sites when Site Type violates hierarchy rules";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Farm", "TestSite1"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName =BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Site Type violates hierarchical rules.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Type empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Type empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
			
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-28: Verify that user cannot upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,2));
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", ""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.fileName = BulkSitefileName;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "No value provided for SITE_NAME.";
		objTmp.passStep = "User was not able to upload Bulk Sites leaving Site Name empty successfully";
		objTmp.failStep = "User was able to upload Bulk Sites leaving Site Name empty";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
			
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-29: Verify that user cannot upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 8, 8, 9, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Region", "TestRegion", "TestRegion", "","","",""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.passStep = "User was not able to upload Bulk Sites with duplicate Site Name successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with duplicate Site Name";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);	
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-30: Verify that user cannot upload Bulk Sites with country other than USA";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2,2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4,0, 1,2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "Pakistan", "", "", ""));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Country does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-31: Verify that user cannot upload Bulk Sites with invalid state";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "Punjab"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "State does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-32: Verify that user cannot upload Bulk Sites with invalid city";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "Lahore"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "City does not exists.";
		objTmp.passStep = "User was not able to upload Bulk Sites with country other than USA successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with country other than USA";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-33: Verify that user cannot upload Bulk Sites with value in not in decimal number for latitude";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6, 8));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "", "Latitude"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Value in LATITUDE is not valid.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-34: Verify that user cannot upload Bulk Sites with value in not in decimal number for Longitude";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6, 8, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "TestRegion", "United States", "New York", "", "", "Longitude"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = true;
		objTmp.AlertMessage = "Errors found in "+BulkSitefileName;
		objTmp.ErrorMessage = "Value in LONGITUDE is not valid.";
		objTmp.passStep = "User was not able to upload Bulk Sites with value in not in decimal number for latitude and longitude successfully";
		objTmp.failStep = "User was able to upload Bulk Sites with value in not in decimal number for latitude and longitude";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
	
		objTmp = new OrganizationManagementModel();
		objFilter = new ReportFilters();
		objTmp.testCaseTitle = "AN-OM-35: Verify that user can upload Bulk Sites hierarchy in correct order";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstRowID = new ArrayList<>(Arrays.asList(1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2));
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1,1,2,2,3,3,4,4,5,5,6,6,8,8,9,9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Region", "Region", "TestRegion1", "TestRegion2", "New York", "New York", "United States", "United States", "New York", "New York", "New York", "New York", "40.71", "40.71", "-74.00", "-74.00"));
		objTmp.getParentSiteID = true;
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkSitefileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Bulk Sites hierarchy in incorrect order successfully";
		objTmp.failStep = "User was able to upload Bulk Sites hierarchy in incorrect order";
		objTmp.lstFilters.add(objFilter);
		lstBulkSiteUploadModel.add(objTmp);
		
		return lstBulkSiteUploadModel;
	}
	

	public static ArrayList<String> lstOrgAlertMessages = new ArrayList<>(
			Arrays.asList("New organization has been created successfully", 
					"Organization details updated successfully",
					"New site created.",
					"Site details updated.",
					"Site details deleted successfully.",
					"Organization details deleted successfully.",
					"Product has been saved successfully.",
					"Product details deleted",
					"Product details updated"));
}