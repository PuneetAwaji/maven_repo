package sg.baseClass;

import org.openqa.selenium.WebDriver;
import sg.driver.CucumberTestRunner;
import sg.pages.HomePage;

public class LoginAndLogoutBaseClass extends CucumberTestRunner {
    /*****************************************
     * method Name     : verifyUserLaunchesTheBrowser()
     *
     *
     ******************************************/
    public boolean verifyUserLaunchesTheBrowser(WebDriver oBrowser){
        try{
            oBrowser = appInd.launchBrowser("Chrome");
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserLaunchesTheBrowser()' method");
            return false;
        }
    }


    /*****************************************
     * method Name     : verifyUserNavigatesURL()
     *
     *
     ******************************************/
    public boolean verifyUserNavigatesURL(WebDriver oBrowser){
        try{
            return appDep.navigateURL(oBrowser, "http://localhost/login.do");
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserNavigatesURL()' method");
            return false;
        }
    }


    /*****************************************
     * method Name     : verifyUserLoginsInWithValidCredentials()
     *
     *
     ******************************************/
    public boolean verifyUserLoginsInWithValidCredentials(WebDriver oBrowser){
        try{
            return appDep.loginToApplication(oBrowser, "admin", "manager");
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserLoginsInWithValidCredentials()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : verifyLoginIsSuccessful()
     *
     *
     ******************************************/
    public boolean verifyLoginIsSuccessful(WebDriver oBrowser){
        try{
            appInd.verifyElementPresent(oBrowser, HomePage.obj_HomePage_Header);
            appInd.verifyText(oBrowser, HomePage.obj_HomePage_Header, "Text", "", "Enter Time-Track");
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyLoginIsSuccessful()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : verifyLoginIsSuccessful()
     *
     *
     ******************************************/
    public boolean verifyUserLogoutFromApplication(WebDriver oBrowser){
        try{
            return appDep.logoutFromApplication(oBrowser);
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserLogoutFromApplication()' method");
            return false;
        }
    }
}
