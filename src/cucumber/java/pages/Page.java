package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    protected static final int DEFAULT_TIMEOUT = 25; // seconds

    protected final WebDriver driver;
    protected final PageUrls pageUrls;

    public Page(WebDriver driver, PageUrls pageUrls) {
        this.driver = driver;
        this.pageUrls = pageUrls;
        PageFactory.initElements(driver, this);
    }

    final protected void waitAndClick(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
}
