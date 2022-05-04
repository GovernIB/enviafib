REM Si no volem LOBs en un altre tablespace llavors afegir la següent
REM linia despres de mvn: -Dsqlgenerator.oracle.generatelob=false
mvn exec:java -Dexec.mainClass="org.fundaciobit.genapp.sqlgenerator.SqlGenerator" -Dexec.args="enviafib enviafibPULocal %1%"
