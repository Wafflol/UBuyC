@echo off
echo Starting Website
echo Building
cd /d ..\UBuyC
gradlew -p ..\UBuyC build
echo starting up
gradlew -p ..\UBuyC bootRun
