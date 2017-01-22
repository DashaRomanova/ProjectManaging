<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="code.domain.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="code.domain.Task" %>
<%--
  Created by IntelliJ IDEA.
  User: Napha
  Date: 16.01.2017
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Employees</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <div class="row">
    <h1>Employees</h1>
  </div>
  <table class="table table-hover">
    <thead>
    <tr>
      <th>#</th>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Qualification</th>
      <th>Role</th>
      <th>Tasks</th>
      <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:if test= "${fn:length(listEmployees) > 0}">
      <c:forEach items="${listEmployees}" var="employee" varStatus="loopStatus">
        <tr>
          <td>${loopStatus.index+1}</td>
          <td>${employee.name}</td>
          <td>${employee.surname}</td>
          <td>${employee.qualification.qualificationName}</td>
          <td>${employee.role}</td>
          <td>
            <select class="form-control" path="employee.tasks">
              <c:if test= "${fn:length(employee.tasks) > 0}">
                <c:forEach items="${employee.tasks}" var="task">
                  <option value = "${task.taskId}">${task.taskName}</option>
                </c:forEach>
              </c:if>
            </select>
          </td>
          <td><a class="btn btn-default" href="/editEmployee?id=${employee.userId}" role="button">Edit</a></td>
        </tr>
      </c:forEach>
    </c:if>
    </tbody>
  </table>
  <div class="col-md-offset-1 col-sm-11">
    <a class="btn btn-primary" href="/createEmployeePage" role="button">Add new employee</a></td>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>