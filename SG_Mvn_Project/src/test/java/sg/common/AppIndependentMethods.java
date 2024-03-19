package sg.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sg.driver.CucumberTestRunner;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AppIndependentMethods extends CucumberTestRunner {
    /********************************************
     * Method Name      : getPropDataByKey()
     * Purpose          : It will read the data from Prop file based on Key
     *
     ********************************************/
    public Properties getPropDataByKey(){
        FileInputStream fin = null;
        Properties prop = null;
        try{
            fin = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\QA.properties");
            prop = new Properties();
            prop.load(fin);
            return prop;
        }catch(Exception e){
            System.out.println("Exception in 'getPropDataByKey()' method. " + e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                prop = null;
            }catch(Exception e){
            }
        }
    }


    /********************************************
     * Method Name      : getDateTime()
     * Purpose          : It will create the timeStamp
     *
     ********************************************/
    public String getDateTime(String dateFormat){
        Date date = null;
        SimpleDateFormat sdf = null;
        try{
            date = new Date();
            sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        }catch(Exception e){
            System.out.println("Exception in 'getDateTime()' method. " + e);
            return null;
        }
        finally {
            date = null;
            sdf = null;
        }
    }



    /********************************************
     * Method Name      : clickObject()
     * Purpose          : It will click the element and validates the same
     *
     ********************************************/
    public boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                reports.writeResult(oBrowser, "Pass", "The Element '"+String.valueOf(objBy)+"' was clicked successful");
            }
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'clickObject()' method. " + e);
            return false;
        }finally {
           oEle = null;
        }
    }


    /********************************************
     * Method Name      : setObject()
     * Purpose          : It will set the value in the element specified and validates the same
     *
     ********************************************/
    public boolean setObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try {
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(strData);
                reports.writeResult(oBrowser, "Pass", "The value '"+strData+"' was set in the element '"+String.valueOf(objBy)+"'");
                return true;
            }
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'setObject()' method. " + e);
            return false;
        }finally {
            oEle = null;
        }
    }


    /********************************************
     * Method Name      : compareExactMatch()
     * Purpose          : It will compare the actual and expected values
     *
     ********************************************/
    public boolean compareExactMatch(WebDriver oBrowser, String actualValue, String expectedValue){
        try{
            if(actualValue.equalsIgnoreCase(expectedValue)){
                reports.writeResult(oBrowser, "Pass", "The actual '"+actualValue+"' & expected '"+expectedValue+"' values are matched");
                return true;
            }else{
                reports.writeResult(oBrowser, "Fail", "Mis-match in the actual '"+actualValue+"' & expected '"+expectedValue+"' values");
                return false;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'compareExactMatch()' method. " + e);
            return false;
        }
    }



    /********************************************
     * Method Name      : verifyText()
     * Purpose          : It will compare the actual and expected values
     *
     ********************************************/
    public boolean verifyText(WebDriver oBrowser, By objBy, String objectType, String attributeName, String expectedValue){
        WebElement oEle = null;
        String actualValue = null;
        Select oSelect = null;
        try{
            oEle = oBrowser.findElement(objBy);
            switch(objectType.toLowerCase()){
                case "text":
                    actualValue = oEle.getText();
                    break;
                case "attribute":
                    actualValue = oEle.getAttribute(attributeName);
                    break;
                case "dropdown":
                    oSelect = new Select(oEle);
                    actualValue = oSelect.getFirstSelectedOption().getText();
                    break;
                default:
                    reports.writeResult(oBrowser, "Fail", "Invalid object Type '"+objectType+"' was specified");
                    return false;
            }

            return compareExactMatch(oBrowser, actualValue, expectedValue);
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyText()' method. " + e);
            return false;
        }finally {
            oEle = null;
            actualValue = null;
            oSelect = null;
        }
    }



    /********************************************
     * Method Name      : verifyElementPresent()
     * Purpose          : It will verify that element is present in the DOM
     *
     ********************************************/
    public boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                reports.writeResult(oBrowser, "Pass", "The Element '"+String.valueOf(objBy)+"' was present in the DOM");
                return true;
            }else{
                reports.writeResult(oBrowser, "Fail", "The Element '"+String.valueOf(objBy)+"' was NOT present in the DOM");
                return false;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyElementPresent()' method. " + e);
            return false;
        }finally {
            oEles = null;
        }
    }



    /********************************************
     * Method Name      : verifyElementNotPresent()
     * Purpose          : It will verify that element is removed from the DOM
     *
     ********************************************/
    public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                reports.writeResult(oBrowser, "Fail", "The Element '"+String.valueOf(objBy)+"' was still present in the DOM");
                return false;
            }else{
                reports.writeResult(oBrowser, "Pass", "The Element '"+String.valueOf(objBy)+"' was NOT present in the DOM");
                return true;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyElementNotPresent()' method. " + e);
            return false;
        }finally {
            oEles = null;
        }
    }



    /********************************************
     * Method Name      : verifyOptionalElementPresent()
     * Purpose          : It will verify that the optional element is present in the DOM
     *
     ********************************************/
    public boolean verifyOptionalElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyOptionalElementPresent()' method. " + e);
            return false;
        }finally {
            oEles = null;
        }
    }



    /********************************************
     * Method Name      : launchBrowser()
     * Purpose          : It will open/launch the specified browser (Chrome, Edge and Firefox)
     *
     ********************************************/
    public WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                default:
                    reports.writeResult(null, "Fail", "Invalid browser '"+browserName+"' was specified.");
                    return null;
            }

            if(oDriver != null){
                oDriver.manage().window().maximize();
                reports.writeResult(oDriver, "Pass", "The '"+browserName+"' browser is launched successful");
                return oDriver;
            }else{
                reports.writeResult(oDriver, "Fail", "Failed to launch '"+browserName+"' browser.");
                return null;
            }
        }catch(Exception e){
            reports.writeResult(oDriver, "Exception", "Exception in 'launchBrowser()' method. " + e);
            return null;
        }
    }



    /********************************************
     * Method Name      : waitForElement()
     * Purpose          : It will wait for the element for specific condition
     *
     ********************************************/
    public boolean waitForElement(WebDriver oBrowser, By objBy, String waitReason, String textToAppear, long timeOut){
        WebDriverWait oWait = null;
        try{
            oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(timeOut));

            switch(waitReason.toLowerCase()){
                case "clickable":
                    oWait.until(ExpectedConditions.elementToBeClickable(objBy));
                    break;
                case "visibility":
                    oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
                    break;
                case "text":
                    oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, textToAppear));
                    break;
                case "invisibility":
                    oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
                    break;
                default:
                    reports.writeResult(oBrowser, "Fail", "Invalid wait condition '"+waitReason+"' was provided");
                    return false;
            }
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'waitForElement()' method. " + e);
            return false;
        }
        finally {
            oWait = null;
        }
    }
}
