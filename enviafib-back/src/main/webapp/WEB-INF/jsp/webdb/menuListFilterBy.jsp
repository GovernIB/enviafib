<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MenuFields" className="es.caib.enviafib.model.fields.MenuFields"/>

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
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.MENUID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.menuID" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.menuID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="menuIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="menuIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="menu.nom" style="display: inline;">
              <fmt:message key="menu.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" aria-label="menu.nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.DESCRIPCIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="menu.descripcio" style="display: inline;">
              <fmt:message key="menu.descripcio" var="descripcio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdescripcio" >                
                 <fmt:param value="${descripcio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${descripcio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdescripcio}" path="descripcio" aria-label="menu.descripcio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.TITOLMENUID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.titolMenuID" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.titolMenuID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="titolMenuIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="titolMenuIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.AJUDAMENUID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.ajudaMenuID" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.ajudaMenuID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="ajudaMenuIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="ajudaMenuIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.ORDRE)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.ordre" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.ordre" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="ordreDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="ordreFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.TIPUS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="menu.tipusSelect" style="display: inline;">
                 <span class="add-on"><fmt:message key="menu.tipus" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="menu.tipusSelect"   id="menu_tipus_select" path="tipusSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipus}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    var $select = $('#menu_tipus_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.GRUPID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.grupID" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.grupID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="grupIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="grupIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.PARAMETRECOMBO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="menu.parametreCombo" style="display: inline;">
              <fmt:message key="menu.parametreCombo" var="parametreCombo" />
              <fmt:message key="genapp.form.searchby" var="cercaperparametreCombo" >                
                 <fmt:param value="${parametreCombo}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${parametreCombo}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperparametreCombo}" path="parametreCombo" aria-label="menu.parametreCombo" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.PARAMETRETEXT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="menu.parametreText" style="display: inline;">
              <fmt:message key="menu.parametreText" var="parametreText" />
              <fmt:message key="genapp.form.searchby" var="cercaperparametreText" >                
                 <fmt:param value="${parametreText}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${parametreText}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperparametreText}" path="parametreText" aria-label="menu.parametreText" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,MenuFields.ACTIU)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="menu.actiu" style="display: inline;">
              <span class="add-on"><fmt:message key="menu.actiu" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="actiuDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="actiuFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
