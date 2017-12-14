package com.crossover.pageobjects;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class JobsPage extends BasePage{

    private static final Log logger = LogFactory.getLog(JobsPage.class);
    
	public JobsPage(WebDriver driver){
		super(driver);	
		PageFactory.initElements(driver, this);
	}    

	
	@FindBy(css="input[class='form-control ng-pristine ng-untouched ng-valid ng-empty']")
	private static WebElement jobsTitleField;	
	
	@FindBy(xpath="//*[@class='btn btn-success']")
    private static WebElement submitButton;
	
	@FindAll({@FindBy(xpath = "//*[@class='jobs-list ng-isolate-scope']//*[@class='cell title ng-binding']")})
    private List<WebElement> jobsSearchResults;
	
	@FindAll({@FindBy(css="span.ui-select-choices-row-inner>div.ng-scope>span.ng-binding")})
    private List<WebElement> allCategories;
	
	@FindBy(css=".logo")
    private static WebElement companyLogo;	
	
	
	public JobsPage enterTextInJobsTitleField(String text){    
	    logger.info("Enter text "+text+" in Jobs Title Field");      
	    jobsTitleField.clear();
	    jobsTitleField.sendKeys(text);
        return this;  
    }
	
	public JobsPage clickOnSubmitButton(){  
	    logger.info("Submit Search Jobs");   
	    submitButton.submit();	    
	    waitFor(By.xpath(String.format("//*[text()='%s']", "RESET")), "RESET button not present");
	    return this;  
    }	

    public  List<WebElement> jobsResultList(){        
	    return jobsSearchResults;        
    }
	
	public  List<String> allCategoriesResultList(){
	    List<String> jobs = new ArrayList<String>();
	    for (WebElement categorie : allCategories) {
            jobs.add(categorie.getText());
        }
	    jobs.remove(0);
        return jobs;                
    }
	
	public JobsPage resetButtonClick(){        
	    logger.info("Clicking on RESET button");
	    webElementByXpathText("RESET").click();      
        return this;  
    }
	
	public JobsPage allJobsCategoriesDropDown(){  
	    logger.info("Clicking on All Job Categories");
        webElementByXpathText("All Job Categories").click(); 
	    return this;  
    }
	
	public JobsPage clickOnJobKeyword(String jobKeyword){   
	    logger.info("Select "+jobKeyword+" keyword");
        webElementByXpathText(jobKeyword).click(); 
	    waitFor(By.xpath(String.format("//*[text()='%s']", "RESET")), "RESET button not present");
        return this;  
    }
	
	public JobsPage clickOnCompanyLogo(){ 
	    logger.info("Clicking on crossover company logo");
	    companyLogo.click(); 
	    waitForPageLoad();
        return this;  
    }	
}	
	