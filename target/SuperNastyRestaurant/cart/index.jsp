<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h2>Your Cart</h2>

    <section>
        <c:choose>
            <c:when test="${emptyCart != null}">
                <p>Your cart is empty. Please check our menu to make an order.</p>

                <%--<form action="<c:url value='/menu/index.jsp'/>">--%>
                    <%--<input type="submit" name="menu" value="Check menu">--%>
                <%--</form>--%>
            </c:when>
            <c:otherwise>
                <p><b>Cart:</b><br>${cart.toString()}</p>
                <section>
                    <form action="<c:url value='/order/checkout'/> " method="post">
                        <input type="submit" name="checkout" value="Checkout">
                    </form>
                </section>
                <%--<form action="<c:url value='/menu/index.jsp'/> ">--%>
                    <%--<input type="submit" name="menu" value="Check menu">--%>
                <%--</form>--%>
            </c:otherwise>
        </c:choose>

        <section>
            <form action="<c:url value='/menu/index.jsp'/>">
                <input type="submit" name="menu" value="Check menu">
            </form>
        </section>
    </section>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>
