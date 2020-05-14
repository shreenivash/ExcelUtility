package org.excelUtility.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

public class Reporter implements IReporter {
    private ExtentReports extentReports;


    @Override
    public void  generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,String outputDirectory){
        extentReports= new ExtentReports(outputDirectory+ File.separator+"reportOfExcelUtility.html",true);
        for(ISuite suite:suites){
            Map<String, ISuiteResult> result=suite.getResults();
            for(ISuiteResult r:result.values()){
                ITestContext context= r.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
//                buildTestNodes(context.getPassedTests(), LogStatus.FAIL);
//                buildTestNodes(context.getPassedTests(), LogStatus.SKIP);
            }

        }
        extentReports.flush();
        extentReports.close();

    }

    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extentReports.startTest(result.getMethod().getMethodName());
                String methodName=result.getMethod().getMethodName();
                String description=result.getMethod().getDescription();
//                test.getTest().getStartedTime() = getTime(result.getStartMillis());
//                test.getTest().getEndedTime() = getTime(result.getEndMillis());

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();

                test.log(status, message);

                extentReports.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
