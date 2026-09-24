<%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" %>

<div class="clear">



<c:choose>
    <c:when test="${accio eq 'excepcio'}">
       <c:set var="accio" value="" ></c:set>
       <% 
         if (true) {
           throw new NullPointerException("Un error en en la vista (jsp)");
         }
       %>
    </c:when>
    <c:otherwise>
        Seleccioni una opci&oacute; ...
    </c:otherwise>
</c:choose>



</div>