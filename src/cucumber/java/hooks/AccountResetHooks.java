package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import support.KnowsTheDomain;

public class AccountResetHooks {
    private KnowsTheDomain helper;

    public AccountResetHooks(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Before
    public void reset(Scenario scenario) {

    }

}
