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

  <title>Dashboard for Project Manager</title>

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
        <li><a href="/projectManager/showAllTasksByProjectManagerIdPage?managerId=${manager.userId}">Task</a></li>
        <li><a href="/projectManager/showAllSprintsByProjectManagerIdPage?managerId=${manager.userId}">Sprint</a></li>
        <li><a href="/projectManager/showAllRequestsByProjectManagerIdPage?managerId=${manager.userId}">Requests from Employees</a></li>
      </ul>
      <ul class="nav nav-sidebar">
        <li>Reports
          <ul class="nav nav-sidebar">
            <li><a href="/projectManager/chooseProjectForOvertimeReportPage?managerId=${manager.userId}">Overtime</a></li>
            <li class="active"><a href="/projectManager/chooseProjectForReportPage?managerId=${manager.userId}">Statistic of project, sprints and tasks</a></li>
            <li><a href="/projectManager/chooseEmployeeForReportPage?managerId=${manager.userId}">Employee work statistic</a></li>
            <li><a href="/projectManager/chooseEmployeeForTaskReportPage?managerId=${manager.userId}">Employee statistic of time deviation from predicted by tasks</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
      <c:choose>
        <c:when test= "${fn:length(projectStatisticReport.sprintStatisticReports) > 0}">
          <div class="row">
            <h1 class="row">Project <${projectStatisticReport.projectName}> start: ${projectStatisticReport.projectStartDate} end: ${projectStatisticReport.projectFinishDate}</h1>
            <h3 class="row">Predicted Estimate: ${projectStatisticReport.sumEstimate}</h3>
            <h3 class="row">Actual Estimate: ${projectStatisticReport.sumActualEstimate}</h3>
            <c:choose>
              <c:when test= "${projectStatisticReport.delta >= 0}">
                <h3 class="row" style="color: green">Delta: ${projectStatisticReport.delta}</h3>
              </c:when>
              <c:otherwise>
                <h3 class="row" style="color: red">Delta: ${projectStatisticReport.delta}</h3>
              </c:otherwise>
            </c:choose>
          </div>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>Sprint</th>
                <th>Task</th>
                <th>Start Date</th>
                <th>Expected Completion Date</th>
                <th>Expected Estimate</th>
                <th>Actual Estimate</th>
                <th>Delta</th>
              </tr>
              </thead>
              <tbody>
                <c:forEach items="${projectStatisticReport.sprintStatisticReports}" var="sprintStatisticReport">
                  <tr>
                    <td colspan="2">${sprintStatisticReport.sprintName}</td>
                    <td>${sprintStatisticReport.sprintStartDate}</td>
                    <td>${sprintStatisticReport.sprintFinishDate}</td>
                    <td>${sprintStatisticReport.sumEstimate}</td>
                    <td>${sprintStatisticReport.sumActualEstimate}</td>
                    <c:choose>
                      <c:when test= "${sprintStatisticReport.delta >= 0}">
                        <td style="color: darkgreen">${sprintStatisticReport.delta}</td>
                      </c:when>
                      <c:otherwise>
                        <td style="color: darkred">${sprintStatisticReport.delta}</td>
                      </c:otherwise>
                    </c:choose>
                  </tr>
                  <c:forEach items="${sprintStatisticReport.taskStatisticReports}" var="taskStatisticReport">
                    <tr>
                      <td></td>
                      <td>${taskStatisticReport.taskName}</td>
                      <td>${taskStatisticReport.startDate}</td>
                      <td>${taskStatisticReport.expectedCompletionDate}</td>
                      <td>${taskStatisticReport.estimate}</td>
                      <td>${taskStatisticReport.actualEstimate}</td>
                      <c:choose>
                        <c:when test= "${taskStatisticReport.delta >= 0}">
                          <td style="color: darkgreen">${taskStatisticReport.delta}</td>
                        </c:when>
                        <c:otherwise>
                          <td style="color: darkred">${taskStatisticReport.delta}</td>
                        </c:otherwise>
                      </c:choose>
                    </tr>
                  </c:forEach>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </c:when>
        <c:otherwise>
          <h2 class="row">Project ${projectStatisticReport.projectName} has not got any Sprints!</h2>
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
