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

  <title>Tasks</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <div class="row">
    <h1>Tasks</h1>
  </div>
  <table class="table table-hover">
    <thead>
    <tr>
      <th>#</th>
      <th>Name</th>
      <th>Description</th>
      <th>Qualification</th>
      <th>Predicted runtime</th>
      <th>Expected finish date</th>
      <th>Influencing tasks</th>
      <th>Sub tasks</th>
      <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:if test= "${fn:length(listTasks) > 0}">
      <c:forEach items="${listTasks}" var="task" varStatus="loopStatus">
        <tr>
          <td>${loopStatus.index+1}</td>
          <td>${task.taskName}</td>
          <td>${task.taskDescription}</td>
          <td>${task.taskQualification.qualificationName}</td>
          <td>${task.estimate}</td>
          <td>${task.expirationDate}</td>
          <td>
            <select class="form-control" path="task.influencingTasks">
              <c:if test= "${fn:length(task.influencingTasks) > 0}">
                <c:forEach items="${task.influencingTasks}" var="influencingTask">
                  <option value = "${influencingTask.taskId}">${influencingTask.taskName}</option>
                </c:forEach>
              </c:if>
            </select>
          </td>
          <td>
            <select class="form-control" path="task.subTasks">
              <c:if test= "${fn:length(task.subTasks) > 0}">
                <c:forEach items="${task.subTasks}" var="subTask">
                  <option value = "${subTask.taskId}">${subTask.taskName}</option>
                </c:forEach>
              </c:if>
            </select>
          </td>
          <td><a class="btn btn-default" href="/editTaskPage?taskId=${task.taskId}" role="button">Edit</a></td>
        </tr>
      </c:forEach>
    </c:if>
    </tbody>
  </table>
  <div class="col-md-offset-1 col-sm-11">
    <a class="btn btn-primary" href="/createTaskPage" role="button">Add new task</a></td>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>