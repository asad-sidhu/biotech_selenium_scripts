package PageObjects;

import org.openqa.selenium.By;

public class BasePage {

	public static String loading_cursor = "notification-loading";
	public static By loader = By.id("notification-loading");
	public static By popupNextButton = By.id("btn-next");
	public static By popupResetButton = By.id("btn-reset");
	public static By popupSaveButton = By.id("btn-save");
	public static By popupCancelButton = By.id("btn-cancel");
	public static By popupSubmitButton = By.id("btn-submit");
	public static By popupSubmitButtonDisabled = By.cssSelector("#btn-submit.cursor-not-allowed");
	public static By popupSubmitButtonDisable = By.cssSelector("#btn-submit.disabled-v2");
	public static By popupSaveButtonXpath = By.xpath("//button[text() = ' Submit ']");
	public static By popupYesButton = By.id("btn-yes");
	public static By popupOKButton = By.id("btn-ok");
	public static By popupNoButton = By.id("btn-no");
	public static By popupSaveButtonDisabled = By.cssSelector("#btn-save.disabled-v2");
	public static By popupCloseButton = By.cssSelector("#close-popup-modal img");
	public static By popupCloseButton2 = By.cssSelector("#close-gen-modal");
	public static By popupTotalRows = By.cssSelector(".apl-resp-table td:nth-child(1)");
	public static By popupHeaderTitle = By.cssSelector(".pop-head div");
	public static By popupHeaderTitle2 = By.cssSelector(".pop-head");
	public static By popupConfirmationTitle = By.cssSelector(".popupHeading h4");
	public static By alertMessage = By.id("message");
	//public static By alertMessageClose = By.cssSelector("button.close span");
	public static By alertMessageClose = By.xpath("/html/body/app-root/app-notification-component/ngb-alert/button/span");
	public static By confirmationYes = By.cssSelector("button.mr-4");
		
	public static String ShowFilter = "_show-filter";
	public static String SortFilter = "sort-";
	public static String ApplyFilter = "_apply";
	public static String ClearFilter = "_clear-filter";
	public static String SearchInput = "_search-input";
	public static String SelectAll = "_cust-cb-lst-txt_selectAll";
	public static String LockFilter = "save-filters";
	public static String UnlockFilter = "remove-filters";
	public static String ResultsCount = "results-found-count";
	public static String ResetFilters = "reset-all-filters";
	public static String FieldAccess = "edit-field-access";
	public static String CSVButton = "";
	public static By clickSearchItemFromHierarchy = By.cssSelector("label b");
	public static By clickAddNewDropdown = By.xpath("//*[text()='Add New + ']");
	public static By getDropdownValueCount = By.cssSelector("div[role='option']");
	
	public static String lastPagePagination = "last-page";
	public static String previousPagePagination ="previous-page";
	public static String firstPagePagination = "first-page";
	public static String nextPagePagination = "next-page";
	
	public static By auditGetRowCount = By.cssSelector(".popup-content tr");
	public static By getauditActionRow1 = By.id("audit-action-0");
	//public static By auditActionRow1 = By.id("audit-trial-0");
	public static By alertClose = By.xpath("//*[@id=\"alrt\"]/button/span");
	
	public static String filterIcon = ".log-header__filter-icon";
	public static String filterSearchInput = ".filter-popup__search-input input";
	public static String filterApplyButton = ".filter-popup__footer--apply";
	public static String filterClearButton = ".log-header__clear-filter span";
	public static String filterWildcardActionToggle = ".filter-popup__action--wildcard";
	public static String footerCount = ".filter-popup__footer--count";
	
}
