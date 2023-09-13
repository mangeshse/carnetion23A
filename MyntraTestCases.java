package TestCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyntraTestCases {
	@Test
	public void verifySearchResultarepopulatingcorrectResultForMensSneakers() throws IOException, InterruptedException {
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder=\"Search for products, brands and more\"]"))
				.sendKeys("men snekers", Keys.RETURN);
		List<WebElement> productList = driver
				.findElements(By.xpath("//div[@class=\"product-productMetaInfo\"]/h4[@class=\"product-product\"]"));
		Iterator<WebElement> itr = productList.iterator();
		while (itr.hasNext()) {
			String productTitle = itr.next().getText();
			System.out.println("Checking: " + productTitle);
			Assert.assertTrue(productTitle.contains("Sneakers"));

		}
		driver.executeScript("window.scrollBy(0,4000)");
		Thread.sleep(2000);

		WebElement nextBtn = driver.findElement(By.xpath("//a[@rel='next']"));

		while (nextBtn.isEnabled()) {
			driver.findElement(By.xpath("//a[@rel='next']")).click();
			List<WebElement> list = driver
					.findElements(By.xpath("//div[@class=\"product-productMetaInfo\"]/h4[@class=\"product-product\"]"));
			System.out.println("Size of list" + list.size());
			// System.out.println(list.contains("Sneakers"));
		}
		// driver.close();

	}

}
