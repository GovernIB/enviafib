
INSERT INTO efi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (30, NULL, 'image/x-icon', 'governib.ico', 1150);
INSERT INTO efi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (31, NULL, 'image/png', 'logo-160.png', 32837);
INSERT INTO efi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (32, NULL, 'image/png', 'app-logo-bn.png', 25008);
INSERT INTO efi_fitxer (fitxerid, descripcio, mime, nom, tamany) VALUES (33, NULL, 'image/jpeg', 'logotaulafirmesfundaciobit.jpg', 2410);

INSERT INTO efi_traduccio (traduccioid) VALUES (100);

INSERT INTO efi_traducciomap (traducciomapid, idiomaid, valor) VALUES (100, 'ca', 'Motiu');
INSERT INTO efi_traducciomap (traducciomapid, idiomaid, valor) VALUES (100, 'es', 'Motivo');

INSERT INTO efi_entitat 
    (entitatid, nom, descripcio, adrezahtml, activa, suporttelefon, suportweb, suportemail, faviconid, logowebid, logowebpeuid, logosegellid, web, motiudelegacioid, segelldetempsviaweb, checkcanviatdocfirmat, propietatstaulafirmes) 
VALUES 
    ('govern', 'GovernIB', 'Govern de les Illes Balears', '<p><span style="color: #fe0089;"><strong>Govern IB</strong></span></p><p><span style="color: #999999;">prova prova text</span></p>', 1, '662548831', 'www.caib.es', 'suport@caib.es', 30, 31, 32, 33, 'https://www.caib.es/webgoib/que-necessites', 100, 0, 1, NULL);

UPDATE efi_usuari SET entitatid = 'govern';

ALTER TABLE efi_usuari MODIFY (entitatid NOT NULL);
