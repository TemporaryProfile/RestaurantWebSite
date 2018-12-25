<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:import url="/includes/header.jsp"/>
    <c:import url="/includes/home/columnLeftHome.jsp"/>

    <section>
    <h2>SuperNasty Restaurant Menu</h2><br>
        <table>
            <tr>
                <td><b>Name</b></td>
                <td><b>Calories</b></td>
                <td><b>Type</b></td>
                <td><b>Is Vegeterian</b></td>
                <td><b>Price</b></td>
            </tr>
            <c:forEach var="menuItem" items="${menu.getMenu()}">
                <tr>
                    <td><a href="products/${menuItem.name}.jsp">${menuItem.name}</a> </td>
                    <td>${menuItem.calories}</td>
                    <td>${menuItem.type.toString()}</td>
                    <td>${menuItem.isVegeterianString()}</td>
                    <td>${menuItem.price.toString()}</td>
                </tr>
            </c:forEach>
        </table>
    </section>

    <c:import url="/includes/home/columnRightHome.jsp"/>
    <c:import url="/includes/footer.jsp"/>