package steps;


import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LoginPage;
import support.KnowsTheDomain;

import static org.junit.Assert.*;

public class LoginSteps {
    KnowsTheDomain helper;
    private final EventFiringWebDriver webDriver;
    private LoginPage loginPage;

    public LoginSteps(KnowsTheDomain helper) {
        System.setProperty("webdriver.gecko.driver",
                "C:\\ws\\SeleniumCookbook\\geckodriver.exe");
        webDriver = helper.getWebDriver();
        loginPage = new LoginPage(webDriver, helper.getPageUrls());
    }


//    @When("^I login with user <wenzhhu@gmail\\.com> and password <Wqq@(\\d+)>$")
//    public void i_login_with_user_wenzhhu_gmail_com_and_password_Wqq(int arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//
//    @Then("^I should be redirected to my profile page with my user name <Stephen Hu> on it\\.$")
//    public void i_should_be_redirected_to_my_profile_page_with_my_user_name_Stephen_Hu_on_it() throws Throwable {
//        loginPage.login(email, password);
//    }




}
