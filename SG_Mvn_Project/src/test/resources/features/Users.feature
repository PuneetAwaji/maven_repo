Feature: User Module test scripts

  @Regression001
  Scenario: Verify create and delete user functionality1
    Given User navigates the URL
    Then User perform login with valid credentials
    And User verify login is successful
    Then User creates new user in the actiTime
      | User_FirstName | User_LastName | User_Email       | User_UserName | User_Password | User_RetypePassword |
      | SG1            | user1         | sg1.user1@sg.com | sguser1       | Mercury1      | Mercury1            |
      | SG2            | user2         | sg2.user2@sg.com | sguser2       | Mercury1      | Mercury1            |
    Then verify user is deleted successful
    And User logouts from the application


  @Regression002
  Scenario Outline: Verify create and delete user functionality2
    Given User navigates the "<URL>" URL
    Then User perform login with valid "<UserName>" and "<Password>" credentials
    And User verify login is successful
    Then User creates new user in the actiTime with "<User_FirstName>", "<User_LastName>", "<User_Email>", "<User_UserName>", "<User_Password>" and "<User_RetypePassword>"
    And Verify user is created successful
    When User perform delete user in actiTime
    And User logouts from the application

    Examples:
      | URL                       | UserName | Password | User_FirstName | User_LastName | User_Email       | User_UserName | User_Password | User_RetypePassword |
      | http://localhost/login.do | admin    | manager  | SG1            | user1         | sg1.user1@sg.com | sg1user1      | Mercury1      | Mercury1            |
      | http://localhost/login.do | admin    | manager  | SG2            | user2         | sg2.user2@sg.com | sg2user2      | Mercury1      | Mercury1            |

