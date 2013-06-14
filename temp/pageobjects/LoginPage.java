package tests.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LoginPage extends LoadableComponent<LoginPage> {

    public WebElement username;
    public WebElement password;
    @FindBy(css = ".loginSubmit")
    @CacheLookup
    public WebElement loginSubmit;
    protected WebDriver driver;

    private String url = "https://qa.eiseverywhere.com/login.php";
    private String title = "etouches - Client Login";


    public LoginPage(){
        //driver = new RemoteWebDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        this.driver.get(url);
    }

    @Override
    protected void isLoaded(){
        //Assert.assertTrue(driver.getTitle().equals(title));
    }

    public void loginWithValidCredentials(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginSubmit.click();
    }

    public void close() {
        this.driver.close();
    }
}
