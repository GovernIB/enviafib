  <c:if test="${empty peticioItems}">
     <%@include file="peticioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty peticioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="peticioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="peticioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="peticioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="peticio" items="${peticioItems}">

        <tr id="peticio_rowid_${peticio.peticioID}">
          <%@include file="peticioListCoreMultipleSelect.jsp" %>

          <%@include file="peticioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="peticioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
