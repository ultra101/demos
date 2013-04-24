package performance.navtiming;

import common.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class NewSubmissionTest {

    WebDriver driver;
    PropertyReader property;

    @BeforeMethod
    public void setUp() throws Exception{
        property = new PropertyReader("./config/run.properties");
        System.setProperty("webdriver.ie.driver", property.get("webdriver.ie.driver"));
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(caps);
    }

    @Test
    public void CreateNewSubmissionAndMeasurePerformance(){

        driver.manage().window().maximize();
        driver.get("https://qa.eiseverywhere.com/eselectv2/frontend/index/60147");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        /*js.executeScript("function myFunction(){if(!window.__profiler || window.__profiler.scriptLoaded == true)" +
                "{var d=document,h=d.getElementsByTagName('head')[0]," +
                "s=d.createElement('script'),l=d.createElement('div')," +
                "c=function(){if(l){d.body.removeChild(l)}}," +
                "t=new Date();s.type='text/javascript';" +
                "l.style.cssText='z-index:999;position:fixed;top:10px;" +
                "left:10px;display:inline;width:auto;font-size:14px;" +
                "line-height:1.5em;font-family:Helvetica,Calibri,Arial,sans-serif;" +
                "text-shadow:none;padding:3px 10px 0;" +
                "background:#FFFDF2;box-shadow:0 0 0 3px rgba(0,0,0,.25),0 0 5px 5px rgba(0,0,0,.25);" +
                " border-radius:1px';l.innerHTML='Just a moment';" +
                "s.src='http://kaaes.github.com/timing/timing.js?'+t.getTime();" +
                "s.onload=c;s.onreadystatechange=" +
                "function(){if(this.readyState=='loaded'){c()}};" +
                "d.body.appendChild(l);h.appendChild(s);}" +
                " else if(typeof window.__profiler === 'function') {window.__profiler();}}");*/
        WebElement loginInput = waitForElementPresence(driver,10,By.cssSelector("form>input:nth-child(2)"));

        //Login name
        loginInput.sendKeys("q1@test.com");
        WebElement passwordInput = waitForElementPresence(driver,10,By.cssSelector("form>input:nth-child(4)"));

        //Login password
        passwordInput.sendKeys("1q2w3e");
        WebElement submitButton = waitForElementPresence(driver,10,By.cssSelector("input[type='submit']"));

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //Login as a submitter
        //submitButton.click();
        submitButton.sendKeys(Keys.RETURN);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // Click on Add New submission input
        WebElement addNewSubmission = waitForElementPresence(driver,10,By.xpath("//input[@value='Add new submission +']"));
        //addNewSubmission.click();
        addNewSubmission.sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("c-panel-add-submission")));
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
        Select submissionGroup = new Select(waitForElementPresence(driver, 10, By.name("submissionGroupID")));
        //Select "Main" submission group from the drop down
        submissionGroup.selectByVisibleText("Main");
        Select applicantType = new Select(driver.findElement(By.name("applicantTypeID")));

        //Select "Author" applicant type from the drop down
        applicantType.selectByVisibleText("Author");

        //Type "Test Submission" in Name input
        driver.findElement(By.cssSelector(".form-item")).sendKeys("Test Submission");

        Double loadEventEnd = (Double) js.executeScript("return window.performance.timing.loadEventEnd;");
        //Click on "Save Changes" button
        driver.findElement(By.cssSelector("button[class='yui3-button save-changes']")).sendKeys(Keys.RETURN);

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Double navigationStart = (Double) js.executeScript("return window.performance.timing.navigationStart;");
        // Difference between Load Event End and Navigation Event Start is
        // Page Load Time
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart)/1000 + " seconds.");
        //Assert that "Test Submission" is listed on Submitter's dashboard
        List<WebElement> submissionList = driver.findElements(By.cssSelector(".submissions-list>li"));
        boolean submissionFound=false;
        System.out.println(submissionList.size());
        for(WebElement submission:submissionList){
            System.out.println(submission.getText());
            /*if(submission.getText().contains("Test Submission")){
                submissionFound=true;
                break;
            }*/
        }
        //Assert.assertTrue(submissionFound);
    }

    protected WebElement waitForElementPresence(WebDriver driver,int timeToWait, By locator){
        return (new WebDriverWait(driver,timeToWait)).
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementVisibility(WebDriver driver,int timeToWait, By locator){
        return (new WebDriverWait(driver,timeToWait)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeClickable(WebDriver driver,int timeToWait, By locator){
        return (new WebDriverWait(driver,timeToWait)).
                until(ExpectedConditions.elementToBeClickable(locator));
    }

    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }
}
