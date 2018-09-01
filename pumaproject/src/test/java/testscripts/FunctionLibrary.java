package testscripts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcases.AppTest;

public class FunctionLibrary extends AppTest {

	public static void navigate(String url) {

		try {

			// Implicitly wait for 3 seconds
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			// Maximize browser window
			driver.manage().window().maximize();

			// Delete all browser cookies
			driver.manage().deleteAllCookies();

			// Navigate to Puma app
			driver.navigate().to(url);

		} catch (Exception E) {

			APPLICATION_LOGS.debug("Error occurred while navigating to Puma app" + E.getMessage());
			System.out.println("Error occured while navigating to Puma app" + E.getMessage());

		}

	}

	// Method to click on any web element
	public static void clickLink(By locator, String elemName) {

		APPLICATION_LOGS.debug("Clicking on : " + elemName);
		System.out.println("Clicking on : " + elemName);

		try {

			// Wait for link to appear on the page
			waitForElementToLoad(locator);

			// Wait for element to be clickable
			waitForElementToBeClickable(locator);

			// Click on the link
			driver.findElement(locator).click();

			// Log result
			System.out.println("Clicked on : '" + elemName + "'");
			APPLICATION_LOGS.debug("Clicked on : '" + elemName + "'");

		}

		catch (Throwable clickLinkException) {

			// Log error
			System.err.println("Error while clicking on - '" + elemName + "' : " + clickLinkException.getMessage());
			APPLICATION_LOGS.debug("Error while clicking on - '" + elemName + "' : " + clickLinkException.getMessage());

		}

	}

	// Method to wait for element to load
	public static void waitForElementToLoad(final By locator) {

		APPLICATION_LOGS.debug("Waiting for web element to load on the page");
		System.out.println("Waiting for web element to load on the page");

		try {

			// Waits for 90 seconds implicitly until expected condition is matched
			Wait<WebDriver> wait = new WebDriverWait(driver, 90);

			// Wait until the element is located on the page
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			// Log result
			APPLICATION_LOGS.debug("Waiting ends ... Web element loaded on the page");
			System.out.println("Waiting ends ... Web element loaded on the page");

		}

		catch (Throwable waitForElementException) {

			// Log error
			APPLICATION_LOGS.debug("Error came while waiting for element to appear : " + waitForElementException.getMessage());
			System.err.println("Error came while waiting for element to appear : " + waitForElementException.getMessage());

		}

	}

	// Method to wait for element to be clickable
	public static void waitForElementToBeClickable(final By locator) {

		APPLICATION_LOGS.debug("Waiting for web element to be clickable");
		System.out.println("Waiting for web element to be clickable");

		try {

			// Waits for 90 seconds implicitly until expected condition is matched
			Wait<WebDriver> wait = new WebDriverWait(driver, 90);

			// Added the new piece of code to click on the element
			@SuppressWarnings("unused")
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

			// Log result
			APPLICATION_LOGS.debug("Waiting ends ... Web element is now clickable");
			System.out.println("Waiting ends ... Web element is now clickable");

		}

		catch (Throwable waitForElementException) {

			// Log error
			APPLICATION_LOGS.debug("Error came while waiting for element to be clickable: " + waitForElementException.getMessage());
			System.err.println("Error came while waiting for element to be clickable: " + waitForElementException.getMessage());

		}

	}

	public static void hoverOnElement(By Locator, String elemName) {

		APPLICATION_LOGS.debug("Hovering on : " + elemName);
		System.out.println("Hovering on : " + elemName);

		try {

			// Wait for parent element to load
			waitForElementToLoad(Locator);

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(Locator)).build().perform();

			// Wait for 1 sec
			Thread.sleep(1000);

			// Log result
			System.out.println("Hovered on : '" + elemName + "'");
			APPLICATION_LOGS.debug("Hovered on : '" + elemName + "'");

		}

		catch (Throwable hoveringException) {

			// Log error
			System.err.println("Error while hovering on - '" + elemName + "' : " + hoveringException.getMessage());
			APPLICATION_LOGS.debug("Error while hovering on - '" + elemName + "' : " + hoveringException.getMessage());

		}

	}

	// Method to retrieve the text from an element
	public static String retrieveText(By locator, String elemName) {

		String retrievedText = null;

		APPLICATION_LOGS.debug("Retrieving Text from : " + elemName);
		System.out.println("Retrieving Text from : " + elemName);

		try {

			// Wait for web element to load on the page
			waitForElementToLoad(locator);

			// Retrieve text from web element
			retrievedText = driver.findElement(locator).getText().trim();

			// Log result
			APPLICATION_LOGS.debug("Retrieved text : " + retrievedText);
			System.out.println("Retrieved text : " + retrievedText);

			return retrievedText.trim();
		}

		catch (Throwable retrieveTextException) {

			// Log error
			System.err.println("Error while Getting Text from '" + elemName + "' : " + retrieveTextException.getMessage());
			APPLICATION_LOGS.debug("Error while Getting Text from '" + elemName + "' : " + retrieveTextException.getMessage());

			return retrievedText;
		}

	}

	public static void switchToNewTab() {

		APPLICATION_LOGS.debug("Switching to new tab...");
		System.out.println("Switching to new tab...");

		try {

			// Get the current window handle
			String parentHandle = driver.getWindowHandle();

			// Get the total window handles
			Set<String> newHandles = driver.getWindowHandles();

			// Switch to the new window
			for (String handle : newHandles) {
				if (!handle.equals(parentHandle)) {
					driver.switchTo().window(handle);
				}

			}

			// Log result
			System.out.println("Switched to new tab");
			APPLICATION_LOGS.debug("Switched to new tab");

		} catch (Throwable switchTabException) {

			// Log error
			System.err.println("Error while Switching to new tab : " + switchTabException.getMessage());
			APPLICATION_LOGS.debug("Error while Switching to new tab : " + switchTabException.getMessage());

		}

	}

	// Retrieve the attribute value
	public static String retrieveAttributeValue(By locator, String value, String elemName) {

		String attributeValue = null;

		APPLICATION_LOGS.debug("Getting Attribute '" + value + "'  Value from : " + elemName);
		System.out.println("Getting Attribute '" + value + "'  Value from : " + elemName);

		try {

			// Get attribute value for the web element
			attributeValue = driver.findElement(locator).getAttribute(value);

			// Log result
			APPLICATION_LOGS.debug("Got Attribute '" + value + "'  Value from : " + elemName);
			System.out.println("Got Attribute '" + value + "'  Value from : " + elemName);

		}

		catch (Throwable retrieveAttributeValueException) {

			// report error
			System.err.println("Error while Getting Attribute '" + value + "' value from '" + elemName + "' : " + retrieveAttributeValueException.getMessage());

			APPLICATION_LOGS.debug("Error while Getting Attribute '" + value + "' value from '" + elemName + "' : " + retrieveAttributeValueException.getMessage());

			return "Fail : Error while Getting Attribute '" + value + "' value from '" + elemName + "' : " + retrieveAttributeValueException.getMessage();

		}

		return attributeValue.trim();

	}

}
