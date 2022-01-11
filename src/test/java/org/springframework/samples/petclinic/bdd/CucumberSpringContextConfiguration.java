package org.springframework.samples.petclinic.bdd;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Class to use spring application context while running cucumber
 */
@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringContextConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

	private World world;

	private WebDriver driver;

	@Autowired
	public CucumberSpringContextConfiguration(World world) {
		this.world = world;
		world.baseUrl = getBaseUrl();
		world.driver = getDriver(world);
	}

	private WebDriver getDriver(World world) {
		String browser = System.getProperty("browser");

		WebDriver driver;

		if (browser == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equals("safari")) {
			if (world.driver == null) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			}
			else {
				driver = world.driver;
			}
		}
		else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else {
			throw new RuntimeException("Invalid browser name!");
		}
		this.driver = driver;
		return driver;
	}

	/**
	 * Need this method so the cucumber will recognize this class as glue context
	 * configuration
	 */
	@Before
	public void setUp(Scenario scenario) {

		this.world.scenario = scenario;

		LOG.info("Scenario info: " + scenario.getName());

		LOG.info("--------------  Context Initialized For Executing Cucumber Tests --------------");
	}

	private String getBaseUrl() {
		String baseUrl = System.getProperty("baseUrl");

		if (baseUrl == null) {
			baseUrl = "http://localhost:8080/";
		}

		LOG.info("The Base URL is: " + baseUrl);
		return baseUrl;
	}

	@After
	public void embedScreenshot(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				// String testName = scenario.getName();
				scenario.attach(screenshot, "image/png", "adding screenchot...");
				// scenario.write(testName);
			}
			catch (WebDriverException wde) {
				System.err.println(wde.getMessage());
			}
			catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
	}

}
