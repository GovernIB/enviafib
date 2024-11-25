<%@page import="es.caib.enviafib.commons.utils.Configuracio"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
	<h5>
		<fmt:message key="menu.aden" />
	</h5>
	<ul class="tree" style="margin: 3px; padding: 0px;">

		<!-- DADES ENTITAT -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/entitat/dadesEntitat"/>"> <span
				style="${(fn:contains(url, 'aden/entitat'))? "font-weight:bold;" : ""}">
					<fmt:message key="aden.menu.dadesentitat" />
			</span>
		</a></li>
		<hr style="margin-top: 6px; margin-bottom: 6px;" />

	    <!-- USUARIS -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/usuari/list"/>"> <span
				style="${(fn:contains(url, 'aden/usuari'))? "font-weight:bold;" : ""}">
					<fmt:message key="aden.menu.usuaris" />
			</span>
		</a></li>
		<hr style="margin-top: 6px; margin-bottom: 6px;" />


        <!-- SERIES DOCUMENTALS -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/serieDocumental/list"/>"> <span
				style="${(fn:contains(url, '/aden/serieDocumental'))? "font-weight:bold;" : ""}"><fmt:message
						key="aden.menu.series" /></span>
		</a></li>
		<hr style="margin-top: 6px; margin-bottom: 6px;" />


        <!-- PLANTILLES FLUXOS FIRMES -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/plantillesfluxfirmes/list"/>"> <span
				style="${(fn:contains(url, '/aden/plantillesfluxfirmes'))? "font-weight:bold;" : ""}"><fmt:message
						key="aden.menus.plantilles.entitat" /></span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />


		<!-- PETICIONS -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/peticio/list"/>"> <span
				style="${(fn:contains(url, '/aden/peticio'))? "font-weight:bold;" : ""}"><fmt:message
						key="aden.menus.peticions" /></span>
		</a></li>
		




<%-- <hr style="margin-top: 6px; margin-bottom: 6px;" />


		<!-- GRUPS -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/grup/list"/>"> <span
				style="${(fn:contains(url, '/aden/grup'))? "font-weight:bold;" : ""}"><fmt:message
						key="admin.menu.grup" /></span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

	    <!-- MENUS -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/aden/menu/list"/>"> <span
				style="${(fn:contains(url, '/aden/menu'))? "font-weight:bold;" : ""}"><fmt:message
						key="menus.gestio" /></span>
		</a></li>
 --%>

	</ul>
</div>

