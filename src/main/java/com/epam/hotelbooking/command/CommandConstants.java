package com.epam.hotelbooking.command;

/**
 * Contains the possible command as constants.
 * The class is final and have private constructor
 * because it shouldn't have subclasses and shouldn't
 * be created as object instance.
 *
 * @author Nickolai Barysevich.
 */
public final class CommandConstants {

    public static final String COMMAND = "command";

    public static final String SHOW_LOGIN = "showLogin";
    public static final String LOGIN = "login";
    public static final String EXIT = "exit";
    public static final String PROFILE = "profile";
    public static final String MANAGEMENT = "management";
    public static final String ROOM_CHOOSE = "roomChoose";
    public static final String ASSIGN_ROOM = "assignRoom";
    public static final String REGISTER_APPLICATION = "registerApplication";
    public static final String SHOW_APPLICATION_REGISTRATION = "showApplicationRegistration";
    public static final String SHOW_INFO = "showInfo";
    public static final String SHOW_ERROR = "showError";
    public static final String SHOW_REGISTRATION = "showRegistration";
    public static final String REGISTRATION = "registration";
    public static final String APPLICATION_HISTORY = "applicationHistory";
    public static final String CANCEL_CONSIDERING = "cancelConsidering";
    public static final String CANCEL_APPROVED = "cancelApproved";
    public static final String SHOW_BALANCE = "showBalance";
    public static final String ADD_BALANCE = "addBalance";
    public static final String PAY = "pay";

    private CommandConstants() {

    }

}