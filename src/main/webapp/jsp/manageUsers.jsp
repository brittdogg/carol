<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <h1>User Management</h1>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <div class="row">
                <div class="col-md-6">
                    <h2>Active Users</h2>
                    <table id="userTable" class="table table-hover">
                        <tr>
                            <th width="25%">Display Name</th>
                            <th width="25%">Login Name</th>
                            <!--<th width="25%">Role</th>-->
                            <th width="10%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentBlogUser" items="${blogUserList}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/blog/user/${currentBlogUser.blogUserId}">
                                        <c:out value ="${currentBlogUser.displayName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value ="${currentBlogUser.loginName}"/>
                                </td>

                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/user/editUser?blogUserId=${currentBlogUser.blogUserId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/user/deleteUser?blogUserId=${currentBlogUser.blogUserId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>

                        </c:forEach> 
                    </table>
                </div>
                <!--Add User Form-->
                <div class="col-md-6">
                    <h2>Add Blog User</h2>
                    <form class="form-horizontal" role="form" method="POST"
                          action="${pageContext.request.contextPath}/admin/user/addUser">
                        <div class="form-group">
                            <label for="add-displayName" class="col-md-4 control-label">
                                Display Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="displayName"
                                       placeholder = "Display Name"/>                                
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-loginName" class="col-md-4 control-label">
                                Login Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="loginName"
                                       placeholder = "Login Name"/>                                
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="add-password" class="col-md-4 control-label">
                                Password:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="password"
                                       placeholder = "Password"/>                                
                            </div>
                        </div>

<!--                        <div class="form-group">
                            <label for="add-roles" class="col-md-4 control-label">
                                Role:
                            </label>
                            <div class="col-md-8">
                                <input type="select" multiple="true" class="form-control" name="roles"/>
                            </div>
                        </div>-->

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default"
                                       value="Create User"/>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>   
        <jsp:include page="/WEB-INF/template/scripts.jsp"/>
    </body>
</html>
