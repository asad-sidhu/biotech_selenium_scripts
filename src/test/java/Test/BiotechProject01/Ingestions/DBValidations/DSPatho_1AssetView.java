package Test.BiotechProject01.Ingestions.DBValidations;

import MiscFunctions.DB_Config_DW;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static MiscFunctions.DBValidationMethods.*;
import static MiscFunctions.ExtentVariables.fileDownloadPath;
import static Test.BiotechProject01.Ingestions.DBValidations.Queries.DSPatho_1Asset_Queries.*;

import static MiscFunctions.ExtentVariables.extent;
import static ExtentReports.ExtentReport.initReport;


public class DSPatho_1AssetView extends DB_Config_DW {

    @BeforeSuite
    public static void setUp() {
        initReport("DSPatho_1AssetView");
    }

    @Test(enabled = true, priority = 1)
    public static void AllRowsCount() throws SQLException, InterruptedException, IOException {
        viewsRowCompare(getAllRowsCountQuery(oldViewName), getAllRowsCountQuery(newViewName));
    }

    @Test(enabled = true, priority = 2)
    public static void AllColumnsMatched() throws SQLException, InterruptedException, IOException {
        viewsColumnsMatched(getColumnsMatchedQuery(oldViewName, newViewName));
        viewsColumnsMatched(getColumnsMatchedQuery(newViewName, oldViewName));
    }

    @Test(enabled = true, priority = 3)
    public static void AllColumnsUnmatched() throws SQLException, InterruptedException, IOException {
        viewsUnmatchedColumns(getColumnsUnmatchedQuery(oldViewName, newViewName));
        viewsUnmatchedColumns(getColumnsUnmatchedQuery(newViewName, oldViewName));
    }

    @Test(enabled = true, priority = 4)
    public static void AllDataCompare() throws SQLException, InterruptedException, IOException {
        rowsDataCompare(getColumnsMatchedQuery(oldViewName, newViewName), oldViewName, newViewName);
    }

    @Test(enabled = false, priority = 5)
    public static void DataCompareSamplingPlanConfig() throws SQLException, InterruptedException, IOException {
        conditionalDataCompare(getSamplingPlanQuery(oldViewName), getSamplingPlanQuery(newViewName),fileDownloadPath+"\\SamplingPlanQuery.txt");
    }

    @Test(enabled = false, priority = 6)
    public static void OutOfIntervalRange() throws SQLException, InterruptedException, IOException {
        conditionalDataCompare(getOutofIntervalRangeQuery(oldViewName), getOutofIntervalRangeQuery(newViewName), fileDownloadPath+"\\Downloads\\getOutOfIntervalRangeQuery.txt");
    }

    @Test(enabled = false, priority = 7)
    public static void NoAssetAssociation() throws SQLException, InterruptedException, IOException {
        conditionalDataCompare(getNoAssetAssociationQuery(oldViewName), getNoAssetAssociationQuery(newViewName), fileDownloadPath+"\\Downloads\\noAssetAssociatedQuery.txt");
    }

    @Test(enabled = false, priority = 8)
    public static void getNoCollectionDate() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoCollectionDateQuery(oldViewName), getNoCollectionDateQuery(newViewName));
    }

    @Test(enabled = false, priority = 9)
    public static void getNoOfSamplesPerCollection() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoSamplesPerCollectionQuery(oldViewName), getNoSamplesPerCollectionQuery(newViewName));
    }

    @Test(enabled = false, priority = 10)
    public static void getNoProgramOnAsset() throws SQLException, InterruptedException, IOException {
        viewsDataCompare(getNoProgramOnAssetQuery(oldViewName), getNoProgramOnAssetQuery(newViewName));
    }

    @Test(enabled = false, priority = 11)
    public static void getCartridgeWithNo12Lanes() throws SQLException, InterruptedException, IOException {
        //   viewsDataCompare(getCartridgeWithNo12LanesQuery(oldViewName), getCartridgeWithNo12LanesQuery(newViewName));
        compareTablesAndGenerateExcel(getCartridgeWithNo12LanesQuery(oldViewName), getCartridgeWithNo12LanesQuery(newViewName));
    }


    @AfterTest
    public void endreport() throws Exception {
        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
        extent.flush();
    }
}
