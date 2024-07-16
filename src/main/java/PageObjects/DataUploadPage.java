package PageObjects;

import org.openqa.selenium.By;

public class DataUploadPage {

	public static By dataUploadTitle = By.id("Data Upload");

	public static By uploadForDropdown = By.id("OrgnTypeID");
	public static By uploadForDropdownInput = By.cssSelector("#OrgnTypeID input");
	public static By dataTemplateDropDown = By.id("DataFormatId");
	public static By dataTemplateDropDownInput = By.cssSelector("#DataFormatId input");
	public static By browseButton = By.id("file-input");
	public static By uploadedTemplateSaveButton = By.cssSelector(".fa-save");

	public static String duUploadDropdown = "#OrgnTypeID > div > span";
	public static String duUploadDropdownSelectClient = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[1]/ng-select/ng-dropdown-panel/div/div[2]/div[2]";
	public static String duClientInput = "#ClientId > div > div > div.ng-input > input[type=text]";
	public static String duClientInputSelect = "/html/body/app-root/div/app-manage-dataupload/div[1]/form/div/div[2]/div[1]/div/div[2]/ng-select/ng-dropdown-panel/div/div[2]/div";
	
	
}
