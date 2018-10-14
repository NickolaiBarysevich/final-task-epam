<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=balance&language=${locale}" var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>

<html lang="${language}">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/base.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/profile.css"/>
    <title>BestHotel.com</title>
</head>
<body>
<main class="content">
    <div class="profile-content">

        <div class="link-container">
            <a class="profile-link" href="controller?command=profile"><fmt:message key="profile.personalData"
                                                                                   bundle="${lang}"/></a>
            <a class="profile-link" href="controller?command=applicationHistory"><fmt:message key="profile.applicationHistory" bundle="${lang}"/></a>
            <a class="profile-link" href="#"><fmt:message key="profile.balance" bundle="${lang}"/></a>
        </div>
        <hr>

        <div class="info-container">
            <form action="controller" method="post">
                <h3><fmt:message key="profile.currentBalance" bundle="${lang}"/> <fmt:formatNumber value="${user.wallet}" currencyCode="USD" type="currency"/></h3>
                <input name="command" value="addBalance" type="hidden">
                <input name="addingValue" type="text" title="Введите сумму для пополнения"
                       pattern="\d{1,6}(\.\d{1,2})?">
                <button class="profile-link"><fmt:message key="profile.replenish" bundle="${lang}"/></button>
                <c:if test="${param.get('balanceError') != null}">
                    <p class="red_text"><fmt:message key="${param.get('balanceError')}" bundle="${lang}"/></p>
                </c:if>
            </form>
        </div>
    </div>
</main>
</body>
</html>
