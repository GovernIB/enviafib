<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SerieDocumentalFields" className="es.caib.enviafib.model.fields.SerieDocumentalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SerieDocumentalFields.NOM)}">
        <tr id="serieDocumental_nom_rowid">
          <td id="serieDocumental_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SerieDocumentalFields.NOM])?'serieDocumental.nom':__theForm.labels[SerieDocumentalFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SerieDocumentalFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SerieDocumentalFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="serieDocumental_nom_columnvalueid">
            <form:errors path="serieDocumental.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SerieDocumentalFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SerieDocumentalFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="256" path="serieDocumental.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SerieDocumentalFields.TIPUSDOCUMENTAL)}">
        <tr id="serieDocumental_tipusDocumental_rowid">
          <td id="serieDocumental_tipusDocumental_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SerieDocumentalFields.TIPUSDOCUMENTAL])?'serieDocumental.tipusDocumental':__theForm.labels[SerieDocumentalFields.TIPUSDOCUMENTAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[SerieDocumentalFields.TIPUSDOCUMENTAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SerieDocumentalFields.TIPUSDOCUMENTAL]}" ></i>
              </c:if>
            </td>
          <td id="serieDocumental_tipusDocumental_columnvalueid">
          <form:errors path="serieDocumental.tipusDocumental" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SerieDocumentalFields.TIPUSDOCUMENTAL)}" >
          <form:hidden path="serieDocumental.tipusDocumental"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.serieDocumental.tipusDocumental,__theForm.listOfValuesForTipusDocumental)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SerieDocumentalFields.TIPUSDOCUMENTAL)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="serieDocumental_tipusDocumental"  onchange="if(typeof onChangeTipusDocumental == 'function') {  onChangeTipusDocumental(this); };"  cssClass="form-control col-md-9-optional" path="serieDocumental.tipusDocumental">
            <c:forEach items="${__theForm.listOfValuesForTipusDocumental}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.serieDocumental.tipusDocumental }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.serieDocumental.tipusDocumental }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
