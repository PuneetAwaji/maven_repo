package sg.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import sg.driver.CucumberTestRunner;

public class UsersSteDefinition extends CucumberTestRunner {
    @Given("User navigates the {string} URL")
    public void userNavigatesTheURL(String strURL) {
        Assert.assertTrue(usersBaseClass.userNavigatesTheURL(oBrowser, strURL));
    }

    @Then("User perform login with valid {string} and {string} credentials")
    public void userPerformLoginWithValidAndCredentials(String userName, String password) {
        Assert.assertTrue(usersBaseClass.verifyUserLoginsInWithValidCredentials(oBrowser, userName, password));
    }

    @Then("User creates new user in the actiTime with {string}, {string}, {string}, {string}, {string} and {string}")
    public void userCreatesNewUserInTheActiTimeWithAnd(String firstName, String lastName, String emailID, String userName, String password, String retypePassword) {
        Assert.assertTrue(usersBaseClass.createNewUser(oBrowser, firstName, lastName, emailID, userName, password, retypePassword));
    }

    @And("Verify user is created successful")
    public void verifyUserIsCreatedSuccessful() {
        Assert.assertTrue(usersBaseClass.verifyUserCreatedSuccessful(oBrowser));
    }

    @When("User perform delete user in actiTime")
    public void userPerformDeleteUserInActiTime() {
        Assert.assertTrue(usersBaseClass.verifyUserDeletedSuccessful(oBrowser));
    }

    @Then("User creates new user in the actiTime")
    public void userCreatesNewUserInTheActiTime(DataTable dataTable) {
        Assert.assertTrue(usersBaseClass.createNewUser(oBrowser, dataTable));
    }

    @Then("verify user is deleted successful")
    public void verifyUserIsDeletedSuccessful() {
        Assert.assertTrue(usersBaseClass.verifyUserIsDeletedSuccessful(oBrowser));
    }
}
