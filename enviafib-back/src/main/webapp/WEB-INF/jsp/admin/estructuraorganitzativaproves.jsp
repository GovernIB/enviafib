<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>

<h2>Resultat d'Estructura Organitzativa per '${username}'</h2>
<style>
fieldset.scheduler-border {
    border: 1px groove #ddd !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

    legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0 10px;
        border-bottom:none;
    }
</style>
<c:forEach var="entry" items="${estructura}">
 
<br/>
<fieldset>
<legend><b>${entry.key}</b></legend>
 <table class="table table-bordered">
<c:forEach var="part" items="${entry.value}">
 <tr>
  <td><c:out value="${part.key}"/></td>
  <td><b>
     
     <c:out value="${(empty part.value)? '--' : part.value}"/>
     
     
     </b></td>
  </tr>
</c:forEach>
</table>
</fieldset>

</c:forEach>


<input type="button" class="btn btn-secondary" onclick="goTo('<c:url value="${tornarurl}"/>')" value="Tornar">


  





