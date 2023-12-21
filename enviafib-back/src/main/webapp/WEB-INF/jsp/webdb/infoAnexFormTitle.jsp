<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(infoAnexForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(infoAnexForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty infoAnexForm.titleCode}">
    <fmt:message key="${infoAnexForm.titleCode}" >
      <fmt:param value="${infoAnexForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty infoAnexForm.entityNameCode}">
      <fmt:message var="entityname" key="infoAnex.infoAnex"/>
    </c:if>
    <c:if test="${not empty infoAnexForm.entityNameCode}">
      <fmt:message var="entityname" key="${infoAnexForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${infoAnexForm.nou?'genapp.createtitle':(infoAnexForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty infoAnexForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(infoAnexForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(infoAnexForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${infoAnexForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>