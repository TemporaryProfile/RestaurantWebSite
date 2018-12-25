<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>SuperNasty Restaurant</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css'/> ">
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>
<body>
    <header>
        <h1>SuperNasty Restaurant</h1>
        <h2>Fresh and Healthy Food Served Fast!</h2>
    </header>

    <nav id="header_nav_bar">
        <ul>
            <li><a href="<c:url value='/admin/'/>">
                Admin</a>
            </li>
            <li><a href="<c:url value='/order/showCart'/> ">
                Cart</a>
            </li>
        </ul>
    </nav>
</body>
</html>
