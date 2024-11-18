      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${usuariEntitat.usuarientitatid}"/>
       &nbsp;
      </td>
      </c:if>

