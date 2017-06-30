package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.accounts.ChartOfAccountsPage;

public class DashBoardPage extends Page {
    private static final int DEFAULT_TIMEOUT = 15; // seconds

    private static final By organisationChooserBy = By.className("org-name");

    public DashBoardPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
      //  driver.get(pageUrls.getDashboardPageUrl());
    }

    public void selectOrganisation(String organisationName) {
        if (organisationName.equals(getCurrentOrganisation())) {
            return;
        }


        try {
            driver.get(pageUrls.getDashboardPageUrl());

            waitAndClick(organisationChooserBy, DEFAULT_TIMEOUT);

            System.err.println("!!!just clicked");
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);



//            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(organisationChooserBy)));
//            wait.until(ExpectedConditions.(By.className("xn-h-org"), By.linkText(organisationName)));
         //   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName))));
            waitAndClick(By.className("xn-h-org"), By.linkText(organisationName), DEFAULT_TIMEOUT);
//            By organisationNameBy = By.xpath("//div[@class='xn-h-org open']//a[text()='" + organisationName + "']");

//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(organisationNameBy));
//
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(organisationNameBy));
//
//            wait.until(ExpectedConditions.elementToBeClickable(organisationNameBy));
        } catch (TimeoutException e) {
//            try {
//                Thread.sleep(500 * 1000);
//            } catch (InterruptedException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
            throw new RuntimeException("unable to select organisation: " + organisationName, e);
        }

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
        allOrganisations.stream().forEach(e -> deleteAllTestBankAccounts(e));
    }

    private List<String> getAllOrganisations() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("org-name"))));
        driver.findElement(By.className("org-name")).click();
        return driver.findElement(By.className("xn-h-org")).findElements(By.tagName("li")).stream()
                .map(e -> e.findElement(By.tagName("a")).getText()).collect(Collectors.toList());
    }

    private void deleteAllTestBankAccounts(String organisation) {
        System.err.println("delete accounts for " + organisation);
        selectOrganisation(organisation);
        new ChartOfAccountsPage(driver, pageUrls).deleteAllTestBankAccounts();;
    }

    private void waitAndClick(By by, int seconds) {
        System.err.println("signle waitAndClick...");
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement e = driver.findElement(by);

        Actions builder = new Actions(driver);
        builder.moveToElement(e).click();
      //  driver.findElement(by).click();
        System.err.println("doen signle waitAndClick...");
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
        String currentOrganisation = driver.findElement(organisationChooserBy).getText();
        System.err.println("getCurrentOrganisation: " + currentOrganisation);
        return currentOrganisation;
    }

}
