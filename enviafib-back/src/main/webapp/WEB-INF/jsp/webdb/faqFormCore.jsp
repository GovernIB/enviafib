<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FaqFields" className="es.caib.enviafib.model.fields.FaqFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.ORDRE)}">
        <tr id="faq_ordre_rowid">
          <td id="faq_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.ORDRE])?'faq.ordre':__theForm.labels[FaqFields.ORDRE]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="faq_ordre_columnvalueid">
            <form:errors path="faq.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FaqFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="faq.ordre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.ENUNCIAT_ES)}">
        <tr id="faq_enunciat_es_rowid">
          <td id="faq_enunciat_es_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.ENUNCIAT_ES])?'faq.enunciat_es':__theForm.labels[FaqFields.ENUNCIAT_ES]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.ENUNCIAT_ES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.ENUNCIAT_ES]}" ></i>
              </c:if>
            </td>
          <td id="faq_enunciat_es_columnvalueid">
              <form:errors path="faq.enunciat_es" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.ENUNCIAT_ES)? 'true' : 'false'}" path="faq.enunciat_es"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_enunciat_es" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_enunciat_es" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_es'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_es'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_es'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_enunciat_es').on('click', function(){
					var valor = ($('#dropdownMenuContainer_enunciat_es').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_enunciat_es').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.ENUNCIAT_CA)}">
        <tr id="faq_enunciat_ca_rowid">
          <td id="faq_enunciat_ca_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.ENUNCIAT_CA])?'faq.enunciat_ca':__theForm.labels[FaqFields.ENUNCIAT_CA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.ENUNCIAT_CA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.ENUNCIAT_CA]}" ></i>
              </c:if>
            </td>
          <td id="faq_enunciat_ca_columnvalueid">
              <form:errors path="faq.enunciat_ca" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.ENUNCIAT_CA)? 'true' : 'false'}" path="faq.enunciat_ca"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_enunciat_ca" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_enunciat_ca" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_ca'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_ca'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('faq.enunciat_ca'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_enunciat_ca').on('click', function(){
					var valor = ($('#dropdownMenuContainer_enunciat_ca').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_enunciat_ca').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.RESPOSTA_ES)}">
        <tr id="faq_resposta_es_rowid">
          <td id="faq_resposta_es_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.RESPOSTA_ES])?'faq.resposta_es':__theForm.labels[FaqFields.RESPOSTA_ES]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.RESPOSTA_ES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.RESPOSTA_ES]}" ></i>
              </c:if>
            </td>
          <td id="faq_resposta_es_columnvalueid">
              <form:errors path="faq.resposta_es" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,FaqFields.RESPOSTA_ES)? 'mceEditorReadOnly':'mceEditor'}"  path="faq.resposta_es"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.RESPOSTA_CA)}">
        <tr id="faq_resposta_ca_rowid">
          <td id="faq_resposta_ca_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.RESPOSTA_CA])?'faq.resposta_ca':__theForm.labels[FaqFields.RESPOSTA_CA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.RESPOSTA_CA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.RESPOSTA_CA]}" ></i>
              </c:if>
            </td>
          <td id="faq_resposta_ca_columnvalueid">
              <form:errors path="faq.resposta_ca" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,FaqFields.RESPOSTA_CA)? 'mceEditorReadOnly':'mceEditor'}"  path="faq.resposta_ca"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.FITXER1ID)}">
        <tr id="faq_fitxer1ID_rowid">
          <td id="faq_fitxer1ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.FITXER1ID])?'faq.fitxer1ID':__theForm.labels[FaqFields.FITXER1ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.FITXER1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.FITXER1ID]}" ></i>
              </c:if>
            </td>
          <td id="faq_fitxer1ID_columnvalueid">
              <form:errors path="faq.fitxer1ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER1ID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer1)}"/>">${__theForm.faq.fitxer1.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER1ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER1ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER1ID)? ' uneditable-input' : ''}"   path="fitxer1ID" type="file" />
                  <label class="custom-file-label" for="fitxer1ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.faq.fitxer1}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer1)}"/>">${__theForm.faq.fitxer1.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer1IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer1ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer1ID').on('change', function(){
						var ruta = $('#fitxer1ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer1ID-custom-file-label').css('display','block');
						$('#fitxer1ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.FITXER2ID)}">
        <tr id="faq_fitxer2ID_rowid">
          <td id="faq_fitxer2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.FITXER2ID])?'faq.fitxer2ID':__theForm.labels[FaqFields.FITXER2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.FITXER2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.FITXER2ID]}" ></i>
              </c:if>
            </td>
          <td id="faq_fitxer2ID_columnvalueid">
              <form:errors path="faq.fitxer2ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER2ID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer2)}"/>">${__theForm.faq.fitxer2.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER2ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER2ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER2ID)? ' uneditable-input' : ''}"   path="fitxer2ID" type="file" />
                  <label class="custom-file-label" for="fitxer2ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.faq.fitxer2}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer2)}"/>">${__theForm.faq.fitxer2.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer2IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer2ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer2ID').on('change', function(){
						var ruta = $('#fitxer2ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer2ID-custom-file-label').css('display','block');
						$('#fitxer2ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FaqFields.FITXER3ID)}">
        <tr id="faq_fitxer3ID_rowid">
          <td id="faq_fitxer3ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FaqFields.FITXER3ID])?'faq.fitxer3ID':__theForm.labels[FaqFields.FITXER3ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FaqFields.FITXER3ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FaqFields.FITXER3ID]}" ></i>
              </c:if>
            </td>
          <td id="faq_fitxer3ID_columnvalueid">
              <form:errors path="faq.fitxer3ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER3ID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer3)}"/>">${__theForm.faq.fitxer3.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER3ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER3ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,FaqFields.FITXER3ID)? ' uneditable-input' : ''}"   path="fitxer3ID" type="file" />
                  <label class="custom-file-label" for="fitxer3ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.faq.fitxer3}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.faq.fitxer3)}"/>">${__theForm.faq.fitxer3.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer3IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer3ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer3ID').on('change', function(){
						var ruta = $('#fitxer3ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer3ID-custom-file-label').css('display','block');
						$('#fitxer3ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
