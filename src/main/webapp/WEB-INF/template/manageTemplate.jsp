<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <h1>Welcome to Carol's Blog</h1>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <table width="80%">
                            <thead>
                            <th width="70%">
                                Resource Name:
                            </th>
                            <th width="15%">Edit</th>
                            <th width="15%">Delete</th>
                            </thead>
                            <tbody>
                                <!-- replace post, posts, blogPostId, etc.
                                with the collection you are managing. -->
                                <c:forEach var="post" items="${posts}">
                                <tr>
                                    <td>
                                        <a href="/CarolsBlog/blog/displayPost/<c:out value="${post.blogPostId}"/>"><c:out value="${post.title}"/></a>
                                    </td>
                                    <td>
                                        <a href="/CarolsBlog/blog/editPost/<c:out value="${post.blogPostId}"/>">Edit</a>
                                    </td>
                                    <td>
                                        <a href="/CarolsBlog/blog/deletePost/<c:out value="${post.blogPostId}"/>">Delete</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>