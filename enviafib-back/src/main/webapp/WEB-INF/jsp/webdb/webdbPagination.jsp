<%@page
    import="org.fundaciobit.genapp.common.web.exportdata.IDataExporter"%>
<%@page import="java.util.List"%>
<%@page
    import="org.fundaciobit.genapp.common.web.exportdata.DataExporterManager"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<script type="text/javascript">

var currentActionForExporter = "";

function submitForm(action, reassign) {
  if (reassign) {  
	 currentActionForExporter = document.${formName}.action;
  }
  document.${formName}.action = action;
  if (reassign) {
    setTimeout(reassignAction, 3000);
  } 
  document.${formName}.submit();
}

function reassignAction() {
  document.${formName}.action = currentActionForExporter;
  currentActionForExporter = "";
}
</script>
<div class="row" id="${formName}_pagination"
    style="width: 100%;">

	<div class="col" style="text-align: left;"
		id="${formName}_pagination_left">
		<c:if test="${__theFilterForm.visibleExportList}">
			<%
            for (IDataExporter dataExporter : DataExporterManager.getAllDataExporters()) {
            %>
			<c:url var="exportUrl" value="${contexte}/export" />
			<a href="#"
				onclick="submitForm('${exportUrl}/<%=dataExporter.getID()%>', true)">
				<img alt="<%=dataExporter.getName()%>"
				src="<%=request.getContextPath() + "/common/icon/" + dataExporter.getID()%>" />
			</a>
			<%
            }
            %>
		</c:if>

		<div id="infoNumRegistres">
			<c:out value="__itemsPerPagina: ${__itemsPerPagina} " />
			<br>
			<c:out value="__totalItems: ${__totalItems}" />
			<br>
			<c:out value="__pagina: ${pagi__paginana}" />
			<br>

			<c:if test="${__itemsPerPagina == -1}">
				<c:out value="Mostrant tots els resultats" />
			</c:if>
			
			<c:if test="${__itemsPerPagina != -1}">
				<c:set var="start" value="${(__pagina-1) * __itemsPerPagina + 1}" />
				<c:set var="end"
					value="${(start + __itemsPerPagina > __totalItems) ? __totalItems : (start + __itemsPerPagina - 1)}" />

				<fmt:message key="show.results.from.to" var="showNResults">
					<fmt:param> ${start} </fmt:param>
					<fmt:param> ${end} </fmt:param>
					<fmt:param> ${__totalItems} </fmt:param>

					<fmt:param>
						<fmt:message key="${__theFilterForm.entityNameCodePlural}" />
					</fmt:param>

				</fmt:message>
				<c:out value="${showNResults}" />
			</c:if>
		</div>

	</div>


	<div class="col-md-auto text-center"
        id="${formName}_pagination_center">
        <c:if test="${not empty __theFilterForm.itemsPerPage}">
          <%@include file="webdbPaginationOnlyBar.jsp" %>
        </c:if>
    </div>


    <fmt:message var="allitems" key="genapp.form.allitems" />
    <div class="col" style="text-align: right"
        id="${formName}_pagination_right">
        <label><fmt:message key="genapp.form.itemsperpage" />:</label>
        <form:select cssClass="input-small" cssStyle="width:4em;"
            onchange="document.${formName}.submit()"
            path="itemsPerPage">
            <c:forEach var="num"
                items="${__theFilterForm.allItemsPerPage}">
                <form:option value="${num}"
                    label="${ (num == -1)? allitems : num}" />
            </c:forEach>
        </form:select>
    </div>


</div>

</div>

