package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private String url = "https://qa.eiseverywhere.com/login.php";
    @FindBy(name = "username")
    @CacheLookup
    private WebElement usernameInput;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement passwordInput;

    @FindBy(className = "loginSubmit")
    @CacheLookup
    private WebElement loginButton;

    public void load(){
        this.driver.get(url);
    }

    public void close(){
        this.driver.close();
    }
    public LoginPage(){
        driver = new FirefoxDriver();
        PageFactory.initElements(driver, this);
    }

    public void login(String usernameValid, String passwordValid){
        usernameInput.sendKeys(usernameValid);
        passwordInput.sendKeys(passwordValid);
        loginButton.click();
    }
}
