
-- 
-- ERROR GREU: No utilitzar camp REASON per altres coses #513  (24/09/2026)
--
UPDATE efi_peticio SET fluxdefirmes=reason WHERE tipus=5 OR tipus=6;