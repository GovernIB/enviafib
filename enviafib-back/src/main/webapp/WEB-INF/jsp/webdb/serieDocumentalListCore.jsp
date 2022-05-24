  <c:if test="${empty serieDocumentalItems}">
     <%@include file="serieDocumentalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty serieDocumentalItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="serieDocumentalListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="serieDocumentalListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="serieDocumentalListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="serieDocumental" items="${serieDocumentalItems}">

        <tr id="serieDocumental_rowid_${serieDocumental.seriedocuid}">
          <%@include file="serieDocumentalListCoreMultipleSelect.jsp" %>

          <%@include file="serieDocumentalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="serieDocumentalListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
