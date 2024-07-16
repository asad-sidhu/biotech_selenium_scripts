package MiscFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ClickElement {

	public static void clickById(WebDriver idriver, String iid) {
		Actions actions = new Actions(idriver);
		actions.moveToElement(idriver.findElement(By.id(iid))).click().perform();
	}	
	
	
	public static void clickByCss(WebDriver idriver, String css) {
		Actions actions = new Actions(idriver);
		actions.moveToElement(idriver.findElement(By.cssSelector(css))).click().perform();
	}	
	
	
	public static void clickByXpath(WebDriver idriver, String xpath) {
		Actions actions = new Actions(idriver);
		actions.moveToElement(idriver.findElement(By.xpath(xpath))).click().perform();
	}	
}
