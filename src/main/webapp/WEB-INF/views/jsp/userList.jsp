<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">User ID</th>
            <th width="120">User Name</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.userid}</td>
                <td>${user.username}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
