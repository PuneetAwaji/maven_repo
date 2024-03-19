package sg.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import sg.driver.CucumberTestRunner;
import java.io.File;

public class ReportUtils extends CucumberTestRunner {
    /********************************************
     * Method Name      : startExtentReport()
     * Purpose          : It will create extent report html file
     *
     ********************************************/
    public ExtentReports startExtentReport(String fileName){
        String reportPath = null;
        File objReportPath = null;
        File objScreenshot = null;
        ExtentReports extent = null;
        try{
            reportPath = System.getProperty("user.dir") + "/target/extent-report";

            objReportPath = new File(reportPath);
            if(!objReportPath.exists()){
                objReportPath.mkdirs();
            }

            objScreenshot = new File(screenshotLocation);
            if(!objScreenshot.exists()){
                objScreenshot.mkdirs();
            }

            extent = new ExtentReports(reportPath + "\\" + fileName + ".html", false);
            extent.addSystemInfo("Host Name", System.getProperty("os.name"));
            extent.addSystemInfo("User Name", System.getProperty("user.name"));
            extent.addSystemInfo("Environment", appInd.getPropDataByKey().getProperty("environment"));
            extent.addSystemInfo("Java Version", System.getProperty("java.version"));
            extent.loadConfig(new File(System.getProperty("user.dir") + "//extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. " + e);
            return null;
        }finally {
            reportPath = null;
            objReportPath = null;
            objScreenshot = null;
            extent = null;
        }
    }



    /********************************************
     * Method Name      : endExtentReport()
     * Purpose          : It end extent report & writes the report to the html file
     *
     ********************************************/
    public void endExtentReport(ExtentTest test){
        try{
            extent.endTest(test);
            extent.flush();
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. " + e);
        }
    }



    /********************************************
     * Method Name      : captureScreenshot()
     * Purpose          : It will capture the screenshot
     *
     ********************************************/
    public String captureScreenshot(WebDriver oBrowser){
        String resFilePath = null;
        File objSrcFile = null;
        File objDestFile = null;
        try{
            resFilePath = screenshotLocation + "//screenshot_" + appInd.getDateTime("hhmmss") + ".png";
            objSrcFile = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.FILE);
            objDestFile = new File(resFilePath);
            FileHandler.copy(objSrcFile, objDestFile);
            return resFilePath;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. " + e);
            return null;
        }
    }



    /********************************************
     * Method Name      : writeResult()
     * Purpose          : It will capture the screenshot
     *
     ********************************************/
    public void writeResult(WebDriver oBrowser, String status, String strDescription){
        try{
            switch(status.toLowerCase()){
                case "pass":
                    test.log(LogStatus.PASS, strDescription);
                    break;
                case "fail":
                    test.log(LogStatus.FAIL, strDescription, test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "info":
                    test.log(LogStatus.INFO, strDescription);
                    break;
                case "warning":
                    test.log(LogStatus.WARNING, strDescription);
                    break;
                case "screenshot":
                    test.log(LogStatus.PASS, strDescription, test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "exception":
                    test.log(LogStatus.FATAL, strDescription, test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                default:
                    System.out.println("Invalid status '"+status+"' was provided");
            }
        }catch(Exception e){
            System.out.println("Exception in 'writeResult()' method. " + e);
        }
    }
}
