package locators;

import common.NullValueExceptionHandler;
import common.PropertyReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valentin
 * Date: 3/25/13
 * Time: 1:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavascriptExecutorDemo {

    protected PropertyReader propertyReader;
    private WebDriver driver;

    @BeforeMethod
    public void setUp()throws NullValueExceptionHandler{
        propertyReader = new PropertyReader("./config/run.properties");
        System.setProperty("webdriver.chrome.driver", propertyReader.get("webdriver.chrome.driver"));
        driver = new ChromeDriver();
    }

    @Test
    public void testJavascriptCalls(){
        driver.get("http://www.google.bg");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        /*
            Test that will call JavaScript code to return title and count of links (that is a count
            of Anchor tags) from a page
         */
        String title = (String) js.executeScript("return document.title");
        Assert.assertEquals("Google",title);
        long links = (Long) js.executeScript("var links = document.getElementsByTagName\n" +
                "('A'); return links.length");
        System.out.println(links);
        Assert.assertEquals(25,links);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
