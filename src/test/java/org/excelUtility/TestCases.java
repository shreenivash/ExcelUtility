package org.excelUtility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.excelUtility.utils.ExcelUtility;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCases {
    @DataProvider(name = "dataFromExcel")
    public Object[][] getDataFromExcel() throws Exception{
        Object[][] testObjectArray= ExcelUtility.
                getTableArray("src/main/java/org/excelUtility/utils/resources/sampleDataSheet.xlsx",
                        "Sheet1");
        return testObjectArray;
    }


    @Test(dataProvider = "dataFromExcel")
    public void validateDataFromExcelUsingDataProvider(String TC_ID,String appStamp,String deviceName,
                                                       String platformName,String deviceVersion,
                                                       String tfSize,String ondeviceSize){
        SoftAssert softAssert= new SoftAssert();
        System.out.println("Test cases ID is "+TC_ID);
        Reporter.log("TC No "+TC_ID +" has values "+appStamp);

        RestAssured.baseURI ="https://localhost:8080/sendData";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        requestParams.put("appStamp", appStamp);
        requestParams.put("deviceName", deviceName);
        requestParams.put("platformName", platformName);
        requestParams.put("deviceVersion", deviceVersion);
        requestParams.put("tfSize",  Double.parseDouble(tfSize));
        requestParams.put("ondeviceSize",Double.parseDouble(ondeviceSize));

        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request

        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        System.out.println(request);

        // Post the request and check the response
        Response response = request.post();

        int statusCode = response.getStatusCode();

        softAssert.assertEquals(statusCode, "200");

        String successCode = response.jsonPath().get("SuccessCode");

        softAssert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");

        System.out.println(response);

        softAssert.assertAll();
    }
}
