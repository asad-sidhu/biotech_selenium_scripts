package PageObjects;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.Queries;
import Models.ProgramManagementModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static PageObjects.BasePage.loading_cursor;

public class ProgramManagementPage extends BaseTest {

	public static By programManagementTitle = By.id("Program Management");

	public static By programCreateButton = By.id("create-program");
	public static By programTreatmentButton = By.xpath("//*[text()=' Record Treatment']");
	public static By programName = By.xpath("//input[@id='programNameId' and @type = 'text']");
	public static By programDisplayName = By.id("programDisplayNameId");
	public static By programColumn1 = By.cssSelector("#col-0 label");
	public static By programTargetPathogen = By.cssSelector("#targetsId input");
	public static By programTargetPathogenDropdownExpand = By.cssSelector("#targetsId .ng-arrow-wrapper");
	public static By programTargetPathogenDropdown = By.cssSelector("#targetsId .ng-option");
	//public static By programTargetPathogenDropdownExpand = By.xpath("(//span[@class='ng-arrow-wrapper'])[1]");
	public static By targetPathogenSelectAllButton = By.xpath("//*[@class='custom-control-label' and text()='Select All']");
	public static By targetPathogenSelectAllSelected = By.id("targets-head");

	public static By targetPathogenDropdownActualValuesSelected = By.cssSelector(".ng-option[aria-selected='true']");




	public static By programTargetPathogenValue = By.cssSelector("#targetPathogenId .ng-value-label");
	public static By programProgramType = By.cssSelector("#programTypeId input");
	public static By programProgramTypeValue = By.cssSelector("#programTypeId .ng-value-label");
	public static By programProgramTypeDisabledCheck = By.cssSelector("#programTypeId.ng-select-disabled");
	public static By programSupplier = By.cssSelector("#supplierId input");
	//	public static By programTradeName = By.xpath("//input[@id='0-tradeNameId']");
//	public static By programTradeName = By.xpath("//*[@id = '0-tradeNameId'] //*[input]");
	public static By programTradeNameInput = By.cssSelector("[name='tradeName'] input");
	public static By programTradeNameVaccineField = By.xpath("//div[@class='ng-select-box']//input[@role='combobox']");
	public static By programTradeNameFeed = By.xpath("//*[@id = '0-0-tradeNameId'] //input[@type = 'text']");

	public static By programIngredient = By.xpath("//input[@id='0-0-ingredientId']");
	public static By programIngredient2 = By.xpath("//input[@id='0-1-ingredientId']");

	public static By deleteIngredientIcon = By.xpath("//img[@src='../../../../../assets/images/components-icons/icon-trash-white.png']");

	public static By programDescription = By.id("descriptionId");
	public static By programTreatmentDescription = By.id("treatmentDescriptionId");
	//	public static By programStartDate = By.id("startDate img");
	public static By programNoApplicationAsset = By.xpath("//input[@id='num-0-numOfApplicationId']");
	public static String programDaysApplicationAsset = "num-AssetDayApplicationId";
	public static By programStartDateIcon = By.cssSelector("#startDate img");
	public static By programEndDateIcon = By.cssSelector("#endDate img");
	public static By programComplexList = By.cssSelector("#compleSiteId .toggle-list");
	public static By programComplexSearch = By.id("compleSiteId_search");
	public static By complexInputField = By.cssSelector("#fcSiteId input");

	public static By programFeedTypeDropdown = By.cssSelector("#feedTypeId-1 input");

	public static By ingredientFeedTypeDropdown = By.xpath("//img[@src='assets/images/list.svg']");


	public static By programFeedTypeValue = By.cssSelector("#feedTypeId-1 .ng-value-label");
	public static By programAssetDayStart = By.id("num-AssetDayStartId-1");
	public static By programAssetDayEnd = By.id("#num-AssetDayEndId-1");

	public static By programComplexSelected = By.cssSelector("#compleSiteId.ng-valid");
	public static By programComplexMandatoryCheck = By.xpath("//*[@id='compleSiteId' and @ng-reflect-mandatory = 'true']");


	public static By programBioshuttleAssetDayStart = By.id("num-AssetDayStartId-1");
	public static By programBioshuttleAssetDayStartError = By.cssSelector("#AssetDayStartId-1-error-container .hide");
	public static By programBioshuttleAssetDayEnd = By.id("num-AssetDayEndId-1");
	public static By programBioshuttleAssetDayEndError = By.cssSelector("#AssetDayEndId-1-error-container .hide");

	public static By programAddFeedType = By.xpath("//*[text() = 'Add Feed Type']");
	public static By programFeedType2Dropdown = By.cssSelector("#feedTypeId-2 input");
	public static By programAssetDayStart2 = By.id("num-AssetDayStartId-2");
	public static By programAssetDayEnd2 = By.id("num-AssetDayEndId-2");
	public static By programFeedCategory = By.xpath("//*[text() = 'Feed Category']");

	public static By programFeedProgramTab = By.xpath("//*[text() = 'Feed Programs ']");
	public static By programTreatmentProgramTab = By.xpath("//*[text() = 'Treatment ']");
	public static By programVaccineProgramTab = By.xpath("//*[text() = 'Vaccine Programs ']");
	public static By programBioshuttleProgramTab = By.xpath("//*[text() = 'Bioshuttle ']");
	public static By programProgramUtilizationTab = By.xpath("//*[text() = 'Program Utilization ']");
	public static By resetFilterButton = By.id("reset-all-filters");
	public static By feedProgramDisplayNameFilter = By.xpath("/html/body/app-root/div/app-manage-program/div/div[3]/app-feedprogram-log/div/div/div/div[2]/div/div/table/thead/tr/th[9]/div[2]/div[1]/span");
	public static By feedProgramDisplayNameSearchInput = By.id("feedprogram_programDisplayName_search-input");
	public static By feedProgramDisplayNameSelectAll = By.id("feedprogram_programDisplayName_cust-cb-lst-txt_selectAll");
	public static By feedProgramDisplayNameApplyButton = By.id("feedprogram_programDisplayName_apply");
	public static By firstRow = By.xpath("//table/tbody/tr[1]");
	public static String getFeedProgramLog = "//*[@id='table-feed-program-log']//tbody//tr[1]//td";
	public static By vaccineProgramdisplayNameFilter = By.xpath("/html/body/app-root/div/app-manage-program/div/div[2]/app-vaccine-log/div/div/div/div[2]/div/div/table/thead/tr/th[8]/div[2]/div[1]/span");
	public static By vaccineProgramdisplayNameSearchInput = By.id("vaccine_programDisplayName_search-input");
	public static By vaccineProgramDisplayNameSelectAll = By.id("vaccine_programDisplayName_cust-cb-lst-txt_selectAll");
	public static By vaccineProgramDisplayNameApplyButton = By.id("vaccine_programDisplayName_apply");
	public static String getVaccineProgramLog = "//*[@id='table-vaccine-log']//tbody//tr[1]//td";
	public static By bioshuttleProgramdisplayNameFilter = By.xpath("/html/body/app-root/div/app-manage-program/div/div[4]/app-bioshuttle-log/div/div/div/div[2]/div/div/table/thead/tr/th[12]/div[2]/div[1]/span");
	public static By bioshuttleProgramdisplayNameSearchInput = By.id("vaccine_BioshuttleprogramDisplayName_search-input");
	public static By bioshuttleProgramDisplayNameSelectAll = By.id("vaccine_BioshuttleprogramDisplayName_cust-cb-lst-txt_selectAll");
	public static By bioshuttleProgramDisplayNameApplyButton = By.id("vaccine_BioshuttleprogramDisplayName_apply");
	public static String getBioshuttleProgramLog = "//*[@id='table-bio-vaccine-log']//tbody//tr[1]//td";



//	public static String programEditVaccineButton = "edit-vaccine-program-";
//	public static String programEditFeedButton = "edit-feed-program-";
//	public static String programDeleteVaccineButton = "delete-vaccine-program-";
//	public static String programDeleteFeedButton = "delete-feed-program-";

	public static String programFeedTable = "feed-program-log";
	public static String programTreatmentTable = "treatment-log";
	public static String programVaccineTable = "vaccine-log";
	public static String programBioshuttleTable = "vaccine-bio-log";
	public static String programUtilizationTable = "program-util-log";

	public static String programFeed_ID = "feedprogram";
	public static String programTreatment_ID = "treatment";
	public static String programVaccine_ID = "vaccine";
	public static String programBioshuttle_ID = "vaccine-bio";

	public static String programFeedEdit = "edit-feed-program-";
	public static String programTreatmentEdit = "edit-treatment-program-";
	public static String programVaccineEdit = "edit-vaccine-program-";
	public static String programBioshuttleEdit = "edit-vaccine-bio-program-";

	public static String programFeedCopy = "copy-feed-program-";
	public static String programTreatmentCopy = "copy-treatment-program-";
	public static String programVaccineCopy = "0-";
	public static String programBioshuttleCopy = "copy-vaccine-bio-program-";

	public static String programFeedDelete = "delete-feed-program-";
	public static String programTreatmentDelete = "delete-treatment-program-";
	public static String programVaccineDelete = "delete-vaccine-program-";
	public static String programBioshuttleDelete = "delete-vaccine-bio-program-";

	public static By programRegisterNewProgramButton = By.xpath("//*[text()=' Register New Program']");
	public static By programVaccineProgramTab_XPATH = By.xpath("//*[text() = 'Vaccine Programs ']");
	public static String programFeedProgramTab_XPATH = "//*[text() = 'Feed Programs ']";
	public static String programVaccine_ProgramName_FilterIcon_CSS = "#" + programVaccineTable + " #programName_show-filter";

	public static String programFeedProgramNameCol = "#col-0-feedprogram label";
	public static String programFeedTypesCol = "#col-2-feedprogram label";
	public static String programFeedStartDateCol = "#col-3-feedprogram label";
	public static String programFeedEndDateCol = "#col-4-feedprogram label";
	public static String programFeedComplexCol = "#col-4-feedprogram label";
	public static String programFeedTargetPathogenCol = "#col-1-feedprogram label";
	public static String programFeedTargetPathogenMoreCol = "#col-1-feedprogram label:nth-child(2)";

	public static String programFeedTradeNameCol = "#col-6-feedprogram label";
	public static String programFeedProgramDisplayNameCol = "#col-7-feedprogram label";

	public static String programTreatmentProgramNameCol = "#col-0-treatment label";
	public static String programTreatmentSupplierNameCol = "#col-1-treatment label";
	public static String programTreatmentDescriptionCol = "#col-2-treatment label";
	public static String programTreatmentStartDateCol = "#col-9-treatment label";
	public static String programTreatmentEndDateCol = "#col-10-treatment label";
	public static String programTreatmentComplexCol = "#col-11-treatment label";

	public static String programVaccineProgramNameCol = "#col-0-vaccine label";
	public static String programVaccineStartDateCol = "#col-2-vaccine label";
	public static String programVaccineEndDateCol = "#col-3-vaccine label";
	public static String programVaccineComplexCol = "#col-4-vaccine label";
	public static String programVaccineTargetPathogenCol = "#col-1-vaccine label";
	public static String programVaccineTargetPathogenMoreCol = "#col-1-vaccine label:nth-child(2)";

	public static String programVaccineTradeNameCol = "#col-5-vaccine label";
	public static String programVaccineProgramDisplayNameCol = "#col-6-vaccine label";


	public static String programBioshuttleProgramNameCol = "#col-0-vaccine-bio label";
	public static String programBioshuttleProgramTypeCol = "#col-1-vaccine-bio label";
	public static String programBioshuttleTargetPathogenCol = "#col-2-vaccine-bio label";
	public static String programBioshuttleTargetPathogenMoreCol = "#col-2-vaccine-bio label:nth-child(2)";

	public static String programBioshuttleComplexCol = "#col-3-vaccine-bio label";
	public static String programBioshuttleStartDateCol = "#col-4-vaccine-bio label";
	public static String programBioshuttleEndDateCol = "#col-5-vaccine-bio label";
	public static String programBioshuttleFeedTypesCol = "#col-6-vaccine-bio label";
	public static String programBioshuttleFeedTypesCategoriesCol = "#col-7-vaccine-bio label";
	public static String programBioshuttleVaccineTradeNameCol = "#col-8-vaccine-bio label";
	public static String programBioshuttleFeedTradeNameCol = "#col-9-vaccine-bio label";
	public static String programBioshuttleProgramDisplayNameCol = "#col-10-vaccine-bio label";


	public WebElement feedNameField;
	@FindBy(how = How.ID, using = "fcBioshuttleFeedName")


	public static String programFeedCSVFileName = "Feed Program Log - ";
	public static String programTreatmentCSVFileName = "Treatment Log - ";
	public static String programVaccineCSVFileName = "Vaccine Program Log - ";
	public static String programBioshuttleCSVFileName = "Bioshuttle Log - ";
	public static String programUtilizationCSVFileName = "Program Utilization Log - ";



	//***INLINE EDIT FUNCTIONALITY LOCATORS**

	public static By inlineEditFeedButton = By.cssSelector("#"+ programFeedTable + " #edit-inline-access");
	public static By inlineEditVaccineButton = By.cssSelector("#"+ programVaccineTable + " #edit-inline-access");
	public static By inlineEditBioshuttleButton = By.cssSelector("#"+ programBioshuttleTable + " #edit-inline-access");

	public static String programFeedProgramTable = "manage-feed-program-log";

	public static String feedProgramsTargetPathogenColumn = "3";
	public static String feedProgramsDescriptionColumnIndex = "2";
	public static String feedProgramsComplexColumnIndex = "7";

	public static String feedProgramsSupplierNameColumnIndex = "1";
	public static String feedProgramsProgramNameColumnIndex = "0";
	public static String feedProgramsStartDateColumnIndex = "3";

	public static String feedProgramsEndDateColumnIndex = "4";


	public static By feedProgramDescriptionColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsDescriptionColumnIndex + " input");
	public static By feedProgramSupplierNameColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsSupplierNameColumnIndex + " input");
	public static By feedProgramsComplexColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsComplexColumnIndex + " input");
	public static By feedProgramsProgramNameColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsProgramNameColumnIndex + " input");
	public static By feedProgramsStartDateColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsStartDateColumnIndex + " input");
	public static By feedProgramsEndDateColumn = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsEndDateColumnIndex + " input");
	public static By feedFeedTypeShowFilter = By.id("feedName_show-filter");
	public static String feedProgramsDisplayNameeCol = "7";
	public static By feedProgramsDisplayNameField = By.cssSelector("tr:nth-child(1) #col-" + feedProgramsDisplayNameeCol + " input");

	public static String vaccineProgramsTable = "vaccine-log";

	public static String vaccineProgramsProgramNameCol = "0";
	public static By vaccineProgramsProgramNameField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsProgramNameCol + " input");
	public static String vaccineProgramsTargetPathogenCol = "1";
	public static By vaccineProgramsSupplierNameField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsTargetPathogenCol + " input");
	public static String vaccineProgramsStartDateCol = "2";
	public static By vaccineProgramsStartDateField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsStartDateCol + " input");
	public static String vaccineProgramsEndDateCol = "3";
	public static By vaccineProgramsEndDateField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsEndDateCol + " input");
	public static String vaccineProgramsDisplayNameeCol = "6";
	public static By vaccineProgramsDisplayNameField = By.cssSelector("tr:nth-child(1) #col-" + vaccineProgramsDisplayNameeCol + " input");

	public static By vaccineTabApplicationsOnAssetPopup = By.xpath("//div[contains(text(),'Edit Program Application')]");
	public static By vaccineTabApplicationsOnAssetField = By.id("num-numOfApplicationId");
	public static By vaccineTabAssetDayApplicationField1 = By.id("num-AssetDayApplicationId-1");
	public static By vaccineTabAssetDayApplicationField2 = By.id("num-AssetDayApplicationId-2");

	//Bioshuttle tab locators
	public static String bioshuttleTabTable = "manage-vaccine-bio-log";

	public static String bioshuttleProgramNameCol = "0";
	public static By bioshuttleProgramNameField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleProgramNameCol + " input");

	public static String bioshuttleProgramTypeCol = "1";
	public static By bioshuttleProgramTypeField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleProgramTypeCol + " input");
	public static String bioshuttleTargetPathogenCol = "2";
	public static By bioshuttleTargetPathogenField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleTargetPathogenCol + " input");
	public static String bioshuttleComplexCol = "3";
	public static By bioshuttleComplexField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleComplexCol + " input");
	public static String bioshuttleStartDateCol = "4";
	public static By bioshuttleStartDateField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleStartDateCol + " input");
	public static String bioshuttleEndDateCol = "5";
	public static By bioshuttleEndDateField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleEndDateCol + " input");
	public static String bioshuttleProgramDisplayNameCol = "10";
	public static By bioshuttleProgramDisplayNameField = By.cssSelector("tr:nth-child(1) #col-" + bioshuttleProgramDisplayNameCol + " input");

	//FIELD ACCESS FUNCTIONALITY LOCATORS

	public static By fieldAccessIconFeed = By.cssSelector("#feed-program-log #edit-field-access");
	public static By fieldAccessIcon = By.id("edit-field-access");

	WebElement fieldAccess = getDriver().findElement(By.id("edit-field-access"));

	public static By resetDefaultButton = By.id("btn-reset");
	public static By saveButton = By.id("btn-save");

	public static By fieldAccessTitle = By.xpath("//label[contains(text(),'Field Order')]");
	public static By dropFirstColumn = By.id("drag-control-element-0");

	public static By successMessageFieldAccess = By.id("message");

	//***Record Treatment Locators*****
	public static By recordTreatmentButton = By.xpath("//*[text()=' Record Treatment']");
	public static By createTreatmentHeading = By.xpath("//div[contains(text(),'Create Treatment')]");
	//public static By complexField = By.xpath("//*[text(),'Select Complex')]");

	public static By complexField = By.id("fcSiteId");
	public static By Canton = By.xpath("//span[contains(text(),'Canton')]");

	public static By farmField = By.id("farmSiteId");
	public static By farmInputField = By.cssSelector("#farmSiteId input");
	public static By farmFieldValue = By.xpath("//span[contains(text(),'P&T')]");

	public static By startDateField = By.cssSelector("#startDate img");

	public static By endDateField = By.cssSelector("#endDate img");

	public static By AssetdeploymentDateField = By.id("deploymentDateId");

	public static By AssetdeploymentDateFieldValue = By.xpath("//span[contains(text(),'03/01/2023 - Small')]");

	public static By housesAppliedField = By.cssSelector("#housePlaced input");

	public static By housesAppliedValue = By.xpath("//label[contains(text(),'House 1')]");

	public static By treatmentNameField = By.id("treatmentNameId");
	public static By treatmentDescriptionField = By.id("treatmentDescriptionId");


	public static By targetField = By.cssSelector("#targetsId");
	public static By targetFieldValue = By.xpath("//label[contains(text(),'Coccidiosis')]");
	public static By formTreatmentField = By.xpath("//input[@id='formId']");

	//Inline Edit Treatment functionality locators

	public static By programInlineFeedButtonSave = By.cssSelector("#"+ programFeedTable + " #edit-inlineSave-access");
	public static By programInlineVaccineButtonSave = By.cssSelector("#"+ programVaccineTable + " #edit-inlineSave-access");

	public static By programInlineBioshuttleButtonSave = By.cssSelector("#"+ programBioshuttleTable + " #edit-inlineSave-access");


	public static By treatmentTab = By.xpath("//*[text()='Treatment ']");
	public static String treatmentTable = "treatment-log";
	public static String treatmentInlineButtonSave = "edit-inlineSave-access";

	public static By treatmentTabInlineEditIcon = By.cssSelector("#treatment-log #edit-inline-access");

	public static By treatmentDescriptionColumn = By.xpath("//tbody/tr[@id='row-0-treatment']/td[@id='col-2-treatment']/div[1]/label[1]/input[1]");
	public static By addedIngredient = By.xpath("//label[contains(text(),'Add Ingredient')]");
	public static By ingredientCategoryDropdown = By.id("feedCategoryId");
	public static By auditTargetPathogenColumn = By.id("audit-sort-targetPathogen");
	public static By selectAllTargetPathogen = By.xpath("(//label[text()='Select All'])[2]");

	public static boolean programDataSanity(String programLogRow, String programQuery)
	{
		try {
			BaseTest driver = new BaseTest();
			SoftAssert softAssert = new SoftAssert();

			List<WebElement> cells = driver.getDriver().findElements(By.xpath(programLogRow));
			List<String> frontendRow = new ArrayList<>();

			if (!cells.isEmpty()) {
				for (WebElement cell : cells) {
					frontendRow.add(cell.getText());
				}
			} else {
				System.out.println("No columns found  " + firstRow);
			}

			ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(programQuery);
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
				System.out.println(frontendValue+" >>> "+backendValue);
				softAssert.assertEquals(frontendValue, backendValue, "Value in frontend and backend do not match for column " + i);
			}

			softAssert.assertAll();
			test.pass("Data sanity verified successfully");
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			test.fail("Data sanity failed");
			return false;
		}
	}
	public static void filterFeedProgram(String programName) throws InterruptedException
	{
		click(feedProgramDisplayNameFilter);
		type(feedProgramDisplayNameSearchInput, programName);
		Thread.sleep(1000);
		click(feedProgramDisplayNameSelectAll);
		click(feedProgramDisplayNameApplyButton);
		Thread.sleep(750);
	}

	public static void filterVaccineProgram(String programName) throws InterruptedException
	{
		click(vaccineProgramdisplayNameFilter);
		type(vaccineProgramdisplayNameSearchInput, programName);
		Thread.sleep(1000);
		click(vaccineProgramDisplayNameSelectAll);
		click(vaccineProgramDisplayNameApplyButton);
		Thread.sleep(750);
	}

	public static void filterBioshuttleProgram(String programName) throws InterruptedException
	{
		click(bioshuttleProgramdisplayNameFilter);
		type(bioshuttleProgramdisplayNameSearchInput, programName);
		Thread.sleep(1000);
		click(bioshuttleProgramDisplayNameSelectAll);
		click(bioshuttleProgramDisplayNameApplyButton);
		Thread.sleep(750);
	}


}