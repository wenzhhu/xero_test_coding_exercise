package pages.accounts.bank.add;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Page;
import pages.PageUrls;

public class FindYourBankPage extends Page {

    @FindBy(className = "ba-banklist--item")
    public WebElement bankListWebElement;

    public FindYourBankPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public EnterDetailsPage selectBank(String bank) {
        driver.findElements(By.className("ba-banklist--item")).stream().filter(e -> e.getText().equals(bank)).findFirst().get().click();

        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ba-page-title")));
            return new EnterDetailsPage(driver, pageUrls);
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to select Bank: " + bank, e);
        }
    }


}
