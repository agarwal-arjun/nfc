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
<link href="../assets/css/style.css" rel="stylesheet">
<link href="../assets/sweetlib/sweet-alert.css" rel="stylesheet">

<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/sweetlib/sweet-alert.min.js"></script>
<script type="text/javascript">
    if (window.location.hash && window.location.hash == '#_=_') {
        window.location.hash = '';
    }
    
    function saveMobile() {
    	var val=$('#mobile').val();
		if(isNaN(val)){
			$('#mobile').val('');
			$('#mobile').focus();
			$('#mobile_err').html('Please enter valid number');
			setTimeout(function() {
				$('#mobile_err').html('');
			}, 3000);
		}else if(val.length<10){
			$('#mobile_err').html('Please enter complete number');
			setTimeout(function() {
				$('#mobile_err').html('');
			}, 3000);
		}else{
			swal("Number saved!",
										"You will receive offer information",
										"success");
		}
	}
</script>
<style>
button.menutype {
	padding: 6px 89px;
}
</style>
</head>
<body>
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

	<div class='jbackground'></div>
	<div style="min-width: 320px;" id='mainContainer'>
		<div class="container">
			<div class="form-login">
				<div style='text-align:center;padding-top: 10px;'>
				<div>
					<span style='background-image: url(${LOGO_IMAGE});'
						class="social_plugin_logo"> </span> 
				</div>
				<div>
				<span style='font-size: 22px;'>${sessionScope.merchant.firstName} ${sessionScope.merchant.lastName}</span>
				</div>
				</div>
				<div style='text-align: center' class="login-wrap">
					<div style='padding-bottom:10px;display:none'>
					<div>
					For exciting offers, enter ur number
					</div>
					<div>
					<input type='text' name='mobile' id='mobile' style='width:205px;padding:10px;outline:none;text-align:center;font-size:14px;' placeholder='Mobile number'/>
					<input type='button' onclick='saveMobile()' value='OK' class='googleBtn'>
					</div>
					<div style='color:red;text-align:center;' id='mobile_err'>
					</div>
					</div>
					<div style="width:300px;">
						<div class="fb-page" data-href="${FB_LIKE_URL}"
							data-height="400px" data-hide-cover="false"
							data-show-facepile="true" data-show-posts="false">
							<div class="fb-xfbml-parse-ignore">
								<blockquote cite="${FB_LIKE_URL}">
									<a href="${FB_LIKE_URL}">
										<span style="text-align: center;" id='mainContainerLoading'>
											<span
												style="display: inline-block; width: 200px; text-align: center;">
												<img src='${context}/assets/img/orbit-loading.gif'
													class="loader_gif" />
											</span>
										</span>
									</a>
								</blockquote>
							</div>
						</div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="mType" value="${sessionScope.mType}">
	<input type="hidden" id="tableId"
		value="${sessionScope.request.tableId}">
	<input type="hidden" id='SID_TOKEN' value='${SID}' />
</body>
</html>