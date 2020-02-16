package tests;

import config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Tester extends Settings {


    public static void tryToLogin(String log, String pas){
        try{
            WebElement login = driver.findElement(By.xpath("//div[@class='control-group']/input[@name='login']"));
            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

            login.clear();
            login.sendKeys(log);
            password.clear();
            password.sendKeys(pas);

            driver.findElement(By.id("login_button")).click();

            System.out.println("try log is ok");
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public static boolean checkLogin(){
        try {
            if (driver.getCurrentUrl().equals(URL)) {
                System.out.println("Try to login is failed");
                return false;
            } else {
                new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='navbar-header flex-container']")));
                driver.get(URL);
                System.out.println("Try to login is ok!");
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

    }

    public static void waitError(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='error']"))));
            System.out.println("waiting");
        }
        catch (Exception e){
            System.out.println("don`t waiting");
        }
    }
}

