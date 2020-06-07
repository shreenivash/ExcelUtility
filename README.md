# ExcelUtility
Excel utility for DataProvider || Sample reporting || Rest Assured 


This utility is created to support sending of data using a excel file 
to a specified Post API.

scenario : lets say you are doing manual testing and have to send
data to a specified server after you collect data on certain params.

then all you need here to use this excel utility.

just add all the variable names in the excel file.
@DataProvider annotation of testng will help extract the information
added in the excel file using ExcelUtility class and pass those 
params row by row in the test .

test will then call the api and send data to which ever server it 
wants to send.

