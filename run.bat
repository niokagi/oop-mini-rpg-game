@echo off
rem
CALL .\gradlew.bat classes

rem
java -cp build/classes/java/main core.GameManager