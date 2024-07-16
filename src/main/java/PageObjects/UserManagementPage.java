package PageObjects;

import Config.BaseTest;
import static MiscFunctions.Constants.url_userManagement;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static Models.UserManagementModel.createUserEmail;
import static PageObjects.BasePage.ResultsCount;
import static PageObjects.BasePage.loading_cursor;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MiscFunctions.DB_Config_DB;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import Config.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;


public class UserManagementPage {


	public static By userManagementTitle = By.id("User Management");
	public static By usercreateButton = By.id("create-user");
	public static By userFirstNameInput = By.id("firstNameId");
	public static By userLastNameInput = By.id("lastNameId");
	public static By userEmailInput = By.id("emailId");
	public static By userOrgTypeDropDownExpand = By.cssSelector("#orgTypeId .ng-arrow-wrapper");
	public static By userOrgTypeInput = By.cssSelector("#orgTypeId input");
	public static By userOrgDropDownExpand = By.cssSelector("#organizationId .ng-arrow-wrapper");
	public static By userOrgInput = By.cssSelector("#organizationId input");
	public static By siteAdministratorToggle = By.cssSelector("#site-administrator .row");
	public static By userRoleCategory = By.id("roleCategoryId");
	public static By userRoleCategoryInput = By.cssSelector("#roleCategoryId input");
	public static By systemRolesExpand = By.cssSelector("#rolesId .ng-arrow-wrapper");
	public static By systemRoleSelect1 = By.cssSelector(".ng-dropdown-panel .ng-option:nth-child(2) label");
	public static By systemRolesSelect = By.xpath("//*[@id=\"rolesId\"]//div[2]/input");
	public static By clearSystemRoles = By.cssSelector("#rolesId > div span.ng-clear-wrapper.ng-star-inserted");
	public static By systemRolesSelected = By.cssSelector("#rolesId .ng-option-selected");
	public static By reportRoleExpand = By.cssSelector("#reportRoleId .ng-arrow-wrapper");
	public static By reportRoleSelect = By.xpath("//*[@id=\"reportRoleId\"]//input");
	public static By reportRoleGetValue = By.cssSelector("#reportRoleId .ng-value-label");
	public static By AgreeementExpand = By.cssSelector("#euladdl .ng-arrow-wrapper");
	public static By AgreementSelect = By.xpath("//*[@id=\"euladdl\"]//div[2]/input");
	public static By getOrgName = By.cssSelector("#organizationId .ng-value-label");
	public static By orgDomains = By.cssSelector("#domains");
	public static By enterAllowDomain = By.cssSelector("#domains input[type=text]");

	public static By AcceptAgreementonLogin = By.xpath("//*[text()= ' Accept ']");

	public static By openUserSites = By.cssSelector(".btn-sites");
	public static By selectTestingSites = By.id("select-testing-sites");
	public static By selectColletionSites = By.id("select-collection-sites");

	public static By saveUserSites = By.id("btn-ok-sites");

	public static By editSearchedUser = By.id("edit-user-1");
	public static By deleteSearchedUser = By.id("delete-user-1");
	public static By agreeementSearchedUser = By.id("view-agreements-1");
	public static By agreementList = By.xpath("//*[@id=\"manage-user\"]//app-user-license-log//tbody/tr[1]");
	public static By enterNewPassword = By.id("passwordId");
	public static By enterConfirmPassword = By.id("rePassordId");
	public static By clickPasswordButton = By.cssSelector("button.apl-btn");

	public static String userFirstNameCol = "0";
	public static String userLastNameCol = "1";
	public static String userMobileNoCol = "2";
	public static String userEmailCol = "3";
	public static String userOrgTypeCol = "4";
	public static String userOrgCol = "5";
	public static String userRoleCol = "6";
	public static String userReportingCol = "7";
	public static String userSiteAccessCol = "8";

	public static String userManagementTable = "manage-user";
	public static String userCSVFileName = "Users Log - ";
	public static String userCSVAuditFileName = "User Management Audit Log - ";
	public static By userTitle = By.id("User Management");
	public static String userFirstName = "userFirstName";
	public static String userLastName = "userLastName";
	public static String userMobileNo = "cfCellPhoneNo";
	public static String userEmail = "userEmail";
	public static String userOrgType = "orgnTypeName";
	public static String userOrg = "orgnName";
	public static String userRole = "cfRoles";
	public static String userReporting = "reportingRole";
	public static String userSiteAccess = "sites";
	public static By userSitesSearch = By.xpath("//*[@placeholder='Filter']");
	public static By userSitesButton = By.cssSelector(".btn-sites");
	public static By userSitesSaveButton = By.id("btn-ok-sites");

	public static By alertClose = By.xpath("//*[@id=\"alrt\"]/button/span");
	public static By firstRow = By.xpath("//table/tbody/tr[1]");
	public static String getUserLog = "//*[@id='table-user-log']//tbody//tr[1]//td";
	public static By clearFilter = By.id("userEmail_clear-filter");
	public static By resetFilter = By.id("reset-all-filters");
	public static By userCellNo = By.id("cellCodeId");
	public static By userCellNoInput = By.cssSelector("#cellCodeId input");
	public static By userPhoneNo = By.cssSelector("#cellNumberId input");
	public static By selectOrganizationTypeError = By.xpath("//div[contains(text(),'Select organization type')]");

	public static By selectOrganizationError = By.xpath("(//div[contains(text(),'Select organization')])[2]");
	public static By emailFieldError = By.xpath("//div[contains(text(),'This email is invalid. Enter correct email address')]");
	public static By fieldAccess = By.cssSelector("#manage-user #edit-field-access");
	public static By inlineEditIcon = By.cssSelector("#manage-user #edit-inline-access");
	public static By inlineEditIconSaveChanges = By.cssSelector("#manage-user #edit-inlineSave-access");

	public static By reportingColumnFilter = By.cssSelector("#reportingRole_show-filter");
	public static By tableRows = By.cssSelector("#" + userManagementTable + " #" + ResultsCount);
	public static By reportingColumnValue1 = By.xpath("//ul[@id='ul-reportingRole']/li[last()]//small");
	public static By reportingColumnValue2 = By.xpath("(//ul[@id='ul-reportingRole']/li[last() - 1]//small)[last()]");
	public static By reportingColumnValue3 = By.xpath("(//ul[@id='ul-reportingRole']/li[last() - 2]//small)[last()]");
	public static By removeColumn = By.cssSelector("tr:nth-child(8) td:nth-child(4) label .rpt-fields");


	public static By selectRoleColumnValue1 = By.xpath("//ul[@id='ul-cfRoles']/li[last()]//small");
	public static By selectRoleColumnValue2 = By.xpath("(//ul[@id='ul-cfRoles']/li[last() - 1]//small)[last()]");
	public static By selectRoleColumnValue3 = By.xpath("(//ul[@id='ul-cfRoles']/li[last() - 2]//small)[last()]");
	public static String userShowFilter = "_show-filter";
	public static String viewAll = "_view-all";
	public static String userApplyFilter = "_apply";
	public static String userClearFilter = "_clear";

	public static String userReportingColumn = "reportingRole";
	public static String userRoleColumn = "cfRoles";



	public void clickCreateButton() throws InterruptedException {
		click(usercreateButton);
		Thread.sleep(2000);
	}
	public static void openEditUserPopup(String emailAddress) throws InterruptedException, IOException {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_userManagement);
		waitElementInvisible(loading_cursor);
		waitElementVisible(UserManagementPage.usercreateButton);
		Thread.sleep(3000);
		click(By.id("userEmail_show-filter"));
		waitElementInvisible(loading_cursor);
		type(By.id("userEmail_search-input"), emailAddress);
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		click(By.id(emailAddress));
		waitElementInvisible(loading_cursor);
		Thread.sleep(1000);
		click(By.id("userEmail_apply"));
		waitElementInvisible(loading_cursor);
		Thread.sleep(6000);
		getScreenshot();
		scroll(By.id("edit-user-1"));
		getScreenshot();
		Thread.sleep(5000);
		try {
			click(By.cssSelector("#edit-user-1 img"));
		}
		catch (StaleElementReferenceException ex) {
			click(By.cssSelector("#edit-user-1 img"));
		}
		waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
	}

	public static boolean verifyUserDataSanity(String createUserEmail, String userLogRow) throws SQLException {
		try {
			BaseTest driver = new BaseTest();
			SoftAssert softAssert = new SoftAssert();
			String userLogQuery = "SELECT U.userFirstName AS [First Name], U.userLastName AS [Last Name], ISNULL((con.countryPhoneCode + U.userPhoneNo),'') AS [phone Number], U.userEmail AS [Email],\n" +
					"LP.[name] AS [Organization Type], ORG.[orgnName] AS [Organization], STRING_AGG(RL.userRoleName, ', ') AS [Role], RR.reportRoleName AS [Reporting],\n" +
					"(\n" +
					"SELECT CASE WHEN SUM(ids) > 0 THEN 'Active' ELSE 'Inactive' END sites  \n" +
					"FROM\n" +
					"(\n" +
					"SELECT SUM(1) ids from abcSiteAssn WHERE userId = U.userId AND isActive = 1 AND isDeleted = 0\n" +
					"UNION ALL\n" +
					"SELECT SUM(1) ids FROM ClientSiteAssn WHERE userId = U.userId AND isActive = 1 AND isDeleted = 0\n" +
					"UNION ALL\n" +
					"SELECT SUM(1) ids from abcTestSitesAssn WHERE userId = U.userId AND isActive = 1 AND isDeleted = 0  \n" +
					") A\n" +
					") AS [Sites Access],\n" +
					"ISNULL(U.roleCategoryName, '') AS [Role Category],\n" +
					"CASE WHEN U.isActive = 1 AND U.isDeleted = 0 THEN 'Active' ELSE 'Inactive' END AS [Status]\n" +
					"FROM dbo.[user] U \n" +
					"LEFT JOIN dbo.Country con ON con.countryId = U.userPhoneCodeId\n" +
					"INNER JOIN LookUp LP ON LP.id = U.userOrgnTypeId\n" +
					"INNER JOIN Organization ORG ON ORG.orgnId = U.userOrgnId\n" +
					"INNER JOIN UserRoleAssn URA ON URA.userId = U.userId AND URA.isActive = 1 AND URA.isDeleted = 0\n" +
					"INNER JOIN [Role] RL ON RL.userRoleId = URA.roleId\n" +
					"LEFT JOIN UserReportRoleAssn URRA ON URRA.userId = U.userId AND URRA.isActive = 1 AND URRA.isDeleted = 0\n" +
					"INNER JOIN ReportRole RR ON RR.reportRoleID = URRA.reportRoleID\n" +
					"WHERE U.userEmail = '"+createUserEmail+"' AND U.isActive = 1 AND U.isDeleted = 0\n" +
					"GROUP BY U.userId, U.userFirstName, U.userLastName, con.countryPhoneCode, U.userPhoneNo, U.userEmail, LP.[name], ORG.[orgnName], RR.reportRoleName, U.roleCategoryName, U.isActive, U.isDeleted";

			// Get all columns data from front end
			List<WebElement> cells = driver.getDriver().findElements(By.xpath(userLogRow));
			List<String> frontendRow = new ArrayList<>();

			if (!cells.isEmpty()) {
				for (WebElement cell : cells) {
					frontendRow.add(cell.getText());
				}
			} else {
				System.out.println("No columns found  " + firstRow);
			}
			ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(userLogQuery);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();

			List<String> backEndRow = new ArrayList<>();
			while (resultSet.next()){
				for (int i=1; i<=columnCount; i++) {
					backEndRow.add(resultSet.getString(i));
				}
			}

			// Compare the front end and back end data
			for (int i = 1; i <= columnCount; i++) {
				String frontendValue = frontendRow.get(i);
				String backendValue = backEndRow.get(i-1);
				softAssert.assertEquals(frontendValue, backendValue, "Value in frontend and backend do not match for column " + i);
			}

			softAssert.assertAll();
			test.pass("Data sanity verified successfully");
			results.createNode("Data sanity verified successfully");
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			test.fail("Data sanity failed");
			results.createNode("Data Sanity failed");
			return false;
		}
	}
	public static int getCreatedUserID() throws SQLException {
		String getUserIDQuery = "SELECT TOP 1 userId FROM dbo.[user] WHERE userEmail = '"+createUserEmail+" ' AND isActive = 1 AND isDeleted = 0 ORDER BY userId DESC";
		ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(getUserIDQuery);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		int userID = 0;

		while (resultSet.next())
		{
			for(int i=1; i<=columnCount; i++) {
				userID = resultSet.getInt(i);
			}
		}
		return userID;
	}
	public static boolean verifyDeleteUserSanity(int userid) throws SQLException {
		try {
			String getUserStatusQuery = "SELECT isActive, isDeleted FROM dbo.[User] WHERE userId = '"+userid+"'";
			ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(getUserStatusQuery);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			List<String> backEndValues = new ArrayList<>();

			while (resultSet.next())
			{
				for (int i=1; i<=columnCount; i++) {
					backEndValues.add(resultSet.getString(i));
				}
			}
			// Verify the column values
			if (backEndValues.size() == columnCount) {
				String valueAtIndex0 = backEndValues.get(0);
				String valueAtIndex1 = backEndValues.get(1);

				if (valueAtIndex0.equals("0") && valueAtIndex1.equals("1")) {
					return true; // Condition met, return true
				}
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			test.fail("Delete User Data sanity failed");
			results.createNode("Delete User Data Sanity failed");
			return false;
		}
		return false;
	}
}
