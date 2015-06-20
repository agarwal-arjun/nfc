<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="SID" value="<%=session.getId()%>" />
<c:set var="mType" value='<%=session.getAttribute("mType")%>' />
<c:set var="userName" value="<%=request.getAttribute(\"userName\")%>" />

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
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/style.css" rel="stylesheet">
<link href="../assets/sweetlib/sweet-alert.css" rel="stylesheet">
<link href="../owl/owl.carousel.css" rel="stylesheet">

<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/sweetlib/sweet-alert.min.js"></script>
<script src="../owl/owl.carousel.min.js"></script>

<script>
	window.glob = '${context}';
	window.onload = function() {
		loadHome();
	}

	function loadHome() {
		var val = ${mType};
		if (val == 2) {
			j_loader_GET("${context}/restaurant/deals", "html", "mainContainer");
		} else if (val == 3) {
			j_loader_GET("${context}/restaurant/deals", "html", "mainContainer");
		}
	}

	function loadList() {
		j_loader_GET("${context}/restaurant/card?cType=list", "html",
				"cardmenudisplay");
	}

	function loadGrid() {
		j_loader_GET("${context}/restaurant/card?cType=grid", "html",
				"cardmenudisplay");
	}

	function manageView() {
		var val = ${mType};
		if (val == 3) {
			$('.activeUser').remove();
		}
		
		$('.food_categories').slideUp(0);
// 		var activeSection=$('#activeSection').val();
// 		if(activeSection!=''){
// 			$('.navshow').each(function() {
// 				if($(this).attr('href')==activeSection){
// 					$(this).addClass('navSelected');	
// 				}else{
// 					$(this).removeClass('navSelected');
// 				}
// 			});
// 			$('.food_categories').slideUp(0);
// 			$(activeSection).slideDown(600);
// 		}
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

	function j_loader_POST(URL, DataType, DisplayID, Paramas) {
		var arr = Paramas.split('&');
		var dataValue = {}

		for (var a = 0; a < arr.length; a++) {
			var token = arr[0];
			var ind = token.indexOf('=');

			var key = token.substring(0, ind);
			var value = token.substring(ind + 1, token.length);

			dataValue[key] = value;
		}
		var request = $.ajax({
			url : URL,
			type : "POST",
			data : dataValue,
			dataType : DataType
		});

		request.done(function(msg) {
			$("#" + DisplayID).html(msg);
		});

		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
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
	<%--
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.3&appId=925645240820290";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	--%>
<!-- 	<div class='jbackground'></div> -->
	<div style="min-width: 340px;" id='mainContainer'></div>
	<div
		style="min-width: 340px; text-align: center; margin-top: 150px; display: none;"
		id='mainContainerLoading'>
		<div style="display: inline-block; width: 200px; text-align: center;">
			<img src='${context}/assets/img/orbit-loading.gif' class="loader_gif" />
		</div>
	</div>
	
	<%--
	<div class="fb-like"
		data-href="https://www.facebook.com/sicmbadshahpur?ref=bookmarks"
		data-width="200" data-layout="button_count" data-action="like"
		data-show-faces="true" data-share="true"></div>
	<div id="socialUrl">
		<%
		  String fbURL =
		      "https://www.facebook.com/dialog/oauth?client_id=925645240820290&redirect_uri="
		          + URLEncoder.encode("http://nfc-pickweed.rhcloud.com/response/handlesocial")
		          + "&scope=email,publish_actions";
		%>
		<a href="<%=fbURL%>">click here to login</a>
	</div>

	<div id="user" style="display: none"></div>
	--%>

	<input type="hidden" id="mType" value="${sessionScope.mType}">
	<input type="hidden" id="tableId" value="${sessionScope.request.tableId}">
	<input type="hidden" id='SID_TOKEN' value='${SID}' />
	<%--
	<script type="text/javascript">
	jQuery(document).ready(function() {
		var cVal = getName();
		if ('${userName}' != '' ) {
			document.getElementById("user").innerHTML = "welcome "+'${userName}';
			document.getElementById('user').style.display = '';
			document.getElementById('socialUrl').style.display = 'none';
		}else if(cVal != '' && cVal!=undefined){
			start=cVal.indexOf(":",cVal.indexOf("firstname"))+1;
			end=cVal.length-1;
			document.getElementById("user").innerHTML = "welcome "+cVal.substring(start,end);
			document.getElementById('user').style.display = '';
			document.getElementById('socialUrl').style.display = 'none';
		}

	});
	function getName() {
		if (document.cookie!='') {
			var cookie_val='';
			index = document.cookie.indexOf('social');
			
			if(index!=-1){
				namestart = (document.cookie.indexOf("=", index) + 1);
				nameend = document.cookie.indexOf(";", index);
				if (nameend == -1) {
					nameend = document.cookie.length;
				}
				cookie_val = document.cookie.substring(namestart, nameend);
				alert(cookie_val);
				return cookie_val;
			}
		}
	}
</script>
--%>
</body>
</html>