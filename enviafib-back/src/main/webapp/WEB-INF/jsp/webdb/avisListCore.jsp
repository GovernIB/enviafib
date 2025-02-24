  <c:if test="${empty avisItems}">
     <%@include file="avisListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty avisItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="avisListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="avisListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="avisListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="avis" items="${avisItems}">

        <tr id="avis_rowid_${avis.avisID}">
          <%@include file="avisListCoreMultipleSelect.jsp" %>

          <%@include file="avisListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="avisListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
