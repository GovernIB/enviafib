<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<script type="text/javascript">

function submitPage(page) {
    submitForm("<c:url value="${contexte}/list/" />" + page, false);
}

</script>

<nav aria-label="Page navigation" style="z-index:1000">
    <ul class="pagination pagination-sm" style="display: inline-flex;">

        <%
        int maxCasellesPerMostrar = 10;

        int start;
        int end;

        int totalPages = (Integer) request.getAttribute("totalPages");
        int currentIndex = (Integer) request.getAttribute("currentIndex");

        if (totalPages <= maxCasellesPerMostrar) {
            start = 1;
            end = totalPages;
        } else {

            start = currentIndex - (int) Math.floor(maxCasellesPerMostrar / 2);
            end = currentIndex + (int) Math.floor(maxCasellesPerMostrar / 2);

            if (start < 1) {
                // Passam els numeros negatius a end 
                end = end - start;
                start = 1;
            } else {

                // Passam els numeros que passen de totalPagines a start  
                if (end > totalPages) {
                    start = start - (end - totalPages);
                    end = totalPages;
                }
            }

            start = Math.max(1, start);
            end = Math.min(end, totalPages);

        }

        System.out.println(" Valor de Start |"+start+"|");
        request.setAttribute("start", start);
        request.setAttribute("end", end);

        System.out.println(" S'ha ficat atribut Start correctament");


        // Si el parametre onClickSelectElementsByPage te valor, existeix una funcio per canviar el nº de elements
        // per tant s'ha de incloure l'element per canviar el nº de elements.

        // Si el parametre elementsByPage te valors, agafarlos, si no, agafar 5, 10 i 25 per defecte.
        //console.log("PaginationCarpeta::render() =>  pagines ...");

        // First |< 
        if (start != 1) {
        %>
        <li class="page-item"><a class="page-link" href="#" onclick="submitPage(0)"
            title="<fmt:message key="genapp.pagination.primerapagina" />">
                <div style="transform: rotate(-90deg)">&#8892;</div>
        </a></li>
        <%
        }

        // Retrocedir << 
        if (start != 1) {
        request.setAttribute("retrocedir", Math.floor(start / 2));
        %>
        <fmt:message var="retrocedirTitle" key="genapp.pagination.retrocedir">
            <fmt:param value="${retrocedir}" />
        </fmt:message>
        <li class="page-item"><a class="page-link" href="#" style="letter-spacing: -4px;" onclick="submitPage(${retrocedir})"
            title="${retrocedirTitle}"> &lt;&lt; </a></li>
        <%
        }

        // Previous < 
        if (currentIndex != 1) {
        %>
        <li class="page-item"><a class="page-link" href="#"
            onclick="submitPage(<%=Math.max(1, currentIndex - 1)%>)"
            title="<fmt:message key="genapp.pagination.anterior" />"> &#60; </a></li>
        <%
        }
        %>


        <c:forEach var="i" begin="${start}" end="${end}">
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="#" onclick="submitPage(${i})"> <c:out
                                value="${i}" />
                    </a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%
        // Seguent >
        if (currentIndex != totalPages) {
        %>

        <li class="page-item"><a class="page-link" href="#"
            onclick="submitPage(<%=Math.min(currentIndex + 1, totalPages)%>)"
            title="<fmt:message key="genapp.pagination.seguent" />"> &#62; </a></li>
        <%
        }

        // Avançar >>
        if (end != totalPages) {
            request.setAttribute("avancar", end + Math.floor((totalPages - end) / 2));
        %>
        <fmt:message var="avancarTitle" key="genapp.pagination.avancar">
            <fmt:param value="${avancar}" />
        </fmt:message>
        <li class="page-item"><a class="page-link" style="letter-spacing:-4px;" href="#" onclick="submitPage(${avancar})"
            title="${avancarTitle}">&#62;&#62;</a></li>
        <%
        }

        // Last  >|
        if (end != totalPages) {
        %>
        <li class="page-item"><a class="page-link" href="#" onclick="submitPage(${totalPages})"
            title="<fmt:message key="genapp.pagination.darrerapagina" />">
                <div style="transform: rotate(+90deg)">&#8892;</div>
        </a></li>
        <%
        }
        %>
    </ul>
</nav>
