<%@page import="java.util.List"
%><%@page import="org.fundaciobit.genapp.common.web.menuoptions.MenuItem"
%><%@page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%
{
int count = 0;

for (List<MenuItem> menu : menus) {
    pageContext.setAttribute("menu", menu);
%>
<ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}">
        <c:if test="${empty item }">
            <hr style="margin-top: 6px; margin-bottom: 6px;" />
        </c:if>
        <c:if test="${not empty item }">
            <c:if test="${fn:substring(item.label, 0, 1) == '='}">
                <c:set var="traduccio" value="${fn:substring(item.label, 1, fn:length(item.label))}" />
            </c:if>
            <c:if test="${fn:substring(item.label, 0, 1) != '='}">
                <fmt:message var="traduccio" key="${item.label}" />
            </c:if>

            <c:set var="theurl" value="${item.url}" />
            <c:set var="theurlbase" value="${item.urlbase}" />
            <li style="list-style-type: disc; list-style-position: inside;">
            <a href="<c:url value="${theurl}"/>">
               <span style="${(fn:contains(urlActual, theurlbase))? "font-weight:bold;" : ""} ${(fn:endsWith(traduccio, '(*)'))? "color:red;" : ""}">${traduccio}</span>
            </a>
            </li>
        </c:if>
    </c:forEach>

</ul>

<%
count++;

} // final FOR
}
%>
