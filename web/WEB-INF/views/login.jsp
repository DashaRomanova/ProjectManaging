<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Login Page</title>

	<!-- Bootstrap core CSS -->
	<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/bootstrap/css/signin.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
<div class="container">

	<form class="form-signin" name='loginForm' action="<c:url value='${request.contextPath}/j_spring_security_check' />" method='POST'>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<h2 class="form-signin-heading">Please sign in</h2>
		<div class="form-group">
			<label for="inputUsername" >Username</label>
			<input type="text" name='username' id="inputUsername" class="form-control" placeholder="Enter username" required autofocus>
		</div>
		<div class="form-group">
			<label for="inputPassword" >Password</label>
			<input type="password" name='password' id="inputPassword" class="form-control" placeholder="Password" required>
		</div>
		<div class="form-group">
			<button name="submit" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</div
	</form>

</div>
</body>
</html>
