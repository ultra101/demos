package drivers.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.BeforeClass;

/**
 * Created with IntelliJ IDEA.
 * User: valentin
 * Date: 13-3-5
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public class RunInChildProcess {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService(){
        //service = new ChromeDriverService.Builder().usi
    }

}
