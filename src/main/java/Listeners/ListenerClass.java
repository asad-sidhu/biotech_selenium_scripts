package Listeners;

import ExtentReports.ExtentReport;
import Utilities.ELKUtils;
import org.testng.*;

import java.util.Arrays;

import static Enums.LogType.*;
import static ExtentReports.ExtentLogger.log;


public class ListenerClass implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
			ExtentReport.initReports(this.getClass().getName());
	}

	@Override
	public void onFinish(ISuite suite) {
			ExtentReport.flushReports();
			
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getDescription());
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		//ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
		log(PASS,result.getMethod().getMethodName() +" is passed");
	//	ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "pass");
	}


	@Override
	public void onTestFailure(ITestResult result) {
			log(FAIL,result.getMethod().getMethodName() +" is failed");
			log(FAIL,result.getThrowable().toString());
			log(FAIL,Arrays.toString(result.getThrowable().getStackTrace()));
	//		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "fail");
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		//ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
		log(SKIP,result.getMethod().getMethodName() +" is skipped");
	//	ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "skip");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
