package pages.accounts.bank;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import domain.BankAccount;
import domain.BankAccountBuilder;
import pages.Page;
import pages.PageUrls;
import pages.accounts.bank.add.EnterDetailsPage;
import pages.accounts.bank.add.FindYourBankPage;

public class BankAccountsPage extends Page {
    private static final int DEFAULT_TIMEOUT = 5; // seconds


    @FindBy(id = "xui-searchfield-1018-inputEl")
    public WebElement bankInputWebElement;

    @FindBy(className = "ba-banklist--item")
    public WebElement bankListWebElement;

    public BankAccountsPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);


    }

    public BankAccountsPage get() {
        driver.get(pageUrls.getBankAccountsPageUrl());
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to access Bank Accounts page with url: " + pageUrls.getBankAccountsPageUrl(), e);
        }

        return this;
    }

    public Optional<BankAccount> findBankAccount(String accountName) {
        System.err.println("entering findBankAccount... ");

        List<WebElement> bankWebElementsWithTheGivenName = driver.findElements(By.className("bank")).stream().filter(bankWebElement -> {
            return accountName.equals(getBankAccount(bankWebElement).getName());
        }).collect(Collectors.toList());

        if (bankWebElementsWithTheGivenName.size() > 1) {
            throw new RuntimeException("there are more than one account with name: " + accountName);
        }

        if (bankWebElementsWithTheGivenName.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(getBankAccount(bankWebElementsWithTheGivenName.get(0)));
    }

    public BankAccountsPage addBankAccount(BankAccount account) {
        driver.get(pageUrls.getAddBankAccountsPageUrl());
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ba-banklist--item")));
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to load add bank account page: " + "https://go.xero.com/Banking/Account/#find", e);
        }

        FindYourBankPage findYourBankPage = new FindYourBankPage(driver, pageUrls);
        EnterDetailsPage enterDetailsPage =  findYourBankPage.selectBank(account.getBank());
        return enterDetailsPage.setAccountDetails(account);
    }

    private static BankAccount getBankAccount(WebElement bankWebElement) {
        String bank = getBankNameViaLogoIamgeLink(bankWebElement.findElement(By.className("logos")).findElement(By.tagName("img")).getAttribute("src"));
        String accountName = bankWebElement.findElement(By.className("bank-name")).getText().split("\\n")[0];
        String accountNumber = bankWebElement.findElement(By.className("bank-name")).findElement(By.tagName("span")).getText();

        // Looks like the currency is not shown for created bank account.
        // Probably because NZD is the only option available for now?
        // Hard code it to NZD
        String currency = "NZD";

        BankAccount account = BankAccountBuilder.any().withName(accountName).withNumber(accountNumber).withBank(bank).withCurrency(currency).build();

        return account;
    }

    private static String getBankNameViaLogoIamgeLink(String linkToBankLogoImage) {
        if (linkToBankLogoImage.equals("https://edge.xero.com/banking/logos/anz_small.png")) {
            return "ANZ (NZ)";
        }

        throw new RuntimeException("unknonw bank logo image:" + linkToBankLogoImage);
    }

}
