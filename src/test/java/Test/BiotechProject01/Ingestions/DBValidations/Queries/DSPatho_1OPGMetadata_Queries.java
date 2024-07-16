package Test.BiotechProject01.Ingestions.DBValidations.Queries;

public class DSPatho_1OPGMetadata_Queries {

    public static String oldViewName = "DS_Patho_1_OPG_METADATA_07_06_2024_temp";
    public static String newViewName = "DS_Patho_1_OPG_METADATA_temp";

    public static String getAllRowsCountQuery(String viewName) {
        String allRowsCountQuery = "Select count(T_Run_ID) as count from " + viewName;
        return allRowsCountQuery;
    }

//    public static String getAllDataQuery(String viewName) {
//        String allDataQuery = "SELECT \n" +
//                "T_RUN_ID,SAMPLE_ID,SAMPLE_MATRIX,TOTAL_OPG,TOTAL_SMALL_OPG,TOTAL_MEDIUM_OPG,TOTAL_LARGE_OPG,SCANDATETIME,INSTRUMENT_ID,CARTRIDGEID,COLLECTION_SITE_ID,HOUSE_ID,HOUSE,FARM_ID,FARM,COMPLEX_ID,COMPLEX,SUBREGION_ID,SUBREGION,REGION_ID,REGION,INTEGRATOR_ID,INTEGRATOR,COLLECTION_DATE,COUNT_OUTCOME,deployment_DATE,PROCESSING_DATE,VACCINE,VACCINE_START_DATE,VACCINE_END_DATE,FEED_PROGRAM,FEED_START_DATE,FEED_END_DATE,BIO_SHUTTLE,BIO_SHUTTLE_START_DATE,BIO_SHUTTLE_END_DATE,INTERVENTION_BIO_SHUTTLE_ID,Asset_DAY,REAL_deployment_DATE,INTERVENTION_VACCINE_ID,INTERVENTION_FEED_PROGRAM_ID,TEST_SITE_NAME,TEST_SITE_ID,METADATA_DATE_RECEIVED,RUN_DATE,METADATA_COMPLETE_DATE,METADATA_UPDATE_DATE,animal_SIZE,UNIQUE_Asset_ID\n" +
//                "FROM "+viewName;
//        return allDataQuery;
//    }

}
