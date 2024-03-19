package sg.driver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import sg.baseClass.LoginAndLogoutBaseClass;
import sg.baseClass.UsersBaseClass;
import sg.common.AppDependentMethods;
import sg.common.AppIndependentMethods;
import sg.common.Datatable;
import sg.reports.ReportUtils;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions
        (
            tags = "@Regression001 or @Regression002 or @Regression",
                glue = {"sg.stepDefinitions"},  //stepDefinitions class PackageName
                features = {"src/test/resources/features"},     //path to your feature files
                dryRun = false, //A cucumber dry run is used to confirm the compilation faults and compile the Step Definition and Feature files.
                monochrome = true,  //true, means that the console output for the Cucumber test are much more readable
                plugin = {
                    "pretty",
                        "html:target/cucumber-report.html",
                        "json:target/cucumber.json"
                }
        )


public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    public static AppIndependentMethods appInd = null;
    public static AppDependentMethods appDep = null;
    public static Datatable datatable = null;
    public static ReportUtils reports = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    public static String screenshotLocation = null;
    public static LoginAndLogoutBaseClass loginAndLogoutBaseclass;
    public static WebDriver oBrowser;
    public static UsersBaseClass usersBaseClass;
    public static String userNameCreated;
    public static List<String> userNames = null;


    @BeforeSuite
    public void loadClasses(){
        try{
            appInd = new AppIndependentMethods();
            appDep = new AppDependentMethods();
            datatable = new Datatable();
            reports = new ReportUtils();
            screenshotLocation = System.getProperty("user.dir") + "/target/extent-report/screen";
            extent = reports.startExtentReport("TestReportFile.html");
            loginAndLogoutBaseclass = new LoginAndLogoutBaseClass();
            usersBaseClass = new UsersBaseClass();
            userNames = new ArrayList<String>();
        }catch(Exception e){
            System.out.println("Exception in 'loadClasses()' method. " + e);
        }
    }


    @DataProvider(parallel = false)
    public Object[][] scenarios(){return super.scenarios();}
}
