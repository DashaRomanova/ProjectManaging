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

  <title>Question</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <div class="row">
    <h1>Influencing task <${task.taskName}> is not added to any Sprints. Would you like to add this Task to this Sprint?</h1>
  </div>

  <div class="form-group">
    <div class="col-md-offset-2 col-sm-1">
      <a class="btn btn-lg btn-primary" href="/addTaskToSprint?taskId=${task.taskId}&Id=${sprint.sprintId}" role="button">Yes</a>
    </div>
    <div class="col-sm-9">
      <a class="btn btn-lg btn-primary" href="/showAllSprintsByProjectManagerIdPage?managerId=${sprint.project.projectManager.userId}" role="button">No</a>
    </div>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>