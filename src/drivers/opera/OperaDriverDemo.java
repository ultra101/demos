package drivers.opera;

import com.opera.core.systems.OperaDriver;
import com.opera.core.systems.OperaProfile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valentin
 * Date: 3/7/13
 * Time: 3:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class OperaDriverDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        OperaProfile profile = new OperaProfile();
        profile.preferences().set("Geolocation","Enable geolocation", false);
        driver=new OperaDriver(profile);
        driver.get("http://www.dir.bg");
    }

    @Test
    public void testSample(){
        WebElement link = driver.findElement(By.linkText("http://dir.bg"));
        link.click();
        Assert.assertEquals(driver.getTitle(),"Dir.bg - Българският Интернет портал!");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
