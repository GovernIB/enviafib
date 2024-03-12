  <c:if test="${empty infoArxiuItems}">
     <%@include file="infoArxiuListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty infoArxiuItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="infoArxiuListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="infoArxiuListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="infoArxiuListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="infoArxiu" items="${infoArxiuItems}">

        <tr id="infoArxiu_rowid_${infoArxiu.infoArxiuID}">
          <%@include file="infoArxiuListCoreMultipleSelect.jsp" %>

          <%@include file="infoArxiuListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="infoArxiuListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
