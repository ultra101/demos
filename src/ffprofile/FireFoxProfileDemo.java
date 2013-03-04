package ffprofile;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FireFoxProfileDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "no,en-us,en");
        profile.setPreference("browser.startup.homepage","https://qa.eiseverywhere.com/login.php");
        profile.setAcceptUntrustedCertificates(true);
        FirefoxBinary binary =new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        driver=new FirefoxDriver(binary,profile);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFirefoxProfile(){
        System.out.println(System.getProperty("webdriver.firefox.bin"));
        System.out.println("Page title: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "etouches - Client Login");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
