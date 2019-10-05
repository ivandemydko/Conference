<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 11.09.2019
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error404</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Request from "${pageContext.errorData.requestURI}" is failed</h2>
<img src="${pageContext.request.contextPath}/resources/css/img/404.png" alt="img"/>
</body>
</html>
