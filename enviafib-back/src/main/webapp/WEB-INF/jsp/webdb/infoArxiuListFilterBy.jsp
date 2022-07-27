<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoArxiuFields" className="es.caib.enviafib.model.fields.InfoArxiuFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="far fa-window-close"></i></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.INFOARXIUID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoArxiu.infoArxiuID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="infoArxiuIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="infoArxiuIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.ORIGINALFILEURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.originalfileurl" var="originalfileurl" />
              <fmt:message key="genapp.form.searchby" var="cercaperoriginalfileurl" >                
                 <fmt:param value="${originalfileurl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${originalfileurl}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperoriginalfileurl}" path="originalfileurl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.CSV)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.csv" var="csv" />
              <fmt:message key="genapp.form.searchby" var="cercapercsv" >                
                 <fmt:param value="${csv}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csv}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsv}" path="csv" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.CSVGENERATIONDEFINITION)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.csvgenerationdefinition" var="csvgenerationdefinition" />
              <fmt:message key="genapp.form.searchby" var="cercapercsvgenerationdefinition" >                
                 <fmt:param value="${csvgenerationdefinition}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csvgenerationdefinition}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsvgenerationdefinition}" path="csvgenerationdefinition" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.CSVVALIDATIONWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.csvvalidationweb" var="csvvalidationweb" />
              <fmt:message key="genapp.form.searchby" var="cercapercsvvalidationweb" >                
                 <fmt:param value="${csvvalidationweb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${csvvalidationweb}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercsvvalidationweb}" path="csvvalidationweb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.ARXIUEXPEDIENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.arxiuexpedientid" var="arxiuexpedientid" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuexpedientid" >                
                 <fmt:param value="${arxiuexpedientid}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuexpedientid}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuexpedientid}" path="arxiuexpedientid" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.ARXIUDOCUMENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.arxiudocumentid" var="arxiudocumentid" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiudocumentid" >                
                 <fmt:param value="${arxiudocumentid}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiudocumentid}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiudocumentid}" path="arxiudocumentid" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.PRINTABLEURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.printableurl" var="printableurl" />
              <fmt:message key="genapp.form.searchby" var="cercaperprintableurl" >                
                 <fmt:param value="${printableurl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${printableurl}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprintableurl}" path="printableurl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.ENIFILEURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.enifileurl" var="enifileurl" />
              <fmt:message key="genapp.form.searchby" var="cercaperenifileurl" >                
                 <fmt:param value="${enifileurl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enifileurl}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenifileurl}" path="enifileurl" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoArxiuFields.VALIDATIONFILEURL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoArxiu.validationfileurl" var="validationfileurl" />
              <fmt:message key="genapp.form.searchby" var="cercapervalidationfileurl" >                
                 <fmt:param value="${validationfileurl}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${validationfileurl}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapervalidationfileurl}" path="validationfileurl" />
            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
