<div style="min-width: 45%;max-width: 900px;">
<h1>DECLARACIÓ D'ACCESSIBILITAT</h1>

<p>
    El Govern de les Illes Balears s'ha compromès a fer accessible el seu lloc web i la seva aplicació per a dispositius mòbils, de conformitat amb
    <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">
        el Reial decret 1112/2018
    </a>,
    de 7 de setembre, d’accessibilitat dels llocs web i aplicacions mòbils del sector públic.
</p>

<p>
    La present declaració d’accessibilitat només s’aplica al lloc web <b>${backurl}</b> i no inclou les pàgines que condueixen a enllaços externs.
</p>

<br>
<h2>Situació de compliment</h2>

<p>
    Aquest lloc web és <b>No Conforme</b> amb
    <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">
        el RD 1112/2018
    </a>
    a causa de les excepcions i de la manca de conformitat dels aspectes que s’indiquen a continuació.
</p>

<br>
<h2>Contingut no accessible</h2>

<p>
    El contingut que es detalla a continuació no és accessible pels motius següents:
</p>

<p>
    Manca de conformitat amb
    <a href="https://www.boe.es/diario_boe/txt.php?id=BOE-A-2018-12699" target="_blank" rel="noopener noreferrer">
        el RD 1112/2018
    </a>.
</p>
<br>
<p>
    <span >
        <strong>1. Ús d’encapçalaments: Absència d’elements d’encapçalament.</strong>
    </span>
</p>

<p>
    <span >
        Les pàgines han d’utilitzar elements d’encapçalament (H1...H6) per identificar els títols de les diferents seccions
        del document. L’estructura dels encapçalaments ha de reflectir la jerarquia lògica del contingut, sense utilitzar-los
        únicament amb finalitats de presentació ni saltar nivells dins de la jerarquia.
    </span>
</p>

<p>
    <span >
        En diverses pàgines no es detecten elements d’encapçalament. Això dificulta que els usuaris, especialment els que
        utilitzen lectors de pantalla, puguin identificar i navegar ràpidament per les diferents seccions de la pàgina.
    </span>
</p>
<br>
<p>
    <span >
        <strong>2. Ús de llistes: Llistes no ordenades mal estructurades.</strong>
    </span>
</p>

<p>
    <span >
        Les llistes no ordenades s’han d’estructurar mitjançant l’element UL i els seus elements han de ser elements LI.
        No s’han d’incloure altres tipus d’elements com a fills directes d’un UL.
    </span>
</p>

<p>
    <span >
        Una estructura incorrecta de les llistes pot dificultar que els productes de suport, com ara els lectors de pantalla,
        interpretin adequadament la informació i la relació entre els diferents elements de la llista.
    </span>
</p>
<br>
<p>
    <span >
        <strong>3. Taules de dades: Estructuració incorrecta dels encapçalaments de les taules.</strong>
    </span>
</p>

<p>
    <span >
        Les taules de dades han d’estar estructurades correctament mitjançant cel·les d’encapçalament (TH) i cel·les de dades.
        Quan la primera fila o la primera columna conté cel·les d’encapçalament, no s’hi han de barrejar incorrectament
        cel·les de dades i cel·les d’encapçalament, excepte en el cas de cel·les buides.
    </span>
</p>

<p>
    <span >
        Una estructura correcta permet que els lectors de pantalla i altres productes de suport puguin interpretar
        correctament les relacions entre els encapçalaments i les dades de la taula.
    </span>
</p>
<br>
<p>
    <span >
        <strong>4. Agrupació estructural: Ús excessiu de salts de línia BR.</strong>
    </span>
</p>

<p>
    <span >
        Els salts de línia BR s’han d’utilitzar de manera excepcional i només quan estigui justificat. No s’han d’utilitzar
        per simular estructures que haurien d’estar representades mitjançant elements HTML específics.
    </span>
</p>

<p>
    <span >
        Per exemple, els llistats s’han d’estructurar amb UL, OL i LI, i els paràgrafs amb P, en lloc de construir aquestes
        estructures mitjançant una successió de salts de línia.
    </span>
</p>
<br>
<p>
    <span >
        <strong>5. Separació de contingut i presentació: Ús d’elements HTML de presentació desaconsellats.</strong>
    </span>
</p>

<p>
    <span >
        S’han detectat elements o atributs HTML destinats a controlar la presentació del contingut, com ara CENTER.
        Aquest tipus d’elements s’han d’evitar, ja que poden quedar obsolets i dificultar el manteniment i la correcta
        interpretació del contingut.
    </span>
</p>

<p>
    <span >
        La presentació visual s’ha de controlar mitjançant fulls d’estil CSS, mantenint separats el contingut i la seva
        presentació.
    </span>
</p>
<br>
<p>
    <span >
        <strong>6. Separació de contingut i presentació: Generació de contingut mitjançant CSS.</strong>
    </span>
</p>

<p>
    <span >
        No s’han d’utilitzar els fulls d’estil per incorporar contingut o informació textual que formi part del contingut
        de la pàgina. Aquest contingut pot no estar disponible per als lectors de pantalla o altres aplicacions de suport.
    </span>
</p>

<p>
    <span >
        En particular, s’ha d’evitar incorporar text mitjançant els pseudoelements :before o :after i la propietat CSS
        content. La informació significativa ha d’estar disponible directament en el contingut HTML.
    </span>
</p>
<br>
<p>
    <span >
        <strong>7. Navegació amb JavaScript i control de l’usuari: Elements d’interacció no accessibles.</strong>
    </span>
</p>

<p>
    <span >
        S’han detectat elements d’interacció programats mitjançant scripts que no garanteixen l’accessibilitat.
        Els elements interactius s’han d’implementar preferentment mitjançant elements HTML estàndard, com ara
        enllaços o botons.
    </span>
</p>

<p>
    <span >
        Quan es creen components interactius personalitzats mitjançant elements com SPAN, DIV, IMG o elements de
        taula, aquests han de continuar sent operables amb el teclat i compatibles amb les tecnologies de suport,
        aplicant les recomanacions WAI-ARIA quan sigui necessari.
    </span>
</p>
<br>
<p>
    <span >
        <strong>8. Formularis i etiquetes: Associació incorrecta entre controls i etiquetes.</strong>
    </span>
</p>

<p>
    <span >
        Les etiquetes dels camps de formulari s’han d’associar explícitament amb el control corresponent. Per fer-ho,
        l’atribut FOR de l’element LABEL ha de coincidir amb l’atribut ID del camp de formulari.
    </span>
</p>

<p>
    <span >
        Aquesta associació permet que els agents d’usuari i les tecnologies de suport identifiquin correctament la
        relació entre cada etiqueta i el seu camp de formulari.
    </span>
</p>
<br>
<p>
    <span >
        <strong>9. Formularis i etiquetes: Camps de formulari sense una etiqueta que n’identifiqui la finalitat.</strong>
    </span>
</p>

<p>
    <span >
        Els camps de formulari han de disposar d’un text o etiqueta que permeti identificar clarament la seva funció.
        Aquesta etiqueta es pot proporcionar mitjançant un element LABEL associat al camp.
    </span>
</p>

<p>
    <span >
        Quan no existeixi un text visible adequat, es poden utilitzar alternatives com l’atribut TITLE, ARIA-LABEL o
        ARIA-LABELLEDBY, segons correspongui. El problema afecta especialment alguns elements SELECT i INPUT.
    </span>
</p>
<br>
<p>
    <span >
        <strong>10. Formularis i etiquetes: No s’identifiquen els camps obligatoris.</strong>
    </span>
</p>

<p>
    <span >
        En els formularis que contenen camps obligatoris i opcionals s’ha de proporcionar informació que permeti als
        usuaris diferenciar-los clarament.
    </span>
</p>

<p>
    <span >
        Aquesta identificació ajuda els usuaris a emplenar correctament els formularis i redueix la possibilitat que es
        produeixin errors durant la validació.
    </span>
</p>
<br>
<p>
    <span >
        <strong>11. Formularis i estructura: Grups de botons de ràdio o caselles de verificació sense FIELDSET.</strong>
    </span>
</p>

<p>
    <span >
        Els grups de camps de formulari relacionats entre si, especialment els grups de botons de ràdio o caselles de
        verificació, s’han d’agrupar mitjançant l’element FIELDSET.
    </span>
</p>

<p>
    <span >
        Cada grup s’ha d’identificar mitjançant un element LEGEND que descrigui la finalitat del conjunt de camps.
        Aquesta estructura permet que les tecnologies de suport entenguin correctament la relació entre els diferents
        controls del formulari.
    </span>
</p>
<br>
<p>
    <span >
        <strong>12. Títol de pàgina i de marcs: Títols inadequats o absents.</strong>
    </span>
</p>

<p>
    <span >
        Les pàgines analitzades utilitzen el mateix títol de pàgina, “Benvingut a EnviaFIB”, fet que no permet identificar
        de manera clara el contingut de cada pàgina.
    </span>
</p>

<p>
    <span >
        Cada pàgina ha de disposar d’un element TITLE breu i descriptiu que permeti identificar-ne el contingut de forma
        inequívoca. A més, els marcs IFRAME han de disposar d’un atribut TITLE que descrigui clarament la seva finalitat
        o contingut.
    </span>
</p>
<br>
<p>
    <span >
        <strong>13. Enllaços descriptius: Enllaços sense text significatiu.</strong>
    </span>
</p>

<p>
    <span >
        S’han detectat enllaços que no contenen text. El text d’un enllaç ha de permetre identificar de manera clara la
        seva funció o el seu destí.
    </span>
</p>

<p>
    <span >
        Quan l’enllaç només conté una imatge o una icona, aquesta ha de disposar d’un text alternatiu significatiu.
        No és suficient que la informació només aparegui com a títol o tooltip si el contingut accessible de l’enllaç
        continua essent buit.
    </span>
</p>
<br>
<p>
    <span >
        <strong>14. Compatibilitat: Errors en el codi CSS.</strong>
    </span>
</p>

<p>
    <span >
        S’han detectat errors de sintaxi en els fulls d’estil CSS que poden dificultar-ne el processament correcte.
        El codi CSS ha de poder ser processat sense inconsistències pels diferents navegadors i aplicacions d’usuari.
    </span>
</p>

<p>
    <span >
        Cal revisar i corregir els errors de sintaxi detectats en els fulls d’estil, mantenint la compatibilitat amb els
        diferents navegadors i tecnologies de suport.
    </span>
</p>


<br>
<h2>Preparació de la present declaració d’accessibilitat</h2>

<p>
    La present declaració va ser preparada el
    <span >8 d´Octubre de 2026.</span>
</p>

<p>
    El mètode emprat per preparar la declaració ha estat una autoavaluació realitzada pel mateix organisme.
</p>

<br>
<h2>Observacions i dades de contacte</h2>

<p>
    El Govern de les Illes Balears pretén continuar millorant i oferir als ciutadans el millor servei possible.
    Podeu realitzar comunicacions sobre requisits d’accessibilitat (article 10.2.a del RD 1112/2018), com per exemple:
</p>

<ul>
    <li>Informar sobre qualsevol possible incompliment per part d’aquest lloc web.</li>
    <li>Transmetre altres dificultats d’accés al contingut.</li>
    <li>Formular qualsevol altra consulta o suggeriment de millora relativa a l’accessibilitat del lloc web.</li>
</ul>

<p>
    A través del següent formulari de
    <a href="https://www.caib.es/seucaib/es/200/persones/tramites/servicio/4055206/" target="_blank" rel="noopener noreferrer">
        contacte
    </a>
    o trucant al telèfon 971177140.
</p>

<p>Podeu presentar:</p>

<ul>
    <li>
        Una queixa relativa al compliment dels requisits del RD 1112/2018.
    </li>
    <li>
        Una sol·licitud d’informació accessible relativa a:
        <ul>
            <li>
                Continguts que estan exclosos de l’àmbit d’aplicació del RD 1112/2018 segons el que estableix l’article 3, apartat 4.
            </li>
            <li>
                Continguts que estan exempts del compliment dels requisits d’accessibilitat per imposar una càrrega desproporcionada.
            </li>
        </ul>
    </li>
</ul>

<p>
    A través del següent procediment:
    <a href="https://www.caib.es/seucaib/es/200/personas/tramites/tramite/4055271/" target="_blank" rel="noopener noreferrer">
        Peticions d’informació accessible i queixes relatives a l’accessibilitat de llocs web i aplicacions mòbils
    </a>.
</p>


<br>
<h2>Procediment d’aplicació</h2>

<p>
    El procediment de reclamació recollit a l’article 13 del RD 1112/2018 va entrar en vigor el 20 de setembre de 2020.
</p>

<p>
    Si un cop realitzada una sol·licitud d’informació accessible o una queixa, aquesta ha estat desestimada, no s’està d’acord amb la decisió adoptada,
    o la resposta no compleix els requisits contemplats a l’article 12.5, la persona interessada podrà iniciar una reclamació. Igualment, es podrà iniciar
    una reclamació en el cas que hagi transcorregut el termini de vint dies hàbils sense haver obtingut resposta.
</p>

<p>
    La reclamació pot ser presentada a través del procediment
    <a href="https://www.caib.es/seucaib/es/200/personas/tramites/tramite/4057494" target="_blank" rel="noopener noreferrer">
        Reclamacions relatives a l’accessibilitat de llocs web i aplicacions mòbils
    </a>.
</p>


<br>
<h2>Contingut opcional</h2>

<p>
    S’han utilitzat eines automàtiques per verificar l’accessibilitat de la pàgina (Observatori d’Accessibilitat).
    El resultat obtingut ha sigut el següent:
</p>

<table class="table table-bordered">
    <thead>
        <tr>
            <th></th>
            <th>Resultat</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Puntuació mitjana del lloc web</td>
            <td><span>4.92</span></td>
        </tr>
        <tr>
            <td>Nivell d’adequació estimat</td>
            <td><span>No v&agrave;lid</span></td>
        </tr>
        <tr>
            <td>Situació de compliment estimada</td>
            <td><span>No conforme</span></td>
        </tr>
    </tbody>
</table>
<br>
<!--
<p>
    <span >Nivell AA WCAG 2.1</span>
</p>

<br>
<p>
    El lloc web està dissenyat per a la seva visualització <i>responsive</i>, de manera que es visualitza de forma òptima en dispositius tauleta i mòbils.
</p>
-->
</div>