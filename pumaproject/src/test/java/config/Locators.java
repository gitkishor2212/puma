package config;

import org.openqa.selenium.By;

public class Locators {

	public static By menOption = By.xpath("//div[@id='header-nav']//a[@data-subnav='men-subnav']");

	public static By runningOption = By.xpath("//li[@id='men-subnav']//a[text()='Shoes']//following::a[text()='Running']");

	public static By secondShoe = By.xpath("//div[@class='category-products']/ul[1]/li[2]/a");

	public static By nameLocator = By.xpath("//div[@class='category-products']/ul[1]/li[2]/div[@class='product-info']//h2[@class='product-name']/a");

	public static By priceLocator = By.xpath("//div[@class='category-products']/ul[1]/li[2]/div[@class='product-info']//div[@class='price-box']//span[@class='price']");

	public static By productSizeDropdown = By.className("product-size-click-btn");

	public static By sizeLocator = By.xpath("//*[@id='size_label']//li[@class='option-6']/a");

	public static By addToCartButton = By.xpath("//button[@title='Add to Cart']");

	public static By cartHeader = By.xpath("//*[@id='header']//span[text()='Cart']");

	public static By productNameOnCart = By.xpath("//p[@class='product-name']/a");

	public static By priceOnCart = By.xpath("//p[@class='product-name']/a//following::table[1]//span[@class='price']");

	public static By qtyOnCart = By.xpath("//p[@class='product-name']/a//following::table[1]//input[@class='qty cart-item-quantity input-text']");

}
