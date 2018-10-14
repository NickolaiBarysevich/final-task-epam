<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="d" uri="dateTag" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=showHome&language=${locale}" var="langRedirect" scope="request"/>
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
    <div class="main-container">
        <label class="slogan"><fmt:message key="home.choose" bundle="${lang}"/>
            <br><fmt:message key="home.rest" bundle="${lang}"/>
        </label>
        <c:if test="${param.get('someError') != null}">
            <p class="red_text error-block"><fmt:message key="${param.get('someError')}" bundle="${lang}"/></p>
        </c:if>

        <form class="application" action="controller" method="post">
            <div class="input_block">
                <label class="label"><fmt:message key="home.guests" bundle="${lang}"/></label>
                <br>
                <select class="guest-field" name="places">
                    <option value="1">1</option>
                    <option selected value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>

            </div>
            <div class="input_block">
                <label class="label"><fmt:message key="home.checkIn" bundle="${lang}"/></label>
                <br>
                <input id="date" class="input-form" type="date" min="<d:date/>"
                       value="<d:date/>" required name="check_in_date">
            </div>
            <div class="input_block">
                <label class="label"><fmt:message key="home.checkOut" bundle="${lang}"/></label>
                <br>
                <input class="input-form" min="<d:date daysToAdd="1"/>" value="<d:date daysToAdd="7"/>" type="date"
                       required name="check_out_date">
            </div>
            <div class="input_block">
                <label class="label"><fmt:message key="home.roomClass" bundle="${lang}"/></label>
                <br>
                <select class="input-form" name="room_class">
                    <option value="1"><fmt:message key="home.standard" bundle="${lang}"/></option>
                    <option value="2"><fmt:message key="home.comfort" bundle="${lang}"/></option>
                    <option value="3"><fmt:message key="home.comfort+" bundle="${lang}"/></option>
                </select>
            </div>
            <input type="hidden" name="command" value="registerApplication">
            <button class="sub-button"><fmt:message key="home.subButton" bundle="${lang}"/></button>
        </form>
    </div>
</main>
</body>
</html>
