package sg.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import sg.driver.CucumberTestRunner;

public class Hooks extends CucumberTestRunner {
    @Before
    public void setUp(Scenario scenario){
        try{
            test = extent.startTest(scenario.getName());
            oBrowser = appInd.launchBrowser(appInd.getPropDataByKey().getProperty("browserName"));
            Assert.assertNotNull(oBrowser, "The browser object is still null");
        }catch(Exception e){
            System.out.println("Exception in 'setUp()' method. " + e);
        }
    }


    @After
    public void tearDown(Scenario scenario){
        String scenarioName = null;
        try{
            if(scenario.isFailed()){
                scenarioName = scenario.getName().replace(" ", "_");
                TakesScreenshot ts = (TakesScreenshot) oBrowser;
                byte[] screen = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screen, "image/png", scenarioName);
            }
            reports.endExtentReport(test);
            oBrowser.quit();
        }catch(Exception e){
            System.out.println("Exception in 'setUp()' method. " + e);
        }
    }
}
