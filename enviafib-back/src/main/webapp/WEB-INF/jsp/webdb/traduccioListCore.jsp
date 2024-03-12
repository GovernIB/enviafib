  <c:if test="${empty traduccioItems}">
     <%@include file="traduccioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty traduccioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="traduccioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="traduccioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="traduccioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="traduccio" items="${traduccioItems}">

        <tr id="traduccio_rowid_${traduccio.traduccioID}">
          <%@include file="traduccioListCoreMultipleSelect.jsp" %>

          <%@include file="traduccioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="traduccioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
