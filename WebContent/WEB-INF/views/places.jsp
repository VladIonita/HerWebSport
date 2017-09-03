<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Begin page content -->

<div class="container">

	<spring:url value="/admin/places/" var="userActionUrl" />

	<form:form class="form-horizontal" method="get" action="${userActionUrl}"  >

		<div class="form-group">
			<div class="col-sm-2">
				<label class="sr-only col-sm-2" for="district">District</label> 
				<select
					class="form-control" id="district" name="district" >
					<option></option>
					<c:forEach var="dist" items="${distList}">
						<option value="${dist.id}">${dist.nameDistrict}</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Filter</button>
			<b><a
				href="<c:url value="/admin/places" />">Reset</a></b>
			<div class="pull-right">
				<a href="<c:url value="/admin/places/add" />"
					class="btn btn-primary btn-sm" role="button">New Location</a>
			</div>
		</div>
	</form:form>



	<sec:authorize access="hasRole('ADMIN')">
		<p style="text-align: right"></p>
	</sec:authorize>
	<div class="container">
		<table class="table table-striped table-bordered table-hover"
			id="mydataPlacesJson">
			<thead>
				<tr>
					<th>Location</th>
					<th>Address</th>
					<th>Town</th>
					<th></th>
				</tr>
			</thead>
		</table>
	</div>
</div>