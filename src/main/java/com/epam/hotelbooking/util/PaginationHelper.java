package com.epam.hotelbooking.util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PaginationHelper<T> {

    private static final String PAGE = "page";
    private static final int FIRST_PAGE = 1;

    private final List<T> listToPaginate;

    private final int recordsPerPage;
    private final int defaultPage;

    public PaginationHelper(List<T> listToPaginate, int recordsPerPage, int defaultPage) {
        this.listToPaginate = listToPaginate;
        this.recordsPerPage = recordsPerPage;
        this.defaultPage = defaultPage;
    }

    public PaginationHelper(List<T> listToPaginate, int recordsPerPage) {
        this.listToPaginate = listToPaginate;
        this.recordsPerPage = recordsPerPage;
        defaultPage = FIRST_PAGE;
    }

    public List<T> getPage(int page) {
        int from = (page - 1) * recordsPerPage;
        int to = from + recordsPerPage;

        if (to > listToPaginate.size()) {
            to = listToPaginate.size();
        }

        return listToPaginate.subList(from, to);
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public int getNumberOfPages() {
        return (int) Math.ceil((listToPaginate.size() * 1.0) / recordsPerPage);
    }

    public int findPage(HttpServletRequest request) {
        int page = defaultPage;
        if (request.getParameter(PAGE) != null) {
            String pageParam = request.getParameter(PAGE);
            page = Integer.parseInt(pageParam);
        }

        return page;
    }
}
