<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h2>Enter your info</h2>

    <form action="<c:url value='/order/processUser'/> " method="post">
        <label>First Name:</label>
        <input type="text" name="firstName" value="${user.firstName}" required><br>

        <label>Last Name:</label>
        <input type="text" name="lastName" value="${user.lastName}" required><br>

        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" required><br>

        <input type="submit" value="Continue">
    </form>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>
