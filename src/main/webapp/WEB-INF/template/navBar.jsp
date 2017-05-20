<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/blog">
                Home</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/posts">
                    Manage Posts</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/static">
                    Manage Pages</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/comment">
                    Manage Comments</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/user">
                    Blog Users</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/category">
                    Categories</a></li>
                </sec:authorize>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                <li role="presentation"><a href="<c:url value="/j_spring_security_logout" />" >
                        Logout</a>
                    </c:when>
                    <c:otherwise>
                <li role="presentation"><a href="${pageContext.request.contextPath}/login">
                        Log In</a></li>
                    </c:otherwise>
                </c:choose>
    </ul>
</div>
