---
--- 12/07/2022 - Estat de Petició ha de ser de tipus integer en java i bbdd #125
---

ALTER TABLE efi_peticio
   ALTER COLUMN estat TYPE integer;