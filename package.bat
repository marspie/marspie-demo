@echo off
echo [INFO] Build and Package demo Project.

cd %~dp0
call mvn clean package -Dmaven.test.skip=true
pause