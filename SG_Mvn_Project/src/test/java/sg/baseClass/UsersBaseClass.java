package sg.baseClass;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sg.driver.CucumberTestRunner;
import sg.pages.HomePage;
import sg.pages.UsersPage;
import java.util.List;
import java.util.Map;

public class UsersBaseClass extends CucumberTestRunner {
    /*****************************************
     * method Name     : userNavigatesTheURL()
     *
     *
     ******************************************/
    public boolean userNavigatesTheURL(WebDriver oBrowser, String strURL){
        try{
            return appDep.navigateURL(oBrowser, strURL);
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'userNavigatesTheURL()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : verifyUserLoginsInWithValidCredentials()
     *
     *
     ******************************************/
    public boolean verifyUserLoginsInWithValidCredentials(WebDriver oBrowser, String userName, String password){
        try{
            return appDep.loginToApplication(oBrowser, userName, password);
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserLoginsInWithValidCredentials()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : createNewUser()
     *
     *
     ******************************************/
    public boolean createNewUser(WebDriver oBrowser, String firstName, String lastName, String email
            , String userName, String password, String retypePassword){
        try{
            appInd.clickObject(oBrowser, HomePage.obj_USERS_Menu);
            appInd.clickObject(oBrowser, UsersPage.obj_AddUser_Button);
            appInd.waitForElement(oBrowser, UsersPage.obj_CreateUser_Button, "Clickable", "", 10);
            reports.writeResult(oBrowser, "Screenshot", "'Add User' screen opened successful");
            appInd.setObject(oBrowser, UsersPage.obj_Users_FirstName_Edit, firstName);
            appInd.setObject(oBrowser, UsersPage.obj_Users_LastName_Edit, lastName);
            appInd.setObject(oBrowser, UsersPage.obj_Users_Email_Edit, email);
            appInd.setObject(oBrowser, UsersPage.obj_Users_UserName_Edit, userName);
            appInd.setObject(oBrowser, UsersPage.obj_Users_Password_Edit, password);
            appInd.setObject(oBrowser, UsersPage.obj_Users_RetypePassword_Edit, retypePassword);
            appInd.clickObject(oBrowser, UsersPage.obj_CreateUser_Button);
            userNameCreated = lastName + ", " + firstName;
            appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)), "Clickable", "", 10);
            appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)), "Text", userNameCreated, 10);
            reports.writeResult(oBrowser, "Screenshot", "The new user '"+userNameCreated+"' created successful");
            reports.writeResult(oBrowser, "Pass", "The new user created successful");
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'createNewUser()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : createNewUser()
     *
     *
     ******************************************/
    public boolean createNewUser(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> userData = null;
        try{
            userData = dataTable.asMaps(String.class, String.class);
            for(int i=0; i<userData.size(); i++){
                appInd.clickObject(oBrowser, HomePage.obj_USERS_Menu);
                appInd.clickObject(oBrowser, UsersPage.obj_AddUser_Button);
                appInd.waitForElement(oBrowser, UsersPage.obj_CreateUser_Button, "Clickable", "", 10);
                reports.writeResult(oBrowser, "Screenshot", "'Add User' screen opened successful");
                appInd.setObject(oBrowser, UsersPage.obj_Users_FirstName_Edit, userData.get(i).get("User_FirstName"));
                appInd.setObject(oBrowser, UsersPage.obj_Users_LastName_Edit, userData.get(i).get("User_LastName"));
                appInd.setObject(oBrowser, UsersPage.obj_Users_Email_Edit, userData.get(i).get("User_Email"));
                appInd.setObject(oBrowser, UsersPage.obj_Users_UserName_Edit, userData.get(i).get("User_UserName"));
                appInd.setObject(oBrowser, UsersPage.obj_Users_Password_Edit, userData.get(i).get("User_Password"));
                appInd.setObject(oBrowser, UsersPage.obj_Users_RetypePassword_Edit, userData.get(i).get("User_RetypePassword"));
                appInd.clickObject(oBrowser, UsersPage.obj_CreateUser_Button);
                userNames.add(userData.get(i).get("User_LastName") + ", " + userData.get(i).get("User_FirstName"));
                appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))), "Clickable", "", 10);
                appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))), "Text", userNames.get(i), 10);
                reports.writeResult(oBrowser, "Screenshot", "The new user '"+userNames.get(i)+"' created successful");
                appInd.verifyElementPresent(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))));
                reports.writeResult(oBrowser, "Pass", "The new user created successful");
            }
            return true;
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'createNewUser()' method");
            return false;
        }
    }


    /*****************************************
     * method Name     : verifyUserCreatedSuccessful()
     *
     *
     ******************************************/
    public boolean verifyUserIsDeletedSuccessful(WebDriver oBrowser){
        String blnSuccess = null;
        try{
            for(int i=0; i<userNames.size(); i++){
                appInd.clickObject(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))));
                appInd.waitForElement(oBrowser, UsersPage.obj_SaveChanges_Button, "Clickable", "", 10);
                appInd.waitForElement(oBrowser, UsersPage.obj_DeleteUser_Button, "Clickable", "", 10);
                reports.writeResult(oBrowser, "Screenshot", "Before deleting the user");
                appInd.clickObject(oBrowser, UsersPage.obj_DeleteUser_Button);
                Thread.sleep(2000);
                oBrowser.switchTo().alert().accept();
                Thread.sleep(2000);
                appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))), "Invisibility", "", 10);
                reports.writeResult(oBrowser, "Pass", "The user '"+userNames.get(i)+"' is getting deleted");

                if(appInd.verifyElementNotPresent(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNames.get(i))))){
                    reports.writeResult(oBrowser, "Screenshot", "After deleting the user");
                    reports.writeResult(oBrowser, "Pass", "The user '"+userNames.get(i)+"' is deleted successful");
                    blnSuccess+=true;
                }else{
                    reports.writeResult(oBrowser, "Fail", "Failed to delete the user '"+userNames.get(i)+"'");
                    blnSuccess+=false;
                }
            }

            if(blnSuccess.contains("false")){
                return false;
            }else{
                return true;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserIsDeletedSuccessful()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : verifyUserCreatedSuccessful()
     *
     *
     ******************************************/
    public boolean verifyUserCreatedSuccessful(WebDriver oBrowser){
        try{
            boolean blnRes = appInd.verifyElementPresent(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)));
            if(blnRes){
                reports.writeResult(oBrowser, "Pass", "The new user '"+userNameCreated+"' was created successful");
                return true;
            }else{
                reports.writeResult(oBrowser, "Fail", "Failed to create the new user '"+userNameCreated+"'");
                return true;
            }
        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserCreatedSuccessful()' method");
            return false;
        }
    }



    /*****************************************
     * method Name     : verifyUserDeletedSuccessful()
     *
     *
     ******************************************/
    public boolean verifyUserDeletedSuccessful(WebDriver oBrowser){
        try{
            appInd.clickObject(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)));
            appInd.waitForElement(oBrowser, UsersPage.obj_SaveChanges_Button, "Clickable", "", 10);
            appInd.waitForElement(oBrowser, UsersPage.obj_DeleteUser_Button, "Clickable", "", 10);
            reports.writeResult(oBrowser, "Screenshot", "Before deleting the user: " + userNameCreated);
            appInd.clickObject(oBrowser, UsersPage.obj_DeleteUser_Button);
            Thread.sleep(2000);
            oBrowser.switchTo().alert().accept();
            Thread.sleep(2000);
            appInd.waitForElement(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)), "Invisibility", "", 10);
            reports.writeResult(oBrowser, "Pass", "The user '"+userNameCreated+"' is getting deleted");

            if(appInd.verifyElementNotPresent(oBrowser, By.xpath(UsersPage.obj_UserName_Link.replace("%s", userNameCreated)))){
                reports.writeResult(oBrowser, "Screenshot", "After deleting the user: " + userNameCreated);
                reports.writeResult(oBrowser, "Pass", "The user '"+userNameCreated+"' is deleted successful");
                return true;
            }else{
                reports.writeResult(oBrowser, "Fail", "Failed to delete the user '"+userNameCreated+"'");
                return false;
            }

        }catch(Exception e){
            reports.writeResult(oBrowser, "Exception", "Exception in 'verifyUserDeletedSuccessful()' method");
            return false;
        }
    }
}
