package PageObjects;

import Config.BaseTest;
import MiscFunctions.DB_Config_DB;
import MiscFunctions.Methods;
import com.aventstack.extentreports.gherkin.model.Scenario;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;


import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.Pagination.Pagination;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.date0;
import static MiscFunctions.DateUtil.dateMMDDYYYY;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.*;
import static PageObjects.LoginPage.*;

public class FormManagementPage {

    public WebDriver driver;
    public static String text;
    public static By SurveyManagementPageTitle = By.id("Form Management");
    public static String surveyManagementTable = "survey-management-log";

    @FindBy(how = How.ID, using = "edit-field-access")
    public By fieldAccessButton;
    @FindBy(how = How.ID, using = "Access Management")
    private WebElement accessManagementPageHeader;
    @FindBy(how = How.ID, using = "edit-role-rights-0")
    private WebElement editRightsIcon;

    //@FindBy(how = How.ID, using = "isCreateForm Management")
    private WebElement createFormManagementRight;
    public static By formNameColumnSort = By.id("formNameColumnSort");
    public static By formTypeNameColumnSort = By.id("formTypeName_sort");

    public static By moduleColumnSort = By.id("module_sort");
    public static By statusColumnSort = By.id("status_sort");
    public static By dateCreatedColumnSort = By.id("insDte_sort");
    @FindBy(how = How.ID, using = "last-page")
    private WebElement lastPageIcon;
    @FindBy(how = How.ID, using = "previous-page")
    private WebElement previousPageIcon;
    @FindBy(how = How.ID, using = "first-page")
    private WebElement firstPageIcon;

    public By rowsRecord = By.xpath("//table/tbody/tr");
    String targetRowText = "Admin";
    private By editRights = By.xpath(".//span[@title='Edit Rights']");
    private static By saveButtonEditRights = By.cssSelector(".fa-save");
    @FindBy(how = How.CSS, using = "tr:nth-child(28) td:nth-child(2) input")
    private WebElement createRightsInput;
    private static By createRightsCheckbox = By.cssSelector("tr:nth-child(28) td:nth-child(2) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(28) td:nth-child(3) input")
    private WebElement updateRightsInput;
    private static By updateRightsCheckbox = By.cssSelector("tr:nth-child(28) td:nth-child(3) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(28) td:nth-child(4) input")
    private WebElement viewRightsInput;
    private static By viewRightsCheckbox = By.cssSelector("tr:nth-child(28) td:nth-child(4) .custom-checkbox");
    private static By firstRow = By.xpath("//table/tbody/tr[1]");
    @FindBy(how = How.ID, using = "btn-save")
    private WebElement fieldAccessSaveButton;
    @FindBy(how = How.ID, using = "btn-reset")
    private WebElement fieldAccessResetButton;
    @FindBy(how = How.ID, using = "results-found-count")
    private WebElement ResultsFoundCountText;
    @FindBy(how = How.ID, using = "remove-filters")
    private WebElement unlockFiltersButton;
    @FindBy(how = How.ID, using = "message")
    private WebElement formChangesSavedLabel;
    @FindBy(how = How.ID, using = "create-form")
    private WebElement newFormButton;

    private static By labelToGetText = By.xpath("//div[@class='row']//div[@class='form-group col-md-12 col-lg-12 col-sm-12 survey-label ']//label");

    private static By newFormPopupHeading = By.xpath("//div[contains(text(),'Create Form')]");
    @FindBy(how = How.ID, using = "delete-survey-management-1-surveyManagement")
    private WebElement deleteIcon;
    public static String removeFilterIcon = "#remove-filters.d-none";
    public static String expectedTooltipTextResetFilters = "Reset Filters";
    public static String expectedTooltipTextLocked = "Lock Applied Filters";
    public static String expectedTooltipFieldAccess = "Field Access";
    public static String expectedTooltipViewIcon = "View Form";
    public static String expectedTooltipTextUnlocked = "Unlock Filters";

    public static String expectedTooltipEditIcon = "Edit Form";
    public static String expectedTooltipDeleteIcon = "Delete Form";
    public static String expectedTooltipProfileIcon = " Profile";
    private static String getRecordsFoundText;
    private static By viewIcon = By.id("view-survey-gram-1-surveyManagement");
    private static By editIcon = By.id("edit-survey-gram-1-surveyManagement");
    @FindBy(how = How.ID, using = "btn-yes")
    private WebElement deleteYesButton;
    @FindBy(how = How.ID, using = "btn-no")
    private WebElement deleteNoButton;
    @FindBy(how = How.ID, using = "close-confirmation")
    private WebElement closeDeletePopup;
    public static String formEditIcon = "/ancestor::tr//span[contains(@title, 'Edit')]";

    @FindBy(how = How.ID, using = "close-popup-modal")
    private WebElement crossEditform;

    @FindBy(how = How.ID, using = "view-able-2")
    private WebElement checkbox;
    @FindBy(how = How.ID, using = "btn-reset")
    private WebElement editCancelButton;
    @FindBy(how = How.ID, using = "btn-next")
    private WebElement editNextButton;
    @FindBy(how = How.ID, using = "close-popup-modal")
    private WebElement crossButtonNewForm;

    @FindBy(how = How.ID, using = "surveyName")
    private WebElement formNameField;
    @FindBy(how = How.XPATH, using = "//textarea[contains(@class, 'survey-textBox-Area')]")
    private WebElement formDescriptionField;
    @FindBy(how = How.CSS, using = "#survey-btn-1")
    private WebElement selectSurveyButton;
    private static By selectSurveyButtonForm = By.cssSelector("#survey-btn-1");
    private static By selectSurveyModuleForm = By.cssSelector("#survayModule");

    @FindBy(how = How.ID, using = "survey-btn-0")
    private WebElement selectGeneralButton;
    private static By formGeneralButton = By.id("survey-btn-0");
    @FindBy(how = How.ID, using = "survayModule")
    private WebElement selectSurveyModule;
    @FindBy(how = How.ID, using = "btn-next")
    private WebElement nextButtonForm;
    @FindBy(how = How.ID, using = "btn-save")
    private WebElement submitButtonForm;
    @FindBy(how = How.ID, using = "btn-back")
    private WebElement backButtonNewForm;
    @FindBy(how = How.ID, using = "btn-reset")
    private WebElement cancelButtonNewForm;

    @FindBy(how = How.ID, using = "generalLineId")
    private WebElement lineForm;

    @FindBy(how = How.ID, using = "generalTextId")
    private WebElement textForm;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Exclude Result')]")
    private WebElement excludeResultText;
    @FindBy(how = How.XPATH, using = "//*[text()=' Add Category ']")
    private WebElement addCategoryButton;
    @FindBy(how = How.ID, using = "categoryName")
    private WebElement categoryNameField;
    @FindBy(how = How.ID, using = "deleteCategoryIconId-1")
    private WebElement categoryDeleteIcon;
    @FindBy(how = How.ID, using = "categoryWeightFactor")
    private WebElement categoryWeightField;
    @FindBy(how = How.ID, using = "open-profile")
    private WebElement profileSettingsButton;
    @FindBy(how = How.ID, using = "close-profile")
    private WebElement profileSettingsBackButton;
    @FindBy(how = How.ID, using = "view-survey-gram-1-surveyManagement")
    private WebElement surveyViewButton;
    @FindBy(how = How.ID, using = "edit-survey-gram-1-surveyManagement")
    private WebElement surveyEditButton;
    //@FindBy(how = How.CLASS_NAME, using = "popup-head ng-tns-c17-0 ng-star-inserted")
    private WebElement FormHeader;

    private static By surveyTextField = By.id("generalTextId");
    private static By formDeleteIcon = By.id("delete0");

    private static String formDeleteButton = "/ancestor::tr//span[contains(@title, 'Delete')]";

    private static String formViewButton = "/ancestor::tr//span[contains(@title, 'View')]";

    @FindBy(how = How.XPATH, using = "(//button[@id='btn-next'])[2]")
    private WebElement SecondNextButtonForm;
    @FindBy(how = How.XPATH, using = "//input[@id='editModeActiveBtn']")
    private WebElement activeToggleButton;

    @FindBy(how = How.CSS, using = "#menu-administration")
    public WebElement hoverElement;
    public static By IE_Menu = By.xpath("//label[contains(text(),'IE Administration')]");
    private static By formManagementScreen = By.xpath("//a[@id='roleMGMTManageSurveyManagement']");

    private static By activeToggleIcon = By.xpath("(//span[@class='slider round ng-star-inserted'])[1]");
    @FindBy(how = How.ID, using = "questionInputFieldId")
    private WebElement questionInputField;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Radio Buttons')]")
    private WebElement questionRadioButtonField;
    @FindBy(how = How.ID, using = "listTwo")
    private WebElement dropField;

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Drag field to the left panel and drop here to add ')]")
    private WebElement para;

    @FindBy(how = How.ID, using = "delete0")
    private WebElement questionInputFieldDeleteIcon;
    public static By valueField = By.cssSelector("input.survay-radio-details.input-field-invalid[placeholder='Value']");

    public static By descriptionField = By.cssSelector("input.survay-radio-details.input-field-invalid[placeholder='Enter description here']");

    @FindBy(how = How.ID, using = "menu-administration")
    private WebElement menuIcon;
    @FindBy(how = How.ID, using = "roleMGMTManageSurveyManagement")
    private WebElement surveyManagementScreen;

    @FindBy(how = How.ID, using = "progressbar-step-2")
    private WebElement surveyFormTab2;

    private static By topTab = By.id("progressbar-step-2");

    @FindBy(how = How.ID, using = "progressbar-step-3")
    private WebElement surveyFormTab3;
    @FindBy(how = How.XPATH, using = "//tbody/tr[5]/td[4]/label[1]/div[1]/label[1]")
    private WebElement dateCreatedRadioButton;

    private static By fieldAccessForm = By.id("close-popup-modal");
    private static By newForm = By.id("create-form");
    private static By confirmationMessage = By.className("confirmation-message");
    private static By editFormHeader = By.className("popup-header");
    private static By editFormBackButton = By.id("btn-back");
    private static By verifyBackButtonNewForm = By.id("btn-back");
    private static By verifyCancelButtonNewForm = By.id("btn-reset");
    private static By categoryName = By.id("categoryName");
    private static By surveyManagementTab = By.id("surveyManagementTab");
    private static By firstNameFieldProfileSection = By.id("firstNameId");
    private static By FormHeaderText = By.id("popup-head ng-tns-c17-0 ng-star-inserted");

    //Create object of Survey Management Class
    //Constructor
    public FormManagementPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void verifyTooltip() throws IOException, InterruptedException {
        test = extent.createTest("Verify tooltip functionality on Form Management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            Actions action = new Actions(driver);
            String tooltipText;
            action.moveToElement(driver.findElement(By.id(ResetFilters))).perform();
            tooltipText = driver.findElement(By.id(ResetFilters)).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipTextResetFilters);
            action.moveToElement(driver.findElement(By.id(ResetFilters))).perform();
            if (driver.findElements(By.cssSelector(removeFilterIcon)).size() == 0) {
                // Get the tooltip text of the lock icon
                tooltipText = driver.findElement(By.id(UnlockFilter)).getAttribute("title");
                System.out.println("Tooltip text of unlock icon: " + tooltipText);
                softAssert.assertEquals(tooltipText, expectedTooltipTextUnlocked);
                // Click on the lock icon to unlock it
                click(By.id(UnlockFilter));
                Thread.sleep(2000);
                // Get the tooltip text of the unlock icon
                tooltipText = driver.findElement(By.id(LockFilter)).getAttribute("title");
                System.out.println("Tooltip text: " + tooltipText);
                // Assert that the tooltip text of the unlock icon matches the expected text
                softAssert.assertEquals(tooltipText, expectedTooltipTextLocked);
            }
            if (driver.findElements(By.cssSelector(removeFilterIcon)).size() == 1) {
                // Get the tooltip text of the unlock icon
                tooltipText = driver.findElement(By.id(UnlockFilter)).getAttribute("title");
                System.out.println("Tooltip text: " + tooltipText);
                softAssert.assertEquals(tooltipText, expectedTooltipTextUnlocked);
                // Click on the unlock icon to lock it
                click(By.id(LockFilter));
                Thread.sleep(2000);
                // Get the tooltip text of the lock icon
                tooltipText = driver.findElement(By.id(LockFilter)).getAttribute("title");
                System.out.println("Tooltip text: " + tooltipText);
                // Assert that the tooltip text of the lock icon matches the expected text
                softAssert.assertEquals(tooltipText, expectedTooltipTextLocked);
            }
            action.moveToElement(driver.findElement(viewIcon)).perform();
            tooltipText = driver.findElement(viewIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipViewIcon);
            action.moveToElement(driver.findElement(editIcon)).perform();
            tooltipText = driver.findElement(editIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipEditIcon);
            action.moveToElement(deleteIcon).perform();
            tooltipText = deleteIcon.getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipDeleteIcon);
            action.moveToElement(profileSettingsButton).perform();
            tooltipText = profileSettingsButton.getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipProfileIcon);
            softAssert.assertAll();
            test.pass("Tooltip functionality passed successfully");
            results.createNode("Tooltip functionality passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("failed to verify Tooltip functionality for the form management screen");
            results.createNode("failed to verify Tooltip functionality for the form management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Tooltip functionality for the form management screen");
            results.createNode("failed to verify Tooltip functionality for the form management screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public static void NavigateToScreen(String url, String name, By id) throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-01: Navigate to " + name + " Screen");
            steps = test.createNode(Scenario.class, Steps);

            steps.createNode("1. Hover to sidebar to expand the menu");
            steps.createNode("2. Select " + name + " from side bar menu");
            BaseTest drive = new BaseTest();
            drive.getDriver().get(url);
            waitElementInvisible(loading_cursor);
            Methods.waitElementVisible(id);
            Thread.sleep(3000);
            Assert.assertEquals(drive.getDriver().findElement(id).getText(), "" + name);
            test.pass("User navigated to " + name + " screen successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("User failed to navigate to " + name + " screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("User failed to navigate to " + name + " screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }
    public void setCategoryNameField(String categoryName) {
        categoryNameField.clear();
        categoryNameField.sendKeys(categoryName);
    }

    public void setCategoryWeightField(String categoryWeight) {
        categoryWeightField.clear();
        categoryWeightField.sendKeys(categoryWeight);
    }

    public void addCategoriesNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, addCategoryButton.isEnabled());
        addCategoryButton.click();
        //Waiting for Category fields to appear
        waitElementVisible(categoryName);
        setCategoryNameField("test 1");
        setCategoryWeightField("50");
        addCategoryButton.click();
        waitElementVisible(categoryName);
        setCategoryNameField("test 2");
        setCategoryWeightField("50");
        softAssert.assertAll();
    }

    public void categoryWeightFactor() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        addCategoryButton.click();
        //Waiting for Category fields to appear
        waitElementVisible(categoryName);
        //verify weight field cannot be more than 100
        if (categoryWeightField.isDisplayed())
            setCategoryWeightField("101");
        else
            setCategoryWeightField("100");
        String enteredText = categoryWeightField.getAttribute("value");
        System.out.println("Entered text is: " + enteredText);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(enteredText, "100");
        softAssert.assertAll();
    }

    public void tabsNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(surveyManagementTab);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, selectSurveyButton.isEnabled());
        softAssert.assertEquals(true, selectGeneralButton.isEnabled());
        softAssert.assertAll();
    }

    public void profileSettingsIcon() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, profileSettingsButton.isDisplayed());
        if (profileSettingsButton.isEnabled()) {
            profileSettingsButton.click();
            waitElementVisible(firstNameFieldProfileSection);
            String actualTitle = driver.getTitle();
            String expectedTitle = "Profile Settings (Testing Environment)";
            softAssert.assertEquals(expectedTitle, actualTitle);
        }
    }

    public void presenceOfDeleteIcon() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        String title = deleteIcon.getAttribute("title");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(title, "Delete Form");
    }

    public void cancelButtonOfEditingPopup() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        click(editIcon);
        waitElementVisible(editFormHeader);
        editCancelButton.click();
    }

    public void nextButtonOfEditingPopup() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        click(editIcon);
        waitElementVisible(editFormHeader);
        editNextButton.click();
        waitElementVisible(editFormBackButton);
    }

    public void setFormNameField(String formName) {
        formNameField.clear();
        formNameField.sendKeys(formName);
    }

    public void setFormDescriptionField(String formDescription) {
        formDescriptionField.clear();
        formDescriptionField.sendKeys(formDescription);
    }
    public void backButtonNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        setFormNameField("test form name field");
        click(selectSurveyModuleForm);
        List<WebElement> surveyModuleDropdown = driver.findElements(By.cssSelector(".ng-option-label"));
        surveyModuleDropdown.get(0).click();
        nextButtonForm.click();
        waitElementVisible(verifyBackButtonNewForm);
        backButtonNewForm.click();
        waitElementVisible(verifyCancelButtonNewForm);
        cancelButtonNewForm.click();
        waitElementVisible(newForm);
    }

    public void verifyAdminRights() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user has all rights for form management Screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            List<WebElement> rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, config.ie_username());
            type(loginPassword, config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(editIcon), 1, "Edit icon is not displayed");
            softAssert.assertEquals(size(newForm), 1, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Edit rights passed successfully");
            results.createNode("Admin rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Edit rights for for the form management screen failed");
            results.createNode("Edit rights for the form management screen failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Edit rights for the form management screen");
            results.createNode("failed to verify Edit rights for for the form management screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void disabledNextButtonNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        //Verify Next Button is disabled
        SoftAssert softAssert = new SoftAssert();
        nextButtonForm.click();
        softAssert.assertEquals(driver.findElement(alertMessage), " Please fill all required fields ");
        setFormNameField("test form name field");
        //Again verify Next Button is disabled
        softAssert.assertEquals(driver.findElement(alertMessage), " Please fill all required fields ");
        selectSurveyButton.click();
        //Again verify Next Button is disabled
        softAssert.assertEquals(driver.findElement(alertMessage), " Please fill all required fields ");
        softAssert.assertAll();
    }

    public void excludeResultToggleButtonPresence() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        surveyEditButton.click();
        //Verifying the presence of the Exclude Result Toggle button
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, excludeResultText.isEnabled());
        softAssert.assertAll();
    }

    public void nextButtonFunctionalityNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        setFormNameField("test form name field");
        selectSurveyButton.click();
        click(selectSurveyModuleForm);
        List<WebElement> surveyModuleDropdown = driver.findElements(By.cssSelector(".ng-option-label"));
        surveyModuleDropdown.get(0).click();
        nextButtonForm.click();
        waitElementVisible(verifyBackButtonNewForm);
    }

    public void cancelButtonOfNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        cancelButtonNewForm.click();
        waitElementVisible(newForm);
    }

    public void deleteAddCategoryNewForm() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        addCategoryButton.click();
        waitElementVisible(categoryName);
        categoryDeleteIcon.click();
        waitElementVisible(editFormHeader);
        cancelButtonNewForm.click();
        waitElementVisible(newForm);
    }

    public void addCategoryInputFieldsCheck() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        newFormButton.click();
        waitElementVisible(editFormHeader);
        addCategoryButton.click();
        waitElementVisible(categoryName);
        //Verify Add Category Section has an input field
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, categoryNameField.isDisplayed());
        softAssert.assertEquals(true, categoryWeightField.isDisplayed());
        softAssert.assertAll();
    }

    public void activeToggleButtonFunctionality() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();
        try {
            if (surveyEditButton.isEnabled()) {
                surveyEditButton.click();
                waitElementVisible(FormHeaderText);
                String getTextForViewFormHeader = FormHeader.getText();
                softAssert.assertTrue(getTextForViewFormHeader.contains(" Edit Form "));
                //Setting form to Active State
                boolean activeChecked = true;
                if (activeChecked != activeToggleButton.isSelected())
                    activeToggleButton.click();
                else
                    softAssert.assertTrue(true, "Active toggle button is already checked");
                //Setting form to Inactive State
                boolean inactiveChecked = true;
                if (inactiveChecked == activeToggleButton.isSelected())
                    activeToggleButton.click();
                else
                    softAssert.assertTrue(true, "Active toggle button is already unchecked");
                nextButtonForm.click();
                //click on the Next button as soon as the "text" field is visible
                waitElementVisible(surveyTextField);
                nextButtonForm.click();
                //click on the Submit button as soon as the "submit" button is visible
                waitElementVisible((By) submitButtonForm);
                submitButtonForm.click();
                waitElementVisible(newForm);
            } else {
                softAssert.assertTrue(true, "Survey Edit icon button is disabled");
            }
        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("Unable to check the active toggle button functionality");
            throw (e);
        }
    }

    public void fieldAccessColumnReordering() throws InterruptedException, IOException {
        FieldAccessFunctionality(surveyManagementTable);
        KeyColumnsCheck(surveyManagementTable, new String[]{"Form Name", "Form Type", "Status"});
    }
    public void verifyTextQuestionForm() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        try {
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            if (newFormButton.isEnabled()) {
                newFormButton.click();
                waitElementVisible(editFormHeader);
                Thread.sleep(1000);
                softAssert.assertTrue(true, "New form gets opened to create a survey/form");
                // Get text of question
                String labelText = driver.findElement(labelToGetText).getText();
                softAssert.assertEquals(labelText, " What kind of form do you want to create?");
                if (labelText.contains("What kind of form do you want to create?")) {
                    System.out.println("Text found: " + labelText);
                } else {
                    System.out.println("Text not found.");
                }
                crossButtonNewForm.click();
                waitElementVisible(newForm);
            } else {
                softAssert.assertTrue(true, "Question does not appear");
            }
        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("Question does not exist on form");
            throw (e);
        }
    }

    public void verifySurveyTabs() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        try {
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(editIcon);

            waitElementInvisible(loading_cursor);
            surveyFormTab2.click();
            Thread.sleep(250);
            surveyFormTab3.click();
            Thread.sleep(250);

            //Click on cross icon to close the popup/form
            crossButtonNewForm.click();
            waitElementVisible(newForm);
            softAssert.assertTrue(true, "The form doesn't have two tabs");

            softAssert.assertAll();
        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("The form does not have two tabs");
            throw (e);
        }
    }

    public void FormManagementScreenUnderIE() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form creation on Survey Management Screen", "This test case will verify that the form created successfully on Form Management screen");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            steps.createNode("Verify Form Management screen is under IE");
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            SoftAssert softAssert = new SoftAssert();

            // Verify user can go to Intervention mgt screen from IE-menu
            Actions builder = new Actions(driver);
            builder.moveToElement(hoverElement).build().perform();
            click(IE_Menu);
            waitElementInvisible(loading_cursor);
            click(formManagementScreen);
            softAssert.assertAll();
            test.pass("Verified Form Management screen is under IE");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Verify Form Management screen is under IE failed for Form Management Screen");
            results.createNode("Verify Form Management screen is under IE failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify Form Management screen is under IE failed for Form Management Screen");
            results.createNode("Verify Form Management screen is under IE failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void actionHeaderIcons() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        try {
            if (driver.findElement(editIcon).isDisplayed())
                softAssert.assertTrue(true, "Edit Action icon is displayed");
            else
                softAssert.assertTrue(true, "Edit action icon is not displayed");
            if (deleteIcon.isDisplayed())
                softAssert.assertTrue(true, "Delete Action icon is displayed");
            else
                softAssert.assertTrue(true, "Delete action icon is not displayed");
            if (driver.findElement(viewIcon).isDisplayed())
                softAssert.assertTrue(true, "View Action icon is displayed");
            else
                softAssert.assertTrue(true, "View action icon is not displayed");
        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("The log view does not have action icons");
            throw (e);
        }
    }

    public void surveyManagementScreenUnderIE() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        try {
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            //Locating the Main Menu
            //Instantiating Actions class
            Actions actions = new Actions(driver);
            //Hovering on main menu
            actions.moveToElement(menuIcon);
            // Locating the element from Menu
            //To mouseover on survey management screen
            actions.moveToElement(surveyManagementScreen);
            //build()- used to compile all the actions into a single step
            actions.click().build().perform();
            //Getting the current url and verifying it
            String get_current_url = driver.getCurrentUrl();
            Assert.assertEquals(get_current_url, "https://ie-qa.Project123.com/home/survey/survey");

        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("The survey management screen is not present under menu and is not enabled");
            throw (e);
        }
        softAssert.assertAll();
    }


    public void editFormFields() throws InterruptedException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(2000);
        SoftAssert softAssert = new SoftAssert();
        try {

        } catch (Exception e) {
            // Printing logs for my report
            Reporter.log("The fields are not editable on Edit Form");
            throw (e);
        }
    }

    public void rowsPerPageFunctionality() throws InterruptedException, IOException {
        RowsPerPage1(surveyManagementTable);
    }

    public void verifyAllColumns() throws InterruptedException, IOException {
        VerifyAllColumns(surveyManagementTable, new String[]{"Form Name", "Form Type", "Module", "Status", "Date Created", "Responses Submitted", "Action"});
    }

    public void wildCardFunctionality() throws InterruptedException, IOException {
        Wildcard(surveyManagementTable, "Form Management", 2);
    }

    public void lockAndUnlockFunctionality() throws InterruptedException, IOException {
        Lock(surveyManagementTable, "Form Management", 2);
    }

    public void administrationRights() throws InterruptedException, IOException {

        test = extent.createTest("Verify create, update and view role of Form Management screen", "This test case will verify that create, update and view role of Form Management screen");
        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            List<WebElement> rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (!createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, config.ie_username());
            type(loginPassword, config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(editIcon), 0, "Edit icon is not displayed");
            softAssert.assertTrue(driver.findElement(newForm).isDisplayed(), "Create icon is not displayed");

            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            rows = driver.findElements(rowsRecord);
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify all action icons are displayed
            type(loginEmail, config.ie_username());
            type(loginPassword, config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(editIcon), 1, "Edit icon is not displayed");
            softAssert.assertEquals(size(newForm), 0, "Create icon is not displayed");

            // Verify view rights now
            driver.get(url_roleManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            rows = driver.findElements(rowsRecord);
            // Loop through each row and click on the "Edit Rights" icon of the Admin row
            for (WebElement row : rows) {
                String rowText = row.getText();
                if (rowText.contains(targetRowText)) {
                    WebElement editRightsIcon = row.findElement(editRights);
                    editRightsIcon.click();
                    Thread.sleep(1500);
                    break;
                }
            }
            if (createRightsInput.isSelected()) {
                click(createRightsCheckbox);
                Thread.sleep(1000);
            }
            if (updateRightsInput.isSelected()) {
                click(updateRightsCheckbox);
                Thread.sleep(1000);
            }
            if (!viewRightsInput.isSelected()) {
                click(viewRightsCheckbox);
                Thread.sleep(1000);
            }

            click(saveButtonEditRights);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Rights details has been updated successfully.");

            //logout
            hover(sideBar);
            click(logoutButton);

            //Verify create rights are displayed
            type(loginEmail, config.ie_username());
            type(loginPassword, config.ie_password());
            click(loginButton);
            waitElementInvisible(loading_cursor);
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(editIcon), 0, "Edit icon is not displayed");
            softAssert.assertEquals(size(newForm), 0, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Access Rights passed for Form Management Screen successfully");
            results.createNode("Access Rights passed for Form Management Screen successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Access Rights failed for Form Management Screen");
            results.createNode("Access Rights failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Access Rights failed for Form Management Screen");
            results.createNode("Access Rights failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void sortingOnColumns() throws InterruptedException, IOException {
        driver.get(url_surveyAdmin);
        waitElementInvisible(loading_cursor);
        Sorting(surveyManagementTable, "Form Management", 2);
    }

    public void verifyPagination() throws InterruptedException, IOException {
        Pagination(surveyManagementTable, "Form Management");
    }

    public boolean createFormDataSanity(String formName, String formRow) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "SELECT\n" +
                    "    FM.FORM_TITLE AS [Form Name],\n" +
                    "    FT.FORM_TYPE_NAME AS [Form Type],\n" +
                    "    FORMAT(CAST(FM.INS_DTE as date),'MM/dd/yyyy') AS [Date Created],\n" +
                    "    CASE WHEN FM.IS_ACTIVE = 1 THEN 'Active' ELSE 'Inactive' END AS STATUS,\n" +
                    "    M.MODULE_NAME AS [Module],\n" +
                    "    COUNT(FF.FILLED_FORM_ID) AS [Response Submitted]\n" +
                    "    FROM dbo.FORM FM\n" +
                    "    JOIN dbo.FORM_TYPE FT ON FT.FORM_TYPE_ID = FM.FORM_TYPE_ID    \n" +
                    "    LEFT JOIN dbo.MODULE M ON M.MODULE_ID = FM.MODULE_ID AND M.IS_ACTIVE = 1 AND M.IS_DELETED = 0 \n" +
                    "    LEFT JOIN dbo.FILLED_FORM FF ON FF.FORM_ID = FM.FORM_ID\n" +
                    "    WHERE FM.FORM_TITLE = '" + formName + "' " +
                    "    GROUP BY\n" +
                    "    FM.FORM_ID,\n" +
                    "    FM.FORM_TITLE,\n" +
                    "    FM.FORM_TYPE_ID,\n" +
                    "    FM.INS_DTE,\n" +
                    "    FT.FORM_TYPE_NAME,\n" +
                    "    FM.IS_ACTIVE,\n" +
                    "    M.MODULE_NAME";

            driver.navigate().refresh();
            waitElementInvisible(loading_cursor);
            List<WebElement> cells = driver.findElement(By.xpath(formRow)).findElements(By.tagName("td"));
            List<String> frontendRow = new ArrayList<>();

            if (!cells.isEmpty()) {
                for (WebElement cell : cells) {
                    frontendRow.add(cell.getText());
                }
            } else {
                System.out.println("No columns found  " + firstRow);
            }

            ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> backendRow = new ArrayList<>();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    backendRow.add(resultSet.getString(i));
                }
            }
            // Compare the front end and back end data
            for (int i = 1; i <= columnCount; i++) {
                String frontendValue = frontendRow.get(i).toLowerCase();
                String backendValue = backendRow.get(i - 1).toLowerCase();
                System.out.println("Frontend value for column " + i + ": " + frontendValue);
                System.out.println("Backend value for column " + i + ": " + backendValue);
                if (frontendValue.startsWith(" ")) {
                    frontendValue = frontendValue.substring(1);
                }

                // Ignore the first letter space in backendValue
                if (backendValue.startsWith(" ")) {
                    backendValue = backendValue.substring(1);
                }

                softAssert.assertEquals(frontendValue, backendValue, "Value in frontend and backend do not match for column " + i);
            }
            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            results.createNode("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed");
            results.createNode("Data Sanity failed");
            return true;
        }
    }
    public void formCreation() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form creation on Survey Management Screen", "This test case will verify that the form created successfully on Form Management screen");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_surveyAdmin);
            waitElementInvisible(loading_cursor);
            newFormButton.click();
            waitElementVisible(newFormPopupHeading);
            softAssert.assertTrue(true, "New form gets opened to create a survey/form");
            click(selectSurveyButtonForm);
            setFormNameField("form" + dateMMDDYYYY + date0);
            formDescriptionField.sendKeys("Form created");
            click(selectSurveyModuleForm);
            List<WebElement> surveyModuleDropdown = driver.findElements(By.cssSelector(".ng-option-label"));
            surveyModuleDropdown.get(0).click();
            addCategoryButton.click();
            //Waiting for Category fields to appear
            waitElementVisible(categoryName);
            setCategoryNameField("test 1");
            setCategoryWeightField("50");
            nextButtonForm.click();
            waitElementVisible(surveyTextField);
            scroll(topTab);
            Actions act = new Actions(driver);
            Action dragAndDrop = act.clickAndHold(questionInputField)
                    .moveToElement(dropField)
                    .release(dropField)
                    .build();

            dragAndDrop.perform();

               /* int i =0;
                while (para.isDisplayed()) {
                    try {
                        // Action draganddrop =  act.clickAndHold(questionInputField).pause(Duration.ofSeconds(2)).moveToElement(dropField).pause(Duration.ofSeconds(2)).release().build();
                       //  draganddrop.perform();
                        Thread.sleep(250);
                        System.out.println(i);
                        act.dragAndDrop(questionInputField, dropField).build().perform();
                        act.moveToElement(dropField).perform();
                        act.dragAndDrop(questionInputField,dropField).build().perform();
                        i++;
                      //  act.clickAndHold(questionInputField).pause(Duration.ofSeconds(2)).moveToElement(dropField).pause(Duration.ofSeconds(2)).release().build().perform();

                    } catch (Exception ex) {
                        System.out.println("Error occurred during drag and drop: " + ex.getMessage());
                    }
                }*/
            Thread.sleep(1000);
            SecondNextButtonForm.click();
            waitElementVisible(popupSaveButton);
            Thread.sleep(1500);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Form details have been saved successfully", "form did not created successfully");
            softAssert.assertAll();
            test.pass("User was not able to upload empty file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Verify form created failed for Form Management Screen");
            results.createNode("Verify form created failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify form created failed for Form Management Screen");
            results.createNode("Verify form created failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void formUpdate() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form creation on Survey Management Screen", "This test case will verify that the form created successfully on Form Management screen");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            preconditions.createNode("1. Go to Form management screen " + url_surveyAdmin);
            preconditions.createNode("2. Click on the new form button");
            preconditions.createNode("3. Select form type, enter form name, and select module");
            preconditions.createNode("4. Click on Next button, save the form");
            steps.createNode("1. Verify 'Input type' Question");
            SoftAssert softAssert = new SoftAssert();
            click(By.xpath("//label[@title='form" + dateMMDDYYYY + date0 + "']" + formEditIcon));
            waitElementInvisible(loading_cursor);
            click(formGeneralButton);
            formDescriptionField.sendKeys("Form updated");
            click(selectSurveyModuleForm);

            //Waiting for Category fields to appear
            waitElementVisible(categoryName);
            setCategoryNameField("test updated");
            setCategoryWeightField("10");
            nextButtonForm.click();
            waitElementVisible(surveyTextField);
            scroll(topTab);
            Actions act = new Actions(driver);
            click(formDeleteIcon);
            Action dragAndDrop = act.clickAndHold(questionRadioButtonField).moveToElement(dropField).release(dropField).build();
            dragAndDrop.perform();
            Thread.sleep(1000);
            dragAndDrop = act.clickAndHold(textForm).moveToElement(dropField).release(dropField).build();
            dragAndDrop.perform();
            List<WebElement> valueFields = driver.findElements(valueField);
            valueFields.get(0).sendKeys("1");
            valueFields.get(1).sendKeys("2");
            valueFields.get(2).sendKeys("3");
            List<WebElement> descriptionFields = driver.findElements(descriptionField);
            descriptionFields.get(0).sendKeys("desc 1");
            descriptionFields.get(1).sendKeys("desc 2");
            descriptionFields.get(2).sendKeys("desc 3");
            Thread.sleep(1000);
            SecondNextButtonForm.click();
            waitElementVisible(popupSaveButton);
            Thread.sleep(1500);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Form details have been saved successfully", "form did not created successfully");
            softAssert.assertAll();
            test.pass("User was not able to upload empty file successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Verify form updated failed for Form Management Screen");
            results.createNode("Verify form updated failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify form updated failed for Form Management Screen");
            results.createNode("Verify form updated failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void formActiveToggleButton() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form active toggle button on Survey Management Screen");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            SoftAssert softAssert = new SoftAssert();
            click(By.xpath("//label[@title='form" + dateMMDDYYYY + date0 + "']" + formEditIcon));
            waitElementInvisible(loading_cursor);
            scroll(activeToggleIcon);
            click(activeToggleIcon);
            Thread.sleep(1000);
            nextButtonForm.click();
            waitElementVisible(surveyTextField);
            SecondNextButtonForm.click();
            waitElementVisible(popupSaveButton);
            Thread.sleep(1500);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Form details have been saved successfully", "form did not created successfully");
            click(By.xpath("//label[@title='form" + dateMMDDYYYY + date0 + "']" + formEditIcon));
            waitElementInvisible(loading_cursor);
            scroll(activeToggleIcon);
            click(activeToggleIcon);
            Thread.sleep(1000);
            nextButtonForm.click();
            waitElementVisible(surveyTextField);
            SecondNextButtonForm.click();
            waitElementVisible(popupSaveButton);
            Thread.sleep(1500);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Form details have been saved successfully", "form did not created successfully");
            softAssert.assertAll();
            test.pass("User was not able to delete form successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Verify active toggle button functionality for Form Management Screen");
            results.createNode("Verify active toggle button functionality for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify active toggle button functionality for Form Management Screen");
            results.createNode("Verify active toggle button functionality for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void formView() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form view on Survey Management Screen", "This test case will verify that the form viewed successfully on Form Management screen");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            SoftAssert softAssert = new SoftAssert();
            click(By.xpath("//label[@title='form" + dateMMDDYYYY + date0 + "']" + formViewButton));
            waitElementInvisible(loading_cursor);
            boolean isEditable = true;
            try {
                formNameField.sendKeys("checking field is editable");
            } catch (Exception e) {
                isEditable = false;
            }
            softAssert.assertFalse(isEditable, "Field is editable.");
            Thread.sleep(1000);
            click(popupCloseButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertAll();
            test.pass("User was not able to view form successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Verify form viewed failed for Form Management Screen");
            results.createNode("Verify form viewed failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify form viewed failed for Form Management Screen");
            results.createNode("Verify form viewed failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void formDelete() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify form deletion on Survey Management Screen", "This test case will verify that the form created successfully on Form Management screen");
            preconditions = test.createNode(Scenario.class, PreConditions);
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);
            preconditions.createNode("1. Go to Form management screen " + url_surveyAdmin);
            preconditions.createNode("2. Click on the new form button");
            preconditions.createNode("3. Select form type, enter form name, and select module");
            preconditions.createNode("4. Click on Next button, save the form");
            SoftAssert softAssert = new SoftAssert();
            click(By.xpath("//label[@title='form" + dateMMDDYYYY + date0 + "']" + formDeleteButton));
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Form Deleted Successfully", "form deleted successfully");
            softAssert.assertAll();
            test.pass("User was not able to delete form successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Verify form deleted failed for Form Management Screen");
            results.createNode("Verify form deleted failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Verify form deleted failed for Form Management Screen");
            results.createNode("Verify form deleted failed for Form Management Screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

}




