
INSERT INTO public.efi_fitxer VALUES (30, NULL, 'image/x-icon', 'governib.ico', 1150);
INSERT INTO public.efi_fitxer VALUES (31, NULL, 'image/png', 'logo-160.png', 32837);
INSERT INTO public.efi_fitxer VALUES (32, NULL, 'image/png', 'app-logo-bn.png', 25008);
INSERT INTO public.efi_fitxer VALUES (33, NULL, 'image/jpeg', 'logotaulafirmesfundaciobit.jpg', 2410);

INSERT INTO public.efi_traduccio VALUES (100)

INSERT INTO public.efi_traducciomap VALUES (100, 'ca', 'Motiu');
INSERT INTO public.efi_traducciomap VALUES (100, 'es', 'Motivo');

INSERT INTO public.efi_entitat VALUES ('govern', 'GovernIB', 'Govern de les Illes Balears', '<p><span style="color: #fe0089;"><strong>Govern IB</strong></span></p>
<p><span style="color: #999999;">prova prova text</span></p>', true, '662548831', 'www.caib.es', 'suport@caib.es', 30, 31, 32, 33, 'https://www.caib.es/webgoib/que-necessites', 100, 0, true, NULL);

UPDATE efi_usuari SET entitatid = 'govern';

ALTER TABLE efi_usuari ALTER COLUMN entitatid SET NOT NULL;
