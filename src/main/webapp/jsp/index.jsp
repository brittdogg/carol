<%-- 
    Document   : home
    Created on : Apr 7, 2017, 10:22:12 AM
    Author     : stephendowning
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carol's Blog Home</title>
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <div id="title" class="row">
                <h1>Carol's Blog</h1>
            </div>

            <!-- include navigation bar template -->
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>

            <div class="col-md-8">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="row">
                    <a href="admin/posts/addPost"><button role="button" class="btn btn-default">Add Post</button></a>
                    <a href="admin/static/add"><button role="button" class="btn btn-default">Add Page</button></a>
                </div>
                </sec:authorize>
                <c:forEach items="${blogPosts}" var="post">
                    <div class="row">
                        <h3><a href="${pageContext.request.contextPath}/blog/showPost/${post.blogPostId}">${post.title}</a></h3>
                        <h4>${post.publishDate}</h4>
                        <h4><a href="${pageContext.request.contextPath}/blog/user/${post.createdBy.blogUserId}">
                                ${post.createdBy.displayName}
                            </a></h4>
                        <h4><a href="${pageContext.request.contextPath}/blog/category/${post.category.categoryId}">
                                <c:out value="${post.category.categoryName}"/>
                            </a></h4>
                        <div class="panel panel-default">
                            ${post.content}
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="col-md-4">
                <jsp:include page="/WEB-INF/template/staticPageList.jsp"/>
            </div>
        </div>
        <jsp:include page="/WEB-INF/template/scripts.jsp"/>
    </body>
</html>