package chromedriver;

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

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-5
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class ChromeDemo {
    WebDriver driver;



    @BeforeMethod
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        options.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
        System.setProperty("webdriver.chrome.driver", "E:\\Tools\\Selenium\\BrDrivers\\chrome\\chromedriver.exe");
        driver=new ChromeDriver(options);
        driver.get("https://qa.eiseverywhere.com/login.php");
    }

    @Test
    public void testExamples(){
        WebElement loginButton=driver.findElement(By.xpath("//input[@type='submit']"));
        Assert.assertEquals("Login",loginButton.getAttribute("value"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
