<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- Begin page content -->
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right">
			<a href="<c:url value="/admin/users/add" />"
				class="btn btn-primary btn-sm" role="button">New User</a>
		</p>
	</sec:authorize>
	<div class="container">
		<table class="table table-striped table-bordered table-hover"
			id="mydataJson">
			<thead>
				<tr>
					<th>Email</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<sec:authorize access="hasRole('ADMIN')">
					<th></th></sec:authorize>
				</tr>
			</thead>
		</table>
	</div>
</div>

