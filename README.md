# xero_test_coding_exercise

Supproted OS / Browser:
-----------------------
Windows / Firefox the latest (54.0.1) 32-bit
Mac OS / Firefox the latest (54.0.1)

Verfied on:
-----------
Windows 7
Windows 10
Mac OS 10.11.6

Test steps:
1. Install the latest JDK 8 (8u131)
2. Install the latest Gradle (4.0)
3. open a CLI window on Windows, or open a terminal on Mac OS
4. set JAVA_HOME to the installed JDK 8
-- on Windows, set JAVA_HOME=<PATH_TO_JDK_8>
-- on Mac, export JAVA_HOME=<PATH_TO_JDK_8>
5. cd <root_dir_to_workspace>
6. <gradle_dir>/bin/gradle cucumber
7. after test completes, test reports can be view at ./out/index.html
