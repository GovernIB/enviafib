<%@page import="es.caib.enviafib.back.controller.user.FirmaPerFluxFirmaSimpleUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecAddicional2UserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaPlantillaFluxEntitatUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaPlantillaFluxUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaFluxUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.AutoFirmaUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaPerNifUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecAddicional1UserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecSecretariUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecGerentPresidentUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecCapAreaConsellerUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecEncarregatCompresUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecRecursosHumansUserController"%>
<%@page import="es.caib.enviafib.back.controller.user.FirmaCarrecDirectorUserController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="org.springframework.security.core.Authentication"%><%@page
    import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
    import="org.springframework.security.core.context.SecurityContext"%><%@page
    import="org.springframework.security.core.context.SecurityContextHolder"%><%@page
    import="es.caib.enviafib.back.security.LoginInfo"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ConstantsEnviaFIB" className="es.caib.enviafib.commons.utils.Constants" />

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



        <li style="list-style-type: disc; list-style-position: inside;">
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

        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/peticio/rebutjades/list"/>">
                <span
                style="${(fn:contains(url, 'user/peticio/rebutjades/list'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.peticionsusuari.rebutjades" /></span>
        </a>
        </li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />



        <%--
        
        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/autofirma/new"/>"> <span
                style="${(fn:contains(url, '/user/autofirma'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.autofirma" /></span>
        </a>
        </li>


        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/firmapernif/new"/>"> <span
                style="${(fn:contains(url, '/user/firmapernif/new'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmapernif" /></span>
        </a>
        </li>


        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/flux/crearflux"/>"> <span
                style="${(fn:contains(url, '/user/flux'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmaperflux" /></span>
        </a>
        </li>


        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/firmadirector/new"/>"> <span
                style="${(fn:contains(url, '/firmadirector'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmacarrec.director" /></span>
        </a>
        </li>

        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/firmasecretari/new"/>"> <span
                style="${(fn:contains(url, '/firmasecretari'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmacarrec.secretari" /></span>
        </a>
        </li>

        <li style="list-style-type: disc; list-style-position: inside;">
            <a
            href="<c:url value="/user/firmaplantillafluxusuari/new"/>">
                <span
                style="${(fn:contains(url, '/firmaplantillafluxusuari'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmaplantillaflux.usuari" /></span>
        </a>
        </li>

        <li style="list-style-type: disc; list-style-position: inside;">
            <a
            href="<c:url value="/user/firmaplantillafluxentitat/new"/>">
                <span
                style="${(fn:contains(url, '/firmaplantillafluxentitat'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.firmaplantillaflux.entitat" /></span>
        </a>
        </li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/plantillesfluxfirmes/list"/>">
                <span
                style="${(fn:contains(url, '/user/plantillesfluxfirmes'))? "font-weight:bold;" : ""}"><fmt:message
                        key="plantillesfluxfirmes.plural" /></span>
        </a>
        </li>
--%>



        <%-- =============== OPCIONS DINAMIQUES --%>


        <%-- QUAN NO TE CAP MENU ... --%>

        <c:if test="${empty menus}">
            <b style="color: red"> <fmt:message
                    key="menu.error.buit" />
            </b>
        </c:if>


        <c:set var="mostrarPlantilles" value="${false}" />



        <c:forEach items="${menus}" var="menu" varStatus="varStatus">




            <c:choose>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_AUTOFIRMA}">
                    <%                             
                     pageContext.setAttribute("urlBlack", AutoFirmaUserController.CONTEXT_WEB);
                     %> 
                </c:when>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PER_NIF}">
                    <%                             
                     pageContext.setAttribute("urlBlack", FirmaPerNifUserController.CONTEXT_WEB);
                     %> 
                </c:when>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_FLUX}">
                   <%                             
                     pageContext.setAttribute("urlBlack", FirmaFluxUserController.CONTEXT_WEB);
                     %> 
                </c:when>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI}">
                    <%                             
                     pageContext.setAttribute("urlBlack", FirmaPlantillaFluxUserController.CONTEXT_WEB);
                     %> 
                </c:when>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT}">
                    <%                             
                     pageContext.setAttribute("urlBlack", FirmaPlantillaFluxEntitatUserController.CONTEXT_WEB);
                     %> 
                </c:when>


                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT}">
                    <%                             
                     pageContext.setAttribute("urlBlack", FirmaPerFluxFirmaSimpleUserController.CONTEXT_WEB);
                     %> 
                </c:when>

                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON}">
                    <c:set var="urlBlack"
                        value="XYZ ZZZ No implementat MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON" />
                </c:when>


                <c:when
                    test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_CARREC}">


                    <c:choose>

                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_GERENT_PRESIDENT}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecGerentPresidentUserController.CONTEXT_WEB);
                             %> 
                        </c:when>

                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_CAP_AREA_CONSELLER}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecCapAreaConsellerUserController.CONTEXT_WEB);
                             %> 
                        </c:when>
                        
                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecDirectorUserController.CONTEXT_WEB);
                             %>    
                                 
                        </c:when>

                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_SECRETARI}">
                            <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecSecretariUserController.CONTEXT_WEB);
                             %> 
                        </c:when>

                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_ENCARREGAT_COMPRES}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecEncarregatCompresUserController.CONTEXT_WEB);
                             %> 
                        </c:when>

                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_RECURSOS_HUMANS}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecRecursosHumansUserController.CONTEXT_WEB);
                             %> 
                        </c:when>
                        
                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_ADDICIONAL_1}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecAddicional1UserController.CONTEXT_WEB);
                             %> 
                        </c:when>
                        
                        <c:when
                            test="${menu.parametreCombo eq ConstantsEnviaFIB.CARREC_ADDICIONAL_2}">
                             <%                             
                             pageContext.setAttribute("urlBlack", FirmaCarrecAddicional2UserController.CONTEXT_WEB);
                             %> 
                        </c:when>

                        <c:otherwise>
                            <c:set var="urlBlack"
                                value="TIPUS CARREC NO DEFINIT ${menu.parametreCombo}" />
                        </c:otherwise>
                    </c:choose>

                </c:when>
                <%-- FINAL DE TIPUS CARREC --%>

                <c:otherwise>
                    <c:set var="urlBlack"
                        value="/user/menu/show/${menu.menuID}" />
                </c:otherwise>
            </c:choose>


            <li
                style="list-style-type: disc; list-style-position: inside;">
                <a
                href="<c:url value="/user/menu/show/${menu.menuID}/${menu.tipus}"/>">
                    <span style="${(fn:contains(url, urlBlack))? "font-weight:bold;" : ""}">
                        ${menu.titolMenu.traduccions[lang].valor} </span> <i
                    class="fas fa-info-circle"
                    title="${menu.ajudaMenu.traduccions[lang].valor}"></i>
            </a>
            </li>
            <c:if
                test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI}">
                <c:set var="mostrarPlantilles" value="${true}" />
            </c:if>
        </c:forEach>

        <c:if test="${mostrarPlantilles}">

            <hr style="margin-top: 6px; margin-bottom: 6px;" />

            <li
                style="list-style-type: disc; list-style-position: inside;">
                <a
                href="<c:url value="/user/plantillesfluxfirmes/list"/>">
                    <span
                    style="${(fn:contains(url, '/user/plantillesfluxfirmes'))? "font-weight:bold;" : ""}"><fmt:message
                            key="plantillesfluxfirmes.plural" /></span>
            </a>
            </li>
        </c:if>


    </ul>



    <c:if test="${LoginInfo.getInstance().getRoles() == '[ROLE_USER]'}">
        <hr style="margin-top: 6px; margin-bottom: 6px;" />
        <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="/user/home"/>"> <span
                style="${(fn:contains(url, '/home'))? "font-weight:bold;" : ""}"><fmt:message
                        key="user.menu.home" /></span>
        </a>
        </li>
    </c:if>
</div>

