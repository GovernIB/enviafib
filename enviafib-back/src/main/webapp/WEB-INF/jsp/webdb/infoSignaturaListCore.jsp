  <c:if test="${empty infoSignaturaItems}">
     <%@include file="infoSignaturaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty infoSignaturaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="infoSignaturaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="infoSignaturaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="infoSignaturaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="infoSignatura" items="${infoSignaturaItems}">

        <tr id="infoSignatura_rowid_${infoSignatura.infosignaturaid}">
          <%@include file="infoSignaturaListCoreMultipleSelect.jsp" %>

          <%@include file="infoSignaturaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="infoSignaturaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
