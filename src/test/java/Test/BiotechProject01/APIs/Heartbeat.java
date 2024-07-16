package Test.BiotechProject01.APIs;


import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Config.BaseTest;
import MiscFunctions.Constants;
import MiscFunctions.ExtentVariables;
import Models.APIVersionModel;
import Models.IngestionsModel;
import Models.ReportFilters;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Heartbeat {


//	@BeforeTest
//	public void extent() throws InterruptedException, IOException {
//		spark = new ExtentSparkReporter("target/Reports/API Version Test"+DateUtil.date+".html");
//		spark.config().setReportName("API Version Test Report");
//	}

	
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Test (description="Test Case: Test API Versions", enabled= true, priority= 1) 
	public void APIVersion() throws InterruptedException, IOException	{
		BaseTest base = new BaseTest();
		APIVersionModel.lstTestAPIVersion = APIVersionModel.FillData(); 
		for (APIVersionModel objModel : APIVersionModel.lstTestAPIVersion) { 	
			try {
//				ExtentVariables.test = ExtentVariables.extent.createTest(objModel.TestCaseName, objModel.TestCaseDescription);
//				ExtentVariables.preconditions = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.PreConditions);
//				ExtentVariables.steps = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Steps);
//				ExtentVariables.results = ExtentVariables.test.createNode(Scenario.class, ExtentVariables.Results);

				for (ReportFilters objFilter : objModel.lstFilters) {

					RequestSpecification request = RestAssured.given();
					request.header("Content-Type", "application/json");
					JSONObject json = new JSONObject();
					json.put("piperid", IngestionsModel.piperId);
					json.put("password", IngestionsModel.piperPassword);
					json.put("DISAPIVersion", objModel.version);
					request.body(json.toString());
					Response response = request.post(Constants.api_authenticate);
					int code = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					System.out.println("StatusCode:"+ code);
					softAssert.assertEquals(code, objModel.statusCodeResponseLogin);

					String data = response.asString();
					System.out.println(data);

					JsonPath jsonPathEvaluator = response.jsonPath();
					String token = jsonPathEvaluator.get("token");	
					int versionUpgrade = jsonPathEvaluator.get("versionUpgrade");	
					String message = jsonPathEvaluator.get("message");
				//	System.out.println(versionUpgrade);
	
					softAssert.assertEquals(versionUpgrade, objModel.versionUpgradeResponse); 
					softAssert.assertEquals(message, objModel.messageResponse);		
					
					/////////////////////////////////FileRequest/////////////////////////////////////
					ExtentVariables.test = ExtentVariables.extent.createTest("Response of FileRequest API");
					
					RequestSpecification request_filerequest = RestAssured.given();
					request_filerequest.header("Content-Type", "application/json");
					request_filerequest.header("Authorization", "bearer " +token);
					HttpGet postRequest = new HttpGet(Constants.api_listUsers);
					postRequest.addHeader("Content-Type", "application/json");
					postRequest.addHeader("Authorization", "Bearer "+token);
					JSONObject json1 = new JSONObject();
					json1.put("piperid", "PSN0001");
					json1.put("fileName", "PiperUserApp_Installer.08.11.10.15.msi");
					json1.put("packageType", 0);
					request_filerequest.body(json1.toString());
					Response response1 = request_filerequest.post(Constants.api_listUsers);
					String data1 = response1.asString();
					System.out.println("FileRequest API: "+data1);
					ExtentVariables.results.createNode("API Response: "+data1);
					softAssert.assertEquals(data1.isEmpty(), objModel.responseBody);
					
					if (!data1.isEmpty()) {
						message = response1.jsonPath().get("message");
						softAssert.assertEquals(message, "Successful Response.");
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////   
					
					//////////////////////////////////MPNSettings////////////////////////////////////
		/*			ExtentVariables.test = ExtentVariables.extent.createTest("Response of MPNSetting API");
					
					RequestSpecification request_mpnrequest = RestAssured.given();
					request_mpnrequest.header("Content-Type", "application/json");
					request_mpnrequest.header("Authorization", "bearer " +token);
					HttpGet postRequest1 = new HttpGet(Constants.api_MPNSettingRequest);
					postRequest1.addHeader("Content-Type", "application/json");
					postRequest1.addHeader("Authorization", "Bearer "+token);
					JSONObject json2 = new JSONObject();
					json2.put("piperid", "PSN0001");
					json2.put("RequestType", "1");
					json2.put("MPNSettingsVersion", "1");
					json2.put("Improc", "ImprocSalm01");
					json2.put("ImprocVersion", "4.9.2.0");
					request_mpnrequest.body(json2.toString());
					Response response2 = request_mpnrequest.post(Constants.api_MPNSettingRequest);
					String data2 = response2.asString();
					System.out.println("MPN Setting API: "+data2);
					ExtentVariables.results.createNode("API Response: "+data2);
					softAssert.assertEquals(data2.isEmpty(), objModel.responseBody);
					
					if (!data2.isEmpty()) {
						message = response2.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////
					
					//////////////////////////////////Userlist////////////////////////////////////
					ExtentVariables.test = ExtentVariables.extent.createTest("Response of Userlist API");
					
					RequestSpecification request_userlistrequest = RestAssured.given();
					request_userlistrequest.header("Content-Type", "application/json");
					request_userlistrequest.header("Authorization", "bearer " +token);
					HttpGet postRequest2 = new HttpGet(Constants.api_UserListRequest);
					postRequest2.addHeader("Content-Type", "application/json");
					postRequest2.addHeader("Authorization", "Bearer "+token);
					JSONObject json3 = new JSONObject();
					json3.put("piperid", "PSN0005");
					json3.put("RequestType", "1");
					json3.put("userListVersion", "V-4");
					request_userlistrequest.body(json3.toString());
					Response response3 = request_userlistrequest.post(Constants.api_UserListRequest);
					String data3 = response3.asString();
					System.out.println("Userlist API: "+data3);
					ExtentVariables.results.createNode("API Response: "+data3);
					softAssert.assertEquals(data3.isEmpty(), objModel.responseBody);
					
					if (!data3.isEmpty()) {
						message = response3.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////
					
					////////////////////////////////////Heartbeat//////////////////////////////////
					ExtentVariables.test = ExtentVariables.extent.createTest("Response of Heartbeat API");
					
					BaseTest basetest = new BaseTest();
					
					RequestSpecification request_heartbeat = RestAssured.given();
					request_heartbeat.header("Content-Type", "application/json");
					request_heartbeat.header("Authorization", "bearer " +token);

					HttpGet postRequest3 = new HttpGet(Constants.api_HeartBeat);
					postRequest3.addHeader("Content-Type", "application/json");
					postRequest3.addHeader("Authorization", "Bearer "+token);

					JSONObject json4 = new JSONObject();
					JSONObject json5 = new JSONObject();
					JSONObject json6 = new JSONObject();
					JSONObject json7 = new JSONObject();
					JSONArray list = new JSONArray();

					json4.put("piperid", "PSN0009");
					json4.put("dateTime", DateUtil.dateRIY+"T"+DateUtil.dateRIT+".000Z");
					json4.put("userListVersion",  "4");

					json5.put("name", "PiperUserApp");
					json5.put("version", "1.0.04");
					
					json6.put("name", "ImprocSalm01");
					json6.put("version", "1.0.04");
					json6.put("MPNsettingsVersion", "0");
					
					json7.put("name", "ImprocCocc01");
					json7.put("version", "1.0.04" );
					json7.put("MPNsettingsVersion", "2");

					list.add(json5);
					list.add(json6);
					list.add(json7);
					json4.put("packages", list);

					request_heartbeat.body(json4.toString());
					Response response4 = request_heartbeat.post(Constants.api_HeartBeat);

					String data4 = response4.asString();
					System.out.println("Heartbeat API: "+data4);
					ExtentVariables.results.createNode("API Response: "+data4);
					softAssert.assertEquals(data4.isEmpty(), objModel.responseBody);
					
					String statusCodeAnnouncement = jsonPathEvaluator.get("statusCode");
		
					if (!data4.isEmpty()) {
						message = response4.jsonPath().get("message");
						softAssert.assertEquals(message, objModel.messageResponseAPI);
					}

		 */
					softAssert.assertAll();
					///////////////////////////////////////////////////////////////////////////
		
		//			ExtentVariables.test.pass("API Version test verified successfully");
		//			ExtentVariables.results.createNode("API Version test verified successfully");
		//			base.saveResult(ITestResult.SUCCESS, null);
				}
			}
			catch(AssertionError er) {
		//		ExtentVariables.test.fail("API Version test failed");
			//	ExtentVariables.results.createNode("API Version test failed");
		//		base.saveResult(ITestResult.FAILURE, new Exception(er));
			}catch(Exception ex){
		//		ExtentVariables.test.fail("API Version test failed");
		//		ExtentVariables.results.createNode("API Version test failed");
		//		base.saveResult(ITestResult.FAILURE, ex);
			}
		}
	}
	
//	@AfterTest
//	public static void endreport() {
//		extent.flush();
//	}
}




