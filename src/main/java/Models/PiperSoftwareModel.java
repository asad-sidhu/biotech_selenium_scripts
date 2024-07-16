package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class PiperSoftwareModel {

	public String testCaseTitle;
	public String testCaseDescription;
	public String passMessage;
	public String failMessage;
	public String fileName;
	public String fileType;
	public String alertMessage;
	public String improcButton;
	public String uploadButton;

	
	
	
	public PiperSoftwareModel (String _testCaseTitle, String _testCaseDescription, String _passMessage, String _failMessage, String _fileName, 
			String _fileType, String _alertMessage, String _improcButton, String _uploadButton) {
		
		this.testCaseTitle = _testCaseTitle;
		this.testCaseDescription = _testCaseDescription;
		this.passMessage = _passMessage;
		this.failMessage = _failMessage;
		this.fileName = _fileName;
		this.fileType = _fileType;
		this.alertMessage = _alertMessage;
		this.improcButton = _improcButton;
		this.uploadButton = _uploadButton;
	}
	
	
	public static ArrayList<PiperSoftwareModel> lstPSManagement = new ArrayList<>(
			Arrays.asList(
					new PiperSoftwareModel("AN-PSM-02: Verify user cannot upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-03: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-04: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-05: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-06: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .msi extension", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-07: Verify user cannot upload already uploaded .msi file", "This test case will verify that user cannot upload already uploaded .msi file", "User was not able to upload already uploaded .msi file", "User was able to upload already uploaded .msi file", "/EULA/PiperUserApp_Installer.08.11.10.14.msi", ".msi file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(1)", "userapp"),
					new PiperSoftwareModel("AN-PSM-08: Verify user cannot upload .msi file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .msi file having less then 4 digit value seperated by decimal", "User was not able to upload .msi file having less then 4 digit value seperated by decimal", "User was able to upload .msi file having less then 4 digit value seperated by decimal", "/EULA/PiperUserApp_Installer.08.11.msi", ".msi file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(1)", "userapp"),
					
					new PiperSoftwareModel("AN-PSM-09: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-10: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-11: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-12: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-13: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-14: Verify user cannot upload already uploaded .zip file", "This test case will verify that user cannot upload already uploaded .zip file", "User was not able to upload already uploaded .zip file", "User was able to upload already uploaded .zip file", "/EULA/sal_algo_v09.79.12.10.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(2)", "improSalm"),
					new PiperSoftwareModel("AN-PSM-15: Verify user cannot upload .zip file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .zip file having less then 4 digit value seperated by decimal", "User was not able to upload .zip file having less then 4 digit value seperated by decimal", "User was able to upload .zip file having less then 4 digit value seperated by decimal", "/EULA/sal_algo_v09.79.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(2)", "improSalm"),

					new PiperSoftwareModel("AN-PSM-16: Verify user can upload pdf file", "This test case will verify that user can upload pdf file", "User was not able to upload pdf file successfully", "User was able to upload pdf file", "/EULA/sample.pdf", "PDF file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-17: Verify user cannot upload docx file", "This test case will verify that user cannot upload docx file", "User was not able to upload docx file successfully", "User was able to upload docx file", "/EULA/sample.docx", "DOCX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-18: Verify user cannot upload xlsx file", "This test case will verify that user cannot upload xlsx file", "User was not able to upload xlsx file successfully", "User was able to upload xlsx file", "/EULA/sample.xlsx", "XLSX file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-19: Verify user cannot upload csv file", "This test case will verify that user cannot upload csv file", "User was not able to upload csv file successfully", "User was able to upload csv file", "/EULA/sample.csv", "CSV file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-20: Verify user cannot upload png file", "This test case will verify that user cannot upload png file", "User was not able to upload png file successfully", "User was able to upload png file", "/EULA/sample.png", "PNG file", "Invalid file uploaded. Please upload a file with .zip extension", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-21: Verify user cannot upload already uploaded .zip file", "This test case will verify that user cannot upload already uploaded .zip file", "User was not able to upload already uploaded .zip file", "User was able to upload already uploaded .zip file", "/EULA/algo_v09.62.12.10.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(3)", "improCocc"),
					new PiperSoftwareModel("AN-PSM-22: Verify user cannot upload .zip file having less then 4 digit value seperated by decimal", "This test case will verify that user cannot upload .zip file having less then 4 digit value seperated by decimal", "User was not able to upload .zip file having less then 4 digit value seperated by decimal", "User was able to upload .zip file having less then 4 digit value seperated by decimal", "/EULA/algo_v09.62.zip", ".zip file", "Invalid version uploaded. Either this version already exists or is not the latest version. Please try again with a different version.", ".custom-tab:nth-child(3)", "improCocc")	
					));
	
}
