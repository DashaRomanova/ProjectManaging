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
        <li><a href="#">Dashboard</a></li>
        <li><a href="#">Settings</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">Help</a></li>
      </ul>
      <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="Search...">
      </form>
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <ul class="nav nav-sidebar">
        <li><a href="/showAllCompletedTasksByEmployeeIdPage?employeeId=${employee.userId}">Completed Task</a></li>
        <li><a href="/showAllInProgressTasksByEmployeeIdPage?employeeId=${employee.userId}">Task in progress</a></li>
        <li class="active"><a href="/showAllAssignedTasksByEmployeeIdPage?employeeId=${employee.userId}">Assigned Task</a></li>
        <li><a href="/showAllReadyToRunTasksByEmployeeIdPage?employeeId=${employee.userId}">Task ready to run</a></li>
        <li><a href="/showAllRefusedTasksByEmployeeIdPage?employeeId=${employee.userId}">Refused request task</a></li>
        <li><a href="/showAllRequestsByEmployeeIdPage?employeeId=${employee.userId}">Requests</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
      <h1 class="page-header">Task</h1>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th>
            <th>Project</th>
            <th>Estimate</th>
            <th>Project Manager</th>
            <th>Confirm</th>
            <th>Request</th>
          </tr>
          </thead>
          <tbody>
          <c:if test= "${fn:length(listTasks) > 0}">
            <c:forEach items="${listTasks}" var="task" varStatus="loopStatus">
              <tr>
                <td>${loopStatus.index+1}</td>
                <td>${task.taskName}</td>
                <td>${task.taskDescription}</td>
                <td>${task.project.projectName}</td>
                <td>${task.estimate}</td>
                <td>${task.project.projectManager.surname} ${task.project.projectManager.name}</td>
                <td><a class="btn btn-default" href="/confirmTask?taskId=${task.taskId}" role="button">Confirm</a></td>
                <td><a class="btn btn-default" href="/requestEstimateTaskPage?taskId=${task.taskId}" role="button">Request</a></td>
              </tr>
            </c:forEach>
          </c:if>
          </tbody>
        </table>
      </div>
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
