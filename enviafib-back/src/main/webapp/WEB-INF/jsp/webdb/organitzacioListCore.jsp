  <c:if test="${empty organitzacioItems}">
     <%@include file="organitzacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty organitzacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="organitzacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="organitzacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="organitzacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="organitzacio" items="${organitzacioItems}">

        <tr id="organitzacio_rowid_${organitzacio.organitzacioID}">
          <%@include file="organitzacioListCoreMultipleSelect.jsp" %>

          <%@include file="organitzacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="organitzacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
