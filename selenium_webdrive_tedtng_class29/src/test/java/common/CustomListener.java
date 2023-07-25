package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static common.helpers.CaptureHelpers.captureScreenshot;

public class CustomListener extends BaseSetUp implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {


    }

    @Override
    public void onTestSuccess(ITestResult result) {


    }

    @Override
    public void onTestFailure(ITestResult result) {

        captureScreenshot(driver,result.getMethod().getMethodName());




    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
