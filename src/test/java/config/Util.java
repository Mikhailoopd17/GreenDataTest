package config;




import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;



import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Util extends Settings {
    String screenShotDir = new File("./src/test/resources/shots").getCanonicalPath();

    String actual = screenShotDir +"\\expected\\";
    String expected = screenShotDir +"\\actual\\";
    String difference = screenShotDir +"\\difference\\";
    String name;

    public Util(String name) throws IOException {
        createFolder(screenShotDir);
        createFolder(actual);
        createFolder(expected);
        createFolder(difference);
        this.name = name;
    }

    public void createFolder(String path) {
        File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
    }

    public void takeScreenshot() throws AWTException, IOException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);

        Screenshot screenshot = new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "png", new File(actual+name+".png"));

        if(screenshotDiffIsEmpty(name+".png")) {
            Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expected + name + ".png")));
            ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, screenshot);
            if(diff.getDiffSize()>0){
                File diffFile = new File(difference + name + ".png");
                ImageIO.write(diff.getMarkedImage(), "png", diffFile);
                Assert.fail();
            }
        }
        else {
            ImageIO.write(screenshot.getImage(), "png", new File(expected+name+".png"));
        }
    }

    public boolean screenshotDiffIsEmpty(String name){
        File f = new File(expected+"\\"+name);
        return f.exists();
    }

    public void clearFolders(String path){
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        if(arrFiles!=null){
            for (File f:arrFiles) {
                f.delete();
            }
        }
    }

}
