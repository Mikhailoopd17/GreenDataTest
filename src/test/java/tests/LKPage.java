package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LKPage {
    private WebDriver myDriver;
    private static final String URL_MATCH = "/release-17/#/";

    @FindBy(xpath = "//div[@class='navbar-header flex-container']")
    private WebElement headerMenu;

    public LKPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.myDriver=driver;
        new WebDriverWait(myDriver, 60)
                .until(ExpectedConditions.visibilityOf(headerMenu));
    }

    public boolean lkPageIsDownload(){
        return myDriver.getCurrentUrl().contains(URL_MATCH);
    }
}
