package PageObjects;

import org.openqa.selenium.By;

public class LoginPage {

	public static By loginEmail = By.id("email");
	public static By loginPassword = By.id("pwd");
	public static By loginButton = By.id("btn-sign-in");
	public static By sideBar = By.id("menu-administration");
	public static By getVersion = By.cssSelector(".version-text");
	public static By logoutButton = By.id("logout");
	
}
