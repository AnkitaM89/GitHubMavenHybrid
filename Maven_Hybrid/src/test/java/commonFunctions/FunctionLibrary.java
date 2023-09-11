package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for admin login
	public static boolean adminLogin(String username, String password)
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected = "adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Admin Login success:"+expected+ "   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Admin Login failed:"+expected+ "   "+actual,true);
			return false;
		}
	}
	//method to click Branches button
	public static void branchesButton()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
	}
	//method to create new branch
	public static boolean branchCreation(String branchName,String address1,String address2,String address3,String area,String zipCode,String country,String state,String city)
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys(branchName);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(address1);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(address2);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(address3);
		driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys(area);
		driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys(zipCode);
		driver.findElement(By.xpath(conpro.getProperty("ObjCountry"))).sendKeys(country);
		driver.findElement(By.xpath(conpro.getProperty("ObjState"))).sendKeys(state);
		driver.findElement(By.xpath(conpro.getProperty("ObjCity"))).sendKeys(city);
		driver.findElement(By.xpath(conpro.getProperty("ObjSubmit"))).click();
		//capture alert text
		String expected = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String actual = "New branch with id";
		if(expected.toLowerCase().contains(actual.toLowerCase()))
		{
			Reporter.log(expected,true);
			return true;
		}
		else
		{
			Reporter.log("Fail to create new branch",true);
			return false;
		}
	}
    //method to update existing branch
	public static boolean branchUpdation(String branchName, String address1,String area,String zipCode) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
		WebElement e1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
		e1.clear();
		e1.sendKeys(branchName);
		WebElement e2 = driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
		e2.clear();
		e2.sendKeys(address1);
		WebElement e3 = driver.findElement(By.xpath(conpro.getProperty("ObjAreaName")));
		e3.clear();
		e3.sendKeys(area);
		WebElement e4 = driver.findElement(By.xpath(conpro.getProperty("ObjZip")));
		e4.clear();
		e4.sendKeys(zipCode);
		driver.findElement(By.xpath(conpro.getProperty("ObjUpdate"))).click();
		Thread.sleep(2000);
		String expected = driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		String actual = "Branch updated Sucessfully";
		if(expected.equalsIgnoreCase(actual))
		{
			Reporter.log(expected,true);
			return true;
		}
		else
		{
				Reporter.log("Failed to update branch",true);
				return false;
		}
	}
	//method to log out
	public static boolean adminLogout()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		if(driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Admin logout success",true);
			return true;
		}
		else
		{
			Reporter.log("Admin logout failed",true);
			return false;
		}
	}
	//method to click Branches button
		public static void rolesButton()
		{
			driver.findElement(By.xpath(conpro.getProperty("objRoles"))).click();
		}
    //method to create new role
	public static boolean roleCreation(String roleName,String roleDesc,String roleType) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("objNewRole"))).click();
		driver.findElement(By.xpath(conpro.getProperty("objRoleName"))).sendKeys(roleName);
		driver.findElement(By.xpath(conpro.getProperty("objRoleDesc"))).sendKeys(roleDesc);
		Thread.sleep(5000);
		Select roleTypeListbox = new Select(driver.findElement(By.xpath(conpro.getProperty("objRoleType"))));
		roleTypeListbox.selectByValue(roleType);
		Thread.sleep(5000);
		driver.findElement(By.xpath(conpro.getProperty("objRoleSubmit"))).click();
		Thread.sleep(5000);
		String actual = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String expected = "Created Sucessfully";
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log(actual,true);
			return true;
		}
		else
		{
			Reporter.log("Failed to create new role",true);
			return false;
		}
	}
}


