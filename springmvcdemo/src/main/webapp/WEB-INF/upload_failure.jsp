<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    request.setAttribute("basePath", basePath);
%>
<html>

<head>
    <table>上传失败</table>
</head>
<body><h1>上传失败</h1></body>
</html>