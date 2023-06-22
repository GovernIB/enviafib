<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioFields" className="es.caib.enviafib.model.fields.PeticioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.NOM)}">
        <tr id="peticio_nom_rowid">
          <td id="peticio_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.NOM])?'peticio.nom':__theForm.labels[PeticioFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="peticio_nom_columnvalueid">
            <form:errors path="peticio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.DATACREACIO)}">
        <tr id="peticio_dataCreacio_rowid">
          <td id="peticio_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.DATACREACIO])?'peticio.dataCreacio':__theForm.labels[PeticioFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="peticio_dataCreacio_columnvalueid">
    <form:errors path="peticio.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="peticio_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticio_dataCreacio" path="peticio.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#peticio_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#peticio_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.DATAFINAL)}">
        <tr id="peticio_dataFinal_rowid">
          <td id="peticio_dataFinal_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.DATAFINAL])?'peticio.dataFinal':__theForm.labels[PeticioFields.DATAFINAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.DATAFINAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.DATAFINAL]}" ></i>
              </c:if>
            </td>
          <td id="peticio_dataFinal_columnvalueid">
    <form:errors path="peticio.dataFinal" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="peticio_dataFinal" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.DATAFINAL)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticio_dataFinal" path="peticio.dataFinal" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.DATAFINAL)}" >
                    <div class="input-group-append"  data-target="#peticio_dataFinal"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#peticio_dataFinal').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.FITXERID)}">
        <tr id="peticio_fitxerID_rowid">
          <td id="peticio_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.FITXERID])?'peticio.fitxerID':__theForm.labels[PeticioFields.FITXERID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_fitxerID_columnvalueid">
              <form:errors path="peticio.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.peticio.fitxer)}"/>">${__theForm.peticio.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticio.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.peticio.fitxer)}"/>">${__theForm.peticio.fitxer.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.SOLICITANTID)}">
        <tr id="peticio_solicitantID_rowid">
          <td id="peticio_solicitantID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.SOLICITANTID])?'peticio.solicitantID':__theForm.labels[PeticioFields.SOLICITANTID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.SOLICITANTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.SOLICITANTID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_solicitantID_columnvalueid">
          <form:errors path="peticio.solicitantID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.SOLICITANTID)}" >
          <form:hidden path="peticio.solicitantID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.solicitantID,__theForm.listOfUsuariForSolicitantID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.SOLICITANTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_solicitantID"  onchange="if(typeof onChangeSolicitantID == 'function') {  onChangeSolicitantID(this); };"  cssClass="form-control col-md-9-optional" path="peticio.solicitantID">
            <c:forEach items="${__theForm.listOfUsuariForSolicitantID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.IDIOMAID)}">
        <tr id="peticio_idiomaID_rowid">
          <td id="peticio_idiomaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.IDIOMAID])?'peticio.idiomaID':__theForm.labels[PeticioFields.IDIOMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.IDIOMAID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_idiomaID_columnvalueid">
          <form:errors path="peticio.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMAID)}" >
          <form:hidden path="peticio.idiomaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-9-optional" path="peticio.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.DESTINATARINIF)}">
        <tr id="peticio_destinatariNif_rowid">
          <td id="peticio_destinatariNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.DESTINATARINIF])?'peticio.destinatariNif':__theForm.labels[PeticioFields.DESTINATARINIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.DESTINATARINIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.DESTINATARINIF]}" ></i>
              </c:if>
            </td>
          <td id="peticio_destinatariNif_columnvalueid">
            <form:errors path="peticio.destinatariNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.DESTINATARINIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.DESTINATARINIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="peticio.destinatariNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ESTAT)}">
        <tr id="peticio_estat_rowid">
          <td id="peticio_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ESTAT])?'peticio.estat':__theForm.labels[PeticioFields.ESTAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="peticio_estat_columnvalueid">
          <form:errors path="peticio.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.ESTAT)}" >
          <form:hidden path="peticio.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="peticio.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.FITXERFIRMATID)}">
        <tr id="peticio_fitxerFirmatID_rowid">
          <td id="peticio_fitxerFirmatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.FITXERFIRMATID])?'peticio.fitxerFirmatID':__theForm.labels[PeticioFields.FITXERFIRMATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.FITXERFIRMATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.FITXERFIRMATID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_fitxerFirmatID_columnvalueid">
              <form:errors path="peticio.fitxerFirmatID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERFIRMATID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.peticio.fitxerFirmat)}"/>">${__theForm.peticio.fitxerFirmat.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERFIRMATID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERFIRMATID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.FITXERFIRMATID)? ' uneditable-input' : ''}"   path="fitxerFirmatID" type="file" />
                  <label class="custom-file-label" for="fitxerFirmatID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.peticio.fitxerFirmat}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.peticio.fitxerFirmat)}"/>">${__theForm.peticio.fitxerFirmat.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerFirmatIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerFirmatID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerFirmatID').on('change', function(){
						var ruta = $('#fitxerFirmatID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerFirmatID-custom-file-label').css('display','block');
						$('#fitxerFirmatID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.TIPUSDOCUMENTAL)}">
        <tr id="peticio_tipusDocumental_rowid">
          <td id="peticio_tipusDocumental_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.TIPUSDOCUMENTAL])?'peticio.tipusDocumental':__theForm.labels[PeticioFields.TIPUSDOCUMENTAL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.TIPUSDOCUMENTAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.TIPUSDOCUMENTAL]}" ></i>
              </c:if>
            </td>
          <td id="peticio_tipusDocumental_columnvalueid">
          <form:errors path="peticio.tipusDocumental" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUSDOCUMENTAL)}" >
          <form:hidden path="peticio.tipusDocumental"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.tipusDocumental,__theForm.listOfValuesForTipusDocumental)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUSDOCUMENTAL)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_tipusDocumental"  onchange="if(typeof onChangeTipusDocumental == 'function') {  onChangeTipusDocumental(this); };"  cssClass="form-control col-md-9-optional" path="peticio.tipusDocumental">
            <c:forEach items="${__theForm.listOfValuesForTipusDocumental}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.IDIOMADOC)}">
        <tr id="peticio_idiomaDoc_rowid">
          <td id="peticio_idiomaDoc_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.IDIOMADOC])?'peticio.idiomaDoc':__theForm.labels[PeticioFields.IDIOMADOC]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.IDIOMADOC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.IDIOMADOC]}" ></i>
              </c:if>
            </td>
          <td id="peticio_idiomaDoc_columnvalueid">
          <form:errors path="peticio.idiomaDoc" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMADOC)}" >
          <form:hidden path="peticio.idiomaDoc"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.idiomaDoc,__theForm.listOfValuesForIdiomaDoc)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMADOC)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_idiomaDoc"  onchange="if(typeof onChangeIdiomaDoc == 'function') {  onChangeIdiomaDoc(this); };"  cssClass="form-control col-md-9-optional" path="peticio.idiomaDoc">
            <c:forEach items="${__theForm.listOfValuesForIdiomaDoc}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.INFOSIGNATURAID)}">
        <tr id="peticio_infoSignaturaID_rowid">
          <td id="peticio_infoSignaturaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.INFOSIGNATURAID])?'peticio.infoSignaturaID':__theForm.labels[PeticioFields.INFOSIGNATURAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.INFOSIGNATURAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.INFOSIGNATURAID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_infoSignaturaID_columnvalueid">
          <form:errors path="peticio.infoSignaturaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOSIGNATURAID)}" >
          <form:hidden path="peticio.infoSignaturaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.infoSignaturaID,__theForm.listOfInfoSignaturaForInfoSignaturaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOSIGNATURAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_infoSignaturaID"  onchange="if(typeof onChangeInfoSignaturaID == 'function') {  onChangeInfoSignaturaID(this); };"  cssClass="form-control col-md-9-optional" path="peticio.infoSignaturaID">
            <c:forEach items="${__theForm.listOfInfoSignaturaForInfoSignaturaID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticio.infoSignaturaID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticio.infoSignaturaID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.TIPUS)}">
        <tr id="peticio_tipus_rowid">
          <td id="peticio_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.TIPUS])?'peticio.tipus':__theForm.labels[PeticioFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="peticio_tipus_columnvalueid">
          <form:errors path="peticio.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUS)}" >
          <form:hidden path="peticio.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="peticio.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ERRORMSG)}">
        <tr id="peticio_errorMsg_rowid">
          <td id="peticio_errorMsg_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ERRORMSG])?'peticio.errorMsg':__theForm.labels[PeticioFields.ERRORMSG]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ERRORMSG]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ERRORMSG]}" ></i>
              </c:if>
            </td>
          <td id="peticio_errorMsg_columnvalueid">
              <form:errors path="peticio.errorMsg" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ERRORMSG)? 'true' : 'false'}" path="peticio.errorMsg"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_errorMsg" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_errorMsg" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticio.errorMsg'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('peticio.errorMsg'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticio.errorMsg'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_errorMsg').on('click', function(){
					var valor = ($('#dropdownMenuContainer_errorMsg').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_errorMsg').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ERROREXCEPTION)}">
        <tr id="peticio_errorException_rowid">
          <td id="peticio_errorException_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ERROREXCEPTION])?'peticio.errorException':__theForm.labels[PeticioFields.ERROREXCEPTION]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ERROREXCEPTION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ERROREXCEPTION]}" ></i>
              </c:if>
            </td>
          <td id="peticio_errorException_columnvalueid">
              <form:errors path="peticio.errorException" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ERROREXCEPTION)? 'true' : 'false'}" path="peticio.errorException"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_errorException" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_errorException" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticio.errorException'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('peticio.errorException'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('peticio.errorException'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_errorException').on('click', function(){
					var valor = ($('#dropdownMenuContainer_errorException').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_errorException').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.PETICIOPORTAFIRMES)}">
        <tr id="peticio_peticioPortafirmes_rowid">
          <td id="peticio_peticioPortafirmes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.PETICIOPORTAFIRMES])?'peticio.peticioPortafirmes':__theForm.labels[PeticioFields.PETICIOPORTAFIRMES]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.PETICIOPORTAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.PETICIOPORTAFIRMES]}" ></i>
              </c:if>
            </td>
          <td id="peticio_peticioPortafirmes_columnvalueid">
            <form:errors path="peticio.peticioPortafirmes" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.PETICIOPORTAFIRMES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.PETICIOPORTAFIRMES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.peticioPortafirmes"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.REASON)}">
        <tr id="peticio_reason_rowid">
          <td id="peticio_reason_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.REASON])?'peticio.reason':__theForm.labels[PeticioFields.REASON]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.REASON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.REASON]}" ></i>
              </c:if>
            </td>
          <td id="peticio_reason_columnvalueid">
            <form:errors path="peticio.reason" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.REASON)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.REASON)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.reason"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUFUNCIONARIUSERNAME)}">
        <tr id="peticio_arxiuFuncionariUsername_rowid">
          <td id="peticio_arxiuFuncionariUsername_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUFUNCIONARIUSERNAME])?'peticio.arxiuFuncionariUsername':__theForm.labels[PeticioFields.ARXIUFUNCIONARIUSERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUFUNCIONARIUSERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUFUNCIONARIUSERNAME]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuFuncionariUsername_columnvalueid">
            <form:errors path="peticio.arxiuFuncionariUsername" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUFUNCIONARIUSERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUFUNCIONARIUSERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuFuncionariUsername"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINOM)}">
        <tr id="peticio_arxiuParamFuncionariNom_rowid">
          <td id="peticio_arxiuParamFuncionariNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARINOM])?'peticio.arxiuParamFuncionariNom':__theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARINOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUPARAMFUNCIONARINOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUPARAMFUNCIONARINOM]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuParamFuncionariNom_columnvalueid">
            <form:errors path="peticio.arxiuParamFuncionariNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARINOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARINOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuParamFuncionariNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINIF)}">
        <tr id="peticio_arxiuParamFuncionariNif_rowid">
          <td id="peticio_arxiuParamFuncionariNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARINIF])?'peticio.arxiuParamFuncionariNif':__theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARINIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUPARAMFUNCIONARINIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUPARAMFUNCIONARINIF]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuParamFuncionariNif_columnvalueid">
            <form:errors path="peticio.arxiuParamFuncionariNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARINIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARINIF)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuParamFuncionariNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)}">
        <tr id="peticio_arxiuParamFuncionariDir3_rowid">
          <td id="peticio_arxiuParamFuncionariDir3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARIDIR3])?'peticio.arxiuParamFuncionariDir3':__theForm.labels[PeticioFields.ARXIUPARAMFUNCIONARIDIR3]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUPARAMFUNCIONARIDIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUPARAMFUNCIONARIDIR3]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuParamFuncionariDir3_columnvalueid">
            <form:errors path="peticio.arxiuParamFuncionariDir3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuParamFuncionariDir3"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}">
        <tr id="peticio_arxiuReqParamDocEstatElabora_rowid">
          <td id="peticio_arxiuReqParamDocEstatElabora_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMDOCESTATELABORA])?'peticio.arxiuReqParamDocEstatElabora':__theForm.labels[PeticioFields.ARXIUREQPARAMDOCESTATELABORA]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMDOCESTATELABORA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMDOCESTATELABORA]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamDocEstatElabora_columnvalueid">
          <form:errors path="peticio.arxiuReqParamDocEstatElabora" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}" >
          <form:hidden path="peticio.arxiuReqParamDocEstatElabora"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.arxiuReqParamDocEstatElabora,__theForm.listOfValuesForArxiuReqParamDocEstatElabora)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_arxiuReqParamDocEstatElabora"  onchange="if(typeof onChangeArxiuReqParamDocEstatElabora == 'function') {  onChangeArxiuReqParamDocEstatElabora(this); };"  cssClass="form-control col-md-9-optional" path="peticio.arxiuReqParamDocEstatElabora">
            <c:forEach items="${__theForm.listOfValuesForArxiuReqParamDocEstatElabora}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticio.arxiuReqParamDocEstatElabora }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticio.arxiuReqParamDocEstatElabora }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMINTERESSATS)}">
        <tr id="peticio_arxiuReqParamInteressats_rowid">
          <td id="peticio_arxiuReqParamInteressats_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMINTERESSATS])?'peticio.arxiuReqParamInteressats':__theForm.labels[PeticioFields.ARXIUREQPARAMINTERESSATS]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMINTERESSATS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMINTERESSATS]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamInteressats_columnvalueid">
            <form:errors path="peticio.arxiuReqParamInteressats" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMINTERESSATS)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMINTERESSATS)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuReqParamInteressats"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANIF)}">
        <tr id="peticio_arxiuReqParamCiutadaNif_rowid">
          <td id="peticio_arxiuReqParamCiutadaNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMCIUTADANIF])?'peticio.arxiuReqParamCiutadaNif':__theForm.labels[PeticioFields.ARXIUREQPARAMCIUTADANIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMCIUTADANIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMCIUTADANIF]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamCiutadaNif_columnvalueid">
            <form:errors path="peticio.arxiuReqParamCiutadaNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMCIUTADANIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMCIUTADANIF)? ' uneditable-input' : ''}"  style="" maxlength="15" path="peticio.arxiuReqParamCiutadaNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANOM)}">
        <tr id="peticio_arxiuReqParamCiutadaNom_rowid">
          <td id="peticio_arxiuReqParamCiutadaNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMCIUTADANOM])?'peticio.arxiuReqParamCiutadaNom':__theForm.labels[PeticioFields.ARXIUREQPARAMCIUTADANOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMCIUTADANOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMCIUTADANOM]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamCiutadaNom_columnvalueid">
            <form:errors path="peticio.arxiuReqParamCiutadaNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMCIUTADANOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMCIUTADANOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuReqParamCiutadaNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMORGANS)}">
        <tr id="peticio_arxiuReqParamOrgans_rowid">
          <td id="peticio_arxiuReqParamOrgans_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMORGANS])?'peticio.arxiuReqParamOrgans':__theForm.labels[PeticioFields.ARXIUREQPARAMORGANS]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMORGANS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMORGANS]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamOrgans_columnvalueid">
            <form:errors path="peticio.arxiuReqParamOrgans" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMORGANS)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMORGANS)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuReqParamOrgans"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)}">
        <tr id="peticio_arxiuOptParamProcedimentCodi_rowid">
          <td id="peticio_arxiuOptParamProcedimentCodi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI])?'peticio.arxiuOptParamProcedimentCodi':__theForm.labels[PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuOptParamProcedimentCodi_columnvalueid">
            <form:errors path="peticio.arxiuOptParamProcedimentCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuOptParamProcedimentCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)}">
        <tr id="peticio_arxiuOptParamProcedimentNom_rowid">
          <td id="peticio_arxiuOptParamProcedimentNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM])?'peticio.arxiuOptParamProcedimentNom':__theForm.labels[PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuOptParamProcedimentNom_columnvalueid">
            <form:errors path="peticio.arxiuOptParamProcedimentNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuOptParamProcedimentNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)}">
        <tr id="peticio_arxiuOptParamSerieDocumental_rowid">
          <td id="peticio_arxiuOptParamSerieDocumental_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL])?'peticio.arxiuOptParamSerieDocumental':__theForm.labels[PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuOptParamSerieDocumental_columnvalueid">
            <form:errors path="peticio.arxiuOptParamSerieDocumental" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuOptParamSerieDocumental"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)}">
        <tr id="peticio_arxiuOptParamExpedientId_rowid">
          <td id="peticio_arxiuOptParamExpedientId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUOPTPARAMEXPEDIENTID])?'peticio.arxiuOptParamExpedientId':__theForm.labels[PeticioFields.ARXIUOPTPARAMEXPEDIENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUOPTPARAMEXPEDIENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUOPTPARAMEXPEDIENTID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuOptParamExpedientId_columnvalueid">
            <form:errors path="peticio.arxiuOptParamExpedientId" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.arxiuOptParamExpedientId"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.ARXIUREQPARAMORIGEN)}">
        <tr id="peticio_arxiuReqParamOrigen_rowid">
          <td id="peticio_arxiuReqParamOrigen_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.ARXIUREQPARAMORIGEN])?'peticio.arxiuReqParamOrigen':__theForm.labels[PeticioFields.ARXIUREQPARAMORIGEN]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.ARXIUREQPARAMORIGEN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.ARXIUREQPARAMORIGEN]}" ></i>
              </c:if>
            </td>
          <td id="peticio_arxiuReqParamOrigen_columnvalueid">
          <form:errors path="peticio.arxiuReqParamOrigen" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMORIGEN)}" >
          <form:hidden path="peticio.arxiuReqParamOrigen"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.arxiuReqParamOrigen,__theForm.listOfValuesForArxiuReqParamOrigen)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.ARXIUREQPARAMORIGEN)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_arxiuReqParamOrigen"  onchange="if(typeof onChangeArxiuReqParamOrigen == 'function') {  onChangeArxiuReqParamOrigen(this); };"  cssClass="form-control col-md-9-optional" path="peticio.arxiuReqParamOrigen">
            <c:forEach items="${__theForm.listOfValuesForArxiuReqParamOrigen}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticio.arxiuReqParamOrigen }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticio.arxiuReqParamOrigen }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PeticioFields.INFOARXIUID)}">
        <tr id="peticio_infoArxiuID_rowid">
          <td id="peticio_infoArxiuID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.INFOARXIUID])?'peticio.infoArxiuID':__theForm.labels[PeticioFields.INFOARXIUID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.INFOARXIUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.INFOARXIUID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_infoArxiuID_columnvalueid">
          <form:errors path="peticio.infoArxiuID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOARXIUID)}" >
          <form:hidden path="peticio.infoArxiuID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.infoArxiuID,__theForm.listOfInfoArxiuForInfoArxiuID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOARXIUID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_infoArxiuID"  onchange="if(typeof onChangeInfoArxiuID == 'function') {  onChangeInfoArxiuID(this); };"  cssClass="form-control col-md-9-optional" path="peticio.infoArxiuID">
            <c:forEach items="${__theForm.listOfInfoArxiuForInfoArxiuID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticio.infoArxiuID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticio.infoArxiuID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
