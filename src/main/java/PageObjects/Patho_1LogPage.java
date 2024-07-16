package PageObjects;

import static MiscFunctions.Constants.url_reportManagement;
import static PageObjects.BasePage.loading_cursor;
import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;
import MiscFunctions.Methods;

public class Patho_1LogPage {

	private static By Patho_1LogBox = By.cssSelector("img[alt='Patho_1 Log']"); 
	private static By Patho_1LogTitle = By.id("Patho_1 Log");
	public static String Patho_1LogTable = "Patho_1-data-log";
	public static String clLaneCol = "0";
	public static String clSampleIDCol = "1";
	public static String clQCCodeCol = "2";
	public static String clResultStatusCol = "3";
	public static String clTotalOPGCol = "4";
	public static String clSmallOPGCol = "5";
	public static String clMediumOPGCol = "6";
	public static String clLargeOPGCol = "7";
	public static String clAssayCol = "9";
	public static String clResultIDCol = "10";
	public static String clDateCol = "11";
	public static String clTimeCol = "12";
	public static String clSampleMatrixCol = "14";
	public static String clCSampleIDCol = "15";
	public static String clCatridgeIDCol = "17";
	public static String clInstrumentIDCol = "18";
	public static String clTotalCountCol = "19";
	public static String clSmallCountCol = "20";
	public static String clMediumCountCol = "21";
	public static String clLargeCountCol = "22";
	public static String clMeanIntensityCol = "23";
	public static String clPiperUserCol = "24";
	public static String clKitLotCol = "25";
	public static String clImprocIDCol = "26";
	public static String clTestSiteIDCol = "27";
	public static String clTestSiteNameCol = "28";
	public static String clRequestedAssayCol = "30";
	public static String clAssetIDCol = "31";
	public static String clRunTypeCol = "32";
	public static String clCollectionSiteIDCol = "34";
	public static String clFarmCol = "35";
	public static String clComplexCol = "36";
	public static String clAuditSampleIDCol = "1";
	public static String clAuditQCCodeCol = "2";
	public static String clAuditResultStatusCol = "3";
	public static String clAuditTimeCol = "11";
	public static String clAuditCartridgeIDCol = "16";
	public static String clAuditInstrumentIDCol = "17";
	public static String clAuditTotalCountCol = "18";
	public static String clAuditSmallCountCol = "19";
	public static String clAuditMediumCountCol = "20";
	public static String clAuditLargeCountCol = "21";
	public static String clAuditImprocIDCol = "25";
	public static String clAuditTestSiteIDCol = "26";
	public static String clAuditTestSiteNameCol = "27";
	public static String clAuditRunTypeCol = "31";
	public static String clShowFilter = "_show-filter";
	public static String clApplyFilter = "_apply";
	public static String clCustomerSampleID = "customer_sample_id";

	public static String viewAllFilter = "_view-all";
	public static String customerSampleIdFilterLastItem = "//ul[@id='ul-customer_sample_id']/li[last()]//small";
	public static String removeFilterIcon = "#remove-filters.d-none";
	public static String clPNGFileName = "Patho_1 Run Timeline - ";
	public static String clCSVFileName = "Patho_1 Log - ";
	public static String clCSVAuditFileName = "Patho_1 Log Audit - ";
	public static String clSampleMetaData = "Sample Metadata Upload Template";
	
	public static void openPatho_1LogPage() throws InterruptedException {
		BaseTest driver = new BaseTest();
		driver.getDriver().get(url_reportManagement);
		Methods.waitElementInvisible(loading_cursor);
		Methods.click(Patho_1LogBox);
		Methods.waitElementInvisible(loading_cursor);
		Thread.sleep(3000);
		Assert.assertEquals(Methods.getText(Patho_1LogTitle), "Patho_1 Log"); 	
	}
	
}
