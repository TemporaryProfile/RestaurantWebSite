<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <c:set var="menuItem" value="${menu.getByName('Fried Chicken').get()}"/>
    <h2>${menuItem.getName()}</h2>
    <table>
        <tr>
            <td><b>Calories</b></td>
            <td><b>Type</b></td>
            <td><b>Is Vegeterian</b></td>
            <td><b>Price</b></td>
        </tr>
        <tr>
            <td>${menuItem.getCalories()}</td>
            <td>${menuItem.getType()}</td>
            <td>${menuItem.isVegeterianString()}</td>
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
