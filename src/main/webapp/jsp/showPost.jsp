<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                        <p>${post.publishDate}</p>
                        <h1>${post.title}</h1>
                        <p>Category: <a href="/CarolsBlog/blog/category/${post.category.categoryId}">
                                ${post.category.categoryName}
                            </a></p>
                        <p>Author: <a href="/CarolsBlog/blog/user/${post.createdBy.blogUserId}">
                                ${post.createdBy.displayName}
                            </a></p>
                        <div>
                            ${post.content}
                        </div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <form action="${pageContext.request.contextPath}/admin/posts/deletePost/${post.blogPostId}" method="POST">
                                <button action="submit" class="btn btn-default">Delete Post</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/posts/editPost/${post.blogPostId}" method="GET">
                                <button action="submit" class="btn btn-default">Edit Post</button>
                            </form>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <div>
                                <h2>Comments</h2>
                                <table id="commentsTable" class="table table-hover">
                                    <tr>
                                        <th width="60%">Comment</th>
                                        <th width="15%">Created By</th>
                                        <th width="15%">Date Created</th>
                                        <th width="10%"></th>
                                    </tr>
                                    <c:forEach var="currentComment" items="${postComments}">
                                        <tr>
                                            <td>
                                                <c:out value="${currentComment.content}"/> 
                                            </td>               
                                            <td>
                                                <c:out value="${currentComment.createdBy.displayName}"/>
                                            </td>
                                            <td>
                                                <c:out value="${currentComment.createdDate}"/>
                                            </td>
                                            <td>
                                                <a href="/CarolsBlog/blog/deleteComment?commentId=${currentComment.commentId}&postId=${currentComment.blogPostId}">
                                                    Delete
                                                </a>
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </table>

                            </div>
                            <div>
                                <h4>Add Comment</h4>
                                <form class="form-horizontal" role="form" method="POST"
                                      action="/CarolsBlog/blog/addComment">
                                    <div class="form-group">
                                        <label for="add-comment" class="control-label">
                                            Comment:
                                        </label>
                                        <div>
                                            <input type="text" class="form-control" name="content"
                                                   placeholder = "Comment"/>
                                            <input type="hidden" value="${post.blogPostId}" name="postId"/>
                                            <input type="hidden" value="1" name="createdBy.blogUserId"/>
                                            <input type="hidden" value="1" name="modifiedBy.blogUserId"/>


                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div>
                                            <input type="submit" class="btn btn-default"
                                                   value="Create Comment"/>

                                        </div>
                                    </div>

                                </form>
                            </div>
                        </sec:authorize>
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