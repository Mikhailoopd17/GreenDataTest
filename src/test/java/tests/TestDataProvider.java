package tests;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider
    public Object[][] testData(){
        return new String[][]{
                {"tester","K35G3U"}
        };
    }

    @DataProvider
    public Object[][] testData2(){
        return new String[][]{
                {"","K35G3U"},
                {"null",""},
                {"teer","K35G3U"}
        };
    }
    @DataProvider
    public Object[][] testData3(){
        return new String[][]{
                {"teer","K35G3U"},
        };
    }
}
