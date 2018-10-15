<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=applicationHistory&language=${locale}&page=${currentPage}" var="langRedirect"
       scope="request"/>
<jsp:include page="maket.jsp"/>

<html lang="${language}">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/profile.css"/>
    <title>BestHotel.com</title>
</head>
<body>
<main class="content">

    <div class="profile-content">
        <c:if test="${param.get('message') != null}">
            <div class="message-block"><fmt:message key="${param.get('message')}" bundle="${lang}"/></div>
        </c:if>

        <div class="link-container">

            <a class="profile-link" href="controller?command=profile"><fmt:message key="profile.personalData"
                                                                                   bundle="${lang}"/></a>
            <a class="profile-link" href="#"><fmt:message key="profile.applicationHistory" bundle="${lang}"/></a>
            <a class="profile-link" href="controller?command=showBalance"><fmt:message key="profile.balance"
                                                                                       bundle="${lang}"/></a>
            <a class="make-application-link" href="controller?command=showApplicationRegistration"><fmt:message
                    key="profile.makeApplication" bundle="${lang}"/></a>
        </div>
        <hr>

        <div class="info-container">

            <table>
                <tr>
                    <th><fmt:message key="profile.applicationNumber" bundle="${lang}"/></th>
                    <th class="to_hide"><fmt:message key="home.checkIn" bundle="${lang}"/></th>
                    <th class="to_hide"><fmt:message key="home.checkOut" bundle="${lang}"/></th>
                    <th><fmt:message key="applicationInfo.status" bundle="${lang}"/></th>
                    <th><fmt:message key="profile.info" bundle="${lang}"/></th>
                </tr>
                <c:forEach items="${applicationBill}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td class="to_hide"><fmt:formatDate value="${item.checkIn}"/></td>
                        <td class="to_hide"><fmt:formatDate value="${item.checkOut}"/></td>
                        <td><fmt:message key="management.${item.status.value}" bundle="${lang}"/></td>
                        <td class="info-link-td">
                            <a class="info-link" href="controller?command=showInfo&application_id=${item.id}">info</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
        <div class="page-container">
            <c:if test="${currentPage > 1}">
                <a class="page-button" href="controller?command=applicationHistory&page=${currentPage - 1}"><fmt:message
                        key="profile.previous" bundle="${lang}"/></a>
            </c:if>
            <c:if test="${1 lt numOfPages}">
                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <span class="selected">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a class="page-button" href="controller?command=applicationHistory&page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
            <c:if test="${currentPage lt numOfPages}">
                <a class="page-button"
                   href="controller?command=applicationHistory&page=${currentPage + 1}"><fmt:message key="profile.next"
                                                                                                     bundle="${lang}"/></a>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
