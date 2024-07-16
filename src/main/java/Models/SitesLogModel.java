package Models;

import java.util.ArrayList;
import java.util.Arrays;

import PageObjects.SitesLogPage;

public class SitesLogModel {

	public String TestCaseNameButtonActive;
	public String TestCaseDescriptionButtonActive;
	public String TestCaseName;
	public String TestCaseDescription;
	public String TestCaseNameSearch;
	public String TestCaseDescriptionSearch;
	public String TestCaseNameBubbleFilterTop;
	public String TestCaseDescriptionBubbleFilterTop;
	public String TestCaseNameBubbleFilterCheckbox;
	public String TestCaseDescriptionBubbleFilterCheckbox;
	public String TestCaseNameClearInput;
	public String TestCaseDescClearInput;
	public String TestCaseNameHoverReset;
	public String TestCaseDescriptionHoverReset;
	public String TestCaseNameRevertBack;
	public String TestCaseDescriptionRevertBack;
	public String TestCaseNameSort;
	public String TestCaseDescriptionSort;
	public ArrayList<ReportFilters> lstFilters;
	public boolean ReloadPage;
	public boolean Filter1;
	public boolean Filter2;
	public boolean Filter3;
	public boolean paginationExist;
	public boolean paginationLastPage;
	public boolean paginationNextPage;
	public boolean paginationFirstPage;
	public boolean paginationPreviousPage;
	public boolean sortLogic1;
	public boolean sortLogic2;
	public boolean resetFilter;
	public boolean startWith;
	public boolean endsWith;
	public boolean contains;
	public boolean runIngestion;
	public boolean firstCase;
	public boolean secondCase;
	public boolean viewAccess;
	public boolean unviewAccess;
	public boolean sortDescFirst;
	public String FilterHideID;
	public String FilterUnHideID;
	public String input;
	public String fileJson;
	public String sampleID;

	public static ArrayList<SitesLogModel> lstSitesLogDateEnter = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogFieldAccess = new ArrayList<>();
	public static ArrayList<SitesLogModel> lstSitesLogContexualCheck = new ArrayList<>();
	
	public SitesLogModel() {

	}

	public static String applyFilterTitle = "Verify user can select checkbox from ";
	public static String applyFilterDesc = "This test case will verify that user can select checkbox from ";
	public static String filterIndicatorTitle = "Verify user can apply filter and table displays relevant results on applying ";
	public static String filterIndicatorDesc = "This test case will verify that user can apply filter and table displays relevant results on applying ";
	

	public static ArrayList<SitesLogModel> FieldAccess() {
		ArrayList<SitesLogModel> lstSitesLogModel = new ArrayList<SitesLogModel>();
		SitesLogModel objTmp;
		ReportFilters objFilter;			

		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Site Type Filter";
		objTmp.TestCaseName = "AN-Sites-73: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesSiteTypeCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesSiteType;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesSiteName;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = false;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Street Address Filter";
		objTmp.TestCaseName = "AN-Sites-74: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesStreetAddressCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesStreetAddress;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesSiteType;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "State Filter";
		objTmp.TestCaseName = "AN-Sites-75: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesStateCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesState;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesStreetAddress;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Zip Code Filter";
		objTmp.TestCaseName = "AN-Sites-76: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesZipCodeCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesZipCode;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesState;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Country Filter";
		objTmp.TestCaseName = "AN-Sites-77: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesCountryCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesCountry;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesZipCode;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Latitude Filter";
		objTmp.TestCaseName = "AN-Sites-78: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesLatitudeCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesLatitude;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesCountry;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Longitude Filter";
		objTmp.TestCaseName = "AN-Sites-79: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesLongitudeCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesLongitude;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesLatitude;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Date Created Filter";
		objTmp.TestCaseName = "AN-Sites-80: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesDateCreatedCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesDateCreated;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesLongitude;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Created By Filter";
		objTmp.TestCaseName = "AN-Sites-81: Verify field level accessibility of "+objFilter.FilterName;
		objTmp.TestCaseDescription = "This testcase will Verify field level accessibility of "+objFilter.FilterName;
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesCreatedByCol;
		objTmp.FilterHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesCreatedBy;
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesDateCreated;
		objTmp.unviewAccess = true;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
		
		
		objTmp = new SitesLogModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "Select back all columns";
		objTmp.TestCaseName = "AN-Sites-82: Select back all unselected columns";
		objTmp.TestCaseDescription = "This testcase will select back all unselected columns";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = SitesLogPage.sitesFieldAccessCol;
		objTmp.FilterHideID = "";
		objTmp.FilterUnHideID = SitesLogPage.sitesSortFilter+""+SitesLogPage.sitesDateCreated;
		objTmp.unviewAccess = false;
		objTmp.viewAccess = true;
		objTmp.lstFilters.add(objFilter);
		lstSitesLogModel.add(objTmp);
	
		return lstSitesLogModel;
	}
}


