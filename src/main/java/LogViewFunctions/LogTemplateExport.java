package LogViewFunctions;

import MiscFunctions.ClickElement;
import MiscFunctions.DownloadFileCheck;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Config.BaseTest.saveResult;
import static MiscFunctions.ExtentVariables.*;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static PageObjects.ClostridiumLogPage.clSampleMetaData;

public class LogTemplateExport {


    public static void TemplateExport(String metadataFilename) throws InterruptedException, IOException {
        try {
            test = extent.createTest("AN-CL-218: Verify user can download Template file");
            waitElementClickable(By.cssSelector("#csv-action img"));
            click(By.cssSelector("#csv-action img"));
            Thread.sleep(1000);
            click(By.cssSelector("#export-data-template"));

            if (size(By.id("Sample-Metadata-Upload-Template")) == 1) {
                    click(By.id("Sample-Metadata-Upload-Template"));
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
            Date date1 = new Date();
            String date = dateFormat.format(date1);
            Thread.sleep(5000);
            getScreenshot();

            File newfile = DownloadFileCheck.getTheNewestFile(fileDownloadPath, "xlsx");
            String filename = newfile.getName();
            Assert.assertEquals(filename, metadataFilename + date + ".xlsx");
            test.pass("Template downloaded successfully");
            saveResult(ITestResult.SUCCESS, null);

            File file = new File(fileDownloadPath + "\\" + metadataFilename + date + ".xlsx");
            if (file.delete())
                System.out.println("Template file deleted");
        } catch (AssertionError er) {
            test.fail("Template download failed");
            saveResult(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Template download failed");
            saveResult(ITestResult.FAILURE, ex);
        }
    }



}
