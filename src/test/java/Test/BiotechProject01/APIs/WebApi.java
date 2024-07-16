package Test.BiotechProject01.APIs;

import MiscFunctions.DB_Config_DB;
import MiscFunctions.DB_Config_DW;
import PageObjects.WebApiPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.*;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.Queries.*;
import static MiscFunctions.Queries.getHouseIDAssignedToUser;
import static PageObjects.WebApiPage.*;



public class WebApi {

	@BeforeSuite
	public static void setUp() {
		initReport("WEB_API_CreateAsset_Report");
	}

	@BeforeTest
	public void dbConnect() {
		DB_Config_DB.test();
		DB_Config_DW.test();
	}


	@Test(enabled = true, priority = 1)
	public static void CreateAssetWithAllAttributesAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetWithAllAttributes(getanimalSizeID(animalName), animalName);
	}


	@Test(enabled = true, priority = 2)
	public static void GetAssetByIDAPI() throws IOException {
		WebApiPage wap = new WebApiPage();
		wap.getAssetByID();
	}


	@Test(enabled = true, priority = 3)
	public static void GetAssetWithInvalidIDAPI() throws IOException {
		WebApiPage wap = new WebApiPage();
		wap.AssetInvalidID();
	}


	@Test(enabled = true, priority = 4)
	public static void CreateAssetWithInvalidFarmIDAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10259/10263: Verify user cannot create Asset with invalid farm id and verify the response",
				101444, getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "2023-06-25", "2023-06-26",
				"2023-06-06","Farm: 101444 does not exist.");
	}


	@Test(enabled = true, priority = 5)
	public static void CreateAssetWithInvalidanimalSizeIDAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10262: Verify that the animal size exist on which the user is creating new Asset",
				getHouseParentSiteId(), 11252, getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "2023-06-25", "2023-06-26",
				"2023-06-06","animalSize: 11252 does not exist.");
	}


	@Test(enabled = true, priority = 6)
	public static void CreateAssetWithFarmNotAssignedAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		 wap.CreateAssetAPIValidationChecks("IE-10343: Verify user cannot create Asset with farm id not assigned to user",
				getFarmIDNotAssignedtoUser(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "2023-06-25", "2023-06-26",
				"2023-06-06","One or more house doesn't exist under Farm");
	}


	@Test(enabled = true, priority = 7)
	public static void CreateAssetWithInvalidHouseIDAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10264: Verify user cannot create Asset with invalid house id and verify the response",
				getHouseParentSiteId(), getanimalSizeID(animalName), 55101452, getProcessingSiteIDAssignedToUser(), "2023-06-25", "2023-06-26",
				"2023-06-06","House 55101452 does not exist.");
	}


	@Test(enabled = true, priority = 8)
	public static void CreateAssetWithEstimatedDateGreaterThandeploymentDateAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10265: Verify user cannot create Asset with estimated date less than deployment data",
				getHouseParentSiteId(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "2023-05-25", "2023-04-25",
				"2023-06-06","Estimated Processing Date: '2023-04-25' should be greater than deployment Date: '2023-05-25'");
	}


	@Test(enabled = true, priority = 9)
	public static void CreateAssetWithSamedeploymentDateAndHouseIDAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10266: Verify deploymentDate and house shall be unique for a specific Asset if deploymentDate and house are not unique return validation error while creating Asset by running \"/Assets\" API",
				getHouseParentSiteId(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), getLastYearDate(), "2024-06-25",
				"2023-06-06","Asset already exists on one or more house.");
	}

	@Test(enabled = true, priority = 10)
	public static void CreateAssetWithInvalidDateFormatAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10267: Verify user cannot create Asset with date format other than yyyy-MM-dd",
				getHouseParentSiteId(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "23-05-2023", "25-05-2023",
				"2023-06-06","deployment date is not in Format (yyyy-MM-dd)");
	}


	@Test(enabled = true, priority = 11)
	public static void CreateAssetWithProcessingSiteAssignedToUserAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10268: Verify user cannot create Asset with processing site assigned to user by running \"/Assets\" API",
				getHouseParentSiteId(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), getProcessingSiteIDAssignedToUser(), "2023-05-28", "2023-05-29",
				"2023-06-06","Asset already exists on one or more house.");
	}


	@Test(enabled = false, priority = 12)  //validation removed on processing site id
	public static void CreateAssetWithInvalidProcessingSiteAPI() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.CreateAssetAPIValidationChecks("IE-10269: Verify user cannot create Asset on processing site that does not exists on \"/Assets\" API",
				getHouseParentSiteId(), getanimalSizeID(animalName), getHouseIDAssignedToUser(), 18702535, "2023-05-24", "2023-05-25",
				"2023-06-06","Asset Settlements has invalid Processing Site IDs.");
	}


	@Test(enabled = true, priority = 13)
	public static void CreateAssetWithInvalidProductionTypeID() throws SQLException, IOException {
		WebApiPage wap = new WebApiPage();
		wap.createAssetWithInvalidProductionType(getanimalSizeID(animalName), animalName, dateYMD, 11215);
	}



	@AfterTest
	public void endreport() throws Exception {
		extent.flush();
	}

}
