<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(menuForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(menuForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty menuForm.titleCode}">
    <fmt:message key="${menuForm.titleCode}" >
      <fmt:param value="${menuForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty menuForm.entityNameCode}">
      <fmt:message var="entityname" key="menu.menu"/>
    </c:if>
    <c:if test="${not empty menuForm.entityNameCode}">
      <fmt:message var="entityname" key="${menuForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${menuForm.nou?'genapp.createtitle':(menuForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty menuForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(menuForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(menuForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${menuForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>