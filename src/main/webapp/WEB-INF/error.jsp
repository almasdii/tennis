<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.06.2026
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Errors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}">Home</a>
            </nav>
        </div>
    </section>
</header>
<div class="error">
    <h1>Errors : </h1>
    <h2 class="error-message" style="white-space: pre-line;">${requestScope.error}</h2>
</div>
</body>
</html>
