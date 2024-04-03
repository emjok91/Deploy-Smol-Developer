@echo off
setlocal

set DIR_NAME=%~dp0%
set GRADLE_USER_HOME=%DIR_NAME%.gradle

if "%JAVA_HOME%" == "" (
    set JAVA_EXE=java.exe
) else (
    set JAVA_EXE=%JAVA_HOME%/bin/java.exe
)

@rem Execute Gradle
"%JAVA_EXE%" -Dorg.gradle.appname=%APP_BASE_NAME% -classpath "%APP_HOME%/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
