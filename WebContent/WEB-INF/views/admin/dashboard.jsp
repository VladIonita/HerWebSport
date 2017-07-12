<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
	href="<c:url value="/resources/ico/favicon.ico" />">

<title>Dashboard</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/modernizr.js" />"></script>

</head>
<body>

	<%@ include file="loginHeader.jsp"%>







	<!-- *****************************************************************************************************************
	 HEADERWRAP
	 ***************************************************************************************************************** -->
	<div id="headerwrap">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<form>
					<div class="alert alert-danger">
						<h1>Welcome to dashboard</h1>
					</div>
					<div class="input-group input-sm">
						Dear <strong>${user}</strong>, Welcome to Admin Page. <a
							href="<c:url value="/logout" />">Logout</a>
					</div>
					</form>


				</div>
				<div class="col-lg-8 col-lg-offset-2 himg"></div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /headerwrap -->







	<%@ include file="loginFooter.jsp"%>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/retina-1.1.0.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.hoverdir.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.hoverex.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.prettyPhoto.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.isotope.min.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>

	<script>
		// Portfolio
		(function($) {
			"use strict";
			var $container = $('.portfolio'), $items = $container
					.find('.portfolio-item'), portfolioLayout = 'fitRows';

			if ($container.hasClass('portfolio-centered')) {
				portfolioLayout = 'masonry';
			}

			$container.isotope({
				filter : '*',
				animationEngine : 'best-available',
				layoutMode : portfolioLayout,
				animationOptions : {
					duration : 750,
					easing : 'linear',
					queue : false
				},
				masonry : {}
			}, refreshWaypoints());

			function refreshWaypoints() {
				setTimeout(function() {
				}, 1000);
			}

			$('nav.portfolio-filter ul a').on('click', function() {
				var selector = $(this).attr('data-filter');
				$container.isotope({
					filter : selector
				}, refreshWaypoints());
				$('nav.portfolio-filter ul a').removeClass('active');
				$(this).addClass('active');
				return false;
			});

			function getColumnNumber() {
				var winWidth = $(window).width(), columnNumber = 1;

				if (winWidth > 1200) {
					columnNumber = 5;
				} else if (winWidth > 950) {
					columnNumber = 4;
				} else if (winWidth > 600) {
					columnNumber = 3;
				} else if (winWidth > 400) {
					columnNumber = 2;
				} else if (winWidth > 250) {
					columnNumber = 1;
				}
				return columnNumber;
			}

			function setColumns() {
				var winWidth = $(window).width(), columnNumber = getColumnNumber(), itemWidth = Math
						.floor(winWidth / columnNumber);

				$container.find('.portfolio-item').each(function() {
					$(this).css({
						width : itemWidth + 'px'
					});
				});
			}

			function setPortfolio() {
				setColumns();
				$container.isotope('reLayout');
			}

			$container.imagesLoaded(function() {
				setPortfolio();
			});

			$(window).on('resize', function() {
				setPortfolio();
			});
		})(jQuery);
	</script>
</body>
</html>