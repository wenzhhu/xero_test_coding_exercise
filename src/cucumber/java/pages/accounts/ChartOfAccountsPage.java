package pages.accounts;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Page;
import pages.PageUrls;

public class ChartOfAccountsPage extends Page {
    @FindBy(id = "chartOfAccounts")
    public WebElement accountsTableWebElement;

    @FindBy(linkText = "Delete")
    public WebElement accountsDeleteButtonWebElement;

    @FindBy(id = "popupOK")
    public WebElement popupOKButtonWebElement;

    public ChartOfAccountsPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
        driver.get(pageUrls.getChartOfAccountsPageUrl());
    }

    public void deleteAllTestBankAccounts() {
            List<WebElement> accountRows = accountsTableWebElement.findElements(By.cssSelector("tbody tr"));

            List<WebElement> accountsToDelete = accountRows.stream().filter(e -> {
               return e.findElements(By.tagName("td")).get(1).getText().trim().isEmpty();
            }).collect(Collectors.toList());

            if (!accountsToDelete.isEmpty()) {
                accountsToDelete.forEach(e -> e.findElements(By.tagName("td")).get(0).click());
                accountsDeleteButtonWebElement.click();

                WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("popupOK")));
                driver.findElement(By.id("popupOK")).click();
            }

    }

}
