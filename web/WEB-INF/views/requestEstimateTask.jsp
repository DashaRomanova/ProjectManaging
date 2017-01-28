<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Napha
  Date: 14.01.2017
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Edit Task</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

  <script type="text/javascript">
    //////////////////////////////////////////////////////////////////////////////////
  </script>
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Edit Task</h1>
  </div>
  <form class="form-horizontal" action="/requestEstimateTask" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" id="taskId" name="taskId" value="${task.taskId}"/>
        <input type="text" class="form-control" id="name" name="name" value="${task.taskName}" required readonly>
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="estimate">Predicted runtime:</label>
      </div>
      <div class="col-md-10">
        <input type="text" class="form-control" id="estimate" name="estimate" value="${task.estimate}" required readonly>
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="requestEstimate">Request runtime:</label>
      </div>
      <div class="col-md-10">
        <input type="number" min="1" class="form-control" id="requestEstimate" name="requestEstimate" value="${task.estimate}" required placeholder="Enter request runtime in hours">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-sm-1">
        <button type="submit" class="btn btn-lg btn-primary">Submit</button>
      </div>
      <div class="col-sm-9">
        <a class="btn btn-lg btn-primary" href="/showAllAssignedTasksByEmployeeIdPage?employeeId=${task.employee.userId}" role="button">Cancel</a>
      </div>
    </div>
  </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
