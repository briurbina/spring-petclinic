package org.springframework.samples.petclinic.bdd;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
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
			driver = loadChrome();
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
			driver = loadChrome();
		}
		// else if (browser.equals("firefox")) {
		// if (world.driver == null) {
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();
		// }
		// }
		else {
			throw new RuntimeException("Invalid browser name!");
		}
		this.driver = driver;
		return driver;
	}

	private WebDriver loadChrome() {
		ChromeDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// String browser = System.getProperty("browser");
		String latency = System.getProperty("latency");
		String downloadThroughput = System.getProperty("downloadThroughput");
		String uploadThroughput = System.getProperty("uploadThroughput");

		if (latency != null || downloadThroughput != null || uploadThroughput != null) {
			CommandExecutor executor = driver.getCommandExecutor();

			// Set the conditions
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("offline", false); // dont need to touch
			map.put("latency", latency != null ? latency : 0);
			map.put("download_throughput", downloadThroughput != null ? downloadThroughput : 500 * 1024);
			map.put("upload_throughput", uploadThroughput != null ? uploadThroughput : 500 * 1024);

			try {

				Response response = executor.execute(new Command(driver.getSessionId(), "setNetworkConditions",
						ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));

			}
			catch (Exception e) {
				throw new RuntimeException(e);

			}
		}

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

	@After(order = Integer.MAX_VALUE)
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

	@After(order = Integer.MIN_VALUE)
	public void closeBrowser() {
		world.driver.quit();
	}

}
