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
		<table class="table table-striped table-bordered table-hover" id="mydataEventsJson">
			<thead>
				<tr>
					<th>Date and time</th>
					<th>Event Name</th>
					<th>Categories</th>
					<th></th>
				</tr>
			</thead>
		</table>
	</div>
</div>