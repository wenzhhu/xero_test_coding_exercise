package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {
    private static final int LOGIN_TIMEOUT = 15; // seconds

    @FindBy(id = "email")
    public WebElement emailElement;

    @FindBy(id = "password")
    public WebElement passwordElement;

    @FindBy(id = "submitButton")
    public WebElement submitButtonElement;

    public LoginPage(WebDriver driver, PageUrls pageUrls) {
        super(driver, pageUrls);
    }

    public DashBoardPage login(String email, String password) {
        driver.get(pageUrls.getLoginPageUrl());
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        submitButtonElement.click();

        WebDriverWait wait = new WebDriverWait(driver, LOGIN_TIMEOUT);
        try {
            wait.until(ExpectedConditions.urlToBe(pageUrls.getDashboardPageUrl()));
            return new DashBoardPage(driver, pageUrls);
        } catch (TimeoutException e) {
            throw new RuntimeException("unable to login with email: " + email + " and password: " + password, e);
        }

    }
}
