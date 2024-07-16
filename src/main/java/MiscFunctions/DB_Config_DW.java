package MiscFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Config.ReadPropertyFile;
import org.testng.annotations.Test;

public class DB_Config_DW {

    static Connection con = null;
    private static Statement stmt;
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

    public static String DB_UserName = config.ie_database_username();
    public static String DB_Password = config.ie_database_password();
    public static String DB_Name = config.ie_database_dw();

    @BeforeTest
    public static void test() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://Project123-asql-001.database.windows.net;databaseName=" + DB_Name + ";user=" + DB_UserName + ";Password=" + DB_Password;
           // String DB_URL = "jdbc:sqlserver://sql-ie-prod-001.database.windows.net;databaseName=" + DB_Name + ";user=" + DB_UserName + ";Password=" + DB_Password;
            Connection con = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
            setStmt(con.createStatement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterTest
    public static void tearDown() throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void setStmt(Statement stmt) {
        DB_Config_DW.stmt = stmt;
    }

//    @Test
//    public static void sampleDBConnectiontest() {
//        try {
//            String selectQuery = "select status from Patho_2_data where RUN_ID = '20231101_Salm_1_5802'";
//            ResultSet rs = getStmt().executeQuery(selectQuery);
//            while (rs.next()) {
//                System.out.println("Status: " + rs.getString("status"));
//            }
//            getStmt().close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
