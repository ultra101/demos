package drivers.chrome;

import common.NullValueExceptionHandler;
import common.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

//import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-5
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class ChromeDemo {

    WebDriver driver;
    PropertyReader property;

    @BeforeMethod
    public void setup() throws NullValueExceptionHandler{

        //Class to manage options specific to ChromeDriver
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        //DesiredCapabilities capabilities= DesiredCapabilities.chrome();
        //capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));

        property = new PropertyReader("./config/run.properties");

        //The server expects you to have Chrome installed in the default location for each system
        // Overriding the Chrome binary location:

        options.setBinary(new File(property.get("chrome.binary")));

        // include the ChromeDriver(chromedriver.exe) location in your PATH environment variable
        //  or specify its location via the webdriver.chrome.driver system property
        System.setProperty("webdriver.chrome.driver", property.get("webdriver.chrome.driver"));

        //create a new ChromeDriver instance
        driver=new ChromeDriver(options);
        driver.get("https://qa.eiseverywhere.com/login.php");
    }

    @Test
    public void testExamples(){
        driver.manage().window().maximize();
        WebElement loginButton=driver.findElement(By.xpath("//input[@type='submit']"));
        Assert.assertEquals("Login",loginButton.getAttribute("value"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
