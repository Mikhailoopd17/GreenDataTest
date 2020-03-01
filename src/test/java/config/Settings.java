package config;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Settings {
    protected static ChromeDriver driver;
    public static final String URL = "https://gdcloud.ru/release-17/auth/login";

    @BeforeSuite
    public void setConnect() {
        try {
            System.setProperty("webdriver.chrome.driver", new File("./src/test/resources/chromedriver.exe").getCanonicalPath());
            //ставим опции Eager
            ChromeOptions opt = new ChromeOptions();
            opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(opt);
            //System.out.println("Driver is ready");
            Util u = new Util();
            u.setRootScreenshotsDir();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }
    }

    @BeforeMethod
    public void loadPage(){
        try{
            driver.get(URL);
            //System.out.println("Page URL: "+ URL+ "is open, testing begin");
        }
        catch (Exception e){
            System.out.println("Error! Page don`t open! "+ e.getMessage());
        }
    }

//    @BeforeClass
//    public void infoClassB(){
//        System.out.println("Check Class is beginning");
//    }
//    @AfterClass
//    public void infoClassA(){
//        System.out.println("Check Class is finished");
//    }

    @AfterSuite
    public void close(){
        if(driver != null)
            driver.quit();
        //System.out.println("Check suite is finished");
    }


    public void reConnect(){
        driver.close();
        setConnect();
        loadPage();
    }

    public void openNewPage(String url) {
        String script = "window.open('"+url+"')";
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript(script);

        String newPage =null;
        String page = driver.getWindowHandle();
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        Set <String> allPages = driver.getWindowHandles();
        allPages.remove(page);
        for (String p : allPages) {
            newPage = p;
        }

        driver.switchTo().window(newPage);
    }


}
