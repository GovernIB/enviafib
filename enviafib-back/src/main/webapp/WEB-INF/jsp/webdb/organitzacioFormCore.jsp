<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OrganitzacioFields" className="es.caib.enviafib.model.fields.OrganitzacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganitzacioFields.CODICONSELLERIA)}">
        <tr id="organitzacio_codiConselleria_rowid">
          <td id="organitzacio_codiConselleria_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganitzacioFields.CODICONSELLERIA])?'organitzacio.codiConselleria':__theForm.labels[OrganitzacioFields.CODICONSELLERIA]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganitzacioFields.CODICONSELLERIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganitzacioFields.CODICONSELLERIA]}" ></i>
              </c:if>
            </td>
          <td id="organitzacio_codiConselleria_columnvalueid">
            <form:errors path="organitzacio.codiConselleria" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.CODICONSELLERIA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.CODICONSELLERIA)? ' uneditable-input' : ''}"  style="" maxlength="100" path="organitzacio.codiConselleria"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganitzacioFields.CODIDIRECCIOGENERAL)}">
        <tr id="organitzacio_codiDireccioGeneral_rowid">
          <td id="organitzacio_codiDireccioGeneral_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganitzacioFields.CODIDIRECCIOGENERAL])?'organitzacio.codiDireccioGeneral':__theForm.labels[OrganitzacioFields.CODIDIRECCIOGENERAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganitzacioFields.CODIDIRECCIOGENERAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganitzacioFields.CODIDIRECCIOGENERAL]}" ></i>
              </c:if>
            </td>
          <td id="organitzacio_codiDireccioGeneral_columnvalueid">
            <form:errors path="organitzacio.codiDireccioGeneral" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.CODIDIRECCIOGENERAL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.CODIDIRECCIOGENERAL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="organitzacio.codiDireccioGeneral"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganitzacioFields.TIPUS)}">
        <tr id="organitzacio_tipus_rowid">
          <td id="organitzacio_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganitzacioFields.TIPUS])?'organitzacio.tipus':__theForm.labels[OrganitzacioFields.TIPUS]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganitzacioFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganitzacioFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="organitzacio_tipus_columnvalueid">
          <form:errors path="organitzacio.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.TIPUS)}" >
          <form:hidden path="organitzacio.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.organitzacio.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="organitzacio_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="organitzacio.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.organitzacio.tipus }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.organitzacio.tipus }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganitzacioFields.VALOR)}">
        <tr id="organitzacio_valor_rowid">
          <td id="organitzacio_valor_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganitzacioFields.VALOR])?'organitzacio.valor':__theForm.labels[OrganitzacioFields.VALOR]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganitzacioFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganitzacioFields.VALOR]}" ></i>
              </c:if>
            </td>
          <td id="organitzacio_valor_columnvalueid">
            <form:errors path="organitzacio.valor" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.VALOR)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,OrganitzacioFields.VALOR)? ' uneditable-input' : ''}"  style="" maxlength="255" path="organitzacio.valor"   />

           </td>
        </tr>
        </c:if>
        
