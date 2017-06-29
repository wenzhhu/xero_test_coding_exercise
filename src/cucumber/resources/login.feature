@Login
Feature: Login to Xero
    Registered user should be able to login to Xero

    Background:
        Given There is user wenzhhu@gmail.com with password Wqq@1003 already registered in the system:

    @Positive
    Scenario: login succesfully with correct user and password
        When I login with user <wenzhhu@gmail.com> and password <Wqq@1003>
        Then I should be redirected to my dashboard page with my user name <Stephen Hu> on it.
