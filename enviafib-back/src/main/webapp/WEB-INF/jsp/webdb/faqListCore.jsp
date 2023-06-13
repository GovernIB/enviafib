  <c:if test="${empty faqItems}">
     <%@include file="faqListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty faqItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="faqListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="faqListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="faqListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="faq" items="${faqItems}">

        <tr id="faq_rowid_${faq.faqID}">
          <%@include file="faqListCoreMultipleSelect.jsp" %>

          <%@include file="faqListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="faqListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
