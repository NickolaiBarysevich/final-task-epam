package com.epam.hotelbooking.task;

import java.util.TimerTask;

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
