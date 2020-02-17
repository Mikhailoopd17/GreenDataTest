package tests;
import config.Settings;
import org.testng.Assert;
import org.testng.annotations.*;

import static tests.Tester.*;

public class PositiveTest extends Settings {
    private int counter = 1;
    @DataProvider
    public Object[][] testDataPositive(){
        return new String[][]{
                {"tеster","K35G3U"}, //буква е в русской расладке
                {"tester","K35G3U"}, //буквы и цифры в англ раскладке(исходн)
                {"tester","K35G3U"}  //цифры в русской раскладке
        };
    }

    @Test(dataProvider = "testDataPositive")
    public void toLogIn1(String log, String pas) {
        System.out.println("Starting positive test №"+ counter++);
        tryToLogin(log, pas);
        waitError();
        Assert.assertTrue(checkLogin());
    }






}
