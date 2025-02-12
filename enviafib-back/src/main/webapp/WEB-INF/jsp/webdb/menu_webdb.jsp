<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>

    <%
    java.util.List<java.util.List<org.fundaciobit.genapp.common.web.menuoptions.MenuItem>> menus;
    menus = new java.util.ArrayList<java.util.List<org.fundaciobit.genapp.common.web.menuoptions.MenuItem>>();
    java.util.List<org.fundaciobit.genapp.common.web.menuoptions.MenuItem> discoveredMenus;
    discoveredMenus = org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager.getMenuItems("WEBDB");
    menus.add(discoveredMenus);
    %>
    <%@ include file="/WEB-INF/jsp/moduls/menu_role_generator.jsp"%>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
