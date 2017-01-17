<%@ page import="code.domain.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="code.domain.Task" %>
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

  <title>Employees</title>

  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <div class="row">
    <h1>Employees</h1>
  </div>
  <table class="table table-hover">
    <thead>
    <tr>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Qualification</th>
      <th>Role</th>
      <th>Tasks</th>
      <th>Edit</th>
    </tr>
    </thead>
    <tbody>
      <% List<Employee> employees= (List<Employee>) request.getAttribute("listEmployees");
        for (Employee employee: employees)
        {
      %>
      <tr>
        <td><%=employee.getName()%></td>
        <td><%=employee.getSurname()%></td>
        <td><%=employee.getQualification().getQualificationName()%></td>
        <td><%=employee.getRole()%></td>
        <td>
          <select class="form-control">
            <%if(employee.getTasks()!= null){
              for (Task task: employee.getTasks())
              {
              %>
              <option value = "<%=task.getTaskId()%>"><%=task.getTaskName()%></option>
              <%}%>
            <%}%>
          </select>
        </td>
        <td><a class="btn btn-default" href="#" role="button">Edit</a></td>
      </tr>
    <%}%>
    </tbody>
  </table>
  <div class="col-md-offset-1 col-sm-11">
    <a class="btn btn-primary" href="#" role="button">Add new employee</a></td>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>