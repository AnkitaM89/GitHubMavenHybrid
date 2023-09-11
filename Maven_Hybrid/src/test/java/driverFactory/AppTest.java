package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath="./FileInput/Controller.xlsx";
	String outputpath="./FileOutput/HybridResults.xlsx";
	//store testcases and teststeps into variables
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		boolean res =false;
		String tcres ="";
		report= new ExtentReports("./Reports/FunctionalityTest.html");
		//create reference object to access excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count rows in TCSheet and TShseet
		int TCCount = xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log(TCCount+"    "+TSCount,true);
		//iterate all rows in TCSheet
		for(int i=1;i<=TCCount;i++)
		{
			//read test cases from TCSheet
			String module_Name = xl.getCellData(TCSheet, i, 1);
			logger = report.startTest(module_Name);
			logger.assignAuthor("Ankita");
			logger.assignCategory("Primus Bank Functional Testing");
			
			//read module status cell from TCSheet
			String Module_status =xl.getCellData(TCSheet, i, 2);
			if(Module_status.equalsIgnoreCase("Y"))
			{
				//read tcid from TCSheet
				String tcid = xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1;j<=TSCount;j++)
				{
					String desc = xl.getCellData(TSSheet, j, 2);
					//read tcid from TSHSeet
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid)) {
						
						//read keyword cell
						String keyword = xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("adminLogin"))
						{
							String para1_user = xl.getCellData(TSSheet, j, 5);
							String para2_pass= xl.getCellData(TSSheet, j, 6);
							//call login method from Functionlibrary class
							res = FunctionLibrary.adminLogin(para1_user, para2_pass);
							logger.log(LogStatus.INFO, desc);
						}
						else if(keyword.equalsIgnoreCase("branchCreation"))
						{
							String para1_branch= xl.getCellData(TSSheet, j, 5);
							String para2_address1= xl.getCellData(TSSheet, j, 6);
							String para3_address2= xl.getCellData(TSSheet, j, 7);
							String para4_address3= xl.getCellData(TSSheet, j, 8);
							String para5_area= xl.getCellData(TSSheet, j, 9);
							String para6_zipcode= xl.getCellData(TSSheet, j, 10);
							String para7_country= xl.getCellData(TSSheet, j, 11);
							String para8_state= xl.getCellData(TSSheet, j, 12);
							String para9_city= xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.branchesButton();
							res =FunctionLibrary.branchCreation(para1_branch, para2_address1, para3_address2, para4_address3, para5_area, para6_zipcode, para7_country, para8_state, para9_city);
							logger.log(LogStatus.INFO, desc);
						}
						else if(keyword.equalsIgnoreCase("branchUpdation"))
						{
							String para1_branchname= xl.getCellData(TSSheet, j, 5);
							String para2_adrees= xl.getCellData(TSSheet, j, 6);
							String para5_area= xl.getCellData(TSSheet, j, 9);
							String para6_zipcode= xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.branchesButton();
							res =FunctionLibrary.branchUpdation(para1_branchname, para2_adrees, para5_area, para6_zipcode);
							logger.log(LogStatus.INFO, desc);
							
						}
						else if(keyword.equalsIgnoreCase("roleCreation"))
						{
							String para1_roleName= xl.getCellData(TSSheet, j, 5);
							String para2_roleDesc= xl.getCellData(TSSheet, j, 6);
							String para3_roleType= xl.getCellData(TSSheet, j, 7);
							
							FunctionLibrary.rolesButton();
							res =FunctionLibrary.roleCreation(para1_roleName, para2_roleDesc, para3_roleType);
							logger.log(LogStatus.INFO, desc);
						}
						else if(keyword.equalsIgnoreCase("adminLogout"))
						{
							res = FunctionLibrary.adminLogout();
							logger.log(LogStatus.INFO, desc);
						}
						String tsres ="";
						if(res)
						{
							//if res is true write as pass into status cell in to TSsheet
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							logger.log(LogStatus.PASS, desc);
						}
						else
						{
							//if res is false write as fail into status cell in to TSsheet
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
							logger.log(LogStatus.FAIL, desc);
						}
						tcres = tsres;
					}
				}
				//write as pass or fail into TCSheet in to status cell
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
				
			}
			else
			{
				//which test case flag to N Write as Blocked into Status cell in TCSHeet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
				logger.log(LogStatus.SKIP, "Blocked");
			}
			report.endTest(logger);
			report.flush();
			
		}
		
	}
}
