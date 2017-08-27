<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8" />
<title>Spring Boot + JPA + Datatables</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script>
	$('#tableClient').DataTable({
		serverSide: true,
		ajax: '${pageContext.request.contextPath}/admin/json/all'
	});
</script>
</head>

<body>
	<div>
		<table id="tableClient" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Emp Id</th>
					<th>First Name</th>
					<th>Last Name</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>