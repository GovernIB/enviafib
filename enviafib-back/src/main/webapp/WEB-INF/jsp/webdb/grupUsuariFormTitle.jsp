<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(grupUsuariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(grupUsuariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty grupUsuariForm.titleCode}">
    <fmt:message key="${grupUsuariForm.titleCode}" >
      <fmt:param value="${grupUsuariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty grupUsuariForm.entityNameCode}">
      <fmt:message var="entityname" key="grupUsuari.grupUsuari"/>
    </c:if>
    <c:if test="${not empty grupUsuariForm.entityNameCode}">
      <fmt:message var="entityname" key="${grupUsuariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${grupUsuariForm.nou?'genapp.createtitle':(grupUsuariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty grupUsuariForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(grupUsuariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(grupUsuariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${grupUsuariForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>