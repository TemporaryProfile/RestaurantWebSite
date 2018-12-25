<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h2>Unsubscribe</h2>
    <c:if test = "${message != null}">
        <c:out value="${message}"/>
    </c:if>

    <p>Enter email address you used to subscribe in the form below</p>
    <form action="<c:url value='/userController/unsubscribe'/> " method="post">
        <label>Email:</label>
        <input type="email" name="email" required><br>
        <input type="submit" value="Unsubscribe"><br>
    </form>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>