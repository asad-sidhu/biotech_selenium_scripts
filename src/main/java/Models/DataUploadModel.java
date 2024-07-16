package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class DataUploadModel {


	public String TestCaseName;
	public String TestCaseDescription;
	public String templateName;
	public String fileName;
	public String steps;
	public String passStep;
	public String failStep;
	public String AlertMessage;
	public ArrayList<ReportFilters> lstFilters;
	public boolean ErrorCase;
	public String ErrorMessage;


	public static String OrganizationSiteID = "1001001";   //qa
	public static String RegionSiteID = "1001043";
	public static String SubRegionSiteID = "1001055";
	public static String ComplexSiteID = "1001159";
	public static String HouseSiteID = "10011009";
	public static String ProcessingPlantSiteID = "1001186";
	public static String FarmSiteID = "​1001160";
	public static String SampleMetaDataResultID = "A1521312";
	public static String SampleMetaDataResultID2 = "A1521311";
	public static String ClostridiumMetaDataResultID = "A1241412";
	public static String ClostridiumMetaDataResultID2 = "A1241410";
	public static String AssetLineageParentAssetID = "A01500";
	public static String AssetLineageChildAssetID = "A01499";
	public static String AssetLineageParentAssetdeployment = "08/03/2023";
	public static String AssetLineageChildAssetdeployment = "07/01/2023";
	public static String AssetLineageParentAssetLocation = "House 1";
	public static String AssetLineageChildAssetLocation = "House 1";
	public static String AssetLineageParentAssetLocationExtID = "012H";
	public static String AssetLineageChildAssetLocationExtID = "012H";
	public static String TVBMetaDataResultID = "A1257312";
	public static String TVBMetaDataResultID2 = "A1257310";
	public static String AssetID = "A02491";
	public static String AssetID1 = "A02493";

	public static String AssetFileName = "Asset Metadata.xlsx";
	public static String sitePerformanceFileName = "Weekly Site Performance.xlsx";
	public static String sampleMetadataFileName = "Sample Metadata Upload Template.xlsx";
	public static String ClostridiumTemplateFileName = "Clostridium Template.xlsx";
	public static String BulkAssetLineageTemplateFileName = "Bulk Create Asset Lineage Template.xlsx";
	public static String BulkUserTemplateFileName = "Bulk User Template.xlsx";
	public static String TVBTemplateFileName = "TVB Template.xlsx";

	public static ArrayList<DataUploadModel> lstDataUploadAsset = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSitePerformance = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSampleMetadata = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadClostridiumTemplate = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadBulkAssetLineageTemplate = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadBulkUserTemplate = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadTVBTemplate = new ArrayList<>();
	public static ArrayList<DataUploadModel> lstDataUploadSaveTemplate = new ArrayList<>();

//	public static String OrganizationSiteID = "1001001";   //dev
//	public static String RegionSiteID = "1001043";
//	public static String SubRegionSiteID = "1001052";
//	public static String ComplexSiteID = "1001159";
//	public static String HouseSiteID = "1001188";
//	public static String ProcessingPlantSiteID = "1001186";
//	public static String FarmSiteID = "2002006";
//	public static String ResultID = "A0921201";
//	public static String ResultID2 = "A0921202";
//	public static String AssetID = "";


	public DataUploadModel() {

	}


	public static ArrayList<DataUploadModel> FillData() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();

		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-36: Verify that user cannot upload Asset Metadata without Farm Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata without Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(""));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for FARM_SITE_ID.";
		objTmp.steps = "Leave Site ID field empty and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata without Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata without Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-37: Verify that user cannot upload Asset Metadata without deployment Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata without deployment Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID, ""));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for deployment_DATE.";
		objTmp.steps = "Leave deployment Date field empty and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata without Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata without Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-37: Verify that user cannot upload Asset Metadata with same Asset ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with same";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0, 2, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetID, AssetID, "2022-02-02", "2022-03-03"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.steps = "Enter same Asset id in 2 rows";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with duplicate Asset ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with duplicate Asset ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-37: Verify that user can upload Asset Metadata with Unique Asset ID";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Unique Asset ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetID1));
		objTmp.ErrorCase = false;
		objTmp.steps = "Add unique Asset id";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with unique Asset id successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with unique Asset id";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-40: Verify user cannot upload Asset Metadata with Organization Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Organization Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1, 2, 9));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(OrganizationSiteID, "2022-02-02", "2022-03-03"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.steps = "Enter Organization Site ID and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Organization Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Organization Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-41: Verify user cannot upload Asset Metadata with Region Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Region Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(RegionSiteID));
		objTmp.steps = "Enter Region Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Region Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Region Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-42: Verify user cannot upload Asset Metadata with Sub-Region Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Sub-Region ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SubRegionSiteID));
		objTmp.steps = "Enter Sub-Region Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Sub-Region Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Sub_Region Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-43: Verify user cannot upload Asset Metadata with Complex Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Complex Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ComplexSiteID));
		objTmp.steps = "Enter Complex Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Complex Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Complex Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-44: Verify user cannot upload Asset Metadata with House Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with House Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(HouseSiteID));
		objTmp.steps = "Enter House Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with House Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with House Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-45: Verify user cannot upload Asset Metadata with Processing Plant Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Processing Plant Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ProcessingPlantSiteID));
		objTmp.steps = "Enter Processing Plant Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in FARM_SITE_ID is not a farm type site.";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Processing Plant Site ID successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Processing Plant Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-46: Verify user can upload Asset Metadata with Farm Site ID";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Farm Site ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID));
		objTmp.steps = "Enter Farm Site ID and upload the Asset Metadata";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Asset Metadata with Farm Site ID successfully";
		objTmp.failStep = "User failed to upload Asset Metadata with Farm Site ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-49: Verify that user cannot upload Asset Metadata with deployment Date greater than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with deployment Date greater than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2021-06-01", "2021-06-10"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Date in PROCESSING_DATE is Lesser than or equal to the deployment Date.";
		objTmp.steps = "Enter deployment Date greater than Processing Date and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with deployment Date greater than Processing Date successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with deployment Date greater than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-50: Verify that user can upload Asset Metadata with deployment Date less than Processing Date";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with deployment Date less than Processing Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(9, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("2022-03-03", "2022-02-02"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter deployment Date less than Processing Date and upload the Asset Metadata";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with deployment Date less than Processing Date successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with deployment Date less than Processing Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-51: Verify that user cannot upload Asset Metadata with Organization Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(OrganizationSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter Organization Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Organization Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Organization Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-52: Verify that user cannot upload Asset Metadata with Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(RegionSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter Region Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-53: Verify that user cannot upload Asset Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SubRegionSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter Sub-Region Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Sub-Region Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Sub-Region Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-54: Verify that user cannot upload Asset Metadata with Complex Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ComplexSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter Complex Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Complex Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Complex Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-55: Verify that user cannot upload Asset Metadata with House Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(HouseSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter House Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with House Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with House Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-56: Verify that user cannot upload Asset Metadata with Farm Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(FarmSiteID));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "SiteID in PROCESSING_SITE_ID is not a processing plant type site.";
		objTmp.steps = "Enter Farm Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Farm Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Farm Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-57: Verify that user can upload Asset Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ProcessingPlantSiteID));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Processing Plant Site ID in Processing Site ID field and upload the Asset Metadata";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Processing Plant Site ID in Processing Site ID field successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Processing Plant Site ID in Processing Site ID field";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-58: Verify that user cannot upload Asset Metadata with Number of animals DOA at Plant greater than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Number of animals DOA at Plant greater than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 5));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Value in NUM_animalS_DOA_PLANT is greater than or equal to Number of animals Placed.";
		objTmp.steps = "Enter Number of animals DOA at Plant greater than Number of animals Placed and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Number of animals DOA at Plant greater than Number of animals Placed successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Number of animals DOA at Plant greater than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-59: Verify that user can upload Asset Metadata with Number of animals DOA at Plant less than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Number of animals DOA at Plant less than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(11, 5));
		objTmp.ErrorCase = false;
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "100"));
		objTmp.steps = "Enter Number of animals DOA at Plant less than Number of animals Placed and upload the Asset Metadata";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Asset Metadata with Number of animals DOA at Plant less than Number of animals Placed successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Number of animals DOA at Plant less than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-60: Verify that user cannot upload Asset Metadata with Number of animals Processed greater than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Number of animals Processed greater than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 5));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Value in NUM_animalS_PROCESSED is greater than or equal to Number of animals Placed.";
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80"));
		objTmp.steps = "Enter Number of animals Processed greater than Number of animals Placed and upload the Asset Metadata";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Number of animals Processed greater than Number of animals Placed successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Number of animals Processed greater than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-61: Verify that user can upload Asset Metadata with Number of animals Processed less than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Number of animals Processed less than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 5));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Number of animals Processed less than Number of animals Placed and upload the Asset Metadata";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload Asset Metadata with Number of animals Processed less than Number of animals Placed successfully";
		objTmp.failStep = "User was able to upload Asset Metadata with Number of animals Processed less than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-DU-62: Verify that user cannot upload Asset Metadata with Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 21));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "100"));
//		objTmp.steps = "Enter Total Weight_Condemned_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB and upload the Asset Metadata";
//		objTmp.AlertMessage = "Errors found in "+AssetFileName;
//		objTmp.passStep = "User was not able to upload Asset Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB successfully";
//		objTmp.failStep = "User was able to upload Asset Metadata with Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-DU-63: Verify that user can upload Asset Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(16, 21));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "40"));
//		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB and upload the Asset Metadata";
//		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
//		objTmp.passStep = "User was not able to upload Asset Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB successfully";
//		objTmp.failStep = "User was able to upload Asset Metadata with Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-DU-64: Verify that user cannot upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 20, 22));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "100", "100"));
//		objTmp.steps = "Enter Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG and upload the Asset Metadata";
//		objTmp.AlertMessage = "Errors found in "+AssetFileName;
//		objTmp.passStep = "User was not able to upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG successfully";
//		objTmp.failStep = "User was able to upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-DU-64: Verify that user cannot upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(17, 22));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "200"));
//		objTmp.steps = "Enter Total Weight_LB Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG and upload the Asset Metadata";
//		objTmp.AlertMessage = "Errors found in "+AssetFileName;
//		objTmp.passStep = "User was not able to upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG successfully";
//		objTmp.failStep = "User was able to upload Asset Metadata with Total Weight_KG Condemned Must be <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-65: Verify that user can upload Asset Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(18, 21, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("150", "80", "40"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Total Weight_LB Condemned Must be >= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG and upload the Asset Metadata";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Total Weight_KG Condemned Must be >= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-66: Verify that user cannot upload Asset Metadata with animal Sex other than Male, Female,Mixed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with animal Sex other than Male, Female,Mixed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("test"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Value in animal_SEX is not allowed. Allowable values include Male,Female,Mixed.";
		objTmp.steps = "Enter animal Sex other than male,female, mixed";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with animal Sex other than male,female, mixed successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with animal Sex other than male,female, mixed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-67: Verify that user can upload Asset Metadata with animal Sex as Male";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with animal Sex as Male";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Male"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter animal Sex as Male";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with animal Sex as Male successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with animal Sex as Male";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-68: Verify that user can upload Asset Metadata with animal Sex as Female";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with animal Sex as female";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Female"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter animal Sex as female";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with animal Sex as female successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with animal Sex as female";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-69: Verify that user can upload Asset Metadata with animal Sex as Mixed";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with animal Sex as Mixed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Mixed"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter animal Sex as Mixed";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with animal Sex as mixed successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with animal Sex as Mixed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-70: Verify that user cannot upload Asset Metadata with Marketing Program other than Conventional, No Human Antibiotics, no Anitbiotics Ever, Organic, Pastured, Others";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Asset Metadata with Marketing Program other than Conventional, No Human Antibiotics, no Anitbiotics Ever, Organic, Pastured, Others";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Test"));
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Value in MARKETING_PROGRAM is not allowed. Allowable values include Conventional,No Human Antibiotics,No Antibiotics Ever,Organic,Pastured,Other.";
		objTmp.steps = "Enter Marketing Program other than Conventional, No Human Antibiotics, no Anitbiotics Ever, Organic, Pastured, Others";
		objTmp.AlertMessage = "Errors found in "+AssetFileName;
		objTmp.passStep = "User was not able to upload Asset Metadata with Marketing Program other than Conventional, No Human Antibiotics, no Anitbiotics Ever, Organic, Pastured, Others successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program other than Conventional, No Human Antibiotics, no Anitbiotics Ever, Organic, Pastured, Others";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-71: Verify that user can upload Asset Metadata with Marketing Program as Conventional";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Marketing Program as Conventional";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Conventional"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Marketing Program as Conventional";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Marketing Program as Conventional successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program as Conventional";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-72: Verify that user can upload Asset Metadata with Marketing Program as No Antibiotics Ever";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Marketing Program as No Antibiotics Ever";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("No Antibiotics Ever"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Marketing Program as No Antibiotics Ever";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Marketing Program as No Antibiotics Ever successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program as No Antibiotics Ever";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-73: Verify that user can upload Asset Metadata with Marketing Program as Organic";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Marketing Program as Organic";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Organic"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Marketing Program as Organic";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Marketing Program as Organic successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program as Organic";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-74: Verify that user can upload Asset Metadata with Marketing Program as Pastured";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Marketing Program as Pastured";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Pastured"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Marketing Program as Pastured";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Marketing Program as Pastured successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program as Pastured";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-75: Verify that user can upload Asset Metadata with Marketing Program as Others";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Asset Metadata with Marketing Program as Others";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(39));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Other"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter Marketing Program as Others";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with Marketing Program as Others successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with Marketing Program as Others";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-76: Verify that new animal Size named Pullet can be uploaded through Metadata file.";
		objTmp.TestCaseDescription = "This test case will verify that new animal Size named Pullet can be uploaded through Metadata file.";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(38));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Pullet"));
		objTmp.ErrorCase = false;
		objTmp.steps = "Enter animal Size as Pullet";
		objTmp.AlertMessage = AssetFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Asset Metadata with animal Size as Pullet successfully";
		objTmp.failStep = "User was not able to upload Asset Metadata with animal Size as Pullet";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataSitePerformance() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp = new DataUploadModel();

		ReportFilters objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-66: Verify that user cannot upload Site Performance with Number of animals Sold greater than Number of animals Placed";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with Number of animals Sold greater than Number of animals Placed";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 2, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "80", "2021-12-12"));
		objTmp.steps = "Enter Number of animals Sold greater than Number of animals Placed";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with Number of animals Sold greater than Number of animals Placed successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with Number of animals Sold greater than Number of animals Placed";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-67: Verify that user can upload Site Performance with NUM_animalS_SOLD less than NUM_animalS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with NUM_animalS_SOLD less than NUM_animalS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("80", "100"));
		objTmp.steps = "Enter with NUM_animalS_SOLD less than NUM_animalS_PLACED";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with NUM_animalS_SOLD less than NUM_animalS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with NUM_animalS_SOLD less than NUM_animalS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-68: Verify that user cannot upload Site Performance with DOA_PLANT_PERC greater than NUM_animalS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with DOA_PLANT_PERC greater than NUM_animalS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "80"));
		objTmp.steps = "Enter Number DOA_PLANT_PERC greater than NUM_animalS_PLACED";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with DOA_PLANT_PERC greater than NUM_animalS_PLACED successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with DOA_PLANT_PERC greater than NUM_animalS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-69: Verify that user can upload Site Performance with Percentage DOA less than NUM_animalS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with Percentage DOA less than NUM_animalS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(12, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "100"));
		objTmp.steps = "Enter Percentage DOA less than NUM_animalS_PLACED";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with Percentage DOA less than NUM_animalS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with Percentage DOA less than NUM_animalS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-70: Verify that user cannot upload Site Performance with MORTALITY_NUM_animalS greater than NUM_animalS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with MORTALITY_NUM_animalS greater than NUM_animalS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(15, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "80"));
		objTmp.steps = "Enter MORTALITY_NUM_animalS greater than NUM_animalS_PLACED";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with MORTALITY_NUM_animalS greater than NUM_animalS_PLACED successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with MORTALITY_NUM_animalS greater than NUM_animalS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-71: Verify that user can upload Site Performance with MORTALITY_NUM_animalS less than NUM_animalS_PLACED";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with MORTALITY_NUM_animalS less than NUM_animalS_PLACED";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(15, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("90", "120"));
		objTmp.steps = "Enter MORTALITY_NUM_animalS less than NUM_animalS_PLACED";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with MORTALITY_NUM_animalS less than NUM_animalS_PLACED successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with MORTALITY_NUM_animalS less than NUM_animalS_PLACED";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-72: Verify that user cannot upload Site Performance with Feed Conversion Ratio <1";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with Feed Conversion Ratio <1";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("0.5"));
		objTmp.steps = "Enter Feed Conversion Ratio <1";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with Feed Conversion Ratio <1 successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with Feed Conversion Ratio <1";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-73: Verify that user can upload Site Performance with Feed Conversion Ratio >1";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with Feed Conversion Ratio >1";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(21));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("1.5"));
		objTmp.steps = "Enter Feed Conversion Ratio >1";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with Feed Conversion Ratio >1 successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with Feed Conversion Ratio >1";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-74: Verify that user cannot upload Site Performance with NUM_animalS_CONDEMNED_WHOLE greater than NUM_animalS_SOLD";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with NUM_animalS_CONDEMNED_WHOLE greater than NUM_animalS_SOLD";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "50"));
		objTmp.steps = "Enter NUM_animalS_CONDEMNED_WHOLE greater than NUM_animalS_SOLD";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with NUM_animalS_CONDEMNED_WHOLE greater than NUM_animalS_SOLD successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with NUM_animalS_CONDEMNED_WHOLE greater than NUM_animalS_SOLD";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-75: Verify that user can upload Site Performance with NUM_animalS_CONDEMNED_WHOLE less than NUM_animalS_SOLD";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with NUM_animalS_CONDEMNED_WHOLE less than NUM_animalS_SOLD";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(10, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("50", "100"));
		objTmp.steps = "Enter NUM_animalS_CONDEMNED_WHOLE less than NUM_animalS_SOLD";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with NUM_animalS_CONDEMNED_WHOLE less than NUM_animalS_SOLD successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with NUM_animalS_CONDEMNED_WHOLE less than NUM_animalS_SOLD";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-76: Verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(27, 25, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-77: Verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(27, 25, 23));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_LB <= PARTS_CONDEMNED_LB + animal_CONDEMNED_LB";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-78: Verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(28, 26, 24));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("10", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.AlertMessage = "Errors found in "+sitePerformanceFileName;
		objTmp.passStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG successfully";
		objTmp.failStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-79: Verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.TestCaseDescription = "This test case will verify that user can upload Site Performance with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(28, 26, 24));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("100", "10", "10"));
		objTmp.steps = "Enter TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.AlertMessage = sitePerformanceFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG successfully";
		objTmp.failStep = "User was not able to upload Weekly Site Performance template with TOTAL_WEIGHT_CONDEMNED_KG <= PARTS_CONDEMNED_KG + animal_CONDEMNED_KG";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataSampleMetaData() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-04: Verify that user cannot upload file leaving Key field/s empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file leaving Key field/s empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "A"));
		objTmp.steps = "Leave Key field empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for Result ID.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file leaving Key field empty successfully";
		objTmp.failStep = "User was able to upload file leaving Key field empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-DU-05: Verify that user cannot upload file with case sensitive duplicate values";
//		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with case sensitive duplicate values";
//		objTmp.lstFilters = new ArrayList<>();
//		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2, 2));
//		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A0732302", "A", "a"));
//		objTmp.steps = "Enter duplicate values as case sensitive";
//		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
//		objTmp.passStep = "User was not able to upload file with case sensitive duplicate values successfully";
//		objTmp.failStep = "User was able to upload file with case sensitive duplicate values";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-06: Verify that user can upload file with entering valid data in Key field/s";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with entering valid data in Key field/s";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID, "A"));
		objTmp.steps = "Enter valid data in Key field/s";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID, SampleMetaDataResultID));
		objTmp.steps = "Enter same value in Key field/s for 2 rows";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with values in Key field/s not being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s not being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-08: Verify that user can upload file with values in Key field/s being unique";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with values in Key field/s being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID, SampleMetaDataResultID2));
		objTmp.steps = "Enter unique values for Key field";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with values in Key field/s being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-09: Verify that user cannot upload file with String datatype in Decimal datatype";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with String datatype in Decimal datatype";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A"));
		objTmp.steps = "Enter string value in decimal field";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Value in Run Lane is not valid.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with String datatype in Decimal datatype successfully";
		objTmp.failStep = "User was able to upload file with String datatype in Decimal datatype";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-10: Verify that user can upload file with int datatype in Decimal datatype";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with int datatype in Decimal datatype";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("1"));
		objTmp.steps = "Enter string value in decimal field";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with String datatype in Decimal datatype successfully";
		objTmp.failStep = "User was able to upload file with String datatype in Decimal datatype";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-11: Verify that user cannot upload file with site id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with site id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID, "100900112"));
		objTmp.steps = "Enter invalid site id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Site ID does not exists in this organization.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with site id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with site id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-12: Verify that user cannot upload file with result id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A1286705789", ""));
		objTmp.steps = "Enter invalid result id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "ResultId in Result ID does not exists in system.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with result id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with result id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-13: Verify that user cannot upload file with result id being empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id being empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", ""));
		objTmp.steps = "Leave result id empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for Result ID.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with result id being empty successfully";
		objTmp.failStep = "User was able to upload file with result id being empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-14: Verify that user cannot upload file with duplicate result id";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with duplicate result id";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID2, SampleMetaDataResultID2));
		objTmp.steps = "Enter duplicate result id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.AlertMessage = "Errors found in "+sampleMetadataFileName;
		objTmp.passStep = "User was not able to upload file with duplicate result id successfully";
		objTmp.failStep = "User was able to upload file with duplicate result id";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-15: Verify that user can upload file with decimal value in Decimal datatype";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with decimal value in Decimal datatype";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 22));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(SampleMetaDataResultID, "1.5"));
		objTmp.steps = "Enter decimal value in Decimal datatype";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = sampleMetadataFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with decimal value in Decimal datatype successfully";
		objTmp.failStep = "User was able to upload file with decimal value in Decimal datatype";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}



	public static ArrayList<DataUploadModel> FillDataClostridiumTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-04: Verify that user cannot upload file leaving Key field/s empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file leaving Key field/s empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "A"));
		objTmp.steps = "Leave Key field empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for RESULT_ID.";
		objTmp.AlertMessage = "Errors found in "+ClostridiumTemplateFileName;
		objTmp.passStep = "User was not able to upload file leaving Key field empty successfully";
		objTmp.failStep = "User was able to upload file leaving Key field empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-06: Verify that user can upload file with entering valid data in Key field/s";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with entering valid data in Key field/s";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ClostridiumMetaDataResultID, "A"));
		objTmp.steps = "Enter valid data in Key field/s";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = ClostridiumTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ClostridiumMetaDataResultID, ClostridiumMetaDataResultID));
		objTmp.steps = "Enter same value in Key field/s for 2 rows";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.AlertMessage = "Errors found in "+ClostridiumTemplateFileName;
		objTmp.passStep = "User was not able to upload file with values in Key field/s not being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s not being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-08: Verify that user can upload file with values in Key field/s being unique";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with values in Key field/s being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ClostridiumMetaDataResultID, ClostridiumMetaDataResultID2));
		objTmp.steps = "Enter unique values for Key field";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = ClostridiumTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with values in Key field/s being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-11: Verify that user cannot upload file with site id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with site id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 19));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(ClostridiumMetaDataResultID, "100900112"));
		objTmp.steps = "Enter invalid site id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Collection site id does not exists.";
		objTmp.AlertMessage = "Errors found in "+ClostridiumTemplateFileName;
		objTmp.passStep = "User was not able to upload file with site id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with site id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-12: Verify that user cannot upload file with result id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 19));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A1286705789", ""));
		objTmp.steps = "Enter invalid result id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Result id does not exists.";
		objTmp.AlertMessage = "Errors found in "+ClostridiumTemplateFileName;
		objTmp.passStep = "User was not able to upload file with result id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with result id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-13: Verify that user cannot upload file with result id being empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id being empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 19));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", ""));
		objTmp.steps = "Leave result id empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for RESULT_ID.";
		objTmp.AlertMessage = "Errors found in "+ClostridiumTemplateFileName;
		objTmp.passStep = "User was not able to upload file with result id being empty successfully";
		objTmp.failStep = "User was able to upload file with result id being empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataBulkAssetLineageTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "ASD-218: Verify successful establishment of parent-child relationships using Integrator Asset ID";
		objTmp.TestCaseDescription = "Verify successful establishment of parent-child relationships using Integrator Asset ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetID, AssetLineageChildAssetID));
		objTmp.steps = "Enter Valid Data in Integrator Asset ID fields.";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "ASD-219: Verify successful establishment of parent-child relationships using Location Name + deployment Date.";
		objTmp.TestCaseDescription = "Verify successful establishment of parent-child relationships using Location Name + deployment Date.";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 4, 5, 7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetLocation, AssetLineageParentAssetdeployment, AssetLineageChildAssetLocation, AssetLineageChildAssetdeployment));
		objTmp.steps = "Enter valid location names and deployments";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify successful establishment of parent-child relationships using Location External ID + deployment Date.";
		objTmp.TestCaseDescription = "Verify successful establishment of parent-child relationships using Location External ID + deployment Date.";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 4, 6, 7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetLocationExtID, AssetLineageParentAssetdeployment, AssetLineageChildAssetLocationExtID , AssetLineageParentAssetdeployment));
		objTmp.steps = "Enter valid External ID's and deployments";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to-upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "Verify unsuccessful establishment of parent-child relationships using Invalid Integrator Asset ID";
		objTmp.TestCaseDescription = "Verify unsuccessful establishment of parent-child relationships using Invalid Integrator Asset ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A13214343", AssetLineageChildAssetID));
		objTmp.steps = "Enter invalid values for Integrator Asset ID";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Integrator parent Asset id does not exists.";
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with invalid Integrator Asset ID";
		objTmp.failStep = "User was able to upload file with invalid Integrator Asset ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "Verify unsuccessful establishment of parent-child relationships using Invalid deployment Date";
		objTmp.TestCaseDescription = "Verify unsuccessful establishment of parent-child relationships using Invalid deployment Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 4, 5, 7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetLocation, "10/10/2099", AssetLineageChildAssetLocation, AssetLineageChildAssetdeployment));
		objTmp.steps = "Enter invalid values for Parent deployment Date";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "parent Asset deployment date does not exists.";
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with invalid Parent deployment Date";
		objTmp.failStep = "User was able to upload file with invalid Parent deployment Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataBulkUserTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "IE-11058: Verify that, if an email address outside the added organization's domain is added validation should be shown";
		objTmp.TestCaseDescription = "Verify that, if an email address outside the added organization's domain is added validation should be shown";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2, 3, 4, 12, 16));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("Project123", "Project123", "Asad", "Sidhu", "xxxxxxxxxxxxxxxxx.pk", "Admin"));
		objTmp.steps = "Enter inValid email domain.";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Domain not found in the respective Organization.";
		objTmp.AlertMessage = BulkUserTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "IE-11057: Verify that,There must be a mandatory check on Template Key columns while creating/updating users in bulk.";
		objTmp.TestCaseDescription = "Verify that,There must be a mandatory check on Template Key columns while creating/updating users in bulk.";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2, 3, 4, 12, 16));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("","","","","",""));
		objTmp.steps = "Enter valid location names and deployments";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify successful establishment of parent-child relationships using Location External ID + deployment Date.";
		objTmp.TestCaseDescription = "Verify successful establishment of parent-child relationships using Location External ID + deployment Date.";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(3, 4, 6, 7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetLocationExtID, AssetLineageParentAssetdeployment, AssetLineageChildAssetLocationExtID , AssetLineageParentAssetdeployment));
		objTmp.steps = "Enter valid External ID's and deployments";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to-upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "Verify unsuccessful establishment of parent-child relationships using Invalid Integrator Asset ID";
		objTmp.TestCaseDescription = "Verify unsuccessful establishment of parent-child relationships using Invalid Integrator Asset ID";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 1));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A13214343", AssetLineageChildAssetID));
		objTmp.steps = "Enter invalid values for Integrator Asset ID";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Integrator parent Asset id does not exists.";
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with invalid Integrator Asset ID";
		objTmp.failStep = "User was able to upload file with invalid Integrator Asset ID";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "Verify unsuccessful establishment of parent-child relationships using Invalid deployment Date";
		objTmp.TestCaseDescription = "Verify unsuccessful establishment of parent-child relationships using Invalid deployment Date";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(2, 4, 5, 7));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(AssetLineageParentAssetLocation, "10/10/2099", AssetLineageChildAssetLocation, AssetLineageChildAssetdeployment));
		objTmp.steps = "Enter invalid values for Parent deployment Date";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "parent Asset deployment date does not exists.";
		objTmp.AlertMessage = BulkAssetLineageTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with invalid Parent deployment Date";
		objTmp.failStep = "User was able to upload file with invalid Parent deployment Date";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataTVBTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-04: Verify that user cannot upload file leaving Key field/s empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file leaving Key field/s empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 2));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", "A"));
		objTmp.steps = "Leave Key field empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for RESULT_ID.";
		objTmp.AlertMessage = "Errors found in "+TVBTemplateFileName;
		objTmp.passStep = "User was not able to upload file leaving Key field empty successfully";
		objTmp.failStep = "User was able to upload file leaving Key field empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-06: Verify that user can upload file with entering valid data in Key field/s";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with entering valid data in Key field/s";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 3));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(TVBMetaDataResultID, "A"));
		objTmp.steps = "Enter valid data in Key field/s";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = TVBTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was able to upload file with entering valid data in Key field/s successfully";
		objTmp.failStep = "User was not able to upload file with entering valid data in Key field/s";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-07: Verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with values in Key field/s not being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(TVBMetaDataResultID, TVBMetaDataResultID));
		objTmp.steps = "Enter same value in Key field/s for 2 rows";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Repetition found in Key Field.";
		objTmp.AlertMessage = "Errors found in "+TVBTemplateFileName;
		objTmp.passStep = "User was not able to upload file with values in Key field/s not being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s not being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-08: Verify that user can upload file with values in Key field/s being unique";
		objTmp.TestCaseDescription = "This test case will verify that user can upload file with values in Key field/s being unique";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 0));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(TVBMetaDataResultID, TVBMetaDataResultID2));
		objTmp.steps = "Enter unique values for Key field";
		objTmp.ErrorCase = false;
		objTmp.AlertMessage = TVBTemplateFileName+" loaded successfully.";
		objTmp.passStep = "User was not able to upload file with values in Key field/s being unique successfully";
		objTmp.failStep = "User was able to upload file with values in Key field/s being unique";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-11: Verify that user cannot upload file with site id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with site id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 24));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList(TVBMetaDataResultID, "100900112"));
		objTmp.steps = "Enter invalid site id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Collection site id does not exists.";
		objTmp.AlertMessage = "Errors found in "+TVBTemplateFileName;
		objTmp.passStep = "User was not able to upload file with site id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with site id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-12: Verify that user cannot upload file with result id that does not exist";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id that does not exist";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 19));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("A1286705789", ""));
		objTmp.steps = "Enter invalid result id";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "Result id does not exists.";
		objTmp.AlertMessage = "Errors found in "+TVBTemplateFileName;
		objTmp.passStep = "User was not able to upload file with result id that does not exist successfully";
		objTmp.failStep = "User was able to upload file with result id that does not exist";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-DU-13: Verify that user cannot upload file with result id being empty";
		objTmp.TestCaseDescription = "This test case will verify that user cannot upload file with result id being empty";
		objTmp.lstFilters = new ArrayList<>();
		objFilter.LstColumnID = new ArrayList<>(Arrays.asList(0, 19));
		objFilter.LstColumnValues = new ArrayList<>(Arrays.asList("", ""));
		objTmp.steps = "Leave result id empty";
		objTmp.ErrorCase = true;
		objTmp.ErrorMessage = "No value provided for RESULT_ID.";
		objTmp.AlertMessage = "Errors found in "+TVBTemplateFileName;
		objTmp.passStep = "User was not able to upload file with result id being empty successfully";
		objTmp.failStep = "User was able to upload file with result id being empty";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


	public static ArrayList<DataUploadModel> FillDataSaveTemplate() {
		ArrayList<DataUploadModel> lstDataUploadModel = new ArrayList<DataUploadModel>();
		DataUploadModel objTmp;
		ReportFilters objFilter;

//		objTmp = new DataUploadModel();
//		objFilter = new ReportFilters();
//		objTmp.templateName = "Asset Metadata";
//		objTmp.TestCaseName = "AN-DU-22: Verify that user can save "+objTmp.templateName;
//		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
//		objTmp.lstFilters = new ArrayList<>();
//		objTmp.fileName = "Asset Metadata.xlsx";
//		objTmp.steps = "Upload Asset Metadata";
//		objTmp.lstFilters.add(objFilter);
//		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.templateName = "Sample Metadata";
		objTmp.TestCaseName = "AN-DU-23: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Sample Metadata Upload Template.xlsx";
		objTmp.steps = "Upload Sample Metadata";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		objTmp = new DataUploadModel();
		objFilter = new ReportFilters();
		objTmp.templateName = "Weekly Site Performance";
		objTmp.TestCaseName = "AN-DU-24: Verify that user can save "+objTmp.templateName;
		objTmp.TestCaseDescription = "This test case will verify that user can save "+objTmp.templateName;
		objTmp.lstFilters = new ArrayList<>();
		objTmp.fileName = "Weekly Site Performance.xlsx";
		objTmp.steps = "Upload Weekly Site Performance";
		objTmp.lstFilters.add(objFilter);
		lstDataUploadModel.add(objTmp);

		return lstDataUploadModel;
	}


}

