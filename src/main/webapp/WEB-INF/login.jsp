<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=showLogin&language=${locale}" var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>

<html lang="${language}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            <input type="password" title="<fmt:message key="login.passwordTitle" bundle="${lang}"/>" name="password"
                   pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$">
        </div>
        <c:if test="${loginError != null}">
            <p class="invalid-pass"><fmt:message key="${loginError}" bundle="${lang}"/></p>
        </c:if>
        <input type="hidden" required="required" value="login" name="command">
        <div>
            <button class="login-button" type="submit"><fmt:message key="login.signIn" bundle="${lang}"/></button>

            <a class="sub-button" href="controller?command=showRegistration"><fmt:message key="login.signUp"
                                                                                            bundle="${lang}"/></a>
        </div>
    </form>
</main>


</body>
</html>
