
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



--- Falta afegir FAQs