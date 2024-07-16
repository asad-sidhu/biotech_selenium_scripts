package PageObjects;

import org.openqa.selenium.By;

public class DataTemplateManagementPage {

	public static By dataTemplateTitle = By.id("Data Template Management");
	public static By dtmCreateButton = By.id("create-data-format");
	public static By dtmName = By.id("nameId");
	public static By dtmDesc = By.id("DescId");
	
	
	public static By dtmClmName = By.id("ColNameID");
	public static By dtmClmType = By.id("ColTypeId");
	public static By dtmClmDefaultValue = By.id("defaultValueId");
	public static By dtmClmLength = By.id("num-colLengthId");
	public static By dtmKeyField = By.cssSelector("#key-field div");
	public static By dtmIdentificationField = By.id("identity-field");
	public static By dtmClmReset = By.id("btn-reset-field");
	public static By dtmClmAdd = By.id("btn-add-field");
	public static By dtmClmSave = By.id("btn-save-field");
	public static By dtmClmEdit = By.id("edit-field-1");
	public static By dtmClmDelete = By.id("delete-field-1");
	public static By dtmClientMappingOpenButton = By.id("create-client-mapping");
	public static By dtmClientMappingClientDropdown = By.cssSelector("#ClientId .ng-arrow-wrapper");
	public static By dtmInactivateTemplate = By.cssSelector("#status-data-format  div");
	
	public static By dtmNameValidation = By.id("nameId-error-container");
	public static By dtmDescValidation = By.id("DescId-error-container");
	public static By dtmClmNameValidation = By.id("ColNameID-error-container");
	public static By dtmClmTypeValidation = By.cssSelector(".floating-error");
	public static By dtmClmLengthValidation = By.cssSelector("colLengthId-error-container");
	
	public static int metadata_ResultID = 0;
	public static int metadata_CollectionSiteID = 1;
	public static int metadata_KitLot = 2;
	public static int metadata_SampleMatrix = 3;
	public static int metadata_LabSampleID = 4;
	public static int metadata_DateReceived = 5;
	public static int metadata_CustomerSampleID = 6;
	public static int metadata_CollectedBy = 7;
	public static int metadata_RequestedAssay = 8;
	public static int metadata_AssetID = 9;
	public static int metadata_Complex = 12;
	public static int metadata_Farm = 13; 
	public static int metadata_Lane = 16;
	public static int metadata_ResultDate = 17;
	public static int metadata_ResultTime = 18;
	public static int metadata_CartridgeID = 19;
	public static int metadata_InstrumentID = 20;
	public static int metadata_PiperUser = 21;
	public static int metadata_CollectionDate = 23;
	
}
