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

    @Given("^I have logined as user (.*?) with password (.*?)$")
    public void i_have_logined(String  email, String password) throws Throwable {
        dashBoardPage = loginPage.login(email, password);
    }

    @And("^I have navigated to page Accounts/Bank Accounts$")
    public void i_have_navigated_to_page_Accounts_Bank_Accounts() throws Throwable {
        bankAccountsPage = new BankAccountsPage(webDriver, helper.getPageUrls());
    }

    @When("^I try to add an new bank account with (.*?), (.*?), (.*?), (.*?) and (.*?)$")
    public void i_try_to_add_an_new_bank_account(String bankName, String accountName,
                                                 String accountNunmber, String currency, String type) throws Throwable {
        BankAccount account = BankAccountBuilder.any().withBank(bankName).withName(accountName).withNumber(accountNunmber).withCurrency(currency).withType(type).build();
        bankAccountsPage.addBankAccount(account);
    }

    @Then("^I should see the newly added bank account displayed on page Accounts/Bank Accounts with correct (.*?), (.*?), (.*?), (.*?) and (.*?)$")
    public void i_should_see_the_newly_added_bank_account_displayed_on_page_Accounts_Bank_Accounts(String bankName, String accountName,
            String accountNunmber, String currency, String type) throws Throwable {
        BankAccount actualAccount = bankAccountsPage.findBankAccount(accountName).orElseThrow(() -> new RuntimeException("unable to find bank account with account name: " + accountName));
        if (type.equals("Credit Card")) {
            accountNunmber = "XXXX-XXXX-XXXX-" + accountNunmber;
        }

        BankAccount expectedAccount = new BankAccount(bankName, accountName, accountNunmber, currency, type);
        assertEquals("The newly added bank account's information is not correct.", expectedAccount, actualAccount);
    }

    @Given("^I have chosen (.*?)$")
    public void i_have_chosen_organization(String organisation) throws Throwable {
        dashBoardPage.selectOrganisation(organisation);
    }


}
