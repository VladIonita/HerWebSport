<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



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



<div class="container">
	<c:choose>
		<c:when test="${edit}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br/>
	<spring:url value="/s" var="userActionUrl" />

	<form:form class="form-horizontal" method="post"
		modelAttribute="userForm" action="${userActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="firstName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10">
					<form:input path="firstName" type="text" class="form-control "
						id="firstName" placeholder="First Name" />
					<form:errors path="firstName" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="lastName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10">
					<form:input path="lastName" type="text" class="form-control "
						id="lastName" placeholder="Last Name" />
					<form:errors path="lastName" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email"
						placeholder="Email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:password path="password" class="form-control" id="password"
						placeholder="password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">confirm Password</label>
				<div class="col-sm-10">
					<form:password path="password" class="form-control" onkeyup="checkPasswordMatch();"
						id="password_confirm" placeholder="password" />
					<div id="divCheckPasswordMatch" style="color: red;"></div>
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>



		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>