package workingwithelements;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHTML5VideoPlayer 
{
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				System.getProperty("user.dir")+"\\Sources\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/html5video.html");
	}

	@Test
	public void testHTML5VideoPlayer() throws Exception {
		// Get the HTML5 Video Element
		WebElement videoPlayer = driver.findElement(By.id("vplayer"));

		// We will need a JavaScript Executor for interacting with Video
		// Element's methods and properties for automation
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Get the Source of Video that will be played in Video Player
		String source = (String) jsExecutor.executeScript(
				"return arguments[0].currentSrc;", videoPlayer);
		// Get the Duration of Video
		long duration = (Long) jsExecutor.executeScript(
				"return arguments[0].duration", videoPlayer);

		// Verify Correct Video is loaded and duration
		Assert.assertEquals("http://html5demos.com/assets/dizzy.mp4", source);
		Assert.assertEquals(25, duration);

		// Play the Video
		jsExecutor.executeScript("return arguments[0].play()", videoPlayer);

		Thread.sleep(5000);

		// Pause the video
		jsExecutor.executeScript("arguments[0].pause()", videoPlayer);

		// Take a screenshot for later verification
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./Screenshots/pause_play.png"));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

