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

  <title>Create Project</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Create Project</h1>
  </div>
  <div class="row">
    <h4>Customer: ${customer.surname} ${customer.name}</h4>
  </div>
  <form class="form-horizontal" action="/admin/createProject" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="customerId" name="customerId" value="${customer.userId}"/>
        <input type="text" class="form-control" id="name" name="name" required placeholder="Enter name">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="startDate">Start date:</label>
      </div>
      <div class="col-md-10">
        <input type="date" class="form-control" id="startDate" name="startDate" required placeholder="Enter expected start date">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="finishDate">Finish date:</label>
      </div>
      <div class="col-md-10">
        <input type="date" class="form-control" id="finishDate" name="finishDate" required placeholder="Enter expected finish date">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-2">
        <label for="manager">Project Manager:</label>
      </div>
      <div class="col-md-7">
        <select id="manager" name="manager" class="form-control">
          <option value = "-1">None</option>
          <c:if test= "${fn:length(listManagers) > 0}">
            <c:forEach items="${listManagers}" var="manager">
              <option value = "${manager.userId}">${manager.surname} ${manager.name}</option>
            </c:forEach>
          </c:if>
        </select>
      </div>
    </div>

    <div class="form-group">
      <div class="col-md-offset-2 col-sm-1">
        <button type="submit" class="btn btn-lg btn-primary">Create</button>
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
