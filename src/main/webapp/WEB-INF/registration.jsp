<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=showRegistration&language=${locale}" var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/base.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/login.css"/>
    <title>BestHotel.com</title>
</head>
<body>
<main class="content">

    <form class="login-container" method="post" action="controller">
        <div>
            <label class="label-color"><fmt:message key="login.username" bundle="${lang}"/>:</label>
            <br>
            <input type="text" autocomplete="off" autofocus="autofocus" required="required"
                   title="<fmt:message key="login.userNameTitle" bundle="${lang}"/>"
                   name="username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$">
        </div>
        <div class="input-container">
            <label class="label-color"><fmt:message key="login.password" bundle="${lang}"/>:</label>
            <br>
            <input type="password" required="required"
                   title="<fmt:message key="registration.passwordTitle" bundle="${lang}"/>"
                   name="password"
                   pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$">
        </div>

        <div class="input-container">
            <label class="label-color"><fmt:message key="profile.name" bundle="${lang}"/>:</label>
            <br>
            <input type="text" required="required" name="name">
        </div>

        <div class="input-container">
            <label class="label-color"><fmt:message key="profile.lastName" bundle="${lang}"/>:</label>
            <br>
            <input type="text" required="required" name="last_name">
        </div>

        <div class="input-container">
            <label class="label-color"><fmt:message key="profile.phoneNumber" bundle="${lang}"/>:</label>
            <br>
            <input type="text" name="phone_number">
        </div>

        <div class="input-container">
            <label class="label-color"><fmt:message key="profile.email" bundle="${lang}"/>:</label>
            <br>
            <input type="text" name="email">
        </div>

        <c:if test="${param.get('registrationError') != null}">
            <span class="red_text"><fmt:message key="${param.get('registrationError')}" bundle="${lang}"/></span>
        </c:if>

        <input type="hidden" required="required" value="registration" name="command">
        <div>
            <button class="login-button" type="submit"><fmt:message key="login.signUp" bundle="${lang}"/></button>
        </div>
    </form>
</main>

</body>
</html>
