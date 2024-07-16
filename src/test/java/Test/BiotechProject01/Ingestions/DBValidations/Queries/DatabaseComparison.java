package Test.BiotechProject01.Ingestions.DBValidations.Queries;

import MiscFunctions.DB_Config_DW;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseComparison extends DB_Config_DW {

//    public static void main(String[] args) {
//        compareDatabaseTables();
//    }

    @Test(enabled = true, priority = 2)
    public static void AllRowsCompare() throws SQLException, InterruptedException, IOException {
        compareDatabaseTables();
    }



    public static void compareDatabaseTables() {
        String url1 = "jdbc:sqlserver://BiotechProject01-asql-001.database.windows.net;databaseName=PRH-IE-DW-05-22-2023;user="+DB_UserName+";Password="+DB_Password; // Replace with your first database URL
        String url2 = "jdbc:sqlserver://BiotechProject01-asql-001.database.windows.net;databaseName=PRH-IE-DW-05-22-2023;user="+DB_UserName+";Password="+DB_Password; // Replace with your second database URL
        String username = DB_UserName; // Replace with your username
        String password = DB_Password; // Replace with your password

        // Establish connection for first database
        try (Connection conn1 = DriverManager.getConnection(url1, username, password);
             Connection conn2 = DriverManager.getConnection(url2, username, password)) {

            List<String> table1Data = retrieveData(conn1, DSPatho_1Asset_Queries.oldViewName);
            List<String> table2Data = retrieveData(conn2, DSPatho_1Asset_Queries.newViewName);

            List<String> sortedList = table1Data.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
            List<String> sortedListNew = table2Data.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());

//          List<String> differingRows = compareLists(table1Data, table2Data);
            List<String> differingRows = compareLists(sortedList, sortedListNew);

            if (!differingRows.isEmpty()) {
                writeDataToExcel(differingRows);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> retrieveData(Connection conn, String queryView) throws SQLException {
        List<String> data = new ArrayList<>();

        String query = DSPatho_1Asset_Queries.getAllDataQuery(queryView);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            System.out.println(columns);

            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columns; i++) {
                    row.append(rs.getString(i));
                    if (i < columns) {
                        row.append(",");
                    }
                }
                data.add(row.toString());
            }
        }

        return data;
    }

    public static List<String> compareLists(List<String> list1, List<String> list2) {
        List<String> differingRows = new ArrayList<>();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(list1, list2);

        for (int i=0;i<list1.size();i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                differingRows.add(list1.get(i));
            }
        }
        for (String row : list1) {
            if (!list2.contains(row)) {
                differingRows.add(row);
            }
        }

        for (String row : list2) {
            if (!list1.contains(row)) {
                differingRows.add(row);
            }
        }

        return differingRows;
    }

    public static void writeDataToExcel(List<String> rows) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream("differing_rows.xlsx")) {

            Sheet sheet = workbook.createSheet("Differing Rows");
            int rowNum = 0;

            for (String row : rows) {
                Row excelRow = sheet.createRow(rowNum++);
                String[] rowData = row.split(",");
                int cellNum = 0;
                for (String data : rowData) {
                    Cell cell = excelRow.createCell(cellNum++);
                    cell.setCellValue(data);
                }
            }

            workbook.write(fileOut);
            System.out.println("Differing rows have been written to the Excel file.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}