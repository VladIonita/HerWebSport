<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Begin page content -->
<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right">
			<a href="<c:url value="/admin/users/add" />"
				class="btn btn-primary btn-sm" role="button">New User</a>
		</p>
	</sec:authorize>
	<div class="container">
		<table class="table table-striped table-bordered table-hover" id="mydata">
			<thead>
				<tr>
					<th>Username</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="120"></th>
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
						 <td><a href="<c:url value='/admin/users/update/${user.id}' />" class="btn btn-success btn-sm">edit</a>
                          <a href="<c:url value='/admin/users/delete/${user.id}' />" class="btn btn-danger btn-sm">delete</a></td>
                        </sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

