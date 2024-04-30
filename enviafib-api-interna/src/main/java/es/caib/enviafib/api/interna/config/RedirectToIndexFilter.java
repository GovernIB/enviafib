package es.caib.enviafib.api.interna.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author anadal
 *
 */
@WebFilter(urlPatterns = { "/*" })
public class RedirectToIndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        final String uri = httpRequest.getRequestURI();

        /*
        
         System.out.println("============================================");
        
        System.out.println("getRequestURL: " + httpRequest.getRequestURL()); 
        
        System.out.println("getRequestURI: " + uri);
        */

        final String cp = httpRequest.getContextPath();
        //System.out.println("getContextPath: " + cp);

        String path = uri.substring(cp.length());
        //System.out.println("getPathInfo: " + path);

        if (path.startsWith("/public/") || path.startsWith("/secure/")) {
            chain.doFilter(request, response);
        } else if ("".equals(path) || "/".equals(path)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
        } else  {
            httpRequest.getServletContext().getNamedDispatcher("default").forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }
}