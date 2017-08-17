<%@page import="com.hubsport.service.CurrentTimeFormated"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Begin page content -->
<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right">
			<a href="<c:url value="/admin/events/add" />"
				class="btn btn-primary btn-sm" role="button">New Location</a>
		</p>
	</sec:authorize>
	<div class="container">
		<table class="table table-striped table-bordered table-hover" id="mydata">
			<thead>
				<tr>
					<th>Date and time</th>
					<th>Event Name</th>
					<th>Categories</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="120"></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${timetableList}" var="timetable">
					<tr>
						<td>${timetable.date.getTime()}</td>
						<td>${timetable.events.nameEvents}<br/>
						${timetable.events.places.namePlaces},${timetable.events.places.towns.nameTowns}(${timetable.events.places.towns.districts.nameDistrict})</td>
						<td>${timetable.events.categories.nameCat}</td>
						<td><a
							href="<c:url value='/admin/events/${timetable.id}/update' />"
							class="btn btn-success btn-sm">edit</a> <a
							href="<c:url value='/admin/events/${timetable.id}/delete' />"
							class="btn btn-danger btn-sm">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>