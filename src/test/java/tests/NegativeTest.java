package tests;

import config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.Tester;

import static tests.Tester.*;


public class NegativeTest extends Settings {
    @DataProvider
    public Object[][] testDataNegative(){
        return new String[][]{
                {"", ""},
                {"11111", ""},
                {"", "22222"},
                {"tester", "К35G3U"},
                {"ыыыыы", "аааааа"},
                {"ывфыв", "аыв34!!"},
                {"!!", "\""},
                {"test", "test"},
                {"tester", "k35g3u"},
                {"tester","K35G3U"},
                {"tеstеr","K35G3U"}, // буква е - в русской раскладке
                {"tester", "К35G3U"} //буква К - в русской раскладке
        };
    }

    @Test(dataProvider = "testDataNegative")
    public void toLogIn2(String log, String pas){
        System.out.println("Negative test is running!");
        tryToLogin(log, pas);
        waitError();
        Assert.assertFalse(checkLogin());
    }
}
