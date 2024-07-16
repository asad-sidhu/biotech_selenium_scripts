package Test.BiotechProject01.Ingestions.DBValidations;

import MiscFunctions.DB_Config_DW;
import Test.BiotechProject01.Ingestions.DBValidations.Queries.DSPatho_1Asset_Queries;
import Test.BiotechProject01.Ingestions.DBValidations.Queries.DSPatho_1OPGMetadata_Queries;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ExtentReports.ExtentReport.initReport;
import static MiscFunctions.DBValidationMethods.*;
import static MiscFunctions.ExtentVariables.extent;


public class DSPatho_1OPGMetadataView extends DB_Config_DW {


    @BeforeSuite
    public static void setUp() {
        initReport("DSPatho_1View");
    }

    @Test(enabled = true, priority = 1)
    public static void AllRowsCompare() throws SQLException, InterruptedException, IOException {
        viewsRowCompare(DSPatho_1OPGMetadata_Queries.getAllRowsCountQuery(DSPatho_1OPGMetadata_Queries.oldViewName), DSPatho_1OPGMetadata_Queries.getAllRowsCountQuery(DSPatho_1OPGMetadata_Queries.newViewName));
    }

    @Test(enabled = true, priority = 2)
    public static void AllColumnsMatched() throws SQLException, InterruptedException, IOException {
        viewsColumnsMatched(DSPatho_1Asset_Queries.getColumnsMatchedQuery(DSPatho_1OPGMetadata_Queries.oldViewName, DSPatho_1OPGMetadata_Queries.newViewName));
    }

    @Test(enabled = true, priority = 3)
    public static void AllColumnsUnmatched() throws SQLException, InterruptedException, IOException {
        viewsUnmatchedColumns(DSPatho_1Asset_Queries.getColumnsMatchedQuery(DSPatho_1OPGMetadata_Queries.oldViewName, DSPatho_1OPGMetadata_Queries.newViewName));
        viewsUnmatchedColumns(DSPatho_1Asset_Queries.getColumnsMatchedQuery(DSPatho_1OPGMetadata_Queries.newViewName, DSPatho_1OPGMetadata_Queries.oldViewName));
    }

    @Test(enabled = true, priority = 4)
    public static void AllDataCompare() throws SQLException, InterruptedException, IOException {
        rowsDataCompare(DSPatho_1Asset_Queries.getColumnsMatchedQuery(DSPatho_1OPGMetadata_Queries.oldViewName, DSPatho_1OPGMetadata_Queries.newViewName), DSPatho_1OPGMetadata_Queries.oldViewName, DSPatho_1OPGMetadata_Queries.newViewName);
    }


    @AfterTest
    public void endreport() throws Exception {
        DB_Config_DW.tearDown();
        DB_Config_DW.getStmt();
        DB_Config_DW.setStmt(getStmt());
        extent.flush();
    }
}
