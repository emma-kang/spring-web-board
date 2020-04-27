<%--
  Created by IntelliJ IDEA.
  User: yuseon
  Date: 2020-04-26
  Time: 7:57 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <c:forEach items="${listUsers}" var="person">
            <tr>
                <td>${person.userid}</td>
                <td>${person.username}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
