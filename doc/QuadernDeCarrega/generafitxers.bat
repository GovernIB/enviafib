
mkdir fitxers

del /Q .\fitxers\*.*

xcopy /Y /I "..\(CAT) Manual Instal.laci* EnviaFIB.odt" .\fitxers

xcopy /Y /I ..\..\scripts\bbdd\oracle\01_sequences.sql .\fitxers
xcopy /Y /I ..\..\scripts\bbdd\oracle\02_tables.sql .\fitxers
xcopy /Y /I ..\..\scripts\bbdd\oracle\03_constraints.sql .\fitxers
xcopy /Y /I ..\..\scripts\bbdd\oracle\04_indexes.sql .\fitxers
xcopy /Y /I ..\..\scripts\bbdd\oracle\06_grants.sql .\fitxers
xcopy /Y /I ..\..\scripts\bbdd\oracle\07_initial_data.sql .\fitxers

xcopy /Y /I ..\..\scripts\config\enviafib.properties .\fitxers
xcopy /Y /I ..\..\scripts\config\enviafib.system.properties .\fitxers

xcopy /I /Y ..\..\scripts\datasources\enviafib-oracle-ds.xml .\fitxers\

ren .\fitxers\enviafib-oracle-ds.xml  enviafib-ds.xml

xcopy /Y /I ..\..\scripts\security\keycloak-subsystem.xml .\fitxers

xcopy /Y /I ..\..\enviafib-ear\target\enviafib.ear .\fitxers
