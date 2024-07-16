package PageObjects;

import MiscFunctions.Constants;
import org.openqa.selenium.By;
import org.testng.Assert;

import Config.BaseTest;
import MiscFunctions.Methods;
import org.testng.ITestResult;

import java.io.IOException;

import static Config.BaseTest.saveResult;
import static MiscFunctions.Constants.*;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.BasePage.loading_cursor;

public class Patho_2LogPage {

	private static By Patho_2LogBox = By.cssSelector("img[alt='Patho_2 Log']"); 
	private static By bacterialLogBox = By.cssSelector("img[alt='Bacterial Log']"); 
	private static By Patho_2LogTitle = By.id("Patho_2 Log");
	private static By bacterialLogTitle = By.id("Bacterial Log");

	public static String Patho_2LogTable = "Patho_2-data-log";
	
	public static String slLaneCol = "0";
	public static String slSampleIDCol = "1";
	public static String slQCCodeCol = "2";
	public static String slResultStatusCol = "3";
	public static String slResultCol = "4";
	public static String slAssayCol = "5";
	public static String slResultIDCol = "6";
	public static String slDateCol = "7";
	public static String slTimeCol = "8";
	public static String slSiteIDCol = "10";
	public static String slSampleMatrixCol = "12";
	public static String slCSampleIDCol = "14";
	public static String slCartridgeIDCol = "16";
	public static String slInstrumentIDCol = "17";
	public static String slW1CellCountCol = "18";
	public static String slW1PCCountCol = "19";
	public static String slW2CellCountCol = "21";
	public static String slW2CPCCountCol = "22";
	public static String slPiperUserCol = "24";
	public static String slKitLotCol = "25";
	public static String slImprocIDCol = "26";
	public static String slTestSiteIDCol = "27";
	public static String slTestSiteNameCol = "28";
	public static String slRequestedAssayCol = "29";
	public static String slAssetIDCol = "30";
	public static String slRunTypeCol = "31";
	public static String slCollectionDateCol = "32";
	
	public static String slAuditLaneColCss = "5";
	public static String slAuditSampleIDCol = "1";
	public static String slAuditQCCodeCol = "2";
	public static String slAuditResultStatusCol = "3";
	public static String slAuditResultCol = "4";
	public static String slAuditTimeCol = "7";
	public static String slAuditSampleMatrixCol = "11";
	public static String slAuditCartridgeIDCol = "15";
	public static String slAuditInstrumentIDCol = "16";
	public static String slAuditW1CellCountCol = "17";
	public static String slAuditW1PCCountCol = "18";
	public static String slAuditW2CellCountCol = "20";
	public static String slAuditW2CPCCountCol = "21";
	public static String slAuditPiperUserCol = "23";
	public static String slAuditImprocIDCol = "25";
	public static String slAuditTestSiteIDCol = "26";
	public static String slAuditTestSiteNameCol = "27";
	public static String slAuditRunTypeCol = "30";
	public static String slAuditCollectionDateCol = "31";
	public static String closeAudit = "#close-gen-modal";
	public static String slPNGFileName = "Patho_2 Run Timeline - ";
	public static String slCSVFileName = "Patho_2 Log - ";
	public static String slCSVAuditFileName = "Patho_2 Audit Log - ";
	public static String slSampleMetaData = "Sample Metadata Upload Template";
	
	public static void openPatho_2LogPage() throws IOException {
		try {
			BaseTest driver = new BaseTest();
			driver.getDriver().get(url_reportManagement);
			waitElementInvisible(loading_cursor);
			if (Methods.size(Patho_2LogBox) != 0) {
				click(Patho_2LogBox);
			} else {
				click(bacterialLogBox);
			}
			waitElementInvisible(loading_cursor);

	//		if (Constants.config.url().contains("qa") || Constants.config.url().contains("dev")) {
				Assert.assertEquals(getText(Patho_2LogTitle), "Patho_2 Log");
	//		}

	//		if (Constants.config.url().contains("uat")) {
	//			Assert.assertEquals(getText(bacterialLogTitle), "Bacterial Log");
	//		}
			getScreenshot();
			test.pass("Navigated Successfully");
			saveResult(ITestResult.SUCCESS, null);
		}
		catch(AssertionError er) {
			test.fail("Failed to navigate");
			saveResult(ITestResult.FAILURE, new Exception(er));
		}
		catch(Exception ex) {
			test.fail("Failed to navigate");
			saveResult(ITestResult.FAILURE, ex);
		}
	}
}
