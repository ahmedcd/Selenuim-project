package workingwithelements;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestJavaScriptCalls 
{

	public WebDriver driver ; 

	@BeforeClass
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir")+"\\Sources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.navigate().to("http://www.google.com");
		driver.manage().window().maximize();
	}


	@Test
	public void testJavaScript() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		String title = (String) js.executeScript("return document.title");
		assertEquals("Google", title);
		System.out.println(title);

		long links =  (Long) js
		.executeScript("var links = document.getElementsByTagName('A'); return links.length");
		System.out.println(links);
		assertEquals(47, links);

	}


	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}

}
