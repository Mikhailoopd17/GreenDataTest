package tests;


import config.Settings;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;


public class LoginTests extends Settings {

    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginCorrect(String log, String pas) {
        LoginPage page = new LoginPage(driver);
        page.toLogin(log, pas);
        if (!page.isLoginPage()) {
            LKPage lkPage = new LKPage(driver);
            Assert.assertTrue(lkPage.lkPageIsDownload(), "Error, authentication is failed!");
            System.out.println("Authentication is passed");
        } else {
            Assert.fail("Is a login page");
        }

    }

    @Test(dataProvider = "testData2",
            dataProviderClass = TestDataProvider.class)
    public void toLoginUncorrected(String log, String pas) {
        LoginPage page = new LoginPage(driver);
        page.toLogin(log, pas);
        Assert.assertTrue(page.isLoginPage(), "Error, authentication is passed!");
        System.out.println("Authentication is failed");
    }

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

    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginCurrentUser(String log, String pas) throws AWTException {
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


    @Test(dataProvider = "testData",
            dataProviderClass = TestDataProvider.class)
    public void toLoginDomain(String log, String pas) {
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