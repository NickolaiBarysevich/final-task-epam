package com.epam.hotelbooking.command;

/**
 * Contains constants that often found in other classes.
 * The class is final and have private constructor
 * because it shouldn't have subclasses and shouldn't
 * be created as object instance.
 *
 * @author Nickolai Barysevich.
 */
public final class CommonConstants {

    public static final String APPLICATIONS_ATTRIBUTE = "applications";
    public static final String ROOM_LIST_ATTRIBUTE = "roomList";
    public static final String NUMBER_OF_PAGES_ATTRIBUTE = "numOfPages";
    public static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    public static final String SORT_PARAM = "sort";
    public static final String NOT_EXIST = " does not exist";

    private CommonConstants() {
    }
}
