package Models;

import java.util.ArrayList;
import java.util.Arrays;

import MiscFunctions.DateUtil;
import static PageObjects.AssetManagementPage.*;


public class AssetManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public ArrayList<ReportFilters> lstFilters;
	public boolean errorCase;
	public String steps;
	public String input;

	public AssetManagementModel() {

	}

//	public static String AssetIntegratorID = "20240415-0037";
	public static String AssetIntegratorID = DateUtil.dateYYYYMMDD+"-"+DateUtil.date0;
	public static String AssetBreedName = "Canary";
	public static String AssetHatcheryName = "Hubbard";
	public static String AssetProgramName = "SinoVac-"+DateUtil.date0;
//	public static String AssetProgramName = "SinoVac-0337";
	public static String AssetanimalSize = "Small";
	public static String AssetanimalSex = "Male";
	public static int totalColumnsinAsset = 56;


	public static ArrayList<AssetManagementModel> EditAsset() {
		ArrayList<AssetManagementModel> lstAssetManagementModel = new ArrayList<AssetManagementModel>();
		AssetManagementModel objTmp;
		ReportFilters objFilter;			

//		objTmp = new AssetManagementModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "Integrator Site ID";
//		objTmp.TestCaseName = "AN-FR-99: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
//		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = Test_Elements.AssetIntegratorID;
//		objFilter.ColumnID = Test_Elements.AssetIntegratorIDCol;
//		objTmp.input = "IntegratorID_TA";
//		objTmp.steps = "Edit "+objFilter.FilterName;
//		objTmp.lstFilters.add(objFilter);
//		lstAssetManagementModel.add(objTmp);

//		objTmp = new AssetManagementModel();
//		objFilter = new ReportFilters();
//		objFilter.FilterName = "animal Type";
//		objTmp.TestCaseName = "AN-FR-100: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
//		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter = new ReportFilters();
//		objFilter.FilterID = Test_Elements.AssetanimalType;
//		objFilter.ColumnID = Test_Elements.AssetanimalTypeCol;
//		objTmp.input = "Chicken";
//		objTmp.steps = "Edit "+objFilter.FilterName;
//		objTmp.lstFilters.add(objFilter);
//		lstAssetManagementModel.add(objTmp);

		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "animal Sex";
		objTmp.TestCaseName = "AN-FR-101: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
		objFilter.FilterID = AssetanimalSex;
		objFilter.ColumnID = AssetanimalSexdeploymentCol;
		objTmp.input = "Male";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);

		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objFilter.FilterName = "animal Breed";
		objTmp.TestCaseName = "AN-FR-102: Verify user can edit "+objFilter.FilterName+" and it is reflected in audit log";
		objTmp.TestCaseDescription = "This testcase will verify that user can edit "+objFilter.FilterName+" and a new row appears in audit log with changes made";
		objTmp.lstFilters = new ArrayList<>();
		objFilter = new ReportFilters();
	//	objFilter.FilterID = Test_Elements.AssetanimalBreed;
		objFilter.ColumnID = AssetanimalBreeddeploymentCol;
		objTmp.input = "Australorp";
		objTmp.steps = "Edit "+objFilter.FilterName;
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);

		return lstAssetManagementModel;
	}


	public static ArrayList<AssetManagementModel> AssetValidation() {
		ArrayList<AssetManagementModel> lstAssetManagementModel = new ArrayList<AssetManagementModel>();
		AssetManagementModel objTmp;
		ReportFilters objFilter;		

		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-47: Verify that error appears when total weight condemend is less than animals weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend is less than animals weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000", DataUploadModel.FarmSiteID));
	//	objFilter.ColumnID = Test_Elements.AssetTotalWeightCondemnedKGCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than animals weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-48: Verify that no error appears when total weight condemend is less than animals weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that no error appears when total weight condemend is less than animals weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
	//	objFilter.ColumnID = Test_Elements.AssetTotalWeightCondemnedKGCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned kg less than animals weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
		
		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-49: Verify that error appears when total weight condemend lb is less than animals weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lb is less than animals weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("200", "2000"));
	//	objFilter.ColumnID = Test_Elements.AssetTotalWeightCondemnedLBCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter total weight condemned kg less than animals weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that error appears when total weight condemend lb is less than animals weight and parts weights";
		objTmp.TestCaseDescription = "This test case will verify that error appears when total weight condemend lbis less than animals weight and parts weights";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2500", "300"));
	//	objFilter.ColumnID = Test_Elements.AssetTotalWeightCondemnedLBCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter total weight condemned lb less than animals weight and parts weights";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-50: Verify that system should not accept Number of animals Processed greater than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of animals Processed greater than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "20"));
	//	objFilter.ColumnID = Test_Elements.AssetNumanimalsProcessedCol;
		objTmp.errorCase = true;
		objTmp.steps = "Enter Number of animals Processed greater than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
		objTmp = new AssetManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-FR-51: Verify that system accept Number of animals Processed less than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of animals Processed less than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(5, 12));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("20", "10"));
//		objFilter.ColumnID = Test_Elements.AssetNumanimalsProcessedCol;
		objTmp.errorCase = false;
		objTmp.steps = "Enter Number of animals Processed less than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstAssetManagementModel.add(objTmp);
		
//		objTmp = new AssetManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-FR-52: Verify that system should not accept Number of animals Processed greater than Number of animals Placed";
//		objTmp.TestCaseDescription = "This test case will verify that system should not accept Number of animals Processed greater than Number of animals Placed";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(13, 5));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "50"));
//		objFilter.ColumnID = Test_Elements.AssetNumanimalsProcessedCol;
//		objTmp.errorCase = true;
//		objTmp.steps = "Enter Number of animals Processed greater than Number of animals Placed";
//		objTmp.lstFilters.add(objFilter);
//		lstAssetManagementModel.add(objTmp);
//		
//		objTmp = new AssetManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-FR-53: Verify that system accept Number of animals Processed less than Number of animals Placed";
//		objTmp.TestCaseDescription = "This test case will verify that system should accept Number of animals Processed less than Number of animals Placed";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(13, 5));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "20"));
//		objFilter.ColumnID = Test_Elements.AssetNumanimalsProcessedCol;
//		objTmp.errorCase = false;
//		objTmp.steps = "Enter Number of animals Processed less than Number of animals Placed";
//		objTmp.lstFilters.add(objFilter);
//		lstAssetManagementModel.add(objTmp);
		
		return lstAssetManagementModel;
	}
	
	

}