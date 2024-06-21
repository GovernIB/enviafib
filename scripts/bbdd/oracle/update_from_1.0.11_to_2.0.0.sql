
-- Afegir Revisor a petició
ALTER TABLE efi_peticio
   ADD COLUMN revisor varchar2(255);
   
   
--###########################################################################
--##   Actualitzar PluginsIB-Core, PluginsIB-Utils i PluginsIB a les noves versions #403   
--###########################################################################

UPDATE efi_plugin SET classe=REPLACE(classe, 'es.caib.plugins.arxiu.', 'es.caib.pluginsib.arxiu.')  WHERE tipus=2;
UPDATE efi_plugin SET properties=REPLACE(properties, 'es.caib.enviafib.plugin.', 'es.caib.enviafib.pluginsib.')  WHERE tipus=2;

