<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text" var="lang"/>
<fmt:message key="header.locale" bundle="${lang}" var="locale"/>
<c:url value="controller?command=showInfo&language=${locale}&application_id=${application.id}" var="langRedirect"
       scope="request"/>
<jsp:include page="maket.jsp"/>

<html lang="${language}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/base.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="../resources/css/profile.css"/>
    <title>BestHotel.com</title>
</head>
<body>
<main class="content">
    <div class="profile-content">
        <div class="info-container">


            <p class="to-right"><fmt:message key="profile.currentBalance" bundle="${lang}"/> <fmt:formatNumber
                    value="${user.wallet}"
                    currencyCode="USD"
                    type="currency"/></p>

            <h2><fmt:message key="applicationInfo.applicationInfo" bundle="${lang}"/>:</h2>
            <p><fmt:message key="profile.applicationNumber" bundle="${lang}"/>: ${application.id}</p>
            <p><fmt:message key="applicationInfo.status" bundle="${lang}"/>: <fmt:message
                    key="management.${application.status.value}" bundle="${lang}"/></p>
            <p><fmt:message key="home.checkIn" bundle="${lang}"/>: <fmt:formatDate value="${application.checkIn}"/></p>
            <p><fmt:message key="home.checkOut" bundle="${lang}"/>: <fmt:formatDate
                    value="${application.checkOut}"/></p>
            <p><fmt:message key="home.guests" bundle="${lang}"/>: ${application.places}</p>
            <p><fmt:message key="home.roomClass" bundle="${lang}"/>: <fmt:message key="roomClass.${application.roomClassId}" bundle="${lang}"/></p>
            <p><fmt:message key="applicationInfo.roomNumber" bundle="${lang}"/>: ${application.roomId}</p>
            <p><fmt:message key="applicationInfo.billNumber" bundle="${lang}"/>: ${application.billId}</p>
            <p><fmt:message key="applicationInfo.cost" bundle="${lang}"/>: <fmt:formatNumber value="${application.cost}"
                                                                                             currencyCode="USD"
                                                                                             type="currency"/></p>


            <div>
                <button onclick="history.back()" class="profile-link"><fmt:message key="profile.back"
                                                                                       bundle="${lang}"/></button>
                <c:if test="${application.status == 'CONSIDERING' or application.status == 'APPROVED'}">

                    <c:if test="${application.status == 'APPROVED'}">
                        <form class="to-right" action="controller" method="post">
                            <input type="hidden" name="command" value="pay">
                            <input type="hidden" name="application_id" value="${application.id}">
                            <button class="pay-button"><fmt:message key="profile.pay" bundle="${lang}"/></button>
                        </form>
                    </c:if>
                    <button class="to-right profile-link" onclick="cancel()" name="cancel"><fmt:message key="profile.cancel"
                                                                                               bundle="${lang}"/></button>
                </c:if>
            </div>


        </div>
    </div>
    <form class="confirm-block" id="cancel-form" action="controller" method="post">
        <p><fmt:message key="profile.confirmCancelMessage" bundle="${lang}"/></p>
        <input type="hidden" name="command"
               value="${application.status == 'CONSIDERING' ? 'cancelConsidering' : 'cancelApproved'}">
        <input type="hidden" name="application_id" value="${application.id}">
        <button class="sub-button"><fmt:message key="profile.yes" bundle="${lang}"/></button>
        <a class="sub-button" onclick="hide()"><fmt:message key="profile.no" bundle="${lang}"/></a>
    </form>
</main>
<script>
    function hide() {
        document.getElementById('cancel-form').style.display = 'none';
    }

    function cancel() {
        document.getElementById('cancel-form').style.display = 'block';
    }
</script>
</body>
</html>