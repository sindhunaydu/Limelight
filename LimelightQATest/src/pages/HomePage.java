package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	private By getSearchBoxXpath() {
		By xpath = By.xpath("//input[@id='twotabsearchtextbox']");
		return xpath;
	}
	private WebElement getSearchBox(){
		return driver.findElement(getSearchBoxXpath());
	}
	public void fillTextInSearch(String searchText) {
		getSearchBox().sendKeys(searchText);
	}
	public void clickSearch() {
		By xpath = By.xpath("//div[@class='nav-search-submit nav-sprite']//input[@type='submit']");
		driver.findElement(xpath).click();
	}
}