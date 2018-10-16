package com.epam.hotelbooking.task;

import java.util.TimerTask;

/**
 * Creates a new task which defines whether the application is
 * expired.
 *
 * @author Nickolai Barysevich
 */
public class ApplicationCheckTask extends TimerTask {

    private final Thread applicationChecker;

    public ApplicationCheckTask(Thread applicationChecker) {
        this.applicationChecker = applicationChecker;
    }

    @Override
    public void run() {
        applicationChecker.start();
    }
}
