@regression
Feature: Cross Over Demo

  Scenario: Cross Over Link verify
    Given Go to crossover URL
    Then Crossover home page should open: https://www.crossover.com/#index  

  Scenario: Jobs Link verify
    When Click on JOBS and verify it was navigated to jobs available-jobs page
    Then Default available-jobs page should open: https://app.crossover.com/x/marketplace/available-jobs
    
  Scenario: Jobs search verify
    When Focus on the Job title, keywords field and Type the text: Chief
    And Click on the SEARCH JOBS button and verify that it shows the result accordingly
    Then The search should display results containing the text: Chief only
    
  Scenario: Reset button verify
    When Click on the RESET button
    Then Default available-jobs page should open: https://app.crossover.com/x/marketplace/available-jobs
    
   Scenario: All Job Categories drop-down box verify
    When Click on All Job Categories drop-down box
    Then All Job Categories drop-down box should display various:
    
  	| Software Development |
		| C++ |
		| C# (.NET) |
		| Cloud Computing |
		| iOS and Android |
		| Java |
		| PHP |
		| QA and Support |
		| Ruby |
		| UX/UI and Web Design |
		| Other Software Development |
		| Sales |
		| Marketing |
		| Finance |
		| Product Management |
		| Professional Services |
		| Operations |
		| Executive Management |
		| Director/Executive |
		| Management |
		| Other Management |
		| Other |
		
		Scenario: Java related jobs search verify
    	When Click on: Java and verify that it gives the result as per search criteria
    	Then The search should display results containing the text: Java only
    
    Scenario: Reset button verify
    	When Click on the RESET button
    	Then Default available-jobs page should open: https://app.crossover.com/x/marketplace/available-jobs

  	Scenario: Home page verify
    	When Click on COMPANIES and verify that it was navigated to Main page
    	Then Crossover home page should open: https://app.crossover.com/x/home 
    	
    Scenario: Close browser
    	When Close the browser