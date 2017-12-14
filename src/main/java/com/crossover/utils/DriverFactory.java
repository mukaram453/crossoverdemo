package com.crossover.utils;

import com.crossover.exceptions.DriverException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;


public class DriverFactory {
    
    private static final Log logger = LogFactory.getLog(DriverFactory.class);
    private static WebDriver driver;
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless"));
    public static final boolean OS = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser");
        if(browser == null || browser.isEmpty()) {
            logger.info("************************************");
            logger.fatal("browser property is not set properly.......Stopping the test execution.........".toUpperCase());
            System.exit(1);            
        }
        String driverLocation = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"drivers"+File.separator;
        driverLocation = OS ? driverLocation+"windows"+File.separator : driverLocation+"linux"+File.separator;
        try {
            logger.info(String.format("************** Browser is %s in %s **************".toUpperCase(), browser, HEADLESS ? "headless mode" : "non-headless mode"));
            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", (OS ? driverLocation+"chromedriver.exe" : driverLocation+"chromedriver"));
                    driver = getChromeBrowser();  
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", (OS ? driverLocation+"geckodriver.exe" : driverLocation+"geckodriver"));
                    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
                    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "target/firefox_logs.txt");
                    driver =  getFirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.ie.driver", driverLocation+"iedriver.exe");
                    driver = getEdgeDriver();
                    break;
                 default:{
                     logger.info("**********************************************");
                     logger.fatal(browser + " is not a supported browser............. Stopping the test execution..........".toUpperCase());
                     System.exit(1);    
                 }                              
            }
          } catch (DriverException e) {
                e.printStackTrace();
          }
        return driver;
    }

    private static WebDriver getEdgeDriver() {        
        InternetExplorerOptions ieoptions = new InternetExplorerOptions();
        ieoptions.introduceFlakinessByIgnoringSecurityDomains();
        ieoptions.ignoreZoomSettings();
        ieoptions.requireWindowFocus();                    
        return new InternetExplorerDriver(ieoptions);  
    }

    private static WebDriver getFirefoxDriver() {          
         FirefoxOptions firefoxOptions = new FirefoxOptions();
         firefoxOptions.addArguments("--start-maximized");
         firefoxOptions.setHeadless(HEADLESS);      
         return new FirefoxDriver(firefoxOptions);              
    }

    private static WebDriver getChromeBrowser() {       
         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--start-maximized");
         chromeOptions.setHeadless(HEADLESS);         
         return new ChromeDriver(chromeOptions);  
    }
    
    public static void closeBrowser() {
        try {
            if(driver != null) {
                driver.quit();
            }
        } catch (DriverException e) {
            e.printStackTrace();
        }       
    }
}
