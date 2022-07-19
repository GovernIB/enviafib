      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${infoCustody.infocustodyid}"/>
       &nbsp;
      </td>
      </c:if>

