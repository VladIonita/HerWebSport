
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
<script
	src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.4/js/standalone/selectize.min.js" />"></script>


<script type="text/javascript" charset="utf-8">
	$('#mydataUserJson')
			.DataTable(
					{
						serverSide : true,
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
							"data" : "id",
							"render" : function(data, type, row) {
								console.log(data, type, row);
								return '<a href="<c:url value="/admin/users/update/'+data+'"/>" class="btn btn-success btn-sm">edit</a> <a href="<c:url value="/admin/users/delete/'+data+'"/>" class="btn btn-danger btn-sm">delete</a>';
							},
						} ]
					});
	$('#mydataPlacesJson')
			.DataTable(
					{
						serverSide : true,
						searching : false,
						ajax : '${pageContext.request.contextPath}/${jsonLink}',
						columns : [
								{
									data : 'placeName'
								},
								{
									data : 'placeAddress'
								},
								{
									data : 'townName',
									"className" : "left",
									"render" : function(data, type, full, meta) {
										return full.townName + ' ('
												+ full.district + ')';
									}
								} ],
						"columnDefs" : [ {
							"targets" : 3,
							"data" : "id",
							"render" : function(data, type, row) {
								console.log(data, type, row);
								return '<a href="<c:url value="/admin/places/update/'+data+'"/>" class="btn btn-success btn-sm">edit</a> <a href="<c:url value="/admin/places/delete/'+data+'"/>" class="btn btn-danger btn-sm">delete</a>';
							},
						} ]
					});

	$('#district').selectize({
		selectOnTab : true,
		placeholder : 'All Towns'

	});
	$('#districtAndTowns').selectize({
		selectOnTab : true,
		placeholder : 'All Towns',
		valueField: 'id',
        labelField: 'townName',
        searchField: ['townName'],
        create: false,
        onType: function(str) {
            this.clearOptions();
        },
        onDropdownClose: function(items) {
            if (!this.items.length) {
                this.clearOptions();
            }
        },
        render: {
            option: function(item, escape) {
                return '<div>' + escape(item.townName) + " (" + escape(item.district) + ")" + '</div>';
            }
        },
        score: function(search) {
            return function(item) {
                return 1;
            };
        },
        load: function(query, callback) {
            if (!query.length || query.length < 3) return callback();
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/places/towns/' + encodeURIComponent(query),
                type: 'GET',
                dataType: 'json',
                error: function() {
                    callback();
                },
                success: function(res) {
                    callback(res);
                }
            });
        }
	});
</script>

<script type="text/javascript" charset="utf-8">
	var $table = $('#mydata');

	if ($table.length) {
		$table.DataTable();
	}
</script>
</body>
</html>