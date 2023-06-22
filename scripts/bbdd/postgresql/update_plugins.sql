/*
ARXIU:
 - CAIB

ESTRUCTURA ORGANITZATIVA
 - MOCK FUNDACIOBIT
 - TAULA BBDD
 - LDAP CAIB
*/



-- PLUGIN ESTRUCTURA ORGANITZATIVA - LDAP CAIB
UPDATE efi_plugin SET properties = 
'# PLUGIN ESTRUCTURA ORGANITZATIVA - LDAP CAIB

# Classe org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.users_context_dn=dc\=caib,dc\=es
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.host_url=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.host_url"]]
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_authentication=simple

es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.search_scope=subtree
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_principal=[=SP["es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_principal"]]
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
es.caib.enviafib.pluginsib.estructuraorganitzativa.ldapcaib.user.ptrias=dgtic
'
WHERE classe = 'org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin';





-- PLUGIN ARXIU - CAIB
UPDATE efi_plugin SET properties = 
'# PLUGIN ARXIU - CAIB
es.caib.enviafib.plugin.arxiu.caib.base.url=[=SP["es.caib.enviafib.plugin.arxiu.caib.base.url"]]
es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi=[=SP["es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi"]]
es.caib.enviafib.plugin.arxiu.caib.usuari=[=SP["es.caib.enviafib.plugin.arxiu.caib.usuari"]]
es.caib.enviafib.plugin.arxiu.caib.contrasenya=[=SP["es.caib.enviafib.plugin.arxiu.caib.contrasenya"]]

es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url"]]
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari"]]
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya"]]

es.caib.enviafib.plugin.arxiu.caib.original_file_url_EL=https://se.caib.es/concsvapi/interna/original/${csv}
es.caib.enviafib.plugin.arxiu.caib.printable_file_url_EL=https://se.caib.es/concsvapi/interna/printable/${csv}
es.caib.enviafib.plugin.arxiu.caib.eni_file_url_EL=https://se.caib.es/concsvapi/enidoc/${csv}
es.caib.enviafib.plugin.arxiu.caib.csv_validation_web_EL=https://se.caib.es/concsvfront/

# Valor a definir per cada entiat
es.caib.enviafib.plugin.arxiu.caib.csv.definicio=https://se.caib.es/def_csv_gen.pdf
es.caib.enviafib.plugin.arxiu.caib.validation_file_url_EL=https://se.caib.es/concsvapi/hash/${csv}
'
WHERE classe = 'es.caib.plugins.arxiu.caib.ArxiuPluginCaib';




-- PLUGIN ESTRUCTURA ORGANITZATIVA - TAULA BBDD
UPDATE efi_plugin SET properties = 
'# =================  TAULA DE BBDD =========

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
# del plugin de user information han d´estar al System.getProperties()
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.propertiesfile=D:/Projectes/jboss7/standalone/deploy_enviafib/enviafib.properties

es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcaparea=RolCapArea
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcapdepartament=RolCapDepartament
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolsecretari=RolSecretari
'
WHERE classe = 'org.fundaciobit.pluginsib.estructuraorganitzativa.database.DatabaseEstructuraOrganitzativaPlugin';




-- PLUGIN ESTRUCTURA ORGANITZATIVA - MOCK FUNDACIO BIT
UPDATE efi_plugin SET properties = 
'# PLUGIN ESTRUCTURA ORGANITZATIVA - MOCK FUNDACIO BIT
# Classe org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin


# =================  ORGANITZACIÓ - EMPRESA =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.gerentpresident.name=Antoni Roig
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.gerentpresident.username=aroig

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.organitzacioempresa.name=Fundació BIT
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.organitzacioempresa.dir3=A04027052
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.organitzacioempresa.nif=G57775884

# =================  ÀREA - CONSELLERIA =========
 
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.name.ca=Innovació
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.name.es=Innovación
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.dir3=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.code=

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.boss.username=jtramullas
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.areaconselleria.boss.name=Joan Tramullas

# =================  DEPARTAMENT - DIRECCIÓ GENERAL =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.name.ca=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Govern Digital<#break><#case "jsastre">Sanitat<#break><#default>Turisme</#switch>
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.name.es=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Gobierno Digital<#break><#case "jsastre">Sanidad<#break><#default>Turismo</#switch>
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.dir3=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.code=

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.boss.username=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">ptrias<#break><#case "jsastre">xtous<#break><#default>ellado</#switch>
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.departamentdirecciogeneral.boss.name=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">Juan Pablo Trias Segura<#break><#case "jsastre">Xisco Tous<#break><#default>Esteva Llado</#switch>

# =================  DADES GENERALS =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.secretari.username=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.secretari.name=

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.encarregatcompres.username=jfernandez
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.encarregatcompres.name=jfernandez

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.recursoshumans.username=rdiaz
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.recursoshumans.name=rdiaz

# =================  CÀRRECS ADDICIONALS =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec1.name=Antoni Nadal
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec1.username=anadal
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec1.positionname.ca=Cap de Becaris
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec1.positionname.es=Jefe de Becarios


es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec2.name=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec2.username=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec2.positionname.ca=
es.caib.enviafib.pluginsib.estructuraorganitzativa.mock.carrec2.positionname.es=
'
WHERE classe = 'org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin';