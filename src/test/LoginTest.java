package test;


import common.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class LoginTest {

    WebDriver driver;
    PropertyReader property;


    @BeforeMethod
    public void startUp(){

    }

    @Test
    public void loginIn() {
        LoginPage loginPage = new LoginPage();
        loginPage.load();
        loginPage.login("vganchev","sk3t-9867");
        loginPage.close();
    }

    @AfterMethod
    public void tearDown(){
        try{
            Thread.sleep(3000);
            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
