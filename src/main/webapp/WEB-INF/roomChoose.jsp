<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=roomChoose&language=${locale}&application_id=${application.id}&sort=${sort}&page=${currentPage}"
       var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>

<html lang="${language}">
<head>
    <title>BestHotel.com</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/management.css"/>
    <script language="JavaScript" type="text/javascript" src="../resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>

<main class="content">
    <c:if test="${param.get('chooseError') != null}">
        <div class="red_text error-block align-center"><fmt:message key="${param.get('chooseError')}"
                                                                    bundle="${lang}"/></div>
    </c:if>
    <div class="work-place">
        <div class="application-info">
        <span><fmt:message key="home.guests" bundle="${lang}"/>: ${application.places}.
        <fmt:message key="home.roomClass" bundle="${lang}"/>: <fmt:message key="home.${application.roomClass}"
                                                                           bundle="${lang}"/>.
        </span>

        </div>

        <form action="controller" method="post">

            <a class="another-link"
               href="controller?command=roomChoose&sort=free&application_id=${application.id}"><fmt:message
                    key="roomChoose.free" bundle="${lang}"/></a>
            <a class="another-link"
               href="controller?command=roomChoose&sort=busy&application_id=${application.id}"><fmt:message
                    key="roomChoose.busy" bundle="${lang}"/></a>
            <a class="another-link"
               href="controller?command=roomChoose&sort=all&application_id=${application.id}"><fmt:message
                    key="management.all" bundle="${lang}"/></a>


            <input id="room_id" type="hidden" name="room_id">
            <input id="price" type="hidden" name="price">
            <input type="hidden" name="command" value="assignRoom">
            <input type="hidden" name="application_id" value="${application.id}">
            <button class="assign-button another-link"><fmt:message key="roomChoose.assign" bundle="${lang}"/></button>
        </form>
        <table cellspacing="0" class="table">
            <tr>
                <th><fmt:message key="applicationInfo.roomNumber" bundle="${lang}"/></th>
                <th><fmt:message key="roomChoose.beds" bundle="${lang}"/></th>
                <th><fmt:message key="home.roomClass" bundle="${lang}"/></th>
                <th><fmt:message key="roomChoose.costPerPerson" bundle="${lang}"/></th>
            </tr>
            <c:forEach var="room" items="${roomList}">
                <tr tabindex="-1">
                    <td>${room.id}</td>
                    <td>${room.beds}</td>
                    <td><fmt:message key="home.${room.roomClass}" bundle="${lang}"/></td>
                    <td>${room.price}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="pages-holder">
        <c:if test="${currentPage > 1}">
            <a class="page-button"
               href="controller?command=roomChoose&sort=${sort}&page=${currentPage - 1}&application_id=${application.id}"><fmt:message
                    key="profile.previous" bundle="${lang}"/></a>
        </c:if>
        <c:if test="${1 lt numOfPages}">
            <c:forEach begin="1" end="${numOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <span class="selected">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-button"
                           href="controller?command=roomChoose&sort=${sort}&page=${i}&application_id=${application.id}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:if test="${currentPage lt numOfPages}">
            <a class="page-button"
               href="controller?command=roomChoose&sort=${sort}&page=${currentPage + 1}&application_id=${application.id}"><fmt:message
                    key="profile.next" bundle="${lang}"/></a>
        </c:if>
    </div>
</main>
<script type="text/javascript">
    $(document).ready(function () {
        $('table tr').click(function () {
            document.getElementById('room_id').value = $('td:first-child', this).html();
            document.getElementById('price').value = $('td:last-child', this).html();
        })
    });
</script>
</body>
</html>
