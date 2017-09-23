
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">HUBSPORT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/">Admin</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/users">Users</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/events">Events</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/places">Places</a></li>
				</ul>
			</div>
			<%@ include file="autbar.jsp"%>
			<!--/.nav-collapse -->
		</div>
	</nav>