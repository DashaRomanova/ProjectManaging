<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Napha
  Date: 25.01.2017
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Dashboard for administrator</title>

  <!-- Bootstrap core CSS -->
  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/bootstrap/css/dashboard.css" rel="stylesheet">
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Project Manager</a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <script>
          function formSubmit() {
            document.getElementById("logoutForm").submit();
          }
        </script>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <ul class="nav nav-sidebar">
        <li><a href="/employee/showAllCompletedTasksByEmployeeIdPage?employeeId=${employee.userId}">Completed Task</a></li>
        <li><a href="/employee/showAllInProgressTasksByEmployeeIdPage?employeeId=${employee.userId}">Task in progress</a></li>
        <li><a href="/employee/showAllAssignedTasksByEmployeeIdPage?employeeId=${employee.userId}">Assigned Task</a></li>
        <li class="active"><a href="/employee/showAllReadyToRunTasksByEmployeeIdPage?employeeId=${employee.userId}">Task ready to run</a></li>
        <li><a href="/employee/showAllRefusedTasksByEmployeeIdPage?employeeId=${employee.userId}">Refused request task</a></li>
        <li><a href="/employee/showAllRequestsByEmployeeIdPage?employeeId=${employee.userId}">Requests</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
    <c:choose>
      <c:when test= "${fn:length(listTasks) > 0}">
        <h1 class="page-header">Task</h1>
        <div class="table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Description</th>
              <th>Project</th>
              <th>Start date</th>
              <th>Finish date</th>
              <th>Begin</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${listTasks}" var="task" varStatus="loopStatus">
                <tr>
                  <td>${loopStatus.index+1}</td>
                  <td>${task.taskName}</td>
                  <td>${task.taskDescription}</td>
                  <td>${task.project.projectName}</td>
                  <td>${task.startDate}</td>
                  <td>${task.expectedCompletionDate}</td>
                  <td><a class="btn btn-default" href="/employee/beginTask?taskId=${task.taskId}" role="button">Begin</a></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:when>
      <c:otherwise>
        <h1 class="row">You do not have tasks to run!</h1>
      </c:otherwise>
    </c:choose>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap/js/docs.min.js"></script>
</body>
</html>
