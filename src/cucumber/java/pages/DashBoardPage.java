package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.accounts.ChartOfAccountsPage;

public class DashBoardPage extends Page {

    private static final By ORGANISATION_CHOOSER_BY = By.className("org-name");

    public DashBoardPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public void selectOrganisation(String organisationName) {
        if (organisationName.equals(getCurrentOrganisation())) {
            return;
        }

        expandOrganisationChooser();
        driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName)).click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName))));
        } catch (TimeoutException e) {
            System.err.println(driver.getCurrentUrl());
            System.err.println(driver.getPageSource());
            throw new RuntimeException("unable to select organisation: " + organisationName);
        }
    }

    public void deleteAllTestBankAccountsUnderAllOrganisations() {
        List<String> allOrganisations = getAllOrganisations();;

        allOrganisations.stream().forEach(e -> deleteAllTestBankAccounts(e));
    }

    private List<String> getAllOrganisations() {
        expandOrganisationChooser();

        List<String> allOrganisations = driver.findElement(By.className("xn-h-org")).findElements(By.tagName("li")).stream()
                .map(e -> e.findElement(By.tagName("a")).getText()).collect(Collectors.toList());

        foldOrganisationChooser();

        return allOrganisations;
    }

    private void deleteAllTestBankAccounts(String organisation) {
        selectOrganisation(organisation);
        new ChartOfAccountsPage(driver, pageUrls).deleteAllTestBankAccounts();;
    }

    private String getCurrentOrganisation() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ORGANISATION_CHOOSER_BY));
        String currentOrganisation = driver.findElement(ORGANISATION_CHOOSER_BY).getText();

        return currentOrganisation;
    }

    private boolean isOrganisationChooserExpanded() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(ORGANISATION_CHOOSER_BY));
        WebElement parent = driver.findElement(ORGANISATION_CHOOSER_BY).findElement(By.xpath("./.."));
        String parentCss = parent.getAttribute("class");

        return parentCss.contains("open");
    }

    private void expandOrganisationChooser() {
        if (isOrganisationChooserExpanded()) {
            return;
        }

        waitAndClick(ORGANISATION_CHOOSER_BY, DEFAULT_TIMEOUT);
    }

    private void foldOrganisationChooser() {
        if (!isOrganisationChooserExpanded()) {
            return;
        }

        waitAndClick(ORGANISATION_CHOOSER_BY, DEFAULT_TIMEOUT);
    }



}
