package com.epam.hotelbooking.util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Split the list of {@link T} to "pages".
 * Records per page specified into constructor.
 *
 * @param <T> type of the elements in the list.
 */
public class PaginationHelper<T> {

    private static final String PAGE = "page";
    private static final int FIRST_PAGE = 1;

    private final List<T> listToPaginate;

    private final int recordsPerPage;
    private final int defaultPage;

    /**
     * Creates and constructs object with specified default apge.
     *
     * @param listToPaginate list that must be paginated.
     * @param recordsPerPage records the will be print on the page.
     * @param defaultPage default page that will be printed.
     */
    public PaginationHelper(List<T> listToPaginate, int recordsPerPage, int defaultPage) {
        this.listToPaginate = listToPaginate;
        this.recordsPerPage = recordsPerPage;
        this.defaultPage = defaultPage;
    }

    /**
     * Creates and constructs this object.
     *
     * @param listToPaginate list that must be paginated.
     * @param recordsPerPage records the will be print on the page.
     */
    public PaginationHelper(List<T> listToPaginate, int recordsPerPage) {
        this.listToPaginate = listToPaginate;
        this.recordsPerPage = recordsPerPage;
        defaultPage = FIRST_PAGE;
    }

    /**
     * Return the list that the page should represent.
     *
     * @param page needed page.
     * @return list of the {@link T}
     */
    public List<T> getPage(int page) {
        int numberOfPages = getNumberOfPages();
        if (page > numberOfPages && page != 1) {
            page = numberOfPages;
        }

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

    /**
     * Calculates the number of pages in paginated list.
     *
     * @return number of pages in list.
     */
    public int getNumberOfPages() {
        return (int) Math.ceil((listToPaginate.size() * 1.0) / recordsPerPage);
    }

    /**
     * Tries to find page parameter is {@link HttpServletRequest}.
     *
     * @param request request that should be analysed.
     * @return the page if it was found. Otherwise return default page.
     */
    public int findPage(HttpServletRequest request) {
        int page = defaultPage;
        if (request.getParameter(PAGE) != null) {
            String pageParam = request.getParameter(PAGE);
            page = Integer.parseInt(pageParam);
        }

        return page;
    }
}
