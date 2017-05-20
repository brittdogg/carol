<%-- 
    Document   : editCategoryForm
    Created on : Apr 8, 2017, 11:38:57 AM
    Author     : stephendowning
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Carol's Blog</title>
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <h2>Edit Category</h2>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/user/displayUserManagementPage">
                            User Controller</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/category/displayCategoryPage">
                            Categories</a></li>
                </ul>    
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="category"
                     action="editCategory" method="POST">
                <div class="form-group">
                    <label for="add-category" class="col-md-4 control-label">
                        Category
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="categoryName"
                                  id="add-categoryName" placeholder = "Category"/>
                        <sf:errors path="categoryName" cssClass="error">                                    
                        </sf:errors>
                         <sf:hidden path="categoryId"/>
                    </div>
                </div>

                
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Category"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <jsp:include page="/WEB-INF/template/scripts.jsp"/>
    </body>
</html>
