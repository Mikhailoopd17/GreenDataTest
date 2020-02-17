package tests;

import config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//класс Tester создан для разделения использования функций страницы и возможности повторного использования
public class Tester extends Settings {
    public static void tryToLogin(String log, String pas){
        try{
            WebElement login = driver.findElement(By.xpath("//div[@class='control-group']/input[@name='login']"));
            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

            System.out.println("try login to: -" +log+", -"+pas);
            login.clear();
            login.sendKeys(log);
            password.clear();
            password.sendKeys(pas);

            driver.findElement(By.id("login_button")).click();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public static boolean checkLogin(){
        try {
            if (driver.getCurrentUrl().equals(URL)) {
                System.out.println("Authentication is failed!");
                return false;
            } else {
                new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='navbar-header flex-container']")));
                driver.get(URL);
                System.out.println("Authentication is OK!");
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error authentication!!! Check log");
            return false;
        }

    }

    //метод для ожидания начала загрузки ЛК или появления сообщения об ошибке
    public static void waitError(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='error']"))));
            System.out.println("Waiting...");
        }
        catch (Exception e){
            System.out.println("Don`t waiting");
        }
    }
}

