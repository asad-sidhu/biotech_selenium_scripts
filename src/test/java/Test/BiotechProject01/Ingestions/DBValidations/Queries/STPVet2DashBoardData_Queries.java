package Test.BiotechProject01.Ingestions.DBValidations.Queries;

public class STPVet2DashBoardData_Queries {

    public static String oldViewName = "stpVet2DashBoardData";
    public static String newViewName = "stpVet2DashBoardData_NEW_TEMP";


    public static String getvet2DashboardDataQuery(String viewName) {
        String vet2DashboardDataQuery = "declare @p1 UserSites\n" +
                "INSERT INTO @p1\n" +
                "SELECT siteId, NULL\t, 0, 0 FROM ET.Site s WHERE s.isActive = 1 AND s.isDeleted = 0\n" +
                "EXEC "+viewName+" @P_USER_SITES=@p1,@INFO_LEVEL=N'ALL'";
        return vet2DashboardDataQuery;
    }


}
