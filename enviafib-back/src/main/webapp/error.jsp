<%@page import="es.caib.enviafib.back.security.LoginException"
%><%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"
%><%@page import="org.apache.log4j.Logger"
%><%@page import="org.fundaciobit.genapp.common.web.HtmlUtils"
%><%@ page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page import="java.util.Locale"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%!

protected final Logger log = Logger.getLogger(getClass());

%><%
// Página d'error que mostra per pantalla i amb format els errors que es produeixen.
long idError = System.currentTimeMillis() % 100;
try {

    log.error(" =================  ENTRA ERROR.JSP (" + idError 
        + " - " + (String)request.getAttribute("javax.servlet.error.request_uri") 
        + request.getSession().getId() + ") ===================");
    
  
    /**
     * No se per quina rao, però perd la configuració de l'idioma de l'usuari
     * i posa per defecte la del navegador.
     */    
    { 
      Locale loc = request.getLocale();
      if (loc != null) {
        LocaleContextHolder.setLocale(loc);
        {
          log.error("Set locale to " + LocaleContextHolder.getLocale() + " from Request");
        }
      }
    } 

    // Agafam la excepció que s'ha produit.
    Exception e = pageContext.getException();
    boolean sessioinvalida = false;;
    String missatgeSessioInvalida = "";
    String missatgeTipusError="";
    String redirect = null;

    if (e == null){
        String stipusError = request.getParameter("errortype");

        if (pageContext.getErrorData() != null) {
            log.error("[[" + idError + "]] Requested getRequestURI: "
                    + pageContext.getErrorData().getRequestURI() );
            log.error("[[" + idError + "]] Requested getServletName: "
                    + pageContext.getErrorData().getServletName() );
            log.error("[[" + idError + "]] Requested getStatusCode: "
                    + pageContext.getErrorData().getStatusCode() );
        }
        int tipusError = !stipusError.isEmpty()?new Integer(stipusError).intValue():-1;

        switch (tipusError) {
            case 403:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.403", (String)request.getAttribute("javax.servlet.error.request_uri"));
                break;
            case 404:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.404", (String)request.getAttribute("javax.servlet.error.request_uri"));
                break;
            default:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.desconegut");
        }
    } else {
    	
      log.error("Error: " + e.getMessage(), e);
      // Si els errors són de perdua de sessio no mostram el botó per tornar a principal,
      // han de tancar navegador i tornar obrir sessió.
      boolean runAsException = ( e instanceof IllegalArgumentException
          && "Either callerSubject or callerRunAs should be non-null".equals(e.getMessage()) );
      if ( runAsException 
          || ( e instanceof org.springframework.web.HttpSessionRequiredException )
          || ( e instanceof javax.ejb.EJBAccessException && ("Invalid User".equals(e.getMessage())))) {
          // fora botó
          sessioinvalida = true;
          missatgeSessioInvalida = I18NUtils.tradueix("error.jsp.sessioinvalida");
          if (runAsException) {
            redirect = (String)request.getAttribute("javax.servlet.error.request_uri");
          }
      } else {
         
           log.error("[[" + idError + "]] Exceptio de tipus " + e.getClass() );
           log.error("[[" + idError + "]] Exceptio MSG " + e.getMessage() );
         
      }
    }
    // Definim les etiquetes que mostram traduides.
    String etiquetaBoto = I18NUtils.tradueix("error.jsp.tornarprincipal");
    String titolPagina  = I18NUtils.tradueix("error.jsp.pagina");
    String detallError  = I18NUtils.tradueix("error.jsp.detall");
    String veureDetall  = I18NUtils.tradueix("error.jsp.veuredetall");

    request.getSession().setAttribute("locale", LocaleContextHolder.getLocale().toString());


%>
<fmt:setLocale value="${locale}"/>
<html>
<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">
<script src="<c:url value="/js/jquery.js" />"></script>
<script src="<c:url value="/js/bootstrap.js"/>"></script>

<script type="text/javascript">
  function showError(){
    $(".trace").toggle();
  }

<% if (redirect != null) {
      request.getSession().setAttribute("redirect", redirect);
%>
  setTimeout("location.href = '${redirect}';",3500);
<% } %>
</script>

<body>
 <div class="alert alert-danger">
      <c:set var="stacktrace"  value="${pageContext.exception.stackTrace}"/>
      <div><h4><%=titolPagina%></h4></div>
      <br/>
      <% if (!sessioinvalida) { %>
        <c:if test = "${empty stacktrace}">
            <%= missatgeTipusError%>
        </c:if>
        <c:if test = "${not empty stacktrace}">
            <div><b>Error:</b>${pageContext.exception.message}&nbsp;(${requestScope['javax.servlet.forward.request_uri']})</div>
        </c:if>
      <% } %>
      <div><b><%=missatgeSessioInvalida%></b></div>
      <br/>
      <!-- Mostram el stacktrace de l'excepció en cas que hi hagi -->
      <div>
        <!-- Botó de mostrar stacktrace en cas que hi hagi stacktrace -->
        <% if (!sessioinvalida) { %>
        <c:if test="${not empty stacktrace}">
            <button class="btn btn-danger" onclick="showError()"><%=veureDetall%></button>
        </c:if>

        <!-- Mostram el botó de tornar a principal -->
        <a href="<c:url value="/"/>" class="btn btn-secondary"><%=etiquetaBoto%></a>
        
        <br/>
        
        
        <b>[[[[ MESSAGE: ${pageContext.exception.message}]]]</b>

        <br/>
        <br/>
        <!-- Mostram la traça de l'error -->
        <c:if test="${not empty stacktrace}">
            <div class="trace"><b><%=detallError%></b></div>
            <c:forEach var="trace" items="${stacktrace}">
              <p class="trace">"${trace}"</p>
            </c:forEach>
            <br/>
         </c:if>
       <% } %>
      </div>
  </div>
</body>

</html>
<%} catch (Throwable t){
    log.error(t.getMessage(), t);
    out.println("<html><body>S'ha produit un error dins error.jsp " +t.getMessage()+"</body></html>");
  } finally {
    log.error(" =================  FINAL ERROR.JSP (" + idError + ")");
  }%>