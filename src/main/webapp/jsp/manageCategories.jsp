<%-- 
    Document   : category
    Created on : Apr 7, 2017, 11:17:41 PM
    Author     : stephendowning
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <h1>Categories</h1>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <div class="row">
                <div class="col-md-6">
                    <h2>List of Categories</h2>
                    <table id="userTable" class="table table-hover">
                        <tr>
                            <th width="50%">Category</th>                           
                            <th width="25%"></th>
                            <th width="25%"></th>
                        </tr>
                        <c:forEach var="currentCategory" items="${categoryList}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/blog/category/${currentCategory.categoryId}">
                                    <c:out value ="${currentCategory.categoryName}"/>
                                    </a>
                                </td>                               
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/category/editCategory?categoryId=${currentCategory.categoryId}">
                                    Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/category/deleteCategory?categoryId=${currentCategory.categoryId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>

                        </c:forEach> 
                    </table>
                </div>
                <!--Add Category Form-->
                <div class="col-md-6">
                    <h2>Add Category</h2>
                    <form class="form-horizontal" role="form" method="POST"
                          action="/CarolsBlog/admin/category/addCategory">
                        <div class="form-group">
                            <label for="add-category" class="col-md-4 control-label">
                                Category:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="categoryName"
                                       placeholder = "Category"/>                                
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default"
                                       value="Create Category"/>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>   
        <jsp:include page="/WEB-INF/template/scripts.jsp"/>
    </body>
</html>
