package Test.BiotechProject01.Ingestions.DBValidations.Queries;

public class DSPatho_1Asset_Queries {

    public static String oldViewName = "DS_Patho_1_OPG_METADATA_OLD";
    public static String newViewName = "DS_Patho_1_OPG_METADATA";


    public static String getAllRowsCountQuery(String viewName) {
        String allRowsCountQuery = "Select count(T_Run_ID) as count from " + viewName;
        return allRowsCountQuery;
    }


//    public static String getAllDataQuery(String viewName) {
//        String allDataQuery = "Select T_RUN_ID,SAMPLE_ID,SAMPLE_MATRIX,TOTAL_OPG,TOTAL_SMALL_OPG,TOTAL_MEDIUM_OPG,TOTAL_LARGE_OPG,SCANDATETIME,INSTRUMENT_ID,CARTRIDGEID,COLLECTION_SITE_ID,HOUSE_ID,HOUSE,FARM_ID,FARM,COMPLEX_ID,COMPLEX,SUBREGION_ID,SUBREGION,REGION_ID,REGION,INTEGRATOR_ID,INTEGRATOR,COLLECTION_DATE,COUNT_OUTCOME,deployment_DATE,Asset_DAY,REAL_deployment_DATE,TEST_SITE_NAME,TEST_SITE_ID,METADATA_DATE_RECEIVED,RUN_DATE,METADATA_COMPLETE_DATE,METADATA_UPDATE_DATE,SAMPLE_INTERVAL,TOTAL_OPG_IN_RANGE,TOTAL_LARGE_OPG_IN_RANGE,TOTAL_animalS_PLACED,UNIQUE_Asset_ID,crop_DATE,SITE_ID,TOTAL_WEIGHT_CONDEMNED_LB,TOTAL_WEIGHT_CONDEMNED_KG,NUM_animalS_CONDEMNED_WHOLE,PARTS_WEIGHT_CONDEMNED_LB,PARTS_WEIGHT_CONDEMNED_KG,kCAL_PER_POUND,A_GRADE_PAWS_PERC,AIRSAC_PERCENTAGE,IP_PERCENTAGE,LEUKOSIS_PERCENTAGE,SEPTOX_PERCENTAGE,TUMOR_PERCENTAGE,Asset_ID,NUM_animalS_DOA_PLANT,WEEK1_MORTALITY,WEEK2_MORTALITY,WEEK3_MORTALITY,WEEK4_MORTALITY,WEEK5_MORTALITY,WEEK6_MORTALITY,WEEK7_MORTALITY,WEEK8_MORTALITY,WEEK9_MORTALITY,WEEKLY_FARM_RANK,HISTORICAL_FARM_COST_VARIANCE,WEEKLY_FARM_COST_VARIANCE,DAYS_OUT,AGE_OF_LITTER,AVG_SOLD_AGE,NUM_animalS_SOLD,deployment_DENSITY,PROCESSING_DATE,PROCESSING_SITE_ID,USDA_PLANT_ID,PLANT_LOCATION,NUM_animalS_PROCESSED,AVG_animal_WEIGHT_LBS,AVG_animal_WEIGHT_KGS,AVG_DAILY_WEIGHT_GAIN_LB,AVG_DAILY_WEIGHT_GAIN_KG,TOTAL_WEIGHT_PROCESSED_LB,TOTAL_WEIGHT_PROCESSED_KG,TOTAL_FEED_WEIGHT_LB,TOTAL_FEED_WEIGHT_KG,FCR,FCR_ADJ,FEED_COST_PER_LIVE_POUND,MEDICATION_COST_PER_LIVE_POUND,GROWER_COST_PER_LIVE_POUND,LIVABILITY_PERCENTAGE,OVERALL_MORTALITY_PERCENTAGE,HATCH_DATE,VACCINE,VACCINE_START_DATE,VACCINE_END_DATE,FEED_PROGRAM,FEED_START_DATE,FEED_END_DATE,BIO_SHUTTLE,BIO_SHUTTLE_START_DATE,BIO_SHUTTLE_END_DATE,INTERVENTION_BIO_SHUTTLE_ID,INTERVENTION_VACCINE_ID,INTERVENTION_FEED_PROGRAM_ID from " + viewName;
//        return allDataQuery;
//    }

    public static String getAllDataQuery(String viewName) {
        String allDataQuery = "Select T_RUN_ID,SAMPLE_ID,SAMPLE_MATRIX,TOTAL_OPG,TOTAL_SMALL_OPG,TOTAL_MEDIUM_OPG,TOTAL_LARGE_OPG,SCANDATETIME,INSTRUMENT_ID,CARTRIDGEID,COLLECTION_SITE_ID,HOUSE_ID,HOUSE,FARM_ID,FARM,COMPLEX_ID,COMPLEX,SUBREGION_ID,SUBREGION,REGION_ID,REGION,INTEGRATOR_ID,INTEGRATOR,COLLECTION_DATE,COUNT_OUTCOME,deployment_DATE,Asset_DAY,REAL_deployment_DATE,TEST_SITE_NAME,TEST_SITE_ID,METADATA_DATE_RECEIVED,RUN_DATE,METADATA_COMPLETE_DATE,METADATA_UPDATE_DATE,SAMPLE_INTERVAL,TOTAL_OPG_IN_RANGE,TOTAL_LARGE_OPG_IN_RANGE,TOTAL_animalS_PLACED,UNIQUE_Asset_ID,crop_DATE,SITE_ID,TOTAL_WEIGHT_CONDEMNED_LB,TOTAL_WEIGHT_CONDEMNED_KG,NUM_animalS_CONDEMNED_WHOLE,PARTS_WEIGHT_CONDEMNED_LB,PARTS_WEIGHT_CONDEMNED_KG,kCAL_PER_POUND,A_GRADE_PAWS_PERC,AIRSAC_PERCENTAGE,IP_PERCENTAGE,LEUKOSIS_PERCENTAGE,SEPTOX_PERCENTAGE,TUMOR_PERCENTAGE,Asset_ID,NUM_animalS_DOA_PLANT,WEEK1_MORTALITY,WEEK2_MORTALITY,WEEK3_MORTALITY,WEEK4_MORTALITY,WEEK5_MORTALITY,WEEK6_MORTALITY,WEEK7_MORTALITY,WEEK8_MORTALITY,WEEK9_MORTALITY,WEEKLY_FARM_RANK,HISTORICAL_FARM_COST_VARIANCE,WEEKLY_FARM_COST_VARIANCE,DAYS_OUT,AGE_OF_LITTER,AVG_SOLD_AGE,NUM_animalS_SOLD,deployment_DENSITY,PROCESSING_DATE,PROCESSING_SITE_ID,USDA_PLANT_ID,PLANT_LOCATION,NUM_animalS_PROCESSED,AVG_animal_WEIGHT_LBS,AVG_animal_WEIGHT_KGS,AVG_DAILY_WEIGHT_GAIN_LB,AVG_DAILY_WEIGHT_GAIN_KG,TOTAL_WEIGHT_PROCESSED_LB,TOTAL_WEIGHT_PROCESSED_KG,TOTAL_FEED_WEIGHT_LB,TOTAL_FEED_WEIGHT_KG,FCR,FCR_ADJ,FEED_COST_PER_LIVE_POUND,MEDICATION_COST_PER_LIVE_POUND,GROWER_COST_PER_LIVE_POUND,LIVABILITY_PERCENTAGE,OVERALL_MORTALITY_PERCENTAGE,HATCH_DATE,VACCINE,VACCINE_START_DATE,VACCINE_END_DATE,FEED_PROGRAM,FEED_START_DATE,FEED_END_DATE,BIO_SHUTTLE,BIO_SHUTTLE_START_DATE,BIO_SHUTTLE_END_DATE,INTERVENTION_BIO_SHUTTLE_ID,INTERVENTION_VACCINE_ID,INTERVENTION_FEED_PROGRAM_ID from " + viewName;
        return allDataQuery;
    }

    public static String getSamplingPlanQuery(String viewName) {
        String samplingPlanQuery = " with CTEAsset as (select\n" +
                "    fm.ID\n" +
                "   ,fm.FARM_INTERNAL_ID\n" +
                "   ,fm.animal_SIZE\n" +
                "   ,fm.animal_SIZE_ID\n" +
                "   ,fhd.HOUSE_INTERNAL_ID\n" +
                "   ,House.siteUniqueNumber houseUniqueNumber\n" +
                "   ,Farm.siteUniqueNumber farmUniqueNumber\n" +
                "   ,FS.PROCESSING_DATE\n" +
                "   ,fhd.deployment_DATE\n" +
                "   ,fhd.crop_DATE\n" +
                " from Asset_MGMT fm\n" +
                " inner join ET.[Site] Farm\n" +
                "    on fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "    and Farm.isActive = 1\n" +
                "    and Farm.isDeleted = 0\n" +
                " inner join Asset_HOUSE_DETAILS fhd\n" +
                "    on fm.ID = fhd.Asset_ID\n" +
                "    and fhd.isActive = 1\n" +
                "    and fhd.isDeleted = 0\n" +
                " inner join ET.[Site] House\n" +
                "    on fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "    and House.isActive = 1\n" +
                "    and House.isDeleted = 0\n" +
                " left join Asset_SETTLEMENT FS on FS.Asset_ID = fm.ID and FS.IS_ACTIVE = 1 and FS.Is_DELETED = 0\n" +
                " where fm.animal_SIZE_ID is not null\n" +
                ")\n" +
                " , CTECycling\n" +
                " as (select DSCC.CYCLING_INTERVAL_NAME cyclingintervalName\n" +
                "         , DSCC.TARGET_AGE_RANGE_MIN  startDay\n" +
                "         , DSCC.TARGET_AGE_RANGE_MAX  EndDay\n" +
                "         , Complex.siteUniqueNumber   INTERNAL_SITE_ID\n" +
                "         , DSCC.Asset_ID\n" +
                "    from DS_COMPLEX_CYCLING_CONFIG DSCC /*SELECT * FROM AssetCYCLINGINTERVAL*/\n" +
                "    inner join ET.[Site] Complex\n" +
                "        on DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "        and Complex.isActive = 1\n" +
                "        and Complex.isDeleted = 0)\n" +
                " select\n" +
                "    cd.T_RUN_ID\n" +
                "   ,cd.COLLECTION_DATE\n" +
                "   ,cd.HOUSE_ID\n" +
                "   ,cd.COLLECTION_SITE_ID\n" +
                "   ,cd.COMPLEX_ID\n" +
                "   ,cd.FARM_ID\n" +
                " from " + viewName + " cd\n" +
                " where (cd.COUNT_OUTCOME is null\n" +
                "    or cd.COUNT_OUTCOME = 'Completed')\n" +
                "    and cd.Asset_DAY is null\n" +
                "    and exists (select\n" +
                "        1\n" +
                "        from CTEAsset flk\n" +
                "        WHERE cd.COLLECTION_DATE > flk.deployment_DATE\n" +
                "        AND cd.COLLECTION_DATE <= COALESCE(flk.PROCESSING_DATE, flk.crop_DATE, DATEADD(DAY, 63, flk.deployment_DATE))\n" +
                "        AND DATEDIFF(DAY, flk.deployment_DATE, cd.COLLECTION_DATE) <= DATEDIFF(DAY, flk.deployment_DATE, COALESCE(flk.PROCESSING_DATE, flk.crop_DATE, DATEADD(DAY, 63, flk.deployment_DATE)))\n" +
                "        AND cd.HOUSE_ID = houseUniqueNumber\n" +
                "        AND cd.FARM_ID = FarmUniqueNumber)\n" +
                "    AND NOT EXISTS (SELECT\n" +
                "        *\n" +
                "        FROM CTECycling DSCC\n" +
                "        INNER JOIN CTEAsset F\n" +
                "            ON  DSCC.Asset_ID = F.ID --AND DSCC.INTERNAL_SITE_ID=F.FARM_INTERNAL_ID\n" +
                "        WHERE DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "        AND cd.HOUSE_ID = F.houseUniqueNumber)";

        return samplingPlanQuery;
    }



    public static String getOutofIntervalRangeQuery(String viewName) {
        String outOfIntervalRangeQuery = "with CTEAsset\n" +
                "as (select fm.ID\n" +
                "         , fm.FARM_INTERNAL_ID\n" +
                "         , fm.animal_SIZE\n" +
                "         , fm.animal_SIZE_ID\n" +
                "         , fhd.HOUSE_INTERNAL_ID\n" +
                "         , House.siteUniqueNumber houseUniqueNumber\n" +
                "         , Farm.siteUniqueNumber  farmUniqueNumber\n" +
                "         , FS.PROCESSING_DATE\n" +
                "         , fhd.deployment_DATE\n" +
                "         , fhd.crop_DATE\n" +
                "    from Asset_MGMT                    fm\n" +
                "        inner join ET.[Site]           Farm\n" +
                "            on fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "               and Farm.isActive = 1\n" +
                "               and Farm.isDeleted = 0\n" +
                "        inner join Asset_HOUSE_DETAILS fhd\n" +
                "            on fm.ID = fhd.Asset_ID\n" +
                "               and fhd.isActive = 1\n" +
                "               and fhd.isDeleted = 0\n" +
                "        inner join ET.[Site]           House\n" +
                "            on fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "               and House.isActive = 1\n" +
                "               and House.isDeleted = 0\n" +
                "        left join Asset_SETTLEMENT     FS\n" +
                "            on FS.Asset_ID = fm.ID\n" +
                "    where fm.animal_SIZE_ID is not null)\n" +
                "   , CTECycling\n" +
                "as (select DSCC.CYCLING_INTERVAL_NAME cyclingintervalName\n" +
                "         , DSCC.TARGET_AGE_RANGE_MIN  startDay\n" +
                "         , DSCC.TARGET_AGE_RANGE_MAX  EndDay\n" +
                "         , Complex.siteUniqueNumber   INTERNAL_SITE_ID\n" +
                "         , DSCC.Asset_ID\n" +
                "    from DS_COMPLEX_CYCLING_CONFIG DSCC /*SELECT * FROM AssetCYCLINGINTERVAL*/\n" +
                "        inner join ET.[Site]       Complex\n" +
                "            on DSCC.INTERNAL_SITE_ID = Complex.siteId\n" +
                "               and Complex.isActive = 1\n" +
                "               and Complex.isDeleted = 0)\n" +
                "select cd.T_RUN_ID\n" +
                "     , cd.COLLECTION_DATE\n" +
                "     , cd.HOUSE_ID\n" +
                "     , cd.COLLECTION_SITE_ID\n" +
                "     , cd.COMPLEX_ID\n" +
                "     , cd.FARM_ID\n" +
                "from " + viewName + " cd\n" +
                "where (\n" +
                "          cd.COUNT_OUTCOME is null\n" +
                "          or cd.COUNT_OUTCOME = 'Completed'\n" +
                "      )\n" +
                "      and cd.Asset_DAY is null\n" +
                "      and exists\n" +
                "(\n" +
                "    select 1\n" +
                "    from CTEAsset flk\n" +
                "    where cd.COLLECTION_DATE > flk.deployment_DATE\n" +
                "          and cd.COLLECTION_DATE <= coalesce(\n" +
                "                                                flk.PROCESSING_DATE\n" +
                "                                              , flk.crop_DATE\n" +
                "                                              , dateadd(day, 63, flk.deployment_DATE)\n" +
                "                                            )\n" +
                "          and datediff(day, flk.deployment_DATE, cd.COLLECTION_DATE)  <= datediff(\n" +
                "                                                                                       day\n" +
                "                                                                                     , flk.deployment_DATE\n" +
                "                                                                                     , coalesce(\n" +
                "                                                                                                   flk.PROCESSING_DATE\n" +
                "                                                                                                 , flk.crop_DATE\n" +
                "                                                                                                 , dateadd(\n" +
                "                                                                                                              day\n" +
                "                                                                                                            , 63\n" +
                "                                                                                                            , flk.deployment_DATE\n" +
                "                                                                                                          )\n" +
                "                                                                                               )\n" +
                "                                                                                   ) \n" +
                "          and cd.HOUSE_ID = houseUniqueNumber\n" +
                "          and cd.FARM_ID = farmUniqueNumber\n" +
                ")\n" +
                "      and exists\n" +
                "(\n" +
                "    select 1\n" +
                "    from CTECycling         DSCC\n" +
                "        inner join CTEAsset F\n" +
                "            on --ON F.animal_SIZE = DSCC.animal_SIZE\n" +
                "            DSCC.Asset_ID = F.ID --AND DSCC.INTERNAL_SITE_ID=F.FARM_INTERNAL_ID\n" +
                "    where DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "          and cd.HOUSE_ID = F.houseUniqueNumber\n" +
                ")\n" +
                "      and not exists\n" +
                "(\n" +
                "    select *\n" +
                "    from CTECycling         DSCC\n" +
                "        inner join CTEAsset F\n" +
                "            on DSCC.Asset_ID = F.ID\n" +
                "    where DSCC.INTERNAL_SITE_ID = cd.COMPLEX_ID\n" +
                "          and cd.HOUSE_ID = F.houseUniqueNumber\n" +
                "          and datediff(day, F.deployment_DATE, cd.COLLECTION_DATE)  <= datediff(\n" +
                "                                                                                     day\n" +
                "                                                                                   , F.deployment_DATE\n" +
                "                                                                                   , coalesce(\n" +
                "                                                                                                 F.PROCESSING_DATE\n" +
                "                                                                                               , F.crop_DATE\n" +
                "                                                                                               , dateadd(\n" +
                "                                                                                                            day\n" +
                "                                                                                                          , 63\n" +
                "                                                                                                          , F.deployment_DATE\n" +
                "                                                                                                        )\n" +
                "                                                                                             )\n" +
                "                                                                                 ) \n" +
                "          and datediff(day, F.deployment_DATE, cd.COLLECTION_DATE) \n" +
                "          between DSCC.startDay and isnull(\n" +
                "                                              DSCC.EndDay\n" +
                "                                            , datediff(\n" +
                "                                                          day\n" +
                "                                                        , F.deployment_DATE\n" +
                "                                                        , coalesce(\n" +
                "                                                                      F.PROCESSING_DATE\n" +
                "                                                                    , F.crop_DATE\n" +
                "                                                                    , dateadd(day, 63, F.deployment_DATE)\n" +
                "                                                                  )\n" +
                "                                                      ) \n" +
                "                                          )\n" +
                ")\n";
        return outOfIntervalRangeQuery;
    }


    public static String getNoAssetAssociationQuery(String viewName) {
        String noAssetAssociationQuery = "SELECT\n" +
                "\tcd.T_RUN_ID\n" +
                "   ,cd.COLLECTION_DATE\n" +
                "   ,cd.HOUSE_ID\n" +
                "   ,cd.COLLECTION_SITE_ID\n" +
                "   ,cd.COMPLEX_ID\n" +
                "   ,cd.FARM_ID\n" +
                "from " + viewName + " cd\n" +
                "where (cd.COUNT_OUTCOME is null\n" +
                "or cd.COUNT_OUTCOME = 'Completed')\n" +
                "and cd.Asset_DAY is null\n" +
                "and not exists (select\n" +
                "\t\t1\n" +
                "\tfrom Asset_MGMT fm\n" +
                "\tinner join ET.[Site] Farm\n" +
                "\t\ton fm.FARM_INTERNAL_ID = Farm.siteId\n" +
                "\t\tand Farm.isActive = 1\n" +
                "\t\tand Farm.isDeleted = 0\n" +
                "\tinner join Asset_HOUSE_DETAILS fhd\n" +
                "\t\ton fm.ID = fhd.Asset_ID\n" +
                "\t\tand fhd.isActive = 1\n" +
                "\t\tand fhd.isDeleted = 0\n" +
                "\tinner join ET.[Site] House\n" +
                "\t\ton fhd.HOUSE_INTERNAL_ID = House.siteId\n" +
                "\t\tand House.isActive = 1\n" +
                "\t\tand House.isDeleted = 0\n" +
                "\tleft join Asset_SETTLEMENT\tFS on FS.Asset_ID=fm.ID and FS.IS_ACTIVE=1 and FS.Is_DELETED=0\n" +
                "\twhere fm.animal_SIZE_ID is not null\n" +
                "\t\n" +
                "\tAND cd.COLLECTION_DATE > fhd.deployment_DATE\n" +
                "\tAND cd.COLLECTION_DATE <= COALESCE(FS.PROCESSING_DATE ,fhd.crop_DATE, DATEADD(DAY, 63, fhd.deployment_DATE))\n" +
                "\tAND DATEDIFF(DAY, fhd.deployment_DATE, cd.COLLECTION_DATE) <= DATEDIFF(DAY, fhd.deployment_DATE, COALESCE(FS.PROCESSING_DATE ,fhd.crop_DATE, DATEADD(DAY, 63, fhd.deployment_DATE))) \n" +
                "\tAND cd.HOUSE_ID = house.siteUniqueNumber\n" +
                "\tAND cd.FARM_ID = Farm.siteUniqueNumber)";
        return noAssetAssociationQuery;
    }



    public static String getNoCollectionDateQuery(String viewName) {
        String noCollectionDateQuery = "SELECT T_RUN_ID, SAMPLE_ID FROM " + viewName + "\n" +
                "where COLLECTION_DATE IS NULL";
        return noCollectionDateQuery;
    }


    public static String getNoSamplesPerCollectionQuery(String viewName) {
        String noSamplesPerCollectionQuery = "SELECT \n" +
                "    COLLECTION_SITE_ID, \n" +
                "    CONVERT(DATE, COLLECTION_DATE) as \"Collection Date\", \n" +
                "    COUNT(SAMPLE_ID) as \"Samples Collected\"\n" +
                "FROM " + viewName + "\n" +
                "GROUP BY \n" +
                "    COLLECTION_SITE_ID, \n" +
                "    CONVERT(DATE, COLLECTION_DATE)\n" +
                "HAVING COUNT(SAMPLE_ID) % 12 <> 0";
        return noSamplesPerCollectionQuery;
    }


    public static String getNoProgramOnAssetQuery(String viewName) {
        String noProgramOnAssetQuery = "SELECT T_RUN_ID, SAMPLE_ID \n" +
                "FROM \n" +
                "    " + viewName + "\n" +
                "WHERE\n" +
                "    FEED_PROGRAM IS NULL \n" +
                "    AND BIO_SHUTTLE IS NULL \n" +
                "    AND VACCINE IS NULL";
        return noProgramOnAssetQuery;
    }

    public static String getCartridgeWithNo12LanesQuery(String viewName) {
        String no12LaneReportedQuery = "SELECT CARTRIDGEID, COUNT(SAMPLE_ID) as SAMPLEID FROM " + viewName + "\n" +
                "GROUP BY CARTRIDGEID\n" +
                "HAVING COUNT(SAMPLE_ID) <> 12";
        return no12LaneReportedQuery;
    }

    public static String getColumnsMatchedQuery(String viewName, String viewNameTwo) {
        String columnMatchedQuery = "select c.column_id\n" +
                "     , c.name          as column_name\n" +
                "     , old.column_name oldcol\n" +
                "from sys.columns   c\n" +
                "    join sys.views v\n" +
                "        on v.object_id = c.object_id\n" +
                "    join\n" +
                "    (\n" +
                "        select c.name as column_name\n" +
                "        from sys.columns   c\n" +
                "            join sys.views v\n" +
                "                on v.object_id = c.object_id\n" +
                "        where object_name(c.object_id) = '" + viewName + "'\n" +
                "    )              old\n" +
                "        on old.column_name = c.name\n" +
                "where object_name(c.object_id) = '" + viewNameTwo + "'\n" +
                "order by column_id;\n";
        return columnMatchedQuery;
    }


    public static String getColumnsUnmatchedQuery(String viewName, String viewNameTwo) {
        String columnUnMatchedQuery = "SELECT c.name AS column_name\n" +
                "FROM sys.columns c\n" +
                "JOIN sys.views v ON v.object_id = c.object_id\n" +
                "WHERE object_name(c.object_id) = '" + viewName + "'\n" +
                "AND c.name NOT IN (\n" +
                "    SELECT c.name\n" +
                "    FROM sys.columns c\n" +
                "    JOIN sys.views v ON v.object_id = c.object_id\n" +
                "    WHERE object_name(c.object_id) = '" + viewNameTwo + "'\n" +
                ")\n" +
                "ORDER BY column_id;";
        return columnUnMatchedQuery;
    }


    public static String getRowsDataMatched(String viewName, String viewNameTwo) {
        String getRowsMatched = "select * from " + viewName + "\n" +
                "except\n" +
                "select * from = " + viewNameTwo;
        return getRowsMatched;
    }


}
