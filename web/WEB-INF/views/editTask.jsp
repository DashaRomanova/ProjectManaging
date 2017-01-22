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
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Edit Task</h1>
  </div>
  <form class="form-horizontal" action="/editTask" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" id="taskId" name="taskId" value="${task.taskId}"/>
        <input type="text" class="form-control" id="name" name="name" value="${task.taskName}" required placeholder="Enter name">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-2">
        <label for="qualification">Select qualification:</label>
      </div>
      <div class="col-md-10">
        <select class="form-control" id="qualification" name="qualification">
          <c:if test= "${fn:length(listQualifications) > 0}">
            <c:forEach items="${listQualifications}" var="qualification">
              <c:choose>
                <c:when test= "${qualification.qualificationId == task.taskQualification.qualificationId}">
                  <option value = "${qualification.qualificationId}" selected >${qualification.qualificationName}</option>
                </c:when>
                <c:otherwise>
                  <option value = "${qualification.qualificationId}">${qualification.qualificationName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </c:if>
        </select>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="estimate">Predicted runtime:</label>
      </div>
      <div class="col-md-10">
        <input type="number" min="1" class="form-control" id="estimate" name="estimate" value="${task.estimate}" required placeholder="Enter predicted runtime in hours">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="finishDate">Expected finish date:</label>
      </div>
      <div class="col-md-10">
        <input type="date" class="form-control" id="finishDate" name="finishDate" value="${task.expirationDate}" required placeholder="Enter expected finish date">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="description">Task description:</label>
      </div>
      <div class="col-md-10">
        <textarea class="form-control" id="description" rows="4" name="description" placeholder="Enter task description">${task.taskDescription}</textarea>
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-2">
        <label for="influencingTasks">Influencing tasks:</label>
      </div>
      <div class="col-md-10">
        <select class="form-control" id="influencingTasks" name="influencingTask">
          <c:if test= "${fn:length(task.influencingTasks) > 0}">
            <c:forEach items="${task.influencingTasks}" var="influencingTask">
              <option value = "${influencingTask.taskId}">${influencingTask.taskName}</option>
            </c:forEach>
          </c:if>
        </select>
      </div>
      <div class="col-md-2">
        <a class="btn btn-default" href="/addInfluencingTaskToTask?taskId=${task.taskId}" role="button">Add</a>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-2">
        <label for="subTasks">Sub tasks:</label>
      </div>
      <div class="col-md-10">
        <select class="form-control" id="subTasks" name="subTask">
          <c:if test= "${fn:length(task.subTasks) > 0}">
            <c:forEach items="${task.subTasks}" var="subTask">
              <option value = "${subTask.taskId}">${subTask.taskName}</option>
            </c:forEach>
          </c:if>
        </select>
      </div>
      <div class="col-md-2">
        <a class="btn btn-default" href="/addSubTaskToTask?taskId=${task.taskId}" role="button">Add</a>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-offset-2 col-sm-10">
        <button type="submit" class="btn btn-lg btn-primary">Edit</button>
      </div>
    </div>
  </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
