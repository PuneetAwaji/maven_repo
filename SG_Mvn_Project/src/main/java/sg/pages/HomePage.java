package sg.pages;

import org.openqa.selenium.By;

public class HomePage {
    public static By obj_HomePage_Header = By.xpath("//td[@class='pagetitle']");
    public static By obj_SaveChanges_Button = By.xpath("//input[@id='SubmitTTButton']");
    public static By obj_TASKS_Menu = By.xpath("//div[text()='TASKS']");
    public static By obj_USERS_Menu = By.xpath("//div[text()='USERS']");
    public static By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
    public static By obj_Shortcut_Dialog = By.xpath("//div[@id='gettingStartedShortcutsMenuWrapper']");
    public static By obj_Shortcut_Close_Button = By.xpath("//div[@id='gettingStartedShortcutsMenuCloseId']");
}
