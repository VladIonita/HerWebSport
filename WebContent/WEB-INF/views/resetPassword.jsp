<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<title>Forgot password</title>

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

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/signin.css" />"
	rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
	function onLoad() {
		$("#confirmPassword").keyup(checkPasswordMatch);
	}

	function checkPasswordMatch() {
		var password = $("#password").val();
		var confirmPassword = $("#password_confirm").val();

		if (password != confirmPassword)
			$("#divCheckPasswordMatch").text("Passwords do not match!");
		else
			$("#divCheckPasswordMatch").text(" ");
	}

	$(document).ready(onLoad);
</script>
</head>
<body>


	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">HUBSPORT</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<spring:url value="/admin/password/reset" var="newActionUrl" />

		<form:form class="form-horizontal" method="post"
			modelAttribute="userForm" action="${newActionUrl}">

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-4">
						<form:password path="password" class="form-control" id="password"
							placeholder="Password" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Confirm Password</label>
				<div class="col-sm-4">
					<input type="password" class="form-control"
						onkeyup="checkPasswordMatch();" id="password_confirm"
						placeholder="Password Confirmation" />
					<div id="divCheckPasswordMatch" style="color: red;"></div>
				</div>
			</div>
			<button type="submit" class="btn btn-default">Update</button>
		</form:form>
	</div>



	<%@ include file="adminFooter.jsp"%>