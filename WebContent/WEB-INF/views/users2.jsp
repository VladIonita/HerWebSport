<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
	<div class="container">
		<%@ include file="adminHeader.jsp"%>
	</div>
	<!-- Begin page content -->
	<div class="container" style="margin-top: 20px">
		<jsp:useBean id="pagedList" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="/users2" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<sec:authorize access="hasRole('ADMIN')">
			<p style="text-align: right">
				<a href="<c:url value="/registration" />" class="btn btn-primary"
					role="button">Adauga</a>
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
					<c:forEach items="${pagedList.pageList}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/edit-user-${user.username}' />"
									class="btn btn-success custom-width">edit</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a
									href="<c:url value='/delete-user-${user.username}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedLink="${pagedLink}" pagedList="${pagedList}"></tg:paging>

		</div>

	</div>




</body>
</html>