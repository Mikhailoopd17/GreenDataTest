package config;

import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ListenerClass extends TestListenerAdapter {

    public void onTestStart(ITestResult tr){
        log("Test started...");
    }

    public void onTestSuccess(ITestResult tr){
        log("Test '"+ tr.getName()+"' passed");
        //log(tr.getTestClass());
    }

    public void onTestFailure(ITestResult tr) {
        log("Test '" + tr.getName() + "' failed");
    }

    public void onTestSkipped(ITestResult tr) {
        log("Test '" + tr.getName() + "' SKIPPED");
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);
    }
}
