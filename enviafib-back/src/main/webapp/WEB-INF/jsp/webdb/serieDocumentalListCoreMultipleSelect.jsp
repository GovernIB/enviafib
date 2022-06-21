      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${serieDocumental.serieDocumentalID}"/>
       &nbsp;
      </td>
      </c:if>

