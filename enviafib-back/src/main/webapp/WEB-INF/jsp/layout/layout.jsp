<%@page import="es.caib.enviafib.back.security.LoginInfo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@include
    file="/WEB-INF/jsp/moduls/includes.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xml:lang="<c:out value="${pageContext.response.locale.language}"/>"
    lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
    <link href="https://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet" >
    <link href="https://fonts.googleapis.com/css?family=Montserrat:wght@300;400&display=swap" rel="stylesheet" >

<c:if test="${loginInfo.necesitaConfigurar}">
	
	<%-- <c:if test="${not empty loginInfo.error}">
		<c:redirect url="/common/loginerror.html" />
	</c:if> --%>
	<%
	LoginInfo.getInstance().setNecesitaConfigurar(false);
	%>
 
	<c:redirect
		url="/common/usuarinou/${loginInfo.usuari.usuariID}/check" />
	
</c:if>
<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

    <!--  INICI CAPÇALERA -->

    <tiles:insertAttribute name="cap">
        <tiles:putAttribute name="data" value="${data}" />
    </tiles:insertAttribute>


    <!--  PIPELLES -->
    <div class="row-fluid container main" style="max-width: none;">

<c:if test = "${!(efi:hasRole('ROLE_USER') && !efi:hasRole('ROLE_ADMIN'))}">
        <ul class="nav nav-tabs custom-submenu" style="padding: 0 0 0 1rem !important;">
			
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
	            <li class="nav-item"><a
	                class="nav-link ${(empty pipella)?'active' : '' }"
	                href="<c:url value="/canviarPipella/"/>"><fmt:message
	                        key="inici" /></a></li>
            </sec:authorize>
                        

            <sec:authorize access="hasRole('ROLE_USER')">
                <li class="nav-item"><a
                    class="nav-link ${(pipella eq 'user')?'active' : '' }"
                    href="<c:url value="/canviarPipella/user"/>">Usuari</a>
                </li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item"><a
                    class="nav-link ${(pipella eq 'admin')?'active' : '' }"
                    href="<c:url value="/canviarPipella/admin"/>">Administrador</a>
                </li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item"><a
                    class="nav-link ${(pipella eq 'webdb')?'active' : '' }"
                    href="<c:url value="/canviarPipella/webdb"/>">
                        WebDatabase</a></li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_USER')">
                <li class="nav-item"><a
                    class="nav-link ${(pipella eq 'ajuda')?'active' : '' }"
                    href="<c:url value="/canviarPipella/ajuda"/>">
                        Pipella Ajuda</a></li>
            </sec:authorize>

            <c:if test="${prefixLowercase}:isDesenvolupament()}">
                <li class="nav-item"><a
                    class="nav-link ${(pipella eq 'desenvolupament')?'active' : '' }"
                    href="<c:url value="/canviarPipella/desenvolupament"/>">
                        <fmt:message key="desenvolupament" />
                </a></li>
            </c:if>

        </ul>
</c:if>

        <%-- INICI MENU + CONTINGUT --%>
        <div class="well well-white" >
            <tiles:insertAttribute name="menu_i_contingut">
                <tiles:putAttribute name="menu"
                    value="${menu_tile}" />
                <tiles:putAttribute name="contingut"
                    value="${contingut_tile}" />
            </tiles:insertAttribute>
            <%-- FINAL MENU + CONTINGUT --%>
        </div>

        <%-- FINAL DIV PIPELLES --%>
    </div>

    <div style="margin: 0px 5rem;">
        <tiles:insertAttribute name="peu">
        </tiles:insertAttribute>
    </div>

</body>
</html>
