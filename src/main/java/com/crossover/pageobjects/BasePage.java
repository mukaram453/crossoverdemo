package com.crossover.pageobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public abstract class BasePage {
    private static final Log logger = LogFactory.getLog(BasePage.class);
    
    protected static WebDriver driver;
	protected JavascriptExecutor jsExecutor;

	public BasePage(WebDriver driver){
		BasePage.driver = driver;
		this.jsExecutor = (JavascriptExecutor) driver;
	}
	
	public void openUrl(String url) {	    
	    driver.get(url);
	}
	
	public String getTitle() {
	    return driver.getTitle();
	}
	
	public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
	
	public WebElement webElementByXpathText(String xpathText) {
	    return driver.findElement(By.xpath(String.format("//*[text()='%s']", xpathText)));         
    }
	
	
	public void waitForPageLoad() {
	   logger.info("Waiting for Page Load to complete....");
	   ExpectedCondition<Boolean> pageLoadCondition = driver1 -> jsExecutor.executeScript("return document.readyState").equals("complete");
	   WebDriverWait wait = new WebDriverWait(driver, 30);
	   wait.until(pageLoadCondition);
	}
	
	protected WebElement waitFor(final By locator, String message) {
	    logger.info("waiting for " + locator);
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS).withMessage(message)
                .ignoring(NoSuchElementException.class);

        WebElement webElement = wait.until(driver -> {           
            return driver.findElement(locator);
        });
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return webElement;
    }
	
	protected void scrollIntoView(WebElement element) {
	    logger.info("Scrolling into view... ");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

	protected void scrollToTheTop() {
	    logger.info("Scrolling into top... ");
        jsExecutor.executeScript("window.scrollBy(0,-500)", "");
    }

	protected void scrollVerticallyByPixels(int pixels) {
	    logger.info("Scrolling vertically...");
        jsExecutor.executeScript("window.scrollBy(0,\"" + pixels + "\")", "");
    }

	protected void scrollByPixelsHorizontal(int pixels) {
	    logger.info("Scrolling horizontally...");
        jsExecutor.executeScript("window.scrollBy(\"" + pixels + "\", 0)", "");
    }

}
