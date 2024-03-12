  <c:if test="${empty menuItems}">
     <%@include file="menuListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty menuItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="menuListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="menuListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="menuListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="menu" items="${menuItems}">

        <tr id="menu_rowid_${menu.menuID}">
          <%@include file="menuListCoreMultipleSelect.jsp" %>

          <%@include file="menuListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="menuListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
