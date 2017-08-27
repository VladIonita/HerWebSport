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
				<span aria-hidden="true">�</span>
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
					<th></th>
				</tr>
			</thead>
			<!-- <tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.email}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a
								href="<c:url value='/admin/users/update/${user.id}' />"
								class="btn btn-success btn-sm">edit</a> <a
								href="<c:url value='/admin/users/delete/${user.id}' />"
								class="btn btn-danger btn-sm">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody> -->
		</table>
	</div>
</div>

