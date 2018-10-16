package com.epam.hotelbooking.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Sets the encoding to the request.
 *
 * @author Nickolai Barysevich
 */
public class EncodingFilter implements Filter {

    private static final String UTF_8_CHARSET = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getContentType() != null) {
            request.setCharacterEncoding(UTF_8_CHARSET);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
