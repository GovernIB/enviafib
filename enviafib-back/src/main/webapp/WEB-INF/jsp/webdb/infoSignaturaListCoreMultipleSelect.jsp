      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${infoSignatura.infoSignaturaID}"/>
       &nbsp;
      </td>
      </c:if>

