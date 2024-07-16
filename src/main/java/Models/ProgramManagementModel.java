package Models;

import static MiscFunctions.DateUtil.*;

import java.util.ArrayList;

import MiscFunctions.ExtentVariables;
import PageObjects.ProgramManagementPage;

public class ProgramManagementModel {

	public String TestCaseNameCreate;
	public String TestCaseNameUpdate;
	public String TestCaseNameDuplicate;
	public String TestCaseNameDelete;
	public String Program;
	public String ProgramTable;
	public String ProgramName = "Bioshuttle";
	public String ProgramName_CSS;
	public String ProgramType;


	public String EditButtonPre;
	public String CopyButtonPre;
	public String ButtonPost;
	public String DeleteButtonPre;

	public String steps;
	public String input;

	public static String VaccineProgramName = "Sino_"+dateYYYYMMDD+"_"+date0;
	public static String FeedProgramName = "Feed_"+dateYYYYMMDD+"_"+date0;
	public static String TreatmentProgramName = "Treatment_"+dateYYYYMMDD+"_"+date0;
	public static String BioshuttleProgramName = "Bioshuttle_"+dateYYYYMMDD+"_"+date0;

	public static ArrayList<ProgramManagementModel> lstProgramManagementSearch = new ArrayList<>();
//	public static String VaccineProgramName = "Sino_20230328_1059";
//	public static String FeedProgramName = "Feed_20230328_1059";
//	public static String TreatmentProgramName = "Treatment_20230328_1059";
//	public static String BioshuttleProgramName = "Bioshuttle_20230328_1059";

	public static String SupplierName = "China";
	public static String formTreatmentName = "Liquid";
	public static String ingredientName = "Narasin";
	public static String ingredientNameQA = "Ingredient";

	public static String DescriptionName = "Testing Program";

	public static String ComplexNameQA = "Complex 1";
	public static String ComplexNameUAT = "Complex 1";
	public static String FarmNameUAT = "Farm A";


	public static String createProgramTestCaseTitle = "Verify user can create ";
	public static String updateProgramTestCaseTitle = "Verify user can update ";
	public static String duplicateProgramTestCaseTitle = "Verify user can duplicate ";
	public static String deleteProgramTestCaseTitle = "Verify user can delete ";

	public static ArrayList<ProgramManagementModel> FillData() {
		ArrayList<ProgramManagementModel> lstProgramManagementModel = new ArrayList<ProgramManagementModel>();
		ProgramManagementModel objTmp;

		objTmp = new ProgramManagementModel();
		objTmp.Program = "Vaccine Programs";
		objTmp.TestCaseNameCreate = "AN-Program-11: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-15: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDuplicate = "AN-Program-19: "+duplicateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-23: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = VaccineProgramName;
		objTmp.ProgramTable = ProgramManagementPage.programVaccineTable;
		objTmp.ProgramName_CSS =ProgramManagementPage.programVaccineProgramDisplayNameCol;
		objTmp.ProgramType = "Vaccine";
		objTmp.EditButtonPre =  ProgramManagementPage.programVaccineEdit;
		objTmp.CopyButtonPre = ProgramManagementPage.programVaccineCopy;
		objTmp.DeleteButtonPre = ProgramManagementPage.programVaccineDelete;
		objTmp.ButtonPost = ProgramManagementPage.programVaccine_ID;
		lstProgramManagementModel.add(objTmp);

		objTmp = new ProgramManagementModel();
		objTmp.Program = "Feed Programs";
		objTmp.TestCaseNameCreate = "AN-Program-12: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-16: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDuplicate = "AN-Program-20: "+duplicateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-24: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = FeedProgramName;
		objTmp.ProgramTable = ProgramManagementPage.programFeedTable;
		objTmp.ProgramName_CSS =ProgramManagementPage.programFeedProgramDisplayNameCol;
		objTmp.EditButtonPre = ProgramManagementPage.programFeedEdit;
		objTmp.CopyButtonPre = ProgramManagementPage.programFeedCopy;
		objTmp.DeleteButtonPre = ProgramManagementPage.programFeedDelete;
		objTmp.ButtonPost = ProgramManagementPage.programFeed_ID;
		objTmp.ProgramType = "Feed";
		lstProgramManagementModel.add(objTmp);

		objTmp = new ProgramManagementModel();
		objTmp.Program = "Bioshuttle";
		objTmp.TestCaseNameCreate = "AN-Program-14: "+createProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameUpdate = "AN-Program-18: "+updateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDuplicate = "AN-Program-22: "+duplicateProgramTestCaseTitle+objTmp.Program;
		objTmp.TestCaseNameDelete = "AN-Program-26: "+deleteProgramTestCaseTitle+objTmp.Program;
		objTmp.ProgramName = BioshuttleProgramName;
		objTmp.ProgramTable = ProgramManagementPage.programBioshuttleTable;
		objTmp.ProgramName_CSS =ProgramManagementPage.programBioshuttleProgramDisplayNameCol;
		objTmp.ProgramType = "Bioshuttle";
		objTmp.EditButtonPre = ProgramManagementPage.programBioshuttleEdit;
		objTmp.CopyButtonPre = ProgramManagementPage.programBioshuttleCopy;
		objTmp.DeleteButtonPre = ProgramManagementPage.programBioshuttleDelete;
		objTmp.ButtonPost = ProgramManagementPage.programBioshuttle_ID;
		lstProgramManagementModel.add(objTmp);

		return lstProgramManagementModel;
	}


}