<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.enviafib.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ENTITATID)}">
        <tr id="entitat_entitatid_rowid">
          <td id="entitat_entitatid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ENTITATID])?'entitat.entitatid':__theForm.labels[EntitatFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_entitatid_columnvalueid">
            <form:errors path="entitat.entitatid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ENTITATID)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.ENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.entitatid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOM)}">
        <tr id="entitat_nom_rowid">
          <td id="entitat_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOM])?'entitat.nom':__theForm.labels[EntitatFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="entitat_nom_columnvalueid">
            <form:errors path="entitat.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
        <tr id="entitat_descripcio_rowid">
          <td id="entitat_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.DESCRIPCIO])?'entitat.descripcio':__theForm.labels[EntitatFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="entitat_descripcio_columnvalueid">
              <form:errors path="entitat.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="entitat.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ADREZAHTML)}">
        <tr id="entitat_adrezahtml_rowid">
          <td id="entitat_adrezahtml_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ADREZAHTML])?'entitat.adrezahtml':__theForm.labels[EntitatFields.ADREZAHTML]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.ADREZAHTML]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ADREZAHTML]}" ></i>
              </c:if>
            </td>
          <td id="entitat_adrezahtml_columnvalueid">
              <form:errors path="entitat.adrezahtml" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,EntitatFields.ADREZAHTML)? 'mceEditorReadOnly':'mceEditor'}"  path="entitat.adrezahtml"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ACTIVA)}">
        <tr id="entitat_activa_rowid">
          <td id="entitat_activa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ACTIVA])?'entitat.activa':__theForm.labels[EntitatFields.ACTIVA]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ACTIVA]}" ></i>
              </c:if>
            </td>
          <td id="entitat_activa_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
              <form:errors path="entitat.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)? 'false' : 'true'}" path="entitat.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <tr id="entitat_suporttelefon_rowid">
          <td id="entitat_suporttelefon_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTTELEFON])?'entitat.suporttelefon':__theForm.labels[EntitatFields.SUPORTTELEFON]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTTELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTTELEFON]}" ></i>
              </c:if>
            </td>
          <td id="entitat_suporttelefon_columnvalueid">
            <form:errors path="entitat.suporttelefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.suporttelefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <tr id="entitat_suportweb_rowid">
          <td id="entitat_suportweb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTWEB])?'entitat.suportweb':__theForm.labels[EntitatFields.SUPORTWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTWEB]}" ></i>
              </c:if>
            </td>
          <td id="entitat_suportweb_columnvalueid">
            <form:errors path="entitat.suportweb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? ' uneditable-input' : ''}"  style="" maxlength="250" path="entitat.suportweb"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <tr id="entitat_suportemail_rowid">
          <td id="entitat_suportemail_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTEMAIL])?'entitat.suportemail':__theForm.labels[EntitatFields.SUPORTEMAIL]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTEMAIL]}" ></i>
              </c:if>
            </td>
          <td id="entitat_suportemail_columnvalueid">
            <form:errors path="entitat.suportemail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="entitat.suportemail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.FAVICONID)}">
        <tr id="entitat_faviconID_rowid">
          <td id="entitat_faviconID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.FAVICONID])?'entitat.faviconID':__theForm.labels[EntitatFields.FAVICONID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.FAVICONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.FAVICONID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_faviconID_columnvalueid">
              <form:errors path="entitat.faviconID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.favicon)}"/>">${__theForm.entitat.favicon.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.FAVICONID)? ' uneditable-input' : ''}"   path="faviconID" type="file" />
                  <label class="custom-file-label" for="faviconID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.favicon}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.favicon)}"/>">${__theForm.entitat.favicon.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="faviconID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#faviconID').on('change', function(){
						var ruta = $('#faviconID').val(); 
						var rutaArray = ruta.split('\\');
						$('#faviconID-custom-file-label').css('display','block');
						$('#faviconID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOWEBID)}">
        <tr id="entitat_logowebID_rowid">
          <td id="entitat_logowebID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOWEBID])?'entitat.logowebID':__theForm.labels[EntitatFields.LOGOWEBID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOWEBID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_logowebID_columnvalueid">
              <form:errors path="entitat.logowebID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logoweb)}"/>">${__theForm.entitat.logoweb.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBID)? ' uneditable-input' : ''}"   path="logowebID" type="file" />
                  <label class="custom-file-label" for="logowebID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoweb}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logoweb)}"/>">${__theForm.entitat.logoweb.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logowebID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logowebID').on('change', function(){
						var ruta = $('#logowebID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logowebID-custom-file-label').css('display','block');
						$('#logowebID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
        <tr id="entitat_logowebpeuID_rowid">
          <td id="entitat_logowebpeuID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOWEBPEUID])?'entitat.logowebpeuID':__theForm.labels[EntitatFields.LOGOWEBPEUID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOWEBPEUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOWEBPEUID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_logowebpeuID_columnvalueid">
              <form:errors path="entitat.logowebpeuID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logowebpeu)}"/>">${__theForm.entitat.logowebpeu.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOWEBPEUID)? ' uneditable-input' : ''}"   path="logowebpeuID" type="file" />
                  <label class="custom-file-label" for="logowebpeuID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logowebpeu}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logowebpeu)}"/>">${__theForm.entitat.logowebpeu.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logowebpeuID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logowebpeuID').on('change', function(){
						var ruta = $('#logowebpeuID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logowebpeuID-custom-file-label').css('display','block');
						$('#logowebpeuID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
        <tr id="entitat_logosegellID_rowid">
          <td id="entitat_logosegellID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOSEGELLID])?'entitat.logosegellID':__theForm.labels[EntitatFields.LOGOSEGELLID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOSEGELLID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_logosegellID_columnvalueid">
              <form:errors path="entitat.logosegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logosegell)}"/>">${__theForm.entitat.logosegell.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOSEGELLID)? ' uneditable-input' : ''}"   path="logosegellID" type="file" />
                  <label class="custom-file-label" for="logosegellID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logosegell}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${efi:fileUrl(__theForm.entitat.logosegell)}"/>">${__theForm.entitat.logosegell.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logosegellID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logosegellID').on('change', function(){
						var ruta = $('#logosegellID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logosegellID-custom-file-label').css('display','block');
						$('#logosegellID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.WEB)}">
        <tr id="entitat_web_rowid">
          <td id="entitat_web_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.WEB])?'entitat.web':__theForm.labels[EntitatFields.WEB]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.WEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.WEB]}" ></i>
              </c:if>
            </td>
          <td id="entitat_web_columnvalueid">
            <form:errors path="entitat.web" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.WEB)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.WEB)? ' uneditable-input' : ''}"  style="" maxlength="250" path="entitat.web"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
        <tr id="entitat_motiudelegacioID_rowid">
          <td id="entitat_motiudelegacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.MOTIUDELEGACIOID])?'entitat.motiudelegacioID':__theForm.labels[EntitatFields.MOTIUDELEGACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.MOTIUDELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.MOTIUDELEGACIOID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_motiudelegacioID_columnvalueid">
       <form:errors path="entitat.motiudelegacio" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_motiudelegacio_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_motiudelegacio_${idioma.idiomaID}">
               <form:errors path="entitat.motiudelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="entitat.motiudelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.MOTIUDELEGACIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EntitatFields.MOTIUDELEGACIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SEGELLDETEMPSVIAWEB)}">
        <tr id="entitat_segelldetempsviaweb_rowid">
          <td id="entitat_segelldetempsviaweb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SEGELLDETEMPSVIAWEB])?'entitat.segelldetempsviaweb':__theForm.labels[EntitatFields.SEGELLDETEMPSVIAWEB]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.SEGELLDETEMPSVIAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SEGELLDETEMPSVIAWEB]}" ></i>
              </c:if>
            </td>
          <td id="entitat_segelldetempsviaweb_columnvalueid">
            <form:errors path="entitat.segelldetempsviaweb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SEGELLDETEMPSVIAWEB)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SEGELLDETEMPSVIAWEB)? ' uneditable-input' : ''}"  style=""  path="entitat.segelldetempsviaweb"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
        <tr id="entitat_checkcanviatdocfirmat_rowid">
          <td id="entitat_checkcanviatdocfirmat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CHECKCANVIATDOCFIRMAT])?'entitat.checkcanviatdocfirmat':__theForm.labels[EntitatFields.CHECKCANVIATDOCFIRMAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
            </td>
          <td id="entitat_checkcanviatdocfirmat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:errors path="entitat.checkcanviatdocfirmat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)? 'false' : 'true'}" path="entitat.checkcanviatdocfirmat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.CHECKCANVIATDOCFIRMAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.checkcanviatdocfirmat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PROPIETATSTAULAFIRMES)}">
        <tr id="entitat_propietatstaulafirmes_rowid">
          <td id="entitat_propietatstaulafirmes_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PROPIETATSTAULAFIRMES])?'entitat.propietatstaulafirmes':__theForm.labels[EntitatFields.PROPIETATSTAULAFIRMES]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.PROPIETATSTAULAFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PROPIETATSTAULAFIRMES]}" ></i>
              </c:if>
            </td>
          <td id="entitat_propietatstaulafirmes_columnvalueid">
              <form:errors path="entitat.propietatstaulafirmes" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.PROPIETATSTAULAFIRMES)? 'true' : 'false'}" path="entitat.propietatstaulafirmes"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_propietatstaulafirmes" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propietatstaulafirmes" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatstaulafirmes'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatstaulafirmes'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.propietatstaulafirmes'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propietatstaulafirmes').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propietatstaulafirmes').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propietatstaulafirmes').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.DIR3)}">
        <tr id="entitat_dir3_rowid">
          <td id="entitat_dir3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.DIR3])?'entitat.dir3':__theForm.labels[EntitatFields.DIR3]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.DIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.DIR3]}" ></i>
              </c:if>
            </td>
          <td id="entitat_dir3_columnvalueid">
            <form:errors path="entitat.dir3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.DIR3)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.DIR3)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.dir3"   />

           </td>
        </tr>
        </c:if>
        
