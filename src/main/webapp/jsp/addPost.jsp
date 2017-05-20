<%-- 
    Document   : newPost
    Created on : Apr 7, 2017, 2:22:07 PM
    Author     : bdogg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carol's Blog</title>
        <jsp:include page="/WEB-INF/template/css.jsp"/>
        
    </head>
    <body>
        <div class="container">
            <div id="title" class="row">
                <h1>Add a New Post</h1>
            </div>

            <!-- include navigation bar template -->
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>

            <div id="addForm" class="row">
                <div class="col-md-8">
                    <sf:form role="form"
                             class="form-horizontal"
                             modelAttribute="blogPost"
                             action="${pageContext.request.contextPath}/admin/posts/addPost"
                             method="POST">
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
                            <sf:textarea type="textarea"
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
                        <sf:hidden path="blogPostId" value="${post.blogPostId}"/>
                        <sf:hidden path="createdBy.blogUserId" value="1"/>
                        <sf:hidden path="modifiedBy.blogUserId" value="1"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">
                                <strong>Post</strong>
                            </button>
                        </div>
                    </sf:form>
                </div>
                <div class="col-md-4">
                    <!-- include static page list sidebar -->
                    <jsp:include page="/WEB-INF/template/staticPageList.jsp"/>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>
