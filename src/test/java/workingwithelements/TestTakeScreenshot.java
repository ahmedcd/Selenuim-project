package workingwithelements;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTakeScreenshot 
{
	public WebDriver driver ; 

	@BeforeClass
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir")+"\\Sources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.navigate().to("http://www.amazon.com");
		driver.manage().window().maximize();
	}

	@Test
	public void testScreenshotOnFailure() throws InterruptedException 
	{
		WebElement searchTxt =driver.findElement(By.id("twotabsearchtextbox"));
		searchTxt.sendKeys("Selenium WebDriver Book");
		searchTxt.submit();
		assertTrue(driver.getTitle().contains("Selenium WebDriver"));
	}

	@AfterMethod
	public void takeScreenShot(ITestResult result) throws IOException 
	{
		if (ITestResult.FAILURE == result.getStatus()) 
		{
			// create reference of TakesScreenShots
			TakesScreenshot ts = (TakesScreenshot)driver; 
			File source = ts.getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(source, new File("./Screenshots/"+ result.getName()+".png"));
		}
	}

	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}


}
