package config;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    String path = new File("/src/test/java/resources/").getCanonicalPath();


    String expectedDir;
    String actualDir;
    String diffDir;
    String resultGifsDir;

    public Util() throws IOException {
    }

    public void setRootScreenshotsDir(){
        expectedDir = path +"\\expected\\";
        actualDir = path +"\\actual\\";
        diffDir = path +"\\diff\\";
        resultGifsDir = path +"\\gifs\\";
        createFolders();
    }

    public void createFolders(){
        createFolder(expectedDir);
        createFolder(actualDir);
        createFolder(diffDir);
        createFolder(resultGifsDir);
    }

    public void createFolder(String path) {


        File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
    }



}
