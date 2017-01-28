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
        <li><a href="/showAllTasksByProjectManagerIdPage?managerId=${manager.userId}">Task</a></li>
        <li class="active"><a href="/showAllSprintsByProjectManagerIdPage?managerId=${manager.userId}">Sprint</a></li>
        <li><a href="/showAllRequestsByProjectManagerIdPage?managerId=${manager.userId}">Requests from Employees</a></li>
      </ul>
      <ul class="nav nav-sidebar">
        <li><a href="#">Overview</a></li>
        <li><a href="#">Reports</a></li>
        <li><a href="#">Analytics</a></li>
        <li><a href="#">Export</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
      <h1 class="page-header">Task</h1>

      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <a class="btn btn-primary" href="/chooseProjectPage?managerId=${manager.userId}&entityName=Sprint" role="button">Add new Sprint</a></td>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Project</th>
            <th>Previous sprint</th>
            <th>Start date</th>
            <th>Finish date</th>
            <th>Tasks</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
          </thead>
          <tbody>
          <c:if test= "${fn:length(listSprints) > 0}">
            <c:forEach items="${listSprints}" var="sprint" varStatus="loopStatus">
              <tr>
                <td>${loopStatus.index+1}</td>
                <td>${sprint.sprintName}</td>
                <td>${sprint.project.projectName}</td>
                <td>${sprint.previousSprint.sprintName}</td>
                <td>${sprint.sprintStartDate}</td>
                <td>${sprint.sprintFinishDate}</td>
                <td>
                  <select class="form-control" path="task.influencingTasks">
                    <c:if test= "${fn:length(sprint.tasks) > 0}">
                      <c:forEach items="${sprint.tasks}" var="task">
                        <option value = "${task.taskId}">${task.taskName}</option>
                      </c:forEach>
                    </c:if>
                  </select>
                  <a class="btn btn-default" href="/chooseTask?Id=${sprint.sprintId}&entityName=Sprint" role="button">Add</a>
                </td>
                <td><a class="btn btn-default" href="/editSprintPage?sprintId=${sprint.sprintId}" role="button">Edit</a></td>
                <td><a class="btn btn-default" href="/deleteSprintPage?sprintId=${sprint.sprintId}" role="button">Delete</a></td>
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
