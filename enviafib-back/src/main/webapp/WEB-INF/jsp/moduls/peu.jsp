<%@page import="es.caib.enviafib.logic.utils.LogicUtils"%>
<%@page import="es.caib.enviafib.commons.utils.Configuracio"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<footer id="footer" >

		<!-- Esquerra -->
		<div id="peu-esquerra">
			<strong class="font-weight-bold h6"> ${versio.projectName}
				v${versio.version}<%=Configuracio.isCAIB() ? "-caib" : ""%>

			</strong> <br /> <small> Build: ${versio.buildTime} <br /> JDK:
				${version.jdkVersion} <br /> <fmt:message key="revisio" />: <c:if
					test="${empty versio.scmRevision}">
					<fmt:message key="scmversion.msg" />
				</c:if> <c:if test="${not empty versio.scmRevision}">${versio.scmRevision}</c:if>
				<br /> <span style="padding-top: 2px"> <i><fmt:message
							key="desenvolupatper" /></i></span>
			</small>
		</div>

	<%-- 	<!-- Centre -->
		<div id="peu-centre">
			<img src="<c:url value="/img/app-logo-bn.png"/>" style="height: 6rem;"
				alt="EnviaFIB" title="EnviaFIB" />
		</div>
 --%>
		<!-- Dreta -->
		<div id="peu-dreta">
			<a href="http://www.caib.es/"> <img
				src="<c:url value="/img/app-logo-bn.png"/>"
				style="height: 35px;"
				alt="Govern de les Illes Balears" />
			</a>

			 <br /> 
			 
			<!-- Button to trigger modal -->
			<small><a href="#modalAjuda" role="button"
				data-toggle="modal" style="color: #999"><fmt:message key="ajuda.necessitau" /></a></small>
		</div>


	<!-- Modal -->
	<div id="modalAjuda" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="padding: 0 1rem;">
				<div class="modal-header">
					<div class="modal-title h5">
						<fmt:message key="ajuda.titol" />
					</div>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						<fmt:message key="ajuda.missatge" />
					</p>
					<ul>
						<c:if test="${not empty ajudatelefon}">
							<li><fmt:message key="ajuda.viatelefon" /> ${ajudatelefon}</li>
						</c:if>

						<c:if test="${not empty ajudaweb}">
							<li><fmt:message key="ajuda.viaweb" /> ${ajudaweb}</li>
						</c:if>

						<c:if test="${not empty ajudaemail}">
							<li><fmt:message key="ajuda.viaemail" /> <a
								href="mailto: ${ajudaemail}"> ${ajudaemail}</a></li>
						</c:if>

					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<fmt:message key="tancar" />
					</button>
				</div>
			</div>
		</div>
	</div>
</footer>

<style>
#footer {
	padding: 6px 6rem 6px;
	background-color: #4d4d4d;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	
	margin-top: auto;
}

#peu-dreta {
	display: flex;
	flex-direction: column;
	text-align: right;
	gap: 3px;
	width: 25rem;
}

#peu-esquerra {
	width: 25rem;
}

#modalAjuda {
	color: black;
}
</style>