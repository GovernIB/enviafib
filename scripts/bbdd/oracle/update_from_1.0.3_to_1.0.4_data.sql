
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


INSERT INTO efi_traducciomap VALUES (110, 'es', 'Enviar a firmar por NIF');
INSERT INTO efi_traducciomap VALUES (110, 'ca', 'Enviar a firmar per NIF');
INSERT INTO efi_traducciomap VALUES (111, 'es', 'El usuario seleccionará el NIF de la persona que quiere que firme');
INSERT INTO efi_traducciomap VALUES (111, 'ca', 'L''usuari seleccionarà el NIF de la persona que vol que signi');
INSERT INTO efi_traducciomap VALUES (112, 'ca', 'Autofirma');
INSERT INTO efi_traducciomap VALUES (112, 'es', 'Autofirma');
INSERT INTO efi_traducciomap VALUES (113, 'ca', 'Sirveix pera a que vosté mateix firmi un document a l''instant');
INSERT INTO efi_traducciomap VALUES (113, 'es', 'Sirve para que usted mismo firme un documento al instante');
INSERT INTO efi_traducciomap VALUES (114, 'ca', 'Firma Personalitzada');
INSERT INTO efi_traducciomap VALUES (114, 'es', 'Firma Personalizada');
INSERT INTO efi_traducciomap VALUES (115, 'ca', 'Se us obrirà un editor perquè trieu el flux de signants que vulgueu');
INSERT INTO efi_traducciomap VALUES (115, 'es', 'Se le abrirà un editor para que usted elija el flujo de firmantes que desee');
INSERT INTO efi_traducciomap VALUES (116, 'ca', 'Enviar a firmar al/la Director/a');
INSERT INTO efi_traducciomap VALUES (116, 'es', 'Enviar a firmar al/la Director/a');
INSERT INTO efi_traducciomap VALUES (117, 'ca', 'Enviar a firmar un document al/a la Director/a General');
INSERT INTO efi_traducciomap VALUES (117, 'es', 'Enviar a firmar un documento al/la Director/a General');
INSERT INTO efi_traducciomap VALUES (118, 'ca', 'Enviar a firmar al/a la Secretari/a');
INSERT INTO efi_traducciomap VALUES (118, 'es', 'Enviar a firmar al/a la Secretario/a');
INSERT INTO efi_traducciomap VALUES (119, 'ca', 'Per enviar a signar un document al/a la secretari/a de la seva direcció general');
INSERT INTO efi_traducciomap VALUES (119, 'es', 'Para enviar a firmar un documento al/a la secretario/a de su dirección general');
INSERT INTO efi_traducciomap VALUES (120, 'ca', 'Enviar a firmar amb una plantilla de flux de firma de l´usuari');
INSERT INTO efi_traducciomap VALUES (120, 'es', 'Enviar a firmar con una plantilla de flujo de firma del usuario');
INSERT INTO efi_traducciomap VALUES (121, 'ca', 'Enviar a firmar amb plantilla de flux de firma de l´usuari');
INSERT INTO efi_traducciomap VALUES (121, 'es', 'Enviar a firmar con una plantilla de flujo de firma del usuario');
INSERT INTO efi_traducciomap VALUES (122, 'ca', 'Enviar a firmar amb plantilla de flux de firma d´entitat');
INSERT INTO efi_traducciomap VALUES (122, 'es', 'Enviar a firmar con plantilla de flujo de firma de la entidad');
INSERT INTO efi_traducciomap VALUES (123, 'ca', 'Enviar a firmar amb plantilla de flux de firma d´entitat');
INSERT INTO efi_traducciomap VALUES (123, 'es', 'Enviar a firmar con plantilla de flujo de firma de la entidad');


INSERT INTO efi_menu VALUES (10, 'Autofirma', NULL, 112, 113, 10, 1, NULL, NULL, NULL, 1);
INSERT INTO efi_menu VALUES (11, 'Prova NIF', NULL, 110, 111, 20, 2, NULL, NULL, NULL, 1);
INSERT INTO efi_menu VALUES (12, 'Firma Personalitzada', NULL, 114, 115, 30, 3, NULL, NULL, NULL, 1);
INSERT INTO efi_menu VALUES (13, 'Director General', NULL, 116, 117, 40, 8, NULL, NULL, '3', 1);
INSERT INTO efi_menu VALUES (14, 'Enviar a firmar al/la secretari/a', NULL, 118, 119, 50, 8, NULL, NULL, '4', 1);
INSERT INTO efi_menu VALUES (15, 'Plantilla de flux de firma d´usuari', 'L''usuari seleccionarà entre les seves plantilles de flux de firmes. Això implica que podrà crear Flux de Firma que només veurà ell.', 120, 121, 60, 4, NULL, NULL, NULL, 1);
INSERT INTO efi_menu VALUES (16, 'Plantilla de flux de firma d´entitat', NULL, 122, 123, 70, 5, NULL, '# Aquí anirà la llista de CODIS de de PLANTILLES DE L''ENTITAT. 
# L''Administrador triarà quines vol que vegi l''usuari.
# Si no en defineix cap llavors es mostraran totes', NULL, 1);




