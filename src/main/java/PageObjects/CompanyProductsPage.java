package PageObjects;

import org.openqa.selenium.By;

public class CompanyProductsPage {

	public static By companyProductsManagmentTitle = By.id("Company Products");
	public static By getCompanyProductsColumnNames = By.cssSelector("th .log-header label");
	public static String CompanyProductsTableName = "manage-company-product";	
	public static By CompanyProductCreateNewButton = By.id("create-product");	
	public static By CompanyProductNameInput = By.cssSelector("#CompanyId input");
	public static String CompanyNameProductEditButton = "edit-product-";
	public static String CompanyNameProductDeleteButton = "delete-product-";
	
	public static String alliedCompanyCol = "0";
	public static String productImageCol = "1";
	public static String productNameCol = "2";
	public static String productDescriptionCol = "3";
	
	public static String productAlliedCompany = "alliedCompany";
	public static String productName = "productName";
	
}
