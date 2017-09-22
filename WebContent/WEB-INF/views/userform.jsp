<%@ page session="false"%>
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
		<c:when test="${userForm['new']}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/admin/users/add" var="userActionUrl" />


	<form:form class="form-horizontal" method="post"
		modelAttribute="userForm" action="${userActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="firstName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10">
					<form:input path="firstName" type="text" class="form-control"
						id="firstName" placeholder="First Name" />
					<form:errors path="firstName" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="lastName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10">
					<form:input path="lastName" type="text" class="form-control"
						id="lastName" placeholder="Last Name" showPassword="false" />
					<form:errors path="lastName" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<c:choose>
						<c:when test="${userForm['new']}">
							<form:input path="email" class="form-control" id="email"
								placeholder="Email" />
							<form:errors path="email" class="control-label" />
						</c:when>
						<c:otherwise>
							<form:input path="email" class="form-control" id="email"
								placeholder="Email" />
							<form:errors path="email" class="control-label" />
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:password path="password" id="password"
						class="form-control input-sm" placeholder="Password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="retypePassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Confirm Password</label>
				<div class="col-sm-10">
					<form:password path="retypePassword"
						class="form-control input-sm" onkeyup="checkPasswordMatch();"
						id="password_confirm" placeholder="Password Confirmation" />
					<form:errors path="retypePassword" class="control-label" />
					<div id="divCheckPasswordMatch" style="color: red;"></div>
				</div>
			</div>
		</spring:bind>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
						<input type="submit" value="Register"
							class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/users' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/users' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

