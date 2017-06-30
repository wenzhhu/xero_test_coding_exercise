package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.accounts.ChartOfAccountsPage;

public class DashBoardPage extends Page {
    private static final int DEFAULT_TIMEOUT = 25; // seconds

    private static final By organisationChooserBy = By.className("org-name");

    public DashBoardPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public void selectOrganisation(String organisationName) {
        if (organisationName.equals(getCurrentOrganisation())) {
            System.err.println("No need to change org since it's the current one: " + organisationName);
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
        List<String> allOrganisations = getAllOrganisations();
        System.err.println("show allOrganisations...");
        allOrganisations.stream().forEach(System.err::println);

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
        System.err.println("delete accounts for " + organisation);
        selectOrganisation(organisation);
        new ChartOfAccountsPage(driver, pageUrls).deleteAllTestBankAccounts();;
    }



    private void waitAndClick(By byParent, By byChild, int seconds) {
        System.err.println("double waitAndClick...");
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(byParent).findElement(byChild)));
        WebElement e = driver.findElement(byParent).findElement(byChild);

        Actions builder = new Actions(driver);
        builder.moveToElement(e).click();
        System.err.println("done double waitAndClick...");
      //  driver.findElement(by).click();
    }

    private String getCurrentOrganisation() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(organisationChooserBy));
        String currentOrganisation = driver.findElement(organisationChooserBy).getText();
        System.err.println("getCurrentOrganisation: " + currentOrganisation);
        return currentOrganisation;
    }

    private boolean isOrganisationChooserExpanded() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(organisationChooserBy));
        WebElement parent = driver.findElement(organisationChooserBy).findElement(By.xpath("./.."));
        String parentCss = parent.getAttribute("class");
        System.out.println("parent css: " + parentCss);

        return parentCss.contains("open");
    }

    private void expandOrganisationChooser() {
        if (isOrganisationChooserExpanded()) {
            return;
        }

        waitAndClick(organisationChooserBy, DEFAULT_TIMEOUT);

//        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(organisationChooserBy)));
//        driver.findElement(organisationChooserBy).click();
    }

    private void foldOrganisationChooser() {
        if (!isOrganisationChooserExpanded()) {
            return;
        }

        waitAndClick(organisationChooserBy, DEFAULT_TIMEOUT);

//        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(organisationChooserBy)));
//        driver.findElement(organisationChooserBy).click();
    }

    private void waitAndClick(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
        driver.findElement(by).click();
    }

}
