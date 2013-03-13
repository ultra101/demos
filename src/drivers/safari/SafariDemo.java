package drivers.safari;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The SafariDriver is implemented as a Safari browser extension.
 * The driver inverts the traditional client/server relationship
 * and communicates with the WebDriver client using WebSockets.
 * Starting with Selenium 2.30.0, the SafariDriver comes bundled with the Selenium server.
 */
public class SafariDemo {

    private WebDriver driver;

    private static boolean isSupportedPlatform(){
        Platform currentPlatform = Platform.getCurrent();
        return Platform.MAC.is(currentPlatform) || Platform.WINDOWS.is(currentPlatform);
    }

    @BeforeMethod
    public void setup(){
        if(isSupportedPlatform()){
            driver = new SafariDriver();
        }else {
            throw new SkipException("Skipping – This is not ready for testing ");
        }
    }

    @Test
    public void sampleTest(){
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        new WebDriverWait(driver,3).until(ExpectedConditions.titleIs("webdriver - Google Търсене"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
