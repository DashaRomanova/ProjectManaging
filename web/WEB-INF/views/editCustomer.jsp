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

  <title>Edit Customer</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <h1>Edit Customer</h1>
  </div>
  <form class="form-horizontal" action="/admin/editCustomer" method="POST">
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="login">Login:</label>
      </div>
      <div class="col-md-10">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="customerId" name="customerId" value='${customer.userId}'/>
        <input type="text" class="form-control" id="login" name="login" value="${customer.username}" required placeholder="Enter login">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="password">Password:</label>
      </div>
      <div class="col-md-10">
        <input type="password" class="form-control" id="password" name="password" value="${customer.password}" required placeholder="Enter password">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group has-feedback">
      <div class="col-md-2">
        <label class="control-label" for="name">Name:</label>
      </div>
      <div class="col-md-10">
        <input type="text" class="form-control" id="name" name="name" value="${customer.name}" required placeholder="Enter name">
        <span class="glyphicon form-control-feedback"></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-2">
        <label class="control-label" for="surname">Surname:</label>
      </div>
      <div class="col-md-10">
        <input type="text" class="form-control" id="surname" name="surname" value="${customer.surname}" required placeholder="Enter surname">
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-sm-1">
        <button type="submit" class="btn btn-lg btn-primary">Edit</button>
      </div>
      <div class="col-sm-9">
        <a class="btn btn-lg btn-primary" href="/admin/showAllCustomersPage" role="button">Cancel</a>
      </div>
    </div>
  </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
