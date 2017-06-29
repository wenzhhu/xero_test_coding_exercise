package pages.accounts.bank.add;

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
import pages.accounts.bank.BankAccountsPage;

public class EnterDetailsPage extends Page {
    private static final int DEFAULT_TIMEOUT = 5; // seconds

    @FindBy(className = "ba-banklist--item")
    public WebElement bankListWebElement;

    public EnterDetailsPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public BankAccountsPage setAccountDetails(BankAccount account) {
        inputAccountName(account.getName());
        selectAccountType(account.getType());
        inputAccountNumber(account.getNumber());

        return clickContinue();
    }

    private BankAccountsPage clickContinue() {
        return new BankAccountsPage(driver, pageUrls);
    }

    private void inputAccountNumber(String number) {
        driver.findElement(By.cssSelector("div[data-automationid=accountName]")).findElement(By.tagName("input")).sendKeys(number);;
    }

    private void selectAccountType(String type) {
        driver.findElement(By.cssSelector("div[data-automationid=accountType]")).findElement(By.tagName("input")).click();
//        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
//        try {
//            wait.until(new ExpectedCondition<WebElement>() {
//
//                @Override
//                public WebElement apply(WebDriver d) {
//                    // TODO Auto-generated method stub
//                    return d.;
//                }
//
//            });
//        } catch (TimeoutException e) {
//            throw new RuntimeException("unable to select Bank: " + bank, e);
//        }

        driver.findElement(By.cssSelector("div[data-automationid=accountType]")).findElements(By.tagName("li")).stream().filter(e -> e.getText().equals(type)).findFirst().get().click();

    }

    private void inputAccountName(String name) {
        driver.findElement(By.cssSelector("div[data-automationid=accountNumber]")).findElement(By.tagName("input")).sendKeys(name);;
    }
}
