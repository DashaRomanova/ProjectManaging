<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <h1>Choose employee</h1>
  </div>
  <form class="form-horizontal" id="form-id" action="/projectManager/chooseEmployeeForReport" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="startDate">Date of the beginning:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="managerId" name="managerId" value="${manager.userId}"/>
        <input type="date" class="form-control" id="startDate" name="startDate" required placeholder="Enter date <from>">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="endDate">Date of the end:</label>
      </div>
      <div class="col-md-10">
        <input type="date" class="form-control" id="endDate" name="endDate" required placeholder="Enter date <to>">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
      </tr>
      </thead>
      <tbody>
        <c:if test= "${fn:length(listEmployees) > 0}">
          <c:forEach items="${listEmployees}" var="employee" varStatus="loopStatus">
            <tr onclick="document.getElementById('form-id').submit();">
              <input type="hidden" id="employeeId" name="employeeId" value="${employee.id}"/>
              <td>${loopStatus.index+1}</td>
              <td>${employee.name}</td>
              <td>${employee.surname}</td>
            </tr>
          </c:forEach>
        </c:if>
      </tbody>
    </table>
  </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>