<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div id="firmaFluxContainer">
	<c:if test="${not empty wizardstep}">
		<div id="leadTitle" class="lead">
			<label> <c:out value="${titolFluxFirma}"
					default="FIRMA PERSONALITAZA" />
			</label>
		</div>

		<%@include file="wizard_component.jsp"%>

		<div id="botonsFluxContainer">
			<c:if test="${not empty continueUrl}">
				<input type="button" class="btn btn-succes btn-primary btn-flux"
					value="<fmt:message key="genapp.continue"/>"
					onclick='location.href="<c:url value="${continueUrl}"/>";'>
			</c:if>
			<c:if test="${not empty cancelUrl}">
				<input type="button" class="btn btn-warn btn-secondary btn-flux"
					value="<fmt:message key="genapp.cancel" />"
					onclick='location.href="<c:url value="${cancelUrl}"/>";'>
			</c:if>
		</div>
	</c:if>

	<c:if test="${not empty urlflow}">
		<center>
			<iframe id="iframediv" src="${urlflow}" width="100%" height="900px"
				${(empty wizardstep)?'style="border-style:none;"':''}></iframe>
		</center>
	</c:if>

	<c:if test="${empty urlflow}">
		<%@ include file="/WEB-INF/jsp/webdb/peticioForm.jsp"%>
	</c:if>
</div>

<style>
#firmaFluxContainer {
	padding: 0rem 0.85rem;
}

#leadTitle {
	margin-bottom: 10px;
	text-align: left;
}

#botonsFluxContainer{
    margin: 1rem;
    text-align: center;
}

.btn-flux{
	margin: 10px;
	height: 2.5rem;
}
</style>
