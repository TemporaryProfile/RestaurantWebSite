<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebarLeft">
    <nav>
        <ul>
            <li>
                <a href="<c:url value='/'/> ">
                    Home</a>
            </li>
            <li>
                <a href="<c:url value='/emailList/' /> ">
                    Join Email List</a>
            </li>
            <li>
                <a href="<c:url value='/menu/' />">
                   Show Menu</a>
            </li>
            <li><a href="<c:url value='/userController/deleteCookies'/> ">
                Clear History
            </a></li>
            <li>
                Contact Us
            </li>
        </ul>
    </nav>
</aside>
