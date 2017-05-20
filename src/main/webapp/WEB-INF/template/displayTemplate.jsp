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
                        <h2><c:out value="${slug}"/></h2>
                        <h2><c:out value="${page.title}"/></h2>
                        <p><c:out value="${page.content}"/></p>
                        <p>Author: <a href="/CarolsBlog/blog/user/${page.createdBy.blogUserId}">
                                <c:out value="${page.createdBy.displayName}"/>
                            </a></p>
                        <p>Updated: <c:out value="${page.modifiedDate}"/></p>
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