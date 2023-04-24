<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="org.springframework.security.core.Authentication"%><%@page
    import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
    import="org.springframework.security.core.context.SecurityContext"%><%@page
    import="org.springframework.security.core.context.SecurityContextHolder"%><%@page
    import="es.caib.enviafib.back.security.LoginInfo"%><%@page
    import="es.caib.enviafib.back.controller.user.MenuUserController"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ConstantsEnviaFIB"
    className="es.caib.enviafib.commons.utils.Constants" />

<c:set var="url" value="${urlActual}" />
<div>
    <h5>
        <fmt:message key="user.menu.title" />
    </h5>
    <ul class="tree" style="margin: 3px; padding: 0px;">

        <%-- Example with security: virtual roles  --%>
        <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/user/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>

        



<%--         <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/peticio/pendents/list"/>">
                <span
                style="${(fn:contains(url, 'user/peticio/pendents/list'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.peticionsusuari.pendents" /></span>
        </a>
        </li>

        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/peticio/firmades/list"/>">
                <span
                style="${(fn:contains(url, 'user/peticio/firmades/list'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.peticionsusuari.firmades" /></span>
        </a>
        </li>
 --%>
 
        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/peticio/list"/>">
                <span
                style="${(fn:contains(url, 'user/peticio/list'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.peticionsusuari" /></span>
        </a>
        </li>

        





        <%-- =============== OPCIONS DINAMIQUES --%>
<%--         <c:if test="${efi:hasRole(ConstantsEnviaFIB.ROLE_USER) && efi:hasRole(ConstantsEnviaFIB.ROLE_ADMIN)}">
            
            <hr style="margin-top: 6px; margin-bottom: 6px;" />

            QUAN NO TE CAP MENU ...
            <c:if test="${empty menus}">
                <b style="color: red"> 
                    <fmt:message key="menu.error.buit" />
                </b>
            </c:if>

            <c:forEach items="${menus}" var="menu" varStatus="varStatus">

                <c:set var="urlBlack" value="${efi:getBasePathForMenu(menu)}" />

                <li style="list-style-type: disc; list-style-position: inside;">
                    <a href="javascript:cridarOpcioMenu(${menu.menuID},${menu.tipus});">
                        <span style="${(fn:contains(url, urlBlack))? "font-weight:bold;" : ""}">
                            ${menu.titolMenu.traduccions[lang].valor} </span> 
                        <i class="fas fa-info-circle" title="${menu.ajudaMenu.traduccions[lang].valor}"></i>
                    </a>
                </li>
                
                <c:if test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI}">
                    <c:set var="mostrarPlantilles" value="${true}" />
                </c:if>
            </c:forEach>
        </c:if>
 --%>


        <c:if test="${efi:hasRole(ConstantsEnviaFIB.ROLE_USER)}">
            <c:set var="mostrarPlantilles" value="${false}" />

            <c:if test="${mostrarPlantilles}">

                <hr style="margin-top: 6px; margin-bottom: 6px;" />

                <li style="list-style-type: disc; list-style-position: inside;">
                    <a href="<c:url value="/user/plantillesfluxfirmes/list"/>">
                        <span style="${(fn:contains(url, '/user/plantillesfluxfirmes'))? "font-weight:bold;" : ""}">
                        <fmt:message key="plantillesfluxfirmes.plural" /></span>
                    </a>
                </li>
            </c:if>
        </c:if>
        
                
        <c:if
            test="${efi:hasRole(ConstantsEnviaFIB.ROLE_USER) && !efi:hasRole(ConstantsEnviaFIB.ROLE_ADMIN)}">
            <hr style="margin-top: 6px; margin-bottom: 6px;" />
            <li style="list-style-type: disc; list-style-position: inside;">
                <a href="<c:url value="/user/home"/>"> 
                    <span style="${(fn:contains(url, '/home'))? "font-weight:bold;" : ""}">
                    <fmt:message key="user.menu.home" /></span>
                </a>
            </li>
        </c:if>
        

    </ul>


</div>

<script>
    function cridarOpcioMenu(menuID, tipus) {
        window.location = '<c:url value="/user/menu/show/"/>' + menuID + '/' + tipus + '/' + btoa(window.location);
    }
</script>

