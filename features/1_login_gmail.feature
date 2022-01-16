Feature: Log into the email server page

Background:
Given I am on login email page
And I should to be logged out

Scenario: Login with invalid password informations should appear hightlight
When I used invalid user credentials
Then A hightlight should appear

Scenario: Login with valid informations should to get main email page
When I used valid user credentials
Then I should to get access to the main email page
