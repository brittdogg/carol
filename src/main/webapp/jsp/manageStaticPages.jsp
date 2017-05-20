<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <div class="col-md-10">
                        <table width="80%">
                            <thead>
                            <th width="70%">
                                Page Name:
                            </th>
                            <th width="15%">Edit</th>
                            <th width="15%">Delete</th>
                            </thead>
                            <tbody>
                                <c:forEach var="page" items="${pages}">
                                <tr>
                                    <td>
                                        <a href="../static/showPage/<c:out value="${page.staticPageId}"/>"><c:out value="${page.title}"/></a>
                                    </td>
                                    <td>
                                        <a href="static/edit/<c:out value="${page.staticPageId}"/>">Edit</a>
                                    </td>
                                    <td>
                                        <a href="static/delete/<c:out value="${page.staticPageId}"/>">Delete</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/template/scripts.jsp"/>
        </div>
    </body>
</html>