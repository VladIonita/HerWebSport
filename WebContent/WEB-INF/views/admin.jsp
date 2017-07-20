<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>Admin</title>

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


	<!-- Begin page content 
	<div class="container">
		<p align="right">
			Welcome to Admin Page <strong>${user}</strong>, <a
				href="<c:url value="/logout" />">Logout</a>
		</p>
		<div class="page-header">
			<h1>Sticky footer with fixed navbar</h1>
		</div>
		<p class="lead">
			Pin a fixed-height footer to the bottom of the viewport in desktop
			browsers with this custom HTML and CSS. A fixed navbar has been added
			with
			<code>padding-top: 60px;</code>
			on the
			<code>body &gt; .container</code>
			.
		</p>

	</div>
	-->



	<div id="headerwrap">
		<a>Welcome to HubSport <strong>${loggedinuser}</strong></a>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<div class="panel-heading">
						<span class="lead">List of Users </span>
					</div>
					<!-- afisarea e buna -->
					<c:forEach var="row" items="${userList}">
						User Id: ${row.id} <br />
						User first name: ${row.firstName} <br />
						User last name: ${row.lastName} <br />
						Email: ${row.email} <br />
						<br />
					</c:forEach>
				</div>
				<div class="col-lg-8 col-lg-offset-2 himg"></div>
			</div>
			<!-- /row -->
		</div>
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