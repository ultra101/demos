package handling.iframe;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class IFrameDemo{

    WebDriver driver;
    String url="https://dev.eiseverywhere.com/login.php";

    /*public IFrameDemo(){
        super(null);
    } */

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }


    @Test
    public void iframeDemo() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("vganchev");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vganchev1");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        Actions builder = new Actions(driver);

        //Wait for eMarketing menu to be available
        WebElement eMarketingMenu = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("a.topmenu[href='/loggedin/eMarketing/index.php']")));
        builder.moveToElement(eMarketingMenu).build().perform();

        //Waiting for an element's visibility
        WebElement createMailingListMenu = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("a[href*='eMarketing/createlist']")));

        createMailingListMenu.click();

        //Wait for "Create Mailing List" iframe to be visible
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try{
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("innerframe1"));
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }

        //Generate random name and type it for the mailing list name
        Double mailListName= new Random().nextDouble();
        driver.findElement(By.cssSelector("input.shadow")).sendKeys(mailListName.toString());
        //Click on Submit button
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement editListDetailsListDetailsNameAndStatus = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("input[name=name]")));
        //Check mailing list name
        Assert.assertEquals(mailListName.toString(),editListDetailsListDetailsNameAndStatus.getAttribute("value"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
