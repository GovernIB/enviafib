      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${organitzacio.organitzacioID}"/>
       &nbsp;
      </td>
      </c:if>

