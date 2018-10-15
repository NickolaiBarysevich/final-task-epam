<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=profile&language=${locale}" var="langRedirect" scope="request"/>
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
            <a class="profile-link" href="#"><fmt:message key="profile.personalData" bundle="${lang}"/></a>
            <a class="profile-link" href="controller?command=applicationHistory"><fmt:message
                    key="profile.applicationHistory" bundle="${lang}"/></a>
            <a class="profile-link" href="controller?command=showBalance"><fmt:message key="profile.balance"
                                                                                       bundle="${lang}"/></a>
            <a class="make-application-link" href="controller?command=showApplicationRegistration"><fmt:message
                    key="profile.makeApplication" bundle="${lang}"/></a>
        </div>
        <hr>

        <div class="info-container">
            <p><fmt:message key="login.username" bundle="${lang}"/>: ${user.username}</p>
            <p><fmt:message key="profile.name" bundle="${lang}"/>: ${user.name}</p>
            <p><fmt:message key="profile.lastName" bundle="${lang}"/>: ${user.lastName}</p>
            <p><fmt:message key="profile.phoneNumber" bundle="${lang}"/>: ${user.phoneNumber}</p>
            <p><fmt:message key="profile.email" bundle="${lang}"/>: ${user.email}</p>
        </div>
    </div>
</main>
</body>
</html>
