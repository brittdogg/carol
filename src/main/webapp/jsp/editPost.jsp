<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                <!-- this link goes to the home page for this resource -->
                <a href="${pageContext.request.contextPath}/blog"/><< Go Back</a>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <sf:form role="form"
                                 class="form-horizontal"
                                 modelAttribute="blogPost"
                                 action="${pageContext.request.contextPath}/admin/posts/editPost/${blogPost.blogPostId}"
                                 method="POST">
                            <div class="form-inline form-group">
                                <label for="publishDate">
                                    Date Posted:
                                </label>
                                <sf:input type="text"
                                          class="form-control"
                                          id="publishDate"
                                          path="publishDate"
                                          readonly="true"
                                          value="${post.createdDate}"/>
                                <label for="authorName">
                                    Posted By:
                                </label>
                                <sf:input type="text"
                                          class="form-control"
                                          id="authorName"
                                          path="createdBy.displayName"
                                          readonly="true"/>
                            </div>
                            <div class="form-group">
                                <label for="postTitle">
                                    Title
                                </label>
                                <sf:input type="text"
                                          class="form-control" 
                                          id="postTitle"
                                          path="title"
                                          placeholder="Title"/>
                                <sf:errors path="title"
                                           cssClass="btn btn-danger"/>
                            </div>
                            <div class="form-group">
                                <label for="postContent">
                                    Content
                                </label>
                                <sf:textarea type="text"
                                             class="form-control" 
                                             id="postContent"
                                             path="content"
                                             cols="40"
                                             rows="10"
                                             placeholder="Enter the text of your post here..."/>
                                <sf:errors path="content"
                                           cssClass="btn btn-danger"/>
                            </div>
                            <!--        <div class="form-group">
                                        <label for="publishDate">
                                            Date to Publish
                                        </label>
                            <sf:input type="datetime-local"
                                      class="form-control" 
                                      id="publishDate"
                                      path="publishDate"
                                      placeholder=""/>
                            <sf:errors path="publishDate"
                                       cssClass="btn btn-danger"/>
                        </div>
                        <div class="form-group">
                            <label for="expirationDate">
                                Expiration Date
                            </label>
                            <sf:input type="date"
                                      class="form-control" 
                                      id="expirationDate"
                                      path="expirationDate"
                                      placeholder=""/>
                            <sf:errors path="expirationDate"
                                       cssClass="btn btn-danger"/>
                        </div>-->
                            <div class="form-group">
                                <label for="category">
                                    Category
                                </label>
                                <sf:select id="category" path="category.categoryId" class="form-control">
                                    <c:forEach var="category" items="${categories}">
                                        <sf:option value="${category.categoryId}">${category.categoryName}</sf:option>
                                    </c:forEach>
                                </sf:select>

                            </div>
                            <div class="form-group">
                                <label for="urlAlias">
                                    Custom URL
                                </label>
                                <sf:input type="text"
                                          class="form-control" 
                                          id="urlAlias"
                                          path="urlAlias"
                                          placeholder=""/>
                                <sf:errors path="urlAlias"
                                           cssClass="btn btn-danger"/>
                            </div>
                            <sf:hidden path="blogPostId"/>
                            <sf:hidden path="createdBy.blogUserId"/>
                            <sf:hidden path="modifiedBy.blogUserId"/>
                            <sf:hidden path="createdDate"/>
                            <sf:hidden path="expirationDate"/>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">
                                    Update Post
                                </button>
                            </div>
                        </sf:form>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>