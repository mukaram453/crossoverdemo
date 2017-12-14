package com.crossover.pageobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    private static final Log logger = LogFactory.getLog(HomePage.class);
    
    private static final String HOME_PAGE_URL = "https://www.crossover.com/";

	public HomePage(WebDriver driver){
		super(driver);	
		PageFactory.initElements(driver, this);
	} 
	
	public HomePage openHomePage(){
	    logger.info("Opening Url "+HOME_PAGE_URL);
	    openUrl(HOME_PAGE_URL);
	    waitForPageLoad();
        return this;  
   }
	
	public JobsPage openJobsPage(){
	    WebElement jobsLink = webElementByXpathText("Available Jobs");	 	    
	    jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", jobsLink, "target", "_self"); 
	    logger.info("Clicking on Available Jobs link...");
	    jobsLink.click();
	    waitForPageLoad();
	    return PageFactory.initElements(driver, JobsPage.class);  
	}
	
}