<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FaqFields" className="es.caib.enviafib.model.fields.FaqFields"/>
  

  <%-- HIDDEN PARAMS: GROUP BY --%>
  <form:hidden path="visibleGroupBy"/>
  <form:hidden id="agruparPerCamp" path="groupBy"/> 
  <form:hidden id="agruparPerValor"  path="groupValue"/>

<%-- AGRUPAR PER - FUNCIONS --%>   
<script type="text/javascript">
  $("body").on("nodeselect.tree.data-api", "[role=leaf]", function (e) {

      var parentstr = "" + e.node.parentage[e.node.parentage.length - 1];
      document.getElementById('agruparPerCamp').value = parentstr;
      document.getElementById('agruparPerValor').value = e.node.value;
      document.faq.submit();
  })

  function groupByFieldValue(camp, valor) {
    document.getElementById('agruparPerCamp').value = camp;
    document.getElementById('agruparPerValor').value = valor;
    document.faq.submit();
  }

</script>

  <%-- AGRUPAR PER - INICI --%>   

  <c:set var="displayGroupDiv" value="${__theFilterForm.visibleGroupBy?'':'display:none;'}" />  

  <c:if test="${fn:length(groupby_items) > 0}">
 <fmt:message var="buit" key="genapp.notdefined" />
  
  <div id="GroupDiv" class="wellgroupfilter" style="${displayGroupDiv} padding:1px; margin-right:4px; float:left; ">
      <div  style="text-align:right">
           <a style="margin-right:4px" href="#">
               <i title="<fmt:message key="genapp.form.hidegroupby"/>" onclick="document.getElementById('GroupDiv').style.display='none'; document.getElementById('GroupButton').style.display='inline';" class="far fa-window-close"></i>
            </a>
      </div>


<style> ul.gj-list-bootstrap li [data-role=display]{padding:0px 0px 0px 0px;}  ul.gj-list-bootstrap li [data-role=expander].gj-tree-material-icons-expander { padding-top: 0px; padding-bottom: 0px; } </style>      <div id="tree"></div>
      
      <script>
      
      
      <c:set var="expandID" value="-" />
      
      var treedata =
            [{
                "id": '-1',
                "text": "<b><fmt:message key="genapp.form.groupby"/></b>",
                "field": null,
                "hasChildren": true,
                "children": [{
                        "id": "${groupby_item.value}",
                        "text": "&#8811; <span style='${(__theFilterForm.groupBy eq null)? "font-weight: bold;" : ""}'><fmt:message key="genapp.form.groupby.noneitem"/></span>",
                        "field": ' ',
                        "hasChildren": false,
                        "children": []
                    }
                    <c:set var="counterParent" value="0" />
                    <c:forEach  var="groupby_item"  items="${groupby_items}">
                    
                        <c:set var="code" value="${(empty __theFilterForm.labels[groupby_item.field])? groupby_item.codeLabel:__theFilterForm.labels[groupby_item.field]}" />
                        <c:if test="${!fn:startsWith(code,'=')}" >
                        <fmt:message var="thetext" key="${code}">
                              <fmt:param><fmt:message key="${groupby_item.codeParamLabel}"/></fmt:param>
                        </fmt:message>
                        </c:if>
                        <c:if test="${fn:startsWith(code,'=')}" >
                        <c:set var="thetext" value="${fn:substringAfter(code, '=')}" />
                        </c:if>
                    
                    <c:set var="ParentID" value="${groupby_item.value}_${counterParent}" />
                    <c:set var="counterParent" value="${counterParent + 1}" />
                    
                    <c:if test="${groupby_item.selected}" >
                      <c:set var="expandID" value="${ParentID}"/>
                    </c:if>
                    ,{
                        "id": '${ParentID}',
                        "text": "<span style='${groupby_item.selected? "font-weight: bold;" : ""}'>${thetext}</span>",
                        "field": '${groupby_item.value}',
                        "hasChildren": true,
                        "children": [
                            
                            <c:set var="counterG" value="0" />
                            
                            <c:forEach  var="groupbyvalue_item"  items="${groupby_item.values}">
                            
                            
                            
                            <c:if test="${counterG ne 0}">,</c:if>
                            <c:set var="counterG" value="${counterG + 1}" />
                            {
                                "id": '${groupby_item.value}_${groupbyvalue_item.value}_${counterG}',
                                "text": "<span style='${groupbyvalue_item.selected? "font-weight: bold;" : ""}' >${ (empty groupbyvalue_item.codeLabel) ? buit : groupbyvalue_item.codeLabel } (${groupbyvalue_item.count})</span>",
                                "field": '${groupby_item.value}',
                                "value" : '${groupbyvalue_item.value}',
                                "hasChildren": false,
                                "children": []
                            }
                            
                          </c:forEach>
                            
                        ]
                    }   
                    
                    </c:forEach>

                ]
            }];

      var tree = $('#tree').tree({
          dataSource: treedata,
          primaryKey: 'id',
          uiLibrary: 'bootstrap4',
          select: function (e, node, id) {
              var nodedata = tree.getDataById(id);
              if (!nodedata.hasChildren) {
                  groupByFieldValue(nodedata.field, nodedata.value);                  
              }
          },
          icons: {
              expand: '<i class="far fa-plus-square"></i>',
              collapse: '<i class="far fa-minus-square"></i>'
          }
        });
      
          var noderoot = tree.getNodeById('-1');
          tree.expand(noderoot);
       <c:if test="${expandID ne '-'}" >
          var node = tree.getNodeById('${expandID}');
          tree.expand(node);
          
       </c:if>
      
      
      </script>
  </div>
 </c:if>
 
  <%-- AGRUPAR PER - FINAL --%>

