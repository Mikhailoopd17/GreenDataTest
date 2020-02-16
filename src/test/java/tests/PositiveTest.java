package tests;
import config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static tests.Tester.*;

public class PositiveTest extends Settings {
    @DataProvider
    public Object[][] testDataPositive(){
        return new String[][]{
                {"tеster","K35G3U"},
                {"tester","K35G3U"},//буквы и цифры в англ раскладке
                {"tester","K35G3U"}  //цифры в русской раскладке
        };
    }

    @Test(dataProvider = "testDataPositive")
    public void toLogIn1(String log, String pas) throws InterruptedException {
        System.out.println("Starting positive test");
        tryToLogin(log, pas);
        waitError();
        Assert.assertTrue(checkLogin());
    }






}
