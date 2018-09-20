package com.qa.TeaTesting;

import org.junit.runner.RunWith;

import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = Constants.featurePath)
public class TestRunner {
	
	public static ExtentReports report = new ExtentReports(Constants.reportFP + Constants.reportFN, true);
	
	public static int counter = 0;

	public static void main(String[] args) {

	}

}
