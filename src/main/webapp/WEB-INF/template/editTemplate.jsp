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
                <a href="${pageContext.request.contextPath}/"/><< Go Back</a>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <sf:form role="form"
                                 class="form-horizontal"
                                 modelAttribute="yourModelAttribute"
                                 action="${pageContext.request.contextPath}/your/url"
                                 method="POST">
                        <!-- Your spring form here -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">
                                Submit
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