package support;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import pages.PageUrls;

public class KnowsTheDomain {
    private EventFiringWebDriver webDriver;
    private final PageUrls pageUrls = new PageUrls();

    public EventFiringWebDriver getWebDriver() {
        if (webDriver == null) {
           setFirefoxDriverBin();
           webDriver = new EventFiringWebDriver(new FirefoxDriver());
           webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public PageUrls getPageUrls() {
        return pageUrls;
    }

    private void setFirefoxDriverBin() {
        if (RuntimeEnvironment.isWindows()) {
            System.setProperty("webdriver.gecko.driver",
                "src\\selenium\\geckodriver-v0.16.1-win32\\geckodriver.exe");
        } else if (RuntimeEnvironment.isMac()) {
            System.setProperty("webdriver.gecko.driver",
                    "src/selenium/geckodriver-v0.17.0-macos/geckodriver");
        } else {
            throw new IllegalArgumentException("unsupported platform.");
        }
    }
}
