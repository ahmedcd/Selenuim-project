package findingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindElementByXPath {
	FirefoxDriver driver ; 

	@BeforeTest
	public void opeURL() 
	{
		System.setProperty("webdriver.gecko.driver", 
				System.getProperty("user.dir")+"\\Sources\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		driver.navigate().to("https://the-internet.herokuapp.com/login");
	}

	@Test(enabled=false)
	public void testFindByAbsoulteXPath() 
	{
		WebElement usernameTxt = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		WebElement passwordTxt = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
		
		System.out.println(usernameTxt.getTagName());
		System.out.println(passwordTxt.getTagName());
		System.out.println(loginBtn.getText());
	}
	
	@Test
	public void testFindByRelativeXPath() 
	{
		//WebElement usernameTxt = driver.findElement(By.xpath("//input"));
		WebElement passwordTxt = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='radius']"));
		//WebElement loginBtn = driver.findElement(By.xpath("//button"));
		
		//System.out.println(usernameTxt.getTagName());
		System.out.println(passwordTxt.getTagName());
		System.out.println(loginBtn.getText());
	}
	
	
	@AfterTest
	public void closeDriver() 
	{
		driver.quit();
	}
	
}
