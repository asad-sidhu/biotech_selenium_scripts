package PageObjects;

import org.openqa.selenium.By;

public class ComplexOPGPage {

	public static By complexTitle = By.id("Complex OPG Range Config");
	public static By complexCreateButton = By.id("create-complex-cocci-intervention");
	public static By complexSelectComplexDropdown = By.cssSelector("#compleSiteId .down");
	public static By complexSearchComplex = By.id("compleSiteId_search");
	public static By complexSelectComplexSite = By.cssSelector("#compleSiteId tr b");
	public static By complexSelectProgramType = By.cssSelector("#programTypeId input");
	public static By complexSelectProgramId = By.cssSelector("#programId input");
	public static By complexSelectVaccine = By.cssSelector("#vaccineId input");
	public static By complexSelectFeed = By.cssSelector("#feedProgramId input");
	public static By complexOPGType = By.id("opgTypeId");
	public static By complexanimalSize = By.id("animalSizeId"); 
	public static By complexSamplingInterval = By.id("cyclingIntervalId");
	public static By complexSelectValueFromDropdown = By.cssSelector(".ng-option-label");
	public static By complexComplexThreshold = By.id("complexCyclingThreshold");
	public static By complexHouseThreshold = By.id("houseCyclingThreshold");
	public static By complexLowerLimit = By.id("lowerLimit");
	public static By complexUpperLimit = By.id("upperLimitId");
	//public static By complexAddButton = By.xpath("//button[text() = 'Add']");
	public static By complexSubmitButton = By.xpath("//button[text() = ' Submit']");
	
}
