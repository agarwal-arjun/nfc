<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="userName" value="<%=request.getAttribute(\"userName\")%>" />
<script type="text/javascript">
    if (window.location.hash && window.location.hash == '#_=_') {
        window.location.hash = '';
    }
</script>
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
		<div style='text-align:center' class="login-wrap">
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

			<div style='display:inline-block' id="socialUrl">
				<a href="${FB_LOGIN}">
				<img src='../images/loginBtn.png'/>
				</a>
			</div>

			<div id="user"></div>


			<br>
		</div>
	</div>
</div>
