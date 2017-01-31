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

  <title>Edit Project</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Edit Project</h1>
  </div>
  <form class="form-horizontal" action="/admin/editProject" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-8">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="projectId" name="projectId" value="${project.projectId}"/>
        <input type="text" class="form-control" id="name" name="name" value="${project.projectName}" required placeholder="Enter name">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="startDate">Start date:</label>
      </div>
      <div class="col-md-8">
        <input type="date" class="form-control" id="startDate" name="startDate" value="${project.projectStartDate}" required placeholder="Enter expected start date">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="finishDate">Finish date:</label>
      </div>
      <div class="col-md-8">
        <input type="date" class="form-control" id="finishDate" name="finishDate" value="${project.projectFinishDate}" required placeholder="Enter expected finish date">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-2">
        <label for="manager">Project Manager:</label>
      </div>
      <div class="col-md-7">
        <select id="manager" name="manager" class="form-control">
          <c:if test= "${fn:length(listManagers) > 0}">
            <c:choose>
              <c:when test= "${project.projectManager == null}">
                <option value = "-1" selected>None</option>
              </c:when>
              <c:otherwise>
                <c:forEach items="${listManagers}" var="manager">
                  <c:choose>
                    <c:when test= "${manager.userId == project.projectManager.userId}">
                      <option value = "${manager.userId}" selected>${manager.surname} ${manager.name}</option>
                    </c:when>
                    <c:otherwise>
                      <option value = "${manager.userId}">${manager.surname} ${manager.name}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </c:if>
        </select>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-offset-2 col-sm-1">
        <button type="submit" class="btn btn-lg btn-primary">Edit</button>
      </div>
      <div class="col-sm-9">
        <a class="btn btn-lg btn-primary" href="/admin/showAllProjectsPage" role="button">Cancel</a>
      </div>
    </div>
  </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
