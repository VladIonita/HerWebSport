<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">

	<c:choose>
		<c:when test="${placeForm['new']}">
			<h1>Add Place</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Place</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/admin/places/add" var="placesActionUrl" />

	<form:form class="form-horizontal" method="post"
		modelAttribute="placeForm" action="${placesActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="namePlaces">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="namePlaces" type="text" class="form-control"
						id="name" placeholder="Name" />
					<form:errors path="namePlaces" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<form:input path="address" type="text" class="form-control"
						id="address" placeholder="Address" />
					<form:errors path="address" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="towns.nameTowns">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Town</label>
				<div class="col-sm-10">
					<form:input path="towns.nameTowns" type="text" class="form-control"
						placeholder="Town" />
					<form:errors path="towns.nameTowns" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group" id="casc">
			<label class="col-sm-2 control-label">District select</label>
			<div class="col-sm-10">
				<form:select path="towns.districts.nameDistrict" items="${distList}"
					itemValue="id" itemLabel="nameDistrict" class="form-control" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${placeForm['new']}">
						<input type="submit" value="Register"
							class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/places/list' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/admin/places/list' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>
