<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
  <div class="navbar-form" style="text-align:right">
    <c:if test="${__theForm.saveButtonVisible}">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="genapp.save"/>">
    </c:if>
    <c:if test="${__theForm.cancelButtonVisible}">
    <input type="button" class="btn btn-secondary" onclick="goTo('<c:url value="${contexte}${__theForm.nou?'':'/'.concat(__theForm.fitxer.fitxerID)}/cancel"/>')" value="<fmt:message key="genapp.cancel"/>">
    </c:if>
    <c:if test="${!__theForm.nou && __theForm.deleteButtonVisible}">
    <button type="button" class="btn btn-danger" onclick="openModal('<c:url value="${contexte}/${__theForm.fitxer.fitxerID}/delete"/>','show');"><fmt:message key="genapp.delete"/></button>
    </c:if>
    <c:set var="bracket" value="{0}"/>
    <c:forEach var="button" items="${__theForm.additionalButtons}">
    <c:if test="${!__theForm.nou || (-1 == fn:indexOf(button.link,bracket))}">
    <c:set var="pk" value="${__theForm.fitxer.fitxerID}"/>
    <c:set var="thelink" value="${fn:replace(button.link,bracket, pk)}" />
    <c:set var="thehref" value="#"/>
    <c:if test="${!fn:startsWith(thelink,'javascript:')}">
     <c:url var="thehref" value="${thelink}"/>
     <c:url var="thelink" value=""/>
    </c:if>
    <a class="btn <c:out value="${button.style}" />" 
       href="${thehref}" onclick="${thelink}" style="${(empty button.style)? '' : 'color: white;'}"  >
       <i class="${button.icon}"></i><fmt:message key="${button.codeText}"/>
    </a>
    </c:if>
    </c:forEach>
  </div>
