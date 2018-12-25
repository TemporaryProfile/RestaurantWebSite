<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/admin/columnLeftAdmin.jsp" />

<c:if test="${sqlStatement == null}">
    <c:set var="sqlStatement" value="select * from User" />
</c:if>
    
    <section>
        <h1>The SQL Gateway</h1>
        <p>Enter an SQL statement and click the Execute button.</p>

        <p><b>SQL statement:</b></p>
        <form action="<c:url value='/sqlGateway'/> " method="post">
            <textarea name="sqlStatement" cols="60" rows="8">${sqlStatement}</textarea>
            <input type="submit" value="Execute">
        </form>

        <p><b>SQL result:</b></p>
        ${sqlResult}
    </section>

<c:import url="/includes/footer.jsp" />