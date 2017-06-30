@bankAccounts
Feature: manage bank accounts
    organizations should be able to manage their bank accounts

    Background:
        Given I have logined as user wenzhhu@gmail.com with password Dlm@1003
        And I have deleted all test bank accounts under all organisations
        And I have navigated to page Accounts/Bank Accounts

    @Positive @Lengthy
    Scenario Outline: add "ANZ (NZ)" bank accounts for any Xero organisations
        Given I have chosen <organisation>
        When I try to add an new bank account with <bank name>, <account name>, <account number>, <currency> and <type>
        Then I should see the newly added bank account displayed on page Accounts/Bank Accounts with correct <bank name>, <account name>, <account number>, <currency> and <type>

        Examples:
            | organisation | bank name | account name                   | account number | currency | type                  |
            | Stephen Hu   | ANZ (NZ)  | test account Everyday (SH)     | 1234           | NZD      | Everyday (day-to-day) |
            | Stephen Hu   | ANZ (NZ)  | test account Loan (SH)         | 1234           | NZD      | Loan                  |
            | Stephen Hu   | ANZ (NZ)  | test account Term Deposit (SH) | 1234           | NZD      | Term Deposit          |
            | Stephen Hu   | ANZ (NZ)  | test account Credit Card (SH)  | 1234           | NZD      | Credit Card           |
            | Stephen Hu   | ANZ (NZ)  | test account Other (SH)        | 1234           | NZD      | Other                 |
            | Test 2       | ANZ (NZ)  | test account Everyday (T2)     | 1234           | NZD      | Everyday (day-to-day) |
            | Test 2       | ANZ (NZ)  | test account Loan (T2)         | 1234           | NZD      | Loan                  |
            | Test 2       | ANZ (NZ)  | test account Term Deposit (T2) | 1234           | NZD      | Term Deposit          |
            | Test 2       | ANZ (NZ)  | test account Credit Card (T2)  | 1234           | NZD      | Credit Card           |
            | Test 2       | ANZ (NZ)  | test account Other (T2)        | 1234           | NZD      | Other                 |

    @Positive @Quick
    Scenario Outline: add "ANZ (NZ)" bank accounts for any Xero organisations
        Given I have chosen <organisation>
        When I try to add an new bank account with <bank name>, <account name>, <account number>, <currency> and <type>
        Then I should see the newly added bank account displayed on page Accounts/Bank Accounts with correct <bank name>, <account name>, <account number>, <currency> and <type>

        Examples:
            | organisation | bank name | account name                   | account number | currency | type                  |
            | Stephen Hu   | ANZ (NZ)  | test account Everyday (SH)     | 1234           | NZD      | Everyday (day-to-day) |
#            | Test 2       | ANZ (NZ)  | test account Everyday (T2)     | 1234           | NZD      | Everyday (day-to-day) |
