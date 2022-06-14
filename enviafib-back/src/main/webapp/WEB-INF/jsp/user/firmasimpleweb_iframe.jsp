<%@ include file="/WEB-INF/views/html_header.jsp"%>

<style>
    body{
       background-image: url("<c:url value="/img/bg.png"/>");
         background-repeat: repeat;
    }
</style>

<br/><br/>

<center>
<iframe id="iframediv" src="${urlToIFrameCode}" width="600px" height="700px"></iframe>
</center>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>
