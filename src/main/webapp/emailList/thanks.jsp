<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/home/columnLeftHome.jsp"/>

<section>
    <p>Thanks for subscribing for our news!</p>
    <p>Here is the intel that you entered:</p>

    <label>Email:</label>
    <span>${user.email}</span><br>

    <label>First Name:</label>
    <span>${user.firstName}</span><br>

    <label>Last Name:</label>
    <span>${user.lastName}</span><br>
</section>

<jsp:include page="/includes/home/columnRightHome.jsp"/>
<jsp:include page="/includes/footer.jsp"/>
