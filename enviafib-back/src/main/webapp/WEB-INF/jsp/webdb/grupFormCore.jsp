<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupFields" className="es.caib.enviafib.model.fields.GrupFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupFields.NOM)}">
        <tr id="grup_nom_rowid">
          <td id="grup_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupFields.NOM])?'grup.nom':__theForm.labels[GrupFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[GrupFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="grup_nom_columnvalueid">
              <form:errors path="grup.nom" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,GrupFields.NOM)? 'true' : 'false'}" path="grup.nom"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_nom" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_nom" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grup.nom'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('grup.nom'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grup.nom'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_nom').on('click', function(){
					var valor = ($('#dropdownMenuContainer_nom').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_nom').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupFields.DESCRIPCIO)}">
        <tr id="grup_descripcio_rowid">
          <td id="grup_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupFields.DESCRIPCIO])?'grup.descripcio':__theForm.labels[GrupFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[GrupFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="grup_descripcio_columnvalueid">
              <form:errors path="grup.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,GrupFields.DESCRIPCIO)? 'true' : 'false'}" path="grup.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grup.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('grup.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('grup.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
