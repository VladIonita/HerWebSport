<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



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
	<div class="well lead">User Registration Form</div>
	<form:form method="POST" modelAttribute="user" class="form-horizontal">
		<form:input type="hidden" path="username" id="username" />

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="firstName">First
					Name</label>
				<div class="col-md-7">
					<form:input type="text" path="firstName" id="firstName"
						class="form-control input-sm" />
					<p class="help-block">Please provide your First name</p>
					<div style="color: red;" class="has-error">
						<form:errors path="firstName" class="help-inline" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="lastName">Last
					Name</label>
				<div class="col-md-7">
					<form:input type="text" path="lastName" id="lastName"
						class="form-control input-sm" />
					<p class="help-block">Please provide your Last name</p>
					<div style="color: red;" class="has-error">
						<form:errors path="lastName" class="help-inline" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="username">Username</label>
				<div class="col-md-7">
					<c:choose>
						<c:when test="${edit}">
							<form:input type="text" path="username" id="username"
								name="username" value="${username}"
								class="form-control input-sm" disabled="true" />
						</c:when>
						<c:otherwise>
							<form:input type="text" path="username" id="username"
								class="form-control input-sm" />
							<p class="help-block">Username can contain any letters or
								numbers, without spaces</p>
							<div style="color: red;" class="has-error">
								<form:errors path="username" class="help-inline" />
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="password">Password</label>
				<div class="col-md-7">
					<form:input type="password" path="password" id="password"
						class="form-control input-sm" />
					<p class="help-block">Password should be at least 4 characters</p>
					<div style="color: red;" class="has-error">
						<form:errors path="password" class="help-inline" />
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="password_confirm">Password(Confirm)</label>
				<div class="col-md-7">
					<form:input type="password" path="password" id="password_confirm"
						onkeyup="checkPasswordMatch();" class="form-control input-sm" />
					<p class="help-block">Please confirm password</p>
					<div id="divCheckPasswordMatch" style="color: red;"></div>
					<div style="color: red;" class="has-error">
						<form:errors path="password" class="help-inline" />
					</div>
				</div>
			</div>
		</div>



		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="email">Email</label>
				<div class="col-md-7">
					<form:input type="text" path="email" id="email"
						class="form-control input-sm" />
					<p class="help-block">Please provide your E-mail</p>
					<div style="color: red;" class="has-error">
						<form:errors path="email" class="help-inline" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/users/newuser' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Register"
							class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/users/newuser' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</div>


