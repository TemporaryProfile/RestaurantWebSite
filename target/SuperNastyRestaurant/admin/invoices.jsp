<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/home/columnLeftHome.jsp"/>

<section>
    <h1>Invoices to process.</h1>

    <c:choose>
        <c:when test="${unprocessedInvoices != null}">
            <p>No invoices are present.</p>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</section>

<c:import url="/includes/home/columnRightHome.jsp"/>
<c:import url="/includes/footer.jsp"/>

