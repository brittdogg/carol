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
            <h1>Log In</h1>
            <hr/>
            <jsp:include page="/WEB-INF/template/navBar.jsp"/>
            <div class="container">
                <!-- this link goes to the home page for this resource -->
                <a href="/CarolsBlog/"/><< Go Back</a>
                <div class="row">
                    <c:if test="${param.login_error == 1}">
                            <p class="text-center">Wrong username or password. Please try again.</p>
                        </c:if>
                    <div class="col-md-8 col-md-offset-2">
                        <form class="form-horizontal" 
                              role="form" 
                              method="post" 
                              action="j_spring_security_check">
                            <div class="form-group">
                                <label for="j_username" class="col-md-4 control-label">Username:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="j_username" placeholder="Username"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="j_password" class="col-md-4 control-label">Password:</label>
                                <div class="col-md-8">
                                    <input type="password" class="form-control" name="j_password" placeholder="Password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" id="search-button" value="Sign In"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>