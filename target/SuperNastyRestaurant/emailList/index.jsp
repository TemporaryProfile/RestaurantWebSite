<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/home/columnLeftHome.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <h1>Subscribe to our notifications!</h1>
    <h2>Remain in touch with us to receive Nasty Bonuses and notifications!</h2>

    <p><i>${message}</i></p>

    <form action="<c:url value='/userController/subscribe' />" method="post">
        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" required><br>
        <label>First Name:</label>
        <input type="text" name="firstName" value="${user.firstName}" required><br>
        <label>Last Name:</label>
        <input type="text" name="lastName" value="${user.lastName}" required><br>

        <input type="submit" value = "Join Now!" id="submit">
    </form><br><br>


    <h><p>If you are already subscribed to our news, but no longer
        interested, please unsubscribe: <b>press the button below.</b></p></h>

    <form action="<c:url value='/emailList/unsubscribe.jsp' />" method="post">
        <input type="submit" name="unsubscribe" Value="Unsubscribe">
    </form>
</section>

<jsp:include page="/includes/home/columnRightHome.jsp"/>
<jsp:include page="/includes/footer.jsp"/>

