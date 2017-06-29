package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.accounts.ChartOfAccountsPage;

public class DashBoardPage extends Page {
    private static final int DEFAULT_TIMEOUT = 15; // seconds

    @FindBy(className = "org-name")
    public WebElement orgNameSelectElement;

    public DashBoardPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
        driver.get(pageUrls.getDashboardPageUrl());
    }

    public void selectOrganisation(String organisationName) {
        orgNameSelectElement.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName))));
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to select organisation: " + organisationName);
        }

        driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName)).click();


        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName))));
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to select organisation: " + organisationName);
        }
    }

    public void deleteAllTestBankAccountsUnderAllOrganisations() {
        List<String> allOrganisations = getAllOrganisations();
        System.err.println("show allOrganisations...");
        allOrganisations.stream().forEach(e -> deleteAllTestBankAccounts(e));
    }

    private List<String> getAllOrganisations() {
        orgNameSelectElement.click();
        return driver.findElement(By.className("xn-h-org")).findElements(By.tagName("li")).stream()
                .map(e -> e.findElement(By.tagName("a")).getText()).collect(Collectors.toList());
    }

    private void deleteAllTestBankAccounts(String organisation) {
        System.err.println("delete accounts for " + organisation);
        selectOrganisation(organisation);
        new ChartOfAccountsPage(driver, pageUrls).deleteAllTestBankAccounts();;
    }

}
