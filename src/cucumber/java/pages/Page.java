package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
    protected static final int DEFAULT_TIMEOUT = 25; // seconds

    protected final WebDriver driver;
    protected final PageUrls pageUrls;

    public Page(WebDriver driver, PageUrls pageUrls) {
        this.driver = driver;
        this.pageUrls = pageUrls;
        PageFactory.initElements(driver, this);
    }
}
