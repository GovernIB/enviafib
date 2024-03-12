  <c:if test="${empty fitxerItems}">
     <%@include file="fitxerListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty fitxerItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="fitxerListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="fitxerListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="fitxerListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="fitxer" items="${fitxerItems}">

        <tr id="fitxer_rowid_${fitxer.fitxerID}">
          <%@include file="fitxerListCoreMultipleSelect.jsp" %>

          <%@include file="fitxerListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="fitxerListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
