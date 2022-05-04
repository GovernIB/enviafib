<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(fitxerForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(fitxerForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty fitxerForm.titleCode}">
    <fmt:message key="${fitxerForm.titleCode}" >
      <fmt:param value="${fitxerForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty fitxerForm.entityNameCode}">
      <fmt:message var="entityname" key="fitxer.fitxer"/>
    </c:if>
    <c:if test="${not empty fitxerForm.entityNameCode}">
      <fmt:message var="entityname" key="${fitxerForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${fitxerForm.nou?'genapp.createtitle':(fitxerForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty fitxerForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(fitxerForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(fitxerForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${fitxerForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>