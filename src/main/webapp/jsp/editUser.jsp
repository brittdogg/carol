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
        <!-- Bootstrap core CSS -->
        <jsp:include page="/WEB-INF/template/css.jsp"/>
    </head>
    <body>
        <div class="container">
            <h2>Edit Blog User</h2>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <a href="${pageContext.request.contextPath}/blog"/><< Go Back</a>
            <sf:form class="form-horizontal" role="form" modelAttribute="blogUser"
                     action="editUser" method="POST">
                <div class="form-group">
                    <label for="add-displayName" class="col-md-4 control-label">
                        Display Name
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="displayName"
                                  id="add-displayName" placeholder = "Display Name"/>
                        <sf:errors path="displayName" cssClass="error">                                    
                        </sf:errors>                                
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-loginName" class="col-md-4 control-label">
                        Login Name
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="loginName"
                                  id="add-loginName" placeholder = "Login Name"/>
                        <sf:errors path="loginName" cssClass="error">                                    
                        </sf:errors>                                
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-password" class="col-md-4 control-label">
                        Password
                    </label>
                    <div class="col-md-8">
                        <sf:input type="password" class="form-control" path="password"
                                  id="add-password" placeholder = "password"/>
                        <sf:errors path="password" cssClass="error">
                        </sf:errors>                                
                    </div>
                </div>
                <sf:hidden path="blogUserId"/>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Blog User"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <jsp:include page="/WEB-INF/template/scripts.jsp"/>
    </body>
</html>
