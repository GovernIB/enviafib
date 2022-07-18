  <c:if test="${empty infoCustodyItems}">
     <%@include file="infoCustodyListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty infoCustodyItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="infoCustodyListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="infoCustodyListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="infoCustodyListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="infoCustody" items="${infoCustodyItems}">

        <tr id="infoCustody_rowid_${infoCustody.infocustodyid}">
          <%@include file="infoCustodyListCoreMultipleSelect.jsp" %>

          <%@include file="infoCustodyListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="infoCustodyListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
