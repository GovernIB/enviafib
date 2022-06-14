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
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.infosignaturaid" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="infosignaturaidDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="infosignaturaidFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNOPERATION)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.signoperation" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="signoperationDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="signoperationFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNTYPE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.signtype" var="signtype" />
              <fmt:message key="genapp.form.searchby" var="cercapersigntype" >                
                 <fmt:param value="${signtype}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${signtype}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersigntype}" path="signtype" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNALGORITHM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.signalgorithm" var="signalgorithm" />
              <fmt:message key="genapp.form.searchby" var="cercapersignalgorithm" >                
                 <fmt:param value="${signalgorithm}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${signalgorithm}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersignalgorithm}" path="signalgorithm" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNMODE)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.signmode" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="signmodeDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="signmodeFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.signaturestablelocation" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="signaturestablelocationDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="signaturestablelocationFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.TIMESTAMPINCLUDED)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.timestampincluded" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="timestampincludedDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="timestampincludedFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.POLICYINCLUDED)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.policyincluded" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="policyincludedDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="policyincludedFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENITIPOFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.enitipofirma" var="enitipofirma" />
              <fmt:message key="genapp.form.searchby" var="cercaperenitipofirma" >                
                 <fmt:param value="${enitipofirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enitipofirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenitipofirma}" path="enitipofirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENIPERFILFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.eniperfilfirma" var="eniperfilfirma" />
              <fmt:message key="genapp.form.searchby" var="cercapereniperfilfirma" >                
                 <fmt:param value="${eniperfilfirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${eniperfilfirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapereniperfilfirma}" path="eniperfilfirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENIROLFIRMA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.enirolfirma" var="enirolfirma" />
              <fmt:message key="genapp.form.searchby" var="cercaperenirolfirma" >                
                 <fmt:param value="${enirolfirma}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enirolfirma}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenirolfirma}" path="enirolfirma" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNERNAME)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.enisignername" var="enisignername" />
              <fmt:message key="genapp.form.searchby" var="cercaperenisignername" >                
                 <fmt:param value="${enisignername}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enisignername}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenisignername}" path="enisignername" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.enisigneradministrationid" var="enisigneradministrationid" />
              <fmt:message key="genapp.form.searchby" var="cercaperenisigneradministrationid" >                
                 <fmt:param value="${enisigneradministrationid}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enisigneradministrationid}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenisigneradministrationid}" path="enisigneradministrationid" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.ENISIGNLEVEL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="infoSignatura.enisignlevel" var="enisignlevel" />
              <fmt:message key="genapp.form.searchby" var="cercaperenisignlevel" >                
                 <fmt:param value="${enisignlevel}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${enisignlevel}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperenisignlevel}" path="enisignlevel" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.checkadministrationidofsigner" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="checkadministrationidofsignerDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="checkadministrationidofsignerFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.checkdocumentmodifications" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="checkdocumentmodificationsDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="checkdocumentmodificationsFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="infoSignatura.checkvalidationsignature" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="checkvalidationsignatureDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="checkvalidationsignatureFins" />

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
  
