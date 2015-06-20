<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="userName" value="<%=request.getAttribute(\"userName\")%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Click to connect</title>
</head>
<body>
	<%
    String fbURL = "https://www.facebook.com/dialog/oauth?client_id=925645240820290&redirect_uri=" + URLEncoder.encode("http://nfc-pickweed.rhcloud.com/response/handlesocial") + "&scope=email,publish_actions";
%>
<a href="<%= fbURL %>">click here</a>

<div id="user" style="display:none">welcome ${userName}</div>

<script type="text/javascript">
jQuery(document)
.ready(
		function() {
			if('${userName}'!='')
				document.getElementById('user').style.display='';
					
		});
</script>
</body>
</html>


