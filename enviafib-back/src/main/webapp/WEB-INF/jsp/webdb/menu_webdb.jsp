<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Fitxer --%>
       <fmt:message var="entityname" key="fitxer.fitxer.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Idioma --%>
       <fmt:message var="entityname" key="idioma.idioma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- InfoArxiu --%>
       <fmt:message var="entityname" key="infoArxiu.infoArxiu.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/infoArxiu/list/1"/>" ><span style="${(fn:contains(url, 'infoArxiu/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- InfoSignatura --%>
       <fmt:message var="entityname" key="infoSignatura.infoSignatura.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/infoSignatura/list/1"/>" ><span style="${(fn:contains(url, 'infoSignatura/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Peticio --%>
       <fmt:message var="entityname" key="peticio.peticio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/peticio/list/1"/>" ><span style="${(fn:contains(url, 'peticio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Plugin --%>
       <fmt:message var="entityname" key="plugin.plugin.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plugin/list/1"/>" ><span style="${(fn:contains(url, 'plugin/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- SerieDocumental --%>
       <fmt:message var="entityname" key="serieDocumental.serieDocumental.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/serieDocumental/list/1"/>" ><span style="${(fn:contains(url, 'serieDocumental/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Traduccio --%>
       <fmt:message var="entityname" key="traduccio.traduccio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Usuari --%>
       <fmt:message var="entityname" key="usuari.usuari.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuari/list/1"/>" ><span style="${(fn:contains(url, 'usuari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
