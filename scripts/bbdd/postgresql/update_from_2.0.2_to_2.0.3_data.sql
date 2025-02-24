UPDATE efi_plugin
SET properties = properties || 
'

#TimeOuts per arxiu:
es.caib.enviafib.pluginsib.arxiu.caib.timeout.connect=10000
es.caib.enviafib.pluginsib.arxiu.caib.timeout.read=60000'
WHERE classe = 'es.caib.pluginsib.arxiu.caib.ArxiuPluginCaib' AND properties NOT LIKE '%arxiu.caib.timeout.connect%';
