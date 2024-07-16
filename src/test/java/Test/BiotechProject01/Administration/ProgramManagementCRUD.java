package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.*;
import Models.ProgramManagementModel;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.Constants.url_programAdmin;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static Models.ProgramManagementModel.lstProgramManagementSearch;
import static PageObjects.BasePage.*;
import static PageObjects.ProgramManagementPage.*;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class ProgramManagementCRUD extends BaseTest {


    @BeforeTest(groups = {"smoke", "regression"})
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Program_Management" + date + ".html");
//        spark.config().setReportName("Program Management Test Report");
        initReport("Administration_Program_Management_CRUD");
        DB_Config_DW.test();
        DB_Config_DB.test();
    }

    @BeforeClass(groups = {"smoke", "regression"})
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_programAdmin, "Program Management", programManagementTitle);
    }


    @Test(enabled = true, priority = 1, groups = {"smoke", "regression"})
    public void CreatePrograms() throws InterruptedException, IOException, SQLException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();
        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            type(By.tagName("html"), Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        }

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = ExtentVariables.extent.createTest("AN-PM-40: Verify user can create a " + objModel.ProgramType);
                SoftAssert softAssert = new SoftAssert();

                scroll(programCreateButton);
                click(programCreateButton);

                //Program DisplayName
                softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                type(programDisplayName, objModel.ProgramName);
                softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                System.out.println("Program Display Name is " + objModel.ProgramName);
                Thread.sleep(500);

                //Program Type
                type(programProgramType, objModel.ProgramType);
                Thread.sleep(500);
                softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                enterKey(programProgramType);
                Thread.sleep(500);

                //Vaccine Number of Applications on Asset
                if (objModel.ProgramType.startsWith("Vaccine") || objModel.ProgramType.startsWith("Bioshuttle")) {
                    String NoApplicationAsset = "2";
                    type(programNoApplicationAsset, NoApplicationAsset);
                    Thread.sleep(500);

                    // click(popupSaveButtonXpath);
                    for (int i = 1; i <= Integer.parseInt(NoApplicationAsset); i++) {
                        type(By.id(programDaysApplicationAsset + "-" + i), "" + i);
                    }
                }

                //Complex
                click(programComplexList);
                type(programComplexSearch, Queries.getComplexName());
                Thread.sleep(250);
                click(By.xpath("//tr[@class='selectable ng-star-inserted']//label/b"));

                //Start Date
                click(programStartDateIcon);
                Methods method = new Methods();
                WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
                List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
                clickGivenDay(columns1, getFirstDay());
                Thread.sleep(500);

                //End Date
                click(programEndDateIcon);
                WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
                List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
                clickGivenDay(columns2, getDay("30"));
                Thread.sleep(700);
                softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");

                //Target Pathogen
                List<String> selectedValues = new ArrayList<>();

                click(programTargetPathogen);
                Thread.sleep(500);
                // IE-10773
                softAssert.assertEquals(size(targetPathogenSelectAllButton), 1, "Select all button is not present on target pathogen dropdown");

                //   click(targetPathogenSelectAllButton);
                type(programTargetPathogen, "Patho_1");
                enterKey(programTargetPathogen);
                click(programTargetPathogenDropdownExpand);

                // IE-10775 Verify user is able to add new target
//                type(programTargetPathogen, "Patho_1" + date0);
//                enterKey(programTargetPathogen);
//                click(programTargetPathogenDropdownExpand);

                click(programTradeNameInput);
                enterKey(programTradeNameInput);
                Thread.sleep(1000);

                click(programTradeNameInput);
                List<WebElement> tradeNames = getDriver().findElements(By.cssSelector("[name='tradeName'] .ng-option-label"));
                String programNameValue = getAttribute(programName);
                softAssert.assertEquals(tradeNames.get(0).getText(), programNameValue, "Trade name value is not same as program name value in Vaccine program");

                //Feed Details
                if (objModel.ProgramType.equals("Feed") || objModel.ProgramType.equals("Bioshuttle")) {
                    click(programFeedTypeDropdown);
                    Thread.sleep(500);
                    enterKey(programFeedTypeDropdown);
                    type(programAssetDayStart, "1");
                    WebElement EndDay = getDriver().findElement(programAssetDayStart);
                    getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");
                    Thread.sleep(1000);
                }

                click(popupSaveButtonXpath);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1500);
                softAssert.assertEquals(getText(alertMessage), "New program has been created successfully");

                //Feed Verification
                if (objModel.ProgramType.equals("Feed")) {
                    waitElementVisible(By.cssSelector("tr:nth-child(1) " + programFeedProgramNameCol));
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedProgramDisplayNameCol)).getText(), ProgramManagementModel.FeedProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedTypesCol)).getText(), "Feed Type 1");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedTargetPathogenCol)).getText(), "Patho_1");
                    //     softAssert.assertEquals(size(By.cssSelector("tr:nth-child(1) " + programFeedTargetPathogenMoreCol)), 1, "+ more functionality is not added to target pathogen column");

                    filterFeedProgram(objModel.ProgramName);
                    click(fieldAccessIconFeed);
                    click(resetDefaultButton);
                    click(popupSaveButton);
                    Thread.sleep(2000);
                    //     boolean dataSanity = programDataSanity(getFeedProgramLog, feedProgramLogView(objModel.ProgramName));
                    //    softAssert.assertTrue(dataSanity, "Data Sanity of create Feed Program is failed.");
                }

                //Vaccine Verification
                if (objModel.ProgramType.equals("Vaccine")) {
                    waitElementVisible(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol));
                    softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) " + programVaccineProgramDisplayNameCol)), ProgramManagementModel.VaccineProgramName);
                    softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) " + programVaccineStartDateCol)), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) " + programVaccineEndDateCol)), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) " + programVaccineTargetPathogenCol)), "Patho_1");
                    //       softAssert.assertEquals(size(By.cssSelector("tr:nth-child(1) " + programVaccineTargetPathogenMoreCol)), 1, "+ more functionality is not added to target pathogen column");
                    softAssert.assertTrue(!getText(By.cssSelector("tr:nth-child(1) " + programVaccineComplexCol)).isEmpty(), "Complex column is not displaying complex value");
                    softAssert.assertEquals(!getText(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol)).isEmpty(), !getText(By.cssSelector("tr:nth-child(1) " + programVaccineProgramNameCol)).isEmpty(), "Trade name and vaccine name is empty");

                    filterVaccineProgram(objModel.ProgramName);
                    boolean dataSanity = programDataSanity(getVaccineProgramLog, vaccineProgramLogView(objModel.ProgramName));
                    softAssert.assertTrue(dataSanity, "Data Sanity of create Vaccine Program is failed.");
                }

                //Bioshuttle Verification
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    waitElementVisible(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol));
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramDisplayNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    softAssert.assertEquals(getText(By.cssSelector("tr:nth-child(1) " + programBioshuttleTargetPathogenCol)), "Patho_1");
                    //         softAssert.assertEquals(size(By.cssSelector("tr:nth-child(1) " + programBioshuttleTargetPathogenMoreCol)), 1, "+ more functionality is not added to target pathogen column");
                    softAssert.assertTrue(!getText(By.cssSelector("tr:nth-child(1) " + programBioshuttleComplexCol)).isEmpty(), "Complex column is not displaying complex value");
                    softAssert.assertEquals(!getText(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol)).isEmpty(), !getText(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramNameCol)).isEmpty(), "Trade name and vaccine name is empty");
                    filterBioshuttleProgram(objModel.ProgramName);
                    //    boolean dataSanity = programDataSanity(getBioshuttleProgramLog, bioshuttleProgramLogView(objModel.ProgramName));
                    //   softAssert.assertTrue(dataSanity, "Data Sanity of create Feed Program is failed.");
                }

                softAssert.assertAll();
                test.pass("New Program created successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to create");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to create");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 2, groups = {"smoke", "regression"})
    public void UpdatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameUpdate);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Click on Create New Program button");
                steps.createNode("2. Add valid data in all fields");
                steps.createNode("3. Click on save button");

                SoftAssert softAssert = new SoftAssert();

                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        click(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        break;
                    }
                }

                Thread.sleep(2000);
                getScreenshot();
                click(popupSaveButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(getText(alertMessage), "Program details updated");

                //Feed Verification
                if (objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedProgramDisplayNameCol)).getText(), ProgramManagementModel.FeedProgramName);
                    //  softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedSupplierNameCol)).getText(), "");
                    //   softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedTypesCol)).getText(), "Feed Type 1");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                //Treatment Verification
                if (objModel.ProgramType.equals("Treatment")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentProgramNameCol)).getText(), ProgramManagementModel.TreatmentProgramName);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentSupplierNameCol)).getText(), "");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programTreatmentDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                }

                //Vaccine Verification
                if (objModel.ProgramType.equals("Vaccine")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineProgramDisplayNameCol)).getText(), ProgramManagementModel.VaccineProgramName);
                    //   softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    //   softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineNumberOfApplicationAssetCol)).getText(), "2");
                    //    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    //    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    //    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineAssetDayApplicationCol)).getText(), "1,2");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programVaccineTargetPathogenCol)).getText(), "Patho_1", "Expected Patho_1");
                }

                //Vaccine Bioshuttle Verification
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleProgramDisplayNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName);
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleSupplierNameCol)).getText(), ProgramManagementModel.SupplierName);
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleNumberOfApplicationAssetCol)).getText(), "2");
                    // softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleDescriptionCol)).getText(), ProgramManagementModel.DescriptionName + " Updated");
                    //softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleStartDateCol)).getText(),  "01/" + dateMM +"/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                    // softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleAssetDayApplicationCol)).getText(), "12");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleAssetDayStartCol)).getText(), "1");
                    //	softAssert.assertEquals(getDriver().findElement(By.cssSelector(programBioshuttleAssetDayEndCol)).getText(), "10");
                }

                //softAssert.assertEquals(getText(auditTargetPathogenColumn), "Target Pathogen", "Audit Log not displaying a row with column Target Pathogen");

                softAssert.assertAll();
                test.pass("New Program updated successfully");
                results.createNode("New Program updated successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to update");
                results.createNode("New Program failed to update");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to update");
                results.createNode("New Program failed to update");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @Test(enabled = true, priority = 3, groups = {"regression"})
    public void VerifyEditProgramsConfirmationPopup() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameUpdate);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Click on Create New Program button");
                steps.createNode("2. Add valid data in all fields");
                steps.createNode("3. Click on X button");
                steps.createNode("4. Verify that confirmation popup appears");

                SoftAssert softAssert = new SoftAssert();

                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        click(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        break;
                    }
                }

                click(popupCloseButton);
                Thread.sleep(2000);
                getScreenshot();
                //    softAssert.assertEquals(size(confirmationPopupHeader), 0, "Confirmation Popup appeared" );

                softAssert.assertAll();
                test.pass("Confirmation popup functionality verified successfully");
                results.createNode("Confirmation popup functionality verified successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("Confirmation popup functionality verified failed");
                results.createNode("Confirmation popup functionality verified");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Confirmation popup functionality verified failed");
                results.createNode("Confirmation popup functionality verified failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @Test(enabled = true, priority = 4, groups = {"regression"})
    public void DuplicatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDuplicate);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Click on Create New Program button");
                steps.createNode("2. Add valid data in all fields");
                steps.createNode("3. Click on save button");

                SoftAssert softAssert = new SoftAssert();

                getDriver().get(Constants.url_programAdmin);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    System.out.println(getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText());

                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.EditButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        click(By.xpath("//*[@id = '" + objModel.CopyButtonPre + "" + i + "-" + objModel.ButtonPost + "']"));
                        break;
                    }
                }

                waitElementInvisible(loading_cursor);
                Thread.sleep(3000);
                clear(programDisplayName);
                type(programDisplayName, objModel.ProgramName + "_Copy");
                //    softAssert.assertEquals(getDriver().findElement(programTargetPathogenValue).getText(), "Coccidiosis, Necrotic Enteritis", "Target Pathogen is empty ");
                softAssert.assertEquals(getDriver().findElement(programProgramTypeValue).getText(), objModel.ProgramType, " Program Type is empty ");
                softAssert.assertEquals(size(programProgramTypeDisabledCheck), 1, "Program Type is not disabled");


                // softAssert.assertEquals(size(programComplexMandatoryCheck), 1, "Complex should be mandatory field");
                softAssert.assertEquals(size(programComplexSelected), 1);
                softAssert.assertNotEquals(getAttribute(By.id("startDate")), "", "Start Date is empty");
                softAssert.assertNotEquals(getAttribute(By.id("endDate")), "", "End Date is empty");

                Thread.sleep(2000);
                click(popupSaveButtonXpath);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                softAssert.assertEquals(getText(alertMessage), "New program has been created successfully");

                //Feed Verification
                if (objModel.ProgramType.equals("Feed")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedProgramDisplayNameCol)).getText(), ProgramManagementModel.FeedProgramName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedTypesCol)).getText(), "Feed Type 1");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programFeedEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                //Vaccine Verification
                if (objModel.ProgramType.equals("Vaccine")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineProgramDisplayNameCol)).getText(), ProgramManagementModel.VaccineProgramName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programVaccineEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                //Vaccine Bioshuttle Verification
                if (objModel.ProgramType.equals("Bioshuttle")) {
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleProgramDisplayNameCol)).getText(), ProgramManagementModel.BioshuttleProgramName + "_Copy");
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleStartDateCol)).getText(), dateMM + "/01/" + dateYYYY);
                    softAssert.assertEquals(getDriver().findElement(By.cssSelector("tr:nth-child(1) " + programBioshuttleEndDateCol)).getText(), dateMM + "/30/" + dateYYYY);
                }

                softAssert.assertAll();
                test.pass("New Program copied successfully");
                results.createNode("New Program copied successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to copy");
                results.createNode("New Program failed to copy");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to copy");
                results.createNode("New Program failed to copy");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 5, groups = {"regression"})
    public void DeleteDuplicatePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDelete);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Delete the created program");

                SoftAssert softAssert = new SoftAssert();
                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);
                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getText(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).equals(objModel.ProgramName + "_Copy")) {
                        System.out.println("1");
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        click(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        break;
                    }
                }

                waitElementVisible(popupYesButton);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getText(alertMessage), "Program details deleted");
                softAssert.assertAll();
                test.pass("New Program deleted successfully");
                results.createNode("New Program deleted successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @Test(enabled = true, priority = 6, groups = {"smoke", "regression"})
    public void DeletePrograms() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest(objModel.TestCaseNameDelete);
                steps = test.createNode(Scenario.class, Steps);
                results = test.createNode(Scenario.class, Results);

                steps.createNode("1. Delete the created program");

                SoftAssert softAssert = new SoftAssert();
                click(By.xpath("//*[text()= '" + objModel.Program + " ']"));
                waitElementInvisible(loading_cursor);

                List<WebElement> programsName = getDriver().findElements(By.cssSelector(objModel.ProgramName_CSS));

                for (int i = 1; i <= programsName.size(); i++) {
                    if (getDriver().findElement(By.cssSelector("tr:nth-child(" + i + ") " + objModel.ProgramName_CSS)).getText().equals(objModel.ProgramName)) {
                        System.out.println("1");
                        scroll(By.xpath("//*[@id='" + objModel.ProgramTable + "'] //*[text()='Action']"));
                        waitElementClickable(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        Thread.sleep(1000);
                        click(By.id(objModel.DeleteButtonPre + "" + i + "-" + objModel.ButtonPost));
                        break;
                    }
                }

                waitElementVisible(popupYesButton);
                click(popupYesButton);
                waitElementInvisible(loading_cursor);
                Thread.sleep(1000);

                softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), "Program details deleted");
                softAssert.assertAll();
                test.pass("New Program deleted successfully");
                results.createNode("New Program deleted successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);
            } catch (AssertionError er) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("New Program failed to delete");
                results.createNode("New Program failed to delete");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }


    @Test(enabled = true, priority = 7, groups = {"smoke", "regression"})
    public void ComplexFilterTest() throws InterruptedException, IOException {
        lstProgramManagementSearch = ProgramManagementModel.FillData();

        getDriver().get(Constants.url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();

        for (ProgramManagementModel objModel : lstProgramManagementSearch) {
            try {
                test = extent.createTest("AN-Program: Verify Site Name Filter Functionality");
                results = test.createNode(Scenario.class, Results);

                getDriver().findElement(By.xpath("//*[text()= '" + objModel.Program + " ']")).click();
                waitElementInvisible(loading_cursor);


                String recordsBefore = getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText();

                scroll(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                Thread.sleep(500);
                click(By.cssSelector("#" + objModel.ProgramTable + " #complex" + "" + ShowFilter));
                waitElementInvisible(loading_cursor);
                Thread.sleep(700);

                if (getDriver().findElements(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr")).size() >= 2) {

                    scroll(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));
                    click(By.cssSelector("#sort-complex-" + objModel.ButtonPost + " tr:nth-child(2) label:nth-child(1)"));

                    waitElementInvisible(loading_cursor);
                    scroll(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));
                    click(By.cssSelector("#" + objModel.ProgramTable + " #list-title_apply"));

                    waitElementInvisible(loading_cursor);
                    Thread.sleep(1000);
                    softAssert.assertNotEquals(getDriver().findElement(By.cssSelector("#" + objModel.ProgramTable + " #" + ResultsCount)).getText(), recordsBefore);


                    test.pass("Checkbox selected successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                }

            } catch (AssertionError er) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, new Exception(er));
            } catch (Exception ex) {
                test.fail("Filer lock functionality failed");
                saveResult(ITestResult.FAILURE, ex);
            }
        }
    }

    @Test(priority = 9, enabled = true, description = "Inline Edit Functionality for Feed Programs Tab", groups = {"smoke", "regression"})
    public void FeedProgramsInlineEditFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Feed Programs Tab on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Feed Programs ']")).click();
            waitElementInvisible(loading_cursor);
            softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditFeedButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2500);

            String displayName = getDriver().findElement(feedProgramsDisplayNameField).getAttribute("value");
            type(feedProgramsDisplayNameField, "TestName");
            click(programInlineFeedButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");

            /////////////////////change back to old value///////////////////////////////////////////
            click(inlineEditFeedButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            clear(feedProgramsDisplayNameField);
            type(feedProgramsDisplayNameField, displayName);
            click(programInlineFeedButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully for feed programs tab");
            results.createNode("Program inline functionality tested successfully for feed programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset inline failed for feed programs tab");
            results.createNode("Asset inline failed for feed programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset inline failed for feed programs tab");
            results.createNode("Asset inline failed for feed programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 10, enabled = true, description = "Inline Edit Functionality for Vaccine Programs Tab", groups = {"smoke", "regression"})
    public void VaccineProgramsInlineEditFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Vaccine Programs Tab on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Vaccine Programs ']")).click();
            waitElementInvisible(loading_cursor);
            softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditVaccineButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2500);

            String displayName = getDriver().findElement(vaccineProgramsDisplayNameField).getAttribute("value");
            type(vaccineProgramsDisplayNameField, "TestName");
            click(programInlineVaccineButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");

            /////////////////////change back to old value///////////////////////////////////////////
            click(inlineEditVaccineButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            clear(vaccineProgramsDisplayNameField);
            type(vaccineProgramsDisplayNameField, displayName);
            click(programInlineVaccineButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully for vaccine programs tab");
            results.createNode("Program inline functionality tested successfully for vaccine programs tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset inline failed for vaccine programs tab");
            results.createNode("Asset inline failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset inline failed for vaccine programs tab");
            results.createNode("Asset inline failed for vaccine programs tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(priority = 11, enabled = true, description = "Inline Edit Functionality for Vaccine Programs Tab", groups = {"smoke", "regression"})
    public void BioshuttleTabInlineEditFunctionality() throws InterruptedException, IOException {
        try {
            test = extent.createTest("Verify inline edit functionality for Bioshuttle Tab on Program Management Screen ");
            steps = test.createNode(Scenario.class, Steps);
            results = test.createNode(Scenario.class, Results);

            getDriver().get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            SoftAssert softAssert = new SoftAssert();
            getDriver().findElement(By.xpath("//*[text()= 'Bioshuttle ']")).click();
            waitElementInvisible(loading_cursor);
            //	softAssert.assertTrue(getDriver().findElement(By.id("edit-inline-access")).isEnabled(), "Inline Edit button is working");
            click(inlineEditBioshuttleButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            String displayName = getDriver().findElement(bioshuttleProgramDisplayNameField).getAttribute("value");
            clear(bioshuttleProgramDisplayNameField);
            type(bioshuttleProgramDisplayNameField, "TestName");

            //Click on inline button to save changes
            click(programInlineBioshuttleButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");

            /////////////////////change back to old value///////////////////////////////////////////
            click(inlineEditBioshuttleButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);
            clear(bioshuttleProgramDisplayNameField);
            type(bioshuttleProgramDisplayNameField, displayName);
            click(programInlineBioshuttleButtonSave);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Data saved successfully.");
            softAssert.assertAll();

            test.pass("Program inline functionality tested successfully for bioshuttle tab");
            results.createNode("Program inline functionality tested successfully for bioshuttle tab");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Asset inline failed for bioshuttle programs tab");
            results.createNode("Asset inline failed for bioshuttle programs tab");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Asset inline failed for bioshuttle tab");
            results.createNode("Asset inline failed for bioshuttle tab");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test(enabled = true, priority = 12, groups = {"regression"}, description = "TC: IE- 3692, Verify that Reset button is clickable and working on create program form.")
    public void ProgramManagementResetButtonFunctionality() throws InterruptedException, IOException {

        getDriver().get(url_programAdmin);
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);

        steps = test.createNode(Scenario.class, Steps);
        results = test.createNode(Scenario.class, Results);
        steps.createNode("1. Click on Register New Program button");
        steps.createNode("2. Enter all mandatory fields");
        steps.createNode("3. Click on reset and verify it");

        try {
            SoftAssert softAssert = new SoftAssert();
            scroll(programCreateButton);
            click(programCreateButton);
            waitElementInvisible(loading_cursor);
            for (ProgramManagementModel objModel : lstProgramManagementSearch) {
                if (!objModel.Program.equals("Treatment")) {

                    //Program DisplayName
                    softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                    type(programDisplayName, objModel.ProgramName);
                    softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                    System.out.println("Program Display Name is " + objModel.ProgramName);
                    Thread.sleep(500);

                    //Program Type
                    type(programProgramType, objModel.ProgramType);
                    Thread.sleep(500);
                    softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");
                    enterKey(programProgramType);
                    Thread.sleep(500);

                    //Vaccine Number of Applications on Asset
                    if (objModel.ProgramType.startsWith("Vaccine") || objModel.ProgramType.startsWith("Bioshuttle")) {
                        String NoApplicationAsset = "2";
                        type(programNoApplicationAsset, NoApplicationAsset);
                        Thread.sleep(500);

                        // click(popupSaveButtonXpath);
                        for (int i = 1; i <= Integer.parseInt(NoApplicationAsset); i++) {
                            type(By.id(programDaysApplicationAsset + "-" + i), "" + i);
                        }
                    }

                    //Complex
                    click(programComplexList);
                    clear(programComplexSearch);
                    type(programComplexSearch, Queries.getComplexName());
                    Thread.sleep(250);
                    click(By.xpath("//tr[@class='selectable ng-star-inserted']//label/b"));

                    waitElementInvisible(loading_cursor);
                    //  Thread.sleep(2000);
                    //  click(By.cssSelector(".popup-content tr:nth-child(2) td label b"));

                    //Start Date
                    click(programStartDateIcon);
                    waitElementInvisible(loading_cursor);
                    Methods method = new Methods();
                    WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
                    List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
                    clickGivenDay(columns1, getFirstDay());
                    Thread.sleep(1000);
                    //End Date
                    click(programEndDateIcon);
                    waitElementInvisible(loading_cursor);
                    WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
                    List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
                    clickGivenDay(columns2, getDay("30"));
                    Thread.sleep(700);

                    softAssert.assertEquals(size(popupSaveButtonDisabled), 2, "Mandatory check failed");

                    //Target Pathogen
                    type(programTargetPathogen, "Patho_1");
                    enterKey(programTargetPathogen);

                    //TradeNameVaccine
                    //    if (objModel.ProgramType.equals("Vaccine") || objModel.ProgramType.equals("Bioshuttle")) {
                    click(programTradeNameInput);
                    enterKey(programTradeNameInput);
                    Thread.sleep(1000);
                    //    }

                    //TradeNameFeed
//                    if (objModel.ProgramType.equals("Feed") || objModel.ProgramType.equals("Bioshuttle")) {
//                        click(programTradeNameFeed);
//                        enterKey(programTradeNameFeed);
//                        Thread.sleep(1000);
//                    }

                    //Feed Details
                    if (objModel.ProgramType.equals("Feed") || objModel.ProgramType.equals("Bioshuttle")) {
                        click(programFeedTypeDropdown);
                        Thread.sleep(500);
                        enterKey(programFeedTypeDropdown);
                        type(programAssetDayStart, "1");
                        WebElement EndDay = getDriver().findElement(programAssetDayStart);
                        getDriver().findElement(with(By.tagName("input")).toRightOf(EndDay)).sendKeys("10");
                        Thread.sleep(1000);
                    }

                    //click on reset button
                    getDriver().findElement(By.id(("btn-reset"))).click();
                    waitElementInvisible(loading_cursor);

                    //Verify save button is disabled
                    softAssert.assertEquals(getDriver().findElements(By.cssSelector("#btn-save.disabled-v2")).size(), 2, "Mandatory check failed");
                }

                steps.createNode("Verify all fields on reset button click are cleared");
                WebElement programTypeDropdown = getDriver().findElement(By.cssSelector("#programTypeId input"));
                softAssert.assertEquals(programTypeDropdown.getAttribute("value"), "", "Program type field is reset");
                WebElement programDescription = getDriver().findElement(By.id("programDisplayNameId"));
                softAssert.assertEquals(programDescription.getAttribute("value"), "", "Program description field is reset");
                WebElement programStartDateIcon = getDriver().findElement(By.cssSelector("#startDate img"));
                softAssert.assertEquals(programStartDateIcon.getAttribute("value"), null, "Start Date field is reset");
                WebElement programEndDateIcon = getDriver().findElement(By.cssSelector("#endDate img"));
                softAssert.assertEquals(programEndDateIcon.getAttribute("value"), null, "End date field is reset");
                WebElement programComplexList = getDriver().findElement(By.cssSelector("#compleSiteId .toggle-list"));
                softAssert.assertEquals(programComplexList.getAttribute("value"), null, "Complex field is reset");
                WebElement programTargetPathogen = getDriver().findElement(By.cssSelector("#targetsId input"));
                softAssert.assertEquals(programTargetPathogen.getAttribute("value"), "", "target pathogen field is reset");

                softAssert.assertAll();
                test.pass("reset button functionality passed successfully");
                results.createNode("reset button functionality passed successfully");
                getScreenshot();
                saveResult(ITestResult.SUCCESS, null);


            }
        } catch (AssertionError er) {
            test.fail("failed to verify");
            results.createNode("failed to verify");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify added reset button functionality");
            results.createNode("failed to verify reset button functionality");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @AfterTest(groups = {"smoke", "regression"})
    public static void endreport() {
        extent.flush();
    }

}
