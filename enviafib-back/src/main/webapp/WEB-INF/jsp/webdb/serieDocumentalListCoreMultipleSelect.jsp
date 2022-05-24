      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${serieDocumental.seriedocuid}"/>
       &nbsp;
      </td>
      </c:if>

