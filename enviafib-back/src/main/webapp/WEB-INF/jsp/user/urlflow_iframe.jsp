<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${not empty wizardstep}">

<label style="font-size: 1.25rem;font-weight: bold;">

<c:out value="${titolFluxFirma}" default="Aqui hauria de possar FIRMA PERSONALITAZA"/>

<%-- 
<fmt:message key="menu.flux.title" />

 --%>
</label>


<%@include file="wizard_component.jsp" %>

<c:if test="${not empty title}">
<h3>${title}</h3> 
</c:if>

<c:if test="${not empty continueUrl}">
<input type="button" class="btn btn-succes btn-primary" style="margin:20px" value="<fmt:message key="genapp.continue"/>" onclick='location.href="<c:url value="${continueUrl}"/>";'>
</c:if>
<c:if test="${not empty cancelUrl}">
<input type="button" class="btn btn-warn btn-secondary" style="margin:20px" value="<fmt:message key="genapp.cancel" />"  onclick='location.href="<c:url value="${cancelUrl}"/>";'>
</c:if>
<br/>

</c:if>

<c:if test="${not empty urlflow}">
<center>
<iframe id="iframediv" src="${urlflow}" width="100%" height="900px" ${(empty wizardstep)?'style="border-style:none;"':''}></iframe>
</center>
</c:if>

<c:if test="${empty urlflow}">
<%@ include file="/WEB-INF/jsp/webdb/peticioForm.jsp" %>
</c:if>
