<%@page import="es.caib.enviafib.logic.utils.LogicUtils"%>
<%@page import="es.caib.enviafib.commons.utils.Configuracio"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<footer id="footer" >

	<div class="row peuResponsive">

		<!-- Esquerra -->
		<div class="col-4 pt-2 elementPeuResponsive">
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

		<!-- Centre esquerra -->
		<div
			class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionsPeu elementPeuResponsive">

			<!--  <a styleClass="text-dark linkPeu" href="/mapaweb"> <fmt:message
					key="labels.mapaweb" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/accessibilitat">
				<fmt:message key="labels.accessibilitat" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/protecciodades">
				<fmt:message key="labels.protecciodades" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/avislegal"> <fmt:message
					key="labels.avislegal" />
			</a> -->

		</div>

		<!-- Dreta -->
		<div class="col-4 text-right" style="padding-top: 10px">

			<a href="http://www.caib.es/"> <img
				src="<c:url value="/img/logo-caib.png"/>"
				style="height: 45px; filter: grayscale(100%);"
				alt="Govern de les Illes Balears" />
			</a>

			<!-- 		<a href="http://otaeweb.ibit.org/" target="_blank"> <img
				src="<c:url value="/img/fundaciobit-logo-peu.png"/>"
				alt="Fundacio Bit" />
			</a>-->
			 <br /> 
			<!-- Button to trigger modal -->
			<small><a href="#modalAjuda" role="button"
				data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
		</div>

	</div>


	<!-- Modal -->
	<div id="modalAjuda" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
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