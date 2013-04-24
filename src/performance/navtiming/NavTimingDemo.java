package performance.navtiming;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-4-22
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class NavTimingDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void PageLoadTest(){
        driver.get("https://qa.eiseverywhere.com/eselectv2/frontend/index/60097");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        long loadEventEnd = (Long)js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (Long)js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("Page Load Time is " + (loadEventEnd -
                navigationStart)/1000 + " seconds.");
    }

    @AfterMethod
    public void tearDown(){

    }
}
