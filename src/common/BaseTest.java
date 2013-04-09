package common;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    //WebDriver driver;

    public boolean isElementPresent(WebDriver driver,By by, String searchTerm) {
        try {
            driver.findElement(by).sendKeys(searchTerm);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
