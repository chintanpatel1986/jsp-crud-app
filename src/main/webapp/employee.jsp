<%@ page import="com.codecamp.app.repository.DepartmentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecamp.app.model.Department" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: chintanpatel
  Date: 21/05/24
  Time: 10:40 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Management</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" href="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" href="js/bootstrap.min.js"></script>
</head>
<%
    DepartmentDao departmentDao = new DepartmentDao();
    List<Department>departmentList = departmentDao.getAllDepartmentList();
    request.setAttribute("departmentList", departmentList);
%>
<body>
<div class="container mt-5 border border-1">
    <div class="container">
        <div class="fs-1 fw-bold">
            <h1>Manage Employee</h1>
        </div>
        <form action="EmployeeController" method="post" class="row g-3">
            <input type="hidden" name="employeeId" value="${employee.employeeId}">
            <div class="col-4 mt-5">
                <label class="form-label fw-semibold">FirstName</label>
                <input type="text" name="firstName" class="form-control" value="${employee.firstName}">
            </div>
            <div class="col-4 mt-5">
                <label class="form-label fw-semibold">MiddleName</label>
                <input type="text" name="middleName" class="form-control" value="${employee.middleName}">
            </div>
            <div class="col-4 mt-5">
                <label class="form-label fw-semibold">LastName</label>
                <input type="text" name="lastName" class="form-control" value="${employee.lastName}">
            </div>
            <div class="col-12">
                <label class="form-label fw-semibold">Address</label>
                <textarea name="address" cols="38" rows="5" class="form-control">${employee.address}</textarea>
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">E-Mail</label>
                <input type="email" name="email" class="form-control" value="${employee.email}">
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">Mobile</label>
                <input type="text" name="mobile" class="form-control" value="${employee.mobile}">
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">BirthDate</label>
                <input type="date" name="birthDate" class="form-control" value="${employee.birthDate}">
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">UserName</label>
                <input type="text" name="userName" class="form-control" value="${employee.userName}">
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">Password</label>
                <input type="password" name="password" class="form-control" value="${employee.password}">
            </div>
            <div class="col-4">
                <label class="form-label fw-semibold">Department</label>
                <select id="departmentId" name="departmentId" class="form-select">
                    <c:choose>
                        <c:when test="${employee.departmentId > 0}">
                            <option value="-1" id="selected">----------Select Department----------</option>
                            <c:forEach items="${departmentList}" var="department">
                                <c:choose>
                                    <c:when test="${department.departmentId == employee.departmentId}">
                                        <option value="${department.departmentId}" selected="selected">${department.departmentName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.departmentId}">${department.departmentName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <option value="-1" label="---select---" selected="selected">----------Select Department----------</option>
                            <c:forEach items="${departmentList}" var="department">
                                <c:choose>
                                    <c:when test="${department.departmentId == employee.departmentId}">
                                        <option value="${department.departmentId}" selected="selected">${department.departmentName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.departmentId}">${department.departmentName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div class="col-12 d-grid gap-2">
                <input type="submit" value="Submit" class="btn btn-success">
            </div>
        </form>
    </div>
    <div class="container mt-5">
        <c:if test="${!empty employeeList}">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>firsName</th>
                        <th>MiddleName</th>
                        <th>LastName</th>
                        <th>E-Mail</th>
                        <th>Mobile</th>
                        <th>BirthDate</th>
                        <th>UserName</th>
                        <th>Department</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employeeList}" var="employee">
                        <tr>
                            <td>${employee.firstName}</td>
                            <td>${employee.middleName}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.mobile}</td>
                            <td><fmt:formatDate value="${employee.birthDate}" pattern="yyyy/MM/dd"/></td>
                            <td>${employee.userName}</td>
                            <td>${employee.departmentName}</td>
                            <td>
                                <a href="EmployeeController?action=edit&employeeId=${employee.employeeId}" class="link-success">Edit</a>
                                &nbsp;|&nbsp;
                                <a href="EmployeeController?action=delete&employeeId=${employee.employeeId}" class="link-danger">Delete</a>
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
