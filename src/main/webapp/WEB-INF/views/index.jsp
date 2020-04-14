<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
</head>

<body>
<h3>user List</h3>
<c:if test="${!empty listUsers}">
        <c:forEach items="${listUsers}" var="user">
                <li>${user.userid}</li>
                <li>${user.username}</li>
        </c:forEach>
</c:if>
</body>
</html>