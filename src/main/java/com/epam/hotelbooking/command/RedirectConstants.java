package com.epam.hotelbooking.command;

/**
 * Contains the redirect paths to jsp pages as constants.
 * The class is final and have private constructor
 * because it shouldn't have subclasses and shouldn't
 * be created as object instance.
 *
 * @author Nickolai Barysevich.
 */
public final class RedirectConstants {

    public static final String APPLICATION_REGISTRATION_REDIRECT = "/controller?command=showApplicationRegistration";
    public static final String ERROR_REDIRECT = "/controller?command=showError";
    public static final String MANAGEMENT_REDIRECT = "/controller?command=management";
    public static final String SHOW_REGISTRATION_REDIRECT = "/controller?command=showRegistration";
    public static final String PROFILE_REDIRECT = "/controller?command=profile";
    public static final String SHOW_LOGIN_REDIRECT = "/controller?command=showLogin";
    public static final String BALANCE_REDIRECT = "/controller?command=showBalance";
    public static final String APPLICATION_HISTORY_REDIRECT = "/controller?command=applicationHistory";
    public static final String ROOM_CHOOSE_REDIRECT = "/controller?command=roomChoose";

    private RedirectConstants() {
    }

}
