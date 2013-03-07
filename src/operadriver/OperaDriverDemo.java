package operadriver;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.WebDriver;
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
        driver=new OperaDriver();
        driver.get("http://www.dir.bg");
    }

    @Test
    public void testSample(){
        Assert.assertEquals(driver.getTitle(),"Dir.bg - Българският Интернет портал!");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
