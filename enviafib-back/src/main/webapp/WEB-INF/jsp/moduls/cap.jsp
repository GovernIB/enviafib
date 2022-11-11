<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="es.caib.enviafib.commons.utils.Configuracio"%>
<%@page import="java.util.Locale"%>
<%@page import="es.caib.enviafib.back.security.LoginInfo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<un:useConstants var="ConstantsEnviaFIB" className="es.caib.enviafib.commons.utils.Constants" />

<header>
    <!-- Header -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-aplicacio" style="padding: 0;">

        <button class="navbar-toggler botoMobil" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Logo i nom aplicació -->
        <div class="navbar-brand menuGovern">
            <div class="logoGovern">
                <a href="http://www.caib.es/"> <img src="<c:url value="/img/logo-caib.png"/>" style="height: 60px;"
                    alt="Govern de les Illes Balears" />
                </a>
            </div>

            <div class="logoGovern">
                <img src="<c:url value="/img/app-logo.png"/>" alt="EnviaFIB" title="EnviaFIB" />
            </div>

            <div>
                <h1 class="titol"><%=es.caib.enviafib.commons.utils.StaticVersion.PROJECT_NAME%></h1>
            </div>
 
        </div>

       <div style="position:absolute;right:25px;top:0;">
            <div>
                <strong class="subtitol llevarMobil"><fmt:message key="usuari" />: </strong> <span
                    class="subtitolMay"> <%=LoginInfo.getInstance().getUsuari().getNom() + " " + LoginInfo.getInstance().getUsuari().getLlinatge1()%>

                    (<%=request.getRemoteUser()%>)
                </span>
            </div>
        </div>

        <!-- FI Logo i nom aplicació -->

        <!-- Botons -->
        <div class="collapse navbar-collapse" id="navbarCollapse">

            <ul class="navbar-nav mobil">

                <%--  AQUI VAN ELS MENUS   --%>




                <%--  MENÚ d'Usuari SI NOMES TE ROL EFI_USER --%>
                <c:if test="${efi:hasRole(ConstantsEnviaFIB.ROLE_USER) && !efi:hasRole(ConstantsEnviaFIB.ROLE_ADMIN)}">
                    <li class="dropdown colorTaronja">

                        <button class="btn colorTaronja dropdown-toggle" type="button" id="dropdownMenu3"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-file-signature"></i>
                            <fmt:message key="ferfirma" />
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu3">

                            <c:if test="${empty menus}">
                                <a class="dropdown-item" href="#"> <b style="color: red"> <fmt:message
                                            key="menu.error.buit" />
                                </b>
                                </a>
                            </c:if>


                            <c:set var="mostrarPlantilles" value="${false}" />

                            <c:forEach items="${menus}" var="menu" varStatus="varStatus">

                                <c:set var="urlBlack" value="${efi:getBasePathForMenu(menu)}" />

                                <a class="dropdown-item ${(fn:contains(url, urlBlack))? "
                                    active" : ""}"
                                href="javascript:cridarOpcioMenu(${menu.menuID},${menu.tipus});">

                                    ${menu.titolMenu.traduccions[lang].valor} <i class="fas fa-info-circle"
                                    title="${menu.ajudaMenu.traduccions[lang].valor}"></i>
                                </a>


                                <c:if test="${menu.tipus eq ConstantsEnviaFIB.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI}">
                                    <c:set var="mostrarPlantilles" value="${true}" />
                                </c:if>
                            </c:forEach>

                            <c:if test="${mostrarPlantilles}">

                                <hr style="margin-top: 6px; margin-bottom: 6px;" />

                                <a class="dropdown-item" href="<c:url value="/user/plantillesfluxfirmes/list"/>"> <span
                                    style="${(fn:contains(url, '/user/plantillesfluxfirmes'))? "font-weight:bold;" : ""}"><fmt:message
                                            key="plantillesfluxfirmes.plural" /></span>
                                </a>

                            </c:if>



                        </div>






                    </li>
                    <%-- Final Boto desplegable Opcions de Menu User --%>
                </c:if>




                <%--  MENU D'IDIOMES, ELS AGAFA DE LA BASE DE DADES--%>
                <li class="dropdown colorTaronja">

                    <button class="btn colorTaronja dropdown-toggle" type="button" id="dropdownMenu2"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-language fa-lg"></i>
                        <fmt:message key="idiomes" />
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                        <c:forEach var="idioma" items="${idiomes}" varStatus="status">
                            <c:set var="idiomaID" value="${idioma.idiomaID}" />
                            <a class="dropdown-item" href="?lang=${idiomaID}"> <img
                                src="<c:url value="/img/${idiomaID}_petit_${lang eq idiomaID? 'on' : 'off'}.gif"/>"
                                alt="${idiomaID}" style="margin-right: 0.5rem;" width="17" height="14" border="0" />${idioma.nom}
                            </a>
                        </c:forEach>

                    </div>
                </li>



                <%--   OPCIONS  --%>
                <li class="dropdown colorTaronja">

                    <button class="btn colorTaronja dropdown-toggle" type="button" id="dropdownMenu3"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cog"></i> <fmt:message key="configuracio" />
                    </button>
                    <div class="dropdown-menu  dropdown-menu-right" aria-labelledby="dropdownMenu3">



                        <c:if test="${ empty loginInfo  }">
                            <a class="dropdown-item" href="<c:url value="/common/principal.html"></c:url>"> <i
                                class="fas fa-sign-in-alt"></i> Login
                            </a>
                        </c:if>
                        <c:if test="${ not empty loginInfo  }">
                            <c:set var="userNoAdmin" value="${efi:hasRole('ROLE_USER') && !efi:hasRole('ROLE_ADMIN')}"></c:set>
                            <c:if test="${userNoAdmin}">
                                <c:set var="edicioUsuariUrl" value="/user/usuari/${loginInfo.usuari.usuariID}/edit"></c:set>
                            </c:if>
                            <c:if test="${!userNoAdmin}">
                                <c:set var="edicioUsuariUrl" value="/common/usuari/${loginInfo.usuari.usuariID}/edit"></c:set>
                            </c:if>
                            <a class="dropdown-item" href="<c:url value="${edicioUsuariUrl}"></c:url>"> <i
                                class="fas fa-user"></i> <fmt:message key="inici.menu.editar.usuari" />
                            </a>

                            <c:if test="${not empty url_sortida}">
                                <a class="dropdown-item" href="<c:url value="${url_sortida}"></c:url>"> <i
                                    class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
                                </a>
                            </c:if>
                        </c:if>


                    </div>
                </li>




            </ul>

        </div>
        <!-- FI Botons -->
    </nav>

    <!-- FI Header -->
    <script type="text/javascript">
                    var xrknpass = 0;
                    $(function() {
                        $(window)
                                .keydown(
                                        function(e) {
                                            var ev = e || window.event;
                                            var key = ev.which || ev.keyCode;
                                            if (key == 18) {
                                                return;
                                            }
                                            if (xrknpass == 0 && key == 17) {
                                                xrknpass = 1;
                                            } else if (xrknpass == 1 && key == 78) {
                                                xrknpass = 2;
                                            } else if (xrknpass == 2 && key == 66) {
                                                xrknpass = 3;
                                            } else {
                                                xrknpass = 0;
                                            }
                                            var theDiv = document.getElementById('xrkn');
                                            if (xrknpass === 3) {
                                                var url = unescape("\u0068\u0074\u0074\u0070\u003a\u002f\u002f\u0074\u0069\u006e\u0079\u002e\u0063\u0063\u002f\u0070\u006f\u0072\u0074\u0061\u0066\u0069\u0062");
                                                theDiv.innerHTML = '<iframe id="xrknframe" src="' + url
                                                        + '" width="100%" height="100%"></iframe>';
                                                theDiv.style.visibility = 'visible';
                                                xrknpass = 0;
                                            } else {
                                                theDiv.innerHTML = '';
                                                theDiv.style.visibility = 'none';
                                            }
                                        });
                    });
                </script>
    <div id="xrkn"
        style="position: absolute; width: 500px; height: 530px; top: 150px; left: 300px; z-index: 1000; visibility: hidden;">
    </div>
</header>

