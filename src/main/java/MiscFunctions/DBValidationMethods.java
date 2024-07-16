package MiscFunctions;

import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static Config.BaseTest.saveResultNoScreenshot;
import static MiscFunctions.DateUtil.date100;
import static MiscFunctions.ExtentVariables.*;

public class DBValidationMethods extends DB_Config_DW {
    public static void viewsDataCompare(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;

            long startTimeQuery1 = System.currentTimeMillis();

            ResultSet rs1 = getStmt().executeQuery(query1);

            long endTimeQuery1 = System.currentTimeMillis();
            long elapsedTimeQuery1 = endTimeQuery1 - startTimeQuery1;
            System.out.println("Response time Query 1: " + elapsedTimeQuery1 + " milliseconds");

            Thread.sleep(3000);
            List<String> data = new ArrayList<String>();

            ResultSetMetaData rsmd = rs1.getMetaData();
            int column_count = rsmd.getColumnCount();  //get column count

            while (rs1.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1.getString(i);
                    data.add(columnValue);    //add all table data to List 'data'
                }
            }

            String query2 = newView;

            long startTimeQuery2 = System.currentTimeMillis();

            ResultSet rs2 = getStmt().executeQuery(query2);

            long endTimeQuery2 = System.currentTimeMillis();
            long elapsedTimeQuery2 = endTimeQuery2 - startTimeQuery2;
            System.out.println("Response time Query 2: " + elapsedTimeQuery2 + " milliseconds");

            int total_tabledata_old = column_count * data.size() / column_count;  //get total number of values in table
            System.out.println("Total Rows Returned for method 1 '" + methodName + "' are " + total_tabledata_old / column_count);

            Thread.sleep(3000);
            List<String> datanew = new ArrayList<String>();

            test.info("Old View Query execution time: " + elapsedTimeQuery1 / 1000 + " seconds (" + elapsedTimeQuery1 + " milliseconds)");
            test.info("New View Query execution time: " + elapsedTimeQuery2 / 1000 + " seconds (" + elapsedTimeQuery2 + " milliseconds)");
            long secondsDifference = (elapsedTimeQuery2 / 1000) - (elapsedTimeQuery1 / 1000);
            long millisecondsDifference = (elapsedTimeQuery2) - (elapsedTimeQuery1);
            test.info("New Query execution took: " + secondsDifference + " seconds more (" + millisecondsDifference + " milliseconds)");


            while (rs2.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    datanew.add(columnValue);   //add all table data to List 'datanew'

                    if (methodName.equals("DataCompareNoCyclingConfig") ||
                            methodName.equals("OutOfIntervalRange") ||
                            methodName.equals("NoAssetAssociation")) {

                        test.info("T_Run_ID: " + rs2.getString("T_RUN_ID") +
                                " | CollectionDate: " + rs2.getString("COLLECTION_DATE") +
                                " | HouseID: " + rs2.getString("HOUSE_ID") +
                                " | CollectionSiteID: " + rs2.getString("COLLECTION_SITE_ID") +
                                " | ComplexID: " + rs2.getString("COMPLEX_ID") +
                                " | FARM_ID: " + rs2.getString("FARM_ID"));
                    }

                    if (methodName.equals("getCartridgeWithNo12Lanes")) {
                        test.info("CartridgeID: " + rs2.getString("CARTRIDGEID") +
                                " | SampleID: " + rs2.getString("SAMPLEID"));
                    }

                    if (methodName.equals("getNoCollectionDate") || methodName.equals("getNoProgramOnAsset")) {
                        test.info("T_RUN_ID: " + rs2.getString("T_RUN_ID") +
                                " | SampleID: " + rs2.getString("SAMPLE_ID"));
                    }

                    if (methodName.equals("getNoOfSamplesPerCollection")) {
                        test.info("CollectionSiteID: " + rs2.getString("COLLECTION_SITE_ID") +
                                " | CollectionDate: " + rs2.getString("Collection Date") +
                                " | SamplesCollected: " + rs2.getString("Samples Collected"));
                    }


//                    if (methodName.equals("AllDataCompare")) {
//                        test.info("T_RUN_ID: "+rs2.getString("T_RUN_ID"));
//                    }
                }
            }

            List<String> sortedList = data.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew = datanew.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

            int total_tabledata = column_count * datanew.size() / column_count;  //get total number of values in table

            //     System.out.println("1: "+total_tabledata);
            //     System.out.println("2: "+datanew.size());

            System.out.println("Total Rows Returned for method 2 '" + methodName + "' are " + total_tabledata / column_count);

            for (int z = 1; z <= total_tabledata; z++) {
                try {
                    softAssert.assertEquals(sortedListNew.get(z - 1), sortedList.get(z - 1), "Data not matching in Table 1 row " + (z - 1) / column_count);

                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("row " + (z - 1));
                }
            }

            test.log(Status.INFO, "Total Rows Returned for method " + methodName + "' are " + total_tabledata / column_count);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


    public static void viewsDataCompareSP(String oldView, String newView) throws SQLException, InterruptedException, IOException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the data in each row for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            String query1 = oldView;

            long startTimeQuery1 = System.currentTimeMillis();

            String DB_URL = "jdbc:sqlserver://Project123-asql-001.database.windows.net;databaseName=" + DB_Name + ";user=" + DB_UserName + ";Password=" + DB_Password;
            Connection conn = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
            setStmt(conn.createStatement());

            CallableStatement cstmt = conn.prepareCall(query1);
            ResultSet rs1 = cstmt.executeQuery();

            long endTimeQuery1 = System.currentTimeMillis();
            long elapsedTimeQuery1 = endTimeQuery1 - startTimeQuery1;
            System.out.println("Response time Query 1: " + elapsedTimeQuery1 + " milliseconds");

            Thread.sleep(3000);

            List<String> dataTable1 = new ArrayList<String>();
            List<String> dataTable2 = new ArrayList<String>();
            List<String> dataTable3 = new ArrayList<String>();
            List<String> dataTable4 = new ArrayList<String>();

            ResultSetMetaData rsmd1 = rs1.getMetaData();
            int column_count1 = rsmd1.getColumnCount();  //get column count
            while (rs1.next()) {
                //   System.out.print("First Name: "+rs1.getString("interventionTypeName")+", ");
                int column_count = rsmd1.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1.getString(i);
                    dataTable1.add(columnValue);    //add all table data to List 'dataTable1'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs2 = cstmt.getResultSet();
            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int column_count2 = rsmd2.getColumnCount();  //get column count
            while (rs2.next()) {
                int column_count = rsmd2.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2.getString(i);
                    dataTable2.add(columnValue);    //add all table data to List 'dataTable2'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs3 = cstmt.getResultSet();
            ResultSetMetaData rsmd3 = rs3.getMetaData();
            int column_count3 = rsmd3.getColumnCount();  //get column count
            while (rs3.next()) {
                int column_count = rsmd3.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs3.getString(i);
                    dataTable3.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            cstmt.getMoreResults();
            ResultSet rs4 = cstmt.getResultSet();
            ResultSetMetaData rsmd4 = rs4.getMetaData();
            int column_count4 = rsmd4.getColumnCount();  //get column count
            while (rs4.next()) {
                int column_count = rsmd4.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs4.getString(i);
                    dataTable4.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            String query2 = newView;

            long startTimeQuery2 = System.currentTimeMillis();

            CallableStatement cstmtNew = conn.prepareCall(query2);
            ResultSet rs1New = cstmtNew.executeQuery();

            long endTimeQuery2 = System.currentTimeMillis();
            long elapsedTimeQuery2 = endTimeQuery2 - startTimeQuery2;
            System.out.println("Response time Query 2: " + elapsedTimeQuery2 + " milliseconds");

            Thread.sleep(3000);
            List<String> dataTableNew1 = new ArrayList<String>();
            List<String> dataTableNew2 = new ArrayList<String>();
            List<String> dataTableNew3 = new ArrayList<String>();
            List<String> dataTableNew4 = new ArrayList<String>();

            test.info("Old View Query execution time: " + elapsedTimeQuery1 / 1000 + " seconds (" + elapsedTimeQuery1 + " milliseconds)");
            test.info("New View Query execution time: " + elapsedTimeQuery2 / 1000 + " seconds (" + elapsedTimeQuery2 + " milliseconds)");
            long secondsDifference = (elapsedTimeQuery2 / 1000) - (elapsedTimeQuery1 / 1000);
            long millisecondsDifference = (elapsedTimeQuery2) - (elapsedTimeQuery1);
            test.info("New Query execution took: " + secondsDifference + " seconds more (" + millisecondsDifference + " milliseconds)");


            ResultSetMetaData rsmd1New = rs1New.getMetaData();
            int column_count1New = rsmd1New.getColumnCount();  //get column count
            //   System.out.println("Columns Table 1: "+column_count1New);
            while (rs1New.next()) {
                int column_count = rsmd1New.getColumnCount();  //get column count
                //      test.info("T_RUN_ID Table 1: "+rs1New.getString("T_RUN_ID"));
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs1New.getString(i);
                    dataTableNew1.add(columnValue);    //add all table data to List 'dataTable1'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs2New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd2New = rs2New.getMetaData();
            int column_count2New = rsmd2New.getColumnCount();  //get column count
            //  System.out.println("Columns Table 2: "+column_count2New);
            while (rs2New.next()) {
                int column_count = rsmd2New.getColumnCount();  //get column count
                //    test.info("T_RUN_ID Table 2: "+rs2New.getString("T_RUN_ID"));
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs2New.getString(i);
                    dataTableNew2.add(columnValue);    //add all table data to List 'dataTable2'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs3New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd3New = rs3New.getMetaData();
            int column_count3New = rsmd3New.getColumnCount();  //get column count
            // System.out.println("Columns Table 3: "+column_count3New);
            while (rs3New.next()) {
                int column_count = rsmd3New.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs3New.getString(i);
                    //    test.info("SITE_ID Table 3: "+rs2New.getString("SITE_ID"));
                    dataTableNew3.add(columnValue);    //add all table data to List 'dataTable3'
                }
            }


            cstmtNew.getMoreResults();
            ResultSet rs4New = cstmtNew.getResultSet();
            ResultSetMetaData rsmd4New = rs4New.getMetaData();
            int column_count4New = rsmd4New.getColumnCount();  //get column count
            //  System.out.println("Columns Table 4: "+column_count4New);
            while (rs4New.next()) {
                int column_count = rsmd4New.getColumnCount();  //get column count
                for (int i = 1; i <= column_count; i++) {
                    String columnValue = rs4New.getString(i);
                    //     test.info("Intervention Name Table 4: "+rs2New.getString("interventionName"));
                    dataTableNew4.add(columnValue);    //add all table data to List 'dataTable4'
                }
            }


            List<String> sortedList1 = dataTable1.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew1 = dataTableNew1.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

            List<String> sortedList2 = dataTable2.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew2 = dataTableNew2.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

            List<String> sortedList3 = dataTable3.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew3 = dataTableNew3.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

            List<String> sortedList4 = dataTable4.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew4 = dataTableNew4.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

            int total_tabledata1 = column_count1 * dataTable1.size() / column_count1;  //get total number of values in table
            int total_tabledata1New = column_count1New * dataTableNew1.size() / column_count1New;  //get total number of values in table

            for (int z = 1; z <= total_tabledata1New; z++) {
                softAssert.assertEquals(sortedListNew1.get(z - 1), sortedList1.get(z - 1), "Data not matching in Table 1 row " + (z - 1) / column_count1);
            }
            System.out.println("Total Rows Returned for SP Table 1 '" + methodName + "' are " + total_tabledata1New / column_count1New);
            test.log(Status.INFO, "Total Rows Returned for Old SP Table 1 " + methodName + "' are " + total_tabledata1 / column_count1);
            test.log(Status.INFO, "Total Rows Returned for New SP Table 1 " + methodName + "' are " + total_tabledata1New / column_count1New);
            test.log(Status.INFO, "Total Columns Returned for New SP Table 1 " + methodName + "' are " + column_count1New);


            int total_tabledata2 = column_count2 * dataTable2.size() / column_count2;  //get total number of values in table
            int total_tabledata2New = column_count2New * dataTableNew2.size() / column_count2New;  //get total number of values in table

            for (int z = 1; z <= total_tabledata2; z++) {
                softAssert.assertEquals(sortedListNew2.get(z - 1), sortedList2.get(z - 1), "Data not matching in Table 2 row " + (z - 1) / column_count1);
            }
            System.out.println("Total Rows Returned for SP Table 2 '" + methodName + "' are " + total_tabledata2New / column_count2New);
            test.log(Status.INFO, "Total Rows Returned for Old SP Table 2 " + methodName + "' are " + total_tabledata2 / column_count2);
            test.log(Status.INFO, "Total Rows Returned for New SP Table 2 " + methodName + "' are " + total_tabledata2New / column_count2New);
            test.log(Status.INFO, "Total Columns Returned for New SP Table 2 " + methodName + "' are " + column_count2New);

            int total_tabledata3 = column_count3 * dataTable3.size() / column_count3;  //get total number of values in table
            int total_tabledata3New = column_count3New * dataTableNew3.size() / column_count3New;  //get total number of values in table

            for (int z = 1; z <= total_tabledata3; z++) {
                softAssert.assertEquals(sortedListNew3.get(z - 1), sortedList3.get(z - 1), "Data not matching in Table 3 row " + (z - 1) / column_count1);
            }
            System.out.println("Total Rows Returned for SP Table 3 '" + methodName + "' are " + total_tabledata3New / column_count3New);
            test.log(Status.INFO, "Total Rows Returned for Old SP Table 3 " + methodName + "' are " + total_tabledata3 / column_count3);
            test.log(Status.INFO, "Total Rows Returned for New SP Table 3 " + methodName + "' are " + total_tabledata3New / column_count3New);
            test.log(Status.INFO, "Total Columns Returned for New SP Table 3 " + methodName + "' are " + column_count3New);

            int total_tabledata4 = column_count4 * dataTable4.size() / column_count4;  //get total number of values in table
            int total_tabledata4New = column_count4New * dataTableNew4.size() / column_count4New;  //get total number of values in table

            for (int z = 1; z <= total_tabledata4; z++) {
                softAssert.assertEquals(sortedListNew4.get(z - 1), sortedList4.get(z - 1), "Data not matching in Table 4 row " + (z - 1) / column_count1);
            }
            System.out.println("Total Rows Returned for SP Table 4 '" + methodName + "' are " + total_tabledata4New / column_count4New);
            test.log(Status.INFO, "Total Rows Returned for Old SP Table 4 " + methodName + "' are " + total_tabledata4 / column_count4);
            test.log(Status.INFO, "Total Rows Returned for New SP Table 4 " + methodName + "' are " + total_tabledata4New / column_count4New);
            test.log(Status.INFO, "Total Columns Returned for New SP Table 4 " + methodName + "' are " + column_count4New);

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        } catch (Exception ex) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(ex));
        }
    }


    public static void viewsRowCompare(String oldView, String newView) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Compare the row count for method " + methodName);
            SoftAssert softAssert = new SoftAssert();


            String query1 = oldView;
            ResultSet rs1 = getStmt().executeQuery(query1);
            rs1.next();
            int countOldView = rs1.getInt(1);
            System.out.println("Total Number of Rows in Old View for method '" + methodName + "' are " + countOldView);
            test.info("Total Number of Rows in Old View for method '" + methodName + "' are " + countOldView);


            String query2 = newView;
            ResultSet rs2 = getStmt().executeQuery(query2);
            rs2.next();
            int countNewView = rs2.getInt(1);
            System.out.println("Total Number of Rows in New View for method '" + methodName + "' are " + countNewView);
            test.info("Total Number of Rows in Old View for method '" + methodName + "' are " + countNewView);

            softAssert.assertEquals(countNewView, countOldView);
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void viewsColumnsMatched(String matchedColumnsQuery) throws InterruptedException, IOException, SQLException {
        try {
            test = extent.createTest("Check both views have same columns for method ");
            SoftAssert softAssert = new SoftAssert();

            ResultSet rs = getStmt().executeQuery(matchedColumnsQuery);

            List<String> matchedColumns = new ArrayList<>();

            // Populate matchedColumns with the matched column names
            int rowCount = 0;
            while (rs.next()) {
                String columnName = rs.getString("column_name");
                matchedColumns.add(columnName);
                rowCount++;
            }

            // Perform the comparison and validation
            // You can modify this part based on your specific requirements
            if (matchedColumns.isEmpty()) {
                System.out.println("No matched columns found between the views.");
                test.info("No matched columns found between the views.");
            } else {
                // System.out.println("Matched columns between the views: " + matchedColumns);
                test.info("Matched columns between the views: " + matchedColumns);
                System.out.println("Total column_names that are matched are: " + rowCount);
                // Create an Excel workbook
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Matched Columns");

                // Write unmatched columns to Excel file
                int rowNumber = 0;
                Row headerRow = sheet.createRow(rowNumber++);
                headerRow.createCell(0).setCellValue("Column Name");

                for (String columnName : matchedColumns) {
                    Row dataRow = sheet.createRow(rowNumber++);
                    dataRow.createCell(0).setCellValue(columnName);
                }

                // Save the workbook to a file
                //String excelFilePath = "C:\\Users\\xxxxxxxxxx.1234\\Downloads\\matched_columns.xlsx";
                String excelFilePath = fileDownloadPath+"\\matched_columns.xlsx";
                FileOutputStream fileOut = new FileOutputStream(excelFilePath);
                workbook.write(fileOut);
                fileOut.close();

                System.out.println("Matched columns exported to Excel file: " + excelFilePath);

            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


    public static void viewsUnmatchedColumns(String unmatchedColumnsQuery) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Check unmatched columns between the views for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            ResultSet rs = getStmt().executeQuery(unmatchedColumnsQuery);

            List<String> unmatchedColumns = new ArrayList<>();

            // Populate unmatchedColumns with the unmatched column names
            int rowCount = 0;
            while (rs.next()) {
                String columnName = rs.getString("column_name");
                unmatchedColumns.add(columnName);
                rowCount++;
            }

            if (unmatchedColumns.isEmpty()) {
                test.info("No unmatched columns found between the views.");
            } else {
                //  System.out.println("Unmatched columns between the views: " + unmatchedColumns);
                System.out.println("Total column_names that are unmatched are: " + rowCount);
                test.info("Unmatched columns between the views: " + unmatchedColumns);
                test.info("Total column_names that are unmatched are: " + rowCount);

                // Create an Excel workbook
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Unmatched Columns");

                // Write unmatched columns to Excel file
                int rowNumber = 0;
                Row headerRow = sheet.createRow(rowNumber++);
                headerRow.createCell(0).setCellValue("Column Name");

                for (String columnName : unmatchedColumns) {
                    Row dataRow = sheet.createRow(rowNumber++);
                    dataRow.createCell(0).setCellValue(columnName);
                }

                // Save the workbook to a file
                // String excelFilePath = "C:\\Users\\xxxxxxxxxx.1234\\Downloads\\unmatched_columns.xlsx";
                String excelFilePath = fileDownloadPath+"\\unmatched_columns.xlsx";
                FileOutputStream fileOut = new FileOutputStream(excelFilePath);
                workbook.write(fileOut);
                fileOut.close();

                System.out.println("Unmatched columns exported to Excel file: " + excelFilePath);

            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }


    public static void compareTablesAndGenerateExcel(String oldView, String newView) {
        try {

            String query1 = oldView;
            String query2 = newView;

            ResultSet resultSet1 = getStmt().executeQuery(query1);
            ResultSet resultSet2 = getStmt().executeQuery(query2);

            // Create a new Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Differences");

            // Create header row in Excel sheet
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = resultSet1.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(metaData.getColumnName(i));
            }

            // Compare rows between tables and write differences to Excel
            int rowIndex = 1;
            while (resultSet1.next() && resultSet2.next()) {
                Row row = sheet.createRow(rowIndex++);
                boolean hasDifference = false;
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i);
                    Object value1 = resultSet1.getObject(i);
                    Object value2 = resultSet2.getObject(i);
                    if (!isEqual(value1, value2)) {
                        hasDifference = true;
                        cell.setCellValue(value1 + " <> " + value2);
                    }
                }

                // Highlight the row with differences in the web page
            }

            // Save the Excel workbook
            FileOutputStream outputStream = new FileOutputStream("differences.xlsx");
            workbook.write(outputStream);
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    public static void conditionsCheckDataCompare( String oldView, String newView) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Check matched columns between the views for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            ResultSet rs = getStmt().executeQuery(oldView);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuffer sb = new StringBuffer();
            List<String> rs1Rows = new ArrayList<>();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);
                    sb.append(columnName + ":" + columnValue + " ");
                    rs1Rows.add(columnValue);
                }
                //  System.out.println(sb.toString());
                sb.setLength(0);
            }


            int rows = 50;
            int columns = columnCount;

            String[][] oldViewArray = new String[rows][columns];

            // Convert the list to a multidimensional array
            int index = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    oldViewArray[i][j] = rs1Rows.get(index);
                    index++;
                }
            }

            //    System.out.println("Length: " + oldViewArray.length);

            int q = 0;

            for (int x = 0; x < oldViewArray.length; x++) {


                //   String query1 = "Select * from " + oldView + " where T_RUN_ID = '" + multidimensionalArray[x][0] + "'";
                String query1 = "with CTEAsset as (select fm.ID, fm.FARM_INTERNAL_ID, fm.animal_SIZE, fm.animal_SIZE_ID, fhd.HOUSE_INTERNAL_ID, House.siteUniqueNumber as houseUniqueNumber, Farm.siteUniqueNumber as farmUniqueNumber, FS.PROCESSING_DATE, fhd.deployment_DATE, fhd.crop_DATE from Asset_MGMT fm inner join ET.[Site] Farm on fm.FARM_INTERNAL_ID = Farm.siteId and Farm.isActive = 1 and Farm.isDeleted = 0 inner join Asset_HOUSE_DETAILS fhd on fm.ID = fhd.Asset_ID and fhd.isActive = 1 and fhd.isDeleted = 0 inner join ET.[Site] House on fhd.HOUSE_INTERNAL_ID = House.siteId and House.isActive = 1 and House.isDeleted = 0 left join Asset_SETTLEMENT FS on FS.Asset_ID = fm.ID and FS.IS_ACTIVE = 1 and FS.Is_DELETED = 0 where fm.animal_SIZE_ID is not null), CTECycling as (select DSCC.CYCLING_INTERVAL_NAME as cyclingintervalName, DSCC.TARGET_AGE_RANGE_MIN as startDay, DSCC.TARGET_AGE_RANGE_MAX as EndDay, Complex.siteUniqueNumber as INTERNAL_SITE_ID, DSCC.Asset_ID from DS_COMPLEX_CYCLING_CONFIG DSCC inner join ET.[Site] Complex on DSCC.INTERNAL_SITE_ID = Complex.siteId and Complex.isActive = 1 and Complex.isDeleted = 0) select cd.T_RUN_ID, cd.COLLECTION_DATE, cd.HOUSE_ID, cd.COLLECTION_SITE_ID, cd.COMPLEX_ID, cd.FARM_ID from " + newView + " cd where (cd.COUNT_OUTCOME is null or cd.COUNT_OUTCOME = 'Completed') and cd.Asset_DAY is null and cd.T_RUN_ID = '" + oldViewArray[x][0] + "' and exists (select 1 from CTEAsset flk WHERE cd.COLLECTION_DATE > flk.deployment_DATE AND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE, flk.crop_DATE, DATEADD(DAY, 63, flk.deployment_DATE)) AND DATEDIFF(DAY, flk.deployment_DATE, cd.COLLECTION_DATE) <= DATEDIFF(DAY, flk.deployment_DATE, COALESCE(flk.PROCESSING_DATE, flk.crop_DATE, DATEADD(DAY, 63, flk.deployment_DATE))) AND cd.HOUSE_ID = flk.houseUniqueNumber AND cd.FARM_ID = flk.farmUniqueNumber) AND NOT EXISTS (SELECT * FROM CTECycling DSCC INNER JOIN CTEAsset F ON DSCC.Asset_ID = F.ID WHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID AND cd.HOUSE_ID = F.houseUniqueNumber)";

                //System.out.println(query1);
                ResultSet rs2 = getStmt().executeQuery(query1);

                if (!rs2.next()) {

                    System.out.println("T_RUN_ID "+oldViewArray[x][0]+" not found in new view");
                } else {
                    //  System.out.println("hi");
                    //  System.out.println("fdsd"+rs2.getString(1));
                    List<String> rs2Rows = new ArrayList<>();
                    ///////////////////
                    //   while (rs2.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnValue = rs2.getString(i);
                        rs2Rows.add(columnValue);
                    }
                    sb.setLength(0);
                    //     }

                    int rows2 = 1;
                    int columns2 = columnCount;

                    String[][] newViewArray = new String[rows2][columns2];

                    int index1 = 0;
                    for (int i = 0; i < rows2; i++) {
                        for (int j = 0; j < columns2; j++) {
                            if (index1 < rs2Rows.size()) {
                                newViewArray[i][j] = rs2Rows.get(index1);
                                index1++;
                            }
                        }
                    }

                    for (int a = 0; a < columnCount; a++) {
                        String columnName = metaData.getColumnName(a + 1);
                        //  System.out.println("Old: " + oldViewArray[q][a]);
                        //  System.out.println("New: " + newViewArray[0][a]);
                        softAssert.assertEquals(newViewArray[0][a], oldViewArray[q][a],  "------------------------------------------------------------------------- \n The values did not matched for " + oldViewArray[q][0] + " in column " + columnName + "\n");
                    }
                }
                q++;

            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            //  logAssertionFailure(er.getMessage(), "C:\\Users\\xxxxxxxxxx.1234\\Downloads\\log.txt");
            logAssertionFailure(er.getMessage(), fileDownloadPath+"\\Downloads\\log.txt");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }

    public static void conditionalDataCompare(String oldView, String newView, String logFileName) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Check matched columns between the views for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            ResultSet rs = getStmt().executeQuery(oldView);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            int row =0;
            StringBuffer sb = new StringBuffer();
            List<String> rs1Rows = new ArrayList<>();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);
                    sb.append(columnName + ":" + columnValue + " ");
                    rs1Rows.add(columnValue);
                }
                row++;
                sb.setLength(0);
            }


            //   int rows = rowsCount;
            int columns = columnCount;

            String[][] oldViewArray = new String[row][columns];
            int index = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < columns; j++) {
                    oldViewArray[i][j] = rs1Rows.get(index);
                    index++;
                }
            }

            System.out.println("Old View Array:");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(oldViewArray[i][j] + " ");
                }
                System.out.println();
            }

            ResultSet rs1 = getStmt().executeQuery(newView);

            ResultSetMetaData metaData1 = rs1.getMetaData();
            int columnCount1 = metaData1.getColumnCount();

            int row1 = 0;
            StringBuffer sb1 = new StringBuffer();
            List<String> rs2Rows = new ArrayList<>();
            while (rs1.next()) {
                for (int i = 1; i <= columnCount1; i++) {
                    String columnName = metaData1.getColumnName(i);
                    String columnValue = rs1.getString(i);
                    sb1.append(columnName + ":" + columnValue + " ");
                    rs2Rows.add(columnValue);
                }
                row1++;
                sb1.setLength(0);
            }


            //  int rows1 = rowsCount;
            int columns1 = columnCount1;

            String[][] newViewArray = new String[row1][columns1];
            int index1 = 0;
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < columns1; j++) {
                    newViewArray[i][j] = rs2Rows.get(index1);
                    index1++;
                }
            }

            for (int a = 1; a < row; a++) {
                for (int b = 2; b < columnCount; b++) {
                    String columnName = metaData.getColumnName(b + 1);
                    System.out.println(columnName);
                    System.out.println("Old: " + oldViewArray[a][b]);
                    System.out.println("New: " + newViewArray[a][b]);
                    softAssert.assertEquals(newViewArray[a][b], oldViewArray[a][b],  "------------------------------------------------------------------------- \n The values did not matched for " + oldViewArray[a][0] + " in column " + columnName + "\n");
                }
            }

            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            logAssertionFailure(er.getMessage(), logFileName);
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }

    static String logFilePath = fileDownloadPath+"\\MissingRunIDsLog.txt";


    public static void rowsDataCompare(String matchedColumnsQuery, String oldView, String newView) throws InterruptedException, IOException, SQLException {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTraceElements[2];
            String methodName = element.getMethodName();

            test = extent.createTest("Check matched columns between the views for method " + methodName);
            SoftAssert softAssert = new SoftAssert();

            ResultSet rs = getStmt().executeQuery(matchedColumnsQuery);

            List<String> matchedColumns = new ArrayList<>();

            PrintStream logFile = new PrintStream(new FileOutputStream(logFilePath));
            // Redirect System.out to the log file
            System.setOut(logFile);

            // Populate matchedColumns with the matched column names
            while (rs.next()) {
                String columnName = rs.getString(2);
                matchedColumns.add(columnName);
            }

            //  System.out.println("Matched columns: " + matchedColumns);

            // Replace "*" in the query with the matched columns
            String replacedQuery = "SELECT " + String.join(", ", matchedColumns) + " FROM dbo." + oldView
                    + " except SELECT " + String.join(", ", matchedColumns) + " FROM dbo." + newView;

               System.out.println(replacedQuery);

            rs = getStmt().executeQuery(replacedQuery);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            int row = 0;
            StringBuffer sb = new StringBuffer();
            List<String> rs1Rows = new ArrayList<>();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);
                    sb.append(columnName + ":" + columnValue + " ");
                    rs1Rows.add(columnValue);
                }
                row++;
                sb.setLength(0);
            }


            //int rows = 50;
            int columns = columnCount;

            // String[][] oldViewArray = new String[row][columns];
            String[][] oldViewArray = new String[row][columns];

            // Convert the list to a multidimensional array
            int index = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < columns; j++) {
                    oldViewArray[i][j] = rs1Rows.get(index);
                    index++;
                }
            }

            //    System.out.println("Length: " + oldViewArray.length);

            int q = 0;

            for (int x = 0; x < oldViewArray.length; x++) {

                String query1 = "Select " + String.join(", ", matchedColumns) + " from " + newView + " where T_RUN_ID = '" + oldViewArray[x][0] + "'";
                //   System.out.println("Run ID: "+oldViewArray[x][0]);
                ResultSet rs2 = getStmt().executeQuery(query1);

                if (!rs2.next()) {

                    System.out.println("T_RUN_ID "+oldViewArray[x][0]+" not found in new view");
                } else {
                    //  System.out.println("hi");
                    //  System.out.println("fdsd"+rs2.getString(1));
                    List<String> rs2Rows = new ArrayList<>();
                    ///////////////////
                    //   while (rs2.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnValue = rs2.getString(i);
                        rs2Rows.add(columnValue);
                    }
                    sb.setLength(0);
                    //     }

                    int rows2 = 1;
                    int columns2 = columnCount;

                    String[][] newViewArray = new String[rows2][columns2];

                    int index1 = 0;
                    for (int i = 0; i < rows2; i++) {
                        for (int j = 0; j < columns2; j++) {
                            if (index1 < rs2Rows.size()) {
                                newViewArray[i][j] = rs2Rows.get(index1);
                                index1++;
                            }
                        }
                    }

                    for (int a = 0; a < columnCount; a++) {
                        String columnName = metaData.getColumnName(a + 1);
                        //  System.out.println("Old: " + oldViewArray[q][a]);
                        //  System.out.println("New: " + newViewArray[0][a]);
                        softAssert.assertEquals(newViewArray[0][a], oldViewArray[q][a],  "------------------------------------------------------------------------- \n The values did not matched for " + oldViewArray[q][0] + " in column " + columnName + "\n");
                    }
                }
                q++;

            }
            softAssert.assertAll();
            test.pass("Test Passed Successfully");
            saveResultNoScreenshot(ITestResult.SUCCESS, null);
        } catch (AssertionError er) {
            test.fail("Test Failed");
            //  logAssertionFailure(er.getMessage(), "C:\\Users\\xxxxxxxxxx.1234\\Downloads\\log.txt");
            logAssertionFailure(er.getMessage(), fileDownloadPath+"\\log.txt");
            saveResultNoScreenshot(ITestResult.FAILURE, new Exception(er));
        }
    }

    private static void logAssertionFailure(String message, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
