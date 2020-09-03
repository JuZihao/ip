@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\functions\DefaultMessages.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\functions\AnalyseCommand.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\datatypes\Task.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\datatypes\Todo.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\datatypes\Deadline.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\datatypes\Event.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\functions\TaskList.java
javac  -cp ..\bin -Xlint:none -d ..\bin ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
