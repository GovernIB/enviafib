  <c:if test="${empty grupUsuariItems}">
     <%@include file="grupUsuariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupUsuariItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="grupUsuariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="grupUsuariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="grupUsuariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="grupUsuari" items="${grupUsuariItems}">

        <tr id="grupUsuari_rowid_${grupUsuari.grupUsuariID}">
          <%@include file="grupUsuariListCoreMultipleSelect.jsp" %>

          <%@include file="grupUsuariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="grupUsuariListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
