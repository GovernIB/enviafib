

@echo off
:: uncomment the line bellow if the java.exe is not in the %PATH% 
::PATH %PATH%;%JAVA_HOME%\bin\

java -version 1>nul 2>nul || (
   echo no java installed
   exit /b 2
)

for /f tokens^=2-5^ delims^=.-_+^" %%j in ('java -fullversion 2^>^&1') do (
    echo J = %%j
    echo K = %%k
    echo L = %%l
    echo M = %%m
    set "jver=%%j"
)
set onze=11
IF %jver% LSS 11 (
  echo Java version is too low, echo at least 11 is needed
  exit /b 1
) ELSE (
  call mvn exec:java
)