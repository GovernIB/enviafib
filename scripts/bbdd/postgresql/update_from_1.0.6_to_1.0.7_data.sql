
---
--- 11/05/2023 -   Revisar com s'informen les metadades mínimes obligatòries #266
---

 INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES
   (1010, 'SD01333', '1', 'Enviafib TD01 Resolució', '---'),
   (1011, 'SD01334', '2', 'Enviafib TD02 Acords', '---'),
   (1012, 'SD01335', '3', 'Enviafib TD03 Contractes', '---'),
   (1013, 'SD01336', '4', 'Enviafib TD04 Convenis', '---'),
   (1014, 'SD01337', '5', 'Enviafib TD05 Declaracions', '---'),
   (1015, 'SD01338', '6', 'Enviafib TD06 Comunicacions', '---'),
   (1016, 'SD01339', '7', 'Enviafib TD07 Notificacions', '---'),
   (1017, 'SD01340', '8', 'Enviafib TD08 Publicacions', '---'),
   (1018, 'SD01342', '9', 'Enviafib TD09 Justificants de recepció', '---'),
   (1019, 'SD01342', '10', 'Enviafib TD10 Actes', '---'),
   (1020, 'SD01343', '11', 'Enviafib TD11 Certificats', '---'),
   (1021, 'SD01344', '12', 'Enviafib TD12 Diligències', '---'),
   (1022, 'SD01345', '13', 'Enviafib TD13 Informes', '---'),
   (1023, 'SD01346', '14', 'Enviafib TD14 Sol·licituds', '---'),
   (1024, 'SD01347', '15', 'Enviafib TD15 Denúncies', '---'),
   (1025, 'SD01348', '16', 'Enviafib TD16 Al·legacions', '---'),
   (1026, 'SD01349', '17', 'Enviafib TD17 Recursos', '---'),
   (1027, 'SD01350', '18', 'Enviafib TD18 Comunicacions ciutadà', '---'),
   (1028, 'SD01351', '19', 'Enviafib TD19 Factures', '---'),
   (1029, 'SD01352', '20', 'Enviafib TD20 Altre documentació aportada', '---'),
   (1030, 'SD01353', '99', 'Enviafib TD99 Altres', '---');



---
--- 26/05/2023 - Modificar mètode per obtenir el secreatri del plugin d'estructura organitzativa amb LDAP #317
---
INSERT INTO efi_plugin(pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (50, 'org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin', 
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
', true, 1, 'Plugin LDAPCAIB', 'Plugin LDAPCAIB');