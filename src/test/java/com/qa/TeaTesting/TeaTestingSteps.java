package com.qa.TeaTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;;

@SuppressWarnings("deprecation")
public class TeaTestingSteps {
	
	WebDriver driver;
	
	ExtentReports report;
	ExtentTest test;
	
	@Before
	public void setup()	{
		TestRunner.counter++;
		report = TestRunner.report;
		test = report.startTest("TeaTesting"+TestRunner.counter);
		System.setProperty(Constants.driverType, Constants.cDFilePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
	    driver.get(Constants.url);
	    test.log(LogStatus.INFO, "opened the 'TeaTasting' homepage");
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
	    Index indexPage = PageFactory.initElements(driver, Index.class);
	    indexPage.navToMenu();
	    test.log(LogStatus.INFO, "used navBar to navigate to menu page");
	    Assert.assertEquals("Navigated to the wrong page", "Menu", driver.getTitle());
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {

		Menu menuPage = PageFactory.initElements(driver, Menu.class);
	    if(menuPage.isList()) test.log(LogStatus.PASS, "List of the 3 teas found");
	    else test.log(LogStatus.FAIL, "List of the 3 teas not found");
	    
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		Index indexPage = PageFactory.initElements(driver, Index.class);
		indexPage.navToCheckOut();
		test.log(LogStatus.INFO, "used navBar to navigate to checkout page");
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		if (driver.getTitle().equals("Check Out")) 
			test.log(LogStatus.PASS, "successfully transfered to check out page");
		else test.log(LogStatus.FAIL, "transfered to the wrong page");
		Assert.assertEquals("Navigated to the wrong page", "Check Out", driver.getTitle());
	}
	
	@After
	public void tearDown()	{
		
		System.out.println("hello");
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
