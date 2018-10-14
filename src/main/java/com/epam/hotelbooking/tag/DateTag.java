package com.epam.hotelbooking.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

public class DateTag extends TagSupport {

    private String daysToAdd;

    public void setDaysToAdd(String daysToAdd) {
        this.daysToAdd = daysToAdd;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String theDate ;
            if (daysToAdd != null) {
                int days = Integer.parseInt(daysToAdd);
                theDate = getDate(days);
            } else {
                theDate = getCurrentDate();
            }
            pageContext.getOut().write(theDate);
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    private String getDate(int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDate = currentDate.plusDays(days);
        return nextDate.toString();
    }


    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }


}
