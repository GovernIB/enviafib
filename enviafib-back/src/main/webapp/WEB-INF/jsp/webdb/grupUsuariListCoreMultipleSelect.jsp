      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${grupUsuari.grupUsuariID}"/>
       &nbsp;
      </td>
      </c:if>

