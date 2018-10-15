<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="d" uri="dateTag" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=error&language=${locale}" var="langRedirect" scope="request"/>
<jsp:include page="maket.jsp"/>


<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/home.css"/>
    <title>BestHotel.com</title>
</head>
<body>
<main class="content">
    <div class="application main-container">
        <span><fmt:message key="error.error" bundle="${lang}"/></span>
        <br>
        <span><fmt:message key="error.apologize" bundle="${lang}"/></span>
    </div>
</main>
</body>
</html>
