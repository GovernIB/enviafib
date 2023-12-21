  <c:if test="${empty infoAnexItems}">
     <%@include file="infoAnexListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty infoAnexItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="infoAnexListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="infoAnexListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="infoAnexListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="infoAnex" items="${infoAnexItems}">

        <tr id="infoAnex_rowid_${infoAnex.infoanexid}">
          <%@include file="infoAnexListCoreMultipleSelect.jsp" %>

          <%@include file="infoAnexListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="infoAnexListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
