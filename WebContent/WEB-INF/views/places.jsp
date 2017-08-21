<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Begin page content -->
<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right">
			<a href="<c:url value="/admin/places/add" />"
				class="btn btn-primary btn-sm" role="button">New Location</a>
		</p>
	</sec:authorize>
	<div class="container">
		<table class="table table-striped table-bordered table-hover"
			id="mydata">
			<thead>
				<tr>
					<th>Location</th>
					<th>Address</th>
					<th>Town</th>
					<th>District</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="120"></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${placesList}" var="places">
					<tr>
						<td>${places.namePlaces}</td>
						<td>${places.address}</td>
						<td>${places.towns.nameTowns}</td>
						<td>${places.towns.districts.nameDistrict}</td>
						<td><a
							href="<c:url value='/admin/places//update${places.id}' />"
							class="btn btn-success btn-sm">edit</a> <a
							href="<c:url value='/admin/places//delete${places.id}' />"
							class="btn btn-danger btn-sm">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>