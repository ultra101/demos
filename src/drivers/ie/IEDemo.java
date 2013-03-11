package drivers.ie;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * change the Protected Mode settings in the browser to be the same,
 * either enabled or disabled, it doesn't matter which, for all zones.
 *
 * Do NOT use:
 * InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
 *
 * To run the Internet Explorer driver on a remote machine,
 * use the Java standalone remote server in connection with your language binding's equivalent of RemoteWebDriver.
 */
public class IEDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\Selenium\\BrDrivers\\ie\\32bit\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }

    @Test
    public void internetExplorerDemo(){
        driver.get("http://www.dir.bg");
        Assert.assertEquals(driver.getTitle(),"Dir.bg - Българският Интернет портал!");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
