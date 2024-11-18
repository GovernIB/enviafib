<%@page import="es.caib.enviafib.back.preparer.MenuPreparer"%>
<%@page import="es.caib.enviafib.model.entity.Menu"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="es.caib.enviafib.commons.utils.Configuracio"%>
<%@page import="java.util.Locale"%>
<%@page import="es.caib.enviafib.back.security.LoginInfo"%>
<%@page import="es.caib.enviafib.back.controller.user.MenuUserController"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<un:useConstants var="ConstantsEnviaFIB" className="es.caib.enviafib.commons.utils.Constants" />

<header>
    <!-- Header -->
	<!-- set entitat actual.  -->
	<c:set var="entitatActual" value="${loginInfo.entitatRolsActual.entitat}" />
 
    <nav id="nav-cap" class="navbar navbar-expand-md navbar-dark">

        <button class="navbar-toggler botoMobil" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Logo i nom aplicació -->
		<div id="logoEntitatContainer" class="logoEntitat">

			<a href="${entitatActual.web}"> <img
				src="<c:url value="${efi:fileUrl(entitatActual.logoweb)}"/>"
				style="height: 55px;" alt="${entitatActual.descripcio}" />
			</a>
		</div>

		<div id="logoEnviafibContainer" class="logoEntitat">
			<img src="<c:url value="/img/app-logo.png"/>" style="height: 4rem;"
				alt="EnviaFIB" title="EnviaFIB" />
		</div>
		
        <div id="menuCapContainer" >
        	<ul class="navbar-nav mobil">

				<%-- ENTITAT DE L'USUARI --%>
				
				<li id="entitatInfoContainer" class="menuCapItem dropdown"><i
					class="fas fa-university"></i>
					<span id="entitat-descripcio"> ${entitatActual.descripcio} </span>

					<!-- Si solo hay una entidad, no mostrar desplegable -->
					<c:if test="${loginInfo.mapEntitatsAdmin.size() > 1}">
						<span class="dropdown-toggle" type="button"
						id="dropdownMenuEntitat" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> </span>
						
						<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuEntitat">
							<c:forEach var="entry" items="${loginInfo.mapEntitatsAdmin}">
							
								<!--  Si entidad es actual, no añadir al listado -->
								<c:if test="${entry.key ne entitatActual.entitatid}"> 
									<a class="dropdown-item" href="<c:url value="/canviarEntitat/${entry.key}"/>"> ${entry.value.entitat.descripcio} - Roles: ${entry.value.roles}</a> 
								</c:if>
							</c:forEach>
	
						<!-- <a class="dropdown-item" href="http://www.caib.es"> Govern de les Illes Balears</a> 
						 	 <a class="dropdown-item" href="https://www.fundaciobit.org/es/inicio/"> Fundacio BIT</a> -->
						</div>
					</c:if>
				</li>

				<%--  PIPELLES SEGONS EL ROL DE L'USUARI --%>
        		<c:if test = "${efi:hasRole('ROLE_ADMIN')}">
					<li id="rolInfoContainer" class="menuCapItem dropdown" onclick="location='<c:url value="/canviarPipella/${pipella}"/>'">
						<i class="fas fa-address-card"></i>


						<span class="dropdown-toggle" type="button"
							id="dropdownMenuRol" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							
							<c:if test="${not empty pipella}">
						    	<fmt:message key="${pipella}" />
							</c:if>
							<c:if test="${empty pipella}">
						    	<fmt:message key="inici" />
							</c:if>
						</span>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="dropdownMenuRol">
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<c:if test="${not empty pipella}">
								<a class="dropdown-item" href="<c:url value="/canviarPipella/"/>"><fmt:message
										key="inici" /></a>
								</c:if>
								
								
							</sec:authorize>
	
							<sec:authorize access="hasRole('ROLE_USER')">
							<!-- Si entitat actual es la mateixa entitat que l'usuari, mostrar pipella user -->
								<c:if test="${entitatActual.entitatid eq loginInfo.usuari.entitatID}">
									<c:if test="${pipella ne 'user'}">
										<a class="dropdown-item"
											href="<c:url value="/canviarPipella/user"/>">Usuari</a>
									</c:if>
								</c:if>
							</sec:authorize>
	
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<c:if test="${pipella ne 'admin'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/admin"/>">Administrador</a>
								</c:if>
							</sec:authorize>
	
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<c:if test="${pipella ne 'webdb'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/webdb"/>">WebDatabase</a>
								</c:if>
							</sec:authorize>
	
							<sec:authorize access="hasRole('ROLE_USER')">
								<c:if test="${pipella ne 'ajuda'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/ajuda"/>">Pipella Ajuda</a>
								</c:if>
							</sec:authorize>

							<sec:authorize access="hasRole('ROLE_USER')">
							
								<c:set var="isAden" value="false" />
								<c:forEach var="rol" items="${loginInfo.entitatRolsActual.roles}">
								  <c:if test="${rol eq 'ROLE_ADEN'}">
								    <c:set var="isAden" value="true" />
								  </c:if>
								</c:forEach>
							
								<c:if test="${isAden}">
	                                <c:if test="${pipella ne 'aden'}">
										<a class="dropdown-item"
											href="<c:url value="/canviarPipella/aden"/>">Admin Entitat</a>
									</c:if>
								</c:if>
							</sec:authorize>


	
							<c:if test="${prefixLowercase}:isDesenvolupament()}">
								<c:if test="${pipella ne 'desenvolupament'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/desenvolupament"/>"><fmt:message
											key="desenvolupament" /></a>
								</c:if>
							</c:if>
						</div>
					</li>
				</c:if>
				
				<%--  CONFIGURACIÓ DE L'USUARI AMB MENU D'IDIOMES  --%>
				<li id="userInfoContainer" class="menuCapItem dropdown">
					<i class="fa fa-user"></i>
					<span class="dropdown-toggle" type="button"
						id="dropdownMenuUser" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<%=
						   LoginInfo.getInstance().getUsuari().getNom() + " " 
					       + LoginInfo.getInstance().getUsuari().getLlinatge1() + " ("
						   + request.getRemoteUser() + ")"
					    %>
					</span>
	            	
	            	<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenuUser">

						<c:if test="${empty loginInfo}">
							<a class="dropdown-item"
								href="<c:url value="/common/principal.html"></c:url>"> <i
								class="fas fa-sign-in-alt"></i> Login
							</a>
						</c:if>
						
						<c:if test="${not empty loginInfo}">
							<c:set var="userNoAdmin"
								value="${efi:hasRole('ROLE_USER') && !efi:hasRole('ROLE_ADMIN')}"></c:set>
							<c:if test="${userNoAdmin}">
								<c:set var="edicioUsuariUrl"
									value="/user/usuari/${loginInfo.usuari.usuariID}/edit"></c:set>
							</c:if>
							<c:if test="${!userNoAdmin}">
								<c:set var="edicioUsuariUrl"
									value="/common/usuari/${loginInfo.usuari.usuariID}/edit"></c:set>
							</c:if>
							
							<a class="dropdown-item"
								href="<c:url value="${edicioUsuariUrl}"></c:url>"> <fmt:message
									key="inici.menu.editar.usuari" />
							</a>

							<hr style="margin: 6px 6px;" />

							<div id="titol-idiomes" class="dropdown-item">
								<!-- <i class="fas fa-language fa-lg"></i> -->
								<fmt:message key="idiomes" />
							</div>

							<c:forEach var="idioma" items="${idiomes}" varStatus="status">
								<c:set var="idiomaID" value="${idioma.idiomaID}" />
								<a class="dropdown-item" href="?lang=${idiomaID}"> <img
									src="<c:url value="/img/${idiomaID}_petit_${lang eq idiomaID? 'on' : 'off'}.gif"/>"
									alt="${idiomaID}" style="margin-right: 0.5rem;" width="17"
									height="14" border="0" />${idioma.nom}
								</a>
							</c:forEach>

							<c:if test="${not empty url_sortida}">
								<a class="dropdown-item"
									href="<c:url value="${url_sortida}"></c:url>"> <i
									class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
								</a>
							</c:if>
						</c:if>
						
					</div>
				</li>
			</ul>
		</div>
		<!-- FI Logo i nom aplicació -->

        <!-- Botons -->
        <div id="botoneraCapContainer" class="collapse navbar-collapse" id="navbarCollapse">

            <ul class="navbar-nav mobil">
                <%--  MENÚ d'Usuari SI NOMES TE ROL EFI_USER --%>
                <c:if test="${efi:hasRole(ConstantsEnviaFIB.ROLE_USER)}">
					<li class="dropdown">

                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-file-signature"></i>
                            <fmt:message key="ferfirma" />
                        </button>
                        
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                            <c:if test="${empty menus}">
                                <a class="dropdown-item" href="#">
                                    <b style="color: red"> <fmt:message key="menu.error.buit" /></b>
                                </a>
                            </c:if>

							<c:set var="mostrarPlantilles" value="${false}" />

							<c:forEach items="${menus}" var="menu" varStatus="varStatus">

                                <c:set var="urlBlack" value="${efi:getBasePathForMenu(menu)}" />

                                <a class="dropdown-item ${(fn:contains(url, urlBlack))? "active" : ""}"
                                    href="javascript:cridarOpcioMenu(${menu.menuID},${menu.tipus});">

                                    ${menu.titolMenu.traduccions[lang].valor} &nbsp;
                                    <i class="fas fa-info-circle" title="${menu.ajudaMenu.traduccions[lang].valor}"></i>
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
                </c:if>


                <%--   FAQs  --%>
                <li class="dropdown">
					<button class="btn btn-secondary" type="button"
						id="dropdownMenu2" onclick="location.href='<c:url value="/ajuda/faq/list/1"></c:url>'">
						<i class="fas fa-question"></i> FAQs
					</button>
                </li>
			</ul>
        <!-- FI Botons -->
    </nav>
</header>





<!-- CAPÇALERA MODERNA -->
<script type="text/javascript">
    $('.subtitolMay').css('font-size', '1rem !important');
</script>

<style>
header {
/* 	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 5rem;
	height: 7rem; */
	z-index: 10;
	background-color: #fff;
	margin-bottom: 2rem;
}

#nav-cap {
	box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15);
	padding: 0 5rem;
	height: 6rem;
}

.subtitolMay {
	font-size: 1rem;
}

#menu_i_contingut {
	padding: 0rem 8rem;
/* 	padding-top: 8rem; */
}

#logoEntitatContainer {
	border-right: 1px solid black;
}

#botoneraCapContainer {
	margin-top: 2.5rem;
	/*   margin-bottom: 1rem; */
}

#botoneraCapContainer button {
	padding: 0.2rem 0.65rem;
}

#menuCapContainer {
	position: absolute;
	top: 11px;
	right: 88px;
	display: flex;
	color: black;
}

.menuCapItem span {
	color: black;
	text-transform: uppercase;
	margin-left: 4px;
}

#menuCapContainer li {
  padding: 0 1rem;
  border-right: 1px solid black;
  cursor: pointer;
}

#menuCapContainer li:last-child {
  border: none;
  padding-right: 0px;
}

@font-face {
	font-family: CaviarDreamsFont;
	src: url(/fonts/RubikGlitch-Regular.ttf);
}

h1 {
	font-family: CaviarDreamsFont;
	color: darkgreen;
}

#FilterButton, #GroupButton {
	background-color: #E1E1E1;
	border-color: #E1E1E1;
	margin-left: 3px;
}

#FilterButton:hover, #GroupButton:hover {
	background-color: #A1A1A1;
	border-color: #A1A1A1;
}

.dropdown .btn-secondary {
	margin: 0 5px;
}

.dropdown-menu {
	margin-top: 0px;
}

#titol-idiomes {
	color: #314b87;
	font-weight: bold;
}

#titol-idiomes:hover {
	background-color: transparent;
}

#nomApp {
	text-transform: uppercase;
	font-size: 2rem;
	margin: 0;
	font-family: 'Montserrat', serif;
	color: black;
}
</style>


<script>
    function cridarOpcioMenu(menuID, tipus) {
        window.location = '<c:url value="/user/menu/show/"/>' + menuID + '/' + tipus + '/' + btoa(window.location);
    }
</script>

