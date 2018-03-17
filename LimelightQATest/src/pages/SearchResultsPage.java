package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
	WebDriver driver;
	List<Item> topFive;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectPlasticCaseMaterial() {
		By xpath = By.xpath("//div[@data-a-input-name='s-ref-checkbox-8080061011']//label//input[@type='checkbox']");
		driver.findElement(xpath).click();
	}

	public void setMinPrice(String min) {
		By xpath = By.xpath("//*[@id='low-price']");
		driver.findElement(xpath).sendKeys(min);
	}

	public void setMaxPrice(String max) {
		By xpath = By.xpath("//*[@id='high-price']");
		driver.findElement(xpath).sendKeys(max);
	}

	public void clickGo() {
		By xpath = By.xpath("//input[@class='a-button-input'][@type='submit'][@value='Go']");
		driver.findElement(xpath).click();
	}

	public void getTopFive() {
		topFive = new ArrayList<Item>();
		System.out.println("\nTOP 5 RESULTS");
		System.out.println("-------------");
		int i = 0, count = 1;
		List<WebElement> nameElement, priceElement, scoreElement;
		String name, price, score;
		while (count <= 5) {
			nameElement = driver
					.findElements(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[2]/div/div[4]/div[1]/div/ul/li["
							+ String.valueOf(i) + "]/div/div[3]/div[1]/a/h2"));
			priceElement = driver.findElements(By.xpath("//li[@id='result_" + String.valueOf(i - 1)
					+ "']//div[@class='s-item-container']//div[@class='a-row a-spacing-mini']//div[@class='a-row a-spacing-none']//a[@class='a-link-normal a-text-normal']//span[@class='a-offscreen']"));
			scoreElement = driver.findElements(
					By.xpath("((//a[@class='a-popover-trigger a-declarative'])//i//span)[" + String.valueOf(i) + "]"));
			
			//Check for missing values
			if (nameElement.size() > 0 && priceElement.size() > 0 && scoreElement.size() > 0) {
				name = nameElement.get(0).getAttribute("innerHTML");
				price = priceElement.get(0).getAttribute("textContent").replaceAll("\\$", "");
				score = scoreElement.get(0).getAttribute("innerHTML").replaceAll(" out of 5 stars", "");
				System.out.println(count + ")" + " Name  : " + name);
				System.out.println("   Price : $" + price);
				System.out.println("   Score : " + score +" out of 5 stars\n");
				topFive.add(new Item(name, Double.parseDouble(price), Double.parseDouble(score)));
				count++;
			}
			i++;
		}
	}

	public void verifyRange() {
		for (Item item : topFive) {
			Assert.assertTrue(item.getPrice() >= 20.00 && item.getPrice() <= 100.00,
					"The first 5 results are between $20 - $100");
		}
	}

	// Descending order
	public void sortByPrice() {
		Collections.sort(topFive, new Comparator<Item>() {
			@Override
			public int compare(Item left, Item right) {
				return left.getPrice() > right.getPrice() ? -1 : (left.getPrice() < right.getPrice()) ? 1 : 0;
			}
		});
	}

	// Descending order
	public void sortByScore() {
		Collections.sort(topFive, new Comparator<Item>() {
			@Override
			public int compare(Item left, Item right) {
				return left.getScore() > right.getScore() ? -1 : (left.getScore() < right.getScore()) ? 1 : 0;
			}
		});
	}

	// Verify if list is sorted in descending order based on price
	public void verifySorting() {
		sortByPrice();
		for (int i = 0; i < topFive.size() - 1; i++) {
			Assert.assertTrue(topFive.get(i).getPrice() >= topFive.get(i + 1).getPrice(), "Price is sorted");
		}
	}

	// Recommend item based on price and rating
	public void recommendedItem() {
		String recommendedName = topFive.get(0).getName();
		double recommendedScore = topFive.get(0).getScore(), recommendedPrice = topFive.get(0).getPrice();
		for (int i = 1; i < topFive.size(); i++) {
			if (topFive.get(i).getScore() >= recommendedScore && topFive.get(i).getPrice() <= recommendedPrice) {
				recommendedName = topFive.get(i).getName();
				recommendedScore = topFive.get(i).getScore();
				recommendedPrice = topFive.get(i).getPrice();
			}
		}
		System.out.println("\nRECOMMENDED ITEM");
		System.out.println("----------------");
		System.out.println(
				"Name  : " + recommendedName + "\nPrice : $" + recommendedPrice + "\nScore : " + recommendedScore + " out of 5 stars \n\n");
	}
}