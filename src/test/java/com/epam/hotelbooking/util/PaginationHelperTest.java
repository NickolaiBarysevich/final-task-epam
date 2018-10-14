package com.epam.hotelbooking.util;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class PaginationHelperTest {

    private static final int DEFAULT_PAGE = 1;
    private static final int TEST_RECORDS_PER_PAGE = 2;
    private static final List<Integer> TEST_LIST = Arrays.asList(1, 2, 3, 4, 5, 6);
    private static final int TEST_NUMBER_OF_PAGES = 3;
    private static final String PAGE_PARAM = "page";
    private static final String PAGE_PARAM_VALUE = "2";
    private static final int EXPECTED_PAGE = 2;

    @Test
    public void shouldReturnNumberOfPages() {
        PaginationHelper<Integer> paginationHelper = new PaginationHelper<>(TEST_LIST, TEST_RECORDS_PER_PAGE);
        int actual = paginationHelper.getNumberOfPages();

        Assert.assertEquals(actual, TEST_NUMBER_OF_PAGES);
    }

    @DataProvider
    private Object[][] getPageTestParameters() {
        return new Object[][]{
                {1, TEST_LIST.subList(0, 2)},
                {2, TEST_LIST.subList(2, 4)},
                {3, TEST_LIST.subList(4, 6)}
        };
    }

    @Test(dataProvider = "getPageTestParameters")
    public void shouldReturnConcretePageList(int page, List<Integer> expected) {
        PaginationHelper<Integer> paginationHelper = new PaginationHelper<>(TEST_LIST, TEST_RECORDS_PER_PAGE);
        List<Integer> actual = paginationHelper.getPage(page);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnConcretePage() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getParameter(PAGE_PARAM)).thenReturn(PAGE_PARAM_VALUE);

        PaginationHelper<Integer> paginationHelper = new PaginationHelper<>(TEST_LIST, TEST_RECORDS_PER_PAGE);
        int actual = paginationHelper.findPage(request);

        Assert.assertEquals(actual, EXPECTED_PAGE);
    }

    @Test
    public void shouldReturnDefaultPage() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getParameter(PAGE_PARAM)).thenReturn(null);

        PaginationHelper<Integer> paginationHelper = new PaginationHelper<>(TEST_LIST, TEST_RECORDS_PER_PAGE, DEFAULT_PAGE);
        int actual = paginationHelper.findPage(request);

        Assert.assertEquals(actual, DEFAULT_PAGE);
    }

}