


INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('ca', 'Català', 0, 1);
INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('es', 'Castellano', 1, 1);
INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('en', 'English', 2, 0);
    

---
---  Canviar estat REBUTJAT per ERROR #82 
---

UPDATE efi_peticio SET errormsg='Petició Rebutjada'  WHERE estat=4 AND errormsg is null;


---
---  Arrancar Directament les peticions de tipus nif, director i secretari #114 (DEPRECATED)
---

-- UPDATE efi_peticio SET estat=4, errormsg='L´estat CREADA ja no existeix. Esborri aquesta petició i torni a crear-la.'  WHERE estat=1;


---
--- 04/08/2022 - Gestió de PLugins de Estructura Organitzativa #142
---

INSERT INTO efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (1, 'org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin', '# Fundaciobit

# Classe org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin

# =================  ORGANITZACIÓ - EMPRESA =========

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.gerentpresident.name=Antoni Roig
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.gerentpresident.username=aroig

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.organitzacioempresa.name=Fundació BIT
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.organitzacioempresa.dir3=A04027052
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.organitzacioempresa.nif=G57775884

# =================  ÀREA - CONSELLERIA =========
 
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.name.ca=Innovació
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.name.es=Innovación
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.dir3=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.code=

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.boss.username=jtramullas
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.areaconselleria.boss.name=Joan Tramullas

# =================  DEPARTAMENT - DIRECCIÓ GENERAL =========

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.name.ca=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Govern Digital<#break><#case "jsastre">Sanitat<#break><#default>Turisme</#switch>
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.name.es=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Gobierno Digital<#break><#case "jsastre">Sanidad<#break><#default>Turismo</#switch>
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.dir3=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.code=

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.boss.username=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">atrobat<#break><#case "jsastre">xtous<#break><#default>ellado</#switch>
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.departamentdirecciogeneral.boss.name=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Antoni Trobat<#break><#case "jsastre">Xisco Tous<#break><#default>Esteva Llado</#switch>

# =================  DADES GENERALS =========

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.secretari=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.encarregatcompres=jfernandez
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.recursoshumans=rdiaz

# =================  CÀRRECS ADDICIONALS =========

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec1.name=Antoni Nadal
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec1.username=anadal
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec1.positionname.ca=Cap de Becaris
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec1.positionname.es=Jefe de Becarios


es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec2.name=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec2.username=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec2.positionname.ca=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.carrec2.positionname.es=', 0, 1, 'Plugin Mock', 'Plugin Mock que simula entitat Fundació Bit');


    
---
--- 16/08/2022 Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---

INSERT INTO efi_seriedocumental(seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi)
VALUES(10, 'S0001', NULL ,'Subvenciones empleo', 'organo1_PRO_123456789');



---
--- 17/08/2022 Fer que Plugin d'Arxiu llegeixi properties i classe de BBDD enlloc de Propietats de sistema #169
---
INSERT INTO efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (10, 'es.caib.plugins.arxiu.caib.ArxiuPluginCaib', 
'# PLUGIN ARXIU - CAIB
es.caib.enviafib.plugin.arxiu.caib.base.url=https://esbse.caib.es:4430/esb
es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi=Tests
es.caib.enviafib.plugin.arxiu.caib.usuari=username
es.caib.enviafib.plugin.arxiu.caib.contrasenya=password

es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url=https://se.caib.es/concsv/rest/printable/uuid
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari=username
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya=password

es.caib.enviafib.plugin.arxiu.caib.original_file_url_EL=https://se.caib.es/concsv/rest/original/${csv}
es.caib.enviafib.plugin.arxiu.caib.printable_file_url_EL=https://se.caib.es/concsv/rest/printable/${csv}
es.caib.enviafib.plugin.arxiu.caib.eni_file_url_EL=https://se.caib.es/concsv/enidoc/${csv}
es.caib.enviafib.plugin.arxiu.caib.csv_validation_web_EL=http://se.caib.es/concsv/${csv}

# Valor a definir per cada entiat
es.caib.enviafib.plugin.arxiu.caib.csv.definicio=https://se.caib.es/def_csv_gen.pdf
es.caib.enviafib.plugin.arxiu.caib.validation_file_url_EL=https://se.caib.es/concsv/hash/${csv}'
, 1, 2, 'Plugin Arxiu Prova', 'Plugin Arxiu Prova');

---
--- 15/09/2022 -  Crear una taula efi_menu #212 
---

INSERT INTO efi_traduccio VALUES (110);
INSERT INTO efi_traduccio VALUES (111);
INSERT INTO efi_traduccio VALUES (112);
INSERT INTO efi_traduccio VALUES (113);
INSERT INTO efi_traduccio VALUES (114);
INSERT INTO efi_traduccio VALUES (115);
INSERT INTO efi_traduccio VALUES (116);
INSERT INTO efi_traduccio VALUES (117);
INSERT INTO efi_traduccio VALUES (118);
INSERT INTO efi_traduccio VALUES (119);
INSERT INTO efi_traduccio VALUES (120);
INSERT INTO efi_traduccio VALUES (121);
INSERT INTO efi_traduccio VALUES (122);
INSERT INTO efi_traduccio VALUES (123);
INSERT INTO efi_traduccio VALUES (124);
INSERT INTO efi_traduccio VALUES (125);
INSERT INTO efi_traduccio VALUES (126);
INSERT INTO efi_traduccio VALUES (127);
INSERT INTO efi_traduccio VALUES (128);
INSERT INTO efi_traduccio VALUES (129);
INSERT INTO efi_traduccio VALUES (130);
INSERT INTO efi_traduccio VALUES (131);
INSERT INTO efi_traduccio VALUES (132);
INSERT INTO efi_traduccio VALUES (133);
INSERT INTO efi_traduccio VALUES (134);
INSERT INTO efi_traduccio VALUES (135);


INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (110, 'es', 'Enviar a firmar por NIF');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (110, 'ca', 'Enviar a firmar per NIF');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'es', 'El usuario seleccionará el NIF de la persona que quiere que firme');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (111, 'ca', 'L''usuari seleccionarà el NIF de la persona que vol que signi');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (112, 'ca', 'Autofirma');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (112, 'es', 'Autofirma');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (113, 'ca', 'Sirveix pera a que vosté mateix firmi un document a l''instant');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (113, 'es', 'Sirve para que usted mismo firme un documento al instante');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (114, 'ca', 'Firma Personalitzada');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (114, 'es', 'Firma Personalizada');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (115, 'ca', 'Se us obrirà un editor perquè trieu el flux de signants que vulgueu');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (115, 'es', 'Se le abrirà un editor para que usted elija el flujo de firmantes que desee');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (116, 'ca', 'Enviar a firmar al/la Director/a');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (116, 'es', 'Enviar a firmar al/la Director/a');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (117, 'ca', 'Enviar a firmar un document al/a la Director/a General');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (117, 'es', 'Enviar a firmar un documento al/la Director/a General');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (118, 'ca', 'Enviar a firmar al/a la Secretari/a');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (118, 'es', 'Enviar a firmar al/a la Secretario/a');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (119, 'ca', 'Per enviar a signar un document al/a la secretari/a de la seva direcció general');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (119, 'es', 'Para enviar a firmar un documento al/a la secretario/a de su dirección general');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (120, 'ca', 'Enviar a firmar amb una plantilla de flux de firma de l´usuari');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (120, 'es', 'Enviar a firmar con una plantilla de flujo de firma del usuario');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (121, 'ca', 'Enviar a firmar amb plantilla de flux de firma de l´usuari');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (121, 'es', 'Enviar a firmar con una plantilla de flujo de firma del usuario');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'ca', 'Enviar a firmar amb plantilla de flux de firma d´entitat');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (122, 'es', 'Enviar a firmar con plantilla de flujo de firma de la entidad');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (123, 'ca', 'Enviar a firmar amb plantilla de flux de firma d´entitat');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (123, 'es', 'Enviar a firmar con plantilla de flujo de firma de la entidad');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (124, 'ca', 'Per firma el Gerent');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (124, 'es', 'Para firmar el Gerente');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (125, 'ca', 'Envia un documento para que lo firme el gerente');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (125, 'es', 'Envia un documento para que lo firme el gerente');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (126, 'ca', 'Per firmar el meu Conseller');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (126, 'es', 'Para firmar mi Conseller');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (127, 'ca', 'Enviar a firmar un document al meu Conseller');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (127, 'es', 'Enviar a firmar un documento al mi Conseller');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (128, 'ca', 'Firmar l''Encarregat de Compres');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (128, 'es', 'Firmar Encargado de Compras');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (129, 'ca', 'Enviar un document per a que el firmi l''Encarregat de Compres');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (129, 'es', 'Enviar un documento para que lo firme el Encargado de Compras');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (130, 'ca', 'Firma Recursos Humans');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (130, 'es', 'Firma Recursos Humanos');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (131, 'ca', 'Envia un document per a que el signi Recursos Humans');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (131, 'es', 'Envia un documento para que lo firme Recursos Humanos');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (132, 'ca', 'Cap de Becaris (Addic. 1)');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (132, 'es', 'Jefe de Becarios (Adic. 1)');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (133, 'es', 'Firma Jefe de Becarios (Adic. 1)');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (133, 'ca', 'Firma Cap de Becaris (Addic. 1)');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (134, 'ca', 'Firma Secretari i Director General');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (134, 'es', 'Firmar Secretario y Director General');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (135, 'ca', 'Firma Secretari i després Director General');
INSERT INTO efi_traducciomap(traducciomapid, idiomaid, valor) VALUES (135, 'es', 'Firmar Secretario y después Director General');


INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (10, 'Autofirma', NULL, 112, 113, 10, 1, NULL, NULL, NULL, 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (11, 'Prova NIF', NULL, 110, 111, 20, 2, NULL, NULL, NULL, 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (12, 'Firma Personalitzada', NULL, 114, 115, 30, 3, NULL, NULL, NULL, 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (13, 'Director General', NULL, 116, 117, 93, 8, NULL, NULL, '3', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (14, 'Enviar a firmar al/la secretari/a', NULL, 118, 119, 94, 8, NULL, NULL, '4', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (15, 'Plantilla de flux de firma d´usuari', 'L''usuari seleccionarà entre les seves plantilles de flux de firmes. Això implica que podrà crear Flux de Firma que només veurà ell.', 120, 121, 60, 4, NULL, NULL, NULL, 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (16, 'Plantilla de flux de firma d´entitat', NULL, 122, 123, 70, 5, NULL, '# Aquí anirà la llista de CODIS de de PLANTILLES DE L''ENTITAT. 
# L''Administrador triarà quines vol que vegi l''usuari.
# Si no en defineix cap llavors es mostraran totes', NULL, 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (17, 'Gerent President', NULL, 124, 125, 91, 8, NULL, NULL, '1', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (18, 'Conseller', NULL, 126, 127, 92, 8, NULL, NULL, '2', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (19, 'Encarregat de Compres', NULL, 128, 129, 95, 8, NULL, NULL, '5', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (20, 'Recursos Humans', NULL, 130, 131, 96, 8, NULL, NULL, '6', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (21, 'Addicional 1 - Cap de Becaris', NULL, 132, 133, 97, 8, NULL, NULL, '7', 1);
INSERT INTO efi_menu(menuid, nom, descripcio, titolmenuid, ajudamenuid, ordre, tipus, grupid, parametretext, parametrecombo, actiu) VALUES (22, 'Secretari i Director General', NULL, 134, 135, 80, 6, NULL, '# Es definirà el flux simple emprant les següents normes:
#    (0) Comentaris començaran per ''#''
#    (1) Cada Fila representa un bloc de firmes.
#    (2) Els usuaris de cada bloc es representaran pel seu username o càrrec
#        d''Estructura organitzativa i es separaran pel caràcter ''|'' o ''&''.
#    (3) Una separació d''usuaris emprant ''&'' implica que totes les firmes s''han de realitzar.
#    (4) Una separació d''usuaris emprant ''|'' implica que només es requerirà la firma d''un 
#         d''ells i després es passarà al següent bloc.
#    (5) No es poden mesclar en una mateixa fila separadors ''&'' i ''|''
#    (6) Els càrrecs d''Estructura organitzativa es definiran de la següent forma:
#          - Gerent/President => ${GerentPresident}
#          - Cap Area/Conseller => ${CapAreaConseller}
#          - Cap Departament/Director General => ${CapDepartamentDirectorGeneral}
#          - Secretari => ${Secretari}
#          - Encarregat de Compres => ${EncarregatCompres}
#          - Recursos Humans => ${RecursosHumans}
#          - Càrrec Addicional 1 => ${Carrec1}
#          - Càrrec Addicional 2 => ${Carrec2}
#          - Usuari Loguejat => ${UsuariActual}
# 
# Exemple:
#        anadal | ptrias
#        atrobat & fbosh
#        ${Secretari}
#        ${GerentPresident}
#
# Explicació: Primer firmarà anadal o ptrias (només un dels dos), després atrobat
#             i fbosch en l''ordre que vulguin, després el secretari i finalment gerent.
#
anadal | ${Secretari} | ${UsuariActual}', NULL, 0);





---
--- 26/09/2022 -   Estudi Plugin Estructura Organitzativa en taula de BBDD #225 
---

INSERT INTO efi_plugin(pluginid, classe, properties, actiu, tipus, nom, descripcio) 
      VALUES (30, 'org.fundaciobit.pluginsib.estructuraorganitzativa.database.DatabaseEstructuraOrganitzativaPlugin', '# =================  TAULA DE BBDD =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.database.datasourcejndi=java:jboss/datasources/enviafibDS
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.tablename=efi_organitzacio
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.areacodi=codiconselleria
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.departamentcodi=codidirecciogeneral
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.tipus=tipus
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.valor=valor


# =================  PLUGIN DE USER INFORMATION =========


es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.class=org.fundaciobit.pluginsib.userinformation.keycloak.KeyCloakUserInformationPlugin
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.propertybase=es.caib.enviafib.
# Si la següent propietat està comentada, llavors les propietats
# del plugin de user information han d''estar al System.getProperties()
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.propertiesfile=D:\\dades\\dades\\CarpetesPersonals\\ProjecteBase\\jboss7\\standalone\\deploy_enviafib\\enviafib.properties

es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcaparea=RolCapArea
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcapdepartament=RolCapDepartament
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolsecretari=RolSecretari', 1, 1, 'Database', 'Recull informació de l''Estructura d''una taula de la Base de Dades');

---
--- 11/05/2023 -   Revisar com s'informen les metadades mínimes obligatòries #266
---
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1010, 'SD01333', '1', 'Enviafib TD01 Resolució', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1011, 'SD01334', '2', 'Enviafib TD02 Acords', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1012, 'SD01335', '3', 'Enviafib TD03 Contractes', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1013, 'SD01336', '4', 'Enviafib TD04 Convenis', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1014, 'SD01337', '5', 'Enviafib TD05 Declaracions', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1015, 'SD01338', '6', 'Enviafib TD06 Comunicacions', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1016, 'SD01339', '7', 'Enviafib TD07 Notificacions', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1017, 'SD01340', '8', 'Enviafib TD08 Publicacions', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1018, 'SD01342', '9', 'Enviafib TD09 Justificants de recepció', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1019, 'SD01342', '10', 'Enviafib TD10 Actes', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1020, 'SD01343', '11', 'Enviafib TD11 Certificats', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1021, 'SD01344', '12', 'Enviafib TD12 Diligències', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1022, 'SD01345', '13', 'Enviafib TD13 Informes', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1023, 'SD01346', '14', 'Enviafib TD14 Sol·licituds', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1024, 'SD01347', '15', 'Enviafib TD15 Denúncies', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1025, 'SD01348', '16', 'Enviafib TD16 Al·legacions', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1026, 'SD01349', '17', 'Enviafib TD17 Recursos', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1027, 'SD01350', '18', 'Enviafib TD18 Comunicacions ciutadà', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1028, 'SD01351', '19', 'Enviafib TD19 Factures', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1029, 'SD01352', '20', 'Enviafib TD20 Altre documentació aportada', '---');
INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES (1030, 'SD01353', '99', 'Enviafib TD99 Altres', '---');



---
--- 26/05/2023 - Modificar mètode per obtenir el secreatri del plugin d'estructura organitzativa amb LDAP #317
---
declare
  vMyClob Clob;
begin

  vMyClob := to_clob('# PLUGIN ESTRUCTURA ORGANITZATIVA - LDAP CAIB

# Classe org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.users_context_dn=dc\=caib,dc\=es
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.host_url=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.host_url"]]
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_authentication=simple

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.search_scope=subtree
es.caib.enviafib.pluginsib.estucturaorganitzativa.ldapcaib.ldap.security_principal=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_principal"]]
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_credentials=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_credentials"]]

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.username=cn
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.mail=mail
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.administration_id=nif
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.name=givenName
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname=sn
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname1=sn1
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname2=sn2
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.telephone=
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.department=departmentNumber
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.memberof=memberOf
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.prefix_role_match_memberof=cn=
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.suffix_role_match_memberof=,dc=caib,dc=es

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.dir3host=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.dir3host"]]


es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.rolcapdepartament=DIS_IBSALUT_RRHH
#IBK_DIRECTOR
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.rolsecretari=PFI_USER
#IBK_SECRETARI

# S´ha de definir una de les dues propietats: o conselleria.dir o rolcaparea
#es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.rolcaparea=IBK_CONSELLER

#Mapeig de DIR3 de Conselleries i Consellers
# Juan Pedro Yllanes Suarez Yllanes Suarez
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026972=u143134
# Mercedes Garrido Rodriguez
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04027007=e18227253y
# Rosario Sanchez Grau
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026911=u135358
# Iago Negueruela Vázquez
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026960=u93566
# Fina De Santiago Rodríguez
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026929=u06246
# Marti Xavier March Cerda
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026923=u06196
# Patricia Juana Gomez Picard
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026919=u89586
# Miquel Company Pons
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026906=e41507318t
# Miquel Mir Gual
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026953=u97173
# María Asunción Jacoba Pía de la Concha García-Mauriño
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026949=u143142
# Josep Mari Ribas
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026937=e41439526n

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.gerentpresident.nom=Francina Armengol i Socias
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.gerentpresident.username=u135371
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.nom=Govern de les Illes Balears
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.dir3=A04003003
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.nif=S0711001H


#Mapeig dels grups que no tenen DIR3 associat a grups que si el tenen
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.group.externs=dgtic

#Mapeig d´usuaris usuaris de grups que no tenen DIR3 associats a un grup que si en te
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.user.u80067=dgtic
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.user.e45186147w=dgtic
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.user.ptrias=dgtic');

  INSERT INTO efi_plugin (PLUGINID, CLASSE, PROPERTIES, ACTIU, TIPUS,NOM, DESCRIPCIO) VALUES 
    (50, 'org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin', vMyClob, 1, 1, 'Plugin LDAPCAIB', 'Plugin LDAPCAIB');
  commit;

end;
