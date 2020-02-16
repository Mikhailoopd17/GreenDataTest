
package config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.logging.Logger;

import static org.testng.Reporter.log;

public class Settings {
    protected Logger logger;
    protected static ChromeDriver driver;
    protected static final String URL = "https://gdcloud.ru/release-17/auth/login";
    //protected static final String URLLK = "https://gdcloud.ru/release-17/#/";

    @BeforeSuite
    public void setConnect() {
        try {
            System.setProperty("webdriver.chrome.driver", new File("./src/test/resources/chromedriver.exe").getCanonicalPath());
            ChromeOptions opt = new ChromeOptions();
            opt.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(opt);
            log ("Driver is ready");
            //System.out.println("Driver is ready");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }

    }

    @BeforeClass
    public void LoadPage(){
        try{
            driver.get(URL);
            logger.info("Connected to URL: "+ URL);
//            System.out.println("Connected to URL: "+ URL);
//            System.out.println("Page is open, testing is begin...");
        }
        catch (Exception e){
            System.out.println("Error! Page don`t open! "+ e.getMessage());
        }
    }


    @AfterSuite
    public void close(){
        if(driver != null)
            driver.quit();
        System.out.println("Check suite is finished");
    }


}
