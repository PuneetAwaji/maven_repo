package sg.common;

import org.openqa.selenium.WebDriver;
import sg.driver.CucumberTestRunner;
import sg.pages.HomePage;
import sg.pages.LoginPage;

public class AppDependentMethods extends CucumberTestRunner {
    /********************************************
     * Method Name      : navigateURL()
     * Purpose          : It will load/navigate the URL
     *
     ********************************************/
    public boolean navigateURL(WebDriver oBrowser, String strURL) {
        try{
            oBrowser.navigate().to(strURL);
            appInd.waitForElement(oBrowser, LoginPage.obj_Login_Button, "Clickable", "", 10);
            reports.writeResult(oBrowser, "Screenshot", "The url '"+strURL+"' is navigated successful");
            return appInd.compareExactMatch(oBrowser, oBrowser.getTitle(), "actiTIME - Login");
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'navigateURL()' method. " + e);
            return false;
        }
    }



    /********************************************
     * Method Name      : loginToApplication()
     * Purpose          : It will login to actiTime application and validates the same
     *
     ********************************************/
    public boolean loginToApplication(WebDriver oBrowser, String userName, String password){
        try{
            appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, userName);
            appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, password);
            appInd.clickObject(oBrowser, LoginPage.obj_Login_Button);
            appInd.waitForElement(oBrowser, HomePage.obj_SaveChanges_Button, "Clickable", "", 10);
            reports.writeResult(oBrowser, "Screenshot", "Login to actiTime was successful");
            boolean blnRes = appInd.verifyOptionalElementPresent(oBrowser, HomePage.obj_Shortcut_Dialog);
            if(blnRes) appInd.clickObject(oBrowser, HomePage.obj_Shortcut_Close_Button);
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'loginToApplication()' method. " + e);
            return false;
        }
    }


    /********************************************
     * Method Name      : logoutFromApplication()
     * Purpose          : It will logout from the actiTime application and validates the same
     *
     ********************************************/
    public boolean logoutFromApplication(WebDriver oBrowser) {
        try{
            appInd.clickObject(oBrowser, HomePage.obj_Logout_Link);
            appInd.waitForElement(oBrowser, LoginPage.obj_Login_Button, "Clickable", "", 10);
            reports.writeResult(oBrowser, "Screenshot", "Logout from actiTime was successful");
            return appInd.verifyElementPresent(oBrowser, LoginPage.obj_LoginLogo_Img);
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'logoutFromApplication()' method. " + e);
            return false;
        }
    }
}
