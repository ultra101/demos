package drivers.phantomjs;

import common.NullValueExceptionHandler;
import common.PropertyReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Valentin
 * Date: 3/24/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhantomJSDemo {

    public static String PHANTOMJS_EXECUTABLE_PATH_PROPERTY;
    public PropertyReader propertyReader;
    private WebDriver driver;
    protected static DesiredCapabilities caps;

    public PhantomJSDemo() throws NullValueExceptionHandler{
        propertyReader = new PropertyReader(".//config//run.properties");
        this.PHANTOMJS_EXECUTABLE_PATH_PROPERTY = propertyReader.get("PHANTOMJS_EXECUTABLE_PATH_PROPERTY");
    }

    @BeforeMethod
    public void setUp(){
        caps = new DesiredCapabilities();
        System.out.println(PHANTOMJS_EXECUTABLE_PATH_PROPERTY);

        // Enable screenshot functionality
        caps.setCapability("takesScreenshot", true);

        // Fetch configuration parameters
        // "phantomjs_exec_path"
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,PHANTOMJS_EXECUTABLE_PATH_PROPERTY);

        /* Disable "web-security", enable all possible "ssl-protocols" and "ignore-ssl-errors" for PhantomJSDriver
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
                "--web-security=false",
                "--ssl-protocol=any",
                "--ignore-ssl-errors=true"
        });
        */
        driver = new PhantomJSDriver(caps);
    }

    @Test
    public void getLinksOnGooglePage(){
        driver.get("http://google.bg");

        //Check Google's logo is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='hplogo']")).isDisplayed());

        // Take a screenshot
        try{
            //driver = new Augmenter().augment(driver);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH_mm_ss");
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile,new File(".//Google_"+simpleDateFormat.format(calendar.getTime())+".png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
