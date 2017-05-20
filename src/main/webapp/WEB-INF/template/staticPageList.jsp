<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>Pages</h3>
<ul>
        <c:forEach var="page" items="${staticPages}">
        <li>
            <a href="${pageContext.request.contextPath}/static/showPage/${page.staticPageId}">${page.title}</a>
        </li>
        </c:forEach>
</ul>