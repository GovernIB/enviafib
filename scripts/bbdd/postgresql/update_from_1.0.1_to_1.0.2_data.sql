


---
--- 04/08/2022 - Gestió de PLugins de Estructura Organitzativa #142
---

INSERT INTO efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (1, 'org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin',
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
', true, 1, 'Plugin Mock', 'Plugin Mock que simula entitat Fundació Bit');