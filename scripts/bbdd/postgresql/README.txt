Instruccions per entendre aquests fitxers:

Hi ha dos conceptes, esquema i dades. L'esquema es l'estructura de la base dades (taules, indexs, referencies, etc), i les dades es amb el que s'omplen les taules.

A aquesta carpeta hi ha els dos fitxers incials:
- enviafib_create_data.sql
- enviafib_create_schema.sql

Que inclouen tota la base de dades i les dades necessaries per a que funcioni. Cada vegada que es fa un canvi de versió, s'actualitzen aquests fitxers, de manera que per instalar el projecte, basta amb aquests dos.

També hi ha fitxers d'updates de versions. La idea d'aquests es que si algú ja te una versió instalada d'EnviaFIB, basta utilitzar executar els updates AMB ORDRE desde la versió actual fins la desitjada.

Els fitxers tenen aquesta estrucutra:
- update_from_1.0.2_to_1.0.3_schema.sql
- update_from_1.0.2_to_1.0.3_data.sql

On s'indica primer de quina versió es parteix, i a quina versió es vol actualitzar.

### PER AL DESENVOLUPADOR ###
Els canvis que es facin a l'aplicació, s'han de posar al fitxer amb la versió destí la proxima versió on fará feina.

Quan es canvia la versió, la versió que pasa a estable es la nova. (Si esteim fent feina a la 1.0.7, quan canviem de versió, la versió estable será la 1.0.8)
Aixó vol dir que el canvi que s'estan fent ara s'han d'afegir al fitxer de 1.0.7 a 1.0.8.