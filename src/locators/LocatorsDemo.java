package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Valentin
 * Date: 3/19/13
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocatorsDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void locatorSample(){
        driver.get("https://login.yahoo.com/config/login_verify2?&.src=ym");
        WebElement facebookLink = driver.findElement(By.linkText("Facebook"));
        driver.findElement(By.tagName(""));

        System.out.println(facebookLink.getAttribute("href"));
        System.out.println("https://open.login.yahoo.com/openid/yrp/signin?idp=facebook&ts=1363731648&.intl=us&.lang=en%2dUS&.done=http%3A%2F%2Fmail.yahoo.com&rpcrumb=uupBfCZk3%2f7&.src=ym");
        Assert.assertEquals("https://open.login.yahoo.com/openid/yrp/signin?idp=facebook&ts=1363731648&.intl=us&.lang=en%2dUS&.done=http%3A%2F%2Fmail.yahoo.com&rpcrumb=uupBfCZk3%2f7&.src=ym", facebookLink.getAttribute("href"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
