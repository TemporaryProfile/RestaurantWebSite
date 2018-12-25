<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <p><b>Boiled Shark</b>, caught specially for you!</p>
    <p>Discover the power of the Boiled Shark's taste!</p>
</section>

<section>
    <c:set var="menuItem" value="${menu.getByName('Boiled Shark').get()}"/>
    <h2>${menuItem.getName()}</h2><br>
    <table>
        <tr>
            <td><b>Calories</b></td>
            <td><b>Type</b></td>
            <td><b>Is Vegeterian</b></td>
            <td><b>Price</b></td>
        </tr>
        <tr>
            <td>${menuItem.calories}</td>
            <td>${menuItem.type.toString()}</td>
            <td>${menuItem.isVegeterianString()}</td>
            <td>${menuItem.price.toString()}</td>
        </tr>
    </table>
    <section>
        <form action="<c:url value='/order/addMenuItem'/> " method="post">
            <label>Quantity:</label>
            <select name="quantity">
                <c:forEach var="amount" items="${maxUniqueItemsInPair}">
                    <option value="${amount}">${amount}</option>
                </c:forEach>
            </select><br>
            <input type="hidden" name="menuItemName" value="${menuItem.getName()}">
            <input type="submit" value="Order">
        </form>
    </section>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>