package com.crossover.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.crossover.pageobjects.HomePage;
import com.crossover.pageobjects.JobsPage;
import com.crossover.utils.DriverFactory;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CrossOverDemoStepDefs {
       
    HomePage homePage;
    JobsPage jobsPage;
        
     
    public CrossOverDemoStepDefs(){
        homePage = new HomePage(Hooks.driver);
        jobsPage = new JobsPage(Hooks.driver);
    }
    
    @Given("Go to crossover URL") 
    public void goToCrossOver() { 
        homePage.openHomePage();
    }
    
    @Then("Crossover home page should open: (.*)") 
    public void homePageValidation(String expectedUrl) {
      assertEquals("message", expectedUrl, homePage.getCurrentUrl());
    }
    
    @When("Click on JOBS and verify it was navigated to jobs available-jobs page") 
    public void clickOnJobs() {
       jobsPage = homePage.openJobsPage();
    }
    
    @Then("Default available-jobs page should open: (.*)") 
    public void jobsPageValidation(String expectedUrl) {
        assertEquals("message", expectedUrl, jobsPage.getCurrentUrl());
    }
    
    @When("Focus on the Job title, keywords field and Type the text: (.*)") 
    public void textSearchInJobField(String text) { 
        jobsPage.enterTextInJobsTitleField(text);        
    }
    
    @And("Click on the SEARCH JOBS button and verify that it shows the result accordingly") 
    public void submitJobTextSearch() {        
        jobsPage.clickOnSubmitButton();        
    }
    
    @Then("The search should display results containing the text: (.*) only")
    public void jobsSearchValidation(String searchText) {        
        List<String> messages = new ArrayList<String>();
        for (WebElement webElement : jobsPage.jobsResultList()) {
            String job = webElement.getText();
            if(!job.contains(searchText)) {
                messages.add(String.format("The job %s does not contain the text %s", job, searchText));
            }           
        }
        if(!messages.isEmpty()) {          
            assertFalse(messages.toString(), true);
        }
    }
    
    @When("Click on the RESET button") 
    public void clickResetButton() { 
        jobsPage.resetButtonClick();
    }
    
    @When("Click on All Job Categories drop-down box") 
    public void clickAllJobCategories() { 
        jobsPage.allJobsCategoriesDropDown();
    }
    
    @Then("All Job Categories drop-down box should display various:") 
    public void verifyAllJobCategories(List<String> expected) { 
        List<String> actual = jobsPage.allCategoriesResultList();
        assertEquals(expected, actual);
    }
    
    @When("Click on: (.*) and verify that it gives the result as per search criteria") 
    public void searchJavaRelatedJobs(String job) { 
        jobsPage.clickOnJobKeyword(job);
    }    
    
    @When("Click on COMPANIES and verify that it was navigated to Main page") 
    public void mainPageNavigationOnCompanyClick() { 
        jobsPage.clickOnCompanyLogo();
    } 
    
    @Then("Close the browser") 
    public void closeBrowser() { 
        DriverFactory.closeBrowser();
    }
    
}
