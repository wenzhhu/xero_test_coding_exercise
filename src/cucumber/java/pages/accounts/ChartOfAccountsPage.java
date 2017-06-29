package pages.accounts;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Page;
import pages.PageUrls;

public class ChartOfAccountsPage extends Page {
    private static final int DEFAULT_TIMEOUT = 15; // seconds

    @FindBy(className = "org-name")
    public WebElement orgNameSelectElement;

    public ChartOfAccountsPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
        driver.get(pageUrls.getChartOfAccountsPageUrl());
    }

    public void deleteAllTestBankAccounts() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
