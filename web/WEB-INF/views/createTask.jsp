<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Napha
  Date: 14.01.2017
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Create Task</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Create Task</h1>
  </div>
  <div class="row">
    <h4>Project: ${project.projectName}</h4>
  </div>
  <form class="form-horizontal" action="/projectManager/createTask" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="projectId" name="projectId" value="${project.projectId}"/>
        <input type="text" class="form-control" id="name" name="name" required placeholder="Enter name">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-2">
        <label for="qualification">Select qualification:</label>
      </div>
      <div class="col-md-10">
        <select class="form-control" id="qualification" name="qualifications">
          <c:if test= "${fn:length(listQualifications) > 0}">
            <c:forEach items="${listQualifications}" var="qualification">
              <option value = "${qualification}">${qualification}</option>
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
        <input type="number" min="1" class="form-control" id="estimate" name="estimate" required placeholder="Enter predicted runtime in hours">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="description">Task description:</label>
      </div>
      <div class="col-md-10">
        <textarea class="form-control" id="description" rows="4" name="description" placeholder="Enter task description"></textarea>
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-sm-1">
        <button type="submit" class="btn btn-lg btn-primary">Create</button>
      </div>
      <div class="col-sm-9">
        <a class="btn btn-lg btn-primary" href="/projectManager/showAllTasksByProjectManagerIdPage?managerId=${project.projectManager.userId}" role="button">Cancel</a>
      </div>
    </div>
  </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
