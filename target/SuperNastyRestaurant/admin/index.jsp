<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/admin/columnLeftAdmin.jsp" />

<section id="admin">

    <h1>Admin Menu</h1>

    <section>
        <form action="<c:url value='sql/index.jsp' /> ">
            <input type="submit" value="SqlGateway"/>
        </form>

        <form action="<c:url value='/adminController/viewInvoices'/> " method="post">
            <input type="submit" value="View Invoices">
        </form>
    </section>

</section>

<jsp:include page="/includes/footer.jsp" />