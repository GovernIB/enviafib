

---
---  Canviar estat REBUTJAT per ERROR #82 
---

UPDATE efi_peticio SET errormsg='Petició Rebutjada'  WHERE estat=4 AND errormsg is null;


---
---  Arrancar Directament les peticions de tipus nif, director i secretari #114 
---

UPDATE efi_peticio SET estat=4, errormsg='L´estat CREADA ja no existeix. Esborri aquesta petició i torni a crear-la.'  WHERE estat=1;