package pages.accounts.bank.add;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import domain.BankAccount;
import pages.Page;
import pages.PageUrls;
import pages.accounts.bank.BankAccountsPage;

public class EnterDetailsPage extends Page {
    private static final int DEFAULT_TIMEOUT = 5; // seconds

    @FindBy(css = "a[data-automationid=continueButton]")
    public WebElement continueButtonWebElement;

    public EnterDetailsPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public BankAccountsPage setAccountDetails(BankAccount account) {
        inputAccountName(account.getName());
        selectAccountType(account.getType());
        inputAccountNumber(account.getType(), account.getNumber());

        return clickContinue();
    }

    private BankAccountsPage clickContinue() {
        continueButtonWebElement.click();

        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.urlContains("https://go.xero.com/Bank/BankAccounts.aspx?confirmBankAccountID"));

        return new BankAccountsPage(driver, pageUrls);
    }

    private void inputAccountNumber(String accountType, String number) {
        WebElement accountNumberInput;

        if (!accountType.equals("Credit Card")) {
            accountNumberInput = driver.findElement(By.cssSelector("div[data-automationid=accountNumber]")).findElement(By.tagName("input"));
        } else {
            accountNumberInput = driver.findElement(By.cssSelector("div[data-automationid=creditCardNumber]")).findElement(By.tagName("input"));
        }
        accountNumberInput.sendKeys(number);
        accountNumberInput.click();
    }

    private void selectAccountType(String type) {
        driver.findElement(By.cssSelector("div[data-automationid=accountType]")).findElement(By.tagName("input")).click();
        driver.findElements(By.tagName("li")).stream().filter(e -> e.getText().equals(type)).findFirst().get().click();
    }

    private void inputAccountName(String name) {
        driver.findElement(By.cssSelector("div[data-automationid=accountName]")).findElement(By.tagName("input")).sendKeys(name);;
    }
}
