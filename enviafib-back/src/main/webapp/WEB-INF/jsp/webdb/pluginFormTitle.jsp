<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pluginForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pluginForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pluginForm.titleCode}">
    <fmt:message key="${pluginForm.titleCode}" >
      <fmt:param value="${pluginForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pluginForm.entityNameCode}">
      <fmt:message var="entityname" key="plugin.plugin"/>
    </c:if>
    <c:if test="${not empty pluginForm.entityNameCode}">
      <fmt:message var="entityname" key="${pluginForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pluginForm.nou?'genapp.createtitle':(pluginForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pluginForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pluginForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pluginForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pluginForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>