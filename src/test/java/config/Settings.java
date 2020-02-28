package config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.io.File;
import java.util.logging.Logger;

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
            System.out.println("Driver is ready");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }
    }

    @BeforeTest
    public void LoadPage(){
        try{
            driver.get(URL);
            System.out.println("Page URL: "+ URL+ "is open, testing begin");
        }
        catch (Exception e){
            System.out.println("Error! Page don`t open! "+ e.getMessage());
        }
    }

    @BeforeClass
    public void infoClassB(){
        System.out.println("Check Class is beginning");
    }
    @AfterClass
    public void infoClassA(){
        System.out.println("Check Class is finished");
    }

    @AfterSuite
    public void close(){
        if(driver != null)
            driver.quit();
        System.out.println("Check suite is finished");
    }
}
