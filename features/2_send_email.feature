Feature: Send Email

Background:
    Given I am in the main page
    When I click on new email button

Scenario: email should be sent if destination email is valid
    When I set a "valid" destination Email
    And I set a subject for the Email
    And I type the body of the email
    And I send the Email
    Then the notification should be: "success"

Scenario: email should be sent if destination email is invalid
    When I set a "invalid" destination Email
    And I set a subject for the Email
    And I type the body of the email
    And I send the Email
    Then the notification should be: "fail"

Scenario: email should be sent if destination email is empty
    When I set a "empty" destination Email
    And I set a subject for the Email
    And I type the body of the email
    And I send the Email
    Then the notification should be: "empty"

    