<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chintanpatel
  Date: 20/05/24
  Time: 11:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department Management</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" href="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" href="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-5 border border-1">
    <div class="container">
        <div class="fs-1 fw-bold">
            <h1>Manage Department</h1>
        </div>
        <form action="DepartmentController" method="post" class="row g-3">
            <input type="hidden" name="departmentId" value="${department.departmentId}">
            <div class="col-12 mt-5">
                <label class="form-label fw-semibold">Department</label>
                <input type="text" name="departmentName" class="form-control" value="${department.departmentName}">
            </div>
            <div class="col-12 d-grid gap-2">
                <input type="submit" value="Submit" class="btn btn-success">
            </div>
        </form>
    </div>
    <div class="container mt-5">
        <c:if test="${!empty departmentList}">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Department</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${departmentList}" var="department">
                        <tr>
                            <td class="col-6">${department.departmentName}</td>
                            <td class="col-6">
                                <a href="DepartmentController?action=edit&departmentId=${department.departmentId}" class="link-success">Edit</a>
                                &nbsp;|&nbsp;
                                <a href="DepartmentController?action=delete&departmentId=${department.departmentId}" class="link-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
