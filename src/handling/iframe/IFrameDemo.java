package handling.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
public class IFrameDemo {

    WebDriver driver;
    String url="https://dev.eiseverywhere.com/login.php";

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @Test
    public void iframeDemo(){
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("vganchev");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vganchev1");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        WebElement menu_emarketing_createMailingList = driver.findElement(By.xpath("//a[contains(text(),'Create mailing list')][1]"));
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Create mailing list')][1]")));
        menu_emarketing_createMailingList.click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
