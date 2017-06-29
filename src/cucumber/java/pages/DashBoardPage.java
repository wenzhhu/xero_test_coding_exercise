package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage extends Page {
    private static final int DEFAULT_TIMEOUT = 15; // seconds

    @FindBy(className = "org-name")
    public WebElement orgNameSelectElement;

    public DashBoardPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public void selectOrganisation(String organisationName) {
        orgNameSelectElement.click();
        driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName)).click();

        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("xn-h-org")).findElement(By.linkText(organisationName))));
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to select organisation: " + organisationName);
        }
    }

}
