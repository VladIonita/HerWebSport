<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- Begin page content -->
<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right">
			<a href="<c:url value="/admin/users/newuser" />"
				class="btn btn-primary" role="button">New User</a>
		</p>
	</sec:authorize>

	<div class="page-header">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Username</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="100"></th>
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="100"></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/admin/users/edit-user-${user.username}' />"
								class="btn btn-success custom-width">edit</a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a
								href="<c:url value='/admin/users/delete-user-${user.username}' />"
								class="btn btn-danger custom-width">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

