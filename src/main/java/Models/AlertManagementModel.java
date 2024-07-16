package Models;

import java.util.ArrayList;

import static MiscFunctions.DateUtil.*;
import static Models.IngestionsModel.*;

public class AlertManagementModel {

	public String TestCaseName;
	public String TestCaseDescription;
	public String steps;
	public String passStep;
	public String failStep;

	public String AlertInfo;
	public String AlertDesc;
	public String DataSource;
	public String Report;
	public String AlertVariable;
	public String AlertMode;
	public String Condition;
	public String Text;
	public String minValue;
	public String maxValue;
	public String AggregateMode;
	public String Deviation;
	public String Notify;
	public String Days;
	public String customDays;
	public String fileJson;
	public String pathogen;
	public String sampleID;

	public boolean isAlertMode;
	public boolean isBetween;
	public boolean isGenerate;

	public ArrayList<ReportFilters> lstFilters;
	public static ArrayList<AlertManagementModel> lstAlertCreate = new ArrayList<>();
	
	
	public AlertManagementModel() {

	}



	public static ArrayList<AlertManagementModel> FillData() {
		ArrayList<AlertManagementModel> lstAlertManagementModel = new ArrayList<AlertManagementModel>();
		AlertManagementModel objTmp;
		ReportFilters objFilter;

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-02: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert1"+date0;
		objTmp.AlertDesc = "Alert Threshold Between (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10";
		objTmp.maxValue = "2000";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-1"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-04: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert2"+date0;
		objTmp.AlertDesc = "Alert Threshold Less Than (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "1000";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-2"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
			
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-06: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert3"+date0;
		objTmp.AlertDesc = "Alert Threshold Greater Than (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-3"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-08: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert4"+date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average >= (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-4"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-10: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert5"+date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average < (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-5"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-12: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert6"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation > (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.isGenerate = true;
		objTmp.sampleID = dateYYYYMMDD+"-TestAut-Alert-6"+date0;
		objTmp.pathogen = "Patho_2";
		objTmp.fileJson = "[{'LANE':'1','PATHOGEN':'"+objTmp.pathogen+"','SAMPLEID':'"+objTmp.sampleID+"','SCANDATETIME':'"+date100+"','OUTCOME':'','STATUSCODE':'','CALIBRATED_PIPER_COUNTS':'0','COUNT_OUTCOME':'','CARTRIDGEID':'CartridgeAlert100','EXPERIMENTID':'','INSTRUMENTID':'PSN0009','USERID':'"+UserID+"','RUN_ID':'"+objTmp.sampleID+"','RUN_TYPE':'SCRIPT_1001a Patho_2 Probes','MPN_CALCULATION_TYPE':'0','DILUTION_FACTOR':'0.01','LANE_NO':'1','DATE':'2020-06-03','TIME':'10:45:43.914898','W1_PC_COUNT':'0','W1_CELL_COUNT':'0','W1_PC_MEAN_INTENSITY':'','W1_CELL_MEAN_INTENSITY':'','W1_PC_RANGE_INTENSITY':'0','W1_CELL_RANGE_INTENSITY':'0','W1_PC_CV_INTENSITY':'','W1_CELL_CV_INTENSITY':'','W1_PC_MEAN_SIZE':'','W1_CELL_MEAN_SIZE':'','W1_PC_RANGE_SIZE':'','W1_CELL_RANGE_SIZE':'','W1_PC_CV_SIZE':'','W1_CELL_CV_SIZE':'','W2_PC_COUNT':'0','W2_CELL_COUNT':'200','W2_PC_MEAN_INTENSITY':'','W2_CELL_MEAN_INTENSITY':'','W2_PC_RANGE_INTENSITY':'0','W2_CELL_RANGE_INTENSITY':'0','W2_PC_CV_INTENSITY':'','W2_CELL_CV_INTENSITY':'','W2_PC_MEAN_SIZE':'','W2_CELL_MEAN_SIZE':'','W2_PC_RANGE_SIZE':'','W2_CELL_RANGE_SIZE':'','W2_PC_CV_SIZE':'','W2_CELL_CV_SIZE':'','STATUS':'','LANE_TOTAL_AREA_UM2':'326035983.8748','LANE_NOISE_AREA_UM2':'82232170.4712','LANE_NOISE_RATIO_PERCENT':'95.73','LANE_NOISE_OBJECT_COUNT':'71','W1_NOISE_AREA_UM2':'8323637.364','W2_NOISE_AREA_UM2':'8315160.7508','W1_NOISE_RATIO_PERCENT':'100.0','W2_NOISE_RATIO_PERCENT':'99.8993635494474','W1_NOISE_OBJECT_COUNT':'0','W2_NOISE_OBJECT_COUNT':'0','LANE_SMALL_NOISE_OBJECT_COUNT':'71','LANE_MEDIUM_NOISE_OBJECT_COUNT':'0','LANE_LARGE_NOISE_OBJECT_COUNT':'0','LANE_EXTRA_LARGE_NOISE_OBJECT_COUNT':'0','IMPROC':'ImprocSalm01','VERSION':'4.0.8.2','ERROR_CODE':'','IE_COLLECTION_SITE_ID':'1065009','IE_SAMPLE_MATRIX_ID':''}]";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
/*
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-14: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert7"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation < (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Less than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-16: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert8"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation >= (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-18: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert9"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Daily (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-20: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert10"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-22: Create alert of data source 'Piper Log', Alert Variable 'Patho_2' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_2' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert11"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Patho_2)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_2";
		objTmp.AlertVariable = "W2 Cell Count";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_2' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-24: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert12"+date0;
		objTmp.AlertDesc = "Alert Threshold Between (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10";
		objTmp.maxValue = "2000";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-26: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert13"+date0;
		objTmp.AlertDesc = "Alert Threshold Less Than (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-28: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert14"+date0;
		objTmp.AlertDesc = "Alert Threshold Greater Than (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-30: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert15"+date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average >= (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-32: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert16"+date0;
		objTmp.AlertDesc = "Alert Aggregate Moving Average < (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-34: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert17"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation > (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-36: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert18"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation < (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-38: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert19"+date0;
		objTmp.AlertDesc = "Alert Aggregate Standard Deviation >= (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-40: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert20"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Daily (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-42: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert21"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-44: Create alert of data source 'Piper Log', Alert Variable 'Patho_1' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Piper Log', Alert Variable 'Patho_1' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert22"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Patho_1)";
		objTmp.DataSource = "Piper Log";
		objTmp.Report = "Patho_1";
		objTmp.AlertVariable = "OPG Total";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Piper Log', Alert Variable 'Patho_1' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-46: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert23"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Between'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10"; 
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-48: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert24"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Less Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-50: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert25"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Threshold 'Greater Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

//		objTmp = new AlertManagementModel();
//		objFilter = new ReportFilters();
//		objTmp.TestCaseName = "AN-AlertM-52: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.lstFilters = new ArrayList<>();
//		objTmp.AlertInfo = "Alert26"+date0;
//		objTmp.AlertDesc = "Farm Performance Feed Conversion Aggregate '>='";
//		objTmp.DataSource = "Farm Performance";
//		objTmp.AlertVariable = "Feed Conversion";
//		objTmp.AlertMode = "Aggregate";
//		objTmp.AggregateMode = "Moving Average";
//		objTmp.Condition = "Greater than or equal to";
//		objTmp.Text = "10";
//		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average >='";
//		objTmp.lstFilters.add(objFilter);
//		lstAlertManagementModel.add(objTmp);
//		
		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-54: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert27"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Aggregate '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-56: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert28"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '>'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-58: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert29"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-60: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert30"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Standard Deviation '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-62: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert31"+date0;
		objTmp.AlertDesc = "Farm Performance Feed Conversion Absence 'Daily'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-64: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert32"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Farm Perfromance)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-66: Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert33"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Farm Performance)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Feed Conversion";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		/////////////////////


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-68: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert34"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Between'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight/animal";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10"; 
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-70: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert35"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Less Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Feed Conversion' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-72: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert36"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Greater Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-74: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert37"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Aggregate '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-76: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert38"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Aggregate '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-78: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert39"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '>'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-80: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert40"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-82: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert41"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Standard Deviation '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-84: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert31"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Absence 'Daily'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "42";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-86: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert43"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Average Weight)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-88: Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert44"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Average Weight)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Avg Weight";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Average Weight' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		//////////////////////

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-90: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Threshold 'Between'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Between'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert45"+date0;
		objTmp.AlertDesc = "Farm Performance Average Weight Threshold 'Between'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Between";
		objTmp.minValue = "10"; 
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-92: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Threshold 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert46"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Threshold 'Less Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Less Than";
		objTmp.maxValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Between'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-94: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Threshold 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert47"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Threshold 'Greater Than'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Threshold";
		objTmp.Condition = "Greater Than";
		objTmp.minValue = "100";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Threshold 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-96: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert48"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Aggregate '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average >='";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-98: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert49"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Aggregate '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Moving Average";
		objTmp.Condition = "Less Than";
		objTmp.Text = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Aggregate 'Moving Average <'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);	


		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-100: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert50"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Standard Deviation '>'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than";
		objTmp.Text = "10";  //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-102: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Threshold 'Less Than'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert51"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Standard Deviation '<'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Less Than'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-104: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert52"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Standard Deviation '>='";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Aggregate";
		objTmp.AggregateMode = "Standard Deviation";
		objTmp.Condition = "Greater than or equal to";
		objTmp.Text = "10";   //days
		objTmp.Deviation = "10";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Standard Deviation 'Greater Than or equal'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-106: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Daily'";
		objTmp.TestCaseDescription = "This test case will create alert of data source'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert53"+date0;
		objTmp.AlertDesc = "Farm Performance Mortality % Absence 'Daily'";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Daily";
		objTmp.Text = "42";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Daily'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-108: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Custom'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Absence 'Custom'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert54"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Custom (Mortality %)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Custom";
		objTmp.customDays = "1";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Day(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Custom'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);

		objTmp = new AlertManagementModel();
		objFilter = new ReportFilters();
		objTmp.TestCaseName = "AN-AlertM-110: Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' and Alert Mode Absence 'Weekly'";
		objTmp.TestCaseDescription = "This test case will create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters = new ArrayList<>();
		objTmp.AlertInfo = "Alert55"+date0;
		objTmp.AlertDesc = "Alert Mode Absence Weekly (Mortality %)";
		objTmp.DataSource = "Farm Performance";
		objTmp.AlertVariable = "Mortality %";
		objTmp.AlertMode = "Absence";
		objTmp.Notify = "Weekly";
		objTmp.Text = "10";  //Duration
		objTmp.Days = "Week(s)";
		objTmp.steps = "Create alert of data source 'Farm Performance', Alert Variable 'Mortality %' Alert Mode Absence 'Weekly'";
		objTmp.lstFilters.add(objFilter);
		lstAlertManagementModel.add(objTmp);
		 */
		return lstAlertManagementModel;
	}



}
