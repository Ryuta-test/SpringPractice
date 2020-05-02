echo off

rem Current Directory
set CURRENT_DIR=%~dp0

rem Project Path
set PROJECT_PATH=%CURRENT_DIR%/practice_app

echo [INFO] Start of make war file

cd %PROJECT_PATH%

rem remove target Directory
cmd /C mvn clean

rem make war file
cmd /C mvn install

echo [INFO] End of make war file
echo Please press enterKey.
pause >nul
exit
