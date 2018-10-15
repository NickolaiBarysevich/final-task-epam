<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<jsp:useBean id="date" class="java.util.Date"/>

<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Krub|Open+Sans" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/base.css"/>
    <title>BestHotel.com</title>
</head>
<body>

<header class="header">
    <a class="logo"
       href="controller?command=${role.value == 'administrator' ? 'management' : 'showApplicationRegistration'}">BestHotel.com</a>
    <ul class="main-menu">
        <li><a class="hidden"><fmt:message key="header.menu" bundle="${lang}"/></a>
            <ul class="sub-menu">
                <li><span class="phone">+375 (44) 11 22 33</span></li>
                <li>
                    <c:if test="${role.value == 'client'}">
                    <a class="link" href="controller?command=profile"><fmt:message key="header.profile"
                                                                                   bundle="${lang}"/></a>
                    </c:if>
                    <c:if test="${user == null}">
                    <a class="link" href="controller?command=showLogin"><fmt:message key="header.login"
                                                                                     bundle="${lang}"/></a>
                    </c:if>


                    <c:if test="${user != null}">
                <li><a class="link" href="controller?command=exit"><fmt:message key="header.exit" bundle="${lang}"/></a>
                    </c:if>

                <li><a class="link" href="${langRedirect}">
                <fmt:message key="header.language" bundle="${lang}"/></a>

            </ul>

    </ul>
</header>

</body>
</html>
