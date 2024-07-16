package MiscFunctions;

import PageObjects.WebApiPage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Queries extends DB_Config_DW {

    public static String site = "none";

    public static String getUserIDQuery = "Select userId from dbo.[user] where userEmail = '" + config.ie_username() + "' and isActive = '1' and isDeleted = '0'";
    public static String getanimalSizeID = "SELECT Top 1 id FROM dbo.LookUp WHERE groupCode = '00030'";

    public Queries() throws SQLException {
    }

/////////////////////////////////////////////////////COMPLEX QUERIES/////////////////////////////////////////////////////////////////////////////////


    public static String getComplexNameAssignedToUserHavingFarmAndHouse(int userId) {
        String getComplexName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT Top 1 sitename FROM Site frm where frm.sitetypeId = 5  AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) and frm.orgnId = (SELECT Top 1 orgnID " +
                "FROM Site " +
                "WHERE siteTypeId IN (5, 6, 7))";
        return getComplexName;
    }


    public static String getComplexNameAssignedToUser(int userId) {
        String getComplexName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT  DISTINCT frm.siteName FROM Site frm WHERE frm.siteTypeId=5 AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getComplexName;
    }


    public static String getOneComplexNameAssignedToUser(int userId) {
        String getComplexName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT  Top 1 frm.siteName FROM Site frm WHERE frm.siteTypeId=5 AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getComplexName;
    }


    public static String getTwoComplexNameAssignedToUser(int userId) {
        String getComplexName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT  Top 2 frm.siteName FROM Site frm WHERE frm.siteTypeId=5 AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getComplexName;
    }


    public static String getComplexQuery(int userId) throws SQLException {  //HAVING FARM AND HOUSE UNDER COMPLEX
        String getComplex = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 5 AND orgnID = " + getOrgnId() + " AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) ";
        return getComplex;
    }

    public static String getCountOfComplexesAssignedToUserQuery(int userId) throws SQLException {  //HAVING FARM AND HOUSE UNDER COMPLEX
        String getComplex = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT Count(sitename) as count FROM Site frm where frm.sitetypeId = 5  AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR \n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR \n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getComplex;
    }

    public static int getCountOfComplexesAssignedTOUser() throws SQLException {
        ResultSet getComplexSiteID = DB_Config_DB.getStmt().executeQuery(Queries.getCountOfComplexesAssignedToUserQuery(getUsersId()));
        int count = 0;
        while (getComplexSiteID.next()) {
            count = getComplexSiteID.getInt("count");
            System.out.println("Count: " + count);
        }
        return count;
    }

    static String getComplexSiteID;

    static {
        try {
            getComplexSiteID = "DECLARE @userId BIGINT = " + getUsersId() + ";" +
                    "SELECT Top 1 siteId FROM Site frm where frm.sitetypeId = 5  AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                    "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                    "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                    "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) and frm.orgnId = (SELECT Top 1 orgnID " +
                    "FROM Site " +
                    "WHERE siteTypeId IN (5, 6, 7))";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//	public static String getComplexName = "SELECT TOP 1  frm.siteName FROM et.site frm\r\n"
//			+ "JOIN et.site hs ON frm.siteId  = hs.parentSiteId\r\n"
//			+ "AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
//			+ "WHERE frm.siteTypeId = 5";


    public static int getComplexSiteID() throws SQLException {
        ResultSet getComplexSiteID = DB_Config_DB.getStmt().executeQuery(Queries.getComplexSiteID);
        int complexSiteID = 0;
        while (getComplexSiteID.next()) {
            complexSiteID = getComplexSiteID.getInt("siteId");
            System.out.println("Site ID: " + complexSiteID);
        }
        return complexSiteID;
    }


    public static int getComplexSiteId() throws SQLException {
        ResultSet getSiteID = DB_Config_DB.getStmt().executeQuery(getComplexQuery(getUsersId()));
        int siteID = 0;
        while (getSiteID.next()) {
            siteID = getSiteID.getInt("siteID");
            System.out.println("Site ID: " + siteID);
        }
        return siteID;
    }


    public static String getComplexName() throws SQLException {
        ResultSet getSiteName = DB_Config_DB.getStmt().executeQuery(getComplexQuery(getUsersId()));
        String siteName = null;
        while (getSiteName.next()) {
            siteName = getSiteName.getString("siteName");
            System.out.println("Complex Name: " + siteName);
        }
        return siteName;
    }


//////////////////////////////////////////////////////////FARM QUERIES///////////////////////////////////////////////////////////////////////////

    public static String getFarmName = "SELECT TOP 1  frm.siteName FROM et.site frm\r\n"
            + "JOIN et.site hs ON frm.siteId  = hs.parentSiteId\r\n"
            + "AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
            + "WHERE frm.siteTypeId = 6";

    public static String getFarmHousesCount = "	SELECT Count(hs.siteName) as Count FROM et.site frm\r\n"
            + "			JOIN et.site hs ON frm.siteId  = hs.parentSiteId AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
            + "			WHERE frm.siteTypeId = 6 \r\n"
            + "			And frm.siteName = (" + getFarmName + ") ";


    public static String getFarmAssignedToUserQuery(int userId) throws SQLException {
        String getFarmName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 6 AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) ";
//				"and frm.orgnId = (SELECT TOP 1 orgnID\n" +
//				"FROM Site\n" +
//				"WHERE siteTypeId IN (6, 7) order by orgnid asc) ";
        return getFarmName;
    }

    public static String getFarmQuery(int userId) throws SQLException {    //HAVING HOUSE UNDER FARM
        String getFarm = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 6 AND orgnID = " + getOrgnId() + " AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getFarm;
    }


    public static String getFarmNameAssignedToUser() throws SQLException {
        ResultSet getFarmName = DB_Config_DB.getStmt().executeQuery(getFarmAssignedToUserQuery(getUsersId()));
        String farmName = null;
        while (getFarmName.next()) {
            farmName = getFarmName.getString("siteName");
            System.out.println("Farm Name: " + farmName);
        }
        return farmName;
    }


    public static String getFarmName() throws SQLException {
        ResultSet getSiteName = DB_Config_DB.getStmt().executeQuery(getFarmQuery(getUsersId()));
        String siteName = null;
        while (getSiteName.next()) {
            siteName = getSiteName.getString("siteName");
            System.out.println("Farm Name: " + siteName);
        }
        return siteName;
    }


    public static String getFarmHouseCount() throws SQLException {
        String getFarmHousesCount = "	SELECT Count(hs.siteName) as Count FROM et.site frm\r\n"
                + "			JOIN et.site hs ON frm.siteId  = hs.parentSiteId AND hs.isActive = 1 AND hs.isDeleted = 0 AND frm.isActive = 1 AND frm.isDeleted = 0\r\n"
                + "			WHERE frm.siteTypeId = 6 \r\n"
                + "			And frm.siteName = '" + getFarmNameAssignedToUser() + "' ";

        return getFarmHousesCount;
    }


    public static String getFarmWithExternalIDAssignedToUserQuery(int userId) throws SQLException {
        String getFarmName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 6 AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) and frm.orgnId = (SELECT TOP 1 orgnID\n" +
                "FROM Site st\n" +
                "WHERE siteTypeId IN (6, 7) and st.externalSiteId is not null order by orgnid asc) ";
        return getFarmName;
    }


//	public static String getFarmNameAssignedToUserHavingHouse(int userId) {
//		String getFarmName = "DECLARE @userId BIGINT = "+userId+";" +
//				"SELECT Top 1 sitename FROM Site frm where frm.sitetypeId = 6  AND frm.isActive=1 AND frm.isDeleted=0 AND " +
//				"(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
//				"frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
//				"frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) and frm.orgnId = (SELECT Top 1 orgnID " +
//				"FROM Site " +
//				"WHERE siteTypeId IN (6, 7)  order by orgnid asc)";
//		return getFarmName;
//	}


    public static String getFarmNameAssignedToUserAtIndex2(int userId) {
        String getFarmName = "DECLARE @userId BIGINT = " + userId + ";" +
                "\n" +
                "SELECT siteName\n" +
                "FROM\n" +
                "  (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum, *\n" +
                "   FROM\n" +
                "     (SELECT *\n" +
                "      FROM Site frm\n" +
                "      WHERE frm.siteTypeId = 6\n" +
                "        AND frm.isActive = 1\n" +
                "        AND frm.isDeleted = 0\n" +
                "        AND (frm.siteId IN (SELECT siteId\n" +
                "                            from abcSiteAssn\n" +
                "                            WHERE userId = @userId\n" +
                "                              AND isActive = 1\n" +
                "                              AND isDeleted = 0)\n" +
                "             OR frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId = @userId AND isActive = 1 AND isDeleted = 0) OR frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId = @userId AND isActive = 1 AND isDeleted = 0))) AS subquery ) AS numbered_rows WHERE RowNum = 2";

        return getFarmName;
    }


    public static String getFarmIDAssignedToUser(int userId) {
        String getFarmName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT TOP 1 siteId FROM Site frm WHERE frm.siteTypeId=6 AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getFarmName;
    }


    public static String getFarmNameWithExternalIDAssignedToUser() throws SQLException {
        ResultSet getFarmName = DB_Config_DB.getStmt().executeQuery(getFarmWithExternalIDAssignedToUserQuery(getUsersId()));
        String farmName = null;
        while (getFarmName.next()) {
            farmName = getFarmName.getString("siteName");
            System.out.println("Farm Name: " + farmName);
        }
        return farmName;
    }


///////////////////////////////////////////////////HOUSE QUERIES/////////////////////////////////////////////////////////////////////////

    public static String getHouseIDAssignedToUserQuery(int userId) {
        String getHouseName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT TOP 1 siteId FROM Site frm WHERE frm.siteTypeId=7 AND frm.isActive=1 AND frm.isDeleted=0 AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getHouseName;
    }


    public static String getHouseIDAssignedToUserOnAssignedFarm(int userId, int farmId) throws SQLException {
        String getHouseName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT TOP 1 siteId FROM Site frm WHERE frm.siteTypeId=7 AND frm.isActive=1 AND frm.isDeleted=0 AND parentSiteId= " + farmId + " AND " +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR " +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getHouseName;
    }


    public static String getHouseAssignedToUserQuery(int userId) {
        String getHouseName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT Top 1 * FROM Site frm where frm.sitetypeId = 7 AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) order by orgnid asc";
        return getHouseName;
    }

    public static String getHatcheryAssignedToUserQuery(int userId) {
        String getHouseName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT Top 1 sitename FROM Site frm where frm.sitetypeId = 12 AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getHouseName;
    }

    public static String getHatcheryAssignedToUser() throws SQLException {     //will return farm id of a farm having atleast 1 house
        ResultSet getHouse = DB_Config_DB.getStmt().executeQuery(getHatcheryAssignedToUserQuery(getUsersId()));
        String housename = null;
        while (getHouse.next()) {
            housename = getHouse.getString("sitename");
            System.out.println("House Name: " + housename);
        }
        return housename;
    }

    public static String getHatcheryHavingSamplingPlanAssignedToUserQuery(int userId) {
        String getHouseName = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT siteName FROM et.Site frm where frm.sitetypeId = 12 and frm.orgnId = (Select orgnid from et.Site where siteid = (Select Top 1 complex_id from  SAMPLING_PLAN_Asset_HATCHERY)) and frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId FROM et.UserSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM et.ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM et.UserTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getHouseName;
    }

    public static String getHatcheryAssignedToUserHavingSamplingPlan() throws SQLException {     //will return farm id of a farm having atleast 1 house
      System.out.println("Query:  "+getHatcheryHavingSamplingPlanAssignedToUserQuery(getUsersId()));

        ResultSet getHouse = DB_Config_DW.getStmt().executeQuery(getHatcheryHavingSamplingPlanAssignedToUserQuery(getUsersId()));
        String housename = null;
        while (getHouse.next()) {
            housename = getHouse.getString("siteName");
            System.out.println("House Name: " + housename);
        }
        return housename;
    }

    public static String getCountOfHatcheryHavingSamplingPlanAssignedToUserQuery() {
        String getHouseName = "SELECT COUNT(DISTINCT Sampling_Plan_Name) AS distinct_count\n" +
                "FROM SAMPLING_PLAN_Asset_HATCHERY\n" +
                "WHERE complex_id = (Select Top 1 complex_id from SAMPLING_PLAN_Asset_HATCHERY);";
        return getHouseName;
    }

    public static int getCountOfHatcherySamplingPlan() throws SQLException {     //will return farm id of a farm having atleast 1 house
        ResultSet getCount = DB_Config_DW.getStmt().executeQuery(getCountOfHatcheryHavingSamplingPlanAssignedToUserQuery());
        int count = 0;
        while (getCount.next()) {
            count = getCount.getInt("distinct_count");
            System.out.println("Count: " + count);
        }
        return count;
    }

    public static String getHouseQuery(int userId) throws SQLException {
        String getHouse = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 7 AND orgnID = " + getOrgnId() + " AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0))";
        return getHouse;
    }


    public static String getHouseSiteIDQuery(int AssetID) throws SQLException {
        String getHouseSiteID = "Select Top 1 House_internal_id from et.Asset_house_details where Asset_ID = '" + AssetID + "'";
        return getHouseSiteID;
    }


    public static int getHouseParentSiteId() throws SQLException {     //will return farm id of a farm having atleast 1 house
        ResultSet getParentSiteID = DB_Config_DB.getStmt().executeQuery(getHouseAssignedToUserQuery(getUsersId()));
        int parentSiteID = 0;
        while (getParentSiteID.next()) {
            parentSiteID = getParentSiteID.getInt("parentSiteId");
            System.out.println("Parent Site ID: " + parentSiteID);
        }
        return parentSiteID;
    }


    public static String getHouseNameAssignedToUser() throws SQLException {
        ResultSet getSiteName = DB_Config_DB.getStmt().executeQuery(getHouseAssignedToUserQuery(getUsersId()));
        String siteName = null;
        while (getSiteName.next()) {
            siteName = getSiteName.getString("siteName");
            System.out.println("Site Name: " + siteName);
        }
        return siteName;
    }


    public static int getHouseIDAssignedToUser() throws SQLException {
        ResultSet getSiteName = DB_Config_DB.getStmt().executeQuery(getHouseAssignedToUserQuery(getUsersId()));
        int siteId = 0;
        while (getSiteName.next()) {
            siteId = getSiteName.getInt("siteId");
            System.out.println("Site ID: " + siteId);
        }
        return siteId;
    }

    public static int getCreatedHouseUniqueSiteID(String houseName) throws SQLException {
        ResultSet getUniqueSiteId = DB_Config_DB.getStmt().executeQuery("Select siteUniqueNumber from Site where siteName = '" + houseName + "'");
        int siteId = 0;
        while (getUniqueSiteId.next()) {
            siteId = getUniqueSiteId.getInt("siteUniqueNumber");
            System.out.println("Site Unique Number: " + siteId);
        }
        return siteId;
    }


    public static String getHouseName() throws SQLException {
        ResultSet getSiteName = DB_Config_DB.getStmt().executeQuery(getHouseQuery(getUsersId()));
        String siteName = null;
        while (getSiteName.next()) {
            siteName = getSiteName.getString("siteName");
            System.out.println("House Name: " + siteName);
        }
        return siteName;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static String getSamplingPlanAttachedtodeployment(String uniqueAssetID, int deploymentID) {
        String getSamplingPlan = "SELECT sample_plan.* FROM dbo.Asset_MGMT\n" +
                "Asset_main JOIN dbo.Asset_HOUSE_DETAILS Asset_house\n" +
                "ON Asset_house.Asset_ID = Asset_main.ID\n" +
                "JOIN dbo.SAMPLING_PLAN_Asset_HOUSE sample_plan \n" +
                "ON sample_plan.Asset_HOUSE_DETAILS_ID = Asset_house.ID\n" +
                "WHERE Asset_main.UNIQUE_Asset_ID = '" + uniqueAssetID + "' and Asset_house_details_id = '" + deploymentID + "'";
        return getSamplingPlan;
    }


    public static String getProgramAttachedToAsset(String uniqueAssetID) {
        String getSamplingPlan = "Select Top 1 * from Asset_program_details where unique_Asset_id = '" + uniqueAssetID + "' order by id desc";
        return getSamplingPlan;
    }


    public static String getLastCreatedIntervention() {
        String getInterventionDisplayName = "select top 1 ENTITY_TYPE_DISPLAY " +
                "from ENTITY_TYPE ET " +
                "INNER JOIN ENTITY EN " +
                "ON ET.ENTITY_TYPE_ID = EN.ENTITY_TYPE_ID " +
                "order by ET.createdOn Desc";
        return getInterventionDisplayName;
    }


    public static void getSiteID() throws InterruptedException, IOException, SQLException {
        String query1 = "Select siteUniqueNumber from dbo.Site where siteName = 'TestHouse1_20220622' and isDeleted = 0";
        ResultSet rs1 = getStmt().executeQuery(query1);

        while (rs1.next()) {
            System.out.println("Site Unique Number: " + rs1.getString("siteUniqueNumber"));
            site = rs1.getString("siteUniqueNumber");
        }
        getStmt().close();
    }


    public static int getUsersId() throws SQLException {
        ResultSet getUserID = DB_Config_DB.getStmt().executeQuery(Queries.getUserIDQuery);
        int userID = 0;
        while (getUserID.next()) {
            userID = getUserID.getInt("userId");
            System.out.println("User ID: " + userID);
        }
        return userID;
    }


    public static int getOrgnId() throws SQLException {
        ResultSet getOrgnID = DB_Config_DB.getStmt().executeQuery(getHouseAssignedToUserQuery(getUsersId()));
        int orgnID = 0;
        while (getOrgnID.next()) {
            orgnID = getOrgnID.getInt("orgnID");
            System.out.println("Orgn ID: " + orgnID);
        }
        return orgnID;
    }


    public static String getSitesAssignedtoUserCount(int userId) throws SQLException {
        String getFarm = "DECLARE @userId BIGINT = " + userId + "\n" +
                "Select Count(distinct orgnid) as sitesassigned from site frm where  \n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)";
        return getFarm;
    }


    public static String getProcessingSiteIDQuery(int userId) throws SQLException {
        String getComplex = "DECLARE @userId BIGINT = " + userId + ";" +
                "SELECT * FROM Site frm where frm.sitetypeId = 8 AND frm.isActive=1 AND frm.isDeleted=0 AND\n" +
                "(frm.siteId IN (SELECT siteId from abcSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId FROM ClientSiteAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0) OR\n" +
                "frm.siteId IN (SELECT siteId from abcTestSitesAssn WHERE userId=@userId AND isActive=1 AND isDeleted=0)) ";
        return getComplex;
    }


    public static int getProcessingSiteIDAssignedToUser() throws SQLException {
        ResultSet getSiteID = DB_Config_DB.getStmt().executeQuery(getProcessingSiteIDQuery(getUsersId()));
        int siteID = 0;
        while (getSiteID.next()) {
            siteID = getSiteID.getInt("siteID");
            System.out.println("Processing Site ID: " + siteID);
        }
        return siteID;
    }


    public static int getHouseSiteIDAssignedToUser(int AssetID) throws SQLException {
        ResultSet getSiteID = DB_Config_DB.getStmt().executeQuery("Select Top 1 House_internal_id from et.Asset_house_details where Asset_ID = '" + AssetID + "'");
        int siteID = 0;
        while (getSiteID.next()) {
            siteID = getSiteID.getInt("House_internal_id");
            System.out.println("House Site ID: " + siteID);
        }
        return siteID;
    }


//	public static int sampleID() throws SQLException {
//		ResultSet getSiteID = DB_Config_DB.getStmt().executeQuery("Select TOP 1 * from Patho_1_DATA where sample_ID = '" +  + "'");
//		int siteID = 0;
//		while (getSiteID.next()) {
//			siteID = getSiteID.getInt("House_internal_id");
//			System.out.println("House Site ID: " + siteID);
//		}
//		return siteID;
//	}


    public static String getProgramIDQuery() throws SQLException {
        String getProgram = "Select Top 1 * from program where endDate is Null and siteId = (Select Top 1 siteID from Site where orgnId= '" + getOrgnId() + "' and sitetypeId = '5') and isActive = '1' and isDeleted = '0' order by programId desc";
//		String getProgram = "Select Top 1 * from program where siteId = (Select Top 1 siteID from Site where orgnId = '"+getOrgnId()+"' and sitetypeId = '5') and enddate is Null OR enddate > '2023-01-01 12:00:00' and isActive = '1' and isDeleted = '0' order by programId desc ";
        return getProgram;
    }


    public static String getProgramIDQuery2() throws SQLException {
        String getProgram = "Select Top 1 * from program where siteId = (Select Top 1 siteID from Site where orgnId= '" + getOrgnId() + "' and sitetypeId = '5') and isActive = '1' and isDeleted = '0' order by programId asc";
        return getProgram;
    }


    public static int getProgramID() throws SQLException {
        ResultSet getProgramID = DB_Config_DB.getStmt().executeQuery(getProgramIDQuery());
        int programID = 0;
        while (getProgramID.next()) {
            programID = getProgramID.getInt("programId");
            System.out.println("Program ID: " + programID);
        }
        return programID;
    }


    public static int getProgramID2() throws SQLException {
        ResultSet getProgramID = DB_Config_DB.getStmt().executeQuery(getProgramIDQuery2());
        int programID = 0;
        while (getProgramID.next()) {
            programID = getProgramID.getInt("programId");
            System.out.println("Program ID: " + programID);
        }
        return programID;
    }


    public static String getProgramName() throws SQLException {
        ResultSet getProgramName = DB_Config_DB.getStmt().executeQuery(getProgramIDQuery());
        String programName = null;
        while (getProgramName.next()) {
            programName = getProgramName.getString("programName");
            System.out.println("Program Name: " + programName);
        }
        return programName;
    }


    public static String getProgramDisplayName() throws SQLException {
        ResultSet getProgramName = DB_Config_DB.getStmt().executeQuery(getProgramIDQuery());
        String programName = null;
        while (getProgramName.next()) {
            programName = getProgramName.getString("programName");
            System.out.println("Program Display Name: " + programName);
        }
        return programName;
    }


    public static int getProgramTypeID() throws SQLException {
        ResultSet getProgramID = DB_Config_DB.getStmt().executeQuery(getProgramIDQuery());
        int programId = 0;
        while (getProgramID.next()) {
            programId = getProgramID.getInt("programTypeId");
            System.out.println("Program Type ID: " + programId);
        }
        return programId;
    }


    public static String getSamplingPlanWithNullEndDateQuery() throws SQLException {
        String getPlan = "Select Top 1 * from SamplingPlan where endDate is Null and complexID = (SELECT Top 1 siteID FROM Site frm where frm.sitetypeId = 5 and orgniD = '" + getOrgnId() + "') and isActive = '1'  and isDeleted = '0'";
        return getPlan;
    }


    public static String getSamplingPlanQuery() throws SQLException {
        String getPlan = "Select Top 1 * from SamplingPlan where endDate is Null and complexID = (SELECT Top 1 siteID FROM Site frm where frm.sitetypeId = 5 and orgniD = '" + getOrgnId() + "') and isActive = '1'  and isDeleted = '0' and isMigrated is Null";
        return getPlan;
    }


    public static String getSamplingPlanWithComplexNotSameAsAssetQuery() throws SQLException {   //returns a sampling plan to check vaidation that returns error if samplingPlanIdComplexLevel is not at same Complex as Asset
        String getPlan = "Select Top 1 * from SamplingPlan where endDate is Not Null and complexID != (SELECT Top 1 siteID FROM Site frm where frm.sitetypeId = 5 and orgniD = '" + getOrgnId() + "') and isActive = '1'  and isDeleted = '0' order by samplingPlanID desc";
        return getPlan;
    }

    public static String getSamplingPlanCountQuery() throws SQLException {
        String getPlan = "Select count(samplingPlanID) as count from SamplingPlan where endDate is Null and complexID = (SELECT Top 1 siteID FROM Site frm where frm.sitetypeId = 5 and orgniD = '" + getOrgnId() + "') and isActive = '1'  and isDeleted = '0' and isMigrated is Null";
        return getPlan;
    }


//	public static String getSamplingPlanWithEndDateNotNullQuery() throws SQLException {   //
//		String getPlan = "Select * from SamplingPlan where endDate is Not Null and complexID != (SELECT Top 1 siteID FROM Site frm where frm.sitetypeId = 5 and orgnId = '"+getOrgnId()+"') and isActive = '1'  and isDeleted = '0' order by samplingPlanID desc";
//		return getPlan;
//	}


    public static int getSamplingPlanID() throws SQLException {
        ResultSet getsamplingPlanID = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
        int planID = 0;
        while (getsamplingPlanID.next()) {
            planID = getsamplingPlanID.getInt("samplingPlanID");
            System.out.println("Plan ID: " + planID);
        }
        return planID;
    }


    public static int getSamplingPlanIDWithNullEndDate() throws SQLException {
        ResultSet getsamplingPlanID = DB_Config_DB.getStmt().executeQuery(getSamplingPlanWithNullEndDateQuery());
        int planID = 0;
        while (getsamplingPlanID.next()) {
            planID = getsamplingPlanID.getInt("samplingPlanID");
            System.out.println("Plan ID: " + planID);
        }
        return planID;
    }


    public static int getSamplingPlanWithComplexNotSameAsAsset() throws SQLException {
        ResultSet getsamplingPlanID = DB_Config_DB.getStmt().executeQuery(getSamplingPlanWithComplexNotSameAsAssetQuery());
        int planID = 0;
        while (getsamplingPlanID.next()) {
            planID = getsamplingPlanID.getInt("samplingPlanID");
            System.out.println("Plan ID: " + planID);
        }
        return planID;
    }


    public static String getSamplingPlanName() throws SQLException {
        ResultSet getsamplingPlanID = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
        String planID = null;
        while (getsamplingPlanID.next()) {
            planID = getsamplingPlanID.getString("samplingPlanName");
            System.out.println("Plan Name: " + planID);
        }
        return planID;
    }


    public static String getSamplingPlanStartDate() throws SQLException {
        ResultSet getsamplingStartDate = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
        String startDate = null;
        while (getsamplingStartDate.next()) {
            startDate = getsamplingStartDate.getString("startDate");
            System.out.println("Start Date: " + startDate);
        }
        return startDate;
    }


    public static String getSamplingPlanEndDate() throws SQLException {
        ResultSet getsamplingEndDate = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
        String endDate = null;
        while (getsamplingEndDate.next()) {
            endDate = getsamplingEndDate.getString("endDate");
            System.out.println("End Date: " + endDate);
        }
        return endDate;
    }

    public static int getSamplingPlansCountAssignedToUser() throws SQLException {
        ResultSet getCount = DB_Config_DB.getStmt().executeQuery(getSamplingPlanQuery());
        int count = 0;
        while (getCount.next()) {
            count = getCount.getInt("count");
            System.out.println("Count: " + count);
        }
        return count;
    }


    public static String getActivePlansQuery(String endDate) throws SQLException {
        String getActivePlans = "Select * from samplingplan where complexId = " + getComplexSiteID() + " and (enddate > '" + endDate + "' or endDate Is NULL)";
        return getActivePlans;
    }


    public static String getAssetdeploymentIDQuery() throws SQLException {
        String getdeploymentID = "Select Top 1 id from Asset_house_details order by id desc";
        return getdeploymentID;
    }

    public static String getAssetIDQuery() throws SQLException {
        String getAssetID = "SELECT TOP 2 UNIQUE_Asset_ID\n" +
                "FROM (\n" +
                "    SELECT DISTINCT UNIQUE_Asset_ID\n" +
                "    FROM Asset_MGMT_AUDIT\n" +
                "    WHERE changed_by = 'JAlam'\n" +
                ") AS subquery;";
        return getAssetID;
    }


    public static int getAssetdeploymentID() throws SQLException {
        ResultSet getdeploymentID = DB_Config_DB.getStmt().executeQuery(getAssetdeploymentIDQuery());
        int pleacementID = 0;
        while (getdeploymentID.next()) {
            pleacementID = getdeploymentID.getInt("id");
            System.out.println("deployment ID: " + pleacementID);
        }
        return pleacementID;
    }


    public static String feedProgramLogView(String programName) {
        String feedProgramLogViewQuery = "SELECT P.programName AS [Program Name], STRING_AGG(FLP.[name], ', ') AS [Feed Type],\n" +
                "FORMAT(CAST(P.startDate as Date),'MM/dd/yyyy') AS [Start Date], ISNULL(FORMAT(CAST(P.endDate as date),'MM/dd/yyyy'),'') AS [End Date], \n" +
                "ST.siteName AS Complex, TLP.[name] AS [Target Pathogen], TP.tradeProductName AS [Trade Name], P.programDisplayName AS [Program Display Name]\n" +
                "FROM [Program] P\n" +
                "INNER JOIN ProgramFeed PF ON p.programId = PF.programId\n" +
                "INNER JOIN [dbo].[LookUp] FLP ON PF.feedTypeId = FLP.id\n" +
                "INNER JOIN dbo.[Site] ST ON P.siteId = ST.siteId\n" +
                "INNER JOIN [dbo].[LookUp] TLP ON TLP.id = (Select id from LookUp where name = 'Patho_1' and details = 'Program Target Pathogen')\n" +
                "INNER JOIN ProgramFeedProduct PFP ON PFP.programFeedId = PF.programFeedId\n" +
                "INNER JOIN TradeProduct TP ON TP.tradeProductId = PFP.tradeProductId\n" +
                "WHERE P.programDisplayName = '" + programName + "'\n" +
                "GROUP BY P.programName, p.[description], P.startDate, P.endDate, ST.siteName, TLP.[name], P.programDisplayName, TP.tradeProductName";
        return feedProgramLogViewQuery;
    }


    public static String vaccineProgramLogView(String programName) {
        String vaccineProgramLogViewQuery = "SELECT PR.programName AS [Program Name], TP.name AS [Target Pathogen],\n" +
                "FORMAT(CAST(PR.startDate as date),'MM/dd/yyyy') AS [Start Date], FORMAT(CAST(PR.endDate as date),'MM/dd/yyyy') AS [End Date], \n" +
                "S.siteName AS Complex, TRP.tradeProductName AS [Trade Name], PR.programDisplayName AS [Program Display Name]\n" +
                "FROM ProgramVaccine PV\n" +
                "INNER JOIN program PR ON PR.programId = PV.programId         \n" +
                "LEFT JOIN Site S ON PR.siteId = S.siteId\n" +
                "INNER JOIN dbo.LookUp TP ON TP.id = (Select id from LookUp where name = 'Patho_1' and details = 'Program Target Pathogen') AND TP.isActive = 1 AND TP.isDeleted= 0\n" +
                "INNER JOIN ProgramVaccineProduct PVP ON PVP.programVaccineId = PV.programVaccineId\n" +
                "INNER JOIN TradeProduct TRP ON TRP.tradeProductId = PVP.tradeProductId\n" +
                "WHERE PR.programDisplayName = '" + programName + "'\n" +
                "GROUP BY PR.programName, PV.noOfApplications, PR.[description], PR.startDate, PR.endDate, S.siteName, TP.name, TRP.tradeProductName, PR.programDisplayName";
        return vaccineProgramLogViewQuery;
    }


    public static String bioshuttleProgramLogView(String programName) {
        String bioshuttleProgramLogViewQuery = "SELECT PR.programName AS [Program Name], PLP.[name] AS [Program Type], TLP.[name] AS [Target Pathogen], S.siteName AS [Complex],\n" +
                "FORMAT(CAST(PR.startDate as date),'MM/dd/yyyy') AS [Start Date], ISNULL(FORMAT(CAST(PR.endDate as date),'MM/dd/yyyy'),'') AS [End Date], \n" +
                "STRING_AGG(FLP.[name], ', ') AS [Feed Type], ISNULL(STRING_AGG(FTCLP.name, ', '),'') AS [Feed Type Categories], TRP.tradeProductName AS [Vaccine Trade Name], \n" +
                "TP.tradeProductName AS [Feed Trade Name], PR.programDisplayName AS [Program Display Name]\n" +
                "from Program PR\n" +
                "INNER JOIN dbo.LookUp TLP ON TLP.id = PR.targetPathogenId\n" +
                "INNER JOIN dbo.LookUp PLP ON PLP.id = PR.programTypeId\n" +
                "INNER JOIN dbo.Site S ON S.siteId = PR.siteId\n" +
                "INNER JOIN ProgramVaccine PV ON PV.programId = PR.programId\n" +
                "INNER JOIN ProgramFeed PF ON PF.programId = PR.programId\n" +
                "INNER JOIN dbo.LookUp FLP ON FLP.id = PF.feedTypeId AND PF.isActive = 1 AND PF.isDeleted = 0\n" +
                "LEFT JOIN dbo.LookUp FTCLP on FTCLP.id = PF.feedCategoryId\n" +
                "INNER JOIN ProgramVaccineProduct PVP ON PVP.programVaccineId = PV.programVaccineId\n" +
                "INNER JOIN TradeProduct TRP ON TRP.tradeProductId = PVP.tradeProductId\n" +
                "INNER JOIN ProgramFeedProduct PFP ON PFP.programFeedId = PF.programFeedId\n" +
                "INNER JOIN TradeProduct TP ON TP.tradeProductId = PFP.tradeProductId\n" +
                "WHERE PR.programDisplayName = '" + programName + "' AND PR.isActive = 1 AND PR.isDeleted = 0\n" +
                "GROUP BY PR.programName, TLP.name, PLP.name, S.siteName, PR.startDate, PR.endDate, TRP.tradeProductName, TP.tradeProductName, PR.programDisplayName";
        return bioshuttleProgramLogViewQuery;
    }
}


