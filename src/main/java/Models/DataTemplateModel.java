package Models;

import static MiscFunctions.DateUtil.date0;

import java.util.ArrayList;
import java.util.Arrays;

public class DataTemplateModel {


	public boolean isOpenPopUp;
	public String dtmName;
	public String dtmDesc;
	public String step1;
	public boolean chkMandatoryFieldsS1;
	public String step2;
	public boolean chkAlert;
	
	public boolean chkClm;
	public String clmName;
	public boolean clmType;
	public String clmLength;	
	public boolean chkS2;
	
	public boolean verifyClm;
	public String testcaseTitle;
	public String testcaseDesc;
	public String passScenario;
	public String failScenario;
	
	public static String TemplateName = "Test Template"+date0;
	
	public DataTemplateModel (boolean _isOpenPopup, String _dtmName, String _dtmDesc, String _step1, boolean _chkMandatoryFieldsS1,
			String _step2, boolean _chkAlert, boolean _chkClm, String _clmName, boolean _clmType, String _clmLength, String _testcaseTitle, String _testcaseDesc, String _passScenario, String _failScenario, boolean _chkS2, boolean _verifyClm )
	
	{
		this.isOpenPopUp = _isOpenPopup;
		this.dtmName = _dtmName;
		this.dtmDesc = _dtmDesc;
		this.step1 = _step1;
		this.chkMandatoryFieldsS1 = _chkMandatoryFieldsS1;
		this.step2 = _step2;
		this.chkAlert = _chkAlert;
		
		this.chkClm = _chkClm;
		this.clmName = _clmName;
		this.clmType = _clmType;
		this.clmLength = _clmLength;
		
		this.testcaseTitle = _testcaseTitle;
		this.testcaseDesc = _testcaseDesc;
		this.passScenario = _passScenario;
		this.failScenario =_failScenario;
		this.chkS2 = _chkS2;
		this.verifyClm = _verifyClm;
}
	
	
	public boolean updateTemplate;
	public String updateName;
	public String updateDesc;
	public boolean updateColumn;
	public String updateClmName;
	public String updateFieldLength;
	public String updateTestCase;
	
	
	
	public DataTemplateModel (boolean _updateTemplate, String _updateName, String _updateDesc, boolean _updateColumn, String _updateClmName, String _updateFieldsLength, String _updateTestCase)
	{
	
		this.updateTemplate = _updateTemplate;
		this.updateName = _updateName;
		this.updateDesc = _updateDesc;
		this.updateColumn = _updateColumn;
		this.updateClmName = _updateClmName;
		this.updateFieldLength = _updateFieldsLength;
		this.updateTestCase = _updateTestCase;
		
		
		
	}
	
	
	
	
	public static ArrayList<DataTemplateModel> lstDTMMandatoryCheck = new ArrayList<>(
			Arrays.asList(
					new DataTemplateModel(true, "", "", "1. Leave both fields empty", true, "Should not save template", false, false, "", false, "", "AN-DTM-05: Verify mandatory check with all fields empty", "This test case will verify that user cannot create template with all fields empty", "User was not able to create template with all fields empty", "User was able to create template with all fields empty", false, false)	,
					new DataTemplateModel(false, "name", "", "1. Leave only description field empty", true, "Should not save template", false,  false, "", false, "", "AN-DTM-06: Verify user cannot create template with filling only name field", "This test case will verify that user cannot create template with only name field", "User was not able to create template with name field empty", "User was able to create template with name field empty", false, false),	
					new DataTemplateModel(false, "", "desc", "1. Leave only name field empty", true, "Should not save template", false, false, "", false, "",  "AN-DTM-07: Verify that user cannot create template with filling only description field", "This test case will verify that user cannot create template with only description field", "User was not able to create template with description field empty", "User was able to create template with description field empty", false, false),	
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Fill name and description field", true, "Should not save template without adding column", true,  false, "", false, "",  "AN-DTM-08: Verify user cannot create template without adding atleast 1 column", "This test case will verify that user cannot create template without adding atleast 1 column", "User was not able to create template without adding atleast 1 column", "User was able to create template without adding a column", false, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave all column fields empty", false, "Should not save template", false,  true, "", false, "",  "AN-DTM-09: Verify that user cannot add column with all column fields empty", "This test case will verify that user cannot add column leaving all fields empty", "User was not able to add column with all fields empty", "User was able to add column with all fields empty",true, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave type and length field empty", false, "Should not save template", false,  true, "Serial", false, "",  "AN-DTM-10: Verify user cannot add column with only name field", "This test case will verify that user cannot add column with adding only name field", "User was not able to add column with only name field", "User was able to add column with only name field",true, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave name and type field empty", false, "Should not save template", false,  true, "", false, "100",  "AN-DTM-11: Verify that user cannot add column with only length field", "This test case will verify that user cannot add column with adding only length field", "User was not able to add column with only length field", "User was able to add column with only length field",true, false),
					new DataTemplateModel(false, "Test Template", "Test Description", "1. Leave only type field empty", false, "Should not save template", false,  true, "Serial", false, "100",  "AN-DTM-12: Verify that user cannot add column without adding column type", "This test case will verify that user cannot add column without adding column type", "User was not able to add column without adding column type", "User was able to add column without adding column type",true, false),
					new DataTemplateModel(false, TemplateName, "Test Description", "1. Fill all fields of template and column", false, "Should save template", false,  true, "Serial", true, "100",  "AN-DTM-13: Verify user is able to add column after filling all mandatory fields", "This test case will verify that user can add column after filling all mandatory fields of column", "User was able to add column after filling all mandatory fields", "User was not able to add column after filling all mandatory fields", true, true)
					));

	
	public static ArrayList<DataTemplateModel> lstDTMUpdate = new ArrayList<>(
			Arrays.asList(
					new DataTemplateModel(false, "Test Template", "Test Description", true, "Date", "10", "Test Case"),
					new DataTemplateModel(true, "Test Template", "Test Description", false, "", "", "Test Case")
					));
	
	public static ArrayList<String> lstDTMColumnUpdation = new ArrayList<>(
			Arrays.asList("100"));
	
	
	
}