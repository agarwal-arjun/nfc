<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="userName" value="<%=request.getAttribute(\"userName\")%>" />

<div class="container">
	<div class="form-login">
		<h2 class="form-login-heading">Menu Options</h2>
		<div class="login-wrap">
			<button class="btn btn-round btn-primary center-block menutype"
				type="submit" id="cw">Call Waiter</button>
			<br> <br>
			<button class="btn btn-round btn-success center-block menutype"
				type="submit" id="plo">Place Order</button>
			<br> <br>
			<button class="btn btn-round btn-info center-block menutype"
				type="submit" id="po">Pay Online</button>
			<br> <br>
			<button class="btn btn-round btn-warning center-block menutype"
				type="submit" id="pc">Pay Cash</button>
			<br>
		</div>
	</div>
</div>
<script>
	$('.menutype').click(function() {
		var idtype = $(this).attr('id');
		if (idtype == 'cw') {
			alert('WIP..');
		} else if (idtype == 'plo') {
			var URL = "${context}/restaurant/card";
			j_loader_GET(URL, 'html', 'mainContainer');
		} else if (idtype == 'po') {
			var URL = "${context}/restaurant/payOnline";
			j_loader_GET(URL, 'html', 'mainContainer');
		} else if (idtype == 'pc') {
			alert('Cash');
		}
	});
</script>
