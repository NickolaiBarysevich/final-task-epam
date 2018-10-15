package com.epam.hotelbooking.command;

/**
 * Contains the possible jsp pages as constants.
 * The class is final and have private constructor
 * because it shouldn't have subclasses and shouldn't
 * be created as object instance.
 *
 * @author Nickolai Barysevich.
 */
public final class JspConstants {

    public static String MANAGEMENT_JSP = "/WEB-INF/management.jsp";
    public static String ERROR_JSP = "/WEB-INF/error.jsp";
    public static String APPLICATION_REGISTRATION_JSP = "/WEB-INF/applicationRegistration.jsp";
    public static String LOGIN_JSP = "/WEB-INF/login.jsp";
    public static String PROFILE_JSP = "/WEB-INF/profile.jsp";
    public static String ROOM_CHOOSE_JSP = "/WEB-INF/roomChoose.jsp";
    public static String APPLICATION_INFO_JSP = "/WEB-INF/applicationInfo.jsp";
    public static String REGISTRATION_JSP = "/WEB-INF/registration.jsp";
    public static String APPLICATION_HISTORY_JSP = "/WEB-INF/applicationHistory.jsp";
    public static String BALANCE_JSP = "/WEB-INF/balance.jsp";

    private JspConstants() {
    }

}
