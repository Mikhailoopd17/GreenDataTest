package tests;

import config.Settings;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static tests.Tester.*;


public class NegativeTest extends Settings {
    private int counter = 1;

    @DataProvider
    public Object[][] testDataNegative(){
        return new String[][]{
                {"", ""},
                {"", "К35G3U"},
                {"tester", ""},
                {"admin", "admin"},
                {"tester", "К35G3U"},
                {"тестер", "K35G3U"},
                {"еуыеук", "K35G3U"},
                {"\"tester", "K35G3U"},
                {"test er", "K35G3U"},
                {"tester", "k35g3u"},
                {" tester","K35G3U"}, // буквы и цифры в англ расклдаке
                {"tеstеr","K35G3U"}, // буква е - в русской раскладке
                {"tester", "К35G3U"}, //буква К - в русской раскладке
                {"<script>alert('tester')</script>", "К35G3U"},
                {"%cester","К35G3U"}
        };
    }

    @Test(dataProvider = "testDataNegative")
    public void toLogIn2(String log, String pas){
        System.out.println("Starting negative test №"+ counter++);
        tryToLogin(log, pas);
        waitError();
        Assert.assertFalse(checkLogin());
    }
}
