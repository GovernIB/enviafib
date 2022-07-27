<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(infoArxiuForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(infoArxiuForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty infoArxiuForm.titleCode}">
    <fmt:message key="${infoArxiuForm.titleCode}" >
      <fmt:param value="${infoArxiuForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty infoArxiuForm.entityNameCode}">
      <fmt:message var="entityname" key="infoArxiu.infoArxiu"/>
    </c:if>
    <c:if test="${not empty infoArxiuForm.entityNameCode}">
      <fmt:message var="entityname" key="${infoArxiuForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${infoArxiuForm.nou?'genapp.createtitle':(infoArxiuForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty infoArxiuForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(infoArxiuForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(infoArxiuForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${infoArxiuForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>