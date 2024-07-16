package MiscFunctions;

import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Scenario;
import Config.BaseTest;
import static MiscFunctions.Methods.*;

public class NavigateToScreen {

	@Test (enabled=true, invocationCount = 4) 
	public static void navigate(String url,  String name, By id) throws InterruptedException, IOException {
		BaseTest base = new BaseTest();
		try{
			test = extent.createTest("AN-01: Navigate to "+name+" Screen");
			steps = test.createNode(Scenario.class, Steps);

			steps.createNode("1. Hover to sidebar to expand the menu");
			steps.createNode("2. Select "+name+" from side bar menu");					
			BaseTest drive = new BaseTest();
			drive.getDriver().get(url);
			waitElementInvisible(loading_cursor);
			Methods.waitElementVisible(id);
			Thread.sleep(3000);	
			Assert.assertEquals(drive.getDriver().findElement(id).getText(), ""+name); 			
			test.pass("User navigated to "+name+" screen successfully");
			getScreenshot();
			base.saveResult(ITestResult.SUCCESS, null);
		}catch(AssertionError er){
			test.fail("User failed to navigate to "+name+" screen");
			base.saveResult(ITestResult.FAILURE, new Exception(er));
		}	
		catch(Exception ex){
			test.fail("User failed to navigate to "+name+" screen");
			base.saveResult(ITestResult.FAILURE, ex);
		}		
	}
}
