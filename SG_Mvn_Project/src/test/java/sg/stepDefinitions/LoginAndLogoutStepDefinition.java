package sg.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import sg.driver.CucumberTestRunner;
import sg.pages.HomePage;

public class LoginAndLogoutStepDefinition extends CucumberTestRunner {
    @Given("Verify user opens the browser")
    public void verifyUserOpensTheBrowser() {
        Assert.assertTrue(loginAndLogoutBaseclass.verifyUserLaunchesTheBrowser(oBrowser));
    }


    @And("User navigates the URL")
    public void userNavigatesTheURL() {
        Assert.assertTrue(loginAndLogoutBaseclass.verifyUserNavigatesURL(oBrowser));
    }

    @Then("User perform login with valid credentials")
    public void userPerformLoginWithValidCredentials() {
        Assert.assertTrue(loginAndLogoutBaseclass.verifyUserLoginsInWithValidCredentials(oBrowser));
    }

    @And("User verify login is successful")
    public void userVerifyLoginIsSuccessful() {
        Assert.assertTrue(loginAndLogoutBaseclass.verifyLoginIsSuccessful(oBrowser));
    }

    @When("User verify That logout link is display")
    public void userVerifyThatLogoutLinkIsDisplay() {
        Assert.assertTrue(appInd.verifyElementPresent(oBrowser, HomePage.obj_Logout_Link));
    }

    @Then("User logouts from the application")
    public void userLogoutsFromTheApplication() {
        Assert.assertTrue(loginAndLogoutBaseclass.verifyUserLogoutFromApplication(oBrowser));
    }
}
