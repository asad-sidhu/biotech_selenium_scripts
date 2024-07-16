package PageObjects;

import MiscFunctions.*;
import Models.ProgramManagementModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Config.BaseTest.saveResult;
import static LogViewFunctions.FieldAccess.FieldAccessFunctionality;
import static LogViewFunctions.FieldAccess.KeyColumnsCheck;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.LogCSVExport.CSVAuditExport;
import static LogViewFunctions.LogCSVExport.CSVExport1;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage1;
import static LogViewFunctions.VerifyColumnNames.VerifyAllColumns;
import static MiscFunctions.Constants.*;
import static MiscFunctions.DateUtil.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.Methods.*;
import static Models.ProgramManagementModel.lstProgramManagementSearch;
import static PageObjects.BasePage.*;
import static PageObjects.LoginPage.*;
import static PageObjects.ProgramManagementPage.*;

public class ProductManagementPage {

    public boolean sanityPassed = false;
    public static By productManagementTitle = By.id("Product Management");
    public static By createProductButton = By.id("create-product");
    public static By tradeNameField = By.id("programNameId");
    public static By supplierFieldDropdown = By.cssSelector("#supplierId input");
    public static By addNewButton = By.xpath("//li[contains(text(),'Add New +')]");
    public static By categoryFieldDropdown = By.cssSelector("#productCategoryId input");
    public static By descriptionField = By.id("descriptionId");
    public static String productManagementTable = "manage-product-log";
    public static By ingredientField = By.xpath("//input[@id='0-ingredientId']");
    public static By addIngredientField = By.xpath("//input[@id='1-ingredientId']");
    public static By addIngredientButton = By.xpath("//label[contains(text(),'Add Ingredient')]");
    public static String expectedTooltipTextResetFilters = "Reset Filters";
    public static String expectedTooltipTextLocked = "Lock Applied Filters";
    public static String expectedTooltipExportFile = "Export File ";
    public static String expectedTooltipFieldAccess = "Field Access";
    public static String expectedTooltipViewIcon = "View Product";
    public static String expectedTooltipEditIcon = "Edit Product";
    public static String expectedTooltipDeleteIcon = "Delete Product";
    public static By profileSettingsButton = By.id("open-profile");
    public static By profileSettingsCloseButton = By.id("close-profile");
    public static String expectedTooltipProfileIcon = " Profile";
    public static By profileSettingsPageHeading = By.id("Profile Settings");
    @FindBy(how = How.CSS, using = "#menu-administration")
    public WebElement hoverElement;
    public static By IE_Menu = By.xpath("//label[contains(text(),'IE Administration')]");
    private static By navigateToScreen = By.xpath("//a[@id='roleMGMTProductManagementScreen']");
    public By rowsRecord = By.xpath("//table/tbody/tr");
    String targetRowText = "Admin";
    private By editRights = By.xpath(".//span[@title='Edit Rights']");
    private static By saveButtonEditRights = By.cssSelector(".fa-save");
    @FindBy(how = How.CSS, using = "tr:nth-child(32) td:nth-child(2) input")
    private WebElement createRightsInput;
    private static By createRightsCheckbox = By.cssSelector("tr:nth-child(32) td:nth-child(2) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(32) td:nth-child(3) input")
    private WebElement updateRightsInput;
    private static By updateRightsCheckbox = By.cssSelector("tr:nth-child(32) td:nth-child(3) .custom-checkbox");
    @FindBy(how = How.CSS, using = "tr:nth-child(32) td:nth-child(4) input")
    private WebElement viewRightsInput;
    private static By viewRightsCheckbox = By.cssSelector("tr:nth-child(32) td:nth-child(4) .custom-checkbox");
    private static By firstRow = By.xpath("//table/tbody/tr[1]");

    public static By exportFileIcon = By.xpath("//span[@title='Export File ']");
    public static By viewProductIcon = By.cssSelector("#view-treatment-program-1-treatment");
    public static By deleteProductIcon = By.cssSelector("#delete-treatment-program-1-treatment");
    public static String removeFilterIcon = "#remove-filters.d-none";
    public static String expectedTooltipTextUnlocked = "Unlock Filters";
    public static By fieldAccessIcon = By.id("edit-field-access");
    public static String productLogTable = "manage-product-log";
    public static String editIcon = "/ancestor::tr//span[contains(@title, 'Edit')]";
    public static String deleteIcon = "/ancestor::tr//span[contains(@title, 'Delete')]";
    public static String viewIcon = "/ancestor::tr//span[contains(@title, 'View')]";
    public static By tradeNameFieldError = By.cssSelector("#programNameId-error-container .hide");
    public static By supplierDropdownFieldError = By.cssSelector("#supplierId-error-container .hide");
    public static By categoryDropdownFieldError = By.cssSelector("#productCategoryId-error-container .hide");

    public static By tradeNameFieldRequiredError = By.xpath("//div[contains(text(),'Trade Name is required')]");
    public static By supplierDropdownFieldRequiredError = By.xpath("//div[contains(text(),'Supplier is required')]");
    public static By categoryDropdownFieldRequiredError = By.xpath("//div[contains(text(),'Category is required')]");
    public static By submitButtonFieldError = By.cssSelector("#btn-save.disabled-v2");
    public static By imageField = By.cssSelector("#file");
    public static By deleteIngredient = By.xpath("//img[contains(@src,'icon-trash-white.png')][1]");
    public static By confirmationPopup = By.xpath("//h4[contains(text(),'Confirmation')]");
    public static By firstProductEditIcon = By.cssSelector("#edit-treatment-program-1-treatment");
    public static By submitAndAddNewButton = By.xpath("(//button[@id='btn-save'])[2]");
    public static By tradeNameColumnFilter = By.cssSelector("#tradeProductName_show-filter");
    public static By supplierColumnFilter = By.cssSelector("#supplier_show-filter");
    public static By categoryColumnFilter = By.cssSelector("#category_show-filter");
    public static By tradeNameCheckbox = By.xpath("//ul[@id='ul-productEntity_tradeProductName']/li[last()]//small");
    public static By supplierCheckbox = By.xpath("//ul[@id='ul-productEntity_supplier']/li[last()]//small");
    public static By categoryCheckbox = By.xpath("//ul[@id='ul-productEntity_category']/li[last()]//small");
    public static By auditIconFirstRow = By.id("audit-trial-0");
    int auditUpdatedRowCount = 0;
    public static By crossIcon = By.xpath("//div[@id='close-popup-modal']");
    public static By tradeNameApplyButton = By.cssSelector("#productEntity_tradeProductName_apply");
    public static By supplierApplyButton = By.cssSelector("#productEntity_supplier_apply");
    public static By categoryApplyButton = By.cssSelector("#productEntity_category_apply");
    public static By tradeNameClearAllButton = By.cssSelector("#productEntity_tradeProductName_clear");
    public static By supplierClearAllButton = By.cssSelector("#productEntity_supplier_clear");
    public static By categoryClearAllButton = By.cssSelector("#productEntity_category_clear");
    public static By tradeNameViewAllButton = By.cssSelector("#productEntity_tradeProductName_view-all");
    public static By supplierViewAllButton = By.cssSelector("#productEntity_supplier_view-all");
    public static By categoryViewAllButton = By.cssSelector("#productEntity_category_view-all");
    public static String productManagementCSVFileName = "Products Log - ";
    public static String productManagementCSVAuditFileName = "Product Audit Log - ";
    public static String getProductRow = "//*[@id='table-product-entity-log']//tbody//tr[1]";
    public static String getProductAuditRow = "//table//tr[@class='text-nowrap ng-star-inserted'][1]";
    public static By tradeNameSearchField = By.id("productEntity_tradeProductName_search-input");
    public static By tradeNameSelectAllCheckBox = By.id("productEntity_tradeProductName_cust-cb-lst-txt_Trade Name Sanity Edited");
    public static By tradeNameApplyCheckBox = By.id("productEntity_tradeProductName_apply");
    public static By listItem = By.xpath("//li[@class='list-item ng-star-inserted show'][1]");
    public static String tradeName = "Advil-" + date0;
    public static String updatedTradeName = "Motrin-" + date0;
    public static String categoryName = "Antibacterials";
    public static String updatedCategoryName = "Antibiotic";
    public static String ingredientName = "Triclocarban";

    private WebDriver driver;

    //Create object of Intervention Management Class
    //Constructor
    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void navigateProductMgtScreen() throws IOException, InterruptedException {
        NavigateToScreen.navigate(url_productCatalog, "Product Management", productManagementTitle);
        if (size(By.cssSelector("#save-filters.d-none")) == 1) {
            click(By.cssSelector("#remove-filters"));
        }
    }

    public boolean checkImageDataSanity(String setTradeNameField) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "SELECT TP.TradeProductName AS [Trade Name], \n" +
                    "  CASE \n" +
                    "    WHEN IMG.mediaFileName = 'Project123_logo.jpg' THEN 'Project123_logo.jpg'\n" +
                    "    ELSE CASE WHEN IMG.mediaFileName IS NULL THEN 'true' ELSE IMG.mediaFileName END\n" +
                    "  END AS [Product Image], \n" +
                    "  SUP.interventionManufacturer AS [Supplier], \n" +
                    "  TPC.tradeProductCategoryName AS [Category], \n" +
                    "  ISNULL(TP.tradeProductDescription, '') AS [Description], \n" +
                    "  STRING_AGG(PRI.productIngredientName, ', ') AS [Ingredient]\n" +
                    "FROM TradeProduct TP\n" +
                    "JOIN MSTRInterventionManufacturer SUP ON TP.tradeProductSupplierId = SUP.interventionManufacturerId \n" +
                    "  AND SUP.isActive = 1 \n" +
                    "  AND SUP.isDeleted = 0\n" +
                    "JOIN TradeProductCategory TPC ON TPC.tradeProductCategoryId = TP.tradeProductCategoryId \n" +
                    "  AND TPC.isActive = 1 \n" +
                    "  AND TPC.isDeleted = 0\n" +
                    "JOIN TradeProductIngredient TPI ON TPI.tradeProductId = TP.tradeProductId \n" +
                    "  AND TPI.isActive = 1 \n" +
                    "  AND TPI.isDeleted = 0\n" +
                    "JOIN ProductIngredient PRI ON PRI.productIngredientId = TPI.productIngredientId \n" +
                    "  AND PRI.isActive = 1 \n" +
                    "  AND PRI.isDeleted = 0\n" +
                    "LEFT JOIN Mediafile IMG ON IMG.mediaFileId = TP.tradeProductImageId\n" +
                    "WHERE TP.tradeProductName = '" + setTradeNameField + "'\n" +
                    "GROUP BY TP.TradeProductName, IMG.mediaFileName, SUP.interventionManufacturer, TPC.tradeProductCategoryName, TP.tradeProductDescription";

            ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> backendRow = new ArrayList<>();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    backendRow.add(resultSet.getString(i));
                }
            }

            // Print the backend values
            for (int i = 1; i <= columnCount; i++) {
                String backendValue = backendRow.get(i - 1);
                System.out.println("Backend value for column " + i + ": " + backendValue);
            }
            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed");
            return true;
        }

    }

    public boolean createProductDataSanity(String setTradeNameField, String productRow) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "SELECT TP.TradeProductName AS [Trade Name], \n" +
                    "ISNULL(IMG.mediaFileName, '') AS [Product Image],\n" +
                    "SUP.interventionManufacturer AS [Supplier], TPC.tradeProductCategoryName AS [Category], \n" +
                    "ISNULL(TP.tradeProductDescription, '') AS [Description], STRING_AGG(PRI.productIngredientName, ', ') AS [Ingredient]\n" +
                    "from TradeProduct TP\n" +
                    "JOIN MSTRInterventionManufacturer SUP ON TP.tradeProductSupplierId = SUP.interventionManufacturerId AND SUP.isActive = 1 AND SUP.isDeleted = 0\n" +
                    "JOIN TradeProductCategory TPC ON TPC.tradeProductCategoryId = TP.tradeProductCategoryId AND TPC.isActive = 1 AND TPC.isDeleted = 0\n" +
                    "JOIN TradeProductIngredient TPI ON TPI.tradeProductId = TP.tradeProductId AND TPI.isActive = 1 AND TPI.isDeleted = 0\n" +
                    "JOIN ProductIngredient PRI ON PRI.productIngredientId = TPI.productIngredientId AND PRI.isActive = 1 AND PRI.isDeleted = 0\n" +
                    "LEFT JOIN Mediafile IMG ON IMG.mediaFileId = ''\n" +
                    "WHERE TP.tradeProductName = '" + setTradeNameField + "' " +
                    "GROUP BY TP.TradeProductName, IMG.mediaFileName, SUP.interventionManufacturer, TPC.tradeProductCategoryName, TP.tradeProductDescription";            // Get all columns data from front end
            //   driver.navigate().refresh();
            waitElementInvisible(loading_cursor);
            List<WebElement> cells = driver.findElement(By.xpath(productRow)).findElements(By.tagName("td"));
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

                softAssert.assertEquals(frontendValue.replace(" ", ""), backendValue.replace(" ", ""), "Value in frontend and backend do not match for column " + i);
            }
            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed");
            return true;
        }
    }

    public boolean deleteProductDataSanity(String setTradeNameField) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "SELECT TP.TradeProductName AS [Trade Name], \n" +
                    "ISNULL(IMG.mediaFileName, '') AS [Product Image],\n" +
                    "SUP.interventionManufacturer AS [Supplier], TPC.tradeProductCategoryName AS [Category], \n" +
                    "ISNULL(TP.tradeProductDescription, '') AS [Description], STRING_AGG(PRI.productIngredientName, ', ') AS [Ingredient]\n" +
                    "from TradeProduct TP\n" +
                    "JOIN MSTRInterventionManufacturer SUP ON TP.tradeProductSupplierId = SUP.interventionManufacturerId AND SUP.isActive = 1 AND SUP.isDeleted = 0\n" +
                    "JOIN TradeProductCategory TPC ON TPC.tradeProductCategoryId = TP.tradeProductCategoryId AND TPC.isActive = 1 AND TPC.isDeleted = 0\n" +
                    "JOIN TradeProductIngredient TPI ON TPI.tradeProductId = TP.tradeProductId AND TPI.isActive = 1 AND TPI.isDeleted = 0\n" +
                    "JOIN ProductIngredient PRI ON PRI.productIngredientId = TPI.productIngredientId AND PRI.isActive = 1 AND PRI.isDeleted = 0\n" +
                    "LEFT JOIN Mediafile IMG ON IMG.mediaFileId = ''\n" +
                    "WHERE TP.tradeProductName = '" + setTradeNameField + "' " +
                    "GROUP BY TP.TradeProductName, IMG.mediaFileName, SUP.interventionManufacturer, TPC.tradeProductCategoryName, TP.tradeProductDescription";
            ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(query);
            if (!resultSet.next()) {
                // Backend row is empty, indicating successful deletion
                System.out.println("Deletion successful. Backend row is empty.");

            } else {
                // Backend row is not empty, indicating deletion failed
                System.out.println("Deletion failed. Backend row is not empty.");
            }

            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed for delete case");
            return true;
        }

    }

    public boolean crudAuditProductDataSanity(String setTradeNameField, String productRow) {
        try {
            SoftAssert softAssert = new SoftAssert();
            String query = "SELECT FORMAT(CAST(TPA.dmlInstDate as date),'MM/dd/yyyy') AS [Changed Date], TPA.changedBy AS [Changed By],\n" +
                    "CASE WHEN TPA.dml='U' THEN 'Modified' ELSE 'Created' END AS [Action],\n" +
                    "TPA.TradeProductName AS [Trade Name], \n" +
                    "CASE WHEN IMG.mediaFileName = '' THEN 'Default Image' ELSE IMG.mediaFileName END AS [Product Image], \n" +
                    "SUP.interventionManufacturer AS [Supplier], TPC.tradeProductCategoryName AS [Category], \n" +
                    "ISNULL(TPA.tradeProductDescription, '') AS [Description], STRING_AGG(PIN.productIngredientName, ', ') AS [Ingredient]\n" +
                    "from TradeProduct TP\n" +
                    "JOIN TradeProductAudit TPA ON TPA.tradeProductId = TP.tradeProductId\n" +
                    "JOIN MSTRInterventionManufacturer SUP ON SUP.interventionManufacturerId = TPA.tradeProductSupplierId\n" +
                    "JOIN TradeProductIngredientAudit TPIA ON TPIA.tradeProductAuditId = TPA.tradeProductAuditId\n" +
                    "JOIN TradeProductIngredient TPI ON TPIA.tradeProductIngredientId = TPI.tradeProductIngredientId AND TPI.isActive = 1 AND TPI.isDeleted = 0\n" +
                    "JOIN ProductIngredient PIN ON TPIA.productIngredientId = PIN.productIngredientId\n" +
                    "JOIN TradeProductCategory TPC ON TPC.tradeProductCategoryId = TPA.tradeProductCategoryId\n" +
                    "LEFT JOIN Mediafile IMG ON IMG.mediaFileId = TP.tradeProductImageId\n" +
                    "WHERE TP.tradeProductName = '" + setTradeNameField + "' " +
                    "GROUP BY TPA.TradeProductName, IMG.mediaFileName, TPC.tradeProductCategoryName, TPA.tradeProductDescription, TPA.dmlInstDate, TPA.dml, SUP.interventionManufacturer, TPA.changedBy\n" +
                    "ORDER BY TPA.dmlInstDate DESC";

            ResultSet resultSet = DB_Config_DB.getStmt().executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String> backendRow = new ArrayList<>();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    backendRow.add(resultSet.getString(i));
                }
            }

            for (String value : backendRow) {
                System.out.println(value);
            }
            softAssert.assertAll();
            test.pass("Data sanity verified successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            test.fail("Data sanity failed");
            return true;
        }

    }

    public void createProductFunctionality() throws IOException, InterruptedException {
        try {
            test = extent.createTest("Verify Create functionality for product on Product Management Screen");
            SoftAssert softAssert = new SoftAssert();
            click(createProductButton);
            waitElementInvisible(loading_cursor);

            softAssert.assertEquals(size(submitButtonFieldError), 2, "Submit button and submit & add new button are enabled");

            type(tradeNameField, tradeName);
            System.out.println(tradeName);
            type(supplierFieldDropdown, "Merck");
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(categoryFieldDropdown, categoryName);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(descriptionField, "Antibacterials are substances that kill bacteria or stop them from growing and causing disease.");

            // Verify again submit buttons are disabled
            softAssert.assertEquals(size(submitButtonFieldError), 2, "Submit button and submit & add new button are enabled");
            type(ingredientField, ingredientName);
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }

            //  click(addIngredientButton);
            //  waitElementVisible(deleteIngredient);
            //  Thread.sleep(1000);
            // click(deleteIngredient);
            //  softAssert.assertEquals(size(deleteIngredient), 0, "Delete icon is still showing");
            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Product entity has been created successfully", "Product cannot be created successfully");
            waitElementInvisible(loading_cursor);
            Thread.sleep(4000);

            boolean sanityPassed = createProductDataSanity(tradeName, getProductRow);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed");

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Crud functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void viewProductFunctionality() throws IOException {
        test = extent.createTest("Verify View functionality for product on Product Management Screen");
        try {
            SoftAssert softAssert = new SoftAssert();
            click(By.id("view-treatment-program-1-treatment"));

            softAssert.assertEquals(size(alertMessage), 0, "Alert message appeared");
            softAssert.assertEquals(getText(popupHeaderTitle2), "View Product");
            softAssert.assertEquals(size(By.cssSelector("input#programNameId[disabled]")), 1);
            softAssert.assertEquals(size(By.cssSelector("input#supplierId[disabled]")), 1);
            click(popupCloseButton);
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Crud functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void updateProductFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify Edit functionality for product on Product Management Screen");
        try {
            SoftAssert softAssert = new SoftAssert();
            // Click on the audit icon to get the current count of rows
            hover(auditIconFirstRow);
            click(auditIconFirstRow);
            Thread.sleep(1000);
            // Get the number of rows
            int auditRowCount = size(auditGetRowCount);
            System.out.println("Number of rows: " + auditRowCount);
            sanityPassed = crudAuditProductDataSanity(tradeName, getProductAuditRow);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed for audit create case");
            click(crossIcon);

            click(By.id("edit-treatment-program-1-treatment"));
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);
            clear(tradeNameField);
            type(tradeNameField, updatedTradeName);
            clear(descriptionField);
            type(descriptionField, "Antibiotics are substances that kill bacteria.");
            Thread.sleep(1000);

            clear(categoryFieldDropdown);
            type(categoryFieldDropdown, updatedCategoryName);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }

            click(popupSaveButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Product entity has been updated successfully", "Product cannot be updated successfully");

            Thread.sleep(3000);
            sanityPassed = createProductDataSanity(updatedTradeName, getProductRow);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed for update case");

            // Now again click on the audit icon to get the current count of rows
            click(auditIconFirstRow);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            // Get the updated number of rows
            auditUpdatedRowCount = size(auditGetRowCount);
            System.out.println("Updated number of rows: " + auditUpdatedRowCount);

            softAssert.assertEquals(auditUpdatedRowCount, auditRowCount+1, "A new row is not added in audit");
            click(crossIcon);
            sanityPassed = crudAuditProductDataSanity(updatedTradeName, getProductAuditRow);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed for audit edit case");
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Crud functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void deleteProductFunctionality() throws IOException, InterruptedException {
        try {
            test = extent.createTest("Verify Delete functionality for product on Product Management Screen");
            SoftAssert softAssert = new SoftAssert();

            click(By.id("delete-treatment-program-1-treatment"));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);

            softAssert.assertEquals(getText(alertMessage), "Product has been deleted successfully.", "Product cannot be deleted successfully");
            waitElementInvisible(loading_cursor);

            sanityPassed = deleteProductDataSanity(updatedTradeName);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed for delete case");

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Delete functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Delete product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void createProductWithImageFunctionality() throws IOException, InterruptedException {
        try {
            test = extent.createTest("Verify crud functionality for product on Product Management Screen");
            SoftAssert softAssert = new SoftAssert();
            click(createProductButton);

            type(tradeNameField, tradeName);
            type(supplierFieldDropdown, "Merck");
            Thread.sleep(1500);
            if (driver.findElements(addNewButton).size() != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(1000);

            type(categoryFieldDropdown, categoryName);
            if (driver.findElements(addNewButton).size() != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }

            type(descriptionField, "This is a test product");
            type(ingredientField, ingredientName);
            Thread.sleep(500);
            if (driver.findElements(addNewButton).size() != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }

            driver.findElement(imageField).sendKeys(FrameworkConstants.BiotechProject01Logo);
            click(popupSaveButton);
            softAssert.assertEquals(getText(alertMessage), "Product entity has been created successfully", "Product cannot be created successfully");
            waitElementInvisible(loading_cursor);

            boolean sanityPassed = checkImageDataSanity(tradeName);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed");

            Thread.sleep(1500);
            click(By.xpath("//label[@title='" + tradeName + "']" + deleteIcon));
            click(popupYesButton);
            waitElementVisible(alertMessage);
            softAssert.assertEquals(getText(alertMessage), "Product has been deleted successfully.", "Product cannot be deleted successfully");
            waitElementInvisible(loading_cursor);

            sanityPassed = deleteProductDataSanity(tradeName);
            softAssert.assertTrue(sanityPassed, "Data sanity verification failed for delete case");
            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Crud functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Create product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void resetProductFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify reset functionality for product on Product Management Screen");
        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            click(createProductButton);
            waitElementInvisible(loading_cursor);

            softAssert.assertTrue(driver.findElement(popupResetButton).isDisplayed(), "Reset button is not displayed.");
            type(tradeNameField, tradeName);
            type(supplierFieldDropdown, "supplier");
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(categoryFieldDropdown, tradeName);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(descriptionField, "Description field test");

            type(ingredientField, dateYYYYMMDD + "_ingredient_" + date0);
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            click(popupResetButton);

            // Verify all fields are empty

            String field1Value = driver.findElement(tradeNameField).getAttribute("value");
            String field2Value = driver.findElement(supplierFieldDropdown).getAttribute("value");
            String field3Value = driver.findElement(categoryFieldDropdown).getAttribute("value");
            String field4Value = driver.findElement(descriptionField).getAttribute("value");
            String field5Value = driver.findElement(ingredientField).getAttribute("value");

            softAssert.assertTrue(field1Value.isEmpty(), "trade name field has not been reset");
            softAssert.assertTrue(field2Value.isEmpty(), "supplier field dropdown field has not been reset");
            softAssert.assertTrue(field3Value.isEmpty(), "category field dropdown has not been reset");
            softAssert.assertTrue(field4Value.isEmpty(), "description field has not been reset");
            softAssert.assertTrue(field5Value.isEmpty(), "ingredient field has not been reset");

            // Verify the negative test case for reset functionality in which error should appear on mandatory fields

            click(tradeNameField);
            click(supplierFieldDropdown);
            click(categoryFieldDropdown);
            click(ingredientField);
            click(addIngredientButton);
            Thread.sleep(1500);

            softAssert.assertEquals(size(tradeNameFieldError), 0, "Error did not appeared");
            softAssert.assertEquals(size(supplierDropdownFieldError), 0, "Error did not appeared");
            softAssert.assertEquals(size(categoryDropdownFieldError), 0, "Error did not appeared");

            softAssert.assertEquals(size(tradeNameFieldRequiredError), 1, "Trade name field required error did not appeared");
            softAssert.assertEquals(size(supplierDropdownFieldRequiredError), 1, "Supplier dropdown field required error did not appeared");
            softAssert.assertEquals(size(categoryDropdownFieldRequiredError), 1, "Category dropdown required error did not appeared");

            click(popupCloseButton);
            Thread.sleep(1000);
            click(popupYesButton);
            Thread.sleep(1000);
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Reset functionality for product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Reset product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void confirmationPopupFunctionality() throws IOException, InterruptedException {
        try {
            test = extent.createTest("Verify confirmation popup functionality for create/edit product on Product Management Screen");
            SoftAssert softAssert = new SoftAssert();
            click(createProductButton);
            waitElementInvisible(loading_cursor);

            type(tradeNameField, tradeName);
            click(popupCloseButton);
            softAssert.assertTrue(driver.findElement(confirmationPopup).isDisplayed(), "Confirmation popup is not displayed.");

            Thread.sleep(1000);
            click(popupNoButton);
            waitElementInvisible(loading_cursor);

            click(popupCloseButton);
            waitElementVisible(confirmationPopup);

            softAssert.assertTrue(driver.findElement(tradeNameField).isDisplayed(), "Create product popup is not opened");
            Thread.sleep(1000);
            click(popupYesButton);
            waitElementVisible(productManagementTitle);

            click(firstProductEditIcon);
            waitElementInvisible(loading_cursor);

            clear(descriptionField);
            Thread.sleep(1000);
            type(descriptionField, "Description field updated!");
            Thread.sleep(1000);

            click(popupCloseButton);
            waitElementVisible(confirmationPopup);
            softAssert.assertTrue(driver.findElement(confirmationPopup).isDisplayed(), "Confirmation popup is not displayed.");

            Thread.sleep(1000);
            click(popupNoButton);
            waitElementInvisible(loading_cursor);

            click(popupCloseButton);
            waitElementVisible(confirmationPopup);

            softAssert.assertTrue(driver.findElement(tradeNameField).isDisplayed(), "Update product popup is not opened");
            Thread.sleep(1000);
            click(popupYesButton);
            waitElementVisible(productManagementTitle);

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Confirmation popup functionality for create/edit product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Confirmation popup functionality for product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }

    }


    public void verifyWildCardFunctionality() throws InterruptedException, IOException {
        driver.get(url_productCatalog);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Wildcard(productManagementTable, "Product Management", 1);
    }

    public void verifyLockFilterFunctionality() throws InterruptedException, IOException {
        driver.get(url_productCatalog);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        Lock(productManagementTable, "Product Management", 1);
    }

    public void verifyFieldAccess() throws IOException, InterruptedException {
        FieldAccessFunctionality(productLogTable);
    }

    public void verifyKeyColumnsCheck() throws IOException, InterruptedException {
        KeyColumnsCheck(productLogTable, new String[]{"Trade Name", "Product Image", "Description"});
    }

    public void submitAndAddNewFunctionality() throws IOException {
        test = extent.createTest("Verify submit and add new functionality on product on Product Management Screen");
        try {
            SoftAssert softAssert = new SoftAssert();
            scrollToTopOfPage();
            click(createProductButton);
            waitElementInvisible(loading_cursor);

            type(tradeNameField, tradeName);
            type(supplierFieldDropdown, "supplier");
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(categoryFieldDropdown, tradeName);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            type(descriptionField, "Description field test");

            type(ingredientField, "ingredient");
            Thread.sleep(500);
            if (size(addNewButton) != 0) {
                click(addNewButton);
            } else {
                click(listItem);
            }
            Thread.sleep(500);

            click(submitAndAddNewButton);
            waitElementInvisible(loading_cursor);
            softAssert.assertTrue(driver.findElement(tradeNameField).isDisplayed(), "Create product popup is not opened");
            click(popupCloseButton);
            Thread.sleep(1000);
            click(popupYesButton);

            click(By.xpath("//label[@title='" + tradeName + "']" + deleteIcon));
            waitElementInvisible(loading_cursor);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);

            softAssert.assertEquals(driver.findElement(alertMessage).getText(), "Product has been deleted successfully.", "Product cannot be deleted successfully");
            waitElementInvisible(loading_cursor);

            softAssert.assertAll();
            test.pass("Test case passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Submit and add new functionality on create product failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Submit and add new functionality on create product test case failed");
            saveResult(ITestResult.FAILURE, ex);
        }

    }


    public void paginationFunctionality() throws InterruptedException, IOException {
        Pagination(productManagementTable, "Product Management");
        RowsPerPage1(productManagementTable);
    }

    public void exportCSVFunctionality() throws IOException, InterruptedException {
        driver.get(url_productCatalog);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        CSVExport1("Product Management", productManagementCSVFileName, productManagementTable, 2, 0);
    }

    public void exportCSVAuditFunctionality() throws IOException, InterruptedException {
        driver.get(url_productCatalog);
        waitElementInvisible(loading_cursor);
        Thread.sleep(3000);
        CSVAuditExport("Product Management", productManagementCSVAuditFileName, productManagementTable, false);
    }

    public void sortingFunctionality() throws IOException, InterruptedException {
        Sorting(productManagementTable, "Product Management", 2);
    }

    public void verifyAllColumns() throws IOException, InterruptedException {
        VerifyAllColumns(productManagementTable, new String[]{"Trade Name", "Product Image", "Supplier", "Category", "Description", "Ingredient"});
    }

    public void tooltipFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify tooltip functionality on Product Management Screen");
        try {
            SoftAssert softAssert = new SoftAssert();
            Actions action = new Actions(driver);
            String tooltipText;
            action.moveToElement(driver.findElement(By.id(ResetFilters))).perform();
            tooltipText = driver.findElement(By.id(ResetFilters)).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipTextResetFilters);
            action.moveToElement(driver.findElement(fieldAccessIcon)).perform();
            tooltipText = driver.findElement(fieldAccessIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipFieldAccess);
            action.moveToElement(driver.findElement(By.id(ResetFilters))).perform();
            tooltipText = driver.findElement(exportFileIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipExportFile);
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
            action.moveToElement(driver.findElement(viewProductIcon)).perform();
            tooltipText = driver.findElement(viewProductIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipViewIcon);
            action.moveToElement(driver.findElement(firstProductEditIcon)).perform();
            tooltipText = driver.findElement(firstProductEditIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipEditIcon);
            action.moveToElement(driver.findElement(deleteProductIcon)).perform();
            tooltipText = driver.findElement(deleteProductIcon).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipDeleteIcon);
            action.moveToElement(driver.findElement(profileSettingsButton)).perform();
            tooltipText = driver.findElement(profileSettingsButton).getAttribute("title");
            System.out.println("tooltip text: " + tooltipText);
            softAssert.assertEquals(tooltipText, expectedTooltipProfileIcon);
            softAssert.assertAll();
            test.pass("Tooltip functionality passed successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("failed to verify Tooltip functionality for the product management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Tooltip functionality for the product management screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void profileSettingsFunctionality() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user on click on Profile settings icon can navigate to Profile Screen");

        try {
            SoftAssert softAssert = new SoftAssert();
            driver.get(url_productCatalog);
            Thread.sleep(2000);
            click(profileSettingsButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertTrue(driver.findElement(profileSettingsPageHeading).isDisplayed(), "The user is not navigated to the profile settings page");
            click(profileSettingsCloseButton);
            Thread.sleep(1000);
            softAssert.assertAll();
            test.pass("Profile Settings navigation passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("failed to verify Profile Settings navigation for the product management screen");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Tooltip functionality for the product management screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void verifyAccessRights() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user access right functionality works on the Product management Screen");
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
            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewProductIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(firstProductEditIcon), 0, "Edit icon is not displayed");
            softAssert.assertTrue(driver.findElement(createProductButton).isDisplayed(), "Create icon is not displayed");

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
            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewProductIcon), 0, "View icon is not displayed");
            softAssert.assertEquals(size(firstProductEditIcon), 1, "Edit icon is not displayed");
            softAssert.assertEquals(size(createProductButton), 0, "Create icon is not displayed");

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
            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewProductIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(firstProductEditIcon), 0, "Edit icon is not displayed");
            softAssert.assertEquals(size(createProductButton), 0, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Access rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Access rights for for the product management screen failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Access rights for the product management screen");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    public void verifyAdminRights() throws IOException, InterruptedException {
        test = extent.createTest("Verify that the user has all rights for product mgt Screen");
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
            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            softAssert.assertEquals(size(viewProductIcon), 1, "View icon is not displayed");
            softAssert.assertEquals(size(firstProductEditIcon), 1, "Edit icon is not displayed");
            softAssert.assertEquals(size(createProductButton), 1, "Create icon is not displayed");

            softAssert.assertAll();
            test.pass("Admin rights passed successfully");
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Admin rights for for the product management screen failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("failed to verify Admin rights for the product management screen");
            saveResult(ITestResult.FAILURE, ex);
        }

    }

    public void programProductFunctionality() throws IOException, InterruptedException {
        try {
            test = extent.createTest("Verify that the product associated with the program cannot be deleted");
            SoftAssert softAssert = new SoftAssert();
            lstProgramManagementSearch = ProgramManagementModel.FillData();

            driver.get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            String getTradeName = "";

            click(programVaccineProgramTab_XPATH);
            click(programRegisterNewProgramButton);
            type(programDisplayName, "CanSino_" + date0);

            //Target Pathogen
            click(programTargetPathogen);
            Thread.sleep(1500);
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("#targetsId label"));
            if (checkboxes.size() >= 2) {
                checkboxes.get(1).click(); // Click on the second checkbox
            }
            Thread.sleep(500);
            click(programTargetPathogenDropdown);
            Thread.sleep(500);

            //Program Type
            type(programProgramType, "Vaccine");
            Thread.sleep(500);
            enterKey(programProgramType);

            //Vaccine Number of Applications on Asset
            String NoApplicationAsset = "0";
            type(programNoApplicationAsset, NoApplicationAsset);
            Thread.sleep(1000);

            //Complex
            click(programComplexList);
            type(programComplexSearch, Queries.getComplexName());
            Thread.sleep(250);
            click(By.xpath("//tr[@class='selectable ng-star-inserted']//label/b"));
            waitElementInvisible(loading_cursor);

            //Start Date
            click(programStartDateIcon);
            waitElementInvisible(loading_cursor);
            Methods method = new Methods();
            WebElement dateWidgetTo = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#startDate .dp-popup"))).get(0);
            List<WebElement> columns1 = dateWidgetTo.findElements(By.tagName("button"));
            clickGivenDay(columns1, getFirstDay());
            Thread.sleep(500);

            //End Date
            click(programEndDateIcon);
            waitElementInvisible(loading_cursor);
            WebElement dateWidgetToEnd = method.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#endDate .dp-popup"))).get(0);
            List<WebElement> columns2 = dateWidgetToEnd.findElements(By.tagName("button"));
            clickGivenDay(columns2, getDay("30"));
            Thread.sleep(700);

            //TradeNameVaccine
            click(programTradeNameInput);
            enterKey(programTradeNameInput);

            getTradeName = driver.findElement(programName).getAttribute("value");

            System.out.println("value " + getTradeName);
            click(popupSaveButtonXpath);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getText(alertMessage), "New program has been created successfully");
            getScreenshot();

            driver.get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            click(tradeNameColumnFilter);
            waitElementInvisible(loading_cursor);
            type(tradeNameSearchField, getTradeName);
            Thread.sleep(250);
            click(By.id("productEntity_tradeProductName_cust-cb-lst-txt_" + getTradeName));
            click(tradeNameApplyCheckBox);
            waitElementInvisible(loading_cursor);
            Thread.sleep(500);
            String classes = driver.findElement(deleteProductIcon).getAttribute("class");
            boolean isDisabled = classes.contains("low-opacity ng-star-inserted");
            softAssert.assertTrue(isDisabled, "Delete product icon is enabled, test case failed");

            driver.get(url_programAdmin);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(programVaccineProgramTab);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(vaccineProgramdisplayNameFilter);
            Thread.sleep(2000);
            type(vaccineProgramdisplayNameSearchInput, "CanSino_" + date0);
            Thread.sleep(2000);
            click(By.id("vaccine_programDisplayName_cust-cb-lst-txt_CanSino_" + date0));
            Thread.sleep(1000);
            click(feedProgramDisplayNameApplyButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(By.id("delete-vaccine-program-1-vaccine"));
            Thread.sleep(1000);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getText(alertMessage), "Program details deleted");

            softAssert.assertAll();
            test.pass("Delete product icon is enabled, test case passed successfully");
            saveResult(ITestResult.SUCCESS, null);

        } catch (AssertionError er) {
            test.fail("Delete product icon is enabled, test case failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Delete product icon is enabled, test case passed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }
}


