<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h1>Credit card</h1>

    <h2>Enter your credit card data to finish your order</h2>

    <form action="<c:url value='/order/submitOrder'/> " method="post">
        <table>
            <tr>
                <td><b>Credit Card Type: </b></td>
                <td>
                    <select name="creditCardType">
                        <c:forEach var="type" items="${creditCardTypes}">
                            <option name="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><b>Credit Card Number: </b></td>
                <td>
                    <input type="text" size="18" maxlength="30" required>
                </td>
            </tr>
            <tr>
                <td><b>Expiration Date: </b></td>
                <td>
                    <select name="expirationDate">
                        <c:forEach var="expirYear" items="${creditCardExpirationDates}">
                            <option name="${expirYear}">${expirYear}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>
