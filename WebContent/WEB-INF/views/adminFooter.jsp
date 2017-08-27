
<footer class="footer">
	<div class="container">
		<p class="text-muted">Footer content.</p>
	</div>
</footer>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script
	src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"></script>


<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script
	src="<c:url value="/resources/js/dataTables.bootstrap.min.js" />"></script>

<script type="text/javascript">
	$('#mydataJson')
			.DataTable(
					{
						serverSide : true,
						ordering : false,
						searching : false,
						ajax : '${pageContext.request.contextPath}/admin/users/all',
						columns : [ {
							data : 'email'
						}, {
							data : 'firstName'
						}, {
							data : 'lastName'
						} ],
						"columnDefs" : [ {
							"targets" : 3,
							"data": "id",
							"render" : function(data, type, row) {
								console.log(data, type, row);
								return '<a href="<c:url value="/admin/users/update/'+data+'"/>" class="btn btn-success btn-sm">edit</a><a href="<c:url value="/admin/users/delete/'+data+'"/>" class="btn btn-danger btn-sm">delete</a>';
							},
						} ]
					});
</script>

<script type="text/javascript">
	var $table = $('#mydata');

	if ($table.length) {
		$table.DataTable();
	}
</script>
</body>
</html>