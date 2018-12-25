<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/home/columnLeftHome.jsp" />

<section>
    <h1>Login Form - Error</h1>
    <p>You did not log in successfully.</p>
    <p>Please check username and password and try again.</p>

    <form action="j_security_check" method="get">
        <label>Username</label>
        <input type="text" name="j_username"><br>
        <label>Password</label>
        <input type="password" name="j_password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Login">
    </form>
</section>

<jsp:include page="/includes/home/columnRightHome.jsp" />
<jsp:include page="/includes/footer.jsp" />