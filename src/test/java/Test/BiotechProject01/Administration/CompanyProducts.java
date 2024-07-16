package Test.BiotechProject01.Administration;

import Config.BaseTest;
import MiscFunctions.DateUtil;
import MiscFunctions.FrameworkConstants;
import MiscFunctions.NavigateToScreen;
import Test.BiotechProject01.Login.LoginTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static ExtentReports.ExtentReport.initReport;
import static LogViewFunctions.FilterLock.Lock;
import static LogViewFunctions.FilterSort.Sorting;
import static LogViewFunctions.FilterWildcard.Wildcard;
import static LogViewFunctions.Pagination.Pagination;
import static LogViewFunctions.RowsPerPage.RowsPerPage_;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.spark;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.waitElementInvisible;
import static Models.OrganizationManagementModel.*;
import static Models.OrganizationManagementModel.lstOrgAlertMessages;
import static PageObjects.BarCodeManagementPage.barcodeManagmentTitle;
import static PageObjects.BasePage.*;
import static PageObjects.CompanyProductsPage.*;
import static PageObjects.CompanyProductsPage.CompanyProductsTableName;
import static PageObjects.OrganizationManagementPage.*;

public class CompanyProducts extends BaseTest {

    @BeforeTest
    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Company_Products"+ DateUtil.date+".html");
//        spark.config().setReportName("Company Products Test Report");
        initReport("Administration_Company_Products");
    }

    @BeforeClass
    public void Login() throws InterruptedException, IOException {
        LoginTest.login();
        NavigateToScreen.navigate(url_productCatalog, "Company Products",  companyProductsManagmentTitle);
    }

    @Test (enabled= true, priority= 1)
    public void CreateAlliedPartnerOrganization() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-1: Verify user can create New Allied Partner Organization");

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            String recordsCountBefore = getText(By.id(ResultsCount));
            click(orgCreateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(orgTypeDropDownExpand);
            Thread.sleep(750);
            getDriver().findElement(orgTypeInput).sendKeys("Allied Partner");
            getDriver().findElement(orgTypeInput).sendKeys(Keys.ENTER);
            Thread.sleep(750);
            clear(orgNameInput);
            type(orgNameInput, AlliedOrganizationName);
//			Allow Domains Start
            click(orgAllowDomainsExpand);
            Thread.sleep(750);
            click(selectAllCheckBox);
            Thread.sleep(750);
            click(orgAllowDomainsExpand);
            Thread.sleep(750);
            // Allow Domains End
            click(popupNextButton);
            Thread.sleep(700);
            type(orgMaxUsersInput, "10");
            //	Role Category Start
            click(roleCategoryExpand);
            Thread.sleep(1000);
            click(selectAllCheckBox);
            Thread.sleep(750);
            click(roleCategoryExpand);
            Thread.sleep(750);
            //	Role Category End
            Thread.sleep(700);
            type(orgMaxUsersInput, "10");
            click(orgSystemRolesExpand);
            Thread.sleep(700);
            getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
            click(orgSystemRolesExpand);

            click(orgReportRolesExpand);
            Thread.sleep(700);
            getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
            click(orgReportRolesExpand);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(getText(alertMessage), lstOrgAlertMessages.get(0));
            String recordsCountAfter = getText(By.id(ResultsCount));
            softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);
            softAssert.assertAll();
            test.pass("New Allied Organization created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("New AlliedOrganization creation failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("New Allied Organization creation failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

    @Test(enabled= true, priority= 2)
    public void AddProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-02: Verify user can add/remove product for Allied Partner Organization");
            SoftAssert softAssert = new SoftAssert();

            if (size(alertClose)!=0) {
                click(alertClose);
            }

            searchCreatedOrg(AlliedOrganizationName, By.xpath("//*[@title = 'Manage Products'][@id = 'edit-orgn-1']"));
            getScreenshot();
            Thread.sleep(1500);
            getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.BiotechProject01Logo);
            softAssert.assertEquals(size(orgUploadProductImageSize), 0, "Product not added successfully");

            click(orgRemoveUploadedProduct);
            softAssert.assertEquals(size(orgUploadProductImageSize), 1, "Product not removed successfully");
            getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.BiotechProject01Logo);
            type(orgAddProductName, "Allied Product 1");
            type(orgAddProductDescription, "Product for Allied Organization");
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6));
            softAssert.assertAll();
            test.pass("Product added successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("Product failed to add successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed to add successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 3)
    public void VerifyProductInCompanyProductsScreen() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-03: Verify product added from Organization Management screen is reflected on Company Products screen");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            for (int i=1;i<getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
                if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
                    softAssert.assertFalse(getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(""), "Product name is empty");
                    softAssert.assertFalse(getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(5) label")).getText().equals(""), "Product description is empty");
                    softAssert.assertAll();
                    test.pass("Product reflected successfully");
                    getScreenshot();
                    saveResult(ITestResult.SUCCESS, null);
                    break;
                }
            }
        }catch(AssertionError er){
            test.fail("Product did not reflected successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed to add successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 4)
    public void AddProductInCompanyProductsScreen() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-04: Verify user can add product in Company Products screen");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            click(CompanyProductCreateNewButton);
            waitElementInvisible(loading_cursor);

            getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.BiotechProject01Logo);
            softAssert.assertEquals(size(orgUploadProductImage), 0, "Product not added successfully");

            click(orgRemoveUploadedProduct);
            softAssert.assertEquals(size(orgUploadProductImage), 1, "Product not removed successfully");

            getDriver().findElement(orgUploadProductImage).sendKeys(FrameworkConstants.BiotechProject01Logo);
            type(CompanyProductNameInput, AlliedOrganizationName);
            getDriver().findElement(CompanyProductNameInput).sendKeys(Keys.ENTER);
            type(orgAddProductName, AlliedOrganizationNameCompanyProduct);
            type(orgAddProductDescription, "Product for Allied Organization");
            click(popupSaveButtonXpath);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6));
            softAssert.assertAll();
            test.pass("Product added successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("Product failed to create");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed to create");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 5)
    public void VerifyProductInOrganizationScreen() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-05: Verify product added from Company Products is reflected on Organization Management screen screen");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            searchCreatedOrg(AlliedOrganizationName, By.xpath("//*[@title = 'Manage Products'][@id = 'edit-orgn-1']"));

            softAssert.assertEquals(size(orgGetTotalCreatedProducts), 2, "Product created from Company Product screen is not reflected on Organization Screen");
            softAssert.assertAll();
            test.pass("Product reflected successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        }catch(AssertionError er){
            test.fail("Product did not reflected successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed to add successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 6)
    public void DeleteProductsAlliedPartnerOrganization() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-06: Verify user can delete product for Allied Partner Organization");
            SoftAssert softAssert = new SoftAssert();

            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            scroll(orgDeleteUploadedProduct);
            Thread.sleep(800);
            click(orgDeleteUploadedProduct);
            waitElementVisible(popupYesButton);
            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(6));
            test.pass("Product deleted successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("Product failed to deleted successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed to deleted successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 7)
    public void EditProductInCompanyProductsScreen() throws IOException {
        try{
            test = extent.createTest("AN-CP-07: Verify product can be edited in Company Product Screen");
            SoftAssert softAssert = new SoftAssert();

            getDriver().get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            for (int i=1;i<getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
                if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
                    int j = i-1;
                    click(By.id(CompanyNameProductEditButton+""+j));
                    waitElementInvisible(loading_cursor);
                    break;
                }
            }

            type(orgAddProductDescription, "Product for Allied Organization Description");
            waitElementClickable(popupSaveButtonXpath);
            click(popupSaveButtonXpath);
            waitElementInvisible(loading_cursor);
            waitElementVisible(alertMessage);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(8));

            softAssert.assertAll();
            test.pass("Product updated successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        }catch(AssertionError er){
            test.fail("Product failed update successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product failed update successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= false, priority= 8)
    public void DeleteProductInCompanyProductsScreen() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-08: Verify product can be deleted in Company Product Screen");
            SoftAssert softAssert = new SoftAssert();

            if (size(alertClose) != 0) {
                click(alertClose);
            }

            for (int i=1;i<=getDriver().findElements(By.cssSelector("tr td:nth-child(1)")).size(); i++) {
                if (getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(2) label")).getText().equals(AlliedOrganizationName)) {
                    scroll(By.id(CompanyNameProductDeleteButton+""+i));
                    Thread.sleep(1000);
                    click(By.id(CompanyNameProductDeleteButton+""+i));
                    waitElementInvisible(loading_cursor);
                    break;
                }
            }

            click(popupYesButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(7));

            softAssert.assertAll();
            test.pass("Product deleted successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);

        }catch(AssertionError er){
            test.fail("Product did not deleted successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Product did not deleted successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 9)
    public void DeleteAlliedOrganization() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-09: Verify Organization can be deleted and verify it from table and user dropdown as well");

            getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            waitElementVisible(orgTitle);
            Thread.sleep(2000);

            String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();

            searchCreatedOrg(AlliedOrganizationName, organizationDeleteButton);

            Thread.sleep(1000);
            click(confirmationYes);
            waitElementVisible(alertMessage);
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(5));
            Thread.sleep(3500);
            String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
            softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter);

            softAssert.assertAll();
            test.pass("Organization deleted and verified successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("Organization failed to delete");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Organization failed to delete");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (enabled= true, priority= 10)
    public void TestFilterCompanyProducts() throws InterruptedException, IOException {
        try{
            test = extent.createTest("AN-CP-10: Verify Allied Company and Product Name filter functionality");

            getDriver().get(url_productCatalog);
            waitElementInvisible(loading_cursor);
            Thread.sleep(2000);

            SoftAssert softAssert = new SoftAssert();

            String recordsCountBefore = getDriver().findElement(By.id(ResultsCount)).getText();

            click(By.id(productAlliedCompany+""+ShowFilter));
            waitElementInvisible(loading_cursor);
            click(By.cssSelector("#ul-"+productAlliedCompany+" li:nth-child(2) label"));
            click(By.id(productAlliedCompany+""+ApplyFilter));
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            String recordsCountAfter = getDriver().findElement(By.id(ResultsCount)).getText();
            softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter, "Allied Company filter failed to apply");
            click(By.id(productAlliedCompany+""+ClearFilter));
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);

            click(By.id(productName+""+ShowFilter));
            waitElementInvisible(loading_cursor);
            click(By.cssSelector("#ul-"+productName+" li:nth-child(2) label"));
            click(By.id(productName+""+ApplyFilter));
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            getScreenshot();
            String recordsCountAfter2 = getDriver().findElement(By.id(ResultsCount)).getText();
            softAssert.assertNotEquals(recordsCountBefore, recordsCountAfter2, "Product Name filter failed to apply");
            click(By.id(productName+""+ClearFilter));

            softAssert.assertAll();
            test.pass("Filters functionality verified successfully");

            saveResult(ITestResult.SUCCESS, null);
        }catch(AssertionError er){
            test.fail("Filters functionality failed to verify successfully");
            saveResult(ITestResult.FAILURE, new Exception(er));
        }
        catch(Exception ex){
            test.fail("Filters functionality failed to verify successfully");
            saveResult(ITestResult.FAILURE, ex);
        }
    }


    @Test (priority = 11, enabled = true)
    public void WildcardCompanyProducts() throws InterruptedException, IOException {
        Wildcard(CompanyProductsTableName, "Company Products", 1);
    }


    @Test(priority= 12, enabled = true)
    public void SortingCompanyProducts() throws InterruptedException, IOException {
        Sorting(CompanyProductsTableName, "Company Products", 1);
    }


    @Test(priority= 13, enabled = true)
    public void PaginationCompanyProducts() throws InterruptedException, IOException {
        Pagination(CompanyProductsTableName, "Company Products");
    }


    @Test(priority= 14, enabled = true)
    public void RowsPerPageCompanyProducts() throws InterruptedException, IOException {
        RowsPerPage_();
    }

    @AfterTest
    public static void endreport() {
        extent.flush();
    }

}
