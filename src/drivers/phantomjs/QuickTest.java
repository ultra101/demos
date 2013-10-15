package drivers.phantomjs;

import common.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-10-15
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class QuickTest {

    //public static String PHANTOMJS_EXECUTABLE_PATH_PROPERTY;
    public PropertyReader propertyReader;
    private WebDriver driver;
    protected static DesiredCapabilities caps;
    //public static final File PHANTOMJS_EXE = new File("E:/etouches/tools/phantomjs-1.9.2-windows/phantomjs.exe");


    @Test
    public void shouldMakeManyAssertions() throws Exception {

        //System.setProperty("phantomjs.binary.path", propertyReader.get("PHANTOMJS_EXECUTABLE_PATH_PROPERTY"));


        PhantomJSDriverService service = new PhantomJSDriverService.Builder()
                .usingPhantomJSExecutable(new File("E:/etouches/tools/phantomjs-1.9.2-windows/phantomjs.exe"))
                .usingAnyFreePort()
                .build();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("takesScreenshot", false);
        PhantomJSDriver driver = new PhantomJSDriver(service, caps);

        /*DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("phantomjs.binary.path",PHANTOMJS_EXE.getAbsolutePath());
        PhantomJSDriver driver = new PhantomJSDriver(caps);*/

        System.out.println(System.getProperty("phantomjs.binary.path"));
        driver.get("http://www.google.bg");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='hplogo']")).isDisplayed());
        /*for (int i = 0; i < 20000; i++) {
            System.out.println("iteration: " + i);
            hasLink(driver, "link");
        } */
        driver.quit();
    }

    public static boolean hasLink(WebDriver driver, String linkText) {
        try {
            return driver.findElement(By.linkText(linkText)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
