package com.crossover.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "com.crossover.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber", "json:results/cucumber.json", "junit:results/cucumber.xml" })
public class CrossOverDemoTest {
    
}
