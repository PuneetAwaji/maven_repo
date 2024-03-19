Feature: Test automation for login and logout functionality

  @Regression
  Scenario: Verify user perform login and logout functionality in actiTime
    Given User navigates the URL
    Then User perform login with valid credentials
    And User verify login is successful
    When User verify That logout link is display
    Then User logouts from the application