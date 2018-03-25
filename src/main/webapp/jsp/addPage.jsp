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
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <!-- HTML Form (wrapped in a .bootstrap-iso div) -->
                        <sf:form action="" method="post" modelAttribute="staticPage">
                            <div class="form-group ">
                                <label class="control-label requiredField" for="title">
                                    Title
                                    <span class="asteriskField"> *</span>
                                </label>
                                <sf:input class="form-control" id="title" name="title" placeholder="Enter Page Title" type="text" path="title"/>
                            </div>
                            <div class="form-group ">
                                <label class="control-label " for="content">
                                    Content
                                </label>
                                <sf:textarea class="form-control" cols="40" id="content" name="content" placeholder="Enter Page Content" rows="10" path="content"/>
                            </div>
                            <div class="form-group ">
                                <label class="control-label " for="slug">
                                    Url Slug
                                </label>
                                <sf:input class="form-control" id="slug" name="slug" placeholder="Enter the url alias" type="text" path="urlAlias"/>
                            </div>
                            <div class="form-group ">
                                <label class="control-label " for="pageOrder">
                                    Page Order
                                </label>
                                <sf:input class="form-control" id="pageOrder" name="pageOrder" placeholder="Enter the page order" type="text" path="pageOrder"/>
                            </div>
                            <div class="form-group">
                                <div>
                                    <button class="btn btn-primary " name="submit" type="submit">
                                        Add Page
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" value="1" name="userId"/>
                        </sf:form>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>