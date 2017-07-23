<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Registration</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="<c:url value="/resources/css/ie10-viewport-bug-workaround.css" />"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/sticky-footer-navbar.css" />"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<!-- <%@ include file="adminHeader.jsp"%>  -->
<body>
	<div class="container">
		<p align="right">
			<strong>${loggedinuser}</strong>, <a href="<c:url value="/logout" />">Logout</a>
		</p>
		<div class="well lead">User Registration Form</div>
		<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<fieldset>
				<div class="control-group">
					<!-- Username -->
					<label class="control-label" for="username">Username</label>
					<div class="controls">
						<input type="text" id="username" name="username"
							class="input-xlarge">
						<p class="help-block">Username can contain any letters or
							numbers, without spaces</p>
						<div style="color: red;" class="has-error">
							<form:errors path="username" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- First Name -->
					<label class="control-label" for="firstName">First name</label>
					<div class="controls">
						<input type="text" id="firstName" name="firstName"
							class="input-xlarge">
						<p class="help-block">Please provide your First name</p>
						<div style="color: red;" class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- Last Name -->
					<label class="control-label" for="lastName">Last name</label>
					<div class="controls">
						<input type="text" id="lastName" name="lastName"
							class="input-xlarge">
						<p class="help-block">Please provide your Last name</p>
						<div style="color: red;" class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="email">E-mail</label>
					<div class="controls">
						<input type="text" id="email" name="email" placeholder=""
							class="input-xlarge">
						<p class="help-block">Please provide your E-mail</p>
						<div style="color: red;" class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" name="password"
							placeholder="" class="input-xlarge">
						<p class="help-block">Password should be at least 4 characters</p>
						<div style="color: red;" class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<!-- Password -->
					<label class="control-label" for="password_confirm">Password
						(Confirm)</label>
					<div class="controls">
						<input type="password" id="password_confirm"
							name="password_confirm" placeholder="" class="input-xlarge">
						<p class="help-block">Please confirm password</p>
						<div style="color: red;" class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>

				<div class="control-group">
					<div class="form-actions floatRight">
						<c:choose>
							<c:when test="${edit}">
								<input type="submit" value="Update"
									class="btn btn-primary btn-sm" /> or <a
									href="<c:url value='/users' />">Cancel</a>
							</c:when>
							<c:otherwise>
								<input type="submit" value="Register"
									class="btn btn-primary btn-sm" /> or <a
									href="<c:url value='/users' />">Cancel</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>




	<!-- <%@ include file="adminFooter.jsp"%>  -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"></script>

</body>
</html>