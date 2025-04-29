#Author: Muhamad Badru Salam
@login
Feature: Login feature
  I want to login to the web app to access the dashboard

  @positive
  Scenario Outline: Login with valid credential
    Given I want to open the cura web application
    When I click Button Make Appointment
    When I fill the username: <username> and password <password>
    Then I verify the text <successText> showed

    Examples: 
      | username | password           | successText      |
      | John Doe | ThisIsNotAPassword | Make Appointment |

  @negative
  Scenario Outline: Login with invalid credential
    Given I want to open the cura web application
    When I click Button Make Appointment
    When I fill the username: <username> and password <password>
    Then I verify the text <errorText> showed

    Examples: 
      | username  | password           | errorText                                                        |
      | wronguser | ThisIsNotAPassword | Login failed! Please ensure the username and password are valid. |
      | John Doe  | wrongpass          | Login failed! Please ensure the username and password are valid. |
