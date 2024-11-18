<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(usuariEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(usuariEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty usuariEntitatForm.titleCode}">
    <fmt:message key="${usuariEntitatForm.titleCode}" >
      <fmt:param value="${usuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty usuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="usuariEntitat.usuariEntitat"/>
    </c:if>
    <c:if test="${not empty usuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${usuariEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${usuariEntitatForm.nou?'genapp.createtitle':(usuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty usuariEntitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(usuariEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(usuariEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${usuariEntitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>