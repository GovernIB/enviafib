<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MenuFields" className="es.caib.enviafib.model.fields.MenuFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.NOM)}">
        <tr id="menu_nom_rowid">
          <td id="menu_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.NOM])?'menu.nom':__theForm.labels[MenuFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="menu_nom_columnvalueid">
            <form:errors path="menu.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MenuFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,MenuFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="menu.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.DESCRIPCIO)}">
        <tr id="menu_descripcio_rowid">
          <td id="menu_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.DESCRIPCIO])?'menu.descripcio':__theForm.labels[MenuFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="menu_descripcio_columnvalueid">
              <form:errors path="menu.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,MenuFields.DESCRIPCIO)? 'true' : 'false'}" path="menu.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('menu.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('menu.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('menu.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.TITOLMENUID)}">
        <tr id="menu_titolMenuID_rowid">
          <td id="menu_titolMenuID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.TITOLMENUID])?'menu.titolMenuID':__theForm.labels[MenuFields.TITOLMENUID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.TITOLMENUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.TITOLMENUID]}" ></i>
              </c:if>
            </td>
          <td id="menu_titolMenuID_columnvalueid">
       <form:errors path="menu.titolMenu" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_titolMenu_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_titolMenu_${idioma.idiomaID}">
               <form:errors path="menu.titolMenu.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="menu.titolMenu.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,MenuFields.TITOLMENUID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,MenuFields.TITOLMENUID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.AJUDAMENUID)}">
        <tr id="menu_ajudaMenuID_rowid">
          <td id="menu_ajudaMenuID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.AJUDAMENUID])?'menu.ajudaMenuID':__theForm.labels[MenuFields.AJUDAMENUID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.AJUDAMENUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.AJUDAMENUID]}" ></i>
              </c:if>
            </td>
          <td id="menu_ajudaMenuID_columnvalueid">
       <form:errors path="menu.ajudaMenu" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_ajudaMenu_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_ajudaMenu_${idioma.idiomaID}">
               <form:errors path="menu.ajudaMenu.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="menu.ajudaMenu.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,MenuFields.AJUDAMENUID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,MenuFields.AJUDAMENUID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.ORDRE)}">
        <tr id="menu_ordre_rowid">
          <td id="menu_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.ORDRE])?'menu.ordre':__theForm.labels[MenuFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="menu_ordre_columnvalueid">
            <form:errors path="menu.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,MenuFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,MenuFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="menu.ordre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.TIPUS)}">
        <tr id="menu_tipus_rowid">
          <td id="menu_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.TIPUS])?'menu.tipus':__theForm.labels[MenuFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="menu_tipus_columnvalueid">
          <form:errors path="menu.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MenuFields.TIPUS)}" >
          <form:hidden path="menu.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.menu.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MenuFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="menu_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="menu.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.GRUPID)}">
        <tr id="menu_grupID_rowid">
          <td id="menu_grupID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.GRUPID])?'menu.grupID':__theForm.labels[MenuFields.GRUPID]}" />
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.GRUPID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.GRUPID]}" ></i>
              </c:if>
            </td>
          <td id="menu_grupID_columnvalueid">
          <form:errors path="menu.grupID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MenuFields.GRUPID)}" >
          <form:hidden path="menu.grupID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.menu.grupID,__theForm.listOfGrupForGrupID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MenuFields.GRUPID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="menu_grupID"  onchange="if(typeof onChangeGrupID == 'function') {  onChangeGrupID(this); };"  cssClass="form-control col-md-9-optional" path="menu.grupID">
            <c:forEach items="${__theForm.listOfGrupForGrupID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.menu.grupID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.menu.grupID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.PARAMETRECOMBO)}">
        <tr id="menu_parametreCombo_rowid">
          <td id="menu_parametreCombo_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.PARAMETRECOMBO])?'menu.parametreCombo':__theForm.labels[MenuFields.PARAMETRECOMBO]}" />
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.PARAMETRECOMBO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.PARAMETRECOMBO]}" ></i>
              </c:if>
            </td>
          <td id="menu_parametreCombo_columnvalueid">
          <form:errors path="menu.parametreCombo" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MenuFields.PARAMETRECOMBO)}" >
          <form:hidden path="menu.parametreCombo"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.menu.parametreCombo,__theForm.listOfValuesForParametreCombo)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MenuFields.PARAMETRECOMBO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="menu_parametreCombo"  onchange="if(typeof onChangeParametreCombo == 'function') {  onChangeParametreCombo(this); };"  cssClass="form-control col-md-9-optional" path="menu.parametreCombo">
            <c:forEach items="${__theForm.listOfValuesForParametreCombo}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.menu.parametreCombo }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.menu.parametreCombo }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.PARAMETRETEXT)}">
        <tr id="menu_parametreText_rowid">
          <td id="menu_parametreText_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.PARAMETRETEXT])?'menu.parametreText':__theForm.labels[MenuFields.PARAMETRETEXT]}" />
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.PARAMETRETEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.PARAMETRETEXT]}" ></i>
              </c:if>
            </td>
          <td id="menu_parametreText_columnvalueid">
              <form:errors path="menu.parametreText" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,MenuFields.PARAMETRETEXT)? 'true' : 'false'}" path="menu.parametreText"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_parametreText" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_parametreText" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('menu.parametreText'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('menu.parametreText'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('menu.parametreText'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_parametreText').on('click', function(){
					var valor = ($('#dropdownMenuContainer_parametreText').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_parametreText').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,MenuFields.ACTIU)}">
        <tr id="menu_actiu_rowid">
          <td id="menu_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[MenuFields.ACTIU])?'menu.actiu':__theForm.labels[MenuFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[MenuFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[MenuFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="menu_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,MenuFields.ACTIU)}" >
              <form:errors path="menu.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,MenuFields.ACTIU)? 'false' : 'true'}" path="menu.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,MenuFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.menu.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
