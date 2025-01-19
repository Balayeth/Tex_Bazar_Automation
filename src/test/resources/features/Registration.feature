Feature: Registration

  Scenario: Verify that user can not register with existing number
    Given Go to site
    When Click on 'Registration' button
    Then User should be navigated to 'Create a new account' form page
    When Enter an exiting phone number
    And Click on terms and conditions check box
    And Click on Send OTP button
    Then User should see an error message that contains "This number already exists, Please login"

  Scenario: Verify that user can complete registration with a valid phone no
    Given Go to site
    When Click on 'Registration' button
    Then User should be navigated to 'Create a new account' form page
    When Enter a new phone number
    And Click on terms and conditions check box
    And Click on Send OTP button
    Then User should be navigated to OTP Verification page
    When Enter correct OTP
    And Click on Submit button
    Then User should be navigated to complete registration page
    When Enter Company or user Name
    And Enter six digits password
    And Click "COMPLETE REGISTRATION" button
    Then User should see a success toast message which contains "Successfully logged in"





