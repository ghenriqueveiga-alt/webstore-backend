@echo off
cd /d C:\Users\Henrique\Documents\Dev\webstore\webstore-backend
set JAR=C:\Users\Henrique\.gradle\caches\modules-2\files-2.1\com.mysql\mysql-connector-j\9.4.0\8f6c66269048fbd2316b7b45d75898ebee44986f\mysql-connector-j-9.4.0.jar
set CP=build\classes\java\test;build\classes\java\main;build\resources\main;%JAR%
echo Starting scan at %date% %time% > scan-output.log
java -cp "%CP%" com.hvs.webstore.back.EscanearESalvarCortes >> scan-output.log 2>> scan-error.log
echo Finished at %date% %time% >> scan-output.log
