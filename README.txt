The description of Ant, Maven, and Gradle integration is available at https://docs.parasoft.com/display/JTEST1040/Integrating+with+Build+Systems

This document describes how to perform static analysis, run tests, and collect coverage information with Jtest - using the "demo" project shipped with the product.

Please note that the analysis of this project is configured with the settings specified in the demo.properties file located in the [INSTALL]/examples/demo directory.
The demo.properties file is used by Ant, Maven and Gradle build scripts. The following command line examples are intended to be executed from the "demo" project directory.


Prerequisites
-------------------------------------------------
1. Set up the Jtest license in jtestcli.properties in the Jtest installation directory.


Jtest data file
-------------------------------------------------
1. Run the "Recommended Rules" test configuration to perform static analysis directly (without any build system) - using the following command:
   Windows:
     ..\..\jtestcli.exe -config "builtin://Recommended Rules" -data demo.data.json -report report
   UNIX:
     ../../jtestcli -config "builtin://Recommended Rules" -data demo.data.json -report report


Ant
-------------------------------------------------
1. Make sure that you have "ant" available on your path.

2. Run "Demo Configuration" to perform static analysis and collect coverage for unit tests:

     ant -file jtest.xml -Djtest.config="builtin://Demo Configuration"

   The "demo" project will be built and unit tests will be run. The Jtest Plugin for Ant collects source code compilation data,
   and unit test results with coverage to perform analysis and generate the report.

   Note:

   To only run static analysis, use the following command:

     ant -file jtest.xml jtest-sae

   To only run unit tests, use the following command:

     ant -file jtest.xml jtest-utc

   Configurations are specified in jtest.xml - see "jtest", "jtest-sae", or "jtest-utc" targets.


Maven
-------------------------------------------------
1. Make sure that you have "mvn" available on your path.
2. Configure your Maven setup by following the Jtest User Guide:
   https://docs.parasoft.com/display/JTEST1040/Configuring+the+Jtest+Plugin+for+Maven

3. Run "Demo Configuration" to perform static analysis and collect coverage for unit tests:

     mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Demo Configuration"

   The "demo" project will be built and unit tests will be run. The Jtest Plugin for Maven will collect source code compilation data,
   and test results with coverage to perform analysis and generate the report.

   Note:

   To only run static analysis, use the following command:

     mvn jtest:jtest

   The "Recommended Rules" test configuration is used by default.

   To only run unit tests, use the following command:

     mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Unit Tests"


Gradle
-------------------------------------------------
1. Configure the installed Jtest package or add the desired settings to the "jtest" block of your buildscript.

2. Run "Demo Configuration" to perform static analysis and collect coverage for unit tests:

     gradlew clean jtest-agent test jtest -Djtest.config="builtin://Demo Configuration"

   The "demo" project will be built and Junit tests will be run. The Jtest Plugin for Gradle will collect
   source code compilation data, and test results to perform analysis and generate report.

   Note:

   To only run static analysis, use the following command:

     gradlew clean assemble jtest

   "Recommended Rules" configuration is used by default.

   To only run unit tests, use the following command:

     gradlew clean jtest-agent test jtest -Djtest.config="builtin://Unit Tests"


=================================================

Change-Based Testing

Change-based testing is supported for Maven and Gradle.

Windows:

1. Run the tests with Maven or Gradle to collect the baseline data about the tests and coverage:
   mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Unit Tests" -Djtest.report=cbt
   or
   gradlew clean jtest-agent test jtest -Djtest.config="builtin://Unit Tests" -Djtest.report=cbt

   Note: As a result, the report.xml and coverage.xml files will be created in the 'cbt' subfolder.

2. Modify a source file from the test scope:
   src\main\java\examples\mock\FileExample.java

3. Run the following command to execute the tests affected by your change:
   mvn clean cbt:affected-tests test -Dparasoft.coverage.file=cbt/coverage.xml -Dparasoft.test.file=cbt/report.xml
   or
   gradlew clean affectedTests test -Dparasoft.coverage.file=cbt/coverage.xml -Dparasoft.test.file=cbt/report.xml --no-daemon

   Tests that aren't affected by your code modification are not executed.

UNIX:

1. Run the tests with Maven or Gradle to collect the baseline data about the tests and coverage:
   mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Unit Tests" -Djtest.report=cbt
   or
   ./gradlew clean jtest-agent test jtest -Djtest.config="builtin://Unit Tests" -Djtest.report=cbt

   Note: As a result, the report.xml and coverage.xml files will be created in the 'cbt' subfolder.

2. Modify a source file from the test scope:
   src/main/java/examples/mock/FileExample.java

3. Run the following command to execute the tests affected by your change:
   mvn clean cbt:affected-tests test -Dparasoft.coverage.file=cbt/coverage.xml -Dparasoft.test.file=cbt/report.xml
   or
   ./gradlew clean affectedTests test -Dparasoft.coverage.file=cbt/coverage.xml -Dparasoft.test.file=cbt/report.xml --no-daemon

   Tests that aren't affected by your code modification are not executed.


For more information see:
   https://docs.parasoft.com/display/JTEST1040/Testing+and+Analysis+with+Maven
   https://docs.parasoft.com/display/JTEST1040/Testing+and+Analysis+with+Gradle

=================================================

Collecting application coverage

Windows:

1. Build application and collect data necessary for monitoring
     ant -file jtest.xml clean jtest-monitor
     or
     mvn clean package jtest:monitor    
     or
     gradlew clean assemble jtest-monitor
     
   Note: As a result you should get monitor.zip file.

2. Run the application and collect the coverage data
     
   a) Unpack the monitor.zip archive into the "demo" directory (the subdirectory "monitor" will be created).
      ant:
        Archive path: parasoft\jtest-monitor\monitor.zip
      mvn:
        Archive path: target\jtest\monitor\monitor.zip
      gradle:
        Archive path: build\jtest\monitor.zip
     
   b) Run agent.bat
      cd monitor
      agent.bat
      cd ..
      
   c) Run the application using the Java VM argument generated in point b)
      ant
        java -cp demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine
      mvn:
        java -cp target\Demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine
      gradle:
        java -cp build\libs\demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine

   d) Perform several actions using "Stack Machine Example" application:
      - Insert 123 number into "Input" field
      - press "push" button 5 times
      - press "+", "-", "x" and "/" buttons
      - exit application

3. Generate the coverage report

     ..\..\jtestcli.exe -config "builtin://Calculate Application Coverage" -staticcoverage monitor\static_coverage.xml -runtimecoverage monitor\runtime_coverage

     Coverage details are available in report.html

UNIX:

1. Build the application and collect the data necessary for monitoring
     ant -file jtest.xml clean jtest-monitor
     or
     mvn clean package jtest:monitor    
     or
     ./gradlew clean build jtest-monitor
     
   Note: As a result you should get monitor.zip file.

2. Run the application and collect the coverage data
   
   a) Unpack monitor.zip archive into demo directory (subdirectory monitor will be created)
      ant:
        unzip ./parasoft/jtest-monitor/monitor.zip
      mvn: 
        unzip ./target/jtest/monitor/monitor.zip
      gradle:
        unzip ./build/jtest/monitor.zip
     
   b) Run agent.sh
      ./monitor/agent.sh
      
   c) Run application using Java VM argument generated in point b)
      ant
        java -cp ./demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine
      mvn:
        java -cp ./target/Demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine
      gradle:
        java -cp ./build/libs/demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine

   d) Perform few actions using "Stack Machine Example" application
      - Insert 123 number into "Input" field
      - press "push" button 5 times
      - press "+", "-", "x" and "/" buttons
      - exit application

3. Generate coverage report

     ../../jtestcli -config "builtin://Calculate Application Coverage" -staticcoverage ./monitor/static_coverage.xml -runtimecoverage ./monitor/runtime_coverage

     Coverage details are available in report.html


For more information see https://docs.parasoft.com/display/JTEST1040/Application+Coverage