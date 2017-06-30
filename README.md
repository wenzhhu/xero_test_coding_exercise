# xero_test_coding_exercise

Supproted OS / Browser:
-----------------------
- Windows / Firefox the latest (54.0.1) 32-bit
- Mac OS / Firefox the latest (54.0.1)

Verfied on:
-----------
- Windows 7
- Windows 10
- Mac OS 10.11.6

Test steps:
-----------
1. Install the latest JDK 8 (8u131)
2. Install the latest Gradle (4.0)
3. open a CLI window on Windows, or open a terminal on Mac OS
4. set JAVA_HOME to the installed JDK 8
- on Windows, set JAVA_HOME=<PATH_TO_JDK_8>, or
- on Mac, export JAVA_HOME=<PATH_TO_JDK_8>
5. cd <root_dir_of_repo>
6. <gradle_dir>/bin/gradle cucumber -Duser.email=<your_xero_user_email> -Duser.password=<your_xero_user_password>
- Note: it is assumed that there are two organisations under your xero account: "Stephen Hu" and "Test 2"
7. after test completes, test reports can be view at ./out/index.html

Note: The test will do cleanup work automatically (removing bank accounts created during testing), so the test could be run repeatedly without problem.
