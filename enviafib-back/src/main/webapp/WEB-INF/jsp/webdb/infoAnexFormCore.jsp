<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoAnexFields" className="es.caib.enviafib.model.fields.InfoAnexFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoAnexFields.PETICIOID)}">
        <tr id="infoAnex_peticioID_rowid">
          <td id="infoAnex_peticioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoAnexFields.PETICIOID])?'infoAnex.peticioID':__theForm.labels[InfoAnexFields.PETICIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoAnexFields.PETICIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoAnexFields.PETICIOID]}" ></i>
              </c:if>
            </td>
          <td id="infoAnex_peticioID_columnvalueid">
          <form:errors path="infoAnex.peticioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoAnexFields.PETICIOID)}" >
          <form:hidden path="infoAnex.peticioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoAnex.peticioID,__theForm.listOfPeticioForPeticioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoAnexFields.PETICIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoAnex_peticioID"  onchange="if(typeof onChangePeticioID == 'function') {  onChangePeticioID(this); };"  cssClass="form-control col-md-9-optional" path="infoAnex.peticioID">
            <c:forEach items="${__theForm.listOfPeticioForPeticioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoAnex.peticioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoAnex.peticioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoAnexFields.ANEXID)}">
        <tr id="infoAnex_anexID_rowid">
          <td id="infoAnex_anexID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoAnexFields.ANEXID])?'infoAnex.anexID':__theForm.labels[InfoAnexFields.ANEXID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoAnexFields.ANEXID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoAnexFields.ANEXID]}" ></i>
              </c:if>
            </td>
          <td id="infoAnex_anexID_columnvalueid">
              <form:errors path="infoAnex.anexID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoAnexFields.ANEXID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.infoAnex.anex)}"/>">${__theForm.infoAnex.anex.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoAnexFields.ANEXID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,InfoAnexFields.ANEXID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,InfoAnexFields.ANEXID)? ' uneditable-input' : ''}"   path="anexID" type="file" />
                  <label class="custom-file-label" for="anexID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.infoAnex.anex}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.infoAnex.anex)}"/>">${__theForm.infoAnex.anex.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="anexIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="anexID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#anexID').on('change', function(){
						var ruta = $('#anexID').val(); 
						var rutaArray = ruta.split('\\');
						$('#anexID-custom-file-label').css('display','block');
						$('#anexID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
