package com.epam.hotelbooking.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Sets the locale to the request and response.
 */
public class LocaleFilter implements Filter {

    /**
     * Language attribute name
     */
    private static final String LANGUAGE = "language";

    /**
     * Locale attribute name
     */
    private static final String LOCALE = "locale";

    /**
     * Russian locale. Default locale.
     */
    private static final String RU_LOCALE = "ru";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);

        String languageParameter = request.getParameter(LANGUAGE);

        if (languageParameter == null) {
            if (session.getAttribute(LANGUAGE) == null) {
                Locale defaultLocale = Locale.forLanguageTag(RU_LOCALE);
                session.setAttribute(LANGUAGE, defaultLocale);
                request.setAttribute(LOCALE, defaultLocale);
                response.setLocale(defaultLocale);
            }
        } else {
            Locale locale = Locale.forLanguageTag(languageParameter);
            session.setAttribute(LANGUAGE, locale);
            request.setAttribute(LOCALE, locale);
            response.setLocale(locale);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
