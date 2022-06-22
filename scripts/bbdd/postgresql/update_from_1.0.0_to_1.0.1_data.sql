

---
---  Canviar estat REBUTJAT per ERROR #82 
---

UPDATE efi_peticio SET errormsg='Petició Rebutjada'  WHERE estat=4 AND errormsg is not null;