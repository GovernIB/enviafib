  <c:if test="${empty entitatItems}">
     <%@include file="entitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty entitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="entitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="entitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="entitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="entitat" items="${entitatItems}">

        <tr id="entitat_rowid_${entitat.entitatid}">
          <%@include file="entitatListCoreMultipleSelect.jsp" %>

          <%@include file="entitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="entitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
