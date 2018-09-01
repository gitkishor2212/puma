package testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testscripts.FunctionLibrary;
import config.Locators;

/**
 * Unit test for simple App.
 */

public class AppTest {

	public static Properties CONFIG;
	public static Properties LOG;
	public static WebDriver driver = new ChromeDriver();
	public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");

	@BeforeClass
	public static void initialize() throws IOException, ParseException {

		// Override default J2SE built-in workable logger built-in
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

		// Locates and loads the config properties
		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//config//config.properties");
		CONFIG.load(fs);

		// Locate Application Log
		LOG = new Properties();

		fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\log4j.properties");
		LOG.load(fs);
		LOG.setProperty("log4j.appender.dest1.File", System.getProperty("user.dir") + "\\src\\test\\java\\config\\application.log");
		LOG.store(new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\java\\log4j.properties"), null);

	}

	@AfterClass
	public static void endScript() throws InterruptedException {

		// Close the browser if build run is complete and still automation is running
		if (driver != null) {
			// Close the driver
			driver.quit();
		}

		APPLICATION_LOGS.debug("\nTest run finished");
		System.out.println("\nTest run finished");

	}

	@Test
	public void testmyApp() throws InterruptedException {
		// System.out.println("Hello World");
		Assert.assertEquals("Test", "Test");

		// Navigate to the puma application
		FunctionLibrary.navigate(CONFIG.getProperty("url"));

		// Hover on 'Men' option.
		FunctionLibrary.hoverOnElement(Locators.menOption, "'Men' option");

		// Click on Running option
		FunctionLibrary.clickLink(Locators.runningOption, "'Running' option");

		// Retrieve the price of the product
		String expectedPrice = FunctionLibrary.retrieveText(Locators.priceLocator, "Price of the product");
		String expectedName = FunctionLibrary.retrieveText(Locators.nameLocator, "Name of the product");

		// Click on the second item
		FunctionLibrary.clickLink(Locators.secondShoe, "Second Shoe on the page");

		// Switch to the new tab opened
		FunctionLibrary.switchToNewTab();

		// Click on the dropdown
		FunctionLibrary.clickLink(Locators.productSizeDropdown, "Product Size Dropdown");

		// Select size 6
		FunctionLibrary.clickLink(Locators.sizeLocator, "Select size 6");

		Thread.sleep(5000);

		// Click on ADD TO CART button
		FunctionLibrary.clickLink(Locators.addToCartButton, "ADD TO CART button");

		// Hover on Cart option
		FunctionLibrary.hoverOnElement(Locators.cartHeader, "Cart header");

		Thread.sleep(5000);

		// Assert the product in the cart
		String actualPrice = FunctionLibrary.retrieveText(Locators.priceOnCart, "Actual Price of the product");
		String actualName = FunctionLibrary.retrieveText(Locators.productNameOnCart, "Actual Name of the product");
		String actualQuantity = FunctionLibrary.retrieveAttributeValue(Locators.qtyOnCart, "value", "Actual Name of the product");

		Assert.assertEquals(expectedPrice, actualPrice);
		Assert.assertEquals(expectedName, actualName);
		Assert.assertEquals("1", actualQuantity);

	}
}
