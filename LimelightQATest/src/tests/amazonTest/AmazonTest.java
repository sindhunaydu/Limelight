package tests.amazonTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

public class AmazonTest {

	@BeforeClass(alwaysRun = true)
	public void setupClass() {

	}

	@BeforeMethod(alwaysRun = true)
	public void setupTest() {

	}

	@Parameters()
	@Test(description = "Test Description")
	public void groupSetup() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.com");

		// Searching for ipad air 2 case
		HomePage home = new HomePage(driver);
		home.fillTextInSearch("ipad air 2 case");
		home.clickSearch();

		// Select case material and price range
		SearchResultsPage result = new SearchResultsPage(driver);
		result.selectPlasticCaseMaterial();
		Thread.sleep(4000);
		result.setMinPrice("20");
		result.setMaxPrice("100");
		Thread.sleep(4000);
		
		// Get name, price and score/rating (stars) of the first 5 results
		result.clickGo();
		Thread.sleep(4000);
		result.getTopFive();
		
		//Assert that the first 5 results are between $20 - $100
		result.verifyRange();
		
		// Sort by price and assert
		result.sortByPrice();

		// Sort by score
		result.sortByScore();
		
		// Display recommended item
		result.recommendedItem();

		//Assert that you items are sorted correctly
		result.verifySorting();
		driver.close();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDownTest() {
		
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {

	}
}
