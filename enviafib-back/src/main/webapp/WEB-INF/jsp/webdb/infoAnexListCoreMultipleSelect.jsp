      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${infoAnex.infoanexid}"/>
       &nbsp;
      </td>
      </c:if>

