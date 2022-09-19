<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ConstantsEnviaFIB" className="es.caib.enviafib.commons.utils.Constants" />




<div style="text-align: center;">
    <p>
        <img src="" alt="Carpeta" title="Carpeta" />
    </p>
    <h3>
        <spring:message  code="menu.creacio.title" />
    </h3>
</div>


<div class="container">

    <c:forEach items="${ConstantsEnviaFIB.MENU_FIRMA_TIPUS}"
        var="menuID" varStatus="varStatus">

        <div class="card" style="width:fit-content;margin:2px">
            <div class="card-body">
                <a href="<c:url value="/admin/menu/new?menuID=${menuID}"/>" class="btn btn-primary">
                    ${varStatus.index} <h5 class="card-title">
                        <spring:message code="menu.firma.${menuID}" />
                    </h5>
                    <p class="card-text">
                       <i> <spring:message  code="menu.firma.${menuID}" /> XYZ FALTA DESCRIPCIO</i>
                    </p>

                 </a> 
                
            </div>
        </div>

    </c:forEach>
</div>


