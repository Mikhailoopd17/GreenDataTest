package tests;

import config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static config.Settings.URL;

public class LoginPage {

    private WebDriver myDriver;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(id = "login_button")
    private WebElement buttonToLogin1;

    @FindBy(id = "login_button_domain")
    private WebElement buttonToLogin2;

    @FindBy(id = "login_button_current")
    private WebElement buttonToLogin3;

    @FindBy(id = "error")
    private WebElement errorMessage;

    @FindBy(css = "input:required")
    private WebElement informMessage;

    @FindBy(id = "remember")
    private WebElement chboxRemember;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.myDriver = driver;
    }

    public boolean isLoginPage(){
        return  myDriver.getCurrentUrl().equals(URL);
    }

    public void toLogin(String log, String pas) {
        System.out.println("try login to: -"+log+", -"+pas);
        login.sendKeys(log);
        password.sendKeys(pas);

        buttonToLogin1.click();
        System.out.printf("Click button %s\n", buttonToLogin1.getAttribute("id"));

        informMessage(log, login);
        informMessage(pas, password);
        errorMessage();
    }


    public void toLoginCurrentUser(){
        buttonToLogin3.click();
        System.out.printf("Click button %s\n", buttonToLogin3.getAttribute("id"));

        errorMessage();
    }

    public void toLoginDomain(String log, String pas){
        System.out.println("Try login to domain: -"+log+", -"+pas);

        login.sendKeys(log);
        password.sendKeys(pas);
        buttonToLogin2.click();
        errorMessage();
    }

    public void informMessage(String string, WebElement element){
        if(string.equals("")) {
            if (Boolean.parseBoolean(element.getAttribute("required"))) {
                Assert.assertEquals(element.getAttribute("validationMessage"), "Заполните это поле.", "Message text control");
                System.out.println("Inform message is displayed to element: "+element.getAttribute("id"));
            }
            else
                System.out.printf("Input label %s is not required", string);
        }
    }

    public void errorMessage() {
        try{
            new WebDriverWait(myDriver, 2)
                    .until(ExpectedConditions.visibilityOf(errorMessage));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
            System.out.printf("Error message '%s' is displayed\n", errorMessage.getText());
        }
        catch (Exception e){
            System.out.println("Error message is not displayed");
        }

    }

    public void clickRememberMe(){
        chboxRemember.click();
        System.out.println("Checkbox 'remember me' is checked");
    }

    public void checkLoginText(String log){
        String value = login.getAttribute("value");
        if(!value.isEmpty()) {
            Assert.assertEquals(value, log, "login text do not equals");
            System.out.println("login text is equals input value");
        }
        else
            Assert.fail("Input login text is empty");
    }


}
