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
        <tr id="peticio_datacreacio_rowid">
          <td id="peticio_datacreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.DATACREACIO])?'peticio.datacreacio':__theForm.labels[PeticioFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="peticio_datacreacio_columnvalueid">
    <form:errors path="peticio.datacreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="peticio_datacreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#peticio_datacreacio" path="peticio.datacreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#peticio_datacreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#peticio_datacreacio').datetimepicker({
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
            <div class="form-group">
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
        <tr id="peticio_destinatarinif_rowid">
          <td id="peticio_destinatarinif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.DESTINATARINIF])?'peticio.destinatarinif':__theForm.labels[PeticioFields.DESTINATARINIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.DESTINATARINIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.DESTINATARINIF]}" ></i>
              </c:if>
            </td>
          <td id="peticio_destinatarinif_columnvalueid">
            <form:errors path="peticio.destinatarinif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.DESTINATARINIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.DESTINATARINIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="peticio.destinatarinif"   />

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
        <tr id="peticio_tipusdocumental_rowid">
          <td id="peticio_tipusdocumental_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.TIPUSDOCUMENTAL])?'peticio.tipusdocumental':__theForm.labels[PeticioFields.TIPUSDOCUMENTAL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.TIPUSDOCUMENTAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.TIPUSDOCUMENTAL]}" ></i>
              </c:if>
            </td>
          <td id="peticio_tipusdocumental_columnvalueid">
          <form:errors path="peticio.tipusdocumental" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUSDOCUMENTAL)}" >
          <form:hidden path="peticio.tipusdocumental"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.tipusdocumental,__theForm.listOfValuesForTipusdocumental)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.TIPUSDOCUMENTAL)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_tipusdocumental"  onchange="if(typeof onChangeTipusdocumental == 'function') {  onChangeTipusdocumental(this); };"  cssClass="form-control col-md-9-optional" path="peticio.tipusdocumental">
            <c:forEach items="${__theForm.listOfValuesForTipusdocumental}" var="tmp">
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
        <tr id="peticio_idiomadoc_rowid">
          <td id="peticio_idiomadoc_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.IDIOMADOC])?'peticio.idiomadoc':__theForm.labels[PeticioFields.IDIOMADOC]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.IDIOMADOC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.IDIOMADOC]}" ></i>
              </c:if>
            </td>
          <td id="peticio_idiomadoc_columnvalueid">
          <form:errors path="peticio.idiomadoc" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMADOC)}" >
          <form:hidden path="peticio.idiomadoc"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.idiomadoc,__theForm.listOfValuesForIdiomadoc)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.IDIOMADOC)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_idiomadoc"  onchange="if(typeof onChangeIdiomadoc == 'function') {  onChangeIdiomadoc(this); };"  cssClass="form-control col-md-9-optional" path="peticio.idiomadoc">
            <c:forEach items="${__theForm.listOfValuesForIdiomadoc}" var="tmp">
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
        <tr id="peticio_infosignaturaid_rowid">
          <td id="peticio_infosignaturaid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PeticioFields.INFOSIGNATURAID])?'peticio.infosignaturaid':__theForm.labels[PeticioFields.INFOSIGNATURAID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PeticioFields.INFOSIGNATURAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PeticioFields.INFOSIGNATURAID]}" ></i>
              </c:if>
            </td>
          <td id="peticio_infosignaturaid_columnvalueid">
          <form:errors path="peticio.infosignaturaid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOSIGNATURAID)}" >
          <form:hidden path="peticio.infosignaturaid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.peticio.infosignaturaid,__theForm.listOfInfoSignaturaForInfosignaturaid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PeticioFields.INFOSIGNATURAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="peticio_infosignaturaid"  onchange="if(typeof onChangeInfosignaturaid == 'function') {  onChangeInfosignaturaid(this); };"  cssClass="form-control col-md-9-optional" path="peticio.infosignaturaid">
            <c:forEach items="${__theForm.listOfInfoSignaturaForInfosignaturaid}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.peticio.infosignaturaid }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.peticio.infosignaturaid }">
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
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PeticioFields.ERRORMSG)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PeticioFields.ERRORMSG)? ' uneditable-input' : ''}"  style="" maxlength="255" path="peticio.errorMsg"   />

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
        
