  <c:if test="${empty grupItems}">
     <%@include file="grupListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="grupListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="grupListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="grupListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="grup" items="${grupItems}">

        <tr id="grup_rowid_${grup.grupID}">
          <%@include file="grupListCoreMultipleSelect.jsp" %>

          <%@include file="grupListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="grupListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
