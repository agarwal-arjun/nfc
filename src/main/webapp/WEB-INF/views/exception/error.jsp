<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Nikata </title>

<link href="${contextPath}/assets/css/main.css" rel="stylesheet"
	type="text/css" />

<div id="container1">
	<div id="error" align="center" class="red" style="color: red"></div>
	<div id="topHeading_login">
		<div style="width: 22px; float: left;"></div>
		<div style="float: right; width: 350px; padding-top: 5px;">Message</div>
	</div>
	<div id="passInnerChange">

		<center>
			<div style="color: #ff0000; font-size: 18px; margin-top: 80px;">Something
				went wrong, please try later</div>
		</center>

		<br />


	</div>
	
</div>