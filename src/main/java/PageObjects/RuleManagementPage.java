package PageObjects;

import MiscFunctions.DB_Config_DW;
import MiscFunctions.Methods;
import MiscFunctions.NavigateToScreen;
import groovyjarjarantlr4.v4.tool.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.url_ruleAdmin;
import static MiscFunctions.Constants.url_ruleAdmin;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Queries.*;
import static PageObjects.BasePage.*;

public class RuleManagementPage {

    public WebDriver driver;
    public static By ruleManagementTitle = By.id("Rule Management");
    public static String ruleManagementTable = "rule-management-log";
    public static By createRuleButton = By.id("create-form");


    public static By rulePopupHeaderTitle = By.cssSelector(".popup-header");
    //    public static By ruleTypeDropDownInput = By.xpath("//ng-select[@id='ruleType']");
    public static By ruleTypeDropDownInput = By.cssSelector("#ruleType input");
    public static By ruleDropDownInput = By.cssSelector("#ruleName input");
    public static By ruleNameInput = By.cssSelector("input#rule");
    public static By ruleStartDateCalendarIcon = By.cssSelector("#ruleStartDate img");
    public static By ruleEndDateCalendarIcon = By.cssSelector("#ruleEndDate img");
    public static By ruleDescriptionInput = By.id("ruleDescriptionId");
    public static By ruleSiteIDDropDownExpand = By.cssSelector("#siteId .down");
    public static By ruleSiteIDDropDownSearchField = By.id("siteId_search");

    public static By startDateCalendarIcon = By.cssSelector("#startDate img");
    public static By endDateCalendarIcon = By.cssSelector("#endDate img");

    public static String RuleName = dateYYYYMMDD + "_TestRule_" + date0;
    public static String UpdatedRuleName = dateYYYYMMDD + "_TestRuleNew_" + date0;


    public RuleManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateRuleManagementScreen() throws IOException, InterruptedException {
        NavigateToScreen.navigate(url_ruleAdmin, "Rule Management", ruleManagementTitle);
    }

    public void lockFilterFunctionality() throws IOException, InterruptedException {
        Lock(ruleManagementTable, "Rule Management", 0);
    }

    public void wildcardFunctionality() throws InterruptedException, IOException {
        Wildcard(ruleManagementTable, "Rule Management", 0);
    }

    public void sortingFunctionality() throws InterruptedException, IOException {
        Sorting(ruleManagementTable, "Rule Management", 0);
    }

    public void paginationFunctionality() throws InterruptedException, IOException {
        Pagination(ruleManagementTable, "Rule Management");
    }

    public void rowsPerPageFunctionality() throws InterruptedException, IOException {
        RowsPerPage1(ruleManagementTable);
    }

    public void verifyColumnsFunctionality(String[] columnNamesExpected) throws InterruptedException, IOException {
        VerifyAllColumns(ruleManagementTable, columnNamesExpected);
    }

    public void verifyFieldAccessFunctionality() throws InterruptedException, IOException {
        FieldAccessFunctionality( ruleManagementTable);
        KeyColumnsCheck(ruleManagementTable, new String[]{"RULE NAME", "RULE TYPE", "RULE START DATE", "RULE END DATE"});
    }

    public void createRule() throws InterruptedException, IOException {
        try {
            test = extent.createTest("IE-10973: Verify user can create a Rule");
            SoftAssert softAssert = new SoftAssert();

            click(createRuleButton);

            waitElementVisible(rulePopupHeaderTitle);
            softAssert.assertEquals(getText(rulePopupHeaderTitle), "Create Rule");
            click(ruleTypeDropDownInput);
            Thread.sleep(700);
            enterKey(ruleTypeDropDownInput);
            click(ruleDropDownInput);
            Thread.sleep(700);
            enterKey(ruleDropDownInput);
            Thread.sleep(500);

            type(ruleNameInput, RuleName);

            click(ruleStartDateCalendarIcon);
            Thread.sleep(1000);
            Methods method = new Methods();
            WebElement dateWidgetFrom = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ruleStartDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsFrom = dateWidgetFrom.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsFrom, getFirstDay());
            Thread.sleep(500);

            click(ruleEndDateCalendarIcon);
            Thread.sleep(1000);
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ruleEndDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsTo = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsTo, getLastDay());
            Thread.sleep(500);

            type(ruleDescriptionInput, "This is test rule");

            click(ruleSiteIDDropDownExpand);
            Thread.sleep(1000);
            scroll(ruleSiteIDDropDownSearchField);
            type(ruleSiteIDDropDownSearchField, getFarmNameAssignedToUser());
            Thread.sleep(1000);
            click(By.cssSelector("label b"));

            click(startDateCalendarIcon);
            Thread.sleep(500);
            WebElement dateWidgetFrom1 = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsFrom1 = dateWidgetFrom1.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsFrom1, getFirstDay());

            click(endDateCalendarIcon);
            Thread.sleep(1000);
            WebElement dateWidgetTo1 = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsTo1 = dateWidgetTo1.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsTo1, getLastDay());
            Thread.sleep(1000);

            click(popupSaveButton);
            softAssert.assertEquals(getText(alertMessage), "New rule has been created successfully");
            Thread.sleep(3000);

            ResultSet getRuleRow = DB_Config_DW.getStmt().executeQuery("Select * from [Rule_instance] where RULE_INSTANCE_NAME = '" + RuleName + "' and isActive = '1' and isDeleted = '0'");
            while (getRuleRow.next()) {
                softAssert.assertEquals(getRuleRow.getString("RULE_INSTANCE_NAME"), RuleName, "Rule Name not found");
                softAssert.assertTrue(getRuleRow.getString("EFFECTIVE_START_DATE").contains(dateYYYYMM + "-01"), "Eff. Start Date not found");
                softAssert.assertTrue(getRuleRow.getString("EFFECTIVE_END_DATE").contains(dateYYYYMM + "-28"), "Eff. End Date not found");
                softAssert.assertEquals(getRuleRow.getString("RULE_DESCRIPTION"), "This is test rule", "Description not found");
                softAssert.assertTrue(getRuleRow.getString("START_DATE").contains(dateYYYYMM + "-01"), "Start Date not found");
                softAssert.assertTrue(getRuleRow.getString("END_DATE").contains(dateYYYYMM + "-28"), "End Date not found");
            }

            softAssert.assertAll();
            test.pass("New Rule created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Rule failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Rule failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void editRule() throws InterruptedException, IOException {
        try {
            test = extent.createTest("IE-10974: Verify user can edit a Rule");
            SoftAssert softAssert = new SoftAssert();

            for (int i = 1; i <= 100; i++) {
                System.out.println(getText(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1) label")) + " ---> " + RuleName);
                if (getText(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1) label")).equals(RuleName)) {
                    click(By.id("edit-rule-" + i + "-ruleManagement"));
                    break;
                }
            }

            waitElementVisible(rulePopupHeaderTitle);
            softAssert.assertEquals(getText(rulePopupHeaderTitle), "Edit Rule");

            clear(ruleNameInput);
            type(ruleNameInput, UpdatedRuleName);

            click(ruleStartDateCalendarIcon);
            Thread.sleep(1000);
            Methods method = new Methods();
            WebElement dateWidgetFrom = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ruleStartDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsFrom = dateWidgetFrom.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsFrom, "02");
            Thread.sleep(500);

            click(ruleEndDateCalendarIcon);
            Thread.sleep(1000);
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ruleEndDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsTo = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsTo, "20");
            Thread.sleep(500);

            clear(ruleDescriptionInput);
            type(ruleDescriptionInput, "This is updated test rule");

            click(startDateCalendarIcon);
            Thread.sleep(500);
            WebElement dateWidgetFrom1 = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsFrom1 = dateWidgetFrom1.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsFrom1, "02");

            click(endDateCalendarIcon);
            Thread.sleep(1000);
            WebElement dateWidgetTo1 = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> calendarButtonsTo1 = dateWidgetTo1.findElements(By.tagName("button"));
            clickGivenDay(calendarButtonsTo1, "20");
            Thread.sleep(1000);

            click(popupSaveButton);
            softAssert.assertEquals(getText(alertMessage), "Rule has been updated successfully");

            System.out.println("Select * from [Rule_instance] where RULE_INSTANCE_NAME like '%" + RuleName + "%' and isActive = '1' and isDeleted = '0'");
            ResultSet getRuleRow = DB_Config_DW.getStmt().executeQuery("Select * from [Rule_instance] where RULE_INSTANCE_NAME like '%" + RuleName + "%' and isActive = '1' and isDeleted = '0'");
            while (getRuleRow.next()) {
                softAssert.assertEquals(getRuleRow.getString("RULE_INSTANCE_NAME"), UpdatedRuleName);
                //     softAssert.assertTrue( getRuleRow.getString("EFFECTIVE_START_DATE").contains(dateYYYYMM+"-02"), "Effective Start Date not found");
                //     softAssert.assertTrue(getRuleRow.getString("EFFECTIVE_END_DATE").contains(dateYYYYMM + "-20"), "Effective End Date not found");
                System.out.println("--1->" + getRuleRow.getString("RULE_DESCRIPTION"));
                softAssert.assertEquals(getRuleRow.getString("RULE_DESCRIPTION"), "This is updated test rule");
                //        softAssert.assertTrue( getRuleRow.getString("START_DATE").contains(dateYYYYMM+"-02"), "Start Date not found");
                //        softAssert.assertTrue(getRuleRow.getString("END_DATE").contains(dateYYYYMM + "-20"), "End Date not found");
            }

            softAssert.assertAll();
            test.pass("Rule edited successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Rule failed to edit");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Rule failed to edit");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void viewRule() throws InterruptedException, IOException {
        try {
            test = extent.createTest("IE-10974: Verify user can view a Rule");
            SoftAssert softAssert = new SoftAssert();
            Thread.sleep(5000);
            for (int i = 1; i <= 100; i++) {
                System.out.println("Text1: " + getText(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1) label")));

                if (getText(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1) label")).equals(UpdatedRuleName)) {
                    click(By.id("view-rule-gram-" + i + "-ruleManagement"));
                    break;
                }
            }

            softAssert.assertEquals(getText(rulePopupHeaderTitle), "View Rule");
            softAssert.assertEquals(size(alertMessage), 0, "Error alert occured");
            softAssert.assertEquals(getAttribute(ruleNameInput), UpdatedRuleName);
            click(popupCloseButton);

            softAssert.assertAll();
            test.pass("Rule viewed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Rule failed to view");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Rule failed to view");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    public void deleteRule() throws IOException {
        try {
            test = extent.createTest("IE-10974: Verify user can delete a Rule");
            SoftAssert softAssert = new SoftAssert();

            String recordsBeforeDeletion = getText(By.id(ResultsCount));

            for (int i = 1; i <= 100; i++) {
                if (getText(By.cssSelector("tr:nth-child(" + i + ") td:nth-child(1) label")).equals(UpdatedRuleName)) {
                    click(By.id("delete-rule-management-" + i + "-ruleManagement"));
                    break;
                }
            }

            waitElementVisible(popupYesButton);
            click(popupYesButton);
            softAssert.assertEquals(getText(alertMessage), "Rule Deleted Successfully");
            Thread.sleep(5000);
            String recordsAfterDeletion = getText(By.id(ResultsCount));

            //  softAssert.assertNotEquals(Integer.parseInt(recordsAfterDeletion), Integer.parseInt(recordsBeforeDeletion), "Record not subtracted from Results Found Count");

            ResultSet getRuleRow = DB_Config_DW.getStmt().executeQuery("Select * from [Rule_instance] where RULE_INSTANCE_NAME like '%" + UpdatedRuleName+"%'");
            while (getRuleRow.next()) {
                softAssert.assertEquals(getRuleRow.getString("isActive"), "0", "Expected 0 in isActive");
                softAssert.assertEquals(getRuleRow.getString("isDeleted"), "1", "Expected 1 in isDeleted");
            }

            softAssert.assertNotEquals(recordsBeforeDeletion, recordsAfterDeletion, "Row not deleted");
            softAssert.assertAll();
            test.pass("Rule deleted successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Rule failed to delete");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Rule failed to delete");
            saveResult(ITestResult.FAILURE, ex);
        }
    }
}
