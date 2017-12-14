package com.crossover.stepdefinitions;

import com.crossover.pageobjects.HomePage;
import com.crossover.utils.DriverFactory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks{
    
    protected static WebDriver driver;  
    
    
    protected static HomePage openHomePage() {
        return PageFactory.initElements(driver, HomePage.class).openHomePage();
    }
    
    @Before
    public void openBrowser() {
        
        if (driver == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);   
            driver.manage().deleteAllCookies();            
        }        
    }

     
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
       
        if(scenario.isFailed() && driver != null) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }        
        }                
    }  
       
}