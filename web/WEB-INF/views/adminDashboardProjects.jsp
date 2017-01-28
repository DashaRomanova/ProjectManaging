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
      <a class="navbar-brand" href="#">Administrator</a>
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
        <li class="active"><a href="/showAllProjectsPage">Project</a></li>
        <li><a href="/showAllEmployeesPage">Employee</a></li>
        <li><a href="/showAllCustomersPage">Customer</a></li>
      </ul>
      <ul class="nav nav-sidebar">
        <li><a href="#">Overview</a></li>
        <li><a href="#">Reports</a></li>
        <li><a href="#">Analytics</a></li>
        <li><a href="#">Export</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
      <h1 class="page-header">Project</h1>

      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <a class="btn btn-primary" href="/chooseCustomerForProjectPage" role="button">Create new project</a></td>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Start date</th>
            <th>Finish date</th>
            <th>Project Manager</th>
            <th>Customer</th>
            <th>Sprints</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
          </thead>
          <tbody>
          <c:if test= "${fn:length(listProjects) > 0}">
            <c:forEach items="${listProjects}" var="project" varStatus="loopStatus">
              <tr>
                <td>${loopStatus.index+1}</td>
                <td>${project.projectName}</td>
                <td>${project.projectStartDate}</td>
                <td>${project.projectFinishDate}</td>
                <td>${project.projectManager.surname} ${project.projectManager.name}</td>
                <td>${project.customer.surname} ${project.customer.name}</td>
                <td>
                  <select class="form-control" path="project.sprints">
                    <c:if test= "${fn:length(project.sprints) > 0}">
                      <c:forEach items="${project.sprints}" var="sprint">
                        <option value = "${sprint.sprintId}">${sprint.sprintId}</option>
                      </c:forEach>
                    </c:if>
                  </select>
                </td>
                <td><a class="btn btn-default" href="/editProjectPage?projectId=${project.projectId}" role="button">Edit</a></td>
                <td><a class="btn btn-default" href="/deleteProjectPage?projectId=${project.projectId}" role="button">Delete</a></td>
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
