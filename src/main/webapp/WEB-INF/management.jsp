<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=management&language=${locale}&sort=${sort}&page=${currentPage}" var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>


<html lang="${language}">
<head>
    <title>BestHotel.com</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/base.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/management.css"/>
    <script language="JavaScript" type="text/javascript"
            src="../resources/js/jquery-3.3.1.min.js"></script>

</head>
<body>
<main class="content">
    <c:if test="${managementError != null}">
        <div class="red_text error-block align-center"><fmt:message key="${managementError}" bundle="${lang}"/></div>
    </c:if>
    <div class="work-place">

        <form action="controller">
            <a class="another-link" href="controller?command=management&sort=all"><fmt:message key="management.all"
                                                                                               bundle="${lang}"/></a>
            <a class="another-link" href="controller?command=management&sort=approved"><fmt:message
                    key="management.approved" bundle="${lang}"/></a>
            <a class="another-link" href="controller?command=management&sort=considering"><fmt:message
                    key="management.considering" bundle="${lang}"/></a>
            <a class="another-link" href="controller?command=management&sort=paid"><fmt:message
                    key="management.paid" bundle="${lang}"/></a>
            <a class="another-link" href="controller?command=management&sort=canceled"><fmt:message
                    key="management.canceled" bundle="${lang}"/></a>
            <a class="another-link" href="controller?command=management&sort=expired"><fmt:message
                    key="management.expired" bundle="${lang}"/></a>

            <input id="application_id" type="hidden" name="application_id">
            <input type="hidden" name="command" value="roomChoose">
            <button type="submit" class="assign-button another-link"><fmt:message key="management.assignRoom"
                                                                                  bundle="${lang}"/></button>
        </form>

        <table cellspacing="0">
            <tr>
                <th><fmt:message key="profile.applicationNumber" bundle="${lang}"/></th>
                <th><fmt:message key="login.username" bundle="${lang}"/></th>
                <th><fmt:message key="profile.name" bundle="${lang}"/></th>
                <th><fmt:message key="profile.phoneNumber" bundle="${lang}"/></th>
                <th><fmt:message key="home.guests" bundle="${lang}"/></th>
                <th><fmt:message key="home.checkIn" bundle="${lang}"/></th>
                <th><fmt:message key="home.checkOut" bundle="${lang}"/></th>
                <th><fmt:message key="home.roomClass" bundle="${lang}"/></th>
                <th><fmt:message key="applicationInfo.roomNumber" bundle="${lang}"/></th>
                <th><fmt:message key="applicationInfo.cost" bundle="${lang}"/></th>
            </tr>
            <c:forEach var="application" items="${applications}" varStatus="theCount">
                <tr tabindex="-1">
                    <td>${application.id}</td>
                    <td>${application.username}</td>
                    <td>${application.fullName}</td>
                    <td>${application.phoneNumber}</td>
                    <td>${application.places}</td>
                    <td><fmt:formatDate value="${application.checkIn}"/></td>
                    <td><fmt:formatDate value="${application.checkOut}"/></td>
                    <td><fmt:message key="home.${application.roomClass}" bundle="${lang}"/></td>
                    <td>${application.roomId}</td>
                    <td><fmt:formatNumber value="${application.cost}" currencyCode="USD" type="currency"/></td>
                </tr>
            </c:forEach>

        </table>

    </div>
    <div class="pages-holder">
        <c:if test="${currentPage > 1}">
            <a class="page-button"
               href="controller?command=management&sort=${sort}&page=${currentPage - 1}"><fmt:message
                    key="profile.previous" bundle="${lang}"/></a>
        </c:if>
        <c:if test="${1 lt numOfPages}">
            <c:forEach begin="1" end="${numOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <span class="selected">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-button" href="controller?command=management&sort=${sort}&page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:if test="${currentPage lt numOfPages}">
            <a class="page-button"
               href="controller?command=management&sort=${sort}&page=${currentPage + 1}"><fmt:message key="profile.next"
                                                                                                      bundle="${lang}"/></a>
        </c:if>
    </div>
</main>
<script type="text/javascript">
    $(document).ready(function () {
        $('table tr').click(function () {
            document.getElementById('application_id').value = $('td:first-child', this).html();
        })
    });
</script>
</body>
</html>
