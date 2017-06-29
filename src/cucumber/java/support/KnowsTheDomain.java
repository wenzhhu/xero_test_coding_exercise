package support;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import pages.PageUrls;

public class KnowsTheDomain {
    private EventFiringWebDriver webDriver;
    private final PageUrls pageUrls = new PageUrls();

    public EventFiringWebDriver getWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.gecko.driver",
                    "src\\selenium\\geckodriver-v0.16.1-win32\\geckodriver.exe");
           webDriver = new EventFiringWebDriver(new FirefoxDriver());
           webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public PageUrls getPageUrls() {
        return pageUrls;
    }
}
