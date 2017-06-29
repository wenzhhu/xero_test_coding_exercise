package steps;


import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.BankAccount;
import domain.BankAccountBuilder;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.accounts.bank.BankAccountsPage;
import pages.accounts.bank.add.FindYourBankPage;
import support.KnowsTheDomain;

import static org.junit.Assert.*;

public class BankAccountSteps {
    KnowsTheDomain helper;
    private final EventFiringWebDriver webDriver;
    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private BankAccountsPage bankAccountsPage;

    public BankAccountSteps(KnowsTheDomain helper) {
        this.helper = helper;
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

    @Given("^I have logined as user (.*?) with password (.*?)$")
    public void i_have_logined(String  email, String password) throws Throwable {
        dashBoardPage = loginPage.login(email, password);
    }

    @And("^I have navigated to page Accounts/Bank Accounts$")
    public void i_have_navigated_to_page_Accounts_Bank_Accounts() throws Throwable {
        bankAccountsPage = new BankAccountsPage(webDriver, helper.getPageUrls());
    }

    @When("^I try to add an new bank account with (.*?), (.*?), (.*?) and (.*?)$")
    public void i_try_to_add_an_new_bank_account(String bankName, String accountName,
                                                 String accountNunmber, String currency) throws Throwable {
        BankAccount account = BankAccountBuilder.any().withBank(bankName).withName(accountName).withNumber(accountNunmber).withCurrency(currency).build();
        bankAccountsPage.addBankAccount(account);
    }

    @Then("^I should see the newly added bank account displayed on page Accounts/Bank Accounts with correct (.*?), (.*?), (.*?) and (.*?)$")
    public void i_should_see_the_newly_added_bank_account_displayed_on_page_Accounts_Bank_Accounts(String bankName, String accountName,
            String accountNunmber, String currency, String type) throws Throwable {
        BankAccount actualAccount = bankAccountsPage.findBankAccount(accountName).orElseThrow(() -> new RuntimeException("unable to find bank account with account name: " + accountName));
        BankAccount expectedAccount = new BankAccount(bankName, accountName, accountNunmber, currency, type);
        assertEquals("The newly added bank account's information is not correct.", expectedAccount, actualAccount);
    }












}
