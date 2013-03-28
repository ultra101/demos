package handling.iframe;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-26
 * Time: 0:37
 * To change this template use File | Settings | File Templates.
 */
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

        //driver.findElement(By.cssSelector("a.topmenu[href='/loggedin/eMarketing/index.php']")).click();

        Actions builder = new Actions(driver);
        WebElement eMarketingMenu = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("a.topmenu[href='/loggedin/eMarketing/index.php']")));
        builder.moveToElement(eMarketingMenu).build().perform();
        //Thread.sleep(5000);

        //Waiting for an element's visibility
        WebElement createMailingListMenu = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("a[href*='eMarketing/createlist']")));
        builder.moveToElement(createMailingListMenu).build().perform();
        createMailingListMenu.click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("iframe#innerframe1"))).click();

        /*try{
            WebElement createMailListIFrame = (new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.cssSelector("iframe#innerframe1"));
                }
            });
            Assert.assertTrue(createMailListIFrame.isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("Element not found!!");
            e.printStackTrace();
        }*/
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
