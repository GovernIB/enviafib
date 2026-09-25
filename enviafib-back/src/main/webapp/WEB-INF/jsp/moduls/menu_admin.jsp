<%@page import="es.caib.enviafib.commons.utils.Configuracio"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%><%@ page
    contentType="text/html;charset=UTF-8" language="java"%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager"%><%@page
    import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"%><%@page import="es.caib.enviafib.back.utils.Tab"%><%@page
    import="java.util.List"%><%@page import="java.util.ArrayList"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
    <span style="font-size: 1.25rem"> <fmt:message key="menu.administrador" />
    </span>
    <ul class="tree" style="margin: 3px; padding: 0px;">

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/usuari/list"/>">
                <span style="${(fn:contains(url, 'admin/usuari/'))? "font-weight:bold;" : ""}"> <fmt:message key="admin.menu.usuaris" />
                </span>
            </a></li>

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/entitat/list"/>">
                <span style="${(fn:contains(url, 'admin/entitat'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="admin.menu.entitats" />
                </span>
            </a></li>

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/usuarisduplicats/list"/>">
                <span style="${(fn:contains(url, 'admin/usuarisduplicats'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="admin.menu.usuarisduplicats" />
                </span>
            </a></li>

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/canviarusernames/new"/>">
                <span style="${(fn:contains(url, 'admin/canviarusernames'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="admin.menu.canviarusernames" />
                </span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/avis/list"/>">
                <span style="${(fn:contains(url, '/admin/avis'))? "font-weight:bold;" : ""}"><fmt:message key="admin.menu.avisos" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/grup/list"/>">
                <span style="${(fn:contains(url, '/admin/grup'))? "font-weight:bold;" : ""}"><fmt:message key="admin.menu.grup" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/serieDocumental/list"/>">
                <span style="${(fn:contains(url, '/admin/serieDocumental'))? "font-weight:bold;" : ""}"><fmt:message
                        key="admin.menu.series" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />
        <li style="list-style-type: disc; list-style-position: inside;"><a
                href="<c:url value="/admin/estructuraorganitzativaplugin/list"/>">
                <span style="${(fn:contains(url, '/admin/estructuraorganitzativaplugin'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="admin.menu.estructura" /></span>
            </a></li>

        <%
        if (Configuracio.showMenuEstructuraOrganitzativa()) {
        %>

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/organitzacio/list"/>">
                <span style="${(fn:contains(url, '/admin/organitzacio/'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="organitzacio.organitzacio" />
                </span>
            </a></li>
        <%
        }
        %>


        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/arxiuplugin/list"/>">
                <span style="${(fn:contains(url, '/admin/arxiuplugin/list'))? "font-weight:bold;" : ""}"> <fmt:message
                        key="admin.menu.arxiu" />
                </span>
            </a></li>


        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <%--         <li style="list-style-type: disc; list-style-position: inside;"><a
            href="<c:url value="/admin/plantillesfluxfirmes/list"/>"> <span
                style="${(fn:contains(url, '/admin/plantillesfluxfirmes'))? "font-weight:bold;" : ""}"><fmt:message
                        key="plantillesfluxfirmes.admin.plural" /></span>
        </a></li> --%>

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/netejarplantilles/list"/>">
                <span style="${(fn:contains(url, '/admin/netejarplantilles'))? "font-weight:bold;" : ""}"><fmt:message
                        key="plantillesfluxfirmes.obsolet.plural" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/faq/list"/>">
                <span style="${(fn:contains(url, '/admin/faq'))? "font-weight:bold;" : ""}"><fmt:message key="faq.gestio" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/menu/list"/>">
                <span style="${(fn:contains(url, '/admin/menu'))? "font-weight:bold;" : ""}"><fmt:message key="menus.gestio" /></span>
            </a></li>


        <hr style="margin-top: 6px; margin-bottom: 6px;" />

        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/admin/peticio/list"/>">
                <span style="${(fn:contains(url, '/admin/peticio'))? "font-weight:bold;" : ""}"><fmt:message
                        key="admin.menus.peticions" /></span>
            </a></li>

        <hr style="margin-top: 6px; margin-bottom: 6px;" />

    </ul>

    <%
    List<List<MenuItem>> menus = new ArrayList<List<MenuItem>>();
    /*
    MenuItem menuGoogle = new MenuItem("=MENU Google", "", "http://www.google.com", 0);
    MenuItem menumeneame = new MenuItem("=MENU Meneame", "", "http://www.meneame.net", 1000);
    */
    List<MenuItem> discoveredMenus = MenuOptionManager.getMenuItems(Tab.MENU_ADMIN); //, menuGoogle, menumeneame);
    menus.add(discoveredMenus);
    %>

    <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>

</div>

