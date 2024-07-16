package PageObjects;

import static MiscFunctions.Constants.url_userManagement;

import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Config.BaseTest;
import Config.ReadPropertyFile;
import MiscFunctions.Methods;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccessManagementPage {

	public static By accessTitle = By.id("Access Management");
	public static By accessCreateButton = By.id("create-role");
	public static By accessName = By.xpath("//div[1]/app-anc-input-box/div/input[1]");
	public static By accessDesc = By.xpath("//div[2]/app-anc-input-box/div/input[1]");
	
	public static By accessNameDescValidation = By.cssSelector(".has-error");
	public static String accessInActive = "/html/body/app-root/div[1]/app-manage-role/app-popup-component/div/div/div/div[3]/app-update-role/form/div[1]/div/div/div[3]/div/app-custom-radio-button/div/div";
	public static By saveEditRights = By.cssSelector(".fa-save");

	public static By piperMgtupdateRightsCheckbox = By.cssSelector("tr:nth-child(17) td:nth-child(3) .custom-checkbox");
	public static By piperMgtupdateRightCheckbox = By.cssSelector("tr:nth-child(17) td:nth-child(3) input");

	
	
	public static void getUserAccess() throws InterruptedException, IOException {
		BaseTest bt = new BaseTest();
		bt.getDriver().get(url_userManagement);
		Methods.waitElementInvisible(BasePage.loading_cursor);
		ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);
		Thread.sleep(1000);
		for(int i=1; i<=500; i++) {
			if (bt.getDriver().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(4) label")).getText().equals(config.ie_username())) {
				WebElement scroll = bt.getDriver().findElement(By.id("edit-user-"+i));
				((JavascriptExecutor)bt.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
				Thread.sleep(1000);
				bt.getDriver().findElement(By.id("edit-user-"+i)).click();
				break;
			}
		}
		Methods.waitElementInvisible(BasePage.loading_cursor);
		Thread.sleep(4000);
		Methods.click(BasePage.popupSaveButton);
		Thread.sleep(1000);
		Methods.click(BasePage.popupNextButton);
		Thread.sleep(1000);
	}
	
	
}
