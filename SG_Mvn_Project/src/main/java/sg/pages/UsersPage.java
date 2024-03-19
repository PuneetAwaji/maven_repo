package sg.pages;

import org.openqa.selenium.By;

public class UsersPage {
    public static By obj_AddUser_Button = By.xpath("//div[text()='Add User']");
    public static By obj_Users_FirstName_Edit  = By.xpath("//input[@name='firstName']");
    public static By obj_Users_LastName_Edit  = By.xpath("//input[@name='lastName']");
    public static By obj_Users_Email_Edit  = By.xpath("//input[@name='email']");
    public static By obj_Users_UserName_Edit  = By.xpath("//input[@name='username']");
    public static By obj_Users_Password_Edit  = By.xpath("//input[@name='password']");
    public static By obj_Users_RetypePassword_Edit  = By.xpath("//input[@name='passwordCopy']");
    public static By obj_CreateUser_Button = By.xpath("//span[text()='Create User']");
    public static String obj_UserName_Link = "//div[@class='name']/span[text()='%s']";
    public static By obj_DeleteUser_Button = By.xpath("//button[contains(text(), 'Delete User')]");
    public static By obj_SaveChanges_Button = By.xpath("//span[text()='Save Changes']");
}
