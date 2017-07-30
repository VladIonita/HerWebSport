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

<title>HUBSPORT</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="<c:url value="/resources/css/ie10-viewport-bug-workaround.css" />"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/sticky-footer-navbar.css" />"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>


	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">HUBSPORT</a>
			</div>
			<div class="navbar-collapse collapse navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/">HOME</a></li>
					<!-- 		<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">PAGES <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="blog.html">BLOG</a></li>
							<li><a href="single-post.html">SINGLE POST</a></li>
							<li><a href="portfolio.html">PORTFOLIO</a></li>
							<li><a href="single-project.html">SINGLE PROJECT</a></li>
						</ul></li>  -->
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>



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