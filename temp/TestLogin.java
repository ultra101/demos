package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import tests.pageobjects.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;

public class TestLogin {

    public WebDriver driver;

    @Parameters({"browser", "version", "os"})
    @BeforeClass
    public void setup(String browser, String version,@Optional("WINDOWS") String os) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capability=null;
        capability = gridSetting(browser, version, os);
        driver = new RemoteWebDriver(new URL("http://192.168.66.67:4444/wd/hub"), capability);
    }

    @Test
    public void loginToEtouches(){
        //driver.get("https://qa.eiseverywhere.com/login.php");

        //Create instance of LoginPage and pass the driver
        LoginPage loginPage = new LoginPage();

        //Open Login Page
        loginPage.get();

        //Enter Username & Password
        loginPage.loginWithValidCredentials("automation","1q2w3e4");

        //String pageTitle = driver.getTitle();
        Assert.assertTrue(driver.getTitle().equals("etouches"));
    }

    public DesiredCapabilities gridSetting(String browser, String version, String os){
        DesiredCapabilities capability=null;
        if(browser.equals("firefox")){
            System.out.println("Test scripts running on Firefox");
            capability= DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setVersion(version);
        }
        if(browser.equals("iexplorer")){
            System.out.println("Test scripts running on Internet Explorer");
            capability= DesiredCapabilities.internetExplorer();
            capability.setBrowserName("iexplorer");
            //System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
            capability.setVersion(version);
        }

        if(browser.equals("chrome")){
            System.out.println("Test scripts running on Chrome");
            capability= DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            //System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
            capability.setVersion(version);
        }

        if(browser.equals("safari")){
            System.out.println("Test scripts running on Safari");
            capability= DesiredCapabilities.safari();
            capability.setBrowserName("safari");
            capability.setVersion(version);
        }

        if(browser.equals("opera")){
            System.out.println("Test scripts running on Opera");
            capability= DesiredCapabilities.opera();
            capability.setBrowserName("opera");
            capability.setVersion(version);
        }

        /*if(os.equals("WINDOWS")){
            capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        }
        else if(os.equals("Linux")){
            capability.setPlatform(org.openqa.selenium.Platform.LINUX);
        }
        else{
            capability.setPlatform(org.openqa.selenium.Platform.ANY);
        } */
        return capability;
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
