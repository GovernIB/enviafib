
@echo off
setlocal

echo [%date%, %time%] 1>> log.txt

set SERVIDORS=dev.caib.es;intranet.caib.es;afirmades.caib.es:4430;afirmades2.caib.es;pre.firmacloud.com;esbse.caib.es:4430

REM Comprovam que tenim l'executable openssl 
where /q openssl
if ERRORLEVEL 1 (
    echo OpenSSL no està instal·lat o no està dins el PATH. Si empres Cygwin assegurat que C:\cygwin64\bin està al PATH
    exit /B
)

REM Comprova que s'ha definit JAVA_HOME i la versió de la màquina virtual
if NOT DEFINED JAVA_HOME (
	echo Ha de definir la variable JAVA_HOME
	exit /B
)

set PATH=%JAVA_HOME%\bin;%PATH%
for /f tokens^=2-5^ delims^=.-_+^" %%j in ('java -fullversion 2^>^&1') do set "jver=%%j%%k%%l"

if %jver% LSS 160 (
	echo Versió de java no soportada
	exit /b
)

REM Java 11 canvia el directori del fitxer
IF %jver% LSS 1100 (
	set TRUSTSTORE="%JAVA_HOME%\jre\lib\security\cacerts"
) else (
	set TRUSTSTORE="%JAVA_HOME%\lib\security\cacerts"
)


REM cream el directori on davallarem els PEMs
if NOT EXIST pemfiles mkdir pemfiles

echo Els certificats es davallaran a dins el directori "pemfiles" i s'afegiran a %TRUSTSTORE%

for %%s in (%SERVIDORS%) do (  
  REM separam el port si el té posat i processam....
  for /f "tokens=1-2 delims=:" %%p in ("%%s") do call :Processar %%p %%q  
)

REM Anam al final
goto End


	REM Subrutina per processar cada linia de servidor
	:Processar

	rem primer posam el port per defecte si no el té explicit, ja que openssl el requereix
	if [%2]==[] (
		set SERVIDOR=%1:443
	) else (
		set SERVIDOR=%1:%2
	)

	set ALIAS=%1
	set ALIAS=%ALIAS:.=_%
	set PEMFILE=pemfiles\%ALIAS%.pem

	echo ======
	echo Davallant certificat de %SERVIDOR% i guardant-lo a %PEMFILE%
	echo | openssl s_client -showcerts -connect %SERVIDOR% 2>> log.txt | openssl x509 -outform PEM > %PEMFILE%
	for /f %%i in ("%PEMFILE%") do set size=%%~zi
	if %size% EQU 0 (
		echo Error davallant PEM %PEMFILE%
		goto :eof
	)

	keytool -list -alias %ALIAS% -keystore %TRUSTSTORE% -storepass changeit 1>> log.txt 2>&1
	if NOT ERRORLEVEL 1 (
		echo Alias %ALIAS% ja existeix, l'eliminam
		keytool -delete -alias %ALIAS% -keystore %TRUSTSTORE% -storepass changeit 1>> log.txt 2>&1
	)

	echo Importar certificat %PEMFILE% amb l'alias %ALIAS%
	keytool -importcert -noprompt -file %PEMFILE% -alias %ALIAS% -keystore %TRUSTSTORE% -storepass changeit 1>> log.txt 2>&1
	if ERRORLEVEL 1 (
		echo Error incorporant certificat %PEMFILE% amb alias %ALIAS%
	) else (
		echo Certificat %PEMFILE% amb alias %ALIAS% incorporat
	)

	goto :eof

:End

