package Models;

import static MiscFunctions.DateUtil.date0;

import java.util.ArrayList;
import java.util.Arrays;

public class AgreementManagementModel {

	public String testCaseTitle;
	public String testCaseDescription;
	public String passMessage;
	public String failMessage;
	public String fileName;
	public String fileType;
	public String alertMessage;
	public String selectCheckbox;
	public String checkboxInput;
	public String stepName;
	public String testCaseTitleUser;
	public String testCaseDescriptionUser;
	public String userStatus;
	
	
	
	public AgreementManagementModel (String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage, String _fileName, 
			String _fileType, String _alertMessage) {
		
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		this.fileName = _fileName;
		this.fileType = _fileType;
		this.alertMessage = _alertMessage;
	}


	public AgreementManagementModel (String _selectCheckbox, String _chekboxInput, String _testCaseTitle, String _testCaseDescription, String _stepName, String _testCaseTitleUser, String _testCaseDescriptionUser, String _passMessage, String _failMessage) {
		this.selectCheckbox = _selectCheckbox;
		this.checkboxInput = _chekboxInput;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.stepName = _stepName;
		this.testCaseTitleUser = _testCaseTitleUser;
		this.testCaseDescriptionUser = _testCaseDescriptionUser;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
	}
	
	
	public AgreementManagementModel (String _userStatus, String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage) {

		this.userStatus = _userStatus;
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		
	}
	
	public static ArrayList<String> lstAgreemmentManagementFileName = new ArrayList<>(
			Arrays.asList("Agreement.pdf", 
					"file"+date0+".pdf",
					"Agreementfile"+date0+".pdf"));
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagement = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("AN-License-02: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was able to upload pdf file successfully", "User was not able to upload pdf file", "/src/test/resources/EULA/"+lstAgreemmentManagementFileName.get(0), "PDF file", "New user agreement created."),
					new AgreementManagementModel("AN-License-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/src/test/resources/EULA/sample.docx", "DOCX file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/src/test/resources/EULA/sample.xlsx", "XLSX file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/src/test/resources/EULA/sample.csv", "CSV file", "Please select pdf file."),
					new AgreementManagementModel("AN-License-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/src/test/resources/EULA/sample.png", "PNG file", "Please select pdf file.")
					));
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagementCheckbox = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/input", "AN-License-18: Verify user can assign agreement at Organization level", "This test case will verify that user can assign agreement at Organization level", "Organization", "AN-License-19: Verify agreement is assigned to user at Organization Level", "This test case will verify that agreement is assigned to user at Organization Level", "", ""),
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[2]/div/div/table/tbody/tr[1]/td[1]/div", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[2]/div/div/table/tbody/tr[1]/td[1]/div/input", "AN-License-20: Verify user can assign agreement at User level", "This test case will verify that user can assign agreement at User level", "User", "AN-License-21: Verify agreement is assigned to user at User Level", "This test case will verify that agreement is assigned to user at User Level", "", ""),
					new AgreementManagementModel("/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div/div[1]", "/html/body/app-root/div/app-manage-user-license-component/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/ul/li/div[1]/div[1]/input", "AN-License-22: Verify user can assign agreement at Organization Type level", "This test case will verify that user can assign agreement at Organization Type level", "Organization Type", "AN-License-23: Verify agreement is assigned to user at Organization Type Level", "This test case will verify that agreement is assigned to user at Organization Type Level", "", "")
					));
	
	
	public static ArrayList<AgreementManagementModel> lstAgreementManagementDeactivate = new ArrayList<>(
			Arrays.asList(
					new AgreementManagementModel("No items found", "AN-License-32: Verify that assigned but deactivated agreement is not displayed in user agreement dropdown", "This testcase will verify that assigned but deactivated agreement is not displayed in user agreement dropdown", "The assigned but deactivated agreement was not displayed", "The assigned but deactivated agreement was displayed"),
					new AgreementManagementModel("", "AN-License-33: Verify that assigned and reactivated agreement is displayed in user agreement dropdown", "This testcase will verify that assigned and reactivated agreement is displayed in user agreement dropdown", "The assigned and reactivated agreement was displayed successfully", "The assigned and reactivated agreement was not displayed")
							));	
		
	
}