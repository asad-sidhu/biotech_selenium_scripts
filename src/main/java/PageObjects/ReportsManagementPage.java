package PageObjects;

import org.openqa.selenium.By;

public class ReportsManagementPage {

	public static By reportsManagementTitle = By.id("Reports Management");
	public static String reportsManagementTable = "dataformat-log";
	public static By rmCreateButton = By.id("create-report-role");
	public static By rmName = By.id("nameId");
	public static By rmDesc = By.id("DescId");
	public static By rmRoleFilterShow = By.id("reportRoleName_show-filter");
	public static By rmRoleFilterApply = By.id("reportRoleName_apply");
	public static By rmRoleFilterSearch = By.id("reportRoleName_search-input");
	public static By rmRoleFilterWildcardToggle = By.cssSelector(".filter-popup__action--wildcard");
	public static By rmReportGroupPopupOpen = By.id("manage-report-group");
	public static By rmReportGroupsCreateButton = By.id("add-group");
	public static By rmReportGroupsName = By.id("groupNameId");
	public static By rmReportGroupsDesc = By.id("groupDescId");
	public static By rmReportGroupsresetButton = By.id("btn-reset-2");
	
}
