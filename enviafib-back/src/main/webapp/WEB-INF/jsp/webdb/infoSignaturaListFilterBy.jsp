<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoSignaturaFields" className="es.caib.enviafib.model.fields.InfoSignaturaFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.INFOSIGNATURAID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="infoSignatura.infoSignaturaID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="infoSignaturaIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="infoSignaturaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNOPERATION)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="infoSignatura.signOperation" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="infosign_signOperation_select" path="signOperationSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForSignOperation}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.signOperationSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#infosign_signOperation_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNTYPE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.signType" var="signType" />
              <fmt:message key="genapp.form.searchby" var="cercapersignType" >                
                 <fmt:param value="${signType}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${signType}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersignType}" path="signType" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.signAlgorithm" var="signAlgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercapersignAlgorithm" >                
                 <fmt:param value="${signAlgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${signAlgorithm}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersignAlgorithm}" path="signAlgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNMODE)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="infoSignatura.signMode" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="infosign_signMode_select" path="signModeSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForSignMode}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.signModeSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#infosign_signMode_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="infoSignatura.signaturesTableLocation" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="infosign_signaturesTableLocation_select" path="signaturesTableLocationSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForSignaturesTableLocation}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.signaturesTableLocationSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#infosign_signaturesTableLocation_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENITIPOFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniTipoFirma" var="eniTipoFirma" />
              <fmt:message key="genapp.form.searchby" var="cercapereniTipoFirma" >                
                 <fmt:param value="${eniTipoFirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniTipoFirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniTipoFirma}" path="eniTipoFirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENIPERFILFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniPerfilFirma" var="eniPerfilFirma" />
              <fmt:message key="genapp.form.searchby" var="cercapereniPerfilFirma" >                
                 <fmt:param value="${eniPerfilFirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniPerfilFirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniPerfilFirma}" path="eniPerfilFirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENIROLFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniRolFirma" var="eniRolFirma" />
              <fmt:message key="genapp.form.searchby" var="cercapereniRolFirma" >                
                 <fmt:param value="${eniRolFirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniRolFirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniRolFirma}" path="eniRolFirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNERNAME)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniSignerName" var="eniSignerName" />
              <fmt:message key="genapp.form.searchby" var="cercapereniSignerName" >                
                 <fmt:param value="${eniSignerName}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniSignerName}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniSignerName}" path="eniSignerName" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniSignerAdministrationId" var="eniSignerAdministrationId" />
              <fmt:message key="genapp.form.searchby" var="cercapereniSignerAdministrationId" >                
                 <fmt:param value="${eniSignerAdministrationId}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniSignerAdministrationId}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniSignerAdministrationId}" path="eniSignerAdministrationId" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNLEVEL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniSignLevel" var="eniSignLevel" />
              <fmt:message key="genapp.form.searchby" var="cercapereniSignLevel" >                
                 <fmt:param value="${eniSignLevel}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniSignLevel}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniSignLevel}" path="eniSignLevel" />
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
  
