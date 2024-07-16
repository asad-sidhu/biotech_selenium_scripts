package PageObjects;

import static Config.BaseTest.saveResult;
import static MiscFunctions.Constants.url_organizationManagement;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static Models.OrganizationManagementModel.OrganizationName;
import static Models.OrganizationManagementModel.lstOrgAlertMessages;
import static PageObjects.BasePage.*;

import java.io.IOException;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class OrganizationManagementPage {

    public static String orgManagementTable = "orgn-log";
    public static By orgManagementEngagementTab = By.cssSelector(".tabs-list li:nth-child(2)");
    public static String orgCSVFileName = "Organizations Log - ";
    public static By orgTitle = By.id("Organization Management");
    public static By orgCreateButton = By.id("create-organization");
    public static By orgTypeDropDownExpand = By.cssSelector("#orgTypeId .ng-arrow-wrapper");
    public static By orgTypeInput = By.cssSelector("#orgTypeId input");
    public static By orgTypeError = By.cssSelector("#orgTypeId.ng-invalid");
    public static By orgNameInput = By.id("nameId");
    public static By orgNameError = By.cssSelector("#num-nameId.has-error");
    public static By orgPhoneCodeInput = By.id("phoneCodeId");
    public static By orgPhoneNumberInput = By.id("cellNumberId");
    public static By orgPhoneNumberError = By.cssSelector("#cellNumberId .has-error");
    public static By orgEmailInput = By.id("emailId");
    public static By orgEmailError = By.cssSelector("#emailId.has-error");

    public static By farmFarmTypeDropdown = By.cssSelector("#FarmTypeId span.ng-arrow-wrapper");
    public static By farmFarmTypeDropdownItems = By.cssSelector("#FarmTypeId .ng-option");
    public static By houseLitterTypeDropdown = By.cssSelector("#litterTypeId span");
    public static By houseLitterTypefield = By.cssSelector("#litterTypeId input");
    public static By houseLitterTypeClearField = By.cssSelector("#litterTypeId span.ng-clear-wrapper");
    public static By houseFloorTypeDropdown = By.cssSelector("#floorTypeId span");
    public static By houseFloorTypefield = By.cssSelector("#floorTypeId input");
    public static By houseFloorTypeClearField = By.cssSelector("#floorTypeId span.ng-clear-wrapper");

    public static By orgAllowDomainsExpand = By.cssSelector("#domains .ng-arrow-wrapper");
    public static By roleCategoryExpand = By.cssSelector("#roleCategoryId .ng-arrow-wrapper");
    public static By selectAllCheckBox = By.xpath("//label[text() = 'Select All']");
    public static By orgMaxUsersInput = By.id("num-idMaxUsers");
    public static By orgMaxUsersError = By.cssSelector("#num-idMaxUsers .has-error");
    public static By orgAgreementDropDownExpand = By.cssSelector("#euladdl .ng-arrow-wrapper");
    public static By orgAgreementDropDownSelect = By.xpath("//label[contains(text(),'Select All')]");
    public static By orgSystemRolesExpand = By.cssSelector("#rolesId .ng-arrow-wrapper");
    public static By orgSystemRolesSelect = By.cssSelector("#rolesId .ng-input");
    public static By orgReportRolesExpand = By.cssSelector("#rptRolesId .ng-arrow-wrapper");
    public static By orgReportRolesSelect = By.cssSelector("#rptRolesId .ng-input");
    public static By orgExtSiteField = By.id("fcFarmNumberId");
    public static By orgNameSiteHierarchy = By.xpath("//div[contains(@class, 'd-inline-block')]//span");
    public static String orgSiteHierarchy = "//div[contains(@class, 'd-inline-block')]//span";
    public static By orgDelete = By.xpath("//div[contains(@class, 'delete')]");

    public static By orgParentSiteClick = By.cssSelector("li .text-ellipsis");
    public static By orgSiteIDField = By.id("num-SiteIDId");
    public static By siteslist = By.cssSelector("li:nth-child(1) div span");

    public static By orgSiteTypeInput = By.id("SiteTypeId");
    public static By orgSiteTypeInputChild = By.id("SiteTypeId");
    public static By orgSiteFarmDropdown = By.cssSelector("#SiteTypeId .ng-arrow-wrapper");
    public static By orgSiteFarmDropdownFarmSelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'Farm']");
    public static By orgSiteFarmDropdownHatcherySelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'Hatchery']");
    public static By orgSiteTypeDropDownValue = By.cssSelector("div .ng-dropdown-panel-items");
    public static By orgSiteNameInput = By.id("SiteNameId");
    public static By orgSiteNameError = By.cssSelector("#SiteNameId.has-error");
    public static By orgSiteAddressInput = By.id("streetAddressId");
    public static By orgSiteCountryPlaceholder = By.cssSelector("#countryId .ng-placeholder");
    public static By orgSiteCountryInput = By.cssSelector("#countryId input");
    public static By orgSiteCountryDropdownExpand = By.cssSelector("#countryId .ng-arrow-wrapper");
    public static By orgSiteFarmDropdownCanadaSelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'Canada']");
    public static By orgSiteFarmDropdownEgyptSelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'Egypt']");
    public static By orgSiteFarmDropdownUnitedStatesSelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'United States']");

    public static By orgSiteCountrySelect = By.xpath("//*[@id = 'countryId']//*[text() = 'United States']");

    public static By orgSiteStatePlaceholder = By.cssSelector("#stateId .ng-placeholder");
    public static By orgSiteStateInput = By.cssSelector("#stateId .ng-value-label");

    public static By orgSiteCityPlaceholder = By.cssSelector("#cityId .ng-placeholder");
    public static By orgSiteCityInput = By.cssSelector("#cityId .ng-value-label");

    public static By orgSiteZipCodeInput = By.id("num-zipCodeId");
    public static By orgSiteLatitudeInput = By.id("num-LatId");
    public static By orgSiteLongitudeInput = By.id("num-LonId");
    public static By orgUploadProductImage = By.cssSelector("#file");
    public static By orgUploadProductImageSize = By.cssSelector(".textLink");
    public static By orgAddProductName = By.id("nameId");
    public static By orgAddProductDescription = By.id("descriptionId");
    public static By orgRemoveUploadedProduct = By.id("topRightCornerBtn");
    public static By orgDeleteUploadedProduct = By.xpath("(//button[@type = 'button'])[3]");
    public static By orgGetTotalCreatedProducts = By.cssSelector(".fileItemInActive");

//    public static By editSearchedOrgSites = By.cssSelector("#edit-orgn-sites-1 img");
    public static By addFileToUploadButton = By.cssSelector(".btn-lbl");
    public static By fileInputButton = By.id("file-input");
    public static By fileUploadSubmitButton = By.xpath("//button[contains(text(),'Submit')]");
    public static By complexFile = By.xpath("(//span[contains(text(), '10065 COMplex')])[1]");
    public static By complexSiteDeleteIcon = By.xpath("(//span[contains(@title, 'Updated Name')]/../following-sibling::div[@class='pull-right d-flex align-items-center']//img[contains(@src, 'icon-trash-white.png')])[1]");
    public static By orgSiteManagerInput = By.id("complexManagerId");
    public static By orgSiteManagerEmailInput = By.id("complexManagerEmailId");


    public static By orgAddSite1 = By.cssSelector("img[alt = 'add']");
    public static By orgSite1Click = By.cssSelector("li:last-child ul:last-child li:last-child span");
    public static By orgSite1Delete = By.cssSelector(".delete img");
    public static By orgAddSite2 = By.xpath("//ul/div/li/ul/li/div/div[4]/div[1]/img");
    public static By orgAddSite3 = By.xpath("//ul/li/ul/li//div[4]/div[1]/img");
    public static By orgAddSite4 = By.xpath("//ul/li//li//li//div[4]/div[1]/img");
    public static By orgAddSite5 = By.xpath("//ul/div/li//li//li/ul/li/ul/li/div/div[4]/div[1]/img");
    public static By orgAddSite6 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/div/form/div[2]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[2]/div/div[4]/div[1]/img");
    public static By orgAddSite7 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/div/form/div[2]/div/div[1]/div/ul/div/li/ul/li/ul/li/ul/li[3]/div/div[4]/div[1]/img");
    public static By orgDeleteSite1 = By.xpath("/html/body/app-root/div/app-manage-organization-v2/div/div[2]/app-popup-component/div/div/div/div[3]/app-create-site-component/div/form/div[2]/div/div[1]/div/ul/div/li/ul/li/div/div[4]/div[2]");

    public static String orgNameCol = "0";
    public static String orgPhoneNumberCol = "1";
    public static String orgCityCol = "2";
    public static String orgStateCol = "3";
    public static String orgCountryCol = "4";
    public static String orgOrganzationTypeCol = "5";

    public static String orgName = "orgnName";
    public static String orgOrganzationType = "orgnTypeName";
    public static String orgPhoneNumber = "phoneNo";
    public static String orgCity = "cityName";
    public static String orgState = "provinceName";
    public static String orgCountry = "countryName";
    public static By fieldAccessIcon = By.cssSelector("#orgn-log #edit-field-access");
    public static By organizationColumnFilter = By.id("orgnName_show-filter");
    public static By organizationColumnSearchField = By.id("orgnName_search-input");
    public static By organizationColumnApplyButton = By.id("orgnName_apply");
    public static By organizationEditButton = By.id("edit-orgn-1");
    public static By organizationEngagementButton = By.id("create-engag-1");

    public static By organizationDeleteButton = By.id("delete-orgn-1");
    public static By organizationEditSitesButton = By.cssSelector("#edit-orgn-sites-1 img");

    public static By auditIcon = By.id("audit-trial-0");
    public static By auditChangedDateCol = By.id("audit-changed-date");
    public static By auditActionCol = By.id("audit-action");
    public static By auditChangedByCol = By.id("audit-changed-by");
    public static By auditOrganizationCol = By.id("audit-sort-orgnName");
    public static By auditPhoneCol = By.id("audit-sort-phoneNo");
    public static By auditCityCol = By.id("audit-sort-cityName");
    public static By auditEmailCol = By.xpath("//th[contains(text(),'Email')]");
    public static By auditMaxUsersCol = By.xpath("//th[contains(text(),'Max User(s)')]");
    public static By auditActiveCol = By.xpath("//th[contains(text(),'Active')]");
    public static By auditReportRolesCol = By.xpath("//th[contains(text(),'Report Roles')]");
    public static By auditSystemRolesCol = By.xpath("//th[contains(text(),'System Roles')]");
    public static By auditRoleCategoryCol = By.xpath("//th[contains(text(),'Role Category')]");
    public static By auditAllowedDomainsCol = By.xpath("//th[contains(text(),'Allowed Domains')]");

    public static By engagementOrganizationField = By.id("fcName");
    public static By engagementComplexDropdownExpand = By.cssSelector("#complexes .ng-arrow-wrapper");
    public static By engagementComplexSelect = By.xpath("//*[@class = 'ng-dropdown-panel-items scroll-host']");
    public static By engagementStartDateDropdown = By.cssSelector("[formcontrolname=\"fcStartDate\"] img");
    public static By engagementEndDateDropdown = By.cssSelector("[formcontrolname=\"fcEndDate\"] img");
    public static By engagementProductListExpand = By.id("fcProductId-0");
    public static By engagementSubmitButton = By.xpath("//*[text()=' Submit ']");
    public static By engagementEditButton = By.id("edit-engagement-1-engagement");
    public static By engagementDeleteButton = By.id("delete-engagement-1-engagement");
    public static int auditUpdatedRow = 0;
    public static int auditRowCount = 3;

    static BaseTest driver = new BaseTest();

    public static void CreateOrganizationFunction(String orgName) throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-OM-05: Verify user can create New Organization");

            driver.getDriver().get(url_organizationManagement);
            waitElementInvisible(loading_cursor);
            Thread.sleep(3000);

            String recordsCountBefore = driver.getDriver().findElement(By.id(ResultsCount)).getText();
            click(orgCreateButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            click(orgTypeDropDownExpand);
            Thread.sleep(750);
            driver.getDriver().findElement(orgTypeInput).sendKeys(Keys.ENTER);
            Thread.sleep(750);
            clear(orgNameInput);
            type(orgNameInput, orgName);
            // Allow Domains Start
            Thread.sleep(750);
            click(orgAllowDomainsExpand);
            Thread.sleep(1000);
            click(selectAllCheckBox);
            Thread.sleep(750);
            click(orgAllowDomainsExpand);
            Thread.sleep(750);
            // Allow Domains End
            click(popupNextButton);
            Thread.sleep(700);
            type(orgMaxUsersInput, "10");
            // Role Category Start
            click(roleCategoryExpand);
            Thread.sleep(1000);
            click(selectAllCheckBox);
            Thread.sleep(750);
            click(roleCategoryExpand);
            Thread.sleep(750);
            // Role Category End
            click(orgSystemRolesExpand);
            Thread.sleep(700);
            driver.getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
            click(orgSystemRolesExpand);

            click(orgReportRolesExpand);
            Thread.sleep(700);
            driver.getDriver().findElement(By.xpath("//label[text() = 'Select All']")).click();
            click(orgReportRolesExpand);
            click(popupSaveButton);
            waitElementInvisible(loading_cursor);
            Thread.sleep(1000);
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(driver.getDriver().findElement(alertMessage).getText(), lstOrgAlertMessages.get(0));
            String recordsCountAfter = driver.getDriver().findElement(By.id(ResultsCount)).getText();
            //    softAssert.assertNotEquals(recordsCountAfter, recordsCountBefore);
            softAssert.assertAll();
            test.pass("New Organization created successfully");
            getScreenshot();
            saveResult(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("New Organization creation failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("New Organization creation failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }

//    public static void openEditOrgPopup1(String orgName) throws InterruptedException, IOException {
//        click(By.id(ResetFilters));
//        waitElementInvisible(loading_cursor);
//        click(organizationColumnFilter);
//        waitElementInvisible(loading_cursor);
//        type(organizationColumnSearchField, OrganizationName);
//        Thread.sleep(500);
//        waitElementVisible(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
//        click(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
//        click(organizationColumnApplyButton);
//        waitElementInvisible(loading_cursor);
//        Thread.sleep(2000);
//        click(organizationEditButton);
//        waitElementInvisible(loading_cursor);
//
//    }

//    public static void openEditOrgSitesPopup1(String orgName) throws InterruptedException, IOException {
//        click(By.id(ResetFilters));
//        waitElementInvisible(loading_cursor);
//        click(organizationColumnFilter);
//        waitElementInvisible(loading_cursor);
//        type(organizationColumnSearchField, OrganizationName);
//        Thread.sleep(1000);
//        waitElementVisible(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
//        click(By.id("orgnName_cust-cb-lst-txt_" + OrganizationName));
//        click(organizationColumnApplyButton);
//        waitElementInvisible(loading_cursor);
//		Thread.sleep(2000);
//        scroll(editSearchedOrgSites);
//        click(editSearchedOrgSites);
//        waitElementInvisible(loading_cursor);
//    }

    public static void searchCreatedOrg(String orgName, By icon) throws InterruptedException {
        click(By.id(ResetFilters));
        waitElementInvisible(loading_cursor);
        click(organizationColumnFilter);
        waitElementInvisible(loading_cursor);
        type(organizationColumnSearchField, orgName);
        Thread.sleep(1000);
        waitElementVisible(By.id("orgnName_cust-cb-lst-txt_" + orgName));
        click(By.id("orgnName_cust-cb-lst-txt_" + orgName));
        click(organizationColumnApplyButton);
        waitElementInvisible(loading_cursor);
        Thread.sleep(5000);
        if (size(By.cssSelector("#col-0 label[title='"+orgName+"']")) == 1) {
            click(icon);
        }
        waitElementInvisible(loading_cursor);
        Thread.sleep(1000);
    }

}

