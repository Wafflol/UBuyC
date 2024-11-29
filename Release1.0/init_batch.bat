@echo off
echo Starting Website
echo Building
cd ..\UBuyC
./gradlew -p ..\UBuyC build
echo starting up
./gradlew -p ..\UBuyC bootRun
pause
