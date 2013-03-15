package drivers.ie;

import common.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The driver supports running 32-bit and 64-bit versions of the browser.
 * The choice of how to determine which "bit-ness" to use in launching the browser depends on
 * which version of the IEDriverServer.exe is launched.
 * If the 32-bit version of IEDriverServer.exe is launched, the 32-bit version of IE will be launched.
 * Similarly, if the 64-bit version of IEDriverServer.exe is launched, the 64-bit version of IE will be launched.
 *
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
    PropertyReader property;

    @BeforeMethod
    public void setUp() throws Exception{
        property = new PropertyReader("./config/run.properties");
        System.setProperty("webdriver.ie.driver", property.get("webdriver.ie.driver"));
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
