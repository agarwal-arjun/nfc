<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Nikata, Restaurant, Dining, Food, Drinks">
<meta name="keyword" content="Nikata, Restaurant, Dining, Food, Drinks">

<title>Restaurant Menu</title>
<link href="${context}/assets/css/bootstrap.css" rel="stylesheet">
<link href="${context}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href="${context}/assets/css/style.css" rel="stylesheet">
<link href="${context}/assets/css/style-responsive.css" rel="stylesheet">
<script src="${context}/assets/js/jquery-1-9-1.js"></script>
<script src="${context}/assets/js/bootstrap.min.js"></script>
<script src="${context}/assets/js/jquery.nicescroll.js"></script>
</head>
<body>
	<div style="min-width: 320px;padding: 5px;" id='mainContainer'>
		<div style="margin-top: 30px"></div>
		<div style="background: orange;padding-bottom: 20px;" class="containbg container">
			<div style="display: inline-block;">
				<div class="centered">
					<h3>TOTAL AMOUNT</h3>
					<h5>${amount}</h5>
					<h3>ORDER ID</h3>
					<h6>${orderId}</h6>
					<h3>Status</h3>
					<h5>${status}</h5>
					<h3>Bank Txn Id</h3>
					<h6>${bankTxnId}</h6>

				</div>
					<h2>HAVE A GOOD DAY!</h2>
				<div style="margin-top: 10px;">
					<button style="max-width: 300px;"
						class="btn btn-success btn-block animateBtn" type="button"
						onclick='window.location.href="${context}/restaurant/home?tableId=1001&merchantId=2000"'>Home</button>
				</div>
			</div>
		</div>
	</div>
	<div
		style="min-width: 340px; text-align: center; margin-top: 150px; display: none;"
		id='mainContainerLoading'>
		<div style="display: inline-block; width: 200px; text-align: center;">
			<img src='${context}/assets/img/orbit-loading.gif' class="loader_gif" />
		</div>
	</div>
	<script type="text/javascript"
		src="${context}/assets/js/jquery.backstretch.min.js"></script>
	<script>
		$.backstretch("${context}/assets/img/dining-bg.jpg", {
			speed : 500
		});
		$(document).ready(function() {
			$("html").niceScroll();
		});
	</script>
	<input type="hidden" id='SID_TOKEN' value='${SID}' />
</body>
</html>



