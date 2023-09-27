<%@page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div id="faq_list_title">
	<h2>Preguntes frequents</h2>


	<c:if test="${efi:hasRole('ROLE_ADMIN')}">
		<c:set var="backToList"
			value="admin" />
	</c:if>
	<c:if test="${efi:hasRole('ROLE_USER')}">
		<c:set var="backToList"
			value="user" />
	</c:if>

	<a id="backToListBtn" class="btn btn-sm btn-primary float-right botoselecciolist" style=""
		href="<%=request.getContextPath()%>/${backToList}/peticio/list/1"> <i class="fas fa-list icon-white"></i>
		<fmt:message key="back.to.list" />
	</a>
</div>

<style>
#backToListBtn {
	margin-left: auto;
	margin-right: 3rem;
	font-size: 16px;
	display: block;
	padding: 0.25rem 1rem;
	display: block;
}

#faq_list_title {
	display: flex;
	align-items: center;
}
</style>


<div id="faq_list_div">
	<c:forEach var="faq" items="${faqItems}">
		<div class="faq_row" id="faq_rowid_${faq.faqID}">

		    <c:if test="${lang == 'es'}">
		        <div class="pregunta">${not empty faq.enunciat_es ? faq.enunciat_es : faq.enunciat_ca}</div>
		        <div class="resposta">${not empty faq.resposta_es ? faq.resposta_es : faq.resposta_ca}</div>
            </c:if>
            <c:if test="${lang == 'ca'}">
		        <div class="pregunta">${faq.enunciat_ca}</div>
		        <div class="resposta">${faq.resposta_ca}</div>
            </c:if>
		



			<%-- 			
			<p class="pregunta">${faq.enunciat_es}</p>
			<p class="resposta">${faq.resposta_es}</p>
             --%>

			<div class="fitxers-container">
				<c:if test="${not empty faq.fitxer1}">
					<div class="fitxer fitxer1">
						<a target="_blank"
							href="<c:url value="${efi:fileUrl(faq.fitxer1)}"/>">${faq.fitxer1.nom}</a>
					</div>
				</c:if>

				<c:if test="${not empty faq.fitxer2}">
					<div class="fitxer fitxer2">

						<a target="_blank"
							href="<c:url value="${efi:fileUrl(faq.fitxer2)}"/>">${faq.fitxer2.nom}</a>
					</div>
				</c:if>

				<c:if test="${not empty faq.fitxer3}">
					<div class="fitxer fitxer3">
						<a target="_blank"
							href="<c:url value="${efi:fileUrl(faq.fitxer3)}"/>">${faq.fitxer3.nom}</a>
					</div>
				</c:if>

			</div>
		</div>
		<hr class="myLine" />
	</c:forEach>
</div>

<style>
#faq_list_div {
	width: 75%;
}

.faq_row {
/* 	padding: 0 1rem;
 */}

.pregunta {
	word-wrap: break-word;
	font-size: 26px;
	color: rgb(36, 110, 185);
}

.resposta {
	
}

.myLine {
	margin-top: 2rem;
	width: 75%;
}

.fitxers-container {
	display: flex; /*Convertimos al menú en flexbox*/
	justify-content: space-evenly;
	/*Con esto le indicamos que margine todos los items que se encuentra adentro hacia la derecha e izquierda*/
	align-items: center; /*con esto alineamos de manera vertical*/
}

.fitxer {
	border: 1px solid black;
	padding: 0.5rem 2rem;
}
</style>

