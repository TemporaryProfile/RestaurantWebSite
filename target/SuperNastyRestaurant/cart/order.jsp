<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h1>Your order info</h1>

    <p>Your personal detail:</p>

    <table>
        <tr>
            <td><b>First Name</b></td>
            <td><b>Last Name</b></td>
            <td><b>Email</b></td>
        </tr>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
        </tr>
    </table><br>

    <p><b>Your order:</b></p><br>
    <table>
        <tr>
            <td><b>Quantity</b></td>
            <td><b>Name</b></td>
            <td><b>Price</b></td>
            <td><b>Total: ${totalPrice}</b></td>
        </tr>
        <c:forEach var="cartItem" items="${order.getCart()}">
            <tr>
                <td>${cartItem.getQuantity().toString()}</td>
                <td>${cartItem.getDish().getName()}</td>
                <td>${cartItem.getPrice()}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="<c:url value='/cart/creditCard.jsp'/> ">
        <input type="submit" name="continue" value="Continue">
    </form>

    <form action="<c:url value='/cart/checkIn.jsp'/> ">
        <input type="submit" name="editInfo" value="Edit Intel">
    </form>
    <%--<form action="<c:url value='/order/submitOrder'/> " method="post">--%>
        <%--<input type="submit" name="Order" value="Submit Order">--%>
    <%--</form>--%>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>
