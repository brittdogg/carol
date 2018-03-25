<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carol's Blog - Page Title</title>

        <!-- Sitewide styles -->
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <h1>Welcome to Carol's Blog</h1>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <h3>Posts by User : ${user.displayName}</h3>
                        <c:forEach items="${posts}" var="post">
                            <div class="row">
                                <h3><a href="${pageContext.request.contextPath}/blog/showPost/${post.blogPostId}">${post.title}</a></h3>
                                <h4>${post.publishDate}</h4>
                                <h4><a href="${pageContext.request.contextPath}/blog/user/${post.createdBy.blogUserId}">
                                    ${post.createdBy.displayName}
                                    </a></h4>
                                <h4><a href="${pageContext.request.contextPath}/blog/category/${post.category.categoryId}">
                                        ${post.category.categoryName}
                                    </a></h4>
                                <div class="panel panel-default">
                                    ${post.content}
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-4">
                        <!-- Any Controller method returning this view MUST
                        include the list of static pages (i.e. pageService.
                        getAllPages) in the Model object. -->
                        <jsp:include page="/WEB-INF/template/staticPageList.jsp"/>
                    </div>
                </div>
            </div>
            <!-- Sitewide JS links -->
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>