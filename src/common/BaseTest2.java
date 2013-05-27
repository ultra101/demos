package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseTest2 {
    DesiredCapabilities[] browsers = {DesiredCapabilities.firefox(),DesiredCapabilities.chrome(),DesiredCapabilities.internetExplorer()};
    WebDriver driver; // = new RemoteWebDriver(browsers);
    //Capabilities actualCapabilities = ((RemoteWebDriver) driver).getCapabilities();

    @BeforeTest
    public void setup(){

    }

    @Test
    public void testSearchResults(){
        for(DesiredCapabilities browser : browsers)
        {
            try{
                System.out.println("Testing in Browser: "+browser.getBrowserName());
                driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), browser);
                driver.manage().window().maximize();
                driver.get("http://www.google.bg");
                System.out.println("A test");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
        }

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

