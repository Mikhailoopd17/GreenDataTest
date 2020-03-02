package tests;


import config.Settings;
import config.Util;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;


public class LoginTests extends Settings{

    //счетчик для тестов
    int count =1;

    //проверка формы login при коректном login и password
    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginCorrect(String log, String pas){
        LoginPage page = new LoginPage(driver);
        page.toLogin(log, pas);
        if (!page.isLoginPage()) {
            LKPage lkPage = new LKPage(driver);
            Assert.assertTrue(lkPage.lkPageIsDownload(), "Error, authentication is failed!");
        } else {
            Assert.fail("Is a login page");
        }
    }
    //проверка при некорректном login / password, при этом проверяется и выводится в консоль сообщение об ошибке
    @Test(dataProvider = "testData2",
            dataProviderClass = TestDataProvider.class)
    public void toLoginUncorrected(String log, String pas) throws IOException, AWTException {
        LoginPage page = new LoginPage(driver);
        page.toLogin(log, pas);

        String nameSS="login_Uncorrect_test_"+(count++)+driver.manage().window().getSize();
        Util util = new Util(nameSS);
        util.takeScreenshot();

        Assert.assertTrue(page.isLoginPage(), "Error! Authentication is passed");
        System.out.println("Authentication is failed");
    }

    //проверка чекбокса "Запомнить меня"
    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void rememberMe(String log, String pas) {
        LoginPage page = new LoginPage(driver);
        page.clickRememberMe();
        page.toLogin(log, pas);
        LKPage lkPage = new LKPage(driver);
        Assert.assertTrue(lkPage.lkPageIsDownload(), "Error, authentication is failed!");
        System.out.println("Authentication is passed");

        reConnect();

        LoginPage pageNew = new LoginPage(driver);
        pageNew.checkLoginText(log);
    }

    //проверка кнопки "войти(текущая учетная запись)" --- не совсем понял ее назначение,
    // предположил что кнопка позволяет войти при уже активной учетной записи (повторный логин в другой вкладке браузера без ввода лог/пароль)
    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginCurrentUser(String log, String pas) throws AWTException, IOException {
        toLoginCorrect(log, pas);

        openNewPage(URL);

        LoginPage page = new LoginPage(driver);
        page.toLoginCurrentUser();
        if (!page.isLoginPage()) {
            LKPage lkPage = new LKPage(driver);
            Assert.assertTrue(lkPage.lkPageIsDownload(), "Error, authentication is failed!");
            System.out.println("Authentication is passed");
        } else {
            Assert.fail("Is a login page, authentication is failed!");
        }

    }

    //проверка кнопки "войти(другая учетная запись)" --- аналогично не совсем понял ее назначение),
    // предположил что кнопка позволяет войти в учетную запись при другой активной учетной записи (повторный логин в другой вкладке браузера с вводом логина и пароля)
    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginDomain(String log, String pas) throws IOException, AWTException {
        toLoginCorrect(log, pas);

        openNewPage(URL);

        LoginPage page = new LoginPage(driver);
        page.toLoginDomain(log, pas);
        if (!page.isLoginPage()) {
            LKPage lkPage = new LKPage(driver);
            Assert.assertTrue(lkPage.lkPageIsDownload(), "Error, authentication is failed!");
            System.out.println("Authentication is passed");
        } else {
            Assert.fail("Is a login page, authentication is failed!");
        }

    }


}