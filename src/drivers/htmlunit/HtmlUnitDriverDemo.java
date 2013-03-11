package drivers.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valentin
 * Date: 3/11/13
 * Time: 1:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlUnitDriverDemo {

    WebDriver driver;
    HtmlUnitDriver jdriver;

    @BeforeMethod
    public void setUp(){
        driver = new HtmlUnitDriver();
        //jdriver = new HtmlUnitDriver();
        //jdriver.setJavascriptEnabled(true);
    }

    @Test
    public void sampleTest(){
        driver.get("http://www.dir.bg");
        WebElement link = driver.findElement(By.linkText("Dir.bg"));

        Assert.assertEquals(link.getText(),"Dir.bg");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
