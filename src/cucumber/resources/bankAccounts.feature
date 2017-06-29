@bankAccounts
Feature: manage bank accounts
    organizations should be able to manage their bank accounts

    Background:
        Given I have logined as user wenzhhu@gmail.com with password Dlm@1003
        And I have navigated to page Accounts/Bank Accounts

    @Positive
    Scenario Outline: add an "ANZ (NZ)" bank account
        When I try to add an new bank account with <bank name>, <account name>, <account number>, <currency> and <type>
        Then I should see the newly added bank account displayed on page Accounts/Bank Accounts with correct <bank name>, <account name>, <account number>, <currency> and <type>

        Examples:
            | bank name | account name  | account number | currency | type  |
            | ANZ (NZ)  | test account  | 12             | NZD      | Other |
    #        | ANZ (NZ)  | test account2 | 12345678       | NZD      | Other |

