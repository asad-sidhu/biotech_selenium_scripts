package PageObjects;

import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;

import static MiscFunctions.Methods.*;

import Models.AssetManagementModel;
import org.openqa.selenium.WebDriver;

public class AssetManagementPage {
    private WebDriver driver;

    public AssetManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public static By AssetManagementTitle = By.id("Asset Management");
    public static String AssetdeploymentTable = "Asset-deployment-log";
    public static String AssetMortalityTable = "Asset-mortality-log";
    public static String AssetSettlementTable = "Asset-settlement-log";
    public static String AssetCondemnationTable = "Asset-condemnation-log";
    public static String AssetHierarchyTable = "Asset-hierarchy-log";

    public static By AssetdeploymentTab = By.xpath("//*[text() = 'deployment ']");
    public static By AssetMortalityTab = By.xpath("//*[text() = 'Mortality ']");
    public static By AssetSettlementTab = By.xpath("//*[text() = 'Settlement ']");
    public static By AssetCondemnationTab = By.xpath("//*[text() = 'Condemnation ']");
    public static By AssetHierarchyTab = By.xpath("//*[text() = 'Asset Hierarchy ']");

    public static By AssetCreateButton = By.id("create-Asset");
    public static By AssetCreateAssetHierarchyButton = By.id("deployment-heriacrchy-Asset");

    public static By AssetFarmDropdownExpand = By.cssSelector("[formcontrolname='farmId'] .down ");
    public static By AssetFarmDropdownSearch = By.cssSelector("[formcontrolname='farmId'] input");
    public static By AssetFarmDropdownGetAllSites = By.cssSelector("[formcontrolname='farmId'] tr");
    public static By AssetParentAssetInfoContainer = By.xpath("(//*[contains(@class, \"parent-child-container\")])[1]");
    public static By AssetParentAssetInfoAddBtn = By.xpath("(//*[contains(@class, \"parent-child-container\")])[1]//i");
    public static By AssetChildAssetInfoContainer = By.xpath("(//*[contains(@class, \"parent-child-container\")])[2]");
    public static By AssetChildAssetInfoAddBtn = By.xpath("(//*[contains(@class, \"parent-child-container\")])[2]//i");
    public static By AssetAddParentChildSearchAssetID = By.id("searchAssetId");
    public static By AssetAddParentChildSearchAssetIDSearchBtn = By.id("btn-search");
    public static By AssetParentChildRelationTable = By.cssSelector(".parent-child-relation-tbl");
    public static By AssetParentChildRelationTableRows = By.cssSelector(".parent-child-relation-tbl tbody tr");
    public static By AssetParentChildAssetInfoSaveBtn = By.id("btn-save-house");
    public static By AssetIntegratorAssetID = By.cssSelector("#integratorAssetId-0");
    public static By AssetIntegratorAssetAddNew = By.xpath("//*[text() = 'Add New + ']");
    public static By AssetanimalSizeInput = By.cssSelector("#animalSizeId-0 input");
    public static By AssetProductionTypeList = By.cssSelector("[formControlName=\"productionTypeId\"] img");
    public static By AssetanimalBreedInputList = By.cssSelector("[formControlName=\"animalBreed\"] img");
    public static By AssetProductionTypeListItems = By.cssSelector("[formControlName=\"productionTypeId\"] li");
    public static By AssetanimalSizeDropDownOptions = By.cssSelector("#animalSizeId-0 .ng-option");
    public static By AssetanimalSexInput = By.cssSelector("#animalSexId-0 input");
    public static By AssetanimalSexDropDownOptions = By.cssSelector("#animalSexId-0 .ng-option");
    public static By AssetanimalBreedInput = By.id("animalBreedId-0");
    public static By AssetanimalBreedValueSelect = By.xpath("//*[@formcontrolname='animalBreed'] //b");
    public static By AssetMarketingProgramInput = By.cssSelector("#marketingProgramId-0 input");
    public static By AssetHatcherySourceInput = By.id("hatcherySource-0");
    public static By AssetTargetWeightInput = By.xpath("(//*[@id='num-numanimalsPlacedId-0'])[1]");
    public static By AssetTargetSlaughterAgeInput = By.xpath("(//*[@id='num-numanimalsPlacedId-0'])[2]");
    public static By AssetAgeOfdeploymentInput = By.id("num-deploymentAgeId-0");
    public static By AssetUnitDropdownExpand = By.xpath("//*[@id = 'AssetUnit-0'] //*[@class = 'ng-arrow-wrapper']");
    public static By AssetUnitDaysSelect = By.xpath("//*[@class = 'ng-option-label ng-star-inserted' and text() = 'Days']");
    public static By AssetMarketingProgramDropDownOptions = By.cssSelector("#marketingProgramId-0 .ng-option");
    public static By AssetProgramDisplayName = By.cssSelector("tr:nth-child(1) td:nth-child(9) label");

    public static By AssetDoseInput = By.id("numanimalsPlacedId");
    public static By AssetProgramDeleteButton = By.cssSelector("[title='Delete program details']");

    public static By AssetdeploymentDensityInput = By.id("num-deploymentDensity");
    public static By AssetWeeklyFarmRank = By.id("num-weeklyFarmRank");
    public static By AssetHistoricalFarmCostVariance = By.id("num-historicalFarmCostVariance");
    public static By AssetWeeklyFarmCostVariance = By.id("num-weeklyFarmCostVariance");
    public static By AssetNumanimalsDOAPlant = By.id("num-numanimalsDOAPlant");

    public static By AssetdeploymentDateCalendar = By.xpath("(//*[@class = 'apl-datepicker__calender-icon'])[1]");
    public static By AssetEstcropDateCalendar = By.xpath("(//*[@class = 'apl-datepicker__calender-icon'])[2]");
    public static By AssetNumberOfEggsPlaced = By.id("num-numberOfEggs-0");
    public static By AssetHousePlacedDropdownExpand = By.cssSelector("#housePlaced-0 .ng-arrow-wrapper");
    public static By AssetHousePlacedList = By.cssSelector("#housePlaced-0 .ng-option");
    public static By AssetHousePlacedInput = By.cssSelector("#housePlaced-0 .ng-input input");
    public static By AssetAddNewProgram = By.xpath("//*[text() = 'Add New Program']");
    public static By AssetAddNewProgramTypeInput = By.cssSelector("#programTypeId-0-0 input");
    public static By AssetAddNewProgramNameInput = By.cssSelector("#programId-0-0 input");
    public static By AssetCMSAdminInput = By.cssSelector("input#administrationMethodId");
    public static By AssetCollectionPlanInputField = By.cssSelector("#collectionPlan-0-0");
    public static By AssetHierarchyChildAssetID = By.cssSelector("#childAssetId input");
    public static By AssetHierarchyChildAssetdeployment = By.cssSelector("#childAssetdeploymentId input");
    public static By AssetHierarchyParentAssetID = By.cssSelector("#parentAssetId input");
    public static By AssetHierarchyParentAssetdeployment = By.cssSelector("#parentAssetdeploymentId input");
    public static By AssetHierarchyNumOfanimals = By.cssSelector("#num-input-id");
    public static By AssetHierarchyAddHierarchyButton = By.xpath("//label[.='Add deployment Hierarchy']");
    public static By AssetHierarchyDeleteHierarchyButton = By.cssSelector(".accordian-trash-icon");
    public static By AssetHierarchySubmit = By.cssSelector("#btn-submit");

    /////////////////deployment//////////////////
    public static String AssetIDdeploymentCol = "0";
    public static String AssetIntegratorIDdeploymentCol = "1";
    public static String AssetanimalSizedeploymentCol = "2";
    public static String AssetanimalSexdeploymentCol = "3";
    public static String AssetMarketingProgramdeploymentCol = "4";
    public static String AssetanimalBreeddeploymentCol = "5";
    public static String AssetNumofanimalsPlaceddeploymentCol = "7";
    public static String AssetProgramDetailsdeploymentCol = "8";
    public static String AssetProgramHousedeploymentCol = "9";
    public static String AssetComplexdeploymentCol = "10";
    public static String AssetFarmdeploymentCol = "11";
    public static String AssetIntegratordeploymentCol = "12";
    public static String AssetFarmSiteIDdeploymentCol = "13";
    public static By noValues = By.xpath("//div[contains(text(),'No items found')]");
    public static By clearSelectedMonitoringPlan = By.cssSelector("#collectionPlan-0-0 .ng-value-icon.left");
    public static By addNewItemFromDropdownButton = By.xpath("//*[contains(text(), 'Add New +')]");
    ///////////////End deployment//////////////////

    //////////Common Columns in all Tabs///////////
    public static String AssetAssetIDCol = "0";
    public static String AssetIntegratorAssetIDCol = "1";
    public static String AssetIntegratorCol = "2";
    public static String AssetComplexCol = "3";
    public static String AssetFarmCol = "4";
    public static String AssetFarmSiteIDCol = "5";
    public static String AssetdeploymentDateCol = "6";
    ///////End Common Columns in all Tabs///////////

    /////////////////Mortality//////////////////
    public static String AssetMortalitydeploymentDateCol = "6";

    public static String AssetMortalityCol = "7";
    ////////////////End Mortality////////////////

    /////////////////Settlement//////////////////
    public static String AssetSettlementdeploymentDateCol = "6";

    public static String AssetWeeklyFarmRankCol = "7";
    public static String AssetHistoricalFarmCostVarianceCol = "8";
    public static String AssetWeeklyFarmCostVarianceCol = "9";
    public static String AssetHatchDateCol = "10";
    public static String AssetDaysOutCol = "11";
    public static String AssetAgeOfLiterCol = "12";
    public static String AssetAverageSoldAgeCol = "13";
    public static String AssetNumanimalsSoldCol = "14";
    public static String AssetdeploymentDensityCol = "15";
    public static String AssetProcessingDateCol = "16";
    public static String AssetProcessingSiteIDCol = "17";
    public static String AssetUSDAPlantIDCol = "18";
    public static String AssetPlantLocationCol = "19";
    public static String AssetNumOfanimalsProcessedCol = "20";
    public static String AssetAvganimalWeightLBCol = "21";
    public static String AssetAvganimalWeightKGCol = "22";
    public static String AssetTotalWeightProcessedLBCol = "23";
    public static String AssetTotalWeightProcessedKGCol = "24";
    public static String AssetTotalFeedWeightLBCol = "25";
    public static String AssetTotalFeedWeightKGCol = "26";
    public static String AssetFCRCol = "27";
    public static String AssetAdjustedFCRCol = "28";
    public static String AssetFeedCostPerLivePoundCol = "29";
    public static String AssetMedicationCostPerLivePoundCol = "30";
    public static String AssetGrowerCostPerLivePoundCol = "31";
    public static String AssetLivabilityPercentageCol = "32";
    public static String AssetOverallMortalityPercentageCol = "33";
    /////////////////End Settlement//////////////////

    /////////////////Condemnation//////////////////
    public static String AssetCondemnationdeploymentDateCol = "6";

    public static String AssetNumanimalsDOAPlantCol = "7";
    public static String AssetTotalWeightCondemnedLBCol = "8";
    public static String AssetTotalWeightCondemnedKGCol = "9";
    public static String AssetNumanimalsCondemnedWholeCol = "10";
    public static String AssetPartsWeightCondemnedLBCol = "11";
    public static String AssetPartsWeightCondemnedKGCol = "12";
    public static String AssetKCalLBCol = "13";
    public static String AssetGradeAPawsPercentageCol = "14";
    public static String AssetIPPercentageCol = "15";
    public static String AssetLeukosisPercentageCol = "16";
    public static String AssetSeptoxPercentageCol = "17";
    public static String AssetTumorPercentageCol = "18";
    ///////////////End Condemnation////////////////

    public static String AssetInlineButton = "edit-inline-access";
    public static String AssetInlineButtonTooltip = ("span[title  =' In-line Edit']");

    public static String AssetInlineButtonSave = "edit-inlineSave-access";
    public static By AssetanimalBreed = By.cssSelector("#animalBreedId-0-5 input");


    public static By AssetInlineNewProgramIcon = By.cssSelector("tr:nth-child(1) #col-" + AssetProgramDetailsdeploymentCol + " label span");
    public static By AssetInlineAddNewProgramPopup = By.id("add-program");
    public static By AssetInlineProgramName = By.cssSelector("#programId input");
    public static By AssetAdministrationMethod = By.cssSelector("#administrationMethodId input");
    public static By farmExternalSiteId = By.cssSelector("#farmExternalSiteID-0");
    public static By selectProgramFeedType = By.xpath("//*[text() = 'Feed']");
    public static By AssetSelectProgramDropdownValues = By.cssSelector("#programId-0-0 .ng-option");
    public static By AssetProgramSaveButton = By.id("btn-save-program");
    List<String> optionOrder;

    public static By AssetInlineMortality1Input = By.cssSelector("tr:nth-child(1) #col-" + AssetMortalityCol + " input");

    public static By AssetInlineSettlementWeeklyFarmRank = By.cssSelector("tr:nth-child(1) #col-" + AssetWeeklyFarmRankCol + " input");
    public static By AssetInlineSettlementHistoricalFarmCostVariance = By.cssSelector("tr:nth-child(1) #col-" + AssetHistoricalFarmCostVarianceCol + " input");
    public static By AssetInlineSettlementWeeklyFarmCostVariance = By.cssSelector("tr:nth-child(1) #col-" + AssetWeeklyFarmCostVarianceCol + " input");
    public static By AssetInlineSettlementhatchDate = By.cssSelector("tr:nth-child(1) #col-" + AssetHatchDateCol + " input");
    public static By AssetInlineSettlementDaysOut = By.cssSelector("tr:nth-child(1) #col-" + AssetDaysOutCol + " input");
    public static By AssetInlineSettlementAgeofLitter = By.cssSelector("tr:nth-child(1) #col-" + AssetAgeOfLiterCol + " input");
    public static By AssetInlineSettlementAverageSoldAge = By.cssSelector("tr:nth-child(1) #col-" + AssetAverageSoldAgeCol + " input");
    public static By AssetInlineSettlementNumanimalsSold = By.cssSelector("tr:nth-child(1) #col-" + AssetNumanimalsSoldCol + " input");
    public static By AssetInlineSettlementdeploymentDensity = By.cssSelector("tr:nth-child(1) #col-" + AssetdeploymentDensityCol + " input");
    public static By AssetInlineSettlementProcessingDate = By.cssSelector("tr:nth-child(1) #col-" + AssetProcessingDateCol + " input");
    public static By AssetInlineSettlementProcessingSiteID = By.cssSelector("tr:nth-child(1) #col-" + AssetProcessingSiteIDCol + " input");
    public static By AssetInlineSettlementUSDAPlantID = By.cssSelector("tr:nth-child(1) #col-" + AssetUSDAPlantIDCol + " input");
    public static By AssetInlineSettlementPlantLocation = By.cssSelector("tr:nth-child(1) #col-" + AssetPlantLocationCol + " input");
    public static By AssetInlineSettlementNumOfBridsProcessed = By.cssSelector("tr:nth-child(1) #col-" + AssetNumOfanimalsProcessedCol + " input");
    public static By AssetInlineSettlementAvganimalWeightLB = By.cssSelector("tr:nth-child(1) #col-" + AssetAvganimalWeightLBCol + " input");
    public static By AssetInlineSettlementAvganimalWeightKG = By.cssSelector("tr:nth-child(1) #col-" + AssetAvganimalWeightKGCol + " input");
    public static By AssetInlineSettlementTotalWeightProcessedLB = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalWeightProcessedLBCol + " input");
    public static By AssetInlineSettlementTotalWeightProcessedKG = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalWeightProcessedKGCol + " input");
    public static By AssetInlineSettlementTotalFeedWeightLB = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalFeedWeightLBCol + " input");
    public static By AssetInlineSettlementTotalFeedWeightKG = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalFeedWeightKGCol + " input");
    public static By AssetInlineSettlementFCR = By.cssSelector("tr:nth-child(1) #col-" + AssetFCRCol + " input");
    public static By AssetInlineSettlementAdjustedFCR = By.cssSelector("tr:nth-child(1) #col-" + AssetAdjustedFCRCol + " input");
    public static By AssetInlineSettlementFeedCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-" + AssetFeedCostPerLivePoundCol + " input");
    public static By AssetInlineSettlementMedicationCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-" + AssetMedicationCostPerLivePoundCol + " input");
    public static By AssetInlineSettlementGrowerCostPerLivePound = By.cssSelector("tr:nth-child(1) #col-" + AssetGrowerCostPerLivePoundCol + " input");
    public static By AssetInlineSettlementLivabilityPercentage = By.cssSelector("tr:nth-child(1) #col-" + AssetLivabilityPercentageCol + " input");
    public static By AssetInlineSettlementOverallMortalityPercentage = By.cssSelector("tr:nth-child(1) #col-" + AssetOverallMortalityPercentageCol + " input");

    public static By AssetInlineNumanimalsDOAPlantCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetNumanimalsDOAPlantCol + " input");
    public static By AssetInlineTotalWeightCondemnedLBCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalWeightCondemnedLBCol + " input");
    public static By AssetInlineTotalWeightCondemnedKGCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetTotalWeightCondemnedKGCol + " input");
    public static By AssetInlineNumanimalsCondemnedWholeCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetNumanimalsCondemnedWholeCol + " input");
    public static By AssetInlinePartsWeightCondemnedLBCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetPartsWeightCondemnedLBCol + " input");
    public static By AssetInlinePartsWeightCondemnedKGCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetPartsWeightCondemnedKGCol + " input");
    public static By AssetInlineKCalLBCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetKCalLBCol + " input");
    public static By AssetInlineGradeAPawsPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetGradeAPawsPercentageCol + " input");
    public static By AssetInlineIPPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetIPPercentageCol + " input");
    public static By AssetInlineLeukosisPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetLeukosisPercentageCol + " input");
    public static By AssetInlineSeptoxPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetSeptoxPercentageCol + " input");
    public static By AssetInlineTumorPercentageCondemnation = By.cssSelector("tr:nth-child(1) #col-" + AssetTumorPercentageCol + " input");

    public static By AssetAuditTrail = By.id("audit-trial-0");
    public static By AssetAuditRowCount = By.cssSelector(".audit-v2 tr");
    public static By AssetEditdeploymentTab = By.cssSelector("#AssetManage ul.tabs-list li:nth-child(1)");
    public static By AssetEditMortalityTab = By.cssSelector("#AssetManage ul.tabs-list li:nth-child(2)");
    public static By AssetEditSettlementTab = By.cssSelector("#AssetManage ul.tabs-list li:nth-child(3)");
    public static By AssetEditCondemnationTab = By.cssSelector("#AssetManage ul.tabs-list li:nth-child(4)");
    public static By AssetAssetHierarchyTab = By.cssSelector("ul.tabs-list li:nth-child(5)");
// public static By AssetEditMortalityTab = By.cssSelector("#AssetManage li:nth-child(2)");
// public static By AssetEditSettlementTab = By.cssSelector("#AssetManage li:nth-child(3)");
// public static By AssetEditCondemnationTab = By.cssSelector("#AssetManage li:nth-child(4)");

    public static String deploymentAuditAssetIDCol = "0";
    public static String deploymentChangedDateCol = "audit-changed-date-0";
    public static String deploymentAuditActionCol = "audit-action-0";
    public static String deploymentAuditChangedDateCol = "audit-changed-by-0";
    public static String deploymentAuditIntegratorAssetIDCol = "0";
    public static String deploymentAuditanimalSizeCol = "1";
    public static String deploymentAuditanimalSexCol = "2";
    public static String deploymentAuditMarketingProgramCol = "3";
    public static String deploymentAuditanimalBreedCol = "4";
    public static String deploymentAuditNoOFanimalsPlacedCol = "5";
    public static String deploymentAuditProgramDetailsCol = "6";
    public static String deploymentAuditHousedeploymentCol = "10";
    public static String deploymentAuditComplexCol = "8";
    public static String deploymentAuditFarmCol = "9";
    public static String deploymentAuditdeploymentDateCol = "10";
    public static String deploymentAuditIntegratorCol = "11";
    public static String deploymentAuditFarmSiteIDCol = "12";
    public static String deploymentAuditEstimatedcropDateCol = "13";

    public static String mortalityCol = "";

    public static By AssetProgramSaveDisabledCheck = By.cssSelector("#btn-save.disabled-v2");
    public static By AssetProductionTypeShowFilter = By.id("productionType_show-filter");
    public static By AssetProductionTypeSearchFilter = By.id("productionType_search-input");
    public static By AssetProductionTypeApplyFilter = By.id("productionType_apply");
    public static By AssetProductionTypeClearFilter = By.id("productionType_clear-filter-deployment");
    public static By AssetProductionSelectAllFilter = By.id("productionType_cust-cb-lst-txt_selectAll");

    public static By AssetIntegratorIDShowFilter = By.id("integratorAssetId_show-filter");
    public static By AssetIntegratorIDSearchFilter = By.id("integratorAssetId_search-input");
    public static By AssetIntegratorIDApplyFilter = By.id("integratorAssetId_apply");
    public static By AssetIntegratorIDClearFilter = By.id("integratorAssetId_clear-filter-deployment");
    public static By AssetIntegratorIDSelectAllFilter = By.id("integratorAssetId_cust-cb-lst-txt_selectAll");

    public static String AssetShowFilter = "_show-filter";
    public static String AssetSearchFilter = "_search-input";
    public static String AssetApplyFilter = "_apply";

    public static String AssetIntegratorIDFilter = "integratorAssetId";
    public static String AssetanimalSex = "animalSex";

    public static String AssetdeploymentCSVFileName = "deployment Log - ";
    public static String AssetMortalityCSVFileName = "Mortality Log - ";
    public static String AssetSettlementCSVFileName = "Settlement Log - ";
    public static String AssetCondemnationCSVFileName = "Condemnation Log - ";
    public static String AssetHierarchyCSVFileName = "Asset Hierarchy Log - ";
    public static String AssetEOFTemplateFileName = "End of Asset Template_";
    public static String AssetdeploymentAuditFileName = "deployment Audit Log - ";
    public static String AssetMortalityAuditFileName = "Mortality Audit Log - ";
    public static String AssetSettlementAuditFileName = "Settlement Audit Log - ";
    public static String AssetCondemnationAuditFileName = "Condemnation Audit Log - ";

    public static By newAssetCollapseIcon = By.cssSelector(".fa.fa-angle-down");
    public static By addNewAssetButton = By.xpath("//label[contains(text(),'Add New Asset')]");
    public static By AssetDeleteIcon = By.xpath("(//i[contains(@class, 'fa-trash')])[1]");
    public static By selectInterventionButton = By.cssSelector("button[title='Select Intervention']");
    public static By monitoringStrategyButton = By.cssSelector("button[title='Monitoring Plan Name']");
    public static By selectMonitoringPlan = By.id("collectionPlan");
    public static By selectMonitoringPlanInput = By.cssSelector(".radius-section #collectionPlan-0-0 input");
    public static By selectMonitoringPlanValues = By.xpath("//label[contains(text(),'Select All')]");
    public static By monitoringPlanSaveButton = By.id("btn-save-house");

    public static By monitoringPlanBackButton = By.id("btn-cancel-sites");
    public static By inlineEditdeploymentTabFarmField = By.id("farmId-0-11");
    public static By inlineEditdeploymentTabFarmValues = By.cssSelector(".farmId-0-11 .ng-option");
    public static By adddeploymentEventLink = By.xpath("//label[contains(text(),'Add deployment Event')]");

    public static By savaAndCloseButton = By.id("btn-save-close");
    public static By programEndDateCrossSign = By.className("fa-times");

    public static By programDeleteIcon = By.xpath("//i[contains(@class, 'fa-trash')]");
    public static By programColumnFilter = By.id("programDisplayName_show-filter");
    public static By programColumnSearchField = By.id("feedprogram_programDisplayName_search-input");
    public static By programColumnApplyField = By.id("feedprogram_programDisplayName_apply");
    public static By squareFootage = By.id("num-fcSquareFootageId");
    public static By numOfanimalsField = By.cssSelector("#num-houseTest-0-0-numanimalsPlacedId");
    public static By deploymentDensityField = By.id("num-houseTest-0-0-deploymentDencity");

    public static String programSearch = "feedprogram_programDisplayName_cust-cb-lst-txt_";
    public static By programDeleteButton = By.cssSelector("#delete-feed-program-1-feedprogram");
    public static By programEditButton = By.id("edit-feed-program-1-feedprogram");


    public static void openEditAsset() throws InterruptedException, IOException {
        click(AssetdeploymentTab);
        Thread.sleep(2000);
        for (int i = 1; i < size(By.cssSelector("tr")); i++) {
            System.out.println(getText(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetIntegratorAssetIDCol + " label")));
            if (getText(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetIntegratorAssetIDCol + " label")).equals(AssetManagementModel.AssetIntegratorID)) {
                click(By.cssSelector("#edit-feed-program-" + i + ":nth-of-type(2)"));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1500);
                break;
            }
        }
    }

    public static void openAssetAudit() throws InterruptedException, IOException {
        click(AssetdeploymentTab);
        Thread.sleep(2000);
        for (int i = 1; i < size(By.cssSelector("tr")); i++) {
            if (getText(By.cssSelector("tr:nth-child(" + i + ") #col-" + AssetIntegratorAssetIDCol + " label")).equals(AssetManagementModel.AssetIntegratorID)) {
                int j = i - 1;
                click(By.id("audit-trial-" + j));
                waitElementInvisible(loading_cursor);
                Thread.sleep(1500);
                break;
            }
        }
    }

}