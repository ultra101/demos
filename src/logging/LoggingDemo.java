package logging;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;

public class LoggingDemo {
    DesiredCapabilities caps = DesiredCapabilities.firefox();
    LoggingPreferences logPref = new LoggingPreferences();
    WebDriver driver;
    @Test
    public void TestMethod(){

        logPref.enable(LogType.DRIVER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPref);
        driver = new FirefoxDriver(caps);
        Logs log =  driver.manage().logs();
        LogEntries entries = log.get(LogType.PROFILER);
        driver.get("http://www.google.com");
        List<LogEntry> entry=entries.getAll();
        ListIterator<LogEntry> iterator=entry.listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
        driver.quit();
    }

}
