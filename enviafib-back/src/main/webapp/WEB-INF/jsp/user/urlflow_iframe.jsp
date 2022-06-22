<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
</style>

<br/><br/>

<c:if test="${not empty title}">
<h1>${title}</h1> 
</c:if>

<c:if test="${not empty continueUrl}">
<input type="button" class="btn btn-succes" value="Continuar XYZ " onclick='location.href="<c:url value="${continueUrl}"/>";'>
</c:if>
<c:if test="${not empty cancelUrl}">
<input type="button" class="btn btn-warn" value="Cancel·lar XYZ" onclick='location.href="<c:url value="${cancelUrl}"/>";'>
</c:if>

<center>
<iframe id="iframediv" src="${urlflow}" width="800px" height="900px"></iframe>
</center>


