<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="SID" value="<%=session.getId()%>" />
<c:set var="mType" value='<%=session.getAttribute("merchant")%>' />

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="${context}/images/favicon.ico"
	type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

<meta name="description" content="">
<meta name="author" content="Nikata, Restaurant, Dining, Food, Drinks">
<meta name="keyword" content="Nikata, Restaurant, Dining, Food, Drinks">

<title>Restaurant Menu</title>
<link href="../assets/css/style.css" rel="stylesheet">
<script src="../assets/js/jquery.min.js"></script>
<script>
	window.glob = '${context}';
	window.onload = function() {
		loadHome();
	}

	function loadHome() {
		j_loader_GET("${context}/restaurant/social_ui", "html", "mainContainer");
	}

	function j_loader_GET(URL, DataType, DisplayID) {
		$('#' + DisplayID).hide();
		$('#mainContainerLoading').show();
		var req = $.ajax({
			url : URL,
			beforeSend : function(request) {
				request.setRequestHeader("SID", getSID());
			},
			type : "GET",
			dataType : DataType
		});

		req.done(function(msg) {
			$("#" + DisplayID).html(msg);
			setTimeout(function() {
				$('#mainContainerLoading').hide();
				$('#' + DisplayID).show();
			}, 500);
		});

		req.fail(function(jqXHR, textStatus) {
			sweetAlert("Oops!!", "Something went wrong, Try reloading page!",
					"error");
		});
	}


	function getSID() {
		return $("#SID_TOKEN").val();
	}
</script>
<style>
button.menutype {
	padding: 6px 89px;
}
</style>
</head>
<body>
	<div class='jbackground'></div>
	<div style="min-width: 340px;" id='mainContainer'></div>
	<div
		style="min-width: 340px; text-align: center; margin-top: 150px; display: none;"
		id='mainContainerLoading'>
		<div style="display: inline-block; width: 200px; text-align: center;">
			<img src='${context}/assets/img/orbit-loading.gif' class="loader_gif" />
		</div>
	</div>
	
	<input type="hidden" id="mType" value="${sessionScope.mType}">
	<input type="hidden" id="tableId" value="${sessionScope.request.tableId}">
	<input type="hidden" id='SID_TOKEN' value='${SID}' />
</body>
</html>